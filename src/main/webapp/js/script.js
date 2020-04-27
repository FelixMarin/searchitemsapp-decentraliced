window.document.onload = init();
window.addEventListener("resize", resize);
window.document.onresize = resize();

function init() {
    document.getElementById('inputtext').addEventListener('keyup', desplazarbarra, false);    
    document.getElementById('botonAceptar').addEventListener('click',enviar,false);
    document.getElementById('botonBarra').addEventListener('click',enviar,false);
    aceptarSearchBar();
    $("img.imglogo").imgCheckbox();
}

function desplazarbarra() {
    let input = document.getElementById('inputtext');

    if(input.value != '') {
        document.getElementById('logo').style.display = "none";    
        document.getElementById("input-container").classList.add('desplazar');
        document.getElementById('colinput').className = "col-12";
        document.getElementById("tablalogos").classList.remove('hidden');
        document.getElementById("tablalogos").classList.add('show');
        document.getElementById("radiogroup").classList.remove('hidden');
        document.getElementById("radiogroup").classList.add('show');
        document.getElementById("button-aceptar").classList.remove('hidden');
        document.getElementById("button-aceptar").classList.add('show');
        document.getElementById("separdor-footer").classList.remove('hidden');
        document.getElementById("separdor-footer").classList.add('show');
    }
}

function enviar() {
    document.getElementById('cajamensajes').style.display = 'none';
    let elemProducto = document.getElementById('inputtext');
    let elemEmpresas = document.getElementsByClassName('imgChked');
    let elemOrdenar = document.getElementsByName('sizeBy');
    let valOrdenar = 1;
    let valProducto = '';
    let valEmpresas = '';

    document.getElementById('productos').innerHTML ='';
    document.getElementById('caja-logos-checked').innerHTML ='';

    if(elemEmpresas.length > 3) {
        document.getElementById('cajamensajes').style.display = 'block';
        document.getElementById('mensaje').innerHTML = "Máximo 3 supermercados";
        return;
    }

    if(elemProducto.value != '') {
        valProducto = elemProducto.value;
    } else {
        return;
    }

    if(elemEmpresas.length != 0) {

        for (let item of elemEmpresas) {
            let didEmpresa = item.firstChild.alt;
            let imgLogo = imagenLogoEmpresa(parseInt(didEmpresa)).replace('w-50','w-100');
            let logoEmpresa = document.createRange().createContextualFragment(imgLogo);
            let div = document.createElement("div");
            div.classList.add('col-1');
            div.classList.add('mt-3');
            div.classList.add('border');
            div.appendChild(logoEmpresa);
            document.getElementById('caja-logos-checked').appendChild(div);
            valEmpresas += didEmpresa + ',';            
        }

        let div = document.createElement("div");
        div.classList.add('col-' + (12 - elemEmpresas.length));
        div.classList.add('mt-3');       
        let buttonVolver = document.createElement("a");
        buttonVolver.setAttribute("href", "./index.html");
        buttonVolver.setAttribute("id", "boton-volver");
        buttonVolver.innerHTML = "Volver";
        buttonVolver.classList.add('btn');
        buttonVolver.classList.add('btn-outline-primary');
        buttonVolver.classList.add('ml-5');        
        buttonVolver.classList.add('float-right'); 
        div.appendChild(buttonVolver);
        document.getElementById('caja-logos-checked').appendChild(div);
        
        valEmpresas = valEmpresas.substr(0,valEmpresas.length - 1);  
    } else {
        return;
    }

    if(elemOrdenar[1].checked) {
        valOrdenar = 2;
    }

    traerProductos(valProducto, valOrdenar, valEmpresas);
}

