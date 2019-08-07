package com.coderslab.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderslab.entity.Users;
import com.coderslab.model.enums.RecordStatus;
import com.coderslab.repository.UsersRepository;
import com.coderslab.service.UsersService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zubayer Ahamed
 *
 */
@Slf4j
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired private UsersRepository usersRepository;

	@Transactional
	@Override
	public Users save(Users obj) {
		return usersRepository.save(obj);
	}

	@Override
	public Users update(Users obj) {
		return save(obj);
	}

	@Override
	public List<Users> findAll() {
		return usersRepository.findAllByStatus(RecordStatus.L);
	}

	@Override
	public Users findById(Long id) {
		return usersRepository.findById(id).orElse(null);
	}

	@Override
	public Users archiveById(Long id) {
		Users users = findById(id);
		users.setStatus(RecordStatus.D);
		update(users);
		return null;
	}

	@Override
	public Boolean deleteById(Long id) {
		try {
			usersRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Users findByUsername(String username) {
		log.debug("Find user by username : {} and status 'L'", username);
		return usersRepository.findUsersByUsernameAndStatus(username, RecordStatus.L);
	}

}
