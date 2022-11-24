package com.ssafy.zipsee.user.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.zipsee.user.model.UserDongDto;

@Mapper
public interface UserDongMapper {
	public int insertUserDong(UserDongDto userDongDto) throws SQLException;
	public List<UserDongDto> getUserDongList(String userId) throws SQLException;
	public int deleteUserDong(String userId) throws SQLException;
}
