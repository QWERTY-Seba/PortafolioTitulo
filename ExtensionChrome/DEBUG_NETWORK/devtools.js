var texto = ""

var startDate = new Date('2022-11-13');
var endDate = new Date();

function Extraer(){
    document.querySelectorAll('#collectionTable23 > tbody > tr').forEach(e => {texto += e.innerText + ';'})

    if(startDate > endDate) {
        let fecha_loop = new Date(startDate)
        let fecha_extraccion =  fecha_loop.getDate() + '/' + (fecha_loop.getMonth() + 1) + '/' + fecha_loop.getFullYear()
		// dd/mm/yyyy 
        console.log(fecha_extraccion)
		document.querySelector('#dateField0').value = fecha_extraccion
        
        startDate.setDate(startDate.getDate() + 1);
    }else{
		return texto;
	}
}


var ruta_div = '/html/body/app-root/app-techbank-client-consolidated/div[2]/div[2]/div/div/app-workflow-viewer/app-worklflow-container/div/form/div[2]/div[1]/div/div/div[2]/div/div/div[1]/div/table/tbody/tr[3]/td/div/div[2]/div[1]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div'
var button_creado = document.createElement('button')
button_creado.innerText = 'Click'
button_creado.classList.add('cellDiv')
button_creado.onclick = Extraer
document.evaluate(ruta_div, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.appendChild(button_creado);