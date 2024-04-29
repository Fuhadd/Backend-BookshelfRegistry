package com.shelfscribe.backendbookshelfregistry.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name   = "Aminu Fuhad",
                        email  = "jnrolamilekan123@gmail.com",
                        url    =  "http://aminu-fuhad.web.app/"
                ),
                description    = "OpenApi documentation for Bookshelf Registry",
                title          = "OpenAPi specification - Bookshelf Registry",
                version        = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url         = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url         = "http://ec2-54-193-247-227.us-west-1.compute.amazonaws.com:8080/"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "SecureTokenAuth"
                )
        }
)
@SecurityScheme(
        name         = "SecureTokenAuth",
        description  = "A robust and secure JSON Web Token (JWT) authentication mechanism that ensures data integrity and confidentiality in web and mobile applications.",
        scheme       = "bearer",
        type         = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in           = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
