
/etc/hosts

127.0.0.1 login.acme.local
127.0.0.2 app.petclinic.local
127.0.0.3 example-service.local
127.0.0.4 cool-app.local


Goto auth 
http://login.acme.local:8081/auth/

login:
admin
admin

Create new realm ACME

javaee7-petclinic
Example app based on Widlfly, Java EE 7, JSF
Original app: https://github.com/thomasdarimont/javaee7-petclinic

Open javaee7-petclinic in master branch.
/home/tom/dev/repos/gh/jugsaar/javaee7-petclinic
```git checkout feature/sso-with-keycloak```
Start plain javaee7-petclinic example app via intellij.

Open url: http://app.petclinic.local:8080/javaee7-petclinic-1.3-SNAPSHOT/

Demo Application - highlight no user authentication, details... present in page...

---

Create new client acme-petclinic:

Client ID: acme-petclinic
Name: Pet Clinic
Access Type: confidential


root url: http://app.petclinic.local:8080/javaee7-petclinic-1.3-SNAPSHOT
Valid redirect urls: /*
Base URL: /
Admin URL: /keycloak
Web Origins: http://localhost:8080 http://app.petclinic.local:8080

Create client roles: 
admin
user

Scope:
Full Scope Allowed: OFF

-> Client Roles -> javaee-petclinic -> Assign Roles user, admin

client config for keycloak.json
```json 
{
  "realm": "acme",
  "realm-public-key": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk8/XJRIbEW/DW30YxcnjNPFgZjhtsjcatetMP9xxyXDBPQA+jh9xAM4bf1v3QJOJalNuNpHat9k3e9Aa1M+qB1zq8blAZtsSF8jM7okuMHyeU6YXw7rY+d4t1Xigbt9LTaPfb5qCZvMShfjgeSb2DgZRbRlKfh9cVUZ8e7XUTm6UDlEvBI9mQiiwolPVyPCZDFNI8pb35NcXI4Kzh4S15BnAGpyKpiQJEmXX4wifYak8weJVoFuAu9fjjtdeacEqHFafyVZvEdKHTD+ofY9z6/JELdFRcI2N3a8rRa+JM2+CvrAIuOzLHGBZ1WwPKsHe6zhsxC1oODRvzVYOzOtvbQIDAQAB",
  "auth-server-url": "http://login.acme.local:8081/auth",
  "ssl-required": "external",
  "resource": "acme-petclinic",
  "credentials": {
    "secret": "7fb8f907-0cc8-4dfa-89c1-0ae779f3cc13"
  },
  "use-resource-role-mappings": true
}
```

Add map userPrincipal.getName() to the actual username instead of id:

"principal-attribute": "preferred_username"


Open ACME realm in keycloak admin console: http://login.acme.local:8081/auth/
Show acme-petclinic client.

Add sso support with keycloak Keycloak Filter Adpater
```git checkout feature/sso-with-keycloak```

Open again url: http://app.petclinic.local:8080/javaee7-petclinic-1.3-SNAPSHOT/

Highlight auth information in header.

Highlight configuration and integration:
- WEB-INF/keycloak.json
- KeycloakSsoFilter
- template.xhtml -> highlight user information
- KeycloakBean -> ManagedBean (JSF) to extract Auth data from keycloak Security context 

---

Spring Boot App with Servlet Filter

http://example-service.local:18080/app

example-service


---

Spring Boot App with Boot Adapter

http://cool-app.local:28080/cool/data

http://cool-app.local:28080/cool/admin-data


http://login.acme.local:8081/auth/realms/acme/protocol/openid-connect/logout?redirect_uri=http%3A%2F%2Fapp.petclinic.local%3A8080%2Fjavaee7-petclinic-1.3-SNAPSHOT

http://login.acme.local:8081/auth/realms/acme/protocol/openid-connect/logout?redirect_uri=http%3A%2F%2Fapp.petclinic.local%3A8080%2Fjavaee7-petclinic-1.3-SNAPSHOT&redirect_uri=http%3A%2F%2Flogin.acme.local%3A8081%2Fauth%2Frealms%2Facme%2Faccount?redirect_uri%3Dhttp%3A%2F%2Fapp.petclinic.local%3A8080%2Fjavaee7-petclinic-1.3-SNAPSHOT