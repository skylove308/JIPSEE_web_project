package com.ssafy.zipsee.user.model;

import com.ssafy.zipsee.house.model.DongDto;

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
public class UserDongDto {
	private int userDongId;
	private String userId;
	private String dongCode;
	
	private DongDto dong;
}
