/**
 * 
 */
package com.coderslab.service;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Zubayer Ahamed 
 *
 */
@Component
public interface GenericService<T, ID> {

	/**
	 * SAVE OBJECT
	 * 
	 * @param obj
	 * @return SAVED OBJECT
	 */
	public T save(T obj);

	/**
	 * UPDATE OBJECT
	 * 
	 * @param obj
	 * @return UPDATED OBJECT
	 */
	public T update(T obj);

	/**
	 * FIND ALL OBJECTS
	 * 
	 * @return {@link List} OF OBJECT
	 */
	public List<T> findAll();

	/**
	 * FIND OBJECT BY ID
	 * 
	 * @param id
	 * @return OBJECT
	 */
	public T findById(ID id);

	/**
	 * UPDATE OBJECT STATUS ('D', 'L')
	 * 
	 * @param id
	 * @return UPDAED OBJECT
	 */
	public T archiveById(ID id);

	/**
	 * DELETE OBJECT PERMANENTLY FROM DATABASE
	 * 
	 * @param id
	 * @return {@link Boolean}
	 */
	public Boolean deleteById(ID id);
}
