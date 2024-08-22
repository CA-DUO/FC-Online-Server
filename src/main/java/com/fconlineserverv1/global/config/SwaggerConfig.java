package com.fconlineserverv1.global.config;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springBootAPI() {

        Info info = new Info()
                .title("FC-Online-Server-V1")
                .description("FC-Online-Community")
                .contact(new Contact().url("https://github.com/CA-DUO/FC-Online-Server").email("dkdldrha@naver.com"));


        Server server = new Server().url("/");

        return new OpenAPI()
                .servers(List.of(server))
                .info(info);
    }
}
