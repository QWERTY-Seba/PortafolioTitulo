/*
por hacer

ejecutar un script cuando se entra a la pagina que extraiga las ids de las descartadas y sacar las publicaciones que tengan la id--

consultar el webservice de google, direcciones de la empresa y guardar las coordenadas

en la lista de ofertas, agregar un boton para descartar ofertas manualmente--

en la lista de ofertas, agregar un mapa para mostrar las coordenadas guardadas

guardar las fotos de empresa

agregar un script que recorra la base y descarte las ofertas,

agregar script para extraer los años requeridos por oferta

arreglar las peticiones duplicadas --

agregar compatibilidad con duoc y computrabajo

listar las solicitudes en la lista de ofertas

al volver Atras, se pierden los descartados y el divDescartados no vuelve a aparecer

no se extraen los datos cuando se realizan busquedas personalizadas fuera del jobs

al descartar en pagina, agregar un color por tipo de descarte para evitar confusion

agregar, buscar ofertas en la bd cuando se busca fuera de linkedin
*/



var dias_expiracion = 30
var palabras_baneadas = ['cloud architect', 'back-end', 'front-end', 'android', 
						'node[.]js', '[.]net', 'django', 'react[.]js', 'vue[.]js',
						'front end', 'back end', 'devops', 'backend', 'frontend',
						'intern', 'sr', 'senior', 'jefe', 'usd', 'oracle', 'lider', 
						'product owner', 'lead', 'practica', 'site reliability engineer', 
						'sre', 'software engineer', 'data scientist', 'fullstack', 'full stack',
						'us[$]','scrum master']
var reg = new RegExp(/\d+/)
//var regex = /(?:al menos (?:de )?|entre )?(?:(\d)|(\d) [aoy] \d|(\d)-\d) a[ññ]{1,2}o[s]?(?:(?:(?: de)? experiencia)?)/guim;
var regex = /(?:Al menos|mínimo|Desde|Experiencia (?:de|de al menos|mínima de)) (?:\d{1,2}|\d{1,2}[aoy\- ]*\d{1,2}) año[s]? (?:de experiencia(?: en el cargo)?|en cargos similares)/guim///.{20}\d año[s]?.{20}/guim;

/*
Empresas a Descartar 
revelo
Oowlish
Apiux Tecnologia

*/

const imagen_compania = {
	id_imagen: "",
	uri_imagen: "",
	id_compania: 0
}

const oferta = {
	id_compania: 0,
	id_empleo: 0,
	cargo: "",
	empresa: "",
	localizacion_empleo: "",
	fecha_publicacion: "",
	cantidad_solicitudes: [],
	descripcion_empleo: "",
	link_incripcion: "",
	link_oferta: "",
	tipo_jornada: "",
	tipo_modalidad: "",
	fecha_recoleccion_registro: "",
	pagina_recoleccion: "",
	oferta_repetida: false,
	uri_foto_empresa: "",
	nombre_publicador: "",
	link_empresa: "",
	aplicado: false,
	tipo_solicitud: "",
	info_empresa: "",
	descartada: false,
	coordenadas: "",
	fecha_descarte: 0,
	tipo_descarte: {},
	esta_en_ingles: false

}

function regTest(fila, Rex) {
	var respuesta = fila.primaryDescription.text.match(new RegExp(Rex, 'giu'))
	if (respuesta != null) {
		return respuesta[0]
	}
	return ""
}

function getSafe(fn, defaultVal) {
	try {
		return fn();
	} catch (e) {
		return defaultVal;
	}
}

function objectoEstaVacio(objeto) {
	return Object.getOwnPropertyNames(objeto).length == 0

}


function obtenerIdOferta() {
	return reg.exec(fila.trackingUrn)[0]
}
/*
	Enviar la fecha en formato epoch
*/
function estaExpirada(fecha_publicacion) {
	var someDate = new Date();
	var result = someDate.setDate(someDate.getDate() + dias_expiracion);
	return result > fecha_publicacion
}

