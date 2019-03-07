package com.cmp.service;

import java.util.List;

import com.cmp.model.Area;

public interface AreaService {
	List<Area> getProvince();
	List<Area> getCity(Area area);
	List<Area> getCounty(Area area);
	Area selectBycode(Area area);
}
