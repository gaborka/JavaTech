package org.service;

import java.util.Collection;

import org.api.Costanalyze;

public interface CostanalyzeDAO {
	
	void createCostanalyze(Costanalyze costanalyze);
	
	Collection<Costanalyze> readCostanalyzes();	

}