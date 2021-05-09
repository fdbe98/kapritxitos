package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateShopListClothes {
	public static void main(String[] args) throws IOException {
		File f = new File("datosTiendas.csv");
		//if(!f.exists()) {
		//	f.createNewFile();
		//}
		FileWriter fw = new FileWriter(f,false); //FileWriter: false = overwrite, true = append
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		pw.println("Guess,43.263169540496655,-2.936089919931067");
		pw.println("G-star,43.26281,-2.93554");
		pw.println("Parfois,43.26213750264495,-2.9325600650433477");
		pw.println("Napapijri,43.26310839925463,-2.930739653823539");
		pw.println("Brownie,43.26205,-2.93784");
		pw.println("Coquette,43.26170,-2.93443");
		pw.println("Insphyria,43.26272,-2.94590");
		pw.println("Nafnaf,43.26133,-2.92857");
		pw.println("Phuket,43.26196,-2.93865");
		System.out.println("Shops list updated!");
		pw.close();
		bw.close();
	}
}