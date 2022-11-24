package com.ssafy.zipsee.user.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.zipsee.user.model.UserInterestDto;

@Mapper
public interface UserInterestMapper {
	public int insertUserInterest(UserInterestDto userInterestDto) throws SQLException;
	public List<UserInterestDto> getUserInterestList(String userId) throws SQLException;
	public void deleteUserInterest(String userId) throws SQLException;
}
