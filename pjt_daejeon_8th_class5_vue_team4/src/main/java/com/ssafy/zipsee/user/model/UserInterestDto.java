package com.ssafy.zipsee.user.model;

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
public class UserInterestDto {
	private int userInterestId;
	private String userId;
	private String interestId;
	
	private InterestDto interest;
}
