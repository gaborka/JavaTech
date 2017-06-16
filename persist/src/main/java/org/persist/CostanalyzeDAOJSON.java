package org.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.api.Costanalyze;
import org.service.CostanalyzeDAO;

public class CostanalyzeDAOJSON implements CostanalyzeDAO{
	
	private File database;
	

	public CostanalyzeDAOJSON(String databasePath) {
		this.database = new File(databasePath);
	}

	public void createCostanalyze(Costanalyze costanalyze) {
		Collection<Costanalyze> allCostanalyzes = readCostanalyzes();
		allCostanalyzes.add(costanalyze);
		Costanalyze[] extendedDatabase = allCostanalyzes.toArray(new Costanalyze[allCostanalyzes.size()]);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(database, extendedDatabase);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {	
		}
	}

	public Collection<Costanalyze> readCostanalyzes() {
		ObjectMapper mapper = new ObjectMapper();
		Costanalyze[] costanalyzes = new Costanalyze[]{};
		try {
			costanalyzes = mapper.readValue(database, Costanalyze[].class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			
		}
		Collection<Costanalyze> result = new ArrayList<Costanalyze>(Arrays.asList(costanalyzes));
		return result;
	}

	public void createEntry(Costanalyze arg0) {
		// TODO Auto-generated method stub
		
	}

	public Collection<Costanalyze> readCostanalyze() {
		// TODO Auto-generated method stub
		return null;
	}



}