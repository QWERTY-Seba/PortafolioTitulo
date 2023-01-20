let fechas_vencidas = [["2021-11-05","2021-11-10"]];

var v_min = null;
var v_max = null;



function DesactivarFechas(){
    for(var f = 0;f < fechas_vencidas.length; f++){
      Array.from(document.querySelectorAll('.dia')).filter(s => Number(s.innerText) >= fechas_vencidas[f][0] && Number(s.innerText) <= fechas_vencidas[f][1]).forEach(c => c.disabled = true);
    }
}


function PoseeInvalid(){
      var condicion = false;




      document.querySelectorAll('.dia').forEach(function(s){if(s.disabled && s.innerText >= v_min &&  s.innerText <= v_max ){condicion = true;}});
    return condicion;

}

function pintar(){
  if(PoseeInvalid()){
    v_min = null;
    v_max = null;
  }else{
    quitarSelected();
      document.querySelectorAll('.dia').forEach(s => s.innerText >= v_min &&  s.innerText <= v_max ? s.classList.add("selected") : false);

  }
}


function quitarSelected(){
    document.querySelectorAll(".selected").forEach(s => s.classList.remove("selected"));
}

function clickeado(v){
  var t = Number(v.currentTarget.innerText);
  var u = v.currentTarget.parentElement.id; 
  var t_date = new Date(u + '-'+t);

  if(v_min == null  t_date < v_min  t_date < v_max){
        v_min = t_date;
  }else if(t_date > v_max){
      v_max = t_date;
  }
  if(v_min !=  null && v_max != null){
      pintar();
  }
}

function CrearCalendario(){

  var calendarios = document.querySelectorAll('.calendario');

  calendarios.forEach(fecha => {
    fecha = fecha.id;
      var cantidad_dias = new Date(fecha).getDate();

    console.log(fecha,cantidad_dias);

    for(var i = 1 ; i<= cantidad_dias; i++){
      var d = document.createElement('button');
      var t = document.createTextNode(i);
      d.appendChild(t); 
      d.className = 'dia';
      d.addEventListener('click',clickeado,false);

      document.getElementById(fecha).appendChild(d);

      }

  })
}
CrearCalendario();
//DesactivarFechas(); 
#c{
 width: 200px;
  height: 200px;
  display: flex;
  border: 1px solid black;
 flex-wrap: wrap;
}

.dia{
  width: 30px;
  height: 30px;
  border: 1px solid red;
  cursor: pointer;
}
.dia:hover{
    background-color: green;
}

.selected{
background-color: red;

}