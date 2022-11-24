package com.ssafy.zipsee.house.model;

import java.util.List;

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
public class HouseInfoDto {
	private int houseId;
	private String houseName;
	private String houseType;
	private String dongCode;
	private String dongName;
	private int BuildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	
	DongDto dong;
	List<HouseDealDto> dealList;
}
