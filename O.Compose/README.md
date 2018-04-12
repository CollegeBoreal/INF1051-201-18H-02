# Compose Install

## Create own directory

Note: MyID with your student ID

```
$ mkdir MyID; cd MyID 
```

## Frontend

* Create a `nginx` and `nginx/conf` directories

```shell
$ mkdir -p nginx/conf
```

* Edit the configuration file `nginx/conf/nginx.conf`

* copy the [nginx.conf](./nginx.conf.md) content to the `nginx/conf/nginx.conf` file

* Edit the `nginx/Dockerfile` file 

* copy the [Dockerfile](./Dockerfile.md) content to the 'nginx/Dockerfile`

## create your Play Framework backend

```shell
$ sbt new playframework/play-scala-seed.g8 --name=backend
```

* Set the Play secret key

```shell
$ cd backend
$ sbt playGenerateSecret
```

* put the secret key in `conf/application.conf`

play.crypto.secret="IN_PRODUCTION_CHANGE_THIS_TO_A_LONG_RANDOM_STRING"

* to create a docker image run:

```
$ sbt dist
$ sbt docker:publishLocal
```

## Create your orchestration file

* copy the [docker-compose.yml](./docker-compose.yml.md) content to the `docker-compose.yml` file of your project root directory

Build the orchestration module

```
$ docker-compose build
```

Launch the orchestration
```
$ docker-compose up -d
```
