package com.ssafy.zipsee.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {
	private int boardId;
	private String userId;
	private String title;
	private String content;
	private String registerDate;
	
	private CommentDto comment;
}
