# USER-SERVICE

## Register an user

POST 'http://localhost:7000/auth/register' \
--data-raw JSON :
{
    "name":"Basant",
    "password":"Pwd1",
    "email":"basant@gmail.com"
}

## Get Token

POST 'http://localhost:7000/auth/token' \
--data-raw JSON :
{
    "username":"Basant",
    "password":"Pwd1"
}

## Request a protected resource

GET 'http://localhost:8090/test/api/chefs' \

Bearer Token : token

## Connect DBeaver to the database (PostgreSQL)

1. Create a new connection
2. Select PostgreSQL
3. Host : localhost
4. Port : 5435
5. Database : user
6. User name : admin
7. Password : 1234
8. Click on "Test Connection" and "Finish"