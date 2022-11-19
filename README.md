# SAT Recruitment

El objetivo de esta prueba es refactorizar el código de este proyecto.
Se puede realizar cualquier cambio que considere necesario en el código y en los test.


## Requisitos 

- Todos los test deben pasar.
- El código debe seguir los principios de la programación orientada a objetos (SOLID, DRY, etc...).
- El código resultante debe ser mantenible y extensible.
### Decesiones de diseño
Se decision implemnetar la siguiente arquitectura  para  PARAMO - APIs for USER


1CAPA CONTROLLADOR
***
2CAPA SERVICES
* Se implemnto el patron Strategy  con las distintas estrategias para calcular el monto segun el tipo del usuario(Norma,Super,Premiun)
* Se implemento el patron Factory para obtener la estrategia segun el tipo de usuario que se va crear
***
3CAPA PERSISTENCIA:
* Se opto por Spring data jpa para persistir los datos.
* La base de datos es H2 todo en memoria
* en el archivo application.yml se puede configurar para que se pueda cambiar y  usar otra bbdd .



### OPENAPI
* SE utilizo openapi para espeficiar y desarrollar la API para usuarios
* Ver especificacion de la API en openapi/paramo-user.yml

#### Expceciones
se centraliza el manejo de excepciones en la clase SatExecptionHandler

#### ENPOINTS DISPONIBLES EN LA VERSION ACTUAL 1.0.0 
* POST /paramo/v1/user
* GET /paramo/v1/users

***
##EJEMPLOS  ENPOINTS 
***POST /paramo/v1/user***

curl --location --request POST 'http://localhost:8080/paramo/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "miguel22",
"email": "miguel@gmail22.com",
"address": "guardia nacional",
"phone": "1323232",
"userType": "SuperUser",
"money": 100.1
}'

***GET /paramo/v1/users***
curl --location --request GET 'http://localhost:8080/paramo/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "miguel2",
"email": "miguel@gmail2.com",
"address": "guardia nacional",
"phone": "1323232",
"userType": "SuperUser",
"money": 100.1
}'
