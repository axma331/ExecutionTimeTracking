package t1.ismailov.timetracking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("TimeTracking API")
                        .description("Service for storing and retrieving method running time")
                        .version("1.0.0"))
                .servers(List.of(new Server()
                        .url("http://localhost:8005/")
                        .description("Local server")));
    }

    @Bean
    public GroupedOpenApi customOpenAPI() {
        return GroupedOpenApi.builder()
                .group("statistics")
                .addOpenApiCustomizer(openApi ->
                        openApi.getPaths().forEach((path, pathItem) ->
                                pathItem.readOperations().forEach(operation -> {
                                    if (operation.getTags().contains("Statistics")) {
                                        operation.getResponses()
                                                .addApiResponse("400", new ApiResponse()
                                                        .description("invalid request"))
                                                .addApiResponse("500", new ApiResponse()
                                                        .description("internal server error"));
                                    }
                                })))
                .build();
    }
}