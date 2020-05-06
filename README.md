# Preparación del Entorno

Una aplicación web desarrollada en java, Spring Framework e Hibernate. Consiste en un comparador de precios de supermercados online. Partiendo de los siguientes parámetros de entrada, la aplicación devolverá una estructura en formato json con una lista ordenada de productos de alimentación. La característica principal de la aplicación es la extracción de datos usando la técnica del web scraping.
La aplicación es una API REST, lo que significa que para obtener la informcación habrá que solicitarla mediante una URL.

## Instalación de la base de datos 
Se utilizará una base de datos relacional. El SGBD elegido para la capa de persistencia será PostgresSQL. 

Se procede a instalar PostgreSQL en el sistema operativo. Una vez instalado y configurado el SGBD, se ejecuta el script de la base de datos de SIA que se encuentra entre los recursos. ‘backup_sia_bbdd.sql’. 

Antes de ejecutar el script, habrá que crear los siguientes ‘Login/Grup Roles: 

```console
pgadmin:  SuperUser.
sia_select:  Solo permisos de lectura.
```


## Instalación del entorno 
El primer paso consiste en descargar el proyecto de GitHub.  

```bash
~$ sudo apt install git 
~$ git clone https://github.com:/FelixMarin/searchitemsapp.git 
```

Una vez descargado el proyecto de GitHub, instalar la última versión de eclipse JEE y crear un nuevo workspace.  

A continuación, se importa el proyecto Git y se crea el proyecto. 

![import project form git to eclipse](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000001.png)

En el siguiente cuadro de diálogo se introducirá la ruta del proyecto y seleccionar el fichero ‘.git’ que aparece en la lista. Pulsar finalizar. 

![add git repository](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000002.png)

En el siguiente cuadro de diálogo, aparecerá el repositorio marcado. Pulsar siguiente. 

![import project form git to eclipse](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000003.png)

Para terminar el apartado de importación del proyecto, se elegirá la opción ‘Import existing Eclipse Project’.

 ![import project form git to eclipse](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000004.png)

Se comprueba que el proyecto importado no tenga errores. 

 ![import project form git to eclipse](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000005.png)

Aparecen algunos warnings que se irán resolviendo conforme se vaya configurado el entorno. 

El siguiente paso es instalar Apache Tomcat para las ejecuciones de la aplicación mientras se desarrolla y se relazan las pruebas en local. Vincular el servidor Tomcat a Eclipse. 

![Instalación Apache Tomcat](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000006.png)

El directorio resources contiene tres ficheros:

- **db.properties**: contiene los literales y datos de conexión a la base de datos.
- **flow.properties**: contiene todos los literales de la aplicación.
- **log4j.properties**: contiene la configuración de la libreria de registros log4j.

El fichero **flow.properties** contiene tres rutas a tener encueta. Son las rutas al ejecutabme del navegador firefox y los drivers de chrome y gecko. Hay que colocar los drivers en la ruta indicada.

```console
folw.value.firefox.ejecutable.path=/usr/bin/firefox
flow.value.google.driver.path=/usr/local/bin/drivers/chrome/chromedriver 
flow.value.firefox.driver.path=/usr/local/bin/drivers/firefox/geckodriver
```
Sigamos con la configuración, existe un directorio fuera de la aplicación al cual se accede a través de las variables de entono. Esto es debido a que en ese directorio se almacenan los ficheros ‘properties’ que contiene información sensible. Este directorio se denomina resources y se coloca en la raíz del sistema operativo. Los permisos de los properties serán de lectura solamente. 

![Directorio './resources'](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000007.png)

Descargar los drivers de Firefox y Chrome y situarlos en la ruta que aparece a continuación. 

```console
/usr/local/bin/drivers/chrome/chromedriver 
/usr/local/bin/drivers/firefox/geckodriver 
```

En el fichero de propiedades aparece de esta forma: 

![Drivers en './resources/flow.properties'](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000008.png)

Añadir al fichero ‘**/etc/environmet**’ las siguientes variables de entorno.  

- **PROPERTIES_SIA** = "/resources" 
- **CATALINA_HOME** = "/opt/apache-tomcat-9.0.34" 
- **JAVA_HOME** = "/usr/lib/jvm/java-14-openjdk-amd64" 
- **JRE_HOME** = "/usr/lib/jvm/java-14-openjdk-amd64" 

El último paso sería crear un directorio llamado logs en la raíz de sistema para recoger los logs que va soltando la aplicación.  

![Directorio '/log4j/'](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000009.png)

Una vez preparado el entorno habrá que compilar el proyecto y desplegar la aplicación en el servidor de aplicaciones local Tomcat.

Para realizar pruebas se acoseja usar la aplicación de escritorio [Postman](https://www.postman.com/downloads/). 

Para acceder a la interfaz gráfica de la aplicación se hará mediante el fichero index.jsp:

```console
http://[url]:[port]/searchitemsapp/index.jsp
```

Formato de la URL con la que se realizará la solicitud al servicio:

```console
http://[url]:[port]/searchitemsapp/search/[país]/[categoría]/[ordenar]/[producto]/[super]
```

__país__: 101 (España).

__categoría__: 101 (Supermercados)

__ordenar__: precio: 1 / volumen: 2

__producto__: (Arroz, Aceite, sal, ...)

__super__: [101] , [101,103,104] , [ALL]
 

Ejemplo de uso:

```console
/**
*  Esta llamada a la API devolverá un listado de 
*  objetos json con los productos de todos los 
*  supermercados ordenados por precio. 
**/
http://[url]:[port]/searchitemsapp/search/101/101/1/arroz/ALL
```

```console
/**
*  Esta llamada a la API devolverá un listado de 
*  objetos json con los productos de un supermercado  
*  ordenados por volumen.
**/
http://[url]:[port]/searchitemsapp/search/101/101/2/sal/103
```

Ejemplo de respuesta:

```console
{
    "resultado": [
        {
            "identificador": "9",
            "nomProducto": "Arroz categoría extra Carrefour 1 kg.",
            "didEmpresa": "104",
            "nomEmpresa": "CARREFOUR",
            "imagen": "https://static.carrefour.es/hd_280x_/img_pim_food/101424_00_1.jpg",
            "nomUrl": "https://www.carrefour.es/supermercado/arroz-categoria-extra-carrefour-1-kg-carrefour/R-prod1022743/p",
            "precio": "0,78 eur",
            "precioKilo": "0,78 eur/kg"
        },
        {
            "identificador": "10",
            "nomProducto": "Arroz redondo Hacendado",
            "didEmpresa": "101",
            "nomEmpresa": "MERCADONA",
            "imagen": "https://prod-mercadona.imgix.net/images/0daf43fb5761b823ce83c985930c97c9.jpg?fit=crop&h=300&w=300",
            "nomUrl": "null",
            "precio": "0,79",
            "precioKilo": "0,79"
        }
]}
```

#### Ejemplo en vídeo de la aplicación usando una interfaz web:

[![IMAGE ALT TEXT HERE](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/portada-video-0.png)](https://youtu.be/LX-w0rHEKvk)
 