function quitarAcentos(texto) {
	return texto.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
}

function pintarBaneado(id_oferta) {
	var script = `document.getElementById("divDescartados_id").appendChild(document.querySelector('li[data-occludable-job-id="${id_oferta}"]'))`
	chrome.devtools.inspectedWindow.eval(script, { useContentScriptContext: true })
}

let regx = new class {
	//Las palabras se podria pasar a un config o al storage para que sea dinamico y modificable desde js

	expresion = palabras_baneadas.join('|')
	Ofertasregex = new RegExp(this.expresion, 'gium')

	AciertosMatch(palabra, InputRegex) {
		let respuesta = palabra.match(new RegExp(InputRegex, 'giu'))
		if (respuesta) {
			return respuesta[0]
		}
	}

	cargoBaneado(titulo) {
		titulo = quitarAcentos(titulo)
		let respuesta = this.Ofertasregex.exec(titulo)
		console.log('Probando regex titulo con', titulo, respuesta)
		return respuesta != null
	}
}


/*
	Enviar texto literal
*/
function DuocLaboral(Respuesta) {


}

function Computrabajo() {

}

function Talent() {

}



async function Linkedin(respuesta) {
	var Lista_oferta = {}
	var tiempo_llamado = new Date().getTime()
	const resp = JSON.parse(respuesta.replaceAll("\n", "\\n"))

	for (var i = 0, len = resp.included.length; i < len; ++i) {
		let fila = resp.included[i]
		let id_oferta = 0
		let id_compania = 0

		//Quitar o comentar los case sin codigo y agregar un else break
		switch (fila.$type) {
			case 'com.linkedin.voyager.dash.jobs.JobPosting':
				//Cambiar a match
				id_oferta = reg.exec(fila.trackingUrn)[0]

				//Buscar id en la bd de ofertas
				//Si existe, adjuntar la cantidad de solicitudes actuales
				//Si no existe, continuar con el proceso
				let saltar_oferta = false;

				await chrome.storage.local.get(id_oferta).then((oferta) => {

					if (!objectoEstaVacio(oferta)) {
						console.log('oferta ya existente, saltando ', id_oferta)
						saltar_oferta = true;
						if (oferta[id_oferta].descartada) {
							pintarBaneado(id_oferta)
						}
					}
				})
				/*
var regex = /.{0,50}\d{1,3} año[s]?.{0,50}/guim
chrome.storage.local.get(null, lista => {
	Object.entries(lista).forEach(oferta => {
		let match;
		while((match = regex.exec(oferta[1].descripcion_empleo) ) !== null){
			console.log(match[0])    
		}
	})
})
print('\n'.join(exrex.generate('(?:Al menos|mínimo|Desde|Experiencia (?:de|de al menos|minima de)) (?:\d{1,2}|\d{1,2}[aoy\- ]*\d{1,2}) año[s]? (?:de experiencia|de experiencia en el cargo|en cargos similares)')))

(?:Al menos|mínimo|Desde|Experiencia (?:de|de al menos|minima de)) (?:\d{1,2}|\d{1,2}[aoy\- ]*\d{1,2}) año[s]? (?:de experiencia|de experiencia en el cargo|en cargos similares)
(?:Al menos|mínimo|Desde|Experiencia de|Experiencia de al menos|Experiencia mínima de) (?:\d{1,2}|\d{1,2}[aoy\- ]*\d{1,2}) año[s]? (?:de experiencia|de experiencia en el cargo|en cargos similares)
(?:mínimo |al menos |Experiencia(?: laboral)? mínima (?:de )?|entre )[+]?\d
(?:\d{1,2}|\d{1,2}[aoy\- ]*\d{1,2}) año[s]?
(?:Al menos|mínimo|Desde|Experiencia de|Experiencia de al menos|Experiencia mínima de) \d año[s]? (?:de experiencia|de experiencia en el cargo|en cargos similares)
(?:(\d)|(\d) [aoy] \d|(\d)-\d) a[ññ]{1,2}o[s]?(?:(?:(?: de)? experiencia)?)
*/


				let match;
				let HTML_Lista_Experiencia = "";
				
				while ((match = regex.exec(fila.description.text)) !== null) {
					HTML_Lista_Experiencia += `<li class='job-card-list__insight' >${match[0]}</li>`
				}

				console.debug('Resultado años exp', HTML_Lista_Experiencia)

				chrome.devtools.inspectedWindow.eval(
					`claseTestSeba.insertarAñosExpDiv(${id_oferta},"${HTML_Lista_Experiencia}")`, { useContentScriptContext: true },
					(result, exceptionInfo) => {
						console.log(exceptionInfo)
					}
				)

				if (saltar_oferta) {
					break;
				}



				var oferta_temp = Object.assign({}, oferta)
				oferta_temp.tipo_descarte = {
					expirada: false,
					tecnologias: false,
					annos_exp: false,
					cargo: false,
					distancia_oficina: false,
					manual: false,
					empresa: false
				}


				if (regx.cargo_Baneado(fila.title)) {
					oferta_temp.tipo_descarte.cargo = true
					oferta_temp.descartada = true
					pintarBaneado(id_oferta)

				}

				oferta_temp.id_empleo = id_oferta


				//Quitar acentos 
				oferta_temp.descripcion_empleo = fila.description.text


				//Ejecutar el script de regex en la descripcion del empleo y para cada resultado adjuntarlo a un div creado, dentro de la oferta correspondiente



				oferta_temp.pagina_recoleccion = "Linkedin"
				oferta_temp.cargo = fila.title

				oferta_temp.fecha_recoleccion_registro = tiempo_llamado
				Lista_oferta[id_oferta] = oferta_temp
				console.debug(Lista_oferta[id_oferta])
				break;

			case 'com.linkedin.voyager.dash.jobs.JobPostingCard':
				//En este punto siempre esta la oferta
				id_oferta = reg.exec(fila.entityUrn)[0]

				if (Lista_oferta[id_oferta] == null) {
					await chrome.storage.local.get(id_oferta).then((oferta) => {
						try {
							oferta[id_oferta].cantidad_solicitudes.push({
								cantidad_solicitudes: regx.AciertosMatch(fila.primaryDescription.text, /(\d+)(?= solicitud)/),
								fecha_extraccion: tiempo_llamado
							})
						} catch (error) {
							console.error('Error en la asignacion del cant solicitudes', Lista_oferta, id_oferta, error)
						}
						chrome.storage.local.set(oferta)
					})
					break;
				}

				try {
					Lista_oferta[id_oferta].empresa = regx.AciertosMatch(fila.primaryDescription.text, /^([\s\.\wÀ-ÿ]*\b)/)
					Lista_oferta[id_oferta].localizacion_empleo = regx.AciertosMatch(fila.primaryDescription.text, /(\b[\s\w,À-ÿ]*chile)/)
					Lista_oferta[id_oferta].tipo_modalidad = regx.AciertosMatch(fila.primaryDescription.text, /(híbrido|remoto|presencial)/)

					
					Lista_oferta[id_oferta].cantidad_solicitudes = [{
						cantidad_solicitudes: regx.AciertosMatch(fila.primaryDescription.text, /(\d+)(?= solicitud)/),
						fecha_extraccion: tiempo_llamado
					}]


					//Buscar el epoch 
					for (var modulo of fila.primaryDescription.attributesV2) {
						var epoch = modulo.detailData.epoch
						if (epoch) {
							Lista_oferta[id_oferta].fecha_publicacion = epoch.epochAt
							break;
						}
					}

					Lista_oferta[id_oferta].link_empresa = getSafe(() => fila.logo.actionTarget, '')
					Lista_oferta[id_oferta].info_empresa = getSafe(() => fila.jobInsightsV2ResolutionResults[1].insightViewModel.text.text, '')
					Lista_oferta[id_oferta].tipo_jornada = getSafe(() => fila.jobInsightsV2ResolutionResults[0].insightViewModel.text.text, '')
					Lista_oferta[id_oferta].id_compania = reg.exec(fila.logo.attributes[0].detailData["*companyLogo"])[0]



				} catch (error) {
					console.error(error, fila)
				}
				break;
			case 'com.linkedin.voyager.dash.jobs.JobSeekerApplicationDetail':

				id_oferta = reg.exec(fila.entityUrn)[0]
				if (Lista_oferta[id_oferta] == null) {
					break;
				}

				Lista_oferta[id_oferta].link_incripcion = fila.companyApplyUrl

				break;
			case 'com.linkedin.voyager.dash.organization.Company':
				let id_imagen = fila.logoResolutionResult.vectorImage.digitalmediaAsset.match(/urn:li:digitalmediaAsset:(.*)/ui)[1]
				let obj_imagen = imagenes_almacenar[id_imagen]


				/*
				if(obj_imagen.uri_imagen != ""){
					if(obj_imagen.uri_imagen != ""){
					request.getContent(
						function(content, enconding){
										obj_imagen.uri_imagen = content
										chrome.storage.local.set(obj_imagen.id_compania, obj_imagen)
										})
					}
					break; 
				}
				imagenes_almacenar[regex_result[1]] = {
										id_imagen : regex_result[1],
										uri_imagen : "",
										id_compania : 0
														}

			*/
				break;
			default:
				break;
		}
	}
	return Lista_oferta;
}



