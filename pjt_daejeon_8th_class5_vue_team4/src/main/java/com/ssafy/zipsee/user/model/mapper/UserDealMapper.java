package com.ssafy.zipsee.user.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.zipsee.user.model.UserDealDto;

@Mapper
public interface UserDealMapper {
	public int insertUserHouse(UserDealDto userHouseDto) throws SQLException;
	public List<UserDealDto> getUserDealList(String userId) throws SQLException;
	public int deleteUserDeal(String userId, int dealId) throws SQLException;
	public int deleteUserDealByUserId(String userId) throws SQLException;
}
