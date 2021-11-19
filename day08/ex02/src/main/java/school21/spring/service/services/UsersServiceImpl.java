package school21.spring.service.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

@Component
public class UsersServiceImpl implements UsersService {
	@Autowired
	@Qualifier("UsersRepositoryJdbcImpl")
	private UsersRepository repository;

	@Override
	public String signUp(String email) {
		User user = new User();

		user.setEmail(email);
		user.setPassword(RandomStringUtils.random(10, true, true));

		repository.save(user);

		return user.getPassword();
	}
}
