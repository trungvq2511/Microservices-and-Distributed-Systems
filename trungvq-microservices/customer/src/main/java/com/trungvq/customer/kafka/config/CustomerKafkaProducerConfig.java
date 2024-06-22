package com.trungvq.customer.kafka.config;

import com.trungvq.clients.notification.NotificationSendRequest;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CustomerKafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${spring.kafka.security.protocol}")
    private String securityProtocol;

    @Value("${spring.kafka.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.kafka.sasl.mechanism}")
    private String saslMechanism;

    public Map<String, Object> producerConfig() {
        HashMap<String, Object> properties = new HashMap<>();

        // connect to Conduktor Playground
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        properties.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
        properties.put(SaslConfigs.SASL_MECHANISM, saslMechanism);

        // set properties
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return properties;
    }

    @Bean
    public ProducerFactory<String, NotificationSendRequest> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, NotificationSendRequest> kafkaTemplate(ProducerFactory<String, NotificationSendRequest> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
