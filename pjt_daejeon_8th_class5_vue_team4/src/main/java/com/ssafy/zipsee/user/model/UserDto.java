package com.ssafy.zipsee.user.model;

import java.util.List;

import com.ssafy.zipsee.board.model.BoardDto;

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
public class UserDto {
	private String userId;
	private String password;
	private String nickName;
	private String name;
	private String email;
	private String phone;
	private String sex;
	private String registerDate;
	
	private List<UserInterestDto> interestList;
	private List<UserDongDto> dongList;
	private List<UserDealDto> likeList;
	private List<BoardDto> boardList;
}
