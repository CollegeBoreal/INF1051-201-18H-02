# Slick

## Play

* Creer un environemment Play (g8 seed)

## Slick

* Integrer [Slick](http://slick.lightbend.com/doc/3.2.2/) avec MySQL

1 - Injecter `protected val dbConfigProvider: DatabaseConfigProvider` 

2 - Ajouter un `currying` pour permettre un code asynchrone `(implicit ec: ExecutionContext)`

3 - Ajouter un heritage permettant la connection JDBC  `with HasDatabaseConfigProvider[JdbcProfile] `

```Scala
class UserController @Inject()(
                                mcc: MessagesControllerComponents
                               , protected val dbConfigProvider: DatabaseConfigProvider
                              )
                              (implicit ec: ExecutionContext)
                              extends MessagesAbstractController(mcc)
                              with HasDatabaseConfigProvider[JdbcProfile] {
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
