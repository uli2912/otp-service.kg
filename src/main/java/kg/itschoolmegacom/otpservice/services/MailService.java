package kg.itschoolmegacom.otpservice.services;

public interface MailService {
    void sendEmail(String recipient, String text, String subject);

}
