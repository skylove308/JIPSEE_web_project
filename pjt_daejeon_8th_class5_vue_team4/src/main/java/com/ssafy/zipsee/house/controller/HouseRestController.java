package com.ssafy.zipsee.house.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.zipsee.house.model.HouseDealDto;
import com.ssafy.zipsee.house.model.service.HouseDealService;
import com.ssafy.zipsee.user.model.UserDto;
import com.ssafy.zipsee.user.model.UserDealDto;
import com.ssafy.zipsee.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/deal")
@Api("매물 정보 컨트롤러  API")
public class HouseRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private HouseDealService houseDealService;
	private UserService userService;

	@Autowired
	public HouseRestController(HouseDealService houseDealService, UserService userService) {
		super();
		this.houseDealService = houseDealService;
		this.userService = userService;
	}

	@ApiOperation(value = "매물 검색", notes = "동코드 조건에 맞는 매물을 검색해 반환한다", response = List.class)
	@GetMapping
	public ResponseEntity<?> list(@RequestParam String dongCode) {
		try {
			List<HouseDealDto> list = houseDealService.getHouseDealList(dongCode);
			if (list != null && !list.isEmpty()) {
				System.out.println(list);
				return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "매물 검색", notes = "필터 조건(동코드, 집유형, 거래타입, 최소가격, 최대가격, 최소면적, 최대면적)에 맞는 매물을 검색해 반환한다", response = List.class)
	@PostMapping("/filter")
	public ResponseEntity<?> listByFilter(@RequestBody Map<String, Object> map) {
		try {
			List<HouseDealDto> list = houseDealService.getHouseDealListByFilter(map);
			if (list != null && !list.isEmpty()) {
				System.out.println(list);
				return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@ApiOperation(value = "매물 보기", notes = "매물 id에 해당하는 매물을 반환한다", response = List.class)
	@GetMapping("/{dealid}")
	public ResponseEntity<?> viewHouseDeal(@PathVariable("dealid") int dealId) throws Exception {
		return new ResponseEntity<HouseDealDto>(houseDealService.getHouseDeal(dealId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "매물 찜하기", notes = "매물 id에 해당하는 매물을 찜한다", response = List.class)
	@PostMapping("/like")
	public ResponseEntity<?> likeHouse(@RequestBody @ApiParam(value = "찜하는 매물과 유저 정보", required = true) UserDealDto userDealDto) throws Exception {
		System.out.println(userDealDto);

		int dealId = userDealDto.getDealId();
		
		System.out.println(dealId);
		
		if (houseDealService.likeHouse(userDealDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "매물 찜하기 해제", notes = "매물 id에 해당하는 매물을 찜하기를 해제한다", response = List.class)
	@DeleteMapping("/unlike/{dealid}")
	public ResponseEntity<?> unlikeHouse(@PathVariable("dealid") int dealId, HttpServletRequest request) throws Exception {
		String token = request.getHeader("refresh-token");
		UserDto userDto = userService.getUserByToken(token);
		
		System.out.println(dealId);
		
		if (houseDealService.unlikeHouse(userDto.getUserId(), dealId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
