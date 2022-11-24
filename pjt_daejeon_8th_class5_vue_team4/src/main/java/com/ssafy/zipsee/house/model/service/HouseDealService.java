package com.ssafy.zipsee.house.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.zipsee.house.model.HouseDealDto;
import com.ssafy.zipsee.house.model.HouseInfoDto;
import com.ssafy.zipsee.user.model.UserDto;
import com.ssafy.zipsee.user.model.UserDealDto;

public interface HouseDealService {
	List<HouseDealDto> getHouseDealList(String dongCode) throws Exception;
	HouseDealDto getHouseDeal(int dealId) throws Exception;
	List<HouseDealDto> getHouseDealListByFilter(Map<String, Object> map) throws Exception;
	List<HouseDealDto> getApartRecommandList(String dongCode) throws Exception;
	List<HouseDealDto> getOneRoomRecommandList(String dongCode) throws Exception;
	List<HouseDealDto> getPopularDealList() throws Exception;
	
	boolean likeHouse(UserDealDto userHouseDto) throws Exception;
	boolean unlikeHouse(String userId, int dealId) throws Exception;
	
	public HouseInfoDto getHouseInfo(int houseId) throws Exception;
}
