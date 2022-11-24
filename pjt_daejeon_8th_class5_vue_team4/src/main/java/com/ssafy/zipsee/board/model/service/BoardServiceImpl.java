package com.ssafy.zipsee.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.zipsee.board.model.BoardDto;
import com.ssafy.zipsee.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardDto> getNoticeList() throws Exception {
		return boardMapper.getNoticeList();
	}

	@Override
	public List<BoardDto> getInquiryList() throws Exception {
		return boardMapper.getInquiryList();
	}

	@Override
	public BoardDto getBoard(int boardId) throws Exception {
		return boardMapper.getBoard(boardId);
	}

	@Override
	public boolean registerBoard(BoardDto board) throws Exception {
		if(boardMapper.registerBoard(board) == 1) { 
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modifyBoard(BoardDto board) throws Exception {
		if(boardMapper.modifyBoard(board) == 1) { 
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteBoard(int boardId) throws Exception {
		if(boardMapper.deleteBoard(boardId) == 1) { 
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<BoardDto> getInquiryListByUserId(String userId) throws Exception {
		System.out.println("BoardServiceImpl : getInquiryListByUserId 실행");
		return boardMapper.getInquiryListByUserId(userId);
	}

}
