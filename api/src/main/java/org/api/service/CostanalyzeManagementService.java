package org.api.service;

import java.util.Collection;

import org.api.Costanalyze;

public interface CostanalyzeManagementService {
	
	Collection<Costanalyze> listCostanalyzes();
	void acquireCostanalyze(Costanalyze costanalyze);

}