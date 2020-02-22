package edu.ssafy.food.dto;

public class AnnounceVO {
	private String anum;
    private String atitle;
    private String awriter;
    private String acontent;
    private String adate;
    private String acnt;
    private String alike;
    private String areply;
    
	public AnnounceVO() {}

	public AnnounceVO(String anum, String atitle, String awriter, String acontent, String adate, String acnt,
			String alike) {
		super();
		this.anum = anum;
		this.atitle = atitle;
		this.awriter = awriter;
		this.acontent = acontent;
		this.adate = adate;
		this.acnt = acnt;
		this.alike = alike;
		this.areply = "";
	}
	
	public AnnounceVO(String anum, String atitle, String awriter, String acontent, String adate, String acnt,
			String alike, String areply) {
		super();
		this.anum = anum;
		this.atitle = atitle;
		this.awriter = awriter;
		this.acontent = acontent;
		this.adate = adate;
		this.acnt = acnt;
		this.alike = alike;
		this.areply = areply;
	}
	
	

	public String getAnum() {
		return anum;
	}

	public void setAnum(String anum) {
		this.anum = anum;
	}

	public String getAtitle() {
		return atitle;
	}

	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}

	public String getAwriter() {
		return awriter;
	}

	public void setAwriter(String awriter) {
		this.awriter = awriter;
	}

	public String getAcontent() {
		return acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public String getAdate() {
		return adate;
	}

	public void setAdate(String adate) {
		this.adate = adate;
	}

	public String getAcnt() {
		return acnt;
	}

	public void setAcnt(String acnt) {
		this.acnt = acnt;
	}

	public String getAlike() {
		return alike;
	}

	public void setAlike(String alike) {
		this.alike = alike;
	}

	public String getAreply() {
		return areply;
	}

	public void setAreply(String areply) {
		this.areply = areply;
	}

	@Override
	public String toString() {
		return "AnnounceVO [anum=" + anum + ", atitle=" + atitle + ", awriter=" + awriter + ", acontent=" + acontent
				+ ", adate=" + adate + ", acnt=" + acnt + ", alike=" + alike + ", areply=" + areply + "]";
	}

	
    
}
