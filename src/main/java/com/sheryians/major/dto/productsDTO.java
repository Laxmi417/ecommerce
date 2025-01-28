package com.sheryians.major.dto;


import com.sheryians.major.model.Category;

public class productsDTO {
	private Long product_id;
	 private String product_name;
	 private int category_id;
	 private double product_price;
	 private double product_weight;
	 private String description;
	 private String imageName;
	public productsDTO(Long product_id, String product_name, int category_id, double product_price,
			double product_weight, String description, String imageName) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.category_id = category_id;
		this.product_price = product_price;
		this.product_weight = product_weight;
		this.description = description;
		this.imageName = imageName;
	}
	public productsDTO() {
		super();
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public double getProduct_weight() {
		return product_weight;
	}
	public void setProduct_weight(double product_weight) {
		this.product_weight = product_weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public String toString() {
		return "productsDTO [product_id=" + product_id + ", product_name=" + product_name + ", category_id="
				+ category_id + ", product_price=" + product_price + ", product_weight=" + product_weight
				+ ", description=" + description + ", imageName=" + imageName + "]";
	}
	 
}
