package animore.animore.dto.user.request;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;

@Data
public class UpdateUserDto {
	private String name;
	@NumberFormat
	private String phoneNumber;
	@NumberFormat
	private Long subwayId;
	@NumberFormat
	private Long useCar;
}
