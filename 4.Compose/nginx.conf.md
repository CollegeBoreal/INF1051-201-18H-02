worker_processes  1;

events {
    worker_connections  1024;
}

http {
  include       mime.types;
  default_type  application/octet-stream;

  sendfile        on;
  keepalive_timeout  65;

  proxy_buffering    off;
  proxy_set_header   X-Real-IP $remote_addr;
  proxy_set_header   X-Forwarded-Proto $scheme;
  proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
  proxy_set_header   Host $http_host;
  proxy_http_version 1.1;

  upstream my-backend {
     # The DNS name follows Docker Service convention
     server localhost:9000;
  }

  server {
    listen       80;
    #server_name 192.168.99.100;
    location / {
       proxy_pass http://my-backend;
    }
  }

  server {
    listen               443;
    ssl                  on;
  
  # http://www.selfsignedcertificate.com/ is useful for development testing
  #  ssl_certificate      /etc/ssl/certs/mycert.fm.crt;
  #  ssl_certificate_key  /etc/ssl/private/mycert.fm.key;
  
  
  #  # From https://bettercrypto.org/static/applied-crypto-hardening.pdf
    ssl_prefer_server_ciphers on;
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2; # not possible to do exclusive
    ssl_ciphers 'EDH+CAMELLIA:EDH+aRSA:EECDH+aRSA+AESGCM:EECDH+aRSA+SHA384:EECDH+aRSA+SHA256:EECDH:+CAMELLIA256:+AES256:+CAMELLIA128:+AES128:+SSLv3:!aNULL:!eNULL:!LOW:!3DES:!MD5:!EXP:!PSK:!DSS:!RC4:!SEED:!ECDSA:CAMELLIA256-SHA:AES256-SHA:CAMELLIA128-SHA:AES128-SHA';
    add_header Strict-Transport-Security max-age=15768000; # six months
  #  # use this only if all subdomains support HTTPS!
  #  # add_header Strict-Transport-Security "max-age=15768000; includeSubDomains"
  
    keepalive_timeout    70;
  #  server_name www.mysite.com;
    location / {
      proxy_pass  http://my-backend;
    }
  }
}