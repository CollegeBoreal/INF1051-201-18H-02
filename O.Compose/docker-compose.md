version: '2'

services:
   cfmweb:
     build: ./nginx
     container_name: cfmweb
     ports:
       - "80:80"
       - "443:443"
     depends_on:
       - cfmwebapp

   cfmwebapp:
     image: artists:0.1.0-SNAPSHOT
     container_name: cfmwebapp
     expose:
       - "9000"
     depends_on:
       - cfmdb

   cfmdb:
     image: mysql
     container_name: cfmdb
     environment:
      - MYSQL_ROOT_PASSWORD=password
     # Open Port for testing
     # ports:
     #  - "3306:3306"
