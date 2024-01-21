package in.uday.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailsUtil {

    @Autowired
    private JavaMailSender mailsender;

    public boolean sendEmail(String subject, String body, String to) {

        boolean isSent = false;

        try {
            MimeMessage mimeMsg = mailsender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailsender.send(mimeMsg);

            isSent = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSent;
    }
}