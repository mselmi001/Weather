package weather;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;

import weather.DonneesMeteo;
public class RecupDonneesMeteoYahoo {
	
	private DonneesMeteo[] tableauYahoo;
	private String url;
	private String response;
	private Date date;
	
	public RecupDonneesMeteoYahoo(String nomville) throws IOException, JSONException{
		Calendar c = Calendar.getInstance ();
		this.date = (Date) c.getTime ();
		String u="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(0)%20where%20text%3D%22"+nomville+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
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
		
		JSONTokener tokener = new JSONTokener((InputStream)request.getContent());
		JSONObject jsonPrincipal= new JSONObject(tokener);
		JSONObject query = (JSONObject) jsonPrincipal.get("query");
		JSONObject results = (JSONObject) query.get("results");
		JSONArray channel = (JSONArray) results.get("channel");
		
		//Vitesse du vent
		JSONObject day0 = (JSONObject) channel.get(0);
		JSONObject windspeed = (JSONObject) day0.get("wind");
		String vitessevent = (String) windspeed.get("speed");
		
		//Humidite
		JSONObject atmosphere = (JSONObject) day0.get("atmosphere");
		String humidity = (String) atmosphere.get("humidity");
		
		//Temperature
		JSONObject item = (JSONObject) day0.get("item");
		JSONArray forecast = (JSONArray) item.get("forecast");
		
		
		this.tableauYahoo = new DonneesMeteo[5];
		for(int i = 0; i<5; i++){
			//System.out.println("J+"+i);
			JSONObject temp = (JSONObject) forecast.get(i);
			String high = (String) temp.get("high");
			String low = (String) temp.get("low");
			int temperatureh = Integer.parseInt(high);
			int temperaturel = Integer.parseInt(low);
			double moy = (((temperatureh + temperaturel)/2)-32)/1.8;
			int temperature= (int) Math.round(moy);
			//System.out.println("température = "+temperature);
			String vitesse = "--";
			String humidite = "--";
			if (i == 0){
				vitesse = vitessevent;
				humidite = humidity;
			}
			//System.out.println("vitesse du vent = "+vitesse);
			//System.out.println("humidité = "+humidite);
			//System.out.println();
			this.tableauYahoo[i]=new DonneesMeteo(humidite,vitesse, Integer.toString(temperature));
		}		
	}	
	public DonneesMeteo[] getTabYahoo() {
		return this.tableauYahoo;
	}
	public String getURLYahoo() {
		return this.url;
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public Date getDate() {
		return this.date;
	}
}