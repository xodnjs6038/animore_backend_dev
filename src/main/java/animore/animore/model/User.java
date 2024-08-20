package animore.animore.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@Builder.Default
	private String type = "V";
	@Builder.Default
	private Long status = 1L;
	private String name;
	private String phoneNumber;
	private Long subwayId;
	@Builder.Default
	private Long useCar = 0L;
	private LocalDateTime createdDate = LocalDateTime.now();
	private LocalDateTime updatedDate;
}
