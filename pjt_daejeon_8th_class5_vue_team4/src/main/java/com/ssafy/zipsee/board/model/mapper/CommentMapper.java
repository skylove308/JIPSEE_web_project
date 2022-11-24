package com.ssafy.zipsee.board.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.zipsee.board.model.CommentDto;

@Mapper
public interface CommentMapper {
	public int registerComment(CommentDto comment) throws SQLException;
	public int modifyComment(CommentDto comment) throws SQLException;
	public int deleteComment(int commentId) throws SQLException;
	public int deleteCommentByBoardId(int boardId) throws SQLException;
}
