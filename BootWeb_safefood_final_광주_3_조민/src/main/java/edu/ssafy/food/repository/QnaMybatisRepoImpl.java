package edu.ssafy.food.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Element;


import edu.ssafy.food.dto.QnaVO;


@Repository("QnaMybatisRepoImpl")
public class QnaMybatisRepoImpl implements QnaRepo{

	@Autowired
	private SqlSession session;
	
	@Override
	public List<QnaVO> getQnaList() {
		// TODO Auto-generated method stub
		return session.selectList("sql.qna.selectlistQna");
	}

	@Override
	public void insertQna(QnaVO qna) {
		// TODO Auto-generated method stub
		session.insert("sql.qna.insertQna", qna);
		
	}

	@Override
	public void update(QnaVO qna) {
		// TODO Auto-generated method stub
		session.update("sql.qna.updateQna", qna);
		
	}

	@Override
	public void delete(String num) {
		// TODO Auto-generated method stub
		session.delete("sql.qna.deleteQna", num);
	}

	@Override
	public QnaVO getQna(String num) {
		// TODO Auto-generated method stub
		return session.selectOne("sql.qna.selectoneQna", num);
	}

}
