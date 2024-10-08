package tmrv.dev.springprofilingfreemakerlogging.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class JavaMailSenderService {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    public JavaMailSenderService(JavaMailSender javaMailSender, Configuration configuration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    @Async
    public void sendFreemarkerMail(String username) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(username + "@mail.ru");
            mimeMessageHelper.setTo("to@gmail.com");
            mimeMessageHelper.setSubject("Subject");


            Map<String, Object> model = new HashMap<>();
            model.put("username", username);


            Template template = configuration.getTemplate("activate_account.ftlh");
            String processed = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setText(processed, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
