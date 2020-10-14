package br.com.ironcoorp.dogbank.messaging;

import br.com.ironcoorp.dogbank.domain.Tutor;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {


    public static void enviaProposta(Tutor tutor){
    var emailService = new EmailService();
        try (var service = new KafkaService(EmailService.class.getSimpleName(),
            "ABERTURA_CONTA_DIGITAL",
            emailService::parse)) {
        service.run();
         }
    }


    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("------------------------------------------");
        System.out.println("Send email");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace();
        }
        System.out.println("Email sent");
    }

}
