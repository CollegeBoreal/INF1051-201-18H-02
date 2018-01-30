# Echaffaudage (Scaffolding)


## Creer un projet sous `Play Framework`

```
$ sbt new playframework/play-scala-seed.g8 --name=<mon ID>
```

## Echaffaudage

* Changer la version de `sbt` dans `project/build.properties`

   de `1.0.4` a `0.13.13`

* enlever le commentaire du `plugin` `sbt scaffold` dans le fichier `project/scaffold.sbt`

   //addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.10.0")
   
* créer un formulaire sous le CLI `sbt` avec la commande ci-dessous

```
sbt> g8Scaffold form
```

## Test

* Rajouter les `imports` ci-dessous a chaque fichier `*Spec.scala`

import scala.concurrent.ExecutionContext
import play.api.http.FileMimeTypes
import play.api.test.CSRFTokenHelper._
import org.scalatestplus.play.guice._

* executer les tests

```
$ sbt test
```

* Executer un seul test

```
sbt> testOnly controllers.HomeControllerSpec
```

* remplacer `derp` par le nom de la form comme specifie dans la route (i.e. client)

```Scala
    "render the index page from the router" in {
      val request = CSRFTokenHelper.addCSRFToken(FakeRequest(GET, "/derp"))
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }
```

# Projet promettant:

https://github.com/dnvriend/sbt-scaffold-play
