package com.zc.mass.mail;

public class MailTests {
    public static void main(String args[]) {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setUsername("");
        mailInfo.setPassword("");
        mailInfo.setSubject("测试");
        mailInfo.setContent("测试成功");
        mailInfo.setReviceMailAddr("");
        mailInfo.setSendMailAddr("");

        SimpleMailSender simpleMailSender = new SimpleMailSender(mailInfo);
        simpleMailSender.sendTextMail(mailInfo);
    }
}
