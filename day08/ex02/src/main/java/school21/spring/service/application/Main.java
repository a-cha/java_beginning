package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		UsersRepository repository;

		HikariDataSource ds = context.getBean("hikariDS", HikariDataSource.class);
//		repository = context.getBean("usersRepositoryJdbc_Hikari", UsersRepositoryJdbcImpl.class);
//		repository = context.getBean("usersRepositoryJdbc_DriverManager", UsersRepositoryJdbcImpl.class);
//		repository = context.getBean("usersRepositoryJdbcTemplate_Hikari", UsersRepositoryJdbcTemplateImpl.class);
		repository = context.getBean("usersRepositoryJdbcTemplate_DriverManager", UsersRepositoryJdbcTemplateImpl.class);

//		test
		User u = null;
		long id = 17L;

		System.err.println("findById");
		try {
			u = repository.findById(id);
			System.out.println(u);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		assert u != null;
		u.setEmail("new email");
		System.err.println("update");
		repository.update(u);
		System.err.println("findById");
		try {
			u = repository.findById(id);
			System.out.println(u);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.println("delete");
		repository.delete(id);
		System.err.println("findById");
		try {
			System.out.println(repository.findById(id));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.err.println("findByEmail");
		u = repository.findByEmail("pen").orElse(null);
		if (u != null) {
			System.out.println(u);
		}
		System.out.println("save");
		u = new User();
		u.setEmail("email");
		repository.save(u);
		System.err.println("findAll");
		System.out.println(repository.findAll());
	}

}
