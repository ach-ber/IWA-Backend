# USER-SERVICE

## Roles

Les rôles existants sont :
- ADMIN
- FREE
- SILVER
- GOLD
- PLATINUM

## Register an Recruiter ( automatically create an User )

POST 'http://localhost:8090/recruiter/api/public/recruiters' \
--data-raw JSON :
{
"firstName": "admin",
"lastName": "admin",
"phone": "1234567890",
"email": "admin@example.com",
"createdAt": "2023-11-22",
"subscription": "USER_ADMIN",
"subscription_startDate": "2023-11-22",
"subscription_endDate": "2024-11-22",
"company_id": 123,
"establishments": [456, 789],
"password":"admin"
}

## Get Token

POST 'http://localhost:7000/auth/token' \
--data-raw JSON :
{
"email": "admin@example.com",
"password":"admin"
}

## Get User Infos

GET 'http://localhost:8090/user/api/protected/userInfo' \

## Connect DBeaver to the database (PostgreSQL)

1. Create a new connection
2. Select PostgreSQL
3. Host : localhost
4. Port : 5435
5. Database : user
6. User name : admin
7. Password : 1234
8. Click on "Test Connection" and "Finish"