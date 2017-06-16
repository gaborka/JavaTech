package org.service.impl;

import java.util.Collection;
import org.api.Costanalyze;
import org.api.service.CostanalyzeManagementService;
import org.service.CostanalyzeDAO;

public class CostanalyzeManagementServiceImpl implements CostanalyzeManagementService{

	private CostanalyzeDAO costanalyzeDAO;
	
	public CostanalyzeManagementServiceImpl(CostanalyzeDAO costanalyzeDAO) {
		super();
		this.costanalyzeDAO = costanalyzeDAO;
	}
	public Collection<Costanalyze> listCostanalyzes() {
		return costanalyzeDAO.readCostanalyzes();
	}
	public void acquireCostanalyze(Costanalyze costanalyze) {
		costanalyzeDAO.createCostanalyze(costanalyze);
	}
}