package br.com.geovanaaugusta.itau.notificationservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

/**
 * Serviço responsável pelo envio de notificações utilizando Amazon SNS.
 * Este serviço permite o envio de notificações por e-mail e SMS usando o AWS SNS.
 */
@Service
public class SnsNotificationService {

    private final SnsClient snsClient;

    @Value("${aws.sns.topic.arn}")
    private String snsTopicArn;

    /**
     * Construtor da classe SnsNotificationService.
     *
     * @param accessKeyId Chave de acesso AWS.
     * @param secretAccessKey Chave secreta de acesso AWS.
     * @param region Região da AWS onde o SNS está configurado.
     */
    public SnsNotificationService(@Value("${aws.accessKeyId}") String accessKeyId,
                                  @Value("${aws.secretAccessKey}") String secretAccessKey,
                                  @Value("${aws.region}") String region) {
        this.snsClient = SnsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .httpClient(ApacheHttpClient.builder().build())
                .build();
    }

    /**
     * Envia uma notificação por e-mail através do Amazon SNS.
     *
     * @param subject O assunto do e-mail.
     * @param message A mensagem que será enviada no corpo do e-mail.
     * @return O ID da mensagem enviada.
     */
    public String sendEmailNotification(String subject, String message) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(snsTopicArn)
                .subject(subject)
                .message(message)
                .build();

        PublishResponse result = snsClient.publish(request);
        return result.messageId();
    }

    /**
     * Envia uma notificação por SMS através do Amazon SNS.
     *
     * @param phoneNumber O número de telefone para o qual o SMS será enviado (no formato internacional).
     * @param message A mensagem que será enviada via SMS.
     * @return O ID da mensagem enviada.
     */
    public String sendSmsNotification(String phoneNumber, String message) {
        PublishRequest request = PublishRequest.builder()
                .phoneNumber(phoneNumber)
                .message(message)
                .build();

        PublishResponse result = snsClient.publish(request);
        return result.messageId();
    }
}
