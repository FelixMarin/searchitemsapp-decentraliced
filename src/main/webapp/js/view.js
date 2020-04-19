eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('3(7.X){7["R"+a]=a;7["z"+a]=6(){7["R"+a](7.1k)};7.X("1e",7["z"+a])}E{7.19("z",a,15)}2 j=H V();6 a(){2 e=q.1d("1a");3(e){o(e,"P");2 N=B(q,"*","14");3((e.12<=10)||(N=="")){c(e,"P",d)}}4=B(q,"*","1n");k(i=0;i<4.b;i++){3(4[i].F=="1g"||4[i].F=="1f"||4[i].F=="1c"){4[i].1b=6(){r();c(v.5.5,"f",d)};4[i].O=6(){r();c(v.5.5,"f",d)};j.D(j.b,0,4[i])}E{4[i].O=6(){r();c(v.5.5,"f",d)};4[i].18=6(){o(v.5.5,"f")}}}2 C=17.16.13();2 A=q.M("11");3(C.K("J")+1){c(A[0],"J",d)}3(C.K("I")+1){c(A[0],"I",d)}}6 r(){k(2 i=0;i<j.b;i++){o(j[i].5.5,"f")}}6 B(m,y,w){2 x=(y=="*"&&m.Y)?m.Y:m.M(y);2 G=H V();w=w.1m(/\\-/g,"\\\\-");2 L=H 1l("(^|\\\\s)"+w+"(\\\\s|$)");2 n;k(2 i=0;i<x.b;i++){n=x[i];3(L.1j(n.8)){G.1i(n)}}1h(G)}6 o(p,T){3(p.8){2 h=p.8.Z(" ");2 U=T.t();k(2 i=0;i<h.b;i++){3(h[i].t()==U){h.D(i,1);i--}}p.8=h.S(" ")}}6 c(l,u,Q){3(l.8){2 9=l.8.Z(" ");3(Q){2 W=u.t();k(2 i=0;i<9.b;i++){3(9[i].t()==W){9.D(i,1);i--}}}9[9.b]=u;l.8=9.S(" ")}E{l.8=u}}',62,86,'||var|if|elements|parentNode|function|window|className|_16|initialize|length|addClassName|true|_1|highlighted||_10||el_array|for|_13|_6|_c|removeClassName|_e|document|safari_reset||toUpperCase|_14|this|_8|_9|_7|load|_4|getElementsByClassName|_3|splice|else|type|_a|new|firefox|safari|indexOf|_b|getElementsByTagName|_2|onfocus|no_guidelines|_15|event_load|join|_f|_11|Array|_17|attachEvent|all|split|450|body|offsetWidth|toLowerCase|guidelines|false|userAgent|navigator|onblur|addEventListener|main_body|onclick|file|getElementById|onload|radio|checkbox|return|push|test|event|RegExp|replace|element'.split('|'),0,{}))
	
var req; // global variable to hold request object

function checkselect(ev) {
	var checkedEmpresa = document.querySelectorAll('.checkbox:checked');

	if (checkedEmpresa.length > 3) {
    	alert('Máximo 3 supermercados');
     	$(ev).prop("checked", false);
    }
}

function listaProductos() {
	var producto = document.getElementById('element_1').value;
	var checkedEmpresa = document.querySelectorAll('.checkbox:checked'); 
	var ordenarpor = document.getElementsByName('switch');
	var strEmpresas = '';
	var ordenar;
	
	for (var i = 0; i < ordenarpor.length; i++) {
		 if (ordenarpor[i].checked) {
			  ordenar= ordenarpor[i].value;
			  break;
		 }
	 }
	 
	 if (checkedEmpresa.length === 0) {
		 alert('no checkboxes checked');
		console.log('no checkboxes checked');
	} else {
		 for (var i = 0; i < checkedEmpresa.length; i++) {
			 strEmpresas += checkedEmpresa[i].value + ',';
		 }
		 
		 if(strEmpresas.endsWith(',')) {
			 strEmpresas = strEmpresas.substring(0, strEmpresas.length -1);
		 }
	}
		
	if(producto.length > 1 && ordenar != undefined && strEmpresas.length > 1) {
		fun(producto, ordenar, strEmpresas);
	} else {
		alert('error');
		console.log('error');
	}
}

function normalColor(val) {
	$(val).css("background-color", "#fff");
}

function overColor(val) {
	$(val).css("background-color", "beige");
}

