# Spark


```docker
version: '2'

services:

  infdb:
     image: mysql
     environment:
      - MYSQL_ROOT_PASSWORD=password
     ports:
       - "3306:3306"

  infspark:
     build: ./data
     ports:
       - "8088:8088"
       - "8042:8042"
       - "4040:4040"
     entrypoint:
       - "/etc/bootstrap.sh"
       - -d
     depends_on:
       - infdb
```
