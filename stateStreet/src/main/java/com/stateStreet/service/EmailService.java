package com.stateStreet.service;

import java.util.concurrent.CompletableFuture;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.stateStreet.config.EmailConfig;


@Service
public class EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private EmailConfig emailConfig;

	@Autowired
	JavaMailSender javaMailSender;

	/**
	 * send email with all the details
	 * 
	 * @param payrollProcessLogData
	 */
	public void sendNotificationEmail() {
		LOGGER.debug("Started sending email");
		try {

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(emailConfig.getFromAddress());
			helper.setTo(emailConfig.getToAddress());
			helper.setSubject(emailConfig.getSubject());
			helper.setText(emailConfig.getMessage(), true);

			if (StringUtils.isNotEmpty(emailConfig.getCc()))
				helper.setCc(emailConfig.getCc());

			if (StringUtils.isNotEmpty(emailConfig.getBcc()))
				helper.setBcc(emailConfig.getBcc());

			CompletableFuture.runAsync(() -> javaMailSender.send(message));

		} catch (Exception exception) {
			LOGGER.error("Error sending email notification {}", exception);
		}
	}
}