function fun(producto, ordenar, strEmpresas) {
	
		$.ajax({
		type: "GET",
		url: "/searchitemsapp/search/101/101/"+ ordenar +"/"+producto + "/" + strEmpresas,
		dataType: "text",
		timeout: 600000,
		beforeSend: function(){
			$("#idbutton").attr("disabled", true);
			$(".ocultable").css("display", "none");
			$('.resultado').css("display", "none");
        	$('.ruleta').css("display", "block");
			$(".ruleta").css("background","transparent url(/searchitemsapp/img/progress.gif) no-repeat");				
			$(".ruleta").css("background-size","100px");
			$(".ruleta").css("background-position-x","center");
			$(".ruleta").css("background-position-y","center");			
			$("#li_3").css("display", "none");			
			$("#li_4").css("display", "none");
			$(".imglogo").css("display", "block");
		}
	}).done(function (data) {
			$(".ruleta").css("display", "none");		
			$('.resultado').css("display", "block");
			$('.resultado').show();
			$(".form_description").css("display", "none");
        	$('.form-group').css("margin-top","15px");
			$(".resultado").css("width:","100px");
			
			if(data == '<div class=\'faded\' id=\'iddivproductos\'></div>') {
				alert('No se han encontrado resultados');
            window.open ('./index.jsp','_self',false);
			} else {
				$('.resultado').append(resultJsonToHtml(data));							
			}
			
			
			$("#idbutton").attr("disabled", false);
			$(".imglogo").css("display", "none");
	}).fail(function (xhr, textStatus, errorThrown) {
		console.log(errorThrown+'\n'+status+'\n'+xhr.statusText);
		alert("AJAX call failed: " + textStatus + ", " + errorThrown);
	});
}

function selectProduct(producto, ordenar, strEmpresas) {
	
	$.ajax({
		type: "GET",
		url: "http://192.168.0.112/autocomp.com/getData.php",
		data:"didPais=101&didCategoria=101&ordenacion="+ ordenar +"&producto="+producto + "&empresa=" + strEmpresas,
		dataType: "text",
		timeout: 600000,
		beforeSend: function(){

			}
	}).done(function (data) {
			$(".cuerpo").fadeOut( 100 , function() {
				$('.resultado').html(data);
			}).fadeIn(1000);
	
	}).fail(function (xhr, textStatus, errorThrown) {
		console.log(errorThrown+'\n'+status+'\n'+xhr.statusText);
		alert("AJAX call failed: " + textStatus + ", " + errorThrown);
	});		
}

function resultJsonToHtml(data) {
	var cuenta = 0;
	var result = '<div class=\'faded\' id=\'iddivproductos\'>';
	
	var obj = JSON.parse(data);
	
	obj.resultado.forEach(function(item) {
        	result += '<table class=\'cltableresult\' border=\'0\'  width=\'100%\'><tbody>';
			cuenta += 1;
			switch (item.didEmpresa) {
				case 101:
					result += '<tr>';
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/mercadona.svg" alt="mercadona" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 102:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/lidl.png" alt="lidl" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 103:
					result += '<tr>';				
				   result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/hipercor.svg" alt="hipercor" /></a></td>';
				   result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 105:
					result += '<tr>';				
				   result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/dia.svg" alt="dia" /></a></td>';
				   result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 106:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/ulabox.svg" alt="ulabox" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 107:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/eroski.svg" alt="eroski" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 108:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/alcampo.svg" alt="alcampo" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 109:
					result += '<tr>';				
				   result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/caprabo.svg" alt="caprabo" /></a></td>';
				   result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 104:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/carrefour.svg" alt="carrefour" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 110:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/condis.svg" alt="condis" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 111:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/elcorteingles.svg" alt="elcorteingles" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;		
				case 114:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/Simply-market.png" alt="simply" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;
				case 116:
					result += '<tr>';				
					result += '<td class=\'cltdres\' id=\'urlproducto\'><a title=\'link\' href=\'' + item.nomUrlAllProducts  + '\' target=\'_blank\' rel=\'noopener\'><img class="logosvg" src="img/tudespensa.jpg" alt="CONSUM" /></a></td>';
					result += setcolor(cuenta)
					result += '</tr>';
					break;	             
			}		
			
        	result += '<tr><td class=\'cltdres\' id=\'imgproducto\' colspan=\'0\' align=\'center\' valign=\'middle\'><a title=\'link\' href=\'' + item.nomUrl +'\' target=\'_blank\' rel=\'noopener\'><img src=\'' + item.imagen + '\' alt=\'image\' width=\'133\' height=\'133\' /></a></td>';
			result += '<td class=\'cltdres\' id=\'desproducto\' colspan=\'2\'><b id=\'descproducto\'>' + item.desProducto + '</b><br /><br /><span class=\'clprecio>\'>Precio: <b> ' + item.precio + '</b><br />Precio(UM): <b>' + item.precioKilo + '</b></span></td>';        	            
        	result += '</tr></tbody></table>';
	});
	
	    result +='</div>';    
	return stripslashes2(result);
	
}

function stripslashes2(string) {
    string = string.replace("\\\"", "\"");
    string = string.replace("\\'", "'");
    string = string.replace("\\\\", "\\");
    return string;
}

function setcolor(cuenta) {
 	if(cuenta == 1) {
       return '<td class=\'cuenta\' style=\'background-color:#4CAF50;\'>1</td>';
   	} else if(cuenta == 2) {
		return '<td class=\'cuenta\' style=\'background-color:#FF9900;\'>2</td>';
	} else {
		return '<td class=\'cuenta\' style=\'background-color:#c0c2c4;\'>' + cuenta  + '</td>';
	}
}
