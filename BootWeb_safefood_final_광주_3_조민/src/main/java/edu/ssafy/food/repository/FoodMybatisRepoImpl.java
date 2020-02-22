package edu.ssafy.food.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Element;

import edu.ssafy.food.dto.FoodVO;


@Repository("FoodMybatisRepoImpl")
public class FoodMybatisRepoImpl implements FoodRepo{

	@Autowired
	private SqlSession session;
	
	@Override
	public List<FoodVO> getFoodList() {
		// TODO Auto-generated method stub
		return session.selectList("sql.food.selectlistFood");
	}

	@Override
	public FoodVO getFood(int code) {
		// TODO Auto-generated method stub
		System.out.println("getfoo");
		return session.selectOne("sql.food.selectoneFood", code);
	}

	@Override
	public FoodVO getFoodname(String name) {
		// TODO Auto-generated method stub
		return session.selectOne("sql.food.selectnameFood", name);
	}

	@Override
	public List<FoodVO> getFoodnamelist(String name) {
		// TODO Auto-generated method stub
		return session.selectList("sql.food.selectnamelistFood", name);
	}


	@Override
	public List<FoodVO> getFoodmaker(String maker) {
		// TODO Auto-generated method stub
		return session.selectList("sql.food.selectmakerFood", maker);
	}

	@Override
	public void insertfood(FoodVO food) {
		// TODO Auto-generated method stub
		session.insert("sql.food.insertFood", food);
		
	}

}
