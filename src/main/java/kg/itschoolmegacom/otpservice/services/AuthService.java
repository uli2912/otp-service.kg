package kg.itschoolmegacom.otpservice.services;

import kg.itschoolmegacom.otpservice.models.DTO.requests.AuthDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> generateOtp(AuthDto authDto);
}