function traerProductos(producto, ordenar, strEmpresas) {	
    $.ajax({
    type: "GET",
    url: "http://localhost/accesosia/getData.php",
    data:"didPais=101&didCategoria=101&ordenacion="+ ordenar +"&producto="+producto + "&empresa=" + strEmpresas,
    //url: "http://192.168.0.106/searchitemsapp/search/101/101/"+ ordenar +"/"+producto + "/" + strEmpresas,
    dataType: "text",
    timeout: 600000,
    beforeSend: function(){
        $('#cajamensajes').removeClass("show");
        $('#cajamensajes').addClass("hidden");
        $('#productos').addClass("hidden");
        $("#rowempresas").css("display", "none");
        $("#separdor-footer").css("display", "none");
        $("#radiogroupid").css("display", "none");
        $('#input-container').removeClass("containerCL");
        $("#input-container").addClass("mt-2");
        $('#ruleta').removeClass('hidden');
        $('#ruleta').addClass('show');
    }
    }).done(function (data) {
        $('#ruleta').addClass('hidden');
        $('#productos').removeClass("hidden");
        $('#productos').addClass("show");
        componerCartas(data);        
    }).fail(function (xhr, textStatus, errorThrown) {
        console.log(errorThrown+'\n'+status+'\n'+xhr.statusText);
        alert("AJAX call failed: " + textStatus + ", " + errorThrown);
    });
}

function componerCartas(data) {
    let estructuraHTML = '';
    let contador = 0;

    try {
        let datosJSON = jQuery.parseJSON(data);
        let contenedor = document.getElementById('productos');
        let row;

        datosJSON.resultado.forEach(element => {           

            if(contador === 0) {
                row = document.createElement("div");
                row.classList.add("row");
                row.classList.add("pb-3");
                contenedor.appendChild(row);
            }

            estructuraHTML = '<div class="col-md-2 col-sm-3 mb-3 crd">';
            estructuraHTML += '<div class="card card-block crd h-100 showcard">';            
            estructuraHTML += '<h4 class="card-title">';
            estructuraHTML += imagenLogoEmpresa(element.didEmpresa);
            estructuraHTML += '<span class="pt-1 pull-right"><kbd>' + element.identificador + '</kbd></span>';
            estructuraHTML += '</h4>'
            estructuraHTML += '<a href="' + element.nomUrlAllProducts + '" target="_blank" rel="noopener noreferrer">';
            estructuraHTML += '<img class="imgprod" src="' + element.imagen + '" alt="Photo of sunset" />';
            estructuraHTML += '</a>';
            estructuraHTML += '<h5 class="card-title p-2">' + element.nomProducto + '</h5>';
            estructuraHTML += '<p class="text pl-2">Precio: ' + element.precio + '<br />P. Kilo: ' + element.precioKilo + '</p>';     
            estructuraHTML += '</div></div>';

            let card = document.createRange().createContextualFragment(estructuraHTML);
            row.appendChild(card);            

            contador++;

            if(contador === 6) {
                contador = 0;
            }
        });  
    } catch(error) {
        $('#caja-logos-checked').removeClass("hidden");
        document.getElementById("caja-logos-checked").classList.remove('show');
        document.getElementById("caja-logos-checked").classList.add("hidden");          
        document.getElementById("cajamensajes").classList.remove('hidden');
        document.getElementById("cajamensajes").classList.add("show");        
        console.log(error);
    }
}

function imagenLogoEmpresa(didEmpresa) {

    switch (didEmpresa) {
        case 101:
            return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/mercadona.svg" alt="mercadona" />';
        case 102:
            return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/lidl.png" alt="lidl" />';
        case 103:
            return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/hipercor.svg" alt="hipercor" />';
        case 105:            			
            return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/dia.svg" alt="dia" />';
         case 106:            			
             return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/ulabox.svg" alt="ulabox" />';
         case 107:            			
             return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/eroski.svg" alt="eroski" />'; 
         case 108: 			
             return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/alcampo.svg" alt="alcampo" />';
         case 109:	
            return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/caprabo.svg" alt="caprabo" />';
         case 104:	
             return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/carrefour.svg" alt="carrefour" />';
         case 110:	
             return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/condis.svg" alt="condis" />';
         case 111:		
             return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/elcorteingles.svg" alt="elcorteingles" />';
         case 114:		
             return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/Simply-market.png" alt="simply" />';
         case 116:		
             return '<img class="w-50 pt-2 pl-2 pb-2 climg" src="img/tudespensa.jpg" alt="CONSUM" />';
    }
}

