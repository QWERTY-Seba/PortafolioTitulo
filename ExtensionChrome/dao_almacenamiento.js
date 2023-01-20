/*
DAO chrome storage 


*/



function buscarOfertaPorId(id_oferta){
	let oferta;
	chrome.storage.local.get(id_oferta, oferta_almacenada => {
		Object.assign(oferta, oferta_almacenada )
	})
	return oferta;
}

function traerTodasLasOfertas(){
	let resultado;
	chrome.storage.local.get(null, oferta_almacenada => {
		Object.assign(resultado, oferta_almacenada )
	})
	return resultado;
}

function ejecutarRegexEnDescripcionEmpleo(regex){
	let resultado_regex = [];
	let match;
	chrome.storage.local.get(null, lista => {
		Object.entries(lista).forEach(oferta => {
			while((match = regex.exec(oferta[1].descripcion_empleo) ) !== null){
				resultado_regex.append(match[0])    
			}
		})
	})
	return resultado_regex;
}