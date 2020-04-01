package dto;

public class States {

	int ID;
	String States_Name;
	int Countries_ID;
		
	public States(int iD, String states_Name, int countries_ID) {
		this.ID = iD;
		this.States_Name = states_Name;
		this.Countries_ID = countries_ID;
	}
	
	public States(String states_Name) {
		this.States_Name = states_Name;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getStates_Name() {
		return States_Name;
	}
	public void setStates_Name(String states_Name) {
		States_Name = states_Name;
	}
	public int getCountries_ID() {
		return Countries_ID;
	}
	public void setCountries_ID(int countries_ID) {
		Countries_ID = countries_ID;
	}
	
	
	
}
