package edu.ssafy.food.service;

import java.util.List;

import edu.ssafy.food.dto.AnnounceVO;

public interface AnnounceService {

	public List<AnnounceVO> getAnnounceList();
	public void addAnnounce(String anum, String atitle, String awriter, String acontent, String adate, String acnt, String alike, String areply);
	public void updateAnnounce(String anum, String atitle, String awriter, String acontent, String adate, String acnt, String alike, String areply);
	public void deleteAnnounce(String anum);
	public AnnounceVO getAnnounce(String anum);
}
