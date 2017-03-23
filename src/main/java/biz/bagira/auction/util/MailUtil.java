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
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
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

    @Async
    public void sendMail(String mailTo, String message, String subject) {

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
            msg.setSubject(subject,"UTF-8");
            msg.setText(message, "UTF-8","html");
            Enumeration allHeaders = msg.getAllHeaders();
           while (allHeaders.hasMoreElements()){
               logger.info("header: "+allHeaders.nextElement().toString());
           }
            Transport.send(msg);
            logger.info("Message sent to: "+mailTo);

        } catch (MessagingException  e) {
            logger.info("Message send fail: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Async
    public void sendConfirmMessage(User user)
    {

        String randomCode = user.getPassword().substring(7, 17).replace(".","").replace("/","");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div><i>Dear ")
                .append(user.getTitle().getTitle())
                .append(" ")
                .append(user.getFirstName())
                .append(", to complete the registration please confirm your E-mail</i>")
                .append("<a href='")
                .append(props.getProperty("app.host"))
                .append("/confirm/")
                .append(user.getLogin())
                .append("/")
                .append(randomCode)
                .append(" 'style='color:blue;'>Please click here</a></div>");
        logger.info(stringBuffer.toString());
        sendMail(user.getEmail(), stringBuffer.toString(),"Confirm your E-mail");

    }
    @Async
    public void sendMessageWithAttach(String mailTo, String message, String userTitle, String userName, String subject, MultipartFile file) {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(props.getProperty("mail.from"), props.getProperty("mail.password"));
                    }
                });

            message = "Dear "+userTitle+" "+userName+"! "+message;

        try {
            message = new String(message.getBytes("ISO-8859-1"),"UTF-8");
            subject = new String(subject.getBytes("ISO-8859-1"),"UTF-8");
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailTo));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            msg.setHeader("Content-Type", "text/plain;charset=utf-8");
            msg.setSubject(subject,"UTF-8");
            msg.setSentDate(new Date());
            Multipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message,"text/plain; charset=\"UTF-8\"");

            multipart.addBodyPart(messageBodyPart);

            if(!file.isEmpty()) {
                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.setHeader("Content-Type", "text/plain;charset=utf-8");
                DataSource source = new FileDataSource(convert(file));

                attachPart.setDataHandler(new DataHandler(source));
                attachPart.setFileName(new String(file.getOriginalFilename().getBytes("windows-1251"),"UTF-8"));

                multipart.addBodyPart(attachPart);
            }

            msg.setContent(multipart,"text/plain; charset=\"UTF-8\"");

            logger.info("Sending message to "+mailTo);

            Transport.send(msg);
            logger.info("Done");

        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.info(e.getMessage());
        }
    }

    private File convert(MultipartFile file) throws UnsupportedEncodingException {
        File convFile = new File(new String(file.getOriginalFilename().getBytes("windows-1251"),"UTF-8"));
        logger.info("FILE NAME "+convFile.getName());
        try {

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
        String messageToBidder = "Congratulations, your bet "+winner.getBid() +" wins on auction item = "+item.getName();
        String messageToOwner = "Congratulations, your bidding is over, winner: "+userBidder.getLogin()+" bid : "+winner.getBid();
        sendMail(ownerEmail,messageToOwner,"Auction ended");
        sendMail(winnerEmail,messageToBidder,"You win on auction");
        return userBidder;
    }
}
