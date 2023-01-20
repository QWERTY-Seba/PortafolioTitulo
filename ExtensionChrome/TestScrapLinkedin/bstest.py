# -*- coding: utf-8 -*-
"""
Created on Thu Jul  7 19:44:40 2022

@author: Seba
"""

from bs4 import BeautifulSoup
import requests
import time
import pandas
import re
import json

url = r"https://www.linkedin.com/jobs/search/?geoId=104621616&f_TPR=r2592000&keywords=SQL%2BPython&location=Chile&start={nPagina}&location=Regi%C3%B3n%20Metropolitana%20de%20Santiago%2C%20Chile"


documento = ""

savedOfertasIds_texto = open('idsOfertasBD.csv','r').read()
savedOfertasIds = []
if len(savedOfertasIds_texto) > 0:
    savedOfertasIds = list(map(int,savedOfertasIds_texto.split(';')))
ofertasDict = []
# result = open('testing.html', 'r', encoding='utf-8').read()    

for paginaActual in range(0,5):
    
    result = requests.get(url.format(nPagina = paginaActual * 25))
    documento = BeautifulSoup(result.text, "html.parser")

    ofertas = []
    ofertas = documento.select('#main-content > section.two-pane-serp-page__results-list > ul > li > div')
    oferta = ''
    
    for o in ofertas:
        ofertaD = {
                'id' : 0,
                'cargo' : '',
                'empresa' : '',
                'url' : '',
                'descripcion' : ''
                }
        
        ofertaD['url'] = o.a.get('href').strip()    
        
        try:         
            ofertaD['id'] = re.search('\d+',o.get('data-entity-urn')).group()           
            
            if ofertaD['id'] not in savedOfertasIds:
                
                request_sub = requests.get(ofertaD['url'])
                
                oferta = BeautifulSoup(request_sub.text , "html.parser")
            
                ofertaD['empresa'] = o.select('div.base-search-card__info > h4 > a')[0].get_text().strip()
                ofertaD['cargo'] = o.select('div.base-search-card__info > h3')[0].get_text().strip()        
                
                ofertaD['descripcion'] = oferta.select('#main-content > section.core-rail > div > div.decorated-job-posting__details > section.core-section-container.my-3.description > div > div.description__text.description__text--rich')[0].get_text()
                
                savedOfertasIds.append(ofertaD['id'])
                ofertasDict.append(ofertaD)       
            time.sleep(0.3)
        except:
            print("Error en la oferta id: " + ofertaD['id'],ofertaD['url'],request_sub)
            raise
        time.sleep(0.5)

with open('idsOfertasBD.csv','a') as f:
    f.write(';'.join(str(i) for i in savedOfertasIds))
with open('OfertasBD.json','a') as f:
    f.write(json.dumps(ofertasDict))





# from IPython.core.display import display, HTML
# display(HTML(documento))



# url = "https://www.newegg.com/msi-geforce-rtx-3080-rtx-3080-gaming-z-trio-12g-lhr/p/N82E16814137711?Item=N82E16814137711"
# url = "https://www.lacuarta.com/"
# url = r"https://piranha.cl/202-semillas?page={npag}"
# sacarmaxurl = requests.get(url.format(npag=3000))
# ultima_pag = re.match(r".*page=(\d+).*",sacarmaxurl.url).group(1)


# # int(ultima_pag)
# dataset = pandas.DataFrame()
# for i in range(1,int(ultima_pag)):

#     result = requests.get(url.format(npag=i))
#     documento = BeautifulSoup(result.text, "html.parser") 
#     documento.prettify()
    

#     r_seeds = documento.select("div.products.row.products-grid > div")

#     for cosa in r_seeds:
        
#         a1 = cosa.find("h3", class_="h3 product-title").a.get_text()
        
#         a2 = cosa.find("span",class_="product-price").get_text()
        
#         fila = pandas.Series([a1,a2])
        
#         dataset = dataset.append(fila,ignore_index=True)
    
#     time.sleep(0.1)


# dataset = dataset.set_axis(['Nombre','Precio'], axis=1, inplace=False)
# print(dataset)
# dataset.to_csv("weas.csv")




