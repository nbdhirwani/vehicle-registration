###Pre-requisites
- Java 11 (or later) Development kit installed.
- Maven 

###Helpful maven targets to build and execute the application
- `mvn test` Will execute all the tests.
- `mvn spring-boot:run` Start the project as spring boot application.

###Available API Endpoints
| Endpoint                                 | Method | Purpose                                                     |
|------------------------------------------|--------|-------------------------------------------------------------|
| /person                   | POST    | Create a person object in datastore |
| /person/all                   | GET    | Fetch all the available person objects from datastore |
| /vehicle                   | POST    | Create a vehicle object in datastore |
| /vehicle/all                   | GET    | Fetch all the available vehicle objects from datastore |
| /registrations                   | POST    | Create a vehicle registration object in datastore |
| /registrations                   | DELETE    | Delete a vehicle registration object from datastore |
| /registrations/all                   | GET    | Fetch all the available vehicle registration objects from datastore |

###Other available application endpoint URLs
| URL                                 | Purpose                                                     |
|------------------------------------------|-------------------------------------------------------------|
| http://localhost:8080/swagger-ui.html | Use this URL to open Swagger UI for API endpoints documentation. |
| http://localhost:8080/v3/api-docs/ | Use this URL to open json/download representation of API endpoint documentation. |
| http://localhost:8080/v3/api-docs.yaml | Use this URL to open/download yaml representation of API endpoint documentation. |
