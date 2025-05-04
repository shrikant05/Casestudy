This casestudy can only run on local database.

******* To run this casestudy on cloud Database *******
1)login to Mongo Atlas Database.
2)Create a Database (i.e RailwayUserData) 
3)In that Database create collection named roles and create roles which you want (ie.ROLE_USER and ROLE_ADMIN)
Refer to the image attached |
![image](https://github.com/user-attachments/assets/f12ab83c-aa7b-4471-ade3-6ad6b42bd691)

4)In each microservices in resources-->application.yml file change local database config to cloud 
(ie 
 data:
  mongodb:
    uri: mongodb+srv://shrikant05:<entermongopassword>@cluster0.9jk9l.mongodb.net/RailwayUserData
)



******* To connect Microservice with Frontend *******

1)To connect Microservice with Frontend successfully dont forget to add @CrossOrigin(origins="http://localhost:4200", maxAge = 3600, allowCredentials="true") above controller class
This annotation enables cross-origin requests, allowing front-end applications running on different domains to access the backend API.
2) complete frontend code is available in attached github link : https://github.com/shrikant05/Casestudy_frontend


