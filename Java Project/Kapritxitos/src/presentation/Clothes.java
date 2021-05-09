package presentation;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.xml.bind.JAXBException;

import business.Distance;
import business.Methods;
import data.*;


/**
 * Servlet implementation class Clothes
 */
@WebServlet("/clothes") 
@MultipartConfig
public class Clothes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Mostrar HTML con mensaje de error

		PrintWriter out = response.getWriter();
		String titleError = "ERROR, peticiones solo válidas con POST";
		String message = presentation.headHTML.htmlWithTitle(titleError);
		out.println(message); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		// POST: Receive form data: Product, Type of product, Description & Price
		PrintWriter out = response.getWriter();
		String gender = request.getParameter("gen"); 
		String type = request.getParameter("selCat"); 
		String price  = request.getParameter("fprice");
		String desc= request.getParameter("fdesc");
		String latitude= request.getParameter("lat");
		String longitude= request.getParameter("lng");
		String transport= request.getParameter("trans").toLowerCase();
		
		System.out.println("Genero: "+gender);
		System.out.println("Tipo producto: "+type);
		System.out.println("Precio: "+price);
		System.out.println("Descripcion: "+desc); 
		System.out.println("Latitude: "+latitude);
		System.out.println("Longitude: "+longitude);
		
		NumResults num = new NumResults();
		
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
	
		out.println(headHTML.headWithStyle("../css/iframe_style.css")); //iframe (Table) style css
		//Check if latitude/longitud are given by doing: equals 0 ?
		boolean checkcoord = latitude.equals("0");
		Inventory inventory;
		//Check if price is empty
		if(price.equals(""))
			price = "100000";
		//Check if price is a number
		String someString = price;
		boolean isNumeric = someString.chars().allMatch(Character::isDigit);
		if(isNumeric) {
		if(!checkcoord) {
			//Enter if price is valid & coordinates are given
			String[][] orderedList = Distance.getApiDistance("clothes",latitude, longitude, transport);
			for(int i=0;i<orderedList.length;i++)
				System.out.println("Shop: "+orderedList[i][0]+": Value: "+orderedList[i][1]+", Time: "+orderedList[i][2]+" Distance: "+orderedList[i][3]);
			
			try {
				
				inventory = Methods.readJSONfile(gender);
				out.println(showTable.showtableXML_JSON(num, inventory, orderedList, gender, type, desc,Float.parseFloat(price)));
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			try {
				//Enter if price is valid but coordinates are not given
				out.println("<h4>Sin coordenadas: Resultados por orden de precio</h4>");
				inventory = Methods.shopsOrderedByPrice(Methods.readJSONfile(gender));
				out.println(showTable.showtablePrice(num, inventory, gender, type, desc, Float.parseFloat(price)));
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else {
			//Enter if price not a number
			out.println("<p>Error, precio NO válido</p>");
		}
			
		out.println("<p>Resultados = "+num.getNum()+"</p>");
		if(num.getNum()==0)
			out.println("<p>Sin resultados, porfavor intente realizar otra búsqueda</p>");
		out.println("</body>"); 
		out.println("</html>"); 

		out.close();
	}
	
}
