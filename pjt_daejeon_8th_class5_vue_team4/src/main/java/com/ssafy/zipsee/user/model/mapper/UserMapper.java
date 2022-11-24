package com.ssafy.zipsee.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.zipsee.user.model.UserDto;

@Mapper
public interface UserMapper {
	public int registerUser(UserDto userDto) throws SQLException;
	public void registerUserInterest(UserDto userDto) throws SQLException; //
	public void registerUserDong(UserDto userDto) throws SQLException; //
	
	public int deleteUser(String userId) throws SQLException;
	public UserDto getUser(String userId) throws SQLException;
	
	public int modifyUser(UserDto userDto) throws SQLException;
	
	public UserDto login(UserDto userDto) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userId) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	public UserDto getUserByToken(String token) throws SQLException;
}
