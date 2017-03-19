package biz.bagira.auction.util;

import biz.bagira.auction.entities.Bid;
import biz.bagira.auction.entities.Item;
import biz.bagira.auction.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.TreeSet;

/**
 * Created by Dmitriy on 27.02.2017.
 */
public class MailUtil {

    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);


    private Properties props;

    public MailUtil(Properties props) {
        this.props = props;
    }


    public void sendMail(String mailTo, String message) {

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(props.getProperty("mail.from"), props.getProperty("mail.password"));
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

    public void sendConfirmMessage(User user)
    {

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
        sendMail(user.getEmail(), stringBuffer.toString());

    }
    @Async
    public void sendMessageWithAttach(String mailTo, String message,String userTitle, String userName, String subject, MultipartFile file) {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(props.getProperty("mail.from"), props.getProperty("mail.password"));
                    }
                });
             message = "Dear "+userTitle+" "+userName+"! "+message;
        try {

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailTo));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");
            multipart.addBodyPart(messageBodyPart);

            if(!file.isEmpty()) {
                MimeBodyPart attachPart = new MimeBodyPart();
                DataSource source = new FileDataSource(convert(file));

                attachPart.setDataHandler(new DataHandler(source));
                attachPart.setFileName(file.getOriginalFilename());
                multipart.addBodyPart(attachPart);
            }

            msg.setContent(multipart);
            logger.info("Sending message to "+mailTo);

            Transport.send(msg);
            logger.info("Done");

        } catch (MessagingException e) {
            logger.info(e.getMessage());
        }
    }

    private File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }

        return convFile;
    }

    public  User sendMessagesWhenItemsDateOff(Item item){

        User owner = item.getOwner();
        String ownerEmail = owner.getEmail();
        TreeSet<Bid> bidSet = new TreeSet<Bid>(item.getBidSet());
        Bid winner = bidSet.last();
        User userBidder = winner.getUserBidder();
        String winnerEmail = userBidder.getEmail();

        logger.info("Biggest Bid = "+bidSet.last());
        String messageToBidder = "Congratulations, your bet "+winner.getBid() +" wins on item = "+item.getName();
        String messageToOwner = "Congratulations, your bidding is over, winner: "+userBidder.getLogin()+" bid : "+winner.getBid();
        sendMail(ownerEmail,messageToOwner);
        sendMail(winnerEmail,messageToBidder);
        return userBidder;
    }
}
