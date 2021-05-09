package presentation;

import data.Inventory;
import data.Product;

public class showTable {

	public static String showtableXML_JSON(NumResults num, Inventory inventory, String[][] orderedList, String gen, String type, String desc, float price) {
		
		StringBuilder str = new StringBuilder();
		str.append("<table id=\"productos\">\n");
		str.append("<tr>\n");
		//Column headers
		str.append("<th>Género</th>\n");
		str.append("<th>Categoría</th>\n");
		str.append("<th>Productos</th>\n");
		str.append("<th>Precio</th>\n");
		str.append("<th>Tienda</th>\n");
		str.append("<th>Tiempo</th>\n");
		str.append("<th>Distancia</th>\n");
		str.append("</tr>\n");
		str.append("<tbody>\n");
		//Loop show table
		String s = "";
		//Checks how many products found:
		for(int i=0;i<orderedList.length;i++) {
			for(Product p: inventory.getProducts()) {
				if(orderedList[i][0].equalsIgnoreCase(p.getShop()) 
						&& p.getType().equalsIgnoreCase(type) 
						&& p.getDesc().toLowerCase().contains(desc.toLowerCase()) 
						&& Float.parseFloat(p.getPrice())<= price) {
					num.setNum();

					s+="<tr>"; //New row
					s+="<td>"+gen+"</td>";
					s+="<td>"+type+"</td>";
					s+="<td>"+p.getDesc()+"</td>";
					s+="<td>"+p.getPrice()+" &#8364"+"</td>";
					s+="<td>"+p.getShop()+"</td>";
					s+="<td>"+orderedList[i][2]+"</td>";
					s+="<td>"+orderedList[i][3]+"</td>";
					s+="<td><input class=\""+p.getShop()+"\" type=\"button\" value=\"Ver ruta\" onclick=\"parent.initMap(this.className);\" />";
					s+="</tr>";
				}
			}
		}
		
		System.out.println("NUMERO DE RESULTADOS: "+num.getNum());
		str.append(s);
		str.append("</tbody>\n</table>\n");
		return str.toString();
	}
	public static String showtablePrice(NumResults num, Inventory inventory, String gen, String type, String desc, float price) {
		
		StringBuilder str = new StringBuilder();
		str.append("<table id=\"productos\">\n");
		str.append("<tr>\n");
		//Column headers
		str.append("<th>Género</th>\n");
		str.append("<th>Categoría</th>\n");
		str.append("<th>Productos</th>\n");
		str.append("<th>Precio</th>\n");
		str.append("<th>Tienda</th>\n");
		str.append("<th>Tiempo</th>\n");
		str.append("<th>Distancia</th>\n");
		str.append("</tr>\n");
		str.append("<tbody>\n");
		//Loop show table
		String s = "";
		//Checks how many products found:
		
		for(Product p: inventory.getProducts()) {
			if(p.getType().equalsIgnoreCase(type) 
					&& p.getDesc().toLowerCase().contains(desc.toLowerCase()) 
					&& Float.parseFloat(p.getPrice())<= price) {
				num.setNum();

				s+="<tr>"; //New row
				s+="<td>"+gen+"</td>";
				s+="<td>"+type+"</td>";
				s+="<td>"+p.getDesc()+"</td>";
				s+="<td>"+p.getPrice()+" &#8364"+"</td>";
				s+="<td>"+p.getShop()+"</td>";
				s+="<td>---</td>";
				s+="<td>---</td>";
				s+="<td><input class=\""+p.getShop()+"\" type=\"button\" value=\"Ver ruta\" onclick=\"parent.initMap(this.className);\" />";
				s+="</tr>";
			}
		}
			
		
		
		System.out.println("NUMERO DE RESULTADOS: "+num.getNum());
		str.append(s);
		str.append("</tbody>\n</table>\n");
		return str.toString();
	}
	public static String showtableXML_JSONTec(NumResults num, Inventory inventory, String[][] orderedList, String type, String brand, String desc, float price) {
		
		StringBuilder str = new StringBuilder();
		str.append("<table id=\"productos\">\n");
		str.append("<tr>\n");
		//Column headers
		str.append("<th>Categoría</th>\n");
		str.append("<th>Marca</th>\n");
		str.append("<th>Productos</th>\n");
		str.append("<th>Precio</th>\n");
		str.append("<th>Tienda</th>\n");
		str.append("<th>Tiempo</th>\n");
		str.append("<th>Distancia</th>\n");
		str.append("</tr>\n");
		str.append("<tbody>\n");
		//Loop show table
		String s = "";
		//Checks how many products found:
		for(int i=0;i<orderedList.length;i++) {
			for(Product p: inventory.getProducts()) {
				if(orderedList[i][0].equalsIgnoreCase(p.getShop()) 
						&& p.getDesc().toLowerCase().contains(brand.toLowerCase())
						&& p.getDesc().toLowerCase().contains(desc.toLowerCase()) 
						&& Float.parseFloat(p.getPrice())<= price) {
					num.setNum();

					s+="<tr>"; //New row
					s+="<td>"+type+"</td>";
					s+="<td>"+brand+"</td>";
					s+="<td>"+p.getDesc()+"</td>";
					s+="<td>"+p.getPrice()+" &#8364"+"</td>";
					s+="<td>"+p.getShop()+"</td>";
					s+="<td>"+orderedList[i][2]+"</td>";
					s+="<td>"+orderedList[i][3]+"</td>";
					s+="<td><input class=\""+p.getShop()+"\" type=\"button\" value=\"Ver ruta\" onclick=\"parent.initMap(this.className);\" />";
					s+="</tr>";
				}
			}
		}
		
		System.out.println("NUMERO DE RESULTADOS: "+num.getNum());
		str.append(s);
		str.append("</tbody>\n</table>\n");
		return str.toString();
	}
	public static String showtablePriceTec(NumResults num, Inventory inventory, String type, String brand, String desc, float price) {
	
		StringBuilder str = new StringBuilder();
		str.append("<table id=\"productos\">\n");
		str.append("<tr>\n");
		//Column headers
		str.append("<th>Categoría</th>\n");
		str.append("<th>Marca</th>\n");
		str.append("<th>Productos</th>\n");
		str.append("<th>Precio</th>\n");
		str.append("<th>Tienda</th>\n");
		str.append("<th>Tiempo</th>\n");
		str.append("<th>Distancia</th>\n");
		str.append("</tr>\n");
		str.append("<tbody>\n");
		//Loop show table
		String s = "";
		//Checks how many products found:
		for(Product p: inventory.getProducts()) {
			if(p.getDesc().toLowerCase().contains(brand.toLowerCase())
					&& p.getDesc().toLowerCase().contains(desc.toLowerCase()) 
					&& Float.parseFloat(p.getPrice())<= price) 
			{
				num.setNum();

				s+="<tr>"; //New row
				s+="<td>"+type+"</td>";
				s+="<td>"+brand+"</td>";
				s+="<td>"+p.getDesc()+"</td>";
				s+="<td>"+p.getPrice()+" &#8364"+"</td>";
				s+="<td>"+p.getShop()+"</td>";
				s+="<td>---</td>";
				s+="<td>---</td>";
				s+="<td><input class=\""+p.getShop()+"\" type=\"button\" value=\"Ver ruta\" onclick=\"parent.initMap(this.className);\" />";
				s+="</tr>";
			}
		}
			
		
		
		System.out.println("NUMERO DE RESULTADOS: "+num.getNum());
		str.append(s);
		str.append("</tbody>\n</table>\n");
		return str.toString();
	}
}
