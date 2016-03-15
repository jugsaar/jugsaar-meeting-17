package de.tdlabs.keycloak.demo;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.servlet.KeycloakOIDCFilter;
import org.keycloak.adapters.spi.HttpFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ExampleServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ExampleServiceApp.class, args);
    }

    @Bean
    FilterRegistrationBean keycloakFilter() {

        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new KeycloakOIDCFilter());
        filter.setAsyncSupported(true);
        filter.setName("keycloakFilter");
        filter.setUrlPatterns(Arrays.asList("/*"));
        filter.addInitParameter("keycloak.config.resolver", DynamicKeycloakConfigResolver.class.getName());
        return filter;
    }

    public static class DynamicKeycloakConfigResolver implements KeycloakConfigResolver {

        @Override
        public KeycloakDeployment resolve(HttpFacade.Request facade) {
            return KeycloakDeploymentBuilder.build(getClass().getResourceAsStream("/META-INF/keycloak.json"));
        }
    }
}
