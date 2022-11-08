# EMOTICONOS

##  ---» Descripción de la API «---

La API escogida ha sido EmojiHub, una API que tiene registro de todos los emoticonos existentes, actualmente, en la Red.
([Aquí dejo la API para posibles consultas.](https://github.com/cheatsnake/emojihub?ref=publicapis.dev))

## ---» Postman «---
Las consultas que se han decidido plasmar en el proyecto han sido la de adquirir un emoticono aleatorio, una lista que corresponda con un grupo y otra lista que corresponda on una categoría.
Dejamos por aquí, bien adjuntadas, unas capturas de postman con las respectivas consultas.
![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)

## ---» Manual técnico (desarrolladores) «---
En la carpeta *«controllers»* se hallan la clase *«Controller»* y la clase «Funcion_Guardado», las cuales contienen la inicialización de los combobox, llamamiento de los elementos de la interfaz y los métodos de búsqueda; y los métodos de creación de ficheros (texto, xml, binario y json), respectivamente.
Ésto lo explicamos a detalle a continuación.

### --| Controller |--

También llamado controlador, es el que se encarga del centro de mando. Si el programa fuese un sistema ferroviario, el controlador sse consideraría la torre de control que dirige a los maquinistas.
En este nos encontramos, como primer método, `initialize()` , el cual inicializa los combo box para que el usuario pueda escoger categorías y grupos.

Los siguientes métodos recogen la información de los emoticonos según el método, es decir, el que es por grupos, por grupos; el que es po categorías, por categorías; y el que es aleatorio, al azar.

### --| Función Guardado |--

En este simplemente están los ficheros que generan los archivos y escriben en sus respectivos formatos la información pasada por los métodos de la clase Controller.


En la carpeta *«models»* nos encontramos con simplemente las clases *«Icon»* e *«IconList»*. En ellos, están los métodos *get* para poder pasar a la interfaz gráfica los datos (nombre, unicode, categoría y grupo)


#### ----------------------------------------------------------------------------------------------------------------------------------------

## ---» Manual de usuario «---

En el interfaz nos encontraremos con unas *comboBoxes* con las categorías y grupos de emoticonos, respectivamente. Cada opción de búsqueda tiene su propio botón, por lo que el «botón aleatorio» simplemente muestra el emoticono elegido al azar en el *label* inferior.

En la tabla se mostrará la información de los múltiples emoticonos pasados por los botones de categoría y grupo, y debajo de esta se hallan las *checkBoxes*, las cuales te permiten imprimir los resultados en un fichero aparte.
Hay una caja por cada tipo de fichero, que son, de izda. a dcha.: Json, xml, binario y texto.

![img_3.png](img_3.png)

#### ----------------------------------------------------------------------------------------------------------------------------------------

## ---» Reparto de tareas «---

En este proyecto, la primera parte, es decir, el código del *controller*, fue creado por los dos, sin embargo, Iago fue el mayor exponente en este campo. Siguiendo con él, también realizó la tabla de emoticonos y la transformación de unicode a UTF-16. Por parte de Alejandro, el tema ficheros ha sido obra suya, además de la documentación.

## ---» Posibles mejoras «---

Aunque sea complicado mejorar un programa tan simple como lo es este caso, en lo que rodea al plano estético si son notables las necesidades de algunos arreglos. Entre ellos se encuentran una capa de color para que pegue con la temática del programa. Otra posibilidad serían unas ventanas de aviso que te recuerden especificaar categoría y grupo si se te olvida marcar sus respectivos campos.

## ---» Opinión del trabajo «---

Nuestro punto de vista acerca del proyecto se ve entre dos aguas. Nos explicamos:

Por una parte, este proyecto no solo sirve para practicar en tema de ficheros, sino para adentrarnos en tierras de Jackson y javafx, algo que, en lo personal, no habíamos tenido contacto con él anteriormente. Por otra parte, en lo que respecta a preparación, nos ha parecido bastante arriesgado presentarnos un proyecto con unas vagas explicaciones a una semana de su entrega. Como bien se dijo en clase, habría estado bien dedicar un par de clases, por lo menos, a explicar estos novedosos conceptos que, de facto, pueden resultar liosos para aquellos que van a ciegas en este tema. La dedicación de tiempo fuera de horas lectivas ha sido agotador y a veces sin llegar a dar fruto, por lo que la frustración y los nervios han estado a flor de piel en más de una ocasión. 

En conclusión, no queremos que se nos malentienda. La idea del trabajo ha sido fascinante, pero la experiencia en tiempos de realización podría haberse hecho más amena con unos cimientos que sosteniesen todo, ya sean clases o documentos para leer.