# Notification Service

Este projeto é um microserviço para envio de notificações via e-mail e SMS, desenvolvido usando Spring Boot. Ele utiliza o Amazon Simple Notification Service (SNS) para enviar notificações aos usuários.

## Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.3.4**
* **Amazon SNS (SDK v2)**
* **Maven**

## Funcionalidades

* Envio de notificações via e-mail.
* Envio de notificações via SMS.

## Pré-requisitos

Antes de iniciar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

* **Java 17+**
* **Maven 3.6+**
* **Amazon AWS Account** com permissões para o SNS.

## Configuração do Projeto

1. **Clone o repositório:**
   
   ```bash
    git clone https://github.com/seu-repositorio/notification-service.git
    cd notification-service
   ```

2. **Configurar application.properties**
   
   No diretório src/main/resources, configure o arquivo application.properties com suas credenciais da AWS e ARN do tópico SNS:

   ```properties
   aws.accessKeyId=SEU_ACCESS_KEY
    aws.secretAccessKey=SUA_SECRET_KEY
    aws.region=us-east-2
    aws.sns.topic.arn=arn:aws:sns:us-east-2:123456789012:seu-topico-sns
    ```

3. **Build do projeto**

Para compilar o projeto, rode o seguinte comando Maven:
```bash
mvn clean install
```

4. **Executando o Projeto:**

Para rodar o microserviço, use o seguinte comando:
```bash
mvn spring-boot:run
```

5. **Endpoints da API**

    Enviar Notificação por E-mail

    URL: /api/notifications/email

    Método: POST

    Parâmetros:
        subject (String): Assunto do e-mail.
        message (String): Corpo do e-mail.

    Exemplo de requisição:

```bash
curl -X POST "http://localhost:8080/api/notifications/email" -d "subject=Assunto do Email" -d "message=Corpo do Email"
```
Resposta: ID da mensagem SNS.

Enviar Notificação por SMS

URL: /api/notifications/sms

Método: POST

Parâmetros:

    phoneNumber (String): Número de telefone no formato internacional (ex: +5511999999999).
    message (String): Texto da mensagem a ser enviada.

Exemplo de requisição:

```bash
curl -X POST "http://localhost:8080/api/notifications/sms" -d "phoneNumber=+5511999999999" -d "message=Mensagem de Teste"
```
Resposta: ID da mensagem SNS.
