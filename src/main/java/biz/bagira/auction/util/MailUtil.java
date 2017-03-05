package biz.bagira.auction.util;

import biz.bagira.auction.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Dmitriy on 27.02.2017.
 */
public class MailUtil {

    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);


    private Properties props;

    public MailUtil(Properties props) {
        this.props = props;
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

         logger.info("Props : "+props.getProperty("app.host"));
         logger.info("Props : "+props.getProperty("mail.from"));
         logger.info("Props : "+props.getProperty("mail.password"));
        String randomCode = user.getPassword().substring(7, 17).replace(".","").replace("/","");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div><i>Dear ")
                .append(user.getTitle())
                .append(" ")
                .append(user.getFirstName())
                .append(", to complete the registration please confirm your E-mail</i>")
                .append("<a href='")
                .append(props.getProperty("app.host"))
                .append("/confirm/")
                .append(user.getLogin())
                .append("/")
                .append(randomCode)
                .append("' style='color:blue;'>Please click here</a></div>");
        logger.info(stringBuffer.toString());
        sendMail(props.getProperty("mail.from"),props.getProperty("mail.password"),user.getEmail(),stringBuffer.toString());

    }


}
