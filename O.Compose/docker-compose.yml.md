```
version: '2'

services:
   trfweb:
     build: ./nginx
     container_name: trfweb
     ports:
       - "80:80"
       - "443:443"
     depends_on:
       - trfwebapp

   trfwebapp:
     image: backend:1.0-SNAPSHOT
     container_name: trfwebapp
     expose:
       - "9000"
     depends_on:
       - trfdb

   trfdb:
     image: mysql
     container_name: trfdb
     environment:
      - MYSQL_ROOT_PASSWORD=password
     # Open Port for testing
     # ports:
     #  - "3306:3306"
```
