package com.zc.mass;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class SimpleMailSender {

    public boolean sendTextMail(MailInfo mailInfo) {
        Session session = Session.getInstance(mailInfo.getProperties());
        session.setDebug(true);
        MimeMessage mimeMessage = createMimeMessage(session,mailInfo);
        Transport transport = null;
        try {
            transport =   session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            transport.connect(mailInfo.getUsername(),mailInfo.getPassword());
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }

    private MimeMessage createMimeMessage(Session session, MailInfo mailInfo) {
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(mailInfo.getSendMailAddr()));
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(mailInfo.getReviceMailAddr()));
            mimeMessage.setSubject(mailInfo.getSubject());
            mimeMessage.setContent(mailInfo.getContent(),"text/html;charset=UTF-8");
            mimeMessage.setSentDate(new Date());
            mimeMessage.saveChanges();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return mimeMessage;
    }
}
