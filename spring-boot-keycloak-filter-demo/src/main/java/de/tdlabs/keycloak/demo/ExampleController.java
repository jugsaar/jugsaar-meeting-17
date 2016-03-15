package de.tdlabs.keycloak.demo;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 22.02.16.
 */
@RestController
public class ExampleController {

    @RequestMapping(path = "/data")
    public Map<String, Object> getData(HttpServletRequest request, Principal principal) {

        Map<String, Object> data = new HashMap<>();
        data.put("msg", "Hello");
        data.put("principalName", principal.getName());

        KeycloakSecurityContext securityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());

        data.put("realm", securityContext.getRealm());

        IDToken idToken = securityContext.getIdToken();
        data.put("userName", idToken.getPreferredUsername());
        data.put("displayName", idToken.getName());

        AccessToken accessToken = securityContext.getToken();
        AccessToken.Access resourceAccess = accessToken.getResourceAccess(accessToken.getIssuedFor());
        data.put("roles", resourceAccess != null ? resourceAccess.getRoles() : Collections.emptySet());

        return data;
    }
}
