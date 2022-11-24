package com.ssafy.zipsee.board.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.zipsee.board.model.BoardDto;
import com.ssafy.zipsee.board.model.CommentDto;
import com.ssafy.zipsee.board.model.service.BoardService;
import com.ssafy.zipsee.board.model.service.CommentService;
import com.ssafy.zipsee.user.model.UserDto;
import com.ssafy.zipsee.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, maxAge = 6000)
@RestController
@RequestMapping("/board")
@Api("게시판 컨트롤러  API")
public class BoardRestController {
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private UserService userService;
	private BoardService boardService;
	private CommentService commentService;

	@Autowired
	public BoardRestController(UserService userService, BoardService boardService, CommentService commentService) {
		super();
		this.userService = userService;
		this.boardService = boardService;
		this.commentService = commentService;
	}

	@GetMapping("/notice")
	@ApiOperation(value = "게시판 공지목록", notes = "모든 공지 게시글의 정보를 반환한다.", response = List.class)
	public ResponseEntity<?> noticeList() {
		try {
			List<BoardDto> list = boardService.getNoticeList();
			if (list != null && !list.isEmpty()) {
				logger.info("noticeList - 호출");
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/ask")
	@ApiOperation(value = "게시판 문의목록", notes = "모든 문의 게시글의 정보를 반환한다.", response = List.class)
	public ResponseEntity<?> InquiryList() {
		try {
			List<BoardDto> list = boardService.getInquiryList();
			if (list != null && !list.isEmpty()) {
				logger.info("InquiryList - 호출");
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "공지글 글작성", notes = "새로운 공지 정보를 입력한다.(관리자만 가능) 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/notice")
	public ResponseEntity<String> writeNotice(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto board) throws Exception {
		logger.info("writeNotice - 호출");
		if (boardService.registerBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "문의 글작성", notes = "새로운 문의 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/ask")
	public ResponseEntity<String> writeAsk(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto board, HttpServletRequest request) throws Exception {
		logger.info("writeAsk - 호출");
		
		String token = request.getHeader("refresh-token");
		
		UserDto userDto = userService.getUserByToken(token);
		String userId = userDto.getUserId();
		
		board.setUserId(userId);
		
		if (boardService.registerBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공지 글수정", notes = "수정할 공지 정보를 입력한다.(관리자만 가능) 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/notice")
	public ResponseEntity<String> modifyNotice(@RequestBody @ApiParam(value = "수정할 문의 정보", required = true) BoardDto board) throws Exception {
		logger.info("modifyNotice - 호출", board);
		
		if (boardService.modifyBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "문의 글수정", notes = "수정할 문의 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/ask")
	public ResponseEntity<String> modifyAsk(@RequestBody @ApiParam(value = "수정할 문의 정보", required = true) BoardDto board) throws Exception {
		logger.info("modifyAsk - 호출", board);
		
		if (boardService.modifyBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지 글삭제", notes = "글번호에 해당하는 공지글의 정보를 삭제한다.(관리자만 가능) 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("notice/{boardid}")
	public ResponseEntity<String> deleteNotice(@PathVariable("boardid") @ApiParam(value = "삭제할 글의 글번호.", required = true, example = "0") int boardId) throws Exception {
		logger.info("deleteNotice - 호출");
		if (boardService.deleteBoard(boardId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "문의 글삭제", notes = "글번호에 해당하는 문의글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("ask/{boardid}")
	public ResponseEntity<String> deleteAsk(@PathVariable("boardid") @ApiParam(value = "삭제할 글의 글번호.", required = true, example = "0") int boardId) throws Exception {
		logger.info("deleteAsk - 호출");
		if (boardService.deleteBoard(boardId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	//Comment
	
	@ApiOperation(value = "문의에 대한 답변 작성", notes = "새로운 답변을 입력한다.(관리자만 가능) 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/comment")
	public ResponseEntity<String> writeComment(@RequestBody @ApiParam(value = "답변 정보.", required = true) CommentDto comment) throws Exception {
		logger.info("writeNotice - 호출");
		if (commentService.registerComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
