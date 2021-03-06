#  Mongo


## Creer une instance sous docker


* Un volume partagé sera crée

```
docker run --name some-mongo -p27017:27017 -v "$(pwd)":/data -d mongo 
```

Importer des donnees (a travers un volume)

```shell
docker exec -i some-mongo mongoimport -d INF1069 -c departments --drop --type csv --file /data/Semaine01/departments.csv --headerline
```

Exporter une collection sans l'intermediare d'un volume

```
docker exec -i some-mongo  mongoexport --db Transfert --collection Adresse --type=csv --fields _id,name,prenom 2>/tmp/mongoexport.err  > info.csv
```


## Utiliser le Module ReactiveMongo

https://www.playframework.com/documentation/2.6.x/ModuleDirectory#MongoDB-ReactiveMongo-Plugin-(Scala)

### Ajouter la librairie

* Ajouter reactivemongo au fichier librairie . (Note: you can copy and paste the command in your terminal)

```shell
$ echo "libraryDependencies += \"org.reactivemongo\" %% \"reactivemongo\" % \"0.13.0\"" > build-mongo.sbt
```

Documentation:

http://reactivemongo.org/releases/0.1x/documentation/index.html

## Integration a Play

http://reactivemongo.org/releases/0.1x/documentation/tutorial/play.html

```shell
$ echo "libraryDependencies += \"org.reactivemongo\" %% \"play2-reactivemongo\" % \"0.13.0-play26\"" \
  >> build-mongo.sbt
```

## Ajouter le module a Application.conf

* Creer le fichier mongo.conf 

```shell
$ echo "play.modules.enabled += \"play.modules.reactivemongo.ReactiveMongoModule\"" > conf/mongo.conf
```

* Ajouter mongo a conf/application.conf

```
$ printf "\ninclude \"mongo.conf\"" >> conf/application.conf
```

## Configurer l'URL de connection

```
$ echo "mongodb.uri = \"mongodb://localhost:27017/Transfert\"" >> conf/mongo.conf
```

## Coder le controlleur

```scala
import javax.inject.Inject

import play.api.mvc.{ AbstractController, ControllerComponents }
import play.modules.reactivemongo._

class MyController @Inject() (
  components: ControllerComponents,
  val reactiveMongoApi: ReactiveMongoApi
) extends AbstractController(components)
  with MongoController with ReactiveMongoComponents {
...
}
```
