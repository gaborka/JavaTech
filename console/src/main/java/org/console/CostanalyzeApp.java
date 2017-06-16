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
		System.out.println("| type >  add   < to add new entry to Costanalyze Application           |");
		System.out.println("| type >  list  < to list entries from Costanalyze Application          |");
		System.out.println("| type >  exit  < to exit from Costanalyze Application                  |");
		while (run) {

			String line = br.readLine();
			if ("exit".equals(line)) {
				Log.info("Program exited");
				break;
			}
			if ("list".equals(line)) {
				listCostanalyzes();
			}
			if ("add".equals(line)) {
				addEntry();
			}
		}

	}

	private static void listCostanalyzes() throws JsonParseException, JsonMappingException, IOException {
		System.out.println(
				"Property name  Property size  M. Gas fee  M. Power fee  M. Water fee  M. average fee  Property category");

		for (Costanalyze costanalyze : CostanalyzeManager.listCostanalyzes()) {
			System.out.println(String.format("%1$s  %2$d m2  %3$d HUF  %4$d HUF  %5$d HUF  %6$.2f HUF/m2  %7$s",
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

		maveragefee = ((float) mgasfee + (float) mpowerfee + (float) mwaterfee) / (float) propertysize;

		if (maveragefee < 400) {
			category = "cheap";
		} else {
			category = "expensive";
		}

		Costanalyze costanalyze = new Costanalyze(propertyname, propertysize, mgasfee, mpowerfee, mwaterfee,
				maveragefee, category);
		CostanalyzeManager.acquireCostanalyze(costanalyze);
		Log.info("Property data added");

	}
}