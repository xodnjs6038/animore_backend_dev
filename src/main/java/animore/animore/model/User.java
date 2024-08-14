package animore.animore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ani_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
