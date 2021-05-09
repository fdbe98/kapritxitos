package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

public class CreateFiles {
	public static void main(String[] args) throws IOException, JAXBException{
		//Clothes
		String path_hom = "/home/borja/Desktop/Hombres/";
		String path_muj = "/home/borja/Desktop/Mujeres/";
		String JSONfile_hombres = "/home/borja/Desktop/productos_hombres.json";
		String JSONfile_mujeres = "/home/borja/Desktop/productos_mujeres.json";
		//Tecnology
		String path_mon = "/home/borja/Desktop/Monitores/";
		String path_por = "/home/borja/Desktop/Portatiles/";
		String path_sma = "/home/borja/Desktop/Smartphones/";
		String path_tab = "/home/borja/Desktop/Tablets/";
		String path_tel = "/home/borja/Desktop/Televisores/";
		String JSONfile_mon = "/home/borja/Desktop/productos_monitores.json";
		String JSONfile_por = "/home/borja/Desktop/productos_portatiles.json";
		String JSONfile_sma = "/home/borja/Desktop/productos_smartphones.json";
		String JSONfile_tab = "/home/borja/Desktop/productos_tablets.json";
		String JSONfile_tel = "/home/borja/Desktop/productos_televisores.json";
		
		//Clothes
		//createHombresMujeresFilesFromCSV(path_hom, JSONfile_hombres); //change to savetoJSONClothes
		//createHombresMujeresFilesFromCSV(path_muj, JSONfile_mujeres);
		
		//Tecnology
		//createHombresMujeresFilesFromCSV(path_mon, JSONfile_mon);
		//createHombresMujeresFilesFromCSV(path_por, JSONfile_por);
		//createHombresMujeresFilesFromCSV(path_sma, JSONfile_sma);
		//createHombresMujeresFilesFromCSV(path_tab, JSONfile_tab);
		//createHombresMujeresFilesFromCSV(path_tel, JSONfile_tel);
	}
	
	private static void createHombresMujeresFilesFromCSV(String path,String JSONfile) throws IOException, JAXBException{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        
		        ArrayList<String> list = readCSVFile(path+file.getName()); 
		        //saveToJSONFileClothes(JSONfile, list);
		        saveToJSONFileTecnology(JSONfile, list);
		    }
		}
		
	}
	private static ArrayList<String> readCSVFile(String file) throws IOException{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> list = new ArrayList<String>();
		String line;
		while((line = br.readLine()) != null) {
			list.add(line);
		}
		br.close();
		System.out.println("FILE "+file+" read!");
		return list;
	}
	public static void saveToJSONFileClothes(String filename, ArrayList<String> list) throws IOException , JAXBException{
		
		File file = new File(filename);
		Inventory inventory = new Inventory();
		
		if (!file.exists()) {
			file.createNewFile();
		}
		else {
			//Read JSON
			JAXBContext jaxbcontext = JAXBContext.newInstance(Inventory.class);
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
			inventory = (Inventory) unmarshaller.unmarshal(file);
		}
		
		for(int i=0;i<list.size();i++) {
			String[] row = list.get(i).split(",");
			//Add product
			Product product = new Product();
			product.setGen(row[1]);
			product.setType(row[2]);
			product.setShop(row[3].substring(0,1).toUpperCase()+row[3].substring(1).toLowerCase());
			product.setDesc(row[4].substring(0,1).toUpperCase()+row[4].substring(1).toLowerCase());
			product.setPrice(row[5]);		
			inventory.getProducts().add(product);
		}
		
		
		//Write JSON
		JAXBContext jaxbcontext = JAXBContext.newInstance(Inventory.class);
		Marshaller marshaller = jaxbcontext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,"application/json");
		marshaller.marshal(inventory, file);
	
	}
	public static void saveToJSONFileTecnology(String filename, ArrayList<String> list) throws IOException , JAXBException{
		
		File file = new File(filename);
		Inventory inventory = new Inventory();
		
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("Creado el fichero");
		}
		else {
			//Read JSON
			JAXBContext jaxbcontext = JAXBContext.newInstance(Inventory.class);
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
			inventory = (Inventory) unmarshaller.unmarshal(file);
		}
		for(int i=0;i<list.size();i++) {
			String[] row = list.get(i).split(";");
			//Add product
			Product product = new Product();
			product.setType(row[0]);
			product.setShop(row[1].substring(0,1).toUpperCase()+row[1].substring(1).toLowerCase());
			product.setDesc(row[2].substring(0,1).toUpperCase()+row[2].substring(1).toLowerCase());
			product.setPrice(row[3]);		
			inventory.getProducts().add(product);
		}
		
		//Write JSON
		JAXBContext jaxbcontext = JAXBContext.newInstance(Inventory.class);
		Marshaller marshaller = jaxbcontext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,"application/json");
		marshaller.marshal(inventory, file);
	}
	
}
