package edu.ssafy.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.food.dto.AnnounceVO;
import edu.ssafy.food.repository.AnnounceRepo;

@Service(value = "AnnounceServiceImpl")
public class AnnounceServiceImpl implements AnnounceService {

	@Autowired
	@Qualifier("AnnounceMybatisRepoImpl")
	private AnnounceRepo repo;
	
	@Override
	public List<AnnounceVO> getAnnounceList() {
		// TODO Auto-generated method stub
		return repo.getAnnounceList();
	}

	@Override
	public void addAnnounce(String anum, String atitle, String awriter, String acontent, String adate, String acnt, String alike, String areply) {
		// TODO Auto-generated method stub
		repo.insertAnnounce(new AnnounceVO(anum, atitle, awriter, acontent, adate, acnt, alike, areply));
	}

	@Override
	public void updateAnnounce(String anum, String atitle, String awriter, String acontent, String adate, String acnt, String alike, String areply) {
		// TODO Auto-generated method stub
		repo.updateAnnounce(new AnnounceVO(anum, atitle, awriter, acontent, adate, acnt, alike, areply));
	}

	@Override
	public void deleteAnnounce(String anum) {
		// TODO Auto-generated method stub
		repo.deleteAnnounce(anum);
	}

	@Override
	public AnnounceVO getAnnounce(String anum) {
		// TODO Auto-generated method stub
		return repo.getAnnounce(anum);
	}

}
