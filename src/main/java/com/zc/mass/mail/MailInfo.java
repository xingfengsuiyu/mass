package com.zc.mass.mail;

import java.util.Properties;

public class MailInfo {
    //发送邮件服务器IP和端口
    private String mailServerHost;
    private String mailServerPort;
    private String username = "******";
    private String password = "******";
    private String sendMailAddr;
    private String reviceMailAddr;
    private String subject;
    private String content;
    private boolean validate = true;
    private String[] attachFileNames;

    public Properties getProperties() {
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", "smtp.allove.com");   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        return props;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSendMailAddr() {
        return sendMailAddr;
    }

    public void setSendMailAddr(String sendMailAddr) {
        this.sendMailAddr = sendMailAddr;
    }

    public String getReviceMailAddr() {
        return reviceMailAddr;
    }

    public void setReviceMailAddr(String reviceMailAddr) {
        this.reviceMailAddr = reviceMailAddr;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }
}
