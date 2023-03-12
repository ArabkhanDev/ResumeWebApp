package com.company;

import com.company.dao.impl.UserRepository;
import com.company.dao.impl.UserRepositoryCustom;
import com.company.dao.impl.UserRepositoryCustomImpl;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class ResumeDbAppJpaSpringApplication {


	public static void main(String[] args) {

		SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);
	}

	//DI - Dependency Injection
	//IoC - Inversion of Control, RMI
	@Autowired
	@Qualifier("userDao1")
	private UserRepositoryCustomImpl userDao;


	@Bean
	public CommandLineRunner run(){
		CommandLineRunner clr = new CommandLineRunner(){
			@Override
			public void run(String... args) throws Exception {
				List<User> list = userDao.getAll(null,null,null);
				User u = list.get(0);
				u.setName(u.getName().toLowerCase());
				userDao.updateUser(u);// when use this method uncomment @Transactional in UserRepositoryCustomImpl
			}
		};

		return clr;
	}


	//	@Autowired
//	private UserRepository userRepository;
//	private UserServiceInter userService;
//
//	@Autowired
//	private UserRepository repo;
//
//	@Bean
//	public CommandLineRunner run(){
//
//		CommandLineRunner clr = new CommandLineRunner(){
//
//			@Override
//			public void run(String... args) throws Exception {
//				List<User> u = repo.getAll(null,null,null);
//				System.out.println(u);
//
//				for (int i = 0;i < 10;i++){
//					userService.getAll(null,null,null);
//				}
//
//				userService.getById(6);
//			}
//		};
//
//		return clr;
//	}

}
