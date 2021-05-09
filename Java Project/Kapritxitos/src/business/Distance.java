package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Distance {
	
	private static String filepath = "/home/borja/STA/practicas-fdbe98/KapV2/";
	//private static String filepath = "/docencia/cuentas/8/842412/practicas-fdbe98/KapV2/";
	
	private static HttpURLConnection connection;

	public static String[][] getApiDistance(String id, String originLat, String originLng, String transport) throws IOException {
		//Initialize variables
		String[][] arrayString = {};
		BufferedReader br; 
		String line; 
		StringBuffer responseContent = new StringBuffer();
		//Read Shops list
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		ArrayList<String> shoplist;
		if (id.equalsIgnoreCase("clothes"))
			shoplist = readCSVfile(filepath +"datosTiendas.csv");
		else
			shoplist = readCSVfile(filepath +"datosTiendasTec.csv");
		//Create connection
		try { 
			URL url = new URL(createURL(shoplist,originLat,originLng,transport)); 
			connection = (HttpURLConnection) url.openConnection();
			System.out.println(url);
			// REQUEST setup connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000); // 5 seconds no successful --> die
			connection.setReadTimeout(5000);
		
		 	int status = connection.getResponseCode(); // System.out.println(status); RESP: 200 OK
			
		 	if (status > 299) { //NOT SUCCESSFUL
		 		br= new BufferedReader(new InputStreamReader(connection.getErrorStream()));
		 		while((line = br.readLine()) != null) { 
		 			responseContent.append(line); 
			 	} 
			br.close(); 
		 	}
		 	else { //SUCCESSFUL
		 		br= new BufferedReader(new InputStreamReader(connection.getInputStream()));
		 		while((line = br.readLine()) != null) { 
		 			responseContent.append(line); 
		 		}
			  br.close(); 
		} 	arrayString = parse(getShopNameList(shoplist),responseContent.toString());
		//ORDERED	
		arrayString = shopsOrdered(arrayString);
		
			return arrayString;
		  
			
		
		} catch (MalformedURLException e) { // TODO Auto-generated catch block
			  e.printStackTrace(); 
		} finally { connection.disconnect(); }
		return arrayString;
				
	}

	private static String[][] parse(List<String> shopNameList, String responseBody) {
		JSONObject result = new JSONObject(responseBody); 				//Get result = responseBody
		JSONArray array = (JSONArray) result.get("rows");				//Get "rows" array in array
		JSONObject result2 = (JSONObject) array.get(0);					//Get object inside "rows" in result2
		JSONArray array2 = (JSONArray) result2.get("elements");			//Get "elements" array in array2
		
		String[][] arrayString = new String[array2.length()][4];		//ArrayString: will save [shopName,value,time,distance]
		
		for (int i=0;i<array2.length();i++) {							//Loop in array2 extracting distance, time & value
			JSONObject result3 = (JSONObject) array2.get(i);
			JSONObject duration = (JSONObject) result3.get("duration");
			String time = duration.getString("text");
			int value = duration.getInt("value");						//Value = Integer representing duration in seconds 
			JSONObject distance = (JSONObject) result3.get("distance");
			String dist= distance.getString("text"); 
			
			
			arrayString[i][0]=shopNameList.get(i); //SHOPNAME
			arrayString[i][1]=String.valueOf(value);
			arrayString[i][2]=time;
			arrayString[i][3]=dist;
			
			
			//System.out.println("Position "+i+": Time: "+time+", Value: "+value+" Distance: "+dist);
		}
		return arrayString;
	}

	private static String createURL(ArrayList<String> list, String latitude, String longitude,String transport) throws IOException {
				
		// Create URL
		String url_base = "https://maps.googleapis.com/maps/api/distancematrix/json?";
		String origins = "origins=";
		String destinations = "&destinations=";
		String mode = "&mode="+transport; // "&mode=walking"
		System.out.println("Modo de transporte: "+transport);
		String language = "&language=es-ES";
		String api_key = "&key=AIzaSyAz1NM-q-z4HG9gzImJkHbuAVIQA5Tg0-g";

		// Create destinations string for URL
		for (int i = 0; i < list.size(); i++) {
			String[] arrayRow = list.get(i).split(",");
			String slat = arrayRow[1];
			String slon = arrayRow[2];
			destinations += slat + "%2C" + slon + "|";
		}
		destinations = destinations.substring(0, destinations.length() - 1);	//Get destinations string without last "|"
		
		// Final URL 
		String url = url_base + origins + latitude + "," + longitude + destinations + mode + language + api_key;

		return url;
	}

	private static ArrayList<String> readCSVfile(String file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> lista = new ArrayList<String>();
		// Read each line of CSV file
		String line;
		while ((line = br.readLine()) != null) {
			lista.add(line);
		}
		br.close();
		return lista;
	}
	private static List<String> getShopNameList(ArrayList<String> shoplist) {
		String[] arrayRow;
		List<String> shopNameList = new ArrayList<>();
		
		for(int i=0;i<shoplist.size();i++) {
			arrayRow = shoplist.get(i).split(",");
			shopNameList.add(i, arrayRow[0]);
		}
		return shopNameList;
	}
	private static String[][] shopsOrdered(String[][] arrayString) {
		String[][] orderedList = new String[arrayString.length][4];
		int[] numList = new int[arrayString.length];
		//Get "value" from array to order by number:
		for(int i =0; i< arrayString.length; i++) {
			numList[i] = Integer.parseInt(arrayString[i][1]);
		}
		Arrays.sort(numList);
		boolean end = false;
		for(int i=0;i<arrayString.length;i++) {
			int j = 0;
			while(!end){
				if(numList[i] == Integer.parseInt(arrayString[j][1])) {
					orderedList[i] = arrayString[j];
					end = true;
				}
				j++;					
			}
			end = false;
		}
		return orderedList;
	}
}