package dev.arena.order.config;

import dev.arena.order.constants.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name(Constants.ORDER_TOPIC).build();
    }
}
