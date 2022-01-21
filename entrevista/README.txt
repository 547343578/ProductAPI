ProductManagement

Esta aplicacion está basada en Spring Boot, MySQL, TestNG y JUnit. Está escrito mediante una arquitectura Restful donde permite
hacer operaciones CRUD.


Antes de arrancar la aplicación, es necesario tener instalado en su equipo:
- MySQL 8
- Spring Boot 2.5.3
- Java 11


Además, es necesario crear una base de dato llamado "productmanagement" para arrancar la aplicación y es opcional crear una base
de dato llamado "productmanagement_test", que es para ejecutar los tests.


Una vez arrancado la aplicacion, puede hacer operaciones de CRUD mediante POSTMAN (por ejemplo). Para añadir o renovar un producto,
es necesario que le pase datos en formato JSON, por ejemplo:
{
     "name":"banana",
     "sector":"Fruta",
     "price":10.05
}
Después de añadir un nuevo producto, la aplicación generará un id único y un UUID aleatorio para el producto.


Para buscar y eliminar productos, simplemente hay que añadir su id despues de la dirección de la web. Por ejemplo:
http://localhost:8080/product/find/3
Con esta dirección le permite encontrar el producto con id 3, en el caso de que no lo encuentre, generará una exception.