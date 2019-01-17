package com.khaileid.Service;

import com.khaileid.DTO.UserDTO;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityTicket;
import com.khaileid.Entity.EntityUsers;
import com.khaileid.Repository.RepositoryEvent;
import com.khaileid.Repository.RepositoryTicket;
import com.khaileid.Repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
public class NotificationService {

	@Autowired
	private JavaMailSender emailsender;
	@Autowired
	private RepositoryTicket repositoryTicket;
	@Autowired
	private RepositoryEvent repositoryEvent;
	@Autowired
	private RepositoryUser repositoryUser;


	public void notificationAddAtender(@Valid UserDTO userDTO) throws MailException {
		// send email
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(userDTO.getEmail());
		mail.setSubject("Dear. " + userDTO.getUsername());
		mail.setText("Hello " + userDTO.getUsername() + ", Think you for register  !");

		emailsender.send(mail);
	}

	public void notificationUpdateEvent(Long eventid) throws MailException {
//		 send email
		SimpleMailMessage mail = new SimpleMailMessage();
		EntityEvent entityEvent = repositoryEvent.findById(eventid).get();

		List<EntityTicket> entityTickets= repositoryTicket.findByEidAndTicketcancelFalse(entityEvent);
		for (EntityTicket entityTicket:entityTickets){

			mail.setTo(entityTicket.getUid().getEmail());
			mail.setSubject("Dear " + entityTicket.getUid().getUsername());
			mail.setText("Hello " + entityTicket.getUid().getUsername() + "\n"
					+ " There are modification in " + entityEvent.getNameevent() + " Location has been change " + entityEvent.getEventstreet() +"\n"
			        + " Please Check website " + "\n" + "Think you.");

            emailsender.send(mail);

		}
	}


	public void notificationEventCancel(Long eventid) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		EntityEvent entityEvent = repositoryEvent.findById(eventid).get();

        List<EntityTicket> entityTickets= repositoryTicket.findByEidAndTicketcancelFalse(entityEvent);
        for (EntityTicket entityTicket:entityTickets){
			mail.setTo(entityTicket.getUid().getEmail());
			mail.setSubject("Dear. " + entityTicket.getUid().getUsername());
			mail.setText("Hello" + entityTicket.getUid().getUsername() + "\n"
					+ "There are modification in " + entityEvent.getNameevent() + " Event has been canceled !" + "\n"
                    + "We apologize to you, I wish you good luck in the next events." + "\n"
                    + "Think you for time" + entityTicket.getUid().getUsername());

            emailsender.send(mail);
		}
	}

	public void notificationBookTicket(Long eid ,Long uid)
	{
		SimpleMailMessage mail = new SimpleMailMessage();
		EntityEvent entityEvent = repositoryEvent.findById(eid).get();
		EntityUsers entityUsers = repositoryUser.findById(uid).get();
        EntityTicket entityTicket = repositoryTicket.findByUidAndEid(entityUsers,entityEvent);
		mail.setTo(entityUsers.getEmail());
		mail.setSubject("Dear. " + entityUsers.getUsername());
		mail.setText("Hello " + entityUsers.getUsername() + "\n"
				+ "Think you for booking, Your Ticket NO  : " + entityTicket.getTicketid() + "\n"
		+"We hope to Enjoy in." + entityEvent.getNameevent() +"\n"
		+"Think you");
		emailsender.send(mail);

	}

	public void notificationCancelTicket(Long tid) {
        SimpleMailMessage mail = new SimpleMailMessage();
        EntityTicket entityTicket= repositoryTicket.findById(tid).get();

        mail.setTo(entityTicket.getUid().getEmail());
        mail.setSubject("Dear. " + entityTicket.getUid().getUsername());
        mail.setText("Hello " + entityTicket.getUid().getUsername() + "\n"
                + "Concerning : " + entityTicket.getEid().getNameevent() + "\n"
                +"We sorry for cancel the book NU." + entityTicket.getTicketid() + "\n"
                +"Have A nice Day");
        emailsender.send(mail);

    }



}
