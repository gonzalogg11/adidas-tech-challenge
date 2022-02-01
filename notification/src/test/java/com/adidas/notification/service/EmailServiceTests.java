package com.adidas.notification.service;

import com.adidas.notification.client.EmailApiClient;
import com.adidas.notification.dto.EmailDTO;
import com.adidas.notification.dto.EmailRequestDTO;
import com.adidas.notification.util.EmailOperation;
import com.adidas.notification.util.LangEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @MockBean
    private EmailApiClient emailApiClient;

    private String email;

    @Before
    public void init() {
        email = "test@test.com";
        doNothing().when(emailApiClient).sendEmail(any());
    }

    @Test
    public void test_sendEmailForSubscriptionActivated() {
        try {
            EmailRequestDTO emailRequest = new EmailRequestDTO();
            emailRequest.setEmail(email);
            emailRequest.setEmailOperation(EmailOperation.SUBSCRIPTION_ACTIVATED);
            emailService.sendEmail(emailRequest);

            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setEmail(email);
            emailDTO.setSubject("Adidas newsletter subscription activated!");
            emailDTO.setBody("Congratulations! Your subscription to Adidas newsletter has been activated.");

            verify(emailApiClient, times(1)).sendEmail(emailDTO);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void test_sendEmailForSubscriptionCanceled() {
        try {
            EmailRequestDTO emailRequest = new EmailRequestDTO();
            emailRequest.setEmail(email);
            emailRequest.setEmailOperation(EmailOperation.SUBSCRIPTION_CANCELED);
            emailRequest.setLanguage(LangEnum.ES);
            emailService.sendEmail(emailRequest);

            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setEmail(email);
            emailDTO.setSubject("Suscripción al boletín de Adidas cancelada :(");
            emailDTO.setBody("Tu suscripción al boletín de Adidas ha sido cancelada.");

            verify(emailApiClient, times(1)).sendEmail(emailDTO);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
