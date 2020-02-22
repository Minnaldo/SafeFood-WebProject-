package edu.ssafy.food.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.food.dto.AnnounceVO;

@Repository("AnnounceMybatisRepoImpl")
public class AnnounceMybatisRepoImpl implements AnnounceRepo {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<AnnounceVO> getAnnounceList() {
		// TODO Auto-generated method stub
		return session.selectList("sql.announce.selectlistAnnounce");
	}

	@Override
	public void insertAnnounce(AnnounceVO announce) {
		// TODO Auto-generated method stub
		session.insert("sql.announce.insertAnnounce", announce);
	}

	@Override
	public void updateAnnounce(AnnounceVO announce) {
		// TODO Auto-generated method stub
		session.update("sql.announce.updateAnnounce", announce);
	}

	@Override
	public void deleteAnnounce(String anum) {
		// TODO Auto-generated method stub
		session.delete("sql.announce.deleteAnnounce", anum);
	}

	@Override
	public AnnounceVO getAnnounce(String anum) {
		// TODO Auto-generated method stub
		return session.selectOne("sql.announce.selectoneAnnounce", anum);
	}

}
