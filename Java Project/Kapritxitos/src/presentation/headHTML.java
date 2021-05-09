package presentation;

public class headHTML {
	public static String htmlWithTitle(String title) {
		StringBuilder str = new StringBuilder();
		str.append("<!DOCTYPE html>");
		str.append("<html>");
		str.append("<body>");
		str.append("<h1>"+title+"</h1>");
		str.append("</body>");
		str.append("</html>");
		
		return str.toString();
	}

	public static String headWithStyle(String stylesheetFileName) {
		StringBuilder str = new StringBuilder();
		str.append("<!DOCTYPE html>\n");
		str.append("<html>\n<head>\n");
		str.append("<meta charset=\"UTF-8\">\n");
		str.append("<link rel=\"stylesheet\" type=\"text/css\" href=");
		str.append(stylesheetFileName);
		str.append(">\n");
		str.append("</head>\n");
		str.append("<body>\n");
			
		return str.toString();
		
	}
}
