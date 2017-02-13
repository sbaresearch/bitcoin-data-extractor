package thesis.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import thesis.exception.ServiceException;
import thesis.service.SendMailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Value("${email.to}")
    private String to;

    @Value("${email.from}")
    private String from;

    @Value("${email.subject}")
    private String subject;

    @Value("${email.receiver}")
    private String receiver;

    @Value("${email.sender}")
    private String sender;

	public void sendMail(String message) throws ServiceException {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, sender));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(to, receiver));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);

        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
