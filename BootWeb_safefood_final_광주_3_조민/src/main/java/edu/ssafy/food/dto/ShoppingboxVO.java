package edu.ssafy.food.dto;

public class ShoppingboxVO {
	private String id;
    private int foodcode;
    private int quantity;
	public ShoppingboxVO() {
//		super();
		// TODO Auto-generated constructor stub
	}
	public ShoppingboxVO(String id, int foodcode, int quantity) {
//		super();
		this.id = id;
		this.foodcode = foodcode;
		this.quantity = quantity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFoodcode() {
		return foodcode;
	}
	public void setFoodcode(int foodcode) {
		this.foodcode = foodcode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ShoppingboxVO [id=" + id + ", foodcode=" + foodcode + ", quantity=" + quantity + "]";
	}
    
    
}
