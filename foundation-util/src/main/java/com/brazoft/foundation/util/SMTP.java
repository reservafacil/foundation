package com.brazoft.foundation.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.brazoft.foundation.commons.Validator;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class SMTP extends Authenticator {

  private static SMTP instance = new SMTP();

  private SMTPProperties constants;

  /**
	 * 
	 */
  protected SMTP() {
    super();
  }

  /**
   * @return Returns SMTP instance
   */
  public static SMTP getInstance() {
    return SMTP.instance;
  }

  /**
   * @param constants
   */
  public void configure(SMTPProperties constants) {
    this.constants = constants;
  }

  /**
   * @param message
   * @throws IOException
   */
  public void send(MimeMessage message) throws IOException {
    Session session;
    Message mime;
    MimeMultipart part;
    MimeBodyPart body;
    Transport smtp;

    smtp = null;

    try {
      session = this.getSession();
      mime = new javax.mail.internet.MimeMessage(session);
      mime.setHeader("Content-Type", message.getContentType());
      mime.setHeader("Content-Transfer-Encoding", "quoted-printable");
      mime.setFrom(new InternetAddress(this.constants.getFrom()));
      mime.setSubject(message.getSubject());

      if (!Validator.isEmptyOrNull(message.getTo())) {
        mime.setRecipients(Message.RecipientType.TO, InternetAddress.parse(message.getTo()));
      }

      if (!Validator.isEmptyOrNull(message.getCc())) {
        mime.setRecipients(Message.RecipientType.CC, InternetAddress.parse(message.getCc()));
      }

      if (!Validator.isEmptyOrNull(message.getBcc())) {
        mime.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(message.getBcc()));
      }

      part = new MimeMultipart();

      body = new MimeBodyPart();
      body.setContent(message.getContent(), message.getContentType());
      part.addBodyPart(body);

      mime.setContent(part);

      smtp = session.getTransport("smtp");
      if (this.constants.isAuthentication()) {
        smtp.connect(this.constants.getUsername(), this.constants.getPassword());
      } else {
        smtp.connect();
      }
      smtp.sendMessage(mime, mime.getAllRecipients());
    } catch (RuntimeException e) {
      e.printStackTrace();
      throw new IOException(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      throw new IOException(e.getMessage());
    } finally {
      try {
        if (smtp != null) {
          smtp.close();
        }
      } catch (MessagingException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @return Returns Session
   */
  protected Session getSession() {
    Properties constants;

    constants = System.getProperties();
    constants.setProperty("mail.smtp.host", this.constants.getHost());
    constants.setProperty("mail.smtp.port", Converter.toString(this.constants.getPort()));
    constants.setProperty("mail.smtp.from", this.constants.getFrom());
    constants.setProperty("mail.mime.charset", "UTF-8");

    if (this.constants.isAuthentication()) {
      constants.setProperty("mail.smtp.auth", "true");

      return Session.getInstance(constants, this);
    }

    return Session.getInstance(constants);
  }

  @Override
  protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(this.constants.getUsername(), this.constants.getPassword());
  }
}