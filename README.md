# Health Monitor
Springboot/React web app for classifying/tracking health params


## Build with Docker
This stack may be run with a one-liner on any OS, provided a local docker installation and a web browser. Download and unzip/pull this project, navigate to the docker folder and in a terminal run:

docker-compose up -d

Once the build/install is done, open a browser to http://localhost:3000

note: localhost ports 3000, 8080, and 3306 must be available for use by the app.


## Build components individually

### Client:

requires node and npm

cd .../disease-classifier/client   
npm install  
npm start --port 3000

### Server:

requires gradle

cd .../disease-classifier/server  
gradle bootRun

### Database:

MySQL

requires docker

docker run -p 3306:3306 --name mysql-spring -e MYSQL_ROOT_PASSWORD=password -d mysql:latest  
docker ps  
docker exec -it <container_id> mysql -uroot -p  
CREATE USER 'user'@'%' IDENTIFIED BY 'secret';  
GRANT ALL PRIVILEGES ON &ast;.&ast; TO 'user';  
CREATE DATABASE healthmonitor;  
gradle flywayMigrate -i  
