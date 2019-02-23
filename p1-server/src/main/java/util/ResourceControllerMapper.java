package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import abstraction.ApplicationController;
import controller.ExpenseReimbursementController;
import controller.UserAccessController;
import controller.UserSubmittedReimbursementController;

public class ResourceControllerMapper {

	public static HashMap<String, ApplicationController> mapper = new HashMap<>();
	private static Properties prop = getResourceProperties();

	static {
		register(prop.getProperty("GET_ExpenseReimbursements"), new ExpenseReimbursementController());
		register(prop.getProperty("GET_AllExpenseReimbursements"), new UserSubmittedReimbursementController());
		register(prop.getProperty("POST_Login"), new UserAccessController());
		register(prop.getProperty("POST_ExpenseReimbursement"), new ExpenseReimbursementController());
	}

	public static String getResource(String uri) {
		String[] strings = uri.split("/");
		return strings[strings.length - 1];
	}

	private static void register(String resource, ApplicationController controller) {
		mapper.put(resource, controller);
	}

	private static Properties getResourceProperties() {
		try {
			Properties prop = new Properties();
			prop.loadFromXML(ResourceControllerMapper.class.getResourceAsStream("/rest-resources.xml"));
			return prop;
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return null;
	}

}
