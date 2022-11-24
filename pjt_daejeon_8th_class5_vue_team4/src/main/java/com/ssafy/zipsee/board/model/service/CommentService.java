package com.ssafy.zipsee.board.model.service;

import java.sql.SQLException;

import com.ssafy.zipsee.board.model.CommentDto;

public interface CommentService {
	public boolean registerComment(CommentDto comment) throws SQLException;
	public boolean modifyComment(CommentDto comment) throws SQLException;
	public boolean deleteComment(int commentId) throws SQLException;
	public int deleteCommentByBoardId(int boardId) throws SQLException;
}
