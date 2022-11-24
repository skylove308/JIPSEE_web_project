package com.ssafy.zipsee.user.model.service;

import java.sql.SQLException;

import com.ssafy.zipsee.user.model.UserDto;

public interface UserService {
	public boolean registerUser(UserDto userDto) throws Exception;
	
	
	public boolean deleteUser(String userId) throws Exception;
	public UserDto getUser(String userId) throws Exception;
	public boolean modifyUser(UserDto user) throws Exception;
	
	public UserDto login(UserDto userDto) throws SQLException;
	public void saveRefreshToken(String userId, String refreshToken) throws Exception;
	public Object getRefreshToken(String userId) throws Exception;
	public void deleRefreshToken(String userId) throws Exception;
	public UserDto getUserByToken(String token) throws Exception;
}
