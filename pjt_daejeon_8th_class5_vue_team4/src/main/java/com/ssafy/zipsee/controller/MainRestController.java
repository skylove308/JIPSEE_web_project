package com.ssafy.zipsee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.zipsee.house.model.HouseDealDto;
import com.ssafy.zipsee.house.model.service.HouseDealService;
import com.ssafy.zipsee.user.model.UserDongDto;
import com.ssafy.zipsee.user.model.UserDto;
import com.ssafy.zipsee.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/")
@Api("메인  컨트롤러")
public class MainRestController {
	private UserService userService;
	private HouseDealService houseDealService;

	@Autowired
	public MainRestController(UserService userService, HouseDealService houseDealService) {
		super();
		this.userService = userService;
		this.houseDealService = houseDealService;
	}


	@ApiOperation(value = "유저 매물 추천", notes = "유저의 관심사와 관심지역에 맞는 매물을 추천해준다.", response = List.class)
	@GetMapping
	public ResponseEntity<?> recommandlist(HttpServletRequest request) throws Exception {
		String token = request.getHeader("refresh-token");
		
		Map<String, Object> resultMap = new HashMap<>(); //응답으로 보낼 데이터 맵
		
		UserDto userDto = new UserDto();
		List<HouseDealDto> popularDealList = houseDealService.getPopularDealList(); //실시간 top 10
		List<List<HouseDealDto>> apartListByDong = new ArrayList<>(); //아파트 추천 리스트 여러개
		List<List<HouseDealDto>> oneRoomListByDong = new ArrayList<>(); //원룸 추천 리스트 여러개
		
		if(token == null) { //로그인 안했을 경우 - 공통 추천
			List<HouseDealDto> apartList = houseDealService.getApartRecommandList("1168010100"); //역삼동 아파트
			if (apartList != null && !apartList.isEmpty()) {
				apartListByDong.add(apartList);
			}
			
			List<HouseDealDto> oneRoomlist = houseDealService.getOneRoomRecommandList("1168010100"); //역삼동 원룸
			if (oneRoomlist != null && !oneRoomlist.isEmpty()) {
				oneRoomListByDong.add(oneRoomlist);
			}
			
		} else { //로그인 했을 경우 - 유저 맞춤 매물 추천해주기
			userDto = userService.getUserByToken(token);
			
			List<UserDongDto> dongList = userDto.getDongList();
			for(UserDongDto userDong : dongList) {
				String dongCode = userDong.getDongCode();
				
				List<HouseDealDto> apartList = houseDealService.getApartRecommandList(dongCode);
				if (apartList != null && !apartList.isEmpty()) {
					apartListByDong.add(apartList);
				}
				
				List<HouseDealDto> oneRoomlist = houseDealService.getOneRoomRecommandList(dongCode);
				if (oneRoomlist != null && !oneRoomlist.isEmpty()) {
					oneRoomListByDong.add(oneRoomlist);
				}
			}
		}
		
		resultMap.put("user", userDto);
		resultMap.put("popularDealList", popularDealList);
		resultMap.put("popularDealList", popularDealList);
		resultMap.put("apartListBydong", apartListByDong);
		resultMap.put("oneRoomListByDong", oneRoomListByDong);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
