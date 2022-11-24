package com.ssafy.zipsee.house.model;

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
public class HouseDealDto {
	private int dealId;
	private int houseId;
	private int area;
	private int floor;
	private String dealType;
	private int deposit;
	private long price;
	private int like;
	
	private HouseInfoDto houseInfo;
}
