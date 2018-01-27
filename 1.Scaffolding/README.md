# Echaffaudage (Scaffolding)


## Creer un projet sous `Play Framework`

```
$ sbt new playframework/play-scala-seed.g8 --name=<mon ID>
```

## Echaffaudage

* Changer la version de `sbt` dans `project/build.propoerties`

   de `1.0.4` a `0.13.13`

* enlever le commentaire du `plugin` `sbt scaffold`

   addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.10.0")
   
* créer un formulaire sous le CLI `sbt` avec la commande ci-dessous

```
sbt> g8Scaffold form
```


