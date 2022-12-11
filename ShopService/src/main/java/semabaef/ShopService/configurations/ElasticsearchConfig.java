package semabaef.ShopService.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "semabaef.ShopService.repositories")
@ComponentScan(basePackages = "semabaef.ShopService")
public class ElasticsearchConfig extends AbstractReactiveElasticsearchConfiguration {

    @Value("${spring.elasticsearch.url}")
    private String elasticSearchUrl;

    @Bean
    @Override
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        final ClientConfiguration config = ClientConfiguration.builder()
                .connectedTo(elasticSearchUrl)
                .build();
        return ReactiveRestClients.create(config);
    }

}
