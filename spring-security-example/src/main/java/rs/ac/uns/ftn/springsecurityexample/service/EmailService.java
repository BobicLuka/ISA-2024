package rs.ac.uns.ftn.springsecurityexample.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.ComplaintResponse;
import rs.ac.uns.ftn.springsecurityexample.model.User;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Autowired
	private QrCodeService qrCodeService;

	public void sendActivationCode(User user) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Account activation");

		String activationURL = "http://localhost:8080/api/activateAccount/" + user.getActivationCode();

		mail.setText("Hello, " + user.getFirstName() + "! \n "
				+ "To acctivate your account, please click the following link:\n" + activationURL);

		javaMailSender.send(mail);
	}

	public void sendQrCode(User user, Appointment appointment) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			// Koristimo MimeMessageHelper kako bismo dodali prilog
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo("kalargic@gmail.com");
			helper.setFrom(env.getProperty("spring.mail.username"));
			helper.setSubject("QR code for your reservation");

			helper.setText("Hello, " + user.getFirstName() + "! \n " + "Thank you for your reservation. QR code:\n");

			// Generišemo QR kod kao niz bajtova
			byte[] qrCodeBytes = qrCodeService.generateQrCode(appointment.getQrCodeData());

			// Dodajemo QR kod kao prilog
			helper.addAttachment("qrcode.png", new ByteArrayResource(qrCodeBytes), "image/png");

		} catch (Exception e) {
			// Handle exception
			e.printStackTrace();
		}

		javaMailSender.send(mimeMessage);
	}

	
	public void sendComplaintResponse(ComplaintResponse complaintResponse) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(complaintResponse.getComplaint().getComplainant().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username")); // trebalo bi sa system_admin emaila
		mail.setSubject("Complaint response");

		mail.setText(complaintResponse.getResponse());

		javaMailSender.send(mail);
	}
}
