package com.ynhj.ingress.graphsqlController;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Controller
public class GraphqlController {
    private final WebClient http;

    public GraphqlController(WebClient http) {
        this.http = http;
    }

    @SchemaMapping(typeName = "Query", field = "products")
    Flux<Product> products() {
        return this.http.get().uri("http://localhost:8080/flux/products")
                .retrieve().bodyToFlux(Product.class);
    }

}

record Product(Integer id, String name, String description) {
}
