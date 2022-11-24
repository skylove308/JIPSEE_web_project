package com.ssafy.zipsee.board.model.service;

import java.util.List;

import com.ssafy.zipsee.board.model.BoardDto;

public interface BoardService {
	public List<BoardDto> getNoticeList() throws Exception;
	public List<BoardDto> getInquiryList() throws Exception;
	public BoardDto getBoard(int boardId) throws Exception;
	public boolean registerBoard(BoardDto board) throws Exception;
	public boolean modifyBoard(BoardDto board) throws Exception;
	public boolean deleteBoard(int boardId) throws Exception;
	public List<BoardDto> getInquiryListByUserId(String userId) throws Exception;
}
