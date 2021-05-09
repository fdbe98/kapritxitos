package data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Inventory {
	private List<Product> products;
	
	@XmlElement
	public List<Product> getProducts(){
		if(products == null)
			products = new ArrayList<Product>();
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
