package dev.pgjbz.urlshorter.infra.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import dev.pgjbz.urlshorter.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(final String target, final String message) {
        log.info("send message '{}' to topic '{}'", message, target);
        final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(target, message);
        kafkaTemplate.send(producerRecord);
    }

}
