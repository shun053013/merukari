package com.example.domain;


public class Item {
	private Integer id;
	private String name;
	private Integer itemConditionId;
	private double price;
	private Integer categoryId;
	private String brandName;
	private Integer shipping;
	private String itemDescription;
	private Integer constraintItemsPKC;
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getItemConditionId() {
		return itemConditionId;
	}

	public void setItemConditionId(Integer itemConditionId) {
		this.itemConditionId = itemConditionId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Integer getConstraintItemsPKC() {
		return constraintItemsPKC;
	}

	public void setConstraintItemsPKC(Integer constraintItemsPKC) {
		this.constraintItemsPKC = constraintItemsPKC;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", itemConditionId=" + itemConditionId + ", price=" + price
				+ ", categoryId=" + categoryId + ", brandName=" + brandName + ", shipping=" + shipping
				+ ", itemDescription=" + itemDescription + ", constraintItemsPKC=" + constraintItemsPKC + "]";
	}

}
