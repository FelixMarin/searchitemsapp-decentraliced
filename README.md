
# Comparador de precios de supermercados online

Una aplicación web desarrollada en java, Spring Framework e Hibernate. Consiste en un comparador de precios de supermercados online. Partiendo de los siguientes parámetros de entrada, la aplicación devolverá una estructura en formato json con una lista ordenada de productos de alimentación. La característica principal de la aplicación es la extracción de datos usando la técnica del web scraping.
La aplicación es una API REST, lo que significa que para obtener la informcación habrá que solicitarla mediante una URL.

# Herramientas

- [Linux](https://ubuntu.com/download/server)
- [Eclipse IDE for J2EE 2020](https://www.eclipse.org/ide/)
- [PostgresSQL](https://www.postgresql.org/)
- [OpenJDK 13](https://openjdk.java.net/projects/jdk/)
- [Spring Framework](https://spring.io/)
- [Apache Tomcat 9](http://tomcat.apache.org/)
- [SmartBear SoapUI](https://www.soapui.org/)
- [Oracle VirtualBox](https://www.virtualbox.org/)

# Preparación del Entorno

## Instalación del SGBD  

Se procede a [instalar PosgreSQL](https://www.digitalocean.com/community/tutorials/como-instalar-y-utilizar-postgresql-en-ubuntu-18-04-es) en el sistema operativo. Una vez instalado y configurado el SGBD, se ejecuta el script de la base de datos que se encuentra en **./BBDD/backup_sia_bbdd.sql**. Finalmente, Crear los siguientes **'Login/Grup Roles'** usados por la aplicación: 

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
Importar el proyecto en Eclipse IDE. Para importar proyectos como proyectos Eclipse existentes:

Clic en ``` bash File> Import ```.

En el Import Wizard:

Expandir ```bash Git ``` y luego clic en ```bash Projects from Git ``` => ```bash Next ```.
Clic en ```bash Existing local repository ``` => ```bash Next ```.
Clic en ```bash Add ``` para navegar a cualquier repositorio local. Clic en ```bash Next ```. En la sección ```bash Wizard for project import ```, Clic ```bash Import  existing Eclipse project ```. Haga clic ```bash Next ```.
En la ventana ```bash Import Projects ```, seleccionar el proyecto a importar.
Asegurarse de hacer clic en la casilla de verificación ```bash Select nested projects ``` para importar los proyectos anidados bajo el proyecto principal que está importando.
Finalmente, clic en ```bash Finish ```.

Una vez importado el proyecto, actualizar las dependencias Maven: ```bash Project Properties ``` => ```bash Maven ``` => ```bash Maven Update ```.

Crear un el directorio **'/resources/'** en la raiz del sistema. 

![Drivers en './resources/'](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000007.png)

Este directorio contiene los ficheros **'.properties'**.:

- **db.properties**: contiene los literales y datos de conexión a la base de datos.
- **flow.properties**: contiene todos los literales de la aplicación.
- **log4j.properties**: contiene la configuración de la libreria de registros log4j.

El fichero **flow.properties** contiene tres rutas a tener encueta. Son las rutas al ejecutabme del navegador firefox y los drivers de chrome y gecko. Hay que colocar los drivers en la ruta indicada.

```console
folw.value.firefox.ejecutable.path=/usr/bin/firefox
flow.value.google.driver.path=/usr/local/bin/drivers/chrome/chromedriver 
flow.value.firefox.driver.path=/usr/local/bin/drivers/firefox/geckodriver
```

![Drivers en './resources/flow.properties'](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000008.png)

Descargar los drivers de Firefox y Chrome y situarlos en la ruta que aparece a continuación. 

```console
/usr/local/bin/drivers/chrome/chromedriver 
/usr/local/bin/drivers/firefox/geckodriver 
```

Añadir al fichero **/etc/environmet** las siguientes variables de entorno.  

- **PROPERTIES_SIA** = "/resources" 
- **CATALINA_HOME** = "/opt/apache-tomcat-9.0.34" 
- **JAVA_HOME** = "/usr/lib/jvm/java-14-openjdk-amd64" 
- **JRE_HOME** = "/usr/lib/jvm/java-14-openjdk-amd64" 

El último paso sería crear un directorio llamado logs en la raíz de sistema para recoger los logs que va escribiendo la aplicación.  

![Directorio '/log4j/'](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/000009.png)

 [Instalar el servidor de aplicaciones Apache Tomcat 9.](https://tecnstuff.net/how-to-install-tomcat-9-on-ubuntu-18-04/)
 
 [Vicular Tomcat a Eclipse IDE](https://www.codejava.net/servers/tomcat/how-to-add-tomcat-server-in-eclipse-ide). 
 
Una vez preparado el entorno habrá que compilar el proyecto y desplegar la aplicación en el servidor Tomcat.

![Instalación Apache Tomcat](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/tomcat.png)

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

#### [Ejemplo de la aplicación en vídeo](https://youtu.be/K_4Wp0Poh2Q)

[![Ejemplo de la aplicación en vídeo](https://github.com/FelixMarin/searchitemsapp/blob/v0.7.0/docimg/portada-video-0.png)](https://youtu.be/K_4Wp0Poh2Q)
 


