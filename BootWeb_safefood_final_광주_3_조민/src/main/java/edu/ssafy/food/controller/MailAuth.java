package edu.ssafy.food.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class MailAuth extends Authenticator{
   PasswordAuthentication pa;
   public MailAuth() {
       String mail_id = "zkdlwjfl123@gmail.com";
       String mail_pw = "dla13579";
       pa = new PasswordAuthentication(mail_id, mail_pw);
   }
   public PasswordAuthentication getPasswordAuthentication() {
       return pa;
   }
}

