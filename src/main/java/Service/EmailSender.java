package Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static String fromEmail;
    private static String password;
    private static SecureCodeHandler handler = SecureCodeHandler.getInstance();

    public static void sendValidationEmail(String toEmail, String username) throws Exception {
        getProperties();
        Session session = getEmailSession();
        String code = handler.generateCode(username);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Authenticate your account");
            message.setText("To authenticate your account, please input following one time code: "
                    + code
                    + "\n\nOBS! Code is valid for 10 minutes."
                    + "\n\nHappy debating\n-DevDebate Team"
                    + "\n\n\nDo not respond to this email!");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new Exception("Could not send authentication email. Go to your user page and ");
            //TODO log
        }
    }

    private static Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.mail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        return session;
    }

    private static void getProperties() {
        InputStream in = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties");
        Properties props = new Properties();
        try {
            props.load(in);
            fromEmail = props.getProperty("email");
            password = props.getProperty("password");
        } catch (IOException e) {
            //TODO log
        }
    }
}

