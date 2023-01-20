var coordenadas = [-33.585626351041945, -70.69919212880593]
var cant_zoom = 12

var map = L.map('map').setView(coordenadas, cant_zoom);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '© OpenStreetMap'
}).addTo(map);

const llave = "NTTgSV4SPAIJ4DuqGUD0kgAbJDpicRw0"
const direccion = encodeURIComponent("Av. Lo Blanco 108, San Bernardo, Región Metropolitana, Chile")
console.log(direccion)

var url = `https://open.mapquestapi.com/geocoding/v1/address?key=${llave}&location=${direccion}&maxResults=1`

fetch(url)
            .then(function (response) {
                response.json().then(function(responseText) 									{
                   var coords = responseText.results[0].locations[0].latLng;
                   L.marker([coords.lat, coords.lng]).addTo(map);
                   
                });
            });


var coordenadas_circ = [-33.58529134460714, -70.69324465796198]

var circle = L.circle(coordenadas_circ, {
    color: 'red',
    fillColor: '#f03',
    fillOpacity: 0.5,
    radius: 3000 
}).addTo(map);