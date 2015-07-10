package com.simland.core.module.shop.entity;

public class Shop implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.String name;
	private java.lang.String cname;
	private java.lang.String caddress;
	private java.lang.String coordinates;
	private java.lang.String bmodel;
	private java.lang.String brand;
	private java.lang.String salesArea;
	private java.lang.String clientrGroup;
	private java.lang.String regAddress;
	private java.lang.String corporate;
	private java.lang.String people;
	private java.lang.String engage;
	private java.lang.String loginName;
	private java.lang.String loginPwd;
	private java.lang.String email;
	private java.lang.String phone;
	private java.lang.String contact;
	private java.lang.String contactPeople;
	private java.lang.String clogo;
	private java.lang.String licenseImg;
	private java.lang.String score;

	private java.lang.Integer commodityNum;
	private java.lang.Integer collectNum;
	private java.lang.String recomm;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getCname() {
		return this.cname;
	}

	public void setCname(java.lang.String value) {
		this.cname = value;
	}

	public java.lang.String getCaddress() {
		return this.caddress;
	}

	public void setCaddress(java.lang.String value) {
		this.caddress = value;
	}

	public java.lang.String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(java.lang.String coordinates) {
		this.coordinates = coordinates;
	}

	public java.lang.String getBmodel() {
		return this.bmodel;
	}

	public void setBmodel(java.lang.String value) {
		this.bmodel = value;
	}

	public java.lang.String getBrand() {
		return this.brand;
	}

	public void setBrand(java.lang.String value) {
		this.brand = value;
	}

	public java.lang.String getSalesArea() {
		return this.salesArea;
	}

	public void setSalesArea(java.lang.String value) {
		this.salesArea = value;
	}

	public java.lang.String getClientrGroup() {
		return this.clientrGroup;
	}

	public void setClientrGroup(java.lang.String value) {
		this.clientrGroup = value;
	}

	public java.lang.String getRegAddress() {
		return this.regAddress;
	}

	public void setRegAddress(java.lang.String value) {
		this.regAddress = value;
	}

	public java.lang.String getCorporate() {
		return this.corporate;
	}

	public void setCorporate(java.lang.String value) {
		this.corporate = value;
	}

	public java.lang.String getPeople() {
		return this.people;
	}

	public void setPeople(java.lang.String value) {
		this.people = value;
	}

	public java.lang.String getEngage() {
		return this.engage;
	}

	public void setEngage(java.lang.String value) {
		this.engage = value;
	}

	public java.lang.String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}

	public java.lang.String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(java.lang.String value) {
		this.loginPwd = value;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmail(java.lang.String value) {
		this.email = value;
	}

	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String value) {
		this.phone = value;
	}

	public java.lang.String getContact() {
		return this.contact;
	}

	public void setContact(java.lang.String value) {
		this.contact = value;
	}

	public java.lang.String getContactPeople() {
		return this.contactPeople;
	}

	public void setContactPeople(java.lang.String value) {
		this.contactPeople = value;
	}

	public java.lang.String getClogo() {
		return this.clogo;
	}

	public void setClogo(java.lang.String value) {
		this.clogo = value;
	}

	public java.lang.String getLicenseImg() {
		return this.licenseImg;
	}

	public void setLicenseImg(java.lang.String value) {
		this.licenseImg = value;
	}

	public java.lang.String getScore() {
		return this.score;
	}

	public void setScore(java.lang.String value) {
		this.score = value;
	}

	public java.lang.Integer getCommodityNum() {
		return commodityNum;
	}

	public void setCommodityNum(java.lang.Integer commodityNum) {
		this.commodityNum = commodityNum;
	}

	public java.lang.Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(java.lang.Integer collectNum) {
		this.collectNum = collectNum;
	}

	public java.lang.String getRecomm() {
		return recomm;
	}

	public void setRecomm(java.lang.String recomm) {
		this.recomm = recomm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bmodel == null) ? 0 : bmodel.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((caddress == null) ? 0 : caddress.hashCode());
		result = prime * result + ((clientrGroup == null) ? 0 : clientrGroup.hashCode());
		result = prime * result + ((clogo == null) ? 0 : clogo.hashCode());
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((collectNum == null) ? 0 : collectNum.hashCode());
		result = prime * result + ((commodityNum == null) ? 0 : commodityNum.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((contactPeople == null) ? 0 : contactPeople.hashCode());
		result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result + ((corporate == null) ? 0 : corporate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((engage == null) ? 0 : engage.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((licenseImg == null) ? 0 : licenseImg.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((loginPwd == null) ? 0 : loginPwd.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((people == null) ? 0 : people.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((recomm == null) ? 0 : recomm.hashCode());
		result = prime * result + ((regAddress == null) ? 0 : regAddress.hashCode());
		result = prime * result + ((salesArea == null) ? 0 : salesArea.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shop other = (Shop) obj;
		if (bmodel == null) {
			if (other.bmodel != null)
				return false;
		} else if (!bmodel.equals(other.bmodel))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (caddress == null) {
			if (other.caddress != null)
				return false;
		} else if (!caddress.equals(other.caddress))
			return false;
		if (clientrGroup == null) {
			if (other.clientrGroup != null)
				return false;
		} else if (!clientrGroup.equals(other.clientrGroup))
			return false;
		if (clogo == null) {
			if (other.clogo != null)
				return false;
		} else if (!clogo.equals(other.clogo))
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (collectNum == null) {
			if (other.collectNum != null)
				return false;
		} else if (!collectNum.equals(other.collectNum))
			return false;
		if (commodityNum == null) {
			if (other.commodityNum != null)
				return false;
		} else if (!commodityNum.equals(other.commodityNum))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (contactPeople == null) {
			if (other.contactPeople != null)
				return false;
		} else if (!contactPeople.equals(other.contactPeople))
			return false;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		if (corporate == null) {
			if (other.corporate != null)
				return false;
		} else if (!corporate.equals(other.corporate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (engage == null) {
			if (other.engage != null)
				return false;
		} else if (!engage.equals(other.engage))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (licenseImg == null) {
			if (other.licenseImg != null)
				return false;
		} else if (!licenseImg.equals(other.licenseImg))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (loginPwd == null) {
			if (other.loginPwd != null)
				return false;
		} else if (!loginPwd.equals(other.loginPwd))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (people == null) {
			if (other.people != null)
				return false;
		} else if (!people.equals(other.people))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (recomm == null) {
			if (other.recomm != null)
				return false;
		} else if (!recomm.equals(other.recomm))
			return false;
		if (regAddress == null) {
			if (other.regAddress != null)
				return false;
		} else if (!regAddress.equals(other.regAddress))
			return false;
		if (salesArea == null) {
			if (other.salesArea != null)
				return false;
		} else if (!salesArea.equals(other.salesArea))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}

	
	
}
