package com.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderslab.entity.Users;
import com.coderslab.model.enums.RecordStatus;

/**
 * @author Zubayer Ahamed
 *
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	public List<Users> findAllByStatus(RecordStatus status);
	public Users findUsersByUsernameAndStatus(String username, RecordStatus status);
}
