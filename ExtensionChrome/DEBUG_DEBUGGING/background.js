import {holaDesdeModulo} from './library_test.js'
holaDesdeModulo();



chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
	document.onload = () => {document.getElementsByTagName("button")[0].onclick = crearTab}
});


//params.response.method == 'GET' &&
var request_Almacenar = []
var ventanas_attached = []
var urls_sitios = {
    "linkedin" : "https://www.linkedin.com/voyager/api/graphql?variables=(jobCardPrefetchQuery"
}

function adjuntar_network_listener(tabid){
    
    chrome.debugger.sendCommand({tabId: tabid}, "Network.enable", {}, () => {

        chrome.debugger.onEvent.addListener((debuggeeId, message, params) => {

            if (message === 'Network.responseReceived') {
                if( params.response.status == 200 && params.response.url.startsWith(urls_sitios.linkedin)){
                    request_Almacenar.push(params.requestId)
                }
            }

            if(message == 'Network.loadingFinished' && request_Almacenar.length > 0){
                let encontrado = false
                
                for(var index in request_Almacenar){
                    if(request_Almacenar[index] == params.requestId){
                        encontrado = true
                        request_Almacenar.splice(index, 1) 
                        break;
                    }
                }

                if(!encontrado){
                    return;
                }

                chrome.debugger.sendCommand({tabId: tabid}, "Network.getResponseBody", {requestId: params.requestId}, (response) => {

                    console.log(response.body);
                });
          
            }


        });

    });

    
}

function adjuntar_debugger(tabid){

    if(ventanas_attached.includes(tabid)){
        console.log('El debugger ya se encuentra en la ventana', tabid)
        return;
    }
    //Al estar dentro de una funcion, los parametros con nombre dan error, ej: targer = {tabid : tabid}
    chrome.debugger.attach({tabId : tabid}, "1.3", () => {
        adjuntar_network_listener(tabid)       
        ventanas_attached.push(tabid)




        console.log('se ha adjuntado el listener correctamente en ', tabid)
    })    
        
    chrome.debugger.onDetach.addListener((source, reason) => {
        if(source == ... && reason == devtools.open){
            //esperar a que devtools se cierre para volver a adjuntar
        }

    })
    
    
}



function crearTab(){
    chrome.tabs.create(
        {
            active : true,
            url : "https://www.linkedin.com/jobs/search/?currentJobId=3428910276&f_TPR=r604800&geoId=100690236&keywords=SQL%20OR%20DATA&location=Regi%C3%B3n%20Metropolitana%20de%20Santiago%2C%20Chile&refresh=true&sortBy=DD"
        }, (tab) => {
            adjuntar_debugger(tab.id)
        })

}
   
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
    console.log('linkedin se ha abierto')
    adjuntar_debugger(sender.tab.id)
});

