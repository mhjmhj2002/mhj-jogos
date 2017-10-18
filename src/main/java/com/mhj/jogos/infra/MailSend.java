package com.mhj.jogos.infra;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class MailSend {

	private Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private MailSender sender;

	@Async
	public void send(SimpleMailMessage email) {

		System.out.println("antes send: " + new Date());
		LOG.info("antes send: " + new Date());
		sender.send(email);
		System.out.println("depois send: " + new Date());
		LOG.info("depois send: " + new Date());

	}

}
