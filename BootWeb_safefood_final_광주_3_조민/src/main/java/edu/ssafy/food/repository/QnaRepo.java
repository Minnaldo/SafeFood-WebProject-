package edu.ssafy.food.repository;

import java.sql.SQLException;
import java.util.List;

import org.w3c.dom.Element;

import edu.ssafy.food.dto.FoodVO;
import edu.ssafy.food.dto.QnaVO;


public interface QnaRepo {
	public List<QnaVO> getQnaList();
	public void insertQna(QnaVO qna);
	public void update(QnaVO qna);
	public void delete(String num);
	public QnaVO getQna(String num);
	
}
