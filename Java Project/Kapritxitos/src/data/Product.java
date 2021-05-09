package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private String gender;
	private String type;
	private String shop;
	private String desc;
	private String price;
	
	@XmlElement
	public String getGen() {
		return this.gender;
	}
	public void setGen(String gender) {
		this.gender = gender;
	}	
	@XmlElement
	public String getShop() {
		return this.shop;
	}
	public void setShop(String shop) {
		this.shop= shop;
	}
	@XmlElement
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement
	public String getPrice() {
		return this.price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@XmlElement
	public String getDesc() {
		return this.desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}