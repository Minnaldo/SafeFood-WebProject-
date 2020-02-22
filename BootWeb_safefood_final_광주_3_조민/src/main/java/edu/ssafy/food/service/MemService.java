package edu.ssafy.food.service;

import java.util.ArrayList;

import java.util.List;
import edu.ssafy.food.dto.MemVO;

public interface MemService {
	public void addMem(String id, String pw, String name, String addr, String email, String tel, String alinfo);
	public void updateMem(String id, String pw, String name, String addr, String email, String tel, String alinfo);
	public void updatelike(String id, String foodlike);
	public void delMem(String id);
	public MemVO selectOne(String id);
	public List<MemVO> selectList();
	public MemVO login(String id, String pw);
	public boolean isLogin(String id, String pw);
	String searchpw(String id, String name);
	String searchmail(String id, String name);
}
