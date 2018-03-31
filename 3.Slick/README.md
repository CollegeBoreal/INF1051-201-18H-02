# Slick

## Play

* Creer un environemment Play (g8 seed)

## Slick

* Integrer [Slick](http://slick.lightbend.com/doc/3.2.2/) avec MySQL

* Integrer avec Play [ScalaDatabase](https://www.playframework.com/documentation/2.6.x/ScalaDatabase)

1 - Injecter `protected val dbConfigProvider: DatabaseConfigProvider` 

2 - Ajouter un `currying` pour permettre une execution asynchrone `(implicit ec: ExecutionContext)`

3 - Ajouter un héritage permettant la connection JDBC (i.e. objet `db`) `with HasDatabaseConfigProvider[JdbcProfile] `


## Import nescessaire

```Scala
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider} // Play Sclick  

import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._ . // MySQL
import scala.concurrent.{ExecutionContext, Future} // Asynchrone
```

## Exemple au niveau controlleur

```Scala
class UserController @Inject()(
      mcc: MessagesControllerComponents
      , protected val dbConfigProvider: DatabaseConfigProvider
    )
    (implicit ec: ExecutionContext)
    extends MessagesAbstractController(mcc)
    with HasDatabaseConfigProvider[JdbcProfile] {
```

## Exemple au niveau service

```Scala
class UserService @Inject()
    (protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
```

## Installer MySQL

```
$ export MYSQL_ROOT_PASSWORD=<donner un password>
```

```
$ docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} -p 3306:3306 -d mysql:latest 
```

```
$ docker exec -i some-mysql mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "create database Transfert;"
```

```
$ docker exec -i some-mysql mysql -u root -p${MYSQL_ROOT_PASSWORD} \
     -e "grant all privileges on Transfert.* to 'etudiants'@'localhost' identified by 'etudiants_1';"
```

```
$ docker exec -i some-mysql mysql -u root -p${MYSQL_ROOT_PASSWORD} \
     -e "grant all privileges on Transfert.* to 'etudiants'@'%' identified by 'etudiants_1';"
```

```
$ docker exec -i some-mysql  mysql -u root -p${MYSQL_ROOT_PASSWORD} etudiants < ~/Developer/etudiants.sql
```
