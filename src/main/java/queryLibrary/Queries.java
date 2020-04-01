package queryLibrary;

public class Queries {

	public static String askCountries = "select * from [Worlds].[dbo].[Countries]";
	public static String askStates = "select * from [Worlds].[dbo].[States] where Countries_ID=";
	public static String askRoads = "select * from [Worlds].[dbo].[roads] where States_ID=";
	
}
