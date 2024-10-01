package br.com.geovanaaugusta.itau.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação NotificationServiceApplication.
 * 
 * Esta classe inicializa a aplicação de serviço de notificações utilizando o framework Spring Boot.
 */
@SpringBootApplication
public class NotificationServiceApplication {

    /**
     * Método principal (entry point) da aplicação.
     * 
     * Este método utiliza o {@link SpringApplication} para iniciar a aplicação.
     *
     * @param args Argumentos de linha de comando passados para a aplicação.
     */
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

}
