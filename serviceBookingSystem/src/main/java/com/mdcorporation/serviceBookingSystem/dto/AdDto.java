package com.mdcorporation.serviceBookingSystem.dto;





public class AdDto {
	
	private Long id;
	
	private String serviceName;
	
	private String Description;
	
	private double price;
	
	private String img;
	

	
	private long user_id;
	
	private String companyName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}



	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "AdDto [id=" + id + ", serviceName=" + serviceName + ", Description=" + Description + ", price=" + price
				+ ", img=" + img + ", user_id=" + user_id + ", companyName=" + companyName + "]";
	}


	
	
	
	

}
