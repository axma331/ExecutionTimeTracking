package t1.ismailov.timetracking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("TimeTracking API")
                        .description("service for storing and retrieving method running time")
                        .version("0.1"))
                .servers(List.of(new Server()
                        .url("http://localhost:05/")
                        .description("Local server")));
    }
}
