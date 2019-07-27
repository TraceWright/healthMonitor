# healthMonitor
Springboot/React web app for classifying/tracking health params

Client:

requires node and npm

cd .../disease-classifier/client
npm install
npm start --port 3000

note: the server is configured for CORS from http://localhost:3000

Server:

requires gradle

cd .../disease-classifier/server
gradle bootRun
