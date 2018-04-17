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



