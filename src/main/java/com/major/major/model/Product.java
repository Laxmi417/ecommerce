package com.sheryians.major.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Product {
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private Long product_id;
 private String product_name;
 
 @ManyToOne(fetch=FetchType.LAZY)
 @JoinColumn(name="id")
 private Category category;
 private double product_price;
 private double product_weight;
 private String description;
 private String imageName;
public Product(String product_name, Category category, double product_price, double product_weight, String description,
		String imageName) {
	super();
	this.product_name = product_name;
	this.category = category;
	this.product_price = product_price;
	this.product_weight = product_weight;
	this.description = description;
	this.imageName = imageName;
}
public Product() {
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
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
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
	return "Product [product_id=" + product_id + ", product_name=" + product_name + ", category=" + category
			+ ", product_price=" + product_price + ", product_weight=" + product_weight + ", description=" + description
			+ ", imageName=" + imageName + "]";
}
 
 
 
 
}
