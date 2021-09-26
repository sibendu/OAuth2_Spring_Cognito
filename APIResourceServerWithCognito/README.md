# Sample oAuth2 Resource Server Integrated with AWS Cognito

A sample SpringBoot project with API endpoints protected using oAuth2 with AWS Cognito

# Steps:

1. Configure AWS user Identity Pool. This project uses client credentials (refer to cognito-setup*.jpg) 

2. Update applicaiton.yml to point to Cognito pool

3. To run: gradle clean build bootRun

4. Postman collection -> adjust values as per pool setting (url etc) 

Following endpoints are there: 
- localhost:8080/api/public/message -> PublicController.java -> publicly accessible
- localhost:8080/api/admin/message -> Check AdminController.java for permission needed
- localhost:8080/api/employee/message -> Check EmployeeController.java for permission needed

5. Swagger UI available at: http://localhost:8080/swagger-ui.html (can use oAuth client id/secret to authorize) 