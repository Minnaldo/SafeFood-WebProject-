package edu.ssafy.food.service;

import java.sql.SQLException;
import java.util.List;

import org.w3c.dom.Element;

import edu.ssafy.food.dto.FoodVO;

public interface FoodService {

	
	public String getTagValue(String tag, Element eElement);
	public List<FoodVO> productparsing1(String url);
	public List<FoodVO> productparsing2(String url);
	public void loadData(String url, String url2) throws SQLException;
	public List<FoodVO> getFoodList();
	public FoodVO getFood(int code);
	public List<FoodVO> getFoodnamelist(String name);
}
