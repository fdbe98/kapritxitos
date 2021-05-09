package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateShopListTec {
	public static void main(String[] args) throws IOException {
		File f = new File("datosTiendasTec.csv");
		//if(!f.exists()) {
		//	f.createNewFile();
		//}
		FileWriter fw = new FileWriter(f,false); //FileWriter: false = overwrite, true = append
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		pw.println("Fnac,43.26130,-2.93004");
		pw.println("K-tuin,43.26105,-2.93894");
		pw.println("Milar,43.26001,-2.93891");
		pw.println("Modpc,43.25991,-2.94780");
		pw.println("Zbitt,43.25885,-2.94068");
		System.out.println("Shops list updated!");
		pw.close();
		bw.close();
	}
}