//Main
var imagenes_almacenar = {}
const interceptedIds = new Set();

/*
const imagen_compania = {
	id_imagen : "",
	uri_imagen : "",
	id_compania : 0
}*/
chrome.devtools.panels.create("DemoPanel", "toast.png", "devtools_panel.html", function (panel) { });

chrome.devtools.network.onRequestFinished.addListener(function (request) {

	if (request.request.method == 'GET' && request.response.status == 200) {

		/*
		if(request._resourceType == 'image'){
			let regex_result = request.request.url.match(/https:\/\/media.licdn.com\/dms\/image\/([\w\d]{10,})\/company-logo_/iu)
			if(regex_result){
				let obj_imagen = imagenes_almacenar[regex_result[1]]
				
				if(obj_imagen){
					if(obj_imagen.id_compania != 0){
						request.getContent(
							function(content, enconding){
											obj_imagen.uri_imagen = content
											chrome.storage.local.set(obj_imagen.id_compania, obj_imagen)
											})
						return;
					} 
				}
				imagenes_almacenar[regex_result[1]] = {
										id_imagen : regex_result[1],
										uri_imagen : content,
										id_compania : 0
														}

				
				//si la id de regex[1] esta en la lista, agarrar el objecto e insertarlo en el storage
				// si no, crear el objeto



			}
		}
		*/

		if (request.request.url.startsWith('https://www.linkedin.com/voyager/api/graphql?variables=(jobCardPrefetchQuery')) {
			request.getContent(async function (content, encoding) {
				await Linkedin(content).then(resp => {
					chrome.storage.local.set(resp)
					console.debug(request, resp)
				})
			})

			chrome.devtools.inspectedWindow.eval('AgregarBotonesDescarte()', { useContentScriptContext: true })

		}
	}
});


