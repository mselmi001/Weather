package weather;

import java.io.*;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class RecupDonneesPrevMeteo extends DonneesMeteo{
	private DonneesMeteo[] tableauPrevMeteo;
	private String url;
	private String response;
	private Date date;
	
	public RecupDonneesPrevMeteo (String nomville) throws IOException, JSONException{
		Calendar c = Calendar.getInstance ();
		this.date = (Date) c.getTime ();
		String u = "https://www.prevision-meteo.ch/services/json/" +nomville +"/";
		URL request = new URL(u);
		try {
			URLConnection connection = request.openConnection();
			connection.setDoOutput(true);
		}catch(IOException e) {
			System.out.println(e.toString());
			System.out.println("Error connection on API");
		} 
		HttpURLConnection http_conn = (HttpURLConnection)request.openConnection();
		int r = http_conn.getResponseCode();
		String reponse = http_conn.getResponseMessage();
		String r1 = Integer.toString(r);
		this.response = "- ["+r1.concat("-"+reponse)+"]";
		this.url= u;

		JSONTokener tokener = new JSONTokener((InputStream) request.getContent());
		JSONObject jsonPrincipal = new JSONObject(tokener);
		
		int i;
		this.tableauPrevMeteo = new DonneesMeteo[5];
		//System.out.println("PrevMeteo");
		//J-0
		JSONObject jsonCurrentCondition = jsonPrincipal.getJSONObject("current_condition");
		int vitessevent0 = jsonCurrentCondition.getInt("wnd_spd");
		int humidite0 = jsonCurrentCondition.getInt("humidity");
		JSONObject JsonJ0 = jsonPrincipal.getJSONObject("fcst_day_0");
		int tmin0 = JsonJ0.getInt("tmin");
		int tmax0 = JsonJ0.getInt("tmax");
		double tmoy0 = (tmin0 + tmax0) / 2;
		int temperature0= (int) Math.round(tmoy0);
		this.tableauPrevMeteo[0]=new DonneesMeteo(Integer.toString(humidite0), Integer.toString(vitessevent0), Integer.toString(temperature0));
		
		
		//Autres J
		String day = "fcst_day_";
		String hour = "H00";
		for (i = 1; i < 5; i++) {
			JSONObject jsonJ = jsonPrincipal.getJSONObject(day.concat(Integer.toString(i)));
			int tmin = jsonJ.getInt("tmin");
			int tmax = jsonJ.getInt("tmax");
			double tmoy = (tmin + tmax) / 2;
			int temperature= (int) Math.round(tmoy);
			
			JSONObject jsonH = jsonJ.getJSONObject("hourly_data");
			int vitesseVent = 0;
			int humi = 0;
			int j;
			for (j=0 ; j < 24 ; j++) {
				String h= Integer.toString(i)+hour;
				JSONObject jsonHByH = jsonH.getJSONObject(h);
				vitesseVent = vitesseVent + jsonHByH.getInt("WNDSPD10m");
				humi=humi + jsonHByH.getInt("RH2m");
			}
			
			String vitessevent = Integer.toString(vitesseVent/24);
			String humidite = Integer.toString(humi/24);
			this.tableauPrevMeteo[i]=new DonneesMeteo(humidite, vitessevent, Integer.toString(temperature));
			
		}

	}
	public DonneesMeteo[] getTabPrevMeteo() {
		return this.tableauPrevMeteo;
	}
	public String getURLPrev() {
		return this.url;
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public Date getDate() {
		return this.date;
	}

}