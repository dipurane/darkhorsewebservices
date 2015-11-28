# Billfold Web Services
Bill Web Services is stand alone microservice project which is intended to build 
backend services of Billfold Apps which will be exposed over REST.

### Version
1.0

### Steps For Running App

1. Checkout code from github using git clone https://github.com/dipurane/darkhorsewebservices.git
2. If already checked out get latest copy using git pull from source folder.
3. Build code using 'mvn package docker:build'
4. Image billfold/billfoldwebservices would be created as result of mvn build
5. Run the image using docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8000:8000 -t billfold/billfoldwebservices
6. In case of issues please cotact 'dipesh.rane@synerzip.com'


