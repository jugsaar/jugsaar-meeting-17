# OAuth 2.0 via CURL

# 
```
curl -v http://localhost:8081/auth/realms/master/.well-known/openid-configuration | jq .

``` 


```json
{
  "issuer": "http://localhost:8081/auth/realms/master",
  "authorization_endpoint": "http://localhost:8081/auth/realms/master/protocol/openid-connect/auth",
  "token_endpoint": "http://localhost:8081/auth/realms/master/protocol/openid-connect/token",
  "token_introspection_endpoint": "http://localhost:8081/auth/realms/master/protocol/openid-connect/token/introspect",
  "userinfo_endpoint": "http://localhost:8081/auth/realms/master/protocol/openid-connect/userinfo",
  "end_session_endpoint": "http://localhost:8081/auth/realms/master/protocol/openid-connect/logout",
  "jwks_uri": "http://localhost:8081/auth/realms/master/protocol/openid-connect/certs",
  "grant_types_supported": [
    "authorization_code",
    "implicit",
    "refresh_token",
    "password",
    "client_credentials"
  ],
  "response_types_supported": [
    "code",
    "none",
    "id_token",
    "token",
    "id_token token",
    "code id_token",
    "code token",
    "code id_token token"
  ],
  "subject_types_supported": [
    "public"
  ],
  "id_token_signing_alg_values_supported": [
    "RS256"
  ],
  "response_modes_supported": [
    "query",
    "fragment",
    "form_post"
  ],
  "registration_endpoint": "http://localhost:8081/auth/realms/master/clients-registrations/openid-connect"
}
```


```
 curl -v http://localhost:8081/auth/realms/master/protocol/openid-connect/auth\?response_type=code\&client_id=javaee-petclinic
 ```


 ```
 curl -s -c tmp/cookies.txt http://localhost:8081/auth/realms/master/protocol/openid-connect/auth\?response_type=code\&client_id=javaee-petclinic | grep 'action='
 ```


 curl -v -c tmp/cookies.txt -X POST --data 'username=theo&password=test' http://localhost:8081/auth/realms/master/login-actions/authenticate\?code=__LZGEVADC9D_nZiMcAIOMuk6BystZCBCwIe-bV1PxY.a14eb4e5-1fc4-409a-bae8-6dae5c07e5b0\&execution=422e5963-f201-45c1-81c3-38035acf5ad7


 curl -v -c tmp/cookies.txt -X GET http://localhost:8081/auth/realms/master/protocol/openid-connect/token