//Descartar las ofertas cuando se clickean manualmente en Linkedin
var backgroundPageConnection = chrome.runtime.connect({
	name: "devtools-page"
});
backgroundPageConnection.onMessage.addListener(function (message) {
	chrome.storage.local.get(message.id_oferta, (oferta) => {
		if (objectoEstaVacio(oferta)) {
			console.log('La oferta a descartar no esta almacenada', message.id_oferta)
			return;
		}
		oferta[message.id_oferta].descartada = true
		oferta[message.id_oferta].tipo_descarte.manual = true
		chrome.storage.local.set(oferta)
		console.log('oferta descartada', message.id_oferta)
	})
});


/*

chrome.devtools.inspectedWindow.getResources(e => 
	e.forEach(i => {
		if(i.type == 'image' && i.url.match(/https:\/\/media.licdn.com\/dms\/image\/[\w\d]{10,}\/company-logo_/iu)){
			i.getContent( (content, encoding) => {
				console.log(content, encoding, i.url)
			})
		    
		}
	}))

chrome.storage.local.get(null, e => {
	Object.entries(e).forEach(i => {
		for(const a of i){
			if(a[1].descartada == false){
				let divEmpleo = document.createElement('div')
				divEmpleo.className += 'oferta-elemento'
				divEmpleo.innerText = JSON.stringify(a[1])
				document.body.append(divEmpleo)
			}    
		}
	    
	})
    
})
  const ctx = document.getElementById('myChart').getContext('2d');
  const data = {
	labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
	datasets: [{
	  label: 'My Data',
	  data: [65, 59, 80, 81, 56, 55, 40],
	  backgroundColor: 'rgba(255, 99, 132, 0.2)',
	  borderColor: 'rgba(255, 99, 132, 1)',
	  borderWidth: 1
	}]
  };
  const options = {};
  const chart = new Chart(ctx, {
	type: 'line',
	data: data,
	options: options
  });

chrome.storage.local.get(e => {
	let empleos = {}
	for (const [key, lista_empleos] of Object.entries(e)) {
		lista_empleos.forEach(empleo => {
		let temp_id = empleo.id_empleo
		if(empleos[temp_id] == null){
			empleos[temp_id] = {
					descripcion_empleo : empleo.descripcion_empleo
				    
				}
		    
		}
	    
	})
    
	}
})
chrome.storage.local.get(e => {
	var empleos = {}
	for (const [key, lista_empleo] of Object.entries(e)) {

		lista_empleo.forEach(empleo => {
	    
		let temp_id = empleo.id_empleo
	    
		let registro_cant_solicitudes = {
				cantidad_solicitudes : empleo.cantidad_solicitudes,
				fecha_extraccion : empleo.fecha_recoleccion_registro
			} 
	    
		if(empleos[temp_id]){
	    
			empleos[temp_id].cantidad_solicitudes.push(registro_cant_solicitudes)
	    
		}else{
			empleos[temp_id] = empleo
		    
			empleos[temp_id].cantidad_solicitudes = [registro_cant_solicitudes]
	    
		}
	    
							 })}
	console.log(empleos)
    
})

chrome.storage.local.get(null, e => {
	var ids_duplicadas = []
	const lista = Object.entries(e)
	for(let i = 1; i < lista.length ; i++){
	    
		let resultado = lista[i-1][0] - lista[i][0]
	    
		if(resultado > -100){
			ids_duplicadas.push(lista[i][0])
		}
		console.log(i, resultado)
	}
	console.log(ids_duplicadas)
   
})
chrome.storage.local.get(null, e => {
	var ids_duplicadas = []
	const lista = Object.entries(e)
	for(let i = 1; i < lista.length ; i++){
	    
		let resultado = lista[i-1][0] - lista[i][0]
	    
		if(resultado > -100){
			ids_duplicadas.push(lista[i][0])
		}
	    
	}
	chrome.storage.local.remove(ids_duplicadas, () => console.log(`cantidad de ofertas duplicadas ${ids_duplicadas.length}`))
	   
})

			





var test = new Set()
var valores = []
chrome.storage.local.get(null,function(e){

for (const [key, value] of Object.entries(e)) {
  for(const subValue of value){
	  if(test.has(subValue.id_empleo)){
		  break;
	  }else{
		  test.add(subValue.id_empleo)
		  valores.push(new Date(subValue.fecha_publicacion).getDay())
	  }

	  
  }
}
console.log(valores)
})

*/








