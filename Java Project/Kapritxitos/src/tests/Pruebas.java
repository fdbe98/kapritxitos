package tests;
import java.util.Arrays;

import data.Inventory;
import data.Product;

public class Pruebas {
	
	public static Inventory shopsOrdered(Inventory inventory) {
		
		Inventory invOrdered = new Inventory();
		Object[][] numList = new Object[inventory.getProducts().size()][2];
		//Get "value" from array to order by number:
		for(int i =0; i< inventory.getProducts().size(); i++) {
			numList[i][0] = Float.parseFloat(inventory.getProducts().get(i).getPrice());
			numList[i][1] = i;
		}
		
		Arrays.sort(numList,(a,b) -> Float.compare((float)a[0], (float)b[0]));

		for(int i=0;i<numList.length;i++) {
			int pos = (int)numList[i][1];
			//Add product
			Product product = new Product();
			product.setGen(inventory.getProducts().get(pos).getGen());
			product.setType(inventory.getProducts().get(pos).getType());
			product.setShop(inventory.getProducts().get(pos).getShop());
			product.setDesc(inventory.getProducts().get(pos).getDesc());
			product.setPrice(inventory.getProducts().get(pos).getPrice());
			invOrdered.getProducts().add(product);	
		}	
	
		return invOrdered;
	}
}
