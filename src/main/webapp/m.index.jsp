<!-- Boilerplate. -->
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[<!doctype html>]]>
	</jsp:text>

	<html>
	<head>
	<meta charset="ISO-8859-1" />
    	<meta name="viewport" content="height=device-height, width=device-width, minimum-scale=1.0, user-scalable=no" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
			<title>Ranking de Productos</title>
			<script src="https://code.jquery.com/jquery-latest.min.js"><!-- required for some browsers --></script>
			<script type="text/javascript" src="js/view.js"><!-- required for some browsers --></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"><!-- required for some browsers --></script>
			<!-- bootstrap -->
			<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
			<!-- el-checkbox -->
			<link rel="stylesheet" type="text/css"  href="css/checkbox.css" />			
			<link rel="stylesheet" type="text/css" href="css/m.view.css" media="all" />
	</head>
<body id="main_body">
			<div id="form_container">
				<form id="form_85082" class="appnitro"  method="post">
					<div class="form_description">
						<img src="./img/logo/l4.png" class="imglogo" alt="Smiley face"  width="100%" />
					</div>
					<ul >
						<li id="li_1" >
						<div class="form-group">  
								<input id="element_1" name="element_1" class="element text large form-control" type="text" maxlength="255" value="" placeholder="Buscar producto..."/> 							
							</div>
						</li>		
						<li id="li_3" >							
							<table>
									<tr>
										<td colspan="4"><p><b class="lbOrdenar">Ordenar por:</b></p></td>
									</tr>							
								<tr>								
									<td>											
										<label class="switch">
											<input id="element_3_1" name="switch" class="element radio" type="radio" value="1" checked="checked" />
											<span class="slider"></span>
										</label>
									</td>
									<td>
										<label class="lbOrdenar" for="element_3_1">Precio</label>
									</td>
									<td>
										<label class="switch">
											<input id="element_3_2" name="switch" class="element radio" type="radio" value="2" />
											<span class="slider"></span>
										</label>									
									</td>
									<td>
										<label class="el-radio-style lbOrdenar" for="element_3_2">Volumen</label>
									</td>								
								</tr>								
							</table>
						</li>
						<li id="li_4" >
							<span class="ocultable">								
								<table class="seleccionable">
									<tr>
										<td>
											<label class="switch">
												<input id="element_4_1" name="element_4_1" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="107" />
												<span class="slider"></span></label>
											<label class="choice" for="element_4_1"><img src="./img/eroski.svg" class="imglogo" alt="eroski"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_2" name="element_4_2" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="103" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_2"><img src="./img/hipercor.svg" class="imglogo" alt="hipercor"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_3" name="element_4_3" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="111" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_3"><img src="./img/elcorteingles.svg" class="imglogo" alt="el corte ingles"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_4" name="element_4_4" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="105" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_4"><img src="./img/dia.svg" class="imglogo" alt="dia"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_5" name="element_4_5" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="114" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_5"><img src="./img/Simply-market.png" class="imglogo" alt="simply"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_6" name="element_4_6" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="110" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_6"><img src="./img/condis.svg" class="imglogo" alt="condis"  width="100%" height="35px" /></label>											
										</td>
										<td id="nivel2">
											<label class="switch"><input id="element_4_7" name="element_4_7" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="106" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_7"><img src="./img/ulabox.svg" class="imglogo" alt="ulabox"  width="100%" height="35px" /></label>											
											
											<label class="switch"><input id="element_4_8" name="element_4_8" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="104" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_8"><img src="./img/carrefour.svg" class="imglogo" alt="carrefour"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_9" name="element_4_9" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="108" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_9"><img src="./img/alcampo.svg" class="imglogo" alt="alcampo"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_10" name="element_4_10" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="109" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_10"><img src="./img/caprabo.svg" class="imglogo" alt="caprabo"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_11" name="element_4_11" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="101" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_11"><img src="./img/mercadona.svg" class="imglogo" alt="mercadona"  width="100%" height="35px" /></label>
											
											<label class="switch"><input id="element_4_13" name="element_4_13" class="element checkbox" onchange="checkselect(this)" type="checkbox" value="116" />
											<span class="slider"></span></label>
											<label class="choice" for="element_4_13"><img src="./img/consum.svg" class="imglogo" alt="consum"  width="100%" height="70px" /></label>													
										</td>										
									</tr>
								</table>
							</span> 
						</li>
						<li>
							<button id="idbutton" class="button btn btn-default" onclick="listaProductos()">Buscar</button>							
							<button class="buttonb btn btn-default" onclick="location.href='./index.jsp';">Limpiar</button>
						</li>
					</ul>
					<div class="ruleta"><!-- required for some browsers --></div>
					<div class="resultado"><!-- required for some browsers --></div>
				</form>	
			</div>
			</body>
	</html>
</jsp:root>