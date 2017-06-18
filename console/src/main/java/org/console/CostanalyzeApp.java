package org.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

import org.api.Costanalyze;
import org.api.service.CostanalyzeManagementService;
import org.persist.CostanalyzeDAOJSON;
import org.service.CostanalyzeDAO;
import org.service.impl.CostanalyzeManagementServiceImpl;

public class CostanalyzeApp {
	private static final Logger Log = Logger.getLogger(CostanalyzeApp.class);
	private static CostanalyzeManagementService CostanalyzeManager;

	static {
		CostanalyzeDAO costanalyzeDAO = new CostanalyzeDAOJSON("resource/costanalyzes.json");
		CostanalyzeManager = new CostanalyzeManagementServiceImpl(costanalyzeDAO);
	}

	public static void main(String[] args) throws IOException {
		Log.info("Application starts");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean run = true;
		System.out.println("|   Property Cost Analyze Application                                   |");
		System.out.println("| type >  info  < to get information about Costanalyze Application      |");
		System.out.println("| type >  add   < to add new entry to Costanalyze Application           |");
		System.out.println("| type >  list  < to list entries from Costanalyze Application          |");
		System.out.println("| type >  exit  < to exit from Costanalyze Application                  |");
		while (run) {

			String line = br.readLine();
			if ("exit".equals(line)) {
				System.out.println("Good bye! Thank you for using the application!");
				Log.info("Program exited");
				break;
			}
			if ("list".equals(line)) {
				listCostanalyzes();
			}
			if ("add".equals(line)) {
				addEntry();
			}
			if ("info".equals(line)) {
				info();
			}
		}

	}

	private static void info() {
		System.out.println("Java Technology homework Application");
		System.out.println("Project name: Property Cost Analyze");
		System.out.println("------------------------------------");
		System.out.println("necessary user values:");
		System.out.println(" - property name    |only string all");
		System.out.println(" - property size    |max: 999 m2");
		System.out.println(" - monthly gas fee  |max: 999999 HUF");
		System.out.println(" - monthly power fee|max: 999999 HUF");
		System.out.println(" - monthly water fee|max: 999999 HUF");
		System.out.println("------------------------------------");
		System.out.println("sum of fees divided by size = HUF/m2");
		System.out.println("category:<400 cheap | >400 expensive");
		System.out.println("------------------------------------");
		Log.info("Application information listed");
	}

	private static void listCostanalyzes() throws JsonParseException, JsonMappingException, IOException {
		System.out.println(
				"| Property name | Property size | M. Gas fee | M. Power fee | M. Water fee | M. average fee | Property category |");
		for (Costanalyze costanalyze : CostanalyzeManager.listCostanalyzes()) {
			System.out.println(
					String.format("| %1$13s |%2$11d m2 |%3$7d HUF |%4$9d HUF |%5$9d HUF |%6$8.2f HUF/m2 |%7$18s |",
							costanalyze.getpropertyname(), costanalyze.getpropertysize(), costanalyze.getmgasfee(),
							costanalyze.getmpowerfee(), costanalyze.getmwaterfee(), costanalyze.getmaveragefee(),
							costanalyze.getcategory()));
		}
		Log.info("Costanalyze listed");

	}

	private static void addEntry() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String propertyname;
		int propertysize = 0;
		int mgasfee = 0;
		int mpowerfee = 0;
		int mwaterfee = 0;
		float maveragefee = 0;
		String category;

		System.out.println("Property name: ");
		propertyname = br.readLine();

		while (!propertyname.matches("[a-zA-Z]+")) {
			System.out.println("Please enter a valid property name!");
			Log.info("incorrect name entered");
			propertyname = br.readLine();
		}

		System.out.println("Property size in m2: ");
		while (propertysize < 1 | propertysize > 999) {

			try {
				propertysize = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a correct value!");
				Log.info("incorrect property size entered");
			}
		}

		System.out.println("Monthly gas fee in HUF: ");
		while (mgasfee < 1 | mgasfee > 999999) {
			try {
				mgasfee = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a correct value!");
				Log.info("incorrect gas value entered");
			}
		}

		System.out.println("Monthly power fee in HUF: ");
		while (mpowerfee < 1 | mpowerfee > 999999) {
			try {
				mpowerfee = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a correct value!");
				Log.info("incorrect power value entered");
			}
		}
		System.out.println("Monthly water fee in HUF: ");
		while (mwaterfee < 1 | mwaterfee > 999999) {

			try {
				mwaterfee = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a correct value!");
				Log.info("incorrect water value entered");
			}
		}

		// ( gas fee + power fee + water fee ) divided by property size = HUF/m2
		maveragefee = ((float) mgasfee + (float) mpowerfee + (float) mwaterfee) / (float) propertysize;

		if (maveragefee < 400) {
			category = "cheap";
		} else {
			category = "expensive";
		}

		Costanalyze costanalyze = new Costanalyze(propertyname, propertysize, mgasfee, mpowerfee, mwaterfee,
				maveragefee, category);
		CostanalyzeManager.acquireCostanalyze(costanalyze);
		System.out.println(" Property successfully added to the DB");
		Log.info("Property data added");

	}

}