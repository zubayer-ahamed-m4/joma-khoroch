package com.coderslab.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coderslab.entity.Users;
import com.coderslab.model.enums.RecordStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.coderslab.*"})
@Rollback(false)
public class UsersServiceTests {

	@Autowired UsersService usersService;

	@Test
	public void test1SaveUser() {
		Users users = new Users();
		users.setFirstName("Zubayer");
		users.setLastName("Ahamed");
		users.setUsername("admin");
		users.setEmail("admin@gmail.com");
		users.setPassword("admin");
		users = usersService.save(users);
		assertNotNull(users);
	}

	@Test
	public void test2FindUser() {
		Users user = usersService.findByUsername("admin");
		log.info("User : {}", user);
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}

	@Test
	public void test3UpdateUser() {
		Users user = usersService.findByUsername("admin");
		assertNotNull(user);
		user.setPassword("1234");
		user = usersService.update(user);
		assertNotNull(user);
		assertEquals("1234", user.getPassword());
	}

	@Test
	public void test4ReadAll() {
		List<Users> users = usersService.findAll();
		assertNotNull(users);
		users.stream().forEach(u -> log.info("User : {}", u));
	}

	@Test
	public void test5Archive() {
		Users user = usersService.findByUsername("admin");
		assertNotNull(user);
		user = usersService.archiveById(user.getUserId());
		assertNotNull(user);
		assertEquals(user.getStatus(), RecordStatus.D);
	}

}
