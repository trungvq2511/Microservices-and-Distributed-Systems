package com.trungvq.notification.kafka.config;

import com.trungvq.clients.notification.NotificationSendRequest;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationKafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${spring.kafka.security.protocol}")
    private String securityProtocol;

    @Value("${spring.kafka.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.kafka.sasl.mechanism}")
    private String saslMechanism;

    public Map<String, Object> consumerConfig() {
        // create Consumer Properties
        HashMap<String, Object> properties = new HashMap<>();

        // connect to Conduktor Playground
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        properties.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
        properties.put(SaslConfigs.SASL_MECHANISM, saslMechanism);

        return properties;
    }

    @Bean
    public ConsumerFactory<String, NotificationSendRequest> consumerFactory() {
        JsonDeserializer<NotificationSendRequest> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, NotificationSendRequest>> messageFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NotificationSendRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
