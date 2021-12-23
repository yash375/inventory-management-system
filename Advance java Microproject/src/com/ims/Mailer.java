/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ims;

/**
 *
 * @author swarup
 */

import java.util.Properties; 
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;


public class Mailer {
    
    String name, qty;
  
    
    public void senMail(String recipent, String name, String qty) throws Exception{

            this.qty = qty;
            this.name = name;
            
            Properties prop = new Properties();
            
            prop.put("mail.smtp.auth", "true");
                        prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", "smtp.gmail.com");
                        prop.put("mail.smtp.port", "587");

                          String from = "nerdofprogramming@gmail.com";
              String pwd = "Exception@321";
              
              Session session = Session.getInstance(prop, new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new  PasswordAuthentication(from, pwd);
                        
                    }
              });

                
            
            Message msg = prepareMessage(session, from,recipent);
            Transport.send(msg);
    }
    
    public  Message prepareMessage(Session session, String from, String rec){
        
        try{
            Message mesg = new MimeMessage(session);
            mesg.setFrom(new InternetAddress(from));
            mesg.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
            mesg.setSubject("Requirement for " + name);
            String msg = "Requirement of " + qty + " of " + name;
            mesg.setText(msg);
        System.out.println(msg);
        return mesg;
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
        return null;
    }
    
}
