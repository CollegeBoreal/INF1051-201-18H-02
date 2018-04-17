#  Mongo


## Creer une instance sous docker

```
docker run --name some-mongo -p27017:27017 -d mongo 
```

Exporter une collection

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

## Interation a Play

http://reactivemongo.org/releases/0.1x/documentation/tutorial/play.html

```shell
$ echo "libraryDependencies += \"org.reactivemongo\" %% \"play2-reactivemongo\" % \"0.13.0-play26\"" \
  >> build-mongo.sbt
```

