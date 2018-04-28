package gestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import weather.*;

public class GestionAffichage {
	
	public void Affichage(DonneesMeteo[] tabMeta,DonneesMeteo[] tabPrevMeteo,DonneesMeteo[] tabYahoo,int nb_jours, int h, int w, String m) {
		int i;
		String tiretbaseh="-----------+";
		
		System.out.print("+-------------+");
		for (i=0; i< nb_jours ; i++) {
			System.out.print(tiretbaseh);
		}
		System.out.println(tiretbaseh);
		// ligne jours
		System.out.print("|             |");
		for (i=0;i<nb_jours;i++) {
			String s = "J+".concat(Integer.toString(i));
			System.out.print("    "+ s + "    |");
		}
		String s = "J+".concat(Integer.toString(nb_jours));
		System.out.println("    "+ s + "    |");
		//passage suivant
		System.out.print("+-------------+");
		for (i=0; i< nb_jours ; i++) {
			System.out.print(tiretbaseh);
		}
		System.out.println(tiretbaseh);
		//Metaweather
		//temperature
		System.out.print("| MetaWeather |");
		for (i=0;i<nb_jours ;i++) {
			if (tabMeta[i].getTemp(m).length()==1) {
				System.out.print("     "+ tabMeta[i].getTemp(m)+m + "    |");
			}
			if (tabMeta[i].getTemp(m).length()==2) {
				System.out.print("    "+ tabMeta[i].getTemp(m)+m + "    |");
			}
		}
		if (tabMeta[nb_jours].getTemp(m).length()==1) {
			System.out.println("     "+ tabMeta[nb_jours].getTemp(m)+m+ "    |");
		}
		if (tabMeta[nb_jours].getTemp(m).length()==2) {
			System.out.println("    "+ tabMeta[nb_jours].getTemp(m)+m + "    |");
		}
		//humidite
		if (h==1) {
			System.out.print("|             |");
			for (i=0;i<nb_jours ;i++) {
				System.out.print("    "+ tabMeta[i].getHumidite()+"%" + "    |");
			}
			System.out.println("    "+ tabMeta[nb_jours].getHumidite()+"%" + "    |");
		}
		//Vitesse Vent
		if (w==1) {
			System.out.print("|             |");
			for (i=0;i<nb_jours ;i++) {
				if (tabMeta[i].getVitessevent().length()==1) {
					System.out.print("   "+ tabMeta[i].getVitessevent()+"km/h" + "   |");
				}
				if (tabMeta[i].getVitessevent().length()==2) {
					System.out.print("  "+ tabMeta[i].getVitessevent()+"km/h" + "   |");
				}
				if (tabMeta[i].getVitessevent().length()==3) {
					System.out.print("   "+ tabMeta[i].getVitessevent()+"km/h" + "  |");
				}
			}
			if (tabMeta[nb_jours].getVitessevent().length()==1) {
				System.out.println("   "+ tabMeta[nb_jours].getVitessevent()+"km/h" + "   |");
			}
			if (tabMeta[nb_jours].getVitessevent().length()==2) {
				System.out.println("  "+ tabMeta[nb_jours].getVitessevent()+"km/h" + "   |");
			}
			if (tabMeta[nb_jours].getVitessevent().length()==3) {
				System.out.println("   "+ tabMeta[nb_jours].getVitessevent()+"km/h" + "  |");
			}
		}
		//passage suivant
		System.out.print("+-------------+");
		for (i=0; i< nb_jours ; i++) {
			System.out.print(tiretbaseh);
		}
		System.out.println(tiretbaseh);
		// Prevision meteo
		//Temperature
		System.out.print("|  PrevMeteo  |");
		for (i=0;i<nb_jours ;i++) {
			if (tabPrevMeteo[i].getTemp(m).length()==1) {
				System.out.print("     "+ tabPrevMeteo[i].getTemp(m)+m + "    |");
			}
			if (tabPrevMeteo[i].getTemp(m).length()==2) {
				System.out.print("    "+ tabPrevMeteo[i].getTemp(m)+m + "    |");
			}
		}
		if (tabPrevMeteo[nb_jours].getTemp(m).length()==1) {
			System.out.println("     "+ tabPrevMeteo[nb_jours].getTemp(m)+m + "    |");
		}
		if (tabPrevMeteo[nb_jours].getTemp(m).length()==2) {
			System.out.println("    "+ tabPrevMeteo[nb_jours].getTemp(m)+m + "    |");
		}
		//Humidite
		if (h==1) {
			System.out.print("|             |");
			for (i=0;i<nb_jours ;i++) {
				System.out.print("    "+ tabPrevMeteo[i].getHumidite()+"%" + "    |");
			}
			System.out.println("    "+ tabPrevMeteo[nb_jours].getHumidite()+"%" + "    |");
		}
		// Vitesse vent 
		if (w==1) {
			System.out.print("|             |");
			for (i=0;i<nb_jours ;i++) {
				if (tabPrevMeteo[i].getVitessevent().length()==1) {
					System.out.print("   "+ tabPrevMeteo[i].getVitessevent()+"km/h" + "   |");
				}
				if (tabPrevMeteo[i].getVitessevent().length()==2) {
					System.out.print("  "+ tabPrevMeteo[i].getVitessevent()+"km/h" + "   |");
				}
				if (tabPrevMeteo[i].getVitessevent().length()==3) {
					System.out.print("   "+ tabPrevMeteo[i].getVitessevent()+"km/h" + "  |");
				}
			}
			if (tabPrevMeteo[nb_jours].getVitessevent().length()==1) {
				System.out.println("   "+ tabPrevMeteo[nb_jours].getVitessevent()+"km/h" + "   |");
			}
			if (tabPrevMeteo[nb_jours].getVitessevent().length()==2) {
				System.out.println("  "+ tabPrevMeteo[nb_jours].getVitessevent()+"km/h" + "   |");
			}
			if (tabPrevMeteo[nb_jours].getVitessevent().length()==3) {
				System.out.println("   "+ tabPrevMeteo[nb_jours].getVitessevent()+"km/h" + "  |");
			}
		}
		//passage suivant
		System.out.print("+-------------+");
		for (i=0; i< nb_jours ; i++) {
			System.out.print(tiretbaseh);
		}
		System.out.println(tiretbaseh);
		//Yahoo
		System.out.print("|    Yahoo    |");
		for (i=0;i<nb_jours ;i++) {
			if (tabYahoo[i].getTemp(m).length()==1) {
				System.out.print("     "+ tabYahoo[i].getTemp(m)+m + "    |");
			}
			if (tabYahoo[i].getTemp(m).length()==2) {
				System.out.print("    "+ tabYahoo[i].getTemp(m)+m + "    |");
			}
		}
		if (tabYahoo[nb_jours].getTemp(m).length()==1) {
			System.out.println("     "+ tabYahoo[nb_jours].getTemp(m)+m+ "    |");
		}
		if (tabYahoo[nb_jours].getTemp(m).length()==2) {
			System.out.println("    "+ tabYahoo[nb_jours].getTemp(m)+m + "    |");
		}
		// humidite
		if (h==1) {
			System.out.print("|             |");
			for (i=0;i<nb_jours ;i++) {
				System.out.print("    "+ tabYahoo[i].getHumidite()+"%" + "    |");
			}
			System.out.println("    "+ tabYahoo[nb_jours].getHumidite()+"%" + "    |");
		}
		// Vitesse Vent
		if (w==1) {
			System.out.print("|             |");
			for (i=0;i<nb_jours ;i++) {
				if (tabYahoo[i].getVitessevent().length()==1) {
					System.out.print("   "+ tabYahoo[i].getVitessevent()+"km/h" + "   |");
				}
				if (tabYahoo[i].getVitessevent().length()==2) {
					System.out.print("  "+ tabYahoo[i].getVitessevent()+"km/h" + "   |");
				}
				if (tabYahoo[i].getVitessevent().length()==3) {
					System.out.print("   "+ tabYahoo[i].getVitessevent()+"km/h" + "  |");
				}
			}
			if (tabYahoo[nb_jours].getVitessevent().length()==1) {
				System.out.println("   "+ tabYahoo[nb_jours].getVitessevent()+"km/h" + "   |");
			}
			if (tabYahoo[nb_jours].getVitessevent().length()==2) {
				System.out.println("  "+ tabYahoo[nb_jours].getVitessevent()+"km/h" + "   |");
			}
			if (tabYahoo[nb_jours].getVitessevent().length()==3) {
				System.out.println("   "+ tabYahoo[nb_jours].getVitessevent()+"km/h" + "  |");
			}
		}
		//fin tableau 
		System.out.print("+-------------+");
		for (i=0; i< nb_jours ; i++) {
			System.out.print(tiretbaseh);
		}
		System.out.println(tiretbaseh);
	}
	
	
	//fichier
	
	public void PrintFichier(boolean del, String nom_fichier, DonneesMeteo[] tabMeta,DonneesMeteo[] tabPrevMeteo,DonneesMeteo[] tabYahoo,int nb_jours, int h, int w, String m) throws FileNotFoundException {
		if (nom_fichier.equals("") !=true ) {
			if (del==false) {
				File file = new File("DonneesApp/".concat(nom_fichier));
				System.setOut(new PrintStream(file));
				Affichage(tabMeta, tabPrevMeteo, tabYahoo, nb_jours, h, w, m);
			}
			if (del==true) {
				File rec = new File("DonneesApp/".concat(nom_fichier));
				if (rec.exists()== true) {
					FileOutputStream stream= new FileOutputStream(rec,true);
					System.setOut(new PrintStream(stream));
					Affichage(tabMeta, tabPrevMeteo, tabYahoo, nb_jours, h, w, m);
				}
				else {
					System.setOut(new PrintStream(new File("DonneesApp/".concat(nom_fichier))));
					Affichage(tabMeta, tabPrevMeteo, tabYahoo, nb_jours, h, w, m);
				}
			}
		}
	}

}