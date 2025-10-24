package com.gborkar.spring_security;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gborkar.spring_security.domain.AppUser;
import com.gborkar.spring_security.domain.Role;
import com.gborkar.spring_security.service.UserService;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 5);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		CommandLineRunner cmdLineRunner =  args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null, "John Doe", "john", "12345", new ArrayList<Role>()));
			userService.saveUser(new AppUser(null, "Mary Smith", "mary", "12345", new ArrayList<Role>()));
			userService.saveUser(new AppUser(null, "Jim Carry", "jim", "12345", new ArrayList<Role>()));
			userService.saveUser(new AppUser(null, "Will Smith", "will", "12345", new ArrayList<Role>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("mary", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("will", "ROLE_SUPER_ADMIN");
		};

		return cmdLineRunner;
	}

}
