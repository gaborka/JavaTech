package org.api;

public class Costanalyze {
	
	private String propertyname;
	private int propertysize;
	private int mgasfee;
	private int mpowerfee;
	private int mwaterfee;
	private float maveragefee;
	private String category;
	
	public Costanalyze() {
		super();
	}
	
	public Costanalyze(String propertyname, int propertysize, int mgasfee, int mpowerfee, int mwaterfee, float maveragefee, String category) {
		super();
		this.propertyname = propertyname;
		this.propertysize = propertysize;
		this.mgasfee = mgasfee;
		this.mpowerfee = mpowerfee;
		this.mwaterfee = mwaterfee;
		this.maveragefee = maveragefee;
		this.category = category;
	}

	public String getpropertyname() {
		return propertyname;
	}

	public void setpropertyname(String propertyname) {
		this.propertyname = propertyname;
	}

	public int getpropertysize() {
		return propertysize;
	}

	public void setpropertysize(int propertysize) {
		this.propertysize = propertysize;
	}
	
	public int getmgasfee() {
		return mgasfee;
	}

	public void setmgasfee(int mgasfee) {
		this.mgasfee = mgasfee;
	}

	public int getmpowerfee() {
		return mpowerfee;
	}

	public void setmpowerfee(int mpowerfee) {
		this.mpowerfee = mpowerfee;
	}

	public int getmwaterfee() {
		return mwaterfee;
	}

	public void setmwaterfee(int mwaterfee) {
		this.mwaterfee = mwaterfee;
	}
	
	public float getmaveragefee() {
		return maveragefee;
	}

	public void setmaveragefee(float maveragefee) {
		this.maveragefee = maveragefee;
	}
	
	public String getcategory() {
		return category;
	}

	public void setcategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "phonebook [propertyname=" + propertyname + ", propertysize=" + propertysize + ", mgasfee=" + mgasfee + ", mpowerfee=" + mpowerfee + ", mwaterfee=" + mwaterfee + ", maveragefee=" + maveragefee + ", category=" + category + "]";
	}
	
	

}