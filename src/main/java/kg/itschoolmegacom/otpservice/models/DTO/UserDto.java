package kg.itschoolmegacom.otpservice.models.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String email;
    String code;
    Date createDate;
    Date otpCodeEndDate;
    Date endDate;
}
