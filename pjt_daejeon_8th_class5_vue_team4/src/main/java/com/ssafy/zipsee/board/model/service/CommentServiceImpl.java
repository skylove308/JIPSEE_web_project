package com.ssafy.zipsee.board.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.zipsee.board.model.CommentDto;
import com.ssafy.zipsee.board.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper comentMapper;
	
	@Autowired
	public CommentServiceImpl(CommentMapper comentMapper) {
		super();
		this.comentMapper = comentMapper;
	}

	@Override
	public boolean registerComment(CommentDto comment) throws SQLException {
		if(comentMapper.registerComment(comment) == 1) { 
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modifyComment(CommentDto comment) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteComment(int commentId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteCommentByBoardId(int boardId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
