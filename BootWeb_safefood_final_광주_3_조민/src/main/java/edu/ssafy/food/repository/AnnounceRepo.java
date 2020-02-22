package edu.ssafy.food.repository;

import java.util.List;

import edu.ssafy.food.dto.AnnounceVO;

public interface AnnounceRepo {
	public List<AnnounceVO> getAnnounceList();
	public void insertAnnounce(AnnounceVO announce);
	public void updateAnnounce(AnnounceVO announce);
	public void deleteAnnounce(String anum);
	public AnnounceVO getAnnounce(String anum);
}
