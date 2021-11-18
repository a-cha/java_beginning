package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

@Component
public class UsersServiceImpl implements UsersService {
	@Autowired
	UsersRepository repository;

	@Override
	public String signUp(String email) {
		User user = new User();

		user.setEmail(email);
		user.setPassword("changed");

		repository.save(user);

		return user.getPassword();
	}
}
