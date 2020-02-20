package com.example.form;

public class ItemForm {
	
	private String name;
	
	private Integer big;
   
	private Integer middle;
	
	private Integer mini;
	
	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBig() {
		return big;
	}

	public void setBig(Integer big) {
		this.big = big;
	}

	public Integer getMiddle() {
		return middle;
	}

	public void setMiddle(Integer middle) {
		this.middle = middle;
	}

	public Integer getMini() {
		return mini;
	}

	public void setMini(Integer mini) {
		this.mini = mini;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "ItemForm [name=" + name + ", big=" + big + ", middle=" + middle + ", mini=" + mini + ", brand=" + brand
				+ "]";
	}

	
	
	
	
}
