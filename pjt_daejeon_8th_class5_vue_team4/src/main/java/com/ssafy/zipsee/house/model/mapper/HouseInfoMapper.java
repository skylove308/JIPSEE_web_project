package com.ssafy.zipsee.house.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.zipsee.house.model.HouseInfoDto;

@Mapper
public interface HouseInfoMapper {
	public HouseInfoDto getHouseInfo(int houseId) throws SQLException;;
}
