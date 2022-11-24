package com.ssafy.zipsee.user.model;

import com.ssafy.zipsee.house.model.HouseDealDto;

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
public class UserDealDto {
	private int userDealId;
	private String userId;
	private int dealId;
	
	private HouseDealDto houseDeal;
}
