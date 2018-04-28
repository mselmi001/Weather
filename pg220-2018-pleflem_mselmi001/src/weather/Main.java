package weather;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;

import gestion.*;

public class Main {
	public static void main(String[] args) throws IOException, JSONException, MyException {
		int k;
		String ville =args[0].toLowerCase();
		File fichier = new File("DonneesApp/Requetes.log");
		fichier.setWritable(true);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		// On recupere les donnees meteo a partir de MetaWeather
		DonneesMeteo[] tabMeta;
		tabMeta = new DonneesMeteo[5];
		FileOutputStream stream = new FileOutputStream(fichier,true);
		try {
			RecupDonneesMeteoMetaw metaw = new RecupDonneesMeteoMetaw(ville);
			tabMeta =metaw.getTabMeta();
			Date dateM = metaw.getDate();
			String urlMeta =  metaw.getURLMeta();
			String responseMeta = metaw.getResponse();
			String dateMetaw = df.format(dateM);
			byte[] dm = dateMetaw.getBytes();
		    byte[] rm = responseMeta.getBytes();
		    byte[] um = urlMeta.getBytes();
		    stream.write(dm);
		    stream.write(' ');
		    stream.write(rm);
		    stream.write('\n');
		    stream.write(um);
		    stream.write('\n');
		}catch(JSONException e) {
			System.out.println("Localistation inconnue pour API Metaweather");
			for (k=0;k<5;k++) {
				tabMeta[k]=new DonneesMeteo();
			}
		}
		finally{
			// On recupere les donnes meteo a partir de Prevision Meteo
			DonneesMeteo[] tabPrevMeteo;
			tabPrevMeteo = new DonneesMeteo[5];
			try {
				RecupDonneesPrevMeteo prevMeteo = new RecupDonneesPrevMeteo(ville);
				tabPrevMeteo = prevMeteo.getTabPrevMeteo();
				Date dateP =  prevMeteo.getDate();
				String urlPrev =  prevMeteo.getURLPrev();
				String responsePrev = prevMeteo.getResponse();
				String datePrev = df.format(dateP);
				byte[] dp = datePrev.getBytes();
			    byte[] rp = responsePrev.getBytes();
			    byte[] up = urlPrev.getBytes();
			    stream.write(dp);
			    stream.write(' ');
			    stream.write(rp);
			    stream.write('\n');
			    stream.write(up);
			    stream.write('\n');
			}catch(JSONException e) {
				System.out.println("Localisation inconnue pour API PrevisionMeteo");
				for (k=0;k<5;k++) {
					tabPrevMeteo[k]=new DonneesMeteo();
				}
			}
			finally {
				// On recupere les donnees meteo a partir de Yahoo
				DonneesMeteo[] tabYahoo;
				tabYahoo = new DonneesMeteo[5];
				try {
					RecupDonneesMeteoYahoo yahoo = new RecupDonneesMeteoYahoo(ville);
					tabYahoo =yahoo.getTabYahoo();
					Date dateY = yahoo.getDate();
					String urlYahoo =  yahoo.getURLYahoo();
					String responseYahoo = yahoo.getResponse();
					String dateYahoo = df.format(dateY);
					byte[] dy = dateYahoo.getBytes();
				    byte[] ry = responseYahoo.getBytes();
				    byte[] uy = urlYahoo.getBytes();
				    stream.write(dy);
				    stream.write(' ');
				    stream.write(ry);
				    stream.write('\n');
				    stream.write(uy);
				    stream.write('\n');
				}catch(JSONException e) {
					System.out.println("Localisation inconnue pour API Yahoo");
					for (k=0;k<5;k++) {
						tabYahoo[k]=new DonneesMeteo();
					}
				}
				finally {
					stream.close();
					// Gestion arguments 
					GestionArg arg=new GestionArg();
					try {
						arg.setArgs(args);
						//Affichage
						GestionAffichage aff = new GestionAffichage();
						aff.Affichage(tabMeta, tabPrevMeteo, tabYahoo, arg.getNb_jours(), arg.getH(),arg.getW(), arg.getM());
						
						// Print dans un fichier 
						aff.PrintFichier(arg.isDel(),arg.getNom_fichier(),tabMeta, tabPrevMeteo, tabYahoo, arg.getNb_jours(), arg.getH(),arg.getW(), arg.getM());
						
					}catch(MyException e) {
						System.out.println("Ligne attendue :java -jar weather.jar Nomville -j 3 -h -w -(a ou o) fichier.txt -m (F ou C)");
					}
					
					
				}
			}
			
		}
		
		
	    
	    
	    
	    
	}
	
}