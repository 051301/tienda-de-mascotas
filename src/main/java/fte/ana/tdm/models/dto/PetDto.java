package fte.ana.tdm.models.dto;

import fte.ana.tdm.models.Pet;

public class PetDto {
	
	private int id;
	private String type;
	private double price;
	private long storeId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public Pet ToPet() {
		Pet pet = new Pet();
		pet.setPrice(price);
		pet.setType(type);
		return pet;
	}
	
}
