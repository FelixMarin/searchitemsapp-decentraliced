<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Food Rank</title>
    <meta name="description" content="sia">
    <meta name="author" content="Felix Marin Ramirez">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <!-- animated.css -->
    <link rel="stylesheet" href="./css/animate.css">

    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/radiobuttons.css">
    <link rel="stylesheet" href="./css/loading.css">
    <link rel="stylesheet" href="./css/cards.css">

</head>
<body id="body">
<div class="supracontainer">
    <div id="input-container" class="container containerCL">
        <div class="row">
            <div id="logo" class="col-sm-12 col-lg-6 offset-lg-3">
                <img src="./img/1j+ojl1FOMkX9WypfBe43D6kifaIrxJLmxbIwXs1M3EMoAJtlSgqgPRq8vg___.png" alt="">
            </div>
            <div id="colinput" class="colinp col-sm-12 col-lg-6 offset-lg-3">  
                <div class="input-group">
                    <input id="inputtext" type="text" class="form-control searchbar" placeholder="Buscar producto...">
                    <div class="input-group-append">
                        <button id="botonBarra" class="btn btn-secondary pl-5 pr-5" type="button">
                        <i class="fa fa-search"></i>
                    </button>
                    </div>                    
                </div>
            </div>
        </div>
        <div id="sugerencias" class="row position-absolute live-search hidden">
        </div>
        <div id="caja-logos-checked" class="row mt-2">
        </div>
        <div id="cajamensajes" class="row mt-3 hidden">
            <div class="col-12">
                <div id="mensaje" class="alert alert-danger text-center">
                    No hubo resultados.
                </div>
            </div>
        </div>
        <div id="rowempresas" class="row ml-2">
            <div class="col-sm-12 col-lg-9 offset-lg-1 mt-5">
                <table class="table-bordered w-100 hidden" id="tablalogos">
                    <tr>
                        <td class="w-25 p-3">
                            <img src="./img/eroski.svg" class="imglogo w-100" alt="107" />
                        </td>
                        <td class="w-25 p-3">                  
                            <img src="./img/hipercor.svg" class="imglogo w-100" alt="103" />
                        </td>
                        <td class="w-25 p-3">
                            <img src="./img/elcorteingles.svg" class="imglogo w-100" alt="111" />
                        </td>
                        <td class="w-25 p-3">
                            <img src="./img/dia.svg" class="imglogo w-100" alt="105" />
                        </td>
                    </tr>
                    <tr>
                        <td class="w-25 p-3">
                            <img src="./img/Simply-market.png" class="imglogo w-100" alt="114" />          
                        </td>
                        <td class="w-25 p-3">
                            <img src="./img/condis.svg" class="imglogo w-100" alt="110" />		
                        </td>
                        <td class="w-25 p-3">
                            <img src="./img/ulabox.svg" class="imglogo w-100" alt="106" />	
                        </td>
                        <td class="w-25 p-3">
                            <img src="./img/carrefour.svg" class="imglogo w-100" alt="104" />
                        </td>
                    </tr>
                    <tr>
                        <td class="w-25 p-3">
                            <img src="./img/alcampo.svg" class="imglogo w-100" alt="108" />
                        </td>
                        <td class="w-25 p-3">
                            <img src="./img/caprabo.svg" class="imglogo w-100" alt="109" />
                        </td>
                        <td class="w-25 p-3">
                            <img src="./img/mercadona.svg" class="imglogo w-100" alt="101" />
                        </td>
                        <td class="w-25 p-3">                            
                            <img src="./img/consum.svg" class="imglogo w-100" alt="116"  />	
                        </td>
                    </tr>
                </table>
            </div>
        </div> 
        <div id="separdor-footer" class="row pt-3 ml-2 hidden">
            <div class="col-9 offset-1">
                <hr />
            </div>
        </div>       
        <div id="radiogroupid" class="row ml-2 pt-3">
            <div id="radiogroup" class="col-sm-6 col-md-4 col-lg-4 offset-1 hidden">
                <div class="toggle">
                    <input type="radio" name="sizeBy" value="1" id="precio" checked="checked" />
                    <label for="precio">Precio</label>
                    <input type="radio" name="sizeBy" value="2" id="volumen" />
                    <label for="volumen">Volumen</label>
                </div>
            </div>            
            <div id="button-aceptar" class="col-sm-6 col-md-6 col-lg-5 text-right hidden">
                <button id="botonAceptar" type="button" class="btn standar-button">Aceptar</button>
            </div>
        </div>
        <div class="row">
            <div id="ruleta" class="col-12 text-center pt-5 hidden">
                <div class="box">
                    <div class="spinner type2"><span>Loading...</span></div>
                  </div><div class="loading-animation"></div>
            </div>
        </div>     
    </div>
    <div id="productos" class="container mt-4">
    </div>
</div>
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="./js/jquery-3.5.0.min.js"></script>
    <script src="./js/bootstrap.bundle.min.js"></script>    
    <script src="./js/jquery.imgcheckbox.js"></script>
    <script src="./js/script.js"></script>
 </body>
</html>