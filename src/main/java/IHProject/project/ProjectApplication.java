package IHProject.project;

import IHProject.project.Security.Role;
import IHProject.project.Security.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ProjectApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}





	//Failed to introduce roles for Security

/*	@Bean
	public CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role("USER"));
			userService.saveRole(new Role("ADMIN"));

			userService.saveUser(new User( "Jose","JoseRey","password", new ArrayList<>()));
			userService.saveUser(new User( "Sajo","SarjoRey","password", new ArrayList<>()));

			userService.addRoleToUser("Jose", "USER");
			userService.addRoleToUser("Sajo", "ADMIN");
			userService.addRoleToUser("Sajo", "USER");
		};
}*/

/*	@Override
	public void run(String... args) throws Exception {

		UserService userService = new UserService();

		userService.saveRole(new Role(null, "USER"));
		userService.saveRole(new Role(null, "ADMIN"));

		userService.saveUser(new User( null,"Jose","JoseRey","password", new ArrayList<>()));
		userService.saveUser(new User(null, "Sajo","SarjoRey","password", new ArrayList<>()));

		userService.addRoleToUser("Jose", "USER");
		userService.addRoleToUser("Sajo", "ADMIN");
		userService.addRoleToUser("Sajo", "USER");

	}*/
}

