# Sample oAuth2 Authorization Server using Spring

A sample SpringBoot project implementing oAuth2 Authorization Server using Spring

# Available Endpints

POST /oauth2/token — as a token endpoint,
POST /oauth2/authorize — as Authorization endpoint,
GET /oauth2/authorize — It is also an Authorization endpoint,
POST /oauth2/revoke — as a token revoke endpoint,
GET /oauth2/jwks — It is a JWT key endpoint.

# Use of oAuth2 Client Credentials 

In AuthorizationServerConfig.java -> Change following line to use variable client2
InMemoryRegisteredClientRepository repo = new InMemoryRegisteredClientRepository(client2);

Using Postman, 
POST http://localhost:9000/oauth2/token
with Basic Authorization using client id/secret
and Content-Type: application/x-www-form-urlencoded
and Body: grant_type=client_credentials&scope=testscope

Response:
{
    "access_token": "eyJr...",
    "scope": "testscope",
    "token_type": "Bearer",
    "expires_in": 300
}

# Use of oAuth2 AUTHORIZATION CODE

In AuthorizationServerConfig.java -> Change following line to use variable client1
InMemoryRegisteredClientRepository repo = new InMemoryRegisteredClientRepository(client2);

1. Authenticate and get the OAuth2 Code

Browser: http://localhost:9000/oauth2/authorize?response_type=code&client_id=client1&scope=read

Redirects to:  http://localhost:9000/authorized?code=S3TGXxGr3fDxl7s-dRH1nxgZ8BazKLcZVcK9NDqYjZV7k353z4TEb9Gg-JZKE4YAtLC9WWGhZg82kFAP5kZUJk8skB_0bakfCxjo4WyI1b5Wbp7d6n-t-0ql7w4q09ZC
 
2. Exchange the OAuth Code for an Access token and Refresh token.

Using Postman, 
POST http://localhost:9000/oauth2/token?grant_type=authorization_code&code=<Auth Code in above URL>
with Basic Authorization using client id/secret

Response will be like:

{
    "access_token": "eyJraW...",
    "refresh_token": "xZ4pfU0VQ...._s3_f",
    "scope": "read",
    "token_type": "Bearer",
    "expires_in": 300
}

3. Once the accesss token has expired, we can use the Refresh Token

Using Postman, 
POST http://localhost:9000/oauth2/token?grant_type=refresh_token&refresh_token=<Refresh token from above response payload>
with Basic Authorization using client id/secret
