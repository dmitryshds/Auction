package biz.bagira.auction.util;

import biz.bagira.auction.entities.User;
import biz.bagira.auction.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Dmitriy on 27.02.2017.
 */
public class MailUtil {

    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    private UserService userService;


    private final String MAIL_SMTP_AUTH;
    private final String MAIL_SMTP_STARTTLS_ENABLE;
    private final String MAIL_SMTP_HOST;
    private final String MAIL_SMTP_PORT;
    private final String MAIL_SMTP_SSL_TRUST;
    private final String MAIL_FROM;
    private final String PASSWORD;
    private final String APP_HOST;

    private Properties props = new Properties();

    public MailUtil(String MAIL_SMTP_AUTH,
                    String MAIL_FROM,
                    String MAIL_SMTP_STARTTLS_ENABLE,
                    String MAIL_SMTP_HOST,
                    String MAIL_SMTP_PORT,
                    String MAIL_SMTP_SSL_TRUST,
                    String PASSWORD,
                    String APP_HOST) {
        this.MAIL_SMTP_AUTH = MAIL_SMTP_AUTH;
        this.MAIL_FROM = MAIL_FROM;
        this.MAIL_SMTP_STARTTLS_ENABLE = MAIL_SMTP_STARTTLS_ENABLE;
        this.MAIL_SMTP_HOST = MAIL_SMTP_HOST;
        this.MAIL_SMTP_PORT = MAIL_SMTP_PORT;
        this.MAIL_SMTP_SSL_TRUST = MAIL_SMTP_SSL_TRUST;
        this.PASSWORD = PASSWORD;
        this.APP_HOST = APP_HOST;
        props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
        props.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
        props.put("mail.smtp.host", MAIL_SMTP_HOST);
        props.put("mail.smtp.port", MAIL_SMTP_PORT);
        props.put("mail.smtp.ssl.trust", MAIL_SMTP_SSL_TRUST);
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.SocketFactory");
    }

    private void sendMail(final String mailFrom, final String password, String mailTo, String message) {

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailFrom, password);
                    }
                });

        try {

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailTo));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            msg.setSubject("Confirm your E-mail");
            msg.setText(message, "utf-8", "html");
            Transport.send(msg);

            logger.info("Message sent to: "+mailTo);

        } catch (MessagingException e) {
            logger.info("Message send fail: " + e.getMessage());
        }
    }

    public void sendMailMessage(User user)
    {
        String randomCode = user.getPassword().substring(7, 17);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div><i>Dear ")
                .append(user.getTitle())
                .append(" ")
                .append(user.getFirstName())
                .append(", to complete the registration please confirm your E-mail</i>")
                .append("<a href='")
                .append(APP_HOST)
                .append("/confirm/")
                .append(user.getLogin())
                .append("/")
                .append(randomCode)
                .append("' style='color:blue;'>Please click here</a></div>");

        sendMail(MAIL_FROM,PASSWORD,user.getEmail(),stringBuffer.toString());

    }


}
