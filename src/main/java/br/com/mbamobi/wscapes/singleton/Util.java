package br.com.mbamobi.wscapes.singleton;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Util {
	
    private static final Util instance = new Util();
    
    private Util(){}
    
    public static Util getInstance(){
        return instance;
    }

	public void sendEmail(final String sendTo, final String senhaTemp ) {
		final String username = "evee.mosquito@gmail.com";
		final String password = "m0squit0";

		// setting gmail smtp properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// check the authentication
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			InternetAddress addr = new InternetAddress(username);
			addr.setPersonal("App E-MOSQUITO", "UTF-8");
			message.setFrom(addr);

			// recipients email address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));

			// add the Subject of email
			message.setSubject("[e-vee] - Recuperação de senha");

			// message body
			message.setText("Sua nova senha do e-vee é: " + senhaTemp);// message

			Transport.send(message);

		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);

		}
	}

	public <T> T mergeObjects( T p1, T p2 ) {
		List<Field> fields = new ArrayList<>();
        @SuppressWarnings("rawtypes")
		Class clazz = p1.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
		Field[] allFields = fields.toArray(new Field[fields.size()]);;
	    for (Field field : allFields) {

	        if (!field.isAccessible() && Modifier.isPrivate(field.getModifiers())) 
	            field.setAccessible(true);
	        try {
				if ( !Modifier.isFinal(field.getModifiers()) && !"serialVersionUID".equals(field.getName()) &&  field.get(p2) != null ) {
				    field.set(p1, field.get(p2));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    }
	    return p1;
	}

	
}
