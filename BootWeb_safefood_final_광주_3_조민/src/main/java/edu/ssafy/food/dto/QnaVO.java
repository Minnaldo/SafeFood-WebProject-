package edu.ssafy.food.dto;

public class QnaVO{
	private String num;
    private String img;
    private String title;
    private String name;
    private String status;
    private String date;
    private String content;
    

	public QnaVO() {
		//super();
		// TODO Auto-generated constructor stub
	}


	public QnaVO(String num, String img, String title, String name, String status, String date, String content) {
		//super();
		this.num = num;
		this.img = img;
		this.title = title;
		this.name = name;
		this.status = status;
		this.date = date;
		this.content = content;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "QnaVO [num=" + num + ", img=" + img + ", title=" + title + ", name=" + name + ", status=" + status
				+ ", date=" + date + ", content=" + content + "]";
	}
	
	
	
    
}
