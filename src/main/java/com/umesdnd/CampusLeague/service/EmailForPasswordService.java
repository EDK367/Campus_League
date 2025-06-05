package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.DTO.EmailForPassword;
import com.umesdnd.CampusLeague.repository.UserRepository;
import com.umesdnd.CampusLeague.service.interfaces.EmailForPasswordServiceInterface;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailForPasswordService implements EmailForPasswordServiceInterface {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @Value("${EMAIL}")
    private String EMAIL;

    @Override
    public boolean sendEmail(EmailForPassword email) {
        if (email == null || email.getTo() == null || email.getTo().isBlank()) {
            throw new NewExceptionType("No se ha adjuntado el email", HttpStatus.BAD_REQUEST);
        }

        if (!userRepository.existsByEmail(email.getTo())) {
            throw new NewExceptionType("No se encuentra el email en la base de Datos", HttpStatus.NOT_FOUND);
        }

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            String content = genareteText(email.getTo());
            helper.setTo(email.getTo());
            helper.setSubject("Campus_League");
            helper.setFrom(this.EMAIL);
            helper.setText(content, true);

            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            throw new NewExceptionType("No se pudo enviar el email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String genareteText(String email) {
        String code = otpService.generateCode(email);
        String htmlContent = String.format("""
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="UTF-8" />
                  <title>Código de Verificación</title>
                  <style>
                    /* Aquí va tu CSS */
                    .container {
                      max-width: 480px;
                      margin: 40px auto;
                      background-color: #ffffff;
                      border-radius: 8px;
                      padding: 30px;
                      text-align: center;
                      color: #333333;
                    }
                    .code {
                      font-family: 'Courier New', Courier, monospace;
                      background-color: #e2e8f0;
                      padding: 15px 30px;
                      font-size: 28px;
                      font-weight: bold;
                      border-radius: 6px;
                      color: #2b6cb0;
                    }
                  </style>
                </head>
                <body>
                  <div class="container">
                    <h1>Tu código de verificación</h1>
                    <p>Utiliza el siguiente código para continuar con tu proceso. Este código es válido por 10 minutos.</p>
                    <div class="code">%s</div>
                    <p>Si no solicitaste este código, ignora este correo.</p>
                  </div>
                </body>
                </html>
                """, code);
        return htmlContent;
    }

}
