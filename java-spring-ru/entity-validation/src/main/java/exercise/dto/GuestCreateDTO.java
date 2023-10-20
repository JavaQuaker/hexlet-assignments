package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank
    private String name;
    @Email
    private String email;

    @Pattern(regexp = "^+.[0-9]{11,13}")
    @Size(min = 11, max = 13)
    private String phoneNumber;
    @Size(min = 4, max = 4)
    private String clubCard;
    @Future
    private Date cardValidUntil;


}
// END
