package com.ssafy.zipsee.house.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.zipsee.house.model.DongDto;
import com.ssafy.zipsee.house.model.HouseDealDto;
import com.ssafy.zipsee.house.model.HouseInfoDto;
import com.ssafy.zipsee.house.model.mapper.DongMapper;
import com.ssafy.zipsee.house.model.mapper.HouseDealMapper;
import com.ssafy.zipsee.house.model.mapper.HouseInfoMapper;
import com.ssafy.zipsee.user.model.UserDealDto;
import com.ssafy.zipsee.user.model.mapper.UserDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {
	@Autowired
	private HouseDealMapper houseDealMapper;
	@Autowired
	private UserDealMapper userHouseMapper;
	@Autowired
	private HouseInfoMapper houseInfoMapper;

	public HouseDealServiceImpl(HouseDealMapper houseDealMapper, UserDealMapper userHouseMapper,
			HouseInfoMapper houseInfoMapper) {
		super();
		this.houseDealMapper = houseDealMapper;
		this.userHouseMapper = userHouseMapper;
		this.houseInfoMapper = houseInfoMapper;
	}

	@Override
	public List<HouseDealDto> getHouseDealList(String dongCode) throws Exception {
		List<HouseDealDto> dealList = houseDealMapper.getHouseDealList(dongCode);
		for(HouseDealDto houseDeal : dealList) {
			HouseInfoDto houseInfo = houseInfoMapper.getHouseInfo(houseDeal.getHouseId());
			houseDeal.setHouseInfo(houseInfo);
		}
		
		return dealList;
	}

	@Override
	public HouseDealDto getHouseDeal(int dealId) throws Exception {
		HouseDealDto houseDeal = houseDealMapper.getHouseDeal(dealId);
		HouseInfoDto houseInfo = houseInfoMapper.getHouseInfo(houseDeal.getHouseId());
		houseDeal.setHouseInfo(houseInfo);
		
		return houseDeal;
	}

	@Override
	public List<HouseDealDto> getHouseDealListByFilter(Map<String, Object> map) throws Exception {
		List<HouseDealDto> result = new ArrayList<>();
		List<HouseDealDto> result2 = new ArrayList<>();
		
		System.out.println("map으로 들어온거: " + map);
		
		//맵
		String dongCode = (String) map.get("dongCode");
		List<String> houseTypeList = (List<String>) map.get("houseTypeList"); //주택유형 <String, List<String>
		List<String> dealTypeList = (List<String>) map.get("dealTypeList"); //거래유형 <String, List<String>
		Long minDealMoney =  Long.valueOf(String.valueOf(map.get("minDealMoney")));
		Long maxDealMoney =  Long.valueOf(String.valueOf(map.get("maxDealMoney")));
		int minArea = (int) map.get("minArea");
		int maxArea = (int) map.get("maxArea");
		
		//
		List<HouseDealDto> dealList = houseDealMapper.getHouseDealListByFilter(dongCode, minDealMoney, maxDealMoney, minArea, maxArea);
		for(HouseDealDto houseDeal : dealList) {
			for(String houseType : houseTypeList) {
				if(houseDeal.getHouseInfo().getHouseType().equals(houseType)) {
					HouseInfoDto houseInfo = houseInfoMapper.getHouseInfo(houseDeal.getHouseId());
					houseDeal.setHouseInfo(houseInfo);
					
					result.add(houseDeal);
				}
			}	
		}
		
		for(HouseDealDto r: result) {
			for(String dealType : dealTypeList) {
				if(r.getDealType().equals(dealType)) {
					HouseInfoDto houseInfo = houseInfoMapper.getHouseInfo(r.getHouseId());
					r.setHouseInfo(houseInfo);
					
					result2.add(r);
				}
			}
		}
		
		System.out.println("필터 검색 결과: " + result2);
		
		
		return result2;
	}

	@Override
	public List<HouseDealDto> getApartRecommandList(String dongCode) throws Exception {
		List<HouseDealDto> dealList = houseDealMapper.getApartRecommandList(dongCode);
		for(HouseDealDto houseDeal : dealList) {
			HouseInfoDto houseInfo = houseInfoMapper.getHouseInfo(houseDeal.getHouseId());
			houseDeal.setHouseInfo(houseInfo);
		}
		
		return dealList;
	}

	@Override
	public List<HouseDealDto> getOneRoomRecommandList(String dongCode) throws Exception {
		List<HouseDealDto> dealList = houseDealMapper.getOneRoomRecommandList(dongCode);
		for(HouseDealDto houseDeal : dealList) {
			HouseInfoDto houseInfo = houseInfoMapper.getHouseInfo(houseDeal.getHouseId());
			houseDeal.setHouseInfo(houseInfo);
		}
		
		return dealList;
	}

	@Override
	public List<HouseDealDto> getPopularDealList() throws Exception {
		List<HouseDealDto> dealList = houseDealMapper.getPopularDealList();
		for(HouseDealDto houseDeal : dealList) {
			HouseInfoDto houseInfo = houseInfoMapper.getHouseInfo(houseDeal.getHouseId());
			houseDeal.setHouseInfo(houseInfo);
		}
		
		return dealList;
	}

	@Override
	@Transactional
	public boolean likeHouse(UserDealDto userHouseDto) throws Exception {
		if(userHouseMapper.insertUserHouse(userHouseDto) == 1) {
			houseDealMapper.increaseLike(userHouseDto.getDealId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean unlikeHouse(String userId, int dealId) throws Exception {
		if(userHouseMapper.deleteUserDeal(userId, dealId) == 1) {
			houseDealMapper.decreaseLike(dealId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public HouseInfoDto getHouseInfo(int houseId) throws Exception {
		return houseInfoMapper.getHouseInfo(houseId);
	}

}
