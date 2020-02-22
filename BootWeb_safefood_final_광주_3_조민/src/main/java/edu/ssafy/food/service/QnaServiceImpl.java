package edu.ssafy.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.food.dto.QnaVO;
import edu.ssafy.food.repository.QnaRepo;

@Service(value = "QnaServiceImpl")
public class QnaServiceImpl implements QnaService {

	@Autowired
	@Qualifier("QnaMybatisRepoImpl")
	private QnaRepo repo;
	
	@Override
	public List<QnaVO> getQnaList() {
		// TODO Auto-generated method stub
		return repo.getQnaList();
	}

	@Override
	public void addQna(String num, String img, String title, String name, String status, String date, String content) {
		// TODO Auto-generated method stub
		repo.insertQna(new QnaVO(num, img, title, name, status, date, content));
		
	}

	@Override
	public void updateQna(String num, String img, String title, String name, String status, String date,
			String content) {
		// TODO Auto-generated method stub
		repo.update(new QnaVO(num, img, title, name, status, date, content));
		
	}

	@Override
	public void delQna(String num) {
		// TODO Auto-generated method stub
		repo.delete(num);
	}

	@Override
	public QnaVO getQna(String num) {
		// TODO Auto-generated method stub
		return repo.getQna(num);
	}

}
