package br.com.geovanaaugusta.itau.notificationservice;

import br.com.geovanaaugusta.itau.notificationservice.controller.NotificationController;
import br.com.geovanaaugusta.itau.notificationservice.services.SnsNotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SnsNotificationService snsNotificationService;

    @Test
    void testSendEmailNotification() throws Exception {
        Mockito.when(snsNotificationService.sendEmailNotification(Mockito.anyString(), Mockito.anyString()))
                .thenReturn("12345");

        mockMvc.perform(post("/api/notifications/email")
                        .param("subject", "Test Subject")
                        .param("message", "Test Message")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Email enviado com ID: 12345"));
    }

    @Test
    void testSendSmsNotification() throws Exception {
        Mockito.when(snsNotificationService.sendSmsNotification(Mockito.anyString(), Mockito.anyString()))
                .thenReturn("67890");

        mockMvc.perform(post("/api/notifications/sms")
                        .param("phoneNumber", "+1234567890")
                        .param("message", "Test SMS")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("SMS enviado com ID: 67890"));
    }
}
