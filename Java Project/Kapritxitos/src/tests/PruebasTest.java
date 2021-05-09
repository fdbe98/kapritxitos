package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import business.Methods;
import data.Inventory;
import data.Product;

class PruebasTest {
	
	@Test
	void testShopsOrdered() throws JAXBException {
		// Arrange
		String[] price = {"69.00","79.00","85.00","94.50","99.00","129.00"};
		Inventory inv = Methods.readJSONfile("productList.json");
		
		// Act
		Inventory invOrdered = Pruebas.shopsOrdered(inv);
		
		// Assert
		int i = 0;
		for(Product p: invOrdered.getProducts()) {
			assertEquals(price[i], p.getPrice());	
			i++;
		}
		System.out.println("TEST FINALIZADO");
	}
}
