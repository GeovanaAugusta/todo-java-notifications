package br.com.geovanaaugusta.itau.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.geovanaaugusta.itau.notificationservice.services.SnsNotificationService;

/**
 * Controlador REST para envio de notificações via SNS.
 * Este controlador permite o envio de notificações por e-mail e SMS através do Amazon SNS.
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private SnsNotificationService snsNotificationService;

    /**
     * Envia uma notificação por e-mail usando Amazon SNS.
     *
     * @param subject O assunto do e-mail.
     * @param message O conteúdo da mensagem do e-mail.
     * @return Uma ResponseEntity com o ID da mensagem enviada.
     */
    @PostMapping("/email")
    public ResponseEntity<String> sendEmailNotification(@RequestParam String subject,
                                                        @RequestParam String message) {
        String messageId = snsNotificationService.sendEmailNotification(subject, message);
        return ResponseEntity.ok("Email enviado com ID: " + messageId);
    }

    /**
     * Envia uma notificação por SMS usando Amazon SNS.
     *
     * @param phoneNumber O número de telefone para o qual o SMS será enviado.
     * @param message O conteúdo da mensagem SMS.
     * @return Uma ResponseEntity com o ID da mensagem enviada.
     */
    @PostMapping("/sms")
    public ResponseEntity<String> sendSmsNotification(@RequestParam String phoneNumber,
                                                      @RequestParam String message) {
        String messageId = snsNotificationService.sendSmsNotification(phoneNumber, message);
        return ResponseEntity.ok("SMS enviado com ID: " + messageId);
    }
}
