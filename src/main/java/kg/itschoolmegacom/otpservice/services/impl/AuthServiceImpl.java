package kg.itschoolmegacom.otpservice.services.impl;

import kg.itschoolmegacom.otpservice.exceptions.UserNotFoundException;
import kg.itschoolmegacom.otpservice.models.DTO.requests.AuthDto;
import kg.itschoolmegacom.otpservice.models.entities.User;
import kg.itschoolmegacom.otpservice.repositories.UserRepo;
import kg.itschoolmegacom.otpservice.services.AuthService;
import kg.itschoolmegacom.otpservice.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepo userRepo;
    private MailService mailService;

    private final long OTP_TTL = 60_000L;

    @Autowired
    public AuthServiceImpl(UserRepo userRepo, MailService mailService) {
        this.userRepo = userRepo;
        this.mailService = mailService;
    }

    @Override
    public ResponseEntity<?> generateOtp(AuthDto authDto) {

        User user = userRepo.findByEmail(authDto.getEmail());

        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User not found " + authDto.getEmail());
        }
        Random random = new Random();
        int otp = random.nextInt(9999-1000) + 1000;

        user.setCode(String.valueOf(otp));
        user.setOtpCodeEndDate(new Date(System.currentTimeMillis()+OTP_TTL));

        userRepo.save(user);

        mailService.sendEmail(user.getEmail(),"Your code is " + otp, "OTP Code");
        return ResponseEntity.ok("Your code has been send");
    }
}
