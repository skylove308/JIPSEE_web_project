package com.ssafy.zipsee.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.zipsee.board.model.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> getNoticeList() throws SQLException;
	public List<BoardDto> getInquiryList() throws SQLException;
	public BoardDto getBoard(int boardId) throws SQLException;
	public int registerBoard(BoardDto board) throws SQLException;
	public int modifyBoard(BoardDto board) throws SQLException;
	public int deleteBoard(int boardId) throws SQLException;
	public List<BoardDto> getInquiryListByUserId(String userId) throws SQLException;
	public int deleteBoardByUserId(String userId) throws SQLException;
}
