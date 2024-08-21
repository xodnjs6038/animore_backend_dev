package animore.animore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import animore.animore.dto.user.request.CreateUserDto;
import animore.animore.dto.user.request.UpdateUserDto;
import animore.animore.mapper.UserMapper;
import animore.animore.model.User;
import animore.animore.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public User createUser(CreateUserDto createUserDto) {
		User user = userMapper.toEntity(createUserDto);
		user.setUserCode(createUserCode());
		return userRepository.save(user);
	}

	public User updateUser(Long id, UpdateUserDto updateUserDto) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User Not Found"));

		if (updateUserDto.getName() != null) {
			user.setName(updateUserDto.getName());
		}

		if (updateUserDto.getPhoneNumber() != null) {
			user.setPhoneNumber(updateUserDto.getPhoneNumber());
		}

		if (updateUserDto.getSubwayId() != null) {
			user.setSubwayId(updateUserDto.getSubwayId());
		}

		if (updateUserDto.getUseCar() != null) {
			user.setUseCar(updateUserDto.getUseCar());
		}

		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User Not Found"));
		userRepository.delete(user);
	}

	private String createUserCode() {
		String userCodePrefix = "Ani";
		String userCode;
		User existingUser;
		int randomNumber;

		do {
			randomNumber = (int)(Math.random() * 900) + 100;
			userCode = userCodePrefix + randomNumber;

			existingUser = userRepository.findByUserCode(userCode);

		} while (existingUser != null);

		return userCode;
	}
}
