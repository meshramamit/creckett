package com.creckett.dao;

import java.util.List;

import com.creckett.model.User;

public interface UserDAO {

	public User save(User user);

	public User get(Long id);

	public void delete(User user);
	
	public User getUserByEmailId(String emailId);

	public int restMatchPlayingFlag();
	
	public List<User> getUsersByProfileIds(List<String> profileIds);

	public int restMatchPlayingFlag(List<User> users);
}
