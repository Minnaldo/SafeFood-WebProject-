package edu.ssafy.food.service;

import java.util.List;

import edu.ssafy.food.dto.FoodVO;
import edu.ssafy.food.dto.QnaVO;

public interface QnaService {
	
	public List<QnaVO> getQnaList();
	public void addQna(String num, String img, String title, String name, String status, String date, String content);
	public void updateQna(String num, String img, String title, String name, String status, String date, String content);
	public void delQna(String num);
	public QnaVO getQna(String num);
}
