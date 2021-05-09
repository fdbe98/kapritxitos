package business;

import java.io.File;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import data.Inventory;
import data.Product;

public class Methods { 
	
	//private static String filepath = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/";
	private static String filepath = "/home/borja/STA/practicas-fdbe98/KapV2/";
	
	public static Inventory readJSONfile(String gender) throws JAXBException{
		
		//String filename = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/products_hombres.json";
		String filename = filepath + "products_hombres.json";
		if(gender.equalsIgnoreCase("Mujer"))
			filename = filepath + "products_mujeres.json";
			//filename = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/products_mujeres.json";
		
		//String filename = "/home/borja/STA/practicas-fdbe98/KapV2/"+gender;	//PruebasTest
		Inventory inventory = new Inventory();
		JAXBContext jaxbcontext = JAXBContext.newInstance(Inventory.class);
		Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		inventory = (Inventory) unmarshaller.unmarshal(new File(filename));
		return inventory;
	}
	
	public static Inventory readJSONfileTec(String type) throws JAXBException{
		
		//String filename = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/productos_televisores.json";
		String filename = filepath + "productos_televisores.json";
		if(type.equalsIgnoreCase("portatiles"))
			//String filename = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/productos_portatiles.json";
			filename = filepath + "productos_portatiles.json";
		else if(type.equalsIgnoreCase("monitores"))
			//String filename = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/productos_monitores.json";
			filename = filepath + "productos_monitores.json";
		else if(type.equalsIgnoreCase("smartphones"))
			//String filename = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/productos_smartphones.json";
			filename = filepath + "productos_smartphones.json";
		else if(type.equalsIgnoreCase("tablets"))
			//String filename = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/productos_tablets.json";
			filename = filepath + "productos_tablets.json";		
		
		System.out.println(filename);
				
		Inventory inventory = new Inventory();
		JAXBContext jaxbcontext = JAXBContext.newInstance(Inventory.class);
		Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		inventory = (Inventory) unmarshaller.unmarshal(new File(filename));
		return inventory;
	}
	public static Inventory shopsOrderedByPrice(Inventory inventory) {
		
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
			//Añadir product
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
