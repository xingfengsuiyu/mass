package com.zc.mass;

public class MailTests {
    public static void main(String args[]) {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setUsername("erp02@allove.com");
        mailInfo.setPassword("Zc8737875");
        mailInfo.setSubject("测试");
        mailInfo.setContent("测试吴雕是傻逼成功");
        mailInfo.setReviceMailAddr("erp01@allove.com");
        mailInfo.setSendMailAddr("erp02@allove.com");

        SimpleMailSender simpleMailSender = new SimpleMailSender();
        simpleMailSender.sendTextMail(mailInfo);
    }
}
