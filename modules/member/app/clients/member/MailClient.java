package clients.member;

import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailClient {

    private Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Inject
    MailerClient mailerClient;

    public void sendEmail(String from, String subject, String bodyText, String to) {

        logger.info(String.format("mailerClient:%s", mailerClient));
        Email email = new Email().setSubject(subject).setFrom(from).addTo(to).setBodyText(bodyText);
        mailerClient.send(email);
    }
}
