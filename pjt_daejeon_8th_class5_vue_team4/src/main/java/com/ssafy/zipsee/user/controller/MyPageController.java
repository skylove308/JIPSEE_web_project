package com.ssafy.zipsee.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.zipsee.board.controller.BoardRestController;
import com.ssafy.zipsee.board.model.BoardDto;
import com.ssafy.zipsee.board.model.service.BoardService;
import com.ssafy.zipsee.user.model.UserDealDto;
import com.ssafy.zipsee.user.model.UserDto;
import com.ssafy.zipsee.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, maxAge = 6000)
@RestController
@RequestMapping("/mypage")
@Api("마이페이지 컨트롤러  API")
public class MyPageController {
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private UserService userService;
	private BoardService boardService;

	@Autowired
	public MyPageController(UserService userService, BoardService boardService) {
		super();
		this.userService = userService;
		this.boardService = boardService;
	}
	
	@ApiOperation(value = "수정할 회원 정보 보기", notes = "현재 로그인되어 있는 회원의 정보를 반환한다.", response = UserDto.class)
	@GetMapping("/modify")
	public ResponseEntity<?> viewUserInfo(HttpServletRequest request) throws Exception {
		try {
			String token = request.getHeader("refresh-token");
			
			UserDto user = userService.getUserByToken(token);
			
			if (user != null) {
				return new ResponseEntity<UserDto>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "회원 수정", notes = "수정할 회원의 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/modify")
	public ResponseEntity<?> modifyUser(@RequestBody UserDto userDto) throws Exception {
		logger.info("modifyUser - 호출 {}", userDto);
		
		if (userService.modifyUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 삭제", notes = "회원 아이디에 해당하는 회원의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/delete")
	public ResponseEntity<?> userDelete(HttpServletRequest request) throws Exception {
		logger.info("deleteArticle - 호출");
		
		String token = request.getHeader("refresh-token");
		
		UserDto user = userService.getUserByToken(token);
		
		if (userService.deleteUser(user.getUserId())) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 삭제", notes = "회원 아이디에 해당하는 회원의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<?> userDelete2(@PathVariable("userid") @ApiParam(value = "회원의 아이디.", required = true) String userId) throws Exception {
		logger.info("deleteArticle - 호출");
		
		UserDto userDto = userService.getUser(userId);
		
		if (userService.deleteUser(userDto.getUserId())) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "찜한 매물 목록 보기", notes = "회원이 찜한 매물 목록을 반환한다", response = UserDealDto.class)
	@GetMapping("/likehouse")
	public ResponseEntity<?> getLikeHouseList(HttpServletRequest request) throws Exception {
		try {
			String token = request.getHeader("refresh-token");
			
			UserDto userDto = userService.getUserByToken(token);
			List<UserDealDto> likeList = userDto.getLikeList();
			
			if (likeList != null && !likeList.isEmpty()) {
				System.out.println(likeList);
				return new ResponseEntity<List<UserDealDto>>(likeList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "찜한 매물 목록 보기", notes = "회원이 찜한 매물 목록을 반환한다", response = UserDealDto.class)
	@GetMapping("/likehouse/{userid}")
	public ResponseEntity<?> getLikeHouseList2(@PathVariable("userid") @ApiParam(value = "회원의 아이디.", required = true) String userId) throws Exception {
		try {
			UserDto userDto = userService.getUser(userId);
			List<UserDealDto> likeList = userDto.getLikeList();
			
			if (likeList != null && !likeList.isEmpty()) {
				System.out.println(likeList);
				return new ResponseEntity<List<UserDealDto>>(likeList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "문의 내역 목록 보기", notes = "자신이 문의한 목록(일반 유저)이나 답변할 문의 목록(관리자)을 반환한다", response = BoardDto.class)
	@GetMapping("/ask")
	public ResponseEntity<?> getAskList(HttpServletRequest request) throws Exception {
		try {
			logger.info("getAskList - 호출 {}");
			
			String token = request.getHeader("refresh-token");
			
			UserDto userDto = userService.getUserByToken(token);
			String userId = userDto.getUserId();
			
			List<BoardDto> list = new ArrayList<>();
			
			if(userId.equals("admin")) { //관리자일 경우
				System.out.println("getInquiryList() 실행");
				list = boardService.getInquiryList(); //질문 전체 리스트
			} else { //일반 유저일 경우
				System.out.println("getInquiryListByUserId(userId) 실행");
				list = boardService.getInquiryListByUserId(userId); //자신이 문의한 질문 리스트
			}
			
			if (list != null && !list.isEmpty()) {
				System.out.println(list);
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "문의 내역 목록 보기", notes = "자신이 문의한 목록(일반 유저)이나 답변할 문의 목록(관리자)을 반환한다", response = BoardDto.class)
	@GetMapping("/ask/{userid}")
	public ResponseEntity<?> getAskList2(@PathVariable("userid") @ApiParam(value = "회원의 아이디.", required = true) String userId) throws Exception {
		try {
			List<BoardDto> list = new ArrayList<>();
			
			if(userId.equals("admin")) { //관리자일 경우
				list = boardService.getInquiryList(); //질문 전체 리스트
			} else { //일반 유저일 경우
				list = boardService.getInquiryListByUserId(userId); //자신이 문의한 질문 리스트
			}
			
			if (list != null && !list.isEmpty()) {
				System.out.println(list);
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
