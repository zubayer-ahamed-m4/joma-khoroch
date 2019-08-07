package com.coderslab.service;

import org.springframework.stereotype.Component;

import com.coderslab.entity.Users;

/**
 * @author Zubayer Ahamed
 *
 */
@Component
public interface UsersService extends GenericService<Users, Long> {

	/**
	 * FIND BY USERNAME
	 * 
	 * @param username
	 * @return {@link Users}
	 */
	public Users findByUsername(String username);
}
