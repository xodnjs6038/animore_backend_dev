package animore.animore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import animore.animore.dto.user.request.CreateUserDto;
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
		return userRepository.save(user);
	}

	public User updateUser(Long id, User userDetails) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User Not Found"));

		user.setName(userDetails.getName());
		user.setPhoneNumber(userDetails.getPhoneNumber());
		user.setSubwayId(userDetails.getSubwayId());
		user.setUseCar(userDetails.getUseCar());

		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User Not Found"));
		userRepository.delete(user);
	}
}
