package edu.ssafy.food.repository;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import edu.ssafy.food.dto.MemVO;

@Repository("MemMybatisRepoImpl")
public class MemMybatisRepoImpl implements MemRepo {

	@Autowired
	private SqlSession session;

	@Override
	public void insert(MemVO m) {
		session.insert("sql.member.insertMember", m); // member_sql.xml
	}

	@Override
	public void update(MemVO m) {
		session.update("sql.member.updateMember", m);
	}

	@Override
	public void delete(String id) {
		session.delete("sql.member.deleteMember", id);
	}

	@Override
	public MemVO selectOne(String id) {
		return session.selectOne("sql.member.selectMember", id);
	}

	@Override
	public List<MemVO> selectList() {
		return session.selectList("sql.member.selectMembers");
	}

	@Override
	public MemVO login(MemVO m) {
		System.out.println("???");
		return session.selectOne("sql.member.login", m);
	}

	@Override
	public String selectEmail(MemVO m) {
		// TODO Auto-generated method stub
		return session.selectOne("sql.member.selectEmail", m.getId());
	}

	@Override
	public String selectPw(MemVO m) {
		// TODO Auto-generated method stub
		return session.selectOne("sql.member.selectPw", m.getId());
	}

	@Override
	public void updatelike(MemVO m) {
		// TODO Auto-generated method stub
		System.out.println("repo부른다");
		System.out.println(m.toString());
		session.update("sql.member.updatelike", m);
		System.out.println("안와????");
	}
}
