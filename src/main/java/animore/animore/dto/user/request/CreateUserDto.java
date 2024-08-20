package animore.animore.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class CreateUserDto {
    @NotEmpty
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    @NumberFormat
    private String phoneNumber;
    @NumberFormat
    private Long subwayId;
    @NumberFormat
    private Long useCar;
}
