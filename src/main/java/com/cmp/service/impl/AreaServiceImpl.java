package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmp.dao.AreaMapper;
import com.cmp.model.Area;
import com.cmp.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaMapper areaMapper;

	@Override
	public List<Area> getProvince() {
		// TODO Auto-generated method stub
		return areaMapper.getProvince();
	}

	@Override
	public List<Area> getCity(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.getCity(area);
	}

	@Override
	public List<Area> getCounty(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.getCounty(area);
	}

	@Override
	public Area selectBycode(Area area) {
		// TODO Auto-generated method stub
		System.out.println("jadhajdnlahdlasdnalsdkasldkjla"+areaMapper.selectBycode(area).getFull_name());
		return areaMapper.selectBycode(area);
	}
	
	
}
