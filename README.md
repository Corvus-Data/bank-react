# Bank React

Es un proyecto de software el cual tiene como objetivo mostrar un microservicio 
construido bajo los siguientes frameworks.

* Spring Boot
* Kotlin 
* Gradle
* Mustache
* PostgreSQL

Esta bajo la licencia de Apache 2.0 y es propiedad de Corvus Data.

## Como usar Bank React

El proyecto esta originalmente programado en el IDE IntellJ IDEA, por lo que puedes 
clonarlo desde el repositorio oficial en la siguiente URL: https://github.com/Corvus-Data/bank-react


### Crear Base de Datos 

Deberemos de crear una base de datos en PostgreSQL (puedes cambiar si lo gustas las base de datos).

```
CREATE USER bank_react_user WITH PASSWORD 'bank_react_user';
CREATE DATABASE bank_react_db OWNER bank_react_user;
```

Cuando ejecutemos por primera vez el código deberemos de crear la base de datos, así como deberemos de
colocar la dirección de la base de datos que vamos a usar.

Para poder instalar la aplicación ejecutaremos la tarea de gradle

```
gradle bootJar
```

Esto generará el jar dentro de la siguiente ruta: 

```
bank-react/build/libs/bank-react-0.0.1-SNAPSHOT.jar
```

Con ello podremos ejecutar el proceso de ejecución con el siguiente comando: 

```
java -jar bank-react/build/libs/bank-react-0.0.1-SNAPSHOT.jar 
```

Disfruten!

#### Bank React By Corvus Data
www.corvusdata.com.mx