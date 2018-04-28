package weather;

public class DonneesMeteo{
	public String humidite;
	public String vitessevent;
	public String temp;

	public DonneesMeteo() {
		super();
		this.humidite = "--";
		this.vitessevent = "--";
		this.temp = "--";
	}
	
	public DonneesMeteo(String humidite, String vitessevent, String temp) {
		super();
		this.humidite = humidite;
		this.vitessevent = vitessevent;
		this.temp = temp;
	}


	public String getHumidite() {
		return humidite;
	}

	public void setHumidite(String humidite) {
		this.humidite = humidite;
	}

	public String getVitessevent() {
		return vitessevent;
	}

	public void setVitessevent(String vitessevent) {
		this.vitessevent = vitessevent;
	}

	public String getTemp(String m) {
		if (m.equals("F" )) {
			Double f= Integer.parseInt(temp) *1.8 +32;
			int temp= (int) Math.round(f);
			String tempf = (String) Integer.toString(temp);
			return tempf;
			
		}
		return temp;
		
		
		
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}


	
}