function aceptarSearchBar() {
    let elemProducto = document.getElementById('inputtext');

    elemProducto.addEventListener("keyup", function(event) {
        if (event.keyCode === 13) {
          event.preventDefault();
          document.getElementById("botonAceptar").click();
        }
      });
}

$(window).resize(function() {
    $( "#log" ).append( "<div>Handler for .resize() called.</div>" );
  });

function resize() {
    let ancho = window.outerWidth;
    let largo = window.outerHeight;

    if(ancho <= 767) {
        document.getElementById('rowempresas').classList.remove('ml-2');
        document.getElementById('radiogroupid').classList.remove('ml-2');
        document.getElementById('radiogroup').classList.remove('offset-1');
        document.getElementById('colinput').classList.add('pt-5');
        document.getElementById('colinput').classList.add('colinp');

        if(document.getElementById('boton-volver') != null) {
            document.getElementById('boton-volver').style.display = 'none';
        }
        document.getElementById('productos').classList.remove('mt-4');
        document.getElementById('productos').classList.add('p-5');

        let listaTd = document.getElementsByTagName('td');

        for (let index = 0; index < listaTd.length; index++) {
            listaTd[index].classList.remove('p-3');
            listaTd[index].classList.add('p-1');
            
        }

        let cajaLogoCheckedBorder = document.getElementsByClassName('border');

        for (let index = 0; index < cajaLogoCheckedBorder.length; index++) {
            cajaLogoCheckedBorder[index].classList.remove('col-1');
            cajaLogoCheckedBorder[index].classList.add('col-2');
        }

        let logoProducto = document.getElementsByClassName('climg');

        for (let index = 0; index < logoProducto.length; index++) {
            logoProducto[index].classList.remove('w-50');
            logoProducto[index].classList.add('w-25');
        } 
        
        let logochecked = document.querySelectorAll('#caja-logos-checked > div:nth-child(n) > img');

        for (let index = 0; index < logochecked.length; index++) {
            logochecked[index].classList.remove('w-50');
            logochecked[index].classList.remove('w-100');
            logochecked[index].classList.remove('climg');
            logochecked[index].classList.add('w-100');
        }

    } else {
        document.getElementById('rowempresas').classList.add('ml-2');
        document.getElementById('radiogroupid').classList.add('ml-2');
        document.getElementById('radiogroup').classList.add('offset-1');
        document.getElementById('colinput').classList.remove('pt-5');
        document.getElementById('colinput').classList.remove('colinp');

        if(document.getElementById('boton-volver') != null) {
            document.getElementById('boton-volver').style.display = 'block';
        }
        
        let listaTd = document.getElementsByTagName('td');

        for (let index = 0; index < listaTd.length; index++) {
            listaTd[index].classList.remove('p-1');
            listaTd[index].classList.add('p-3');            
        }

        let cajaLogoCheckedBorder = document.getElementsByClassName('border');

        for (let index = 0; index < cajaLogoCheckedBorder.length; index++) {
            cajaLogoCheckedBorder[index].classList.remove('col-2');
            cajaLogoCheckedBorder[index].classList.add('col-1');
        }

        let logoProducto = document.getElementsByClassName('climg');

        for (let index = 0; index < logoProducto.length; index++) {
            logoProducto[index].classList.remove('w-25');
            logoProducto[index].classList.add('w-50');
        }

        let logochecked = document.querySelectorAll('#caja-logos-checked > div:nth-child(n) > img');

        for (let index = 0; index < logochecked.length; index++) {
            logochecked[index].classList.remove('w-50');
            logochecked[index].classList.remove('w-100');
            logochecked[index].classList.remove('climg');
            logochecked[index].classList.add('w-50');
        }
    }
}