package dev.arena.payment.config;

import dev.arena.payment.constants.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {

    @Bean
    public NewTopic paymentTopicConfig() {
        return TopicBuilder.name(Constants.PAYMENT_TOPIC).build();
    }

}
