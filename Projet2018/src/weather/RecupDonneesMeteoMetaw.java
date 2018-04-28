package weather;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import weather.DonneesMeteo;


public class RecupDonneesMeteoMetaw extends DonneesMeteo{
	private DonneesMeteo[] tableauMeta;
	private String url;
	private String response;
	private Date date;
	
	public RecupDonneesMeteoMetaw (String nomville) throws IOException, JSONException{
		
		Calendar c = Calendar.getInstance ();
		this.date = (Date) c.getTime ();
		String requestURLwoeid = "https://www.metaweather.com/api/location/search/?query=" + nomville;
		URL metaRequestwoeid = new URL(requestURLwoeid);
		
		try {
			URLConnection connectionwoeid = metaRequestwoeid.openConnection();
			connectionwoeid.setDoOutput(true);
			HttpURLConnection http_conn = (HttpURLConnection)metaRequestwoeid.openConnection();
			int r = http_conn.getResponseCode();
			String reponse = http_conn.getResponseMessage();
			String r1 = Integer.toString(r);
			this.response = "- ["+r1.concat("-"+reponse)+"]";
			this.url= requestURLwoeid;
		}catch(IOException e) {
			System.out.println(e.toString());
			System.out.println("Error connection on API");
		}
	
		
		

		JSONTokener tokenerwoeid = new JSONTokener(metaRequestwoeid.openStream());
		JSONArray rootwoeid = new JSONArray(tokenerwoeid);
		JSONObject objrootwoeid = (JSONObject) rootwoeid.get(0);

		int woeid = (int) objrootwoeid.get("woeid");

		String requestURL = "https://www.metaweather.com/api/location/" + woeid + "/";
		URL metaRequest = new URL(requestURL);
		try {
			URLConnection connection = metaRequest.openConnection();
			connection.setDoOutput(true);
		}catch(IOException e) {
			System.out.println(e.toString());
			System.out.println("Error connection on API");
		}
		

		JSONTokener tokener = new JSONTokener(metaRequest.openStream());
		JSONObject root = new JSONObject(tokener);
		JSONArray section1 = (JSONArray) root.get("consolidated_weather");
		this.tableauMeta = new DonneesMeteo[5];
		for (int i = 0; i < 5; i++) {
			JSONObject section2 = (JSONObject) section1.get(i);
			//System.out.println("J+" + i);
			double temperaturei = (double) section2.get("the_temp");
			int temperature= (int) Math.round(temperaturei);
			//System.out.println("température = " + temperature);
			//infos[i].temp=temperature;
			double vitesseventi = (double) section2.get("wind_speed");
			int vitessevent= (int) Math.round(vitesseventi);
			//System.out.println("vitesse du vent = " + vitessevent);

			int humidite = (int) section2.get("humidity");
			//System.out.println("humidité = " + humidite);
			//System.out.println();
			this.tableauMeta[i]=new DonneesMeteo(Integer.toString(humidite), Integer.toString(vitessevent), Integer.toString(temperature));
			
		}
	}
	public DonneesMeteo[] getTabMeta() {
		return this.tableauMeta;
	}
	public String getURLMeta() {
		return this.url;
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public Date getDate() {
		return this.date;
	}

	
}