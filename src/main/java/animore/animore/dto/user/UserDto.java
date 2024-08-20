package animore.animore.dto.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;
    private String userCode;
    private String email;
    private String password;
    private String type;
    private Long status;
    private String name;
    private String phoneNumber;
    private Long subwayId;
    private Long useCar;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
