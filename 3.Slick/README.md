# Slick

Creer un environemment Play

Integrer [Slick](http://slick.lightbend.com/doc/3.2.2/) avec MySQL


## Installer MySQL

```
$ export MYSQL_ROOT_PASSWORD=<donner un password>
```

```
$ docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} -p 3306:3306 -d mysql:latest 
```

```
$ docker exec -i some-mysql mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "create database etudiants;"
```

```
$ docker exec -i some-mysql mysql -u root -p${MYSQL_ROOT_PASSWORD} \
     -e "grant all privileges on etudiants.* to 'etudiants'@'localhost' identified by 'etudiants_1';"
```

```
$ docker exec -i some-mysql mysql -u root -p${MYSQL_ROOT_PASSWORD} \
     -e "grant all privileges on etudiants.* to 'etudiants'@'%' identified by 'etudiants_1';"
```

```
$ docker exec -i some-mysql  mysql -u root -p${MYSQL_ROOT_PASSWORD} etudiants < ~/Developer/etudiants.sql
```
