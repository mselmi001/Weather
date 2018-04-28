package gestion;

import weather.MyException;

public class GestionArg  {
	int nb_jours =4;
	int h=0;
	int w=0;
	boolean del=false;
	String nom_fichier ="";
	String m="°";
	
	public int getNb_jours() {
		return nb_jours;
	}
	public void setNb_jours(int nb_jours) {
		this.nb_jours = nb_jours;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public String getNom_fichier() {
		return nom_fichier;
	}
	public void setNom_fichier(String nom_fichier) {
		this.nom_fichier = nom_fichier;
	}
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public void setArgs(String [] args) throws MyException{
		int i;
		for (i=0 ; i<args.length; i++) {
			if (args[i].equals("-j" )) {
				if(Integer.parseInt(args[i+1])<0) {
					throw new MyException(args[i]);
				}
				setNb_jours(Integer.parseInt(args[i+1]));
			}
			if (args[i].equals("-h" )) {
				setH(1);
			}
			if (args[i].equals("-m" )) {
				if((args[i+1].equals("C") != true)&& (args[i+1].equals("F") != true) ){
					throw new MyException(args[i]);
				}
				setM(args[i+1]);
				if (m.equals("C")) {
					setM("°");
				}
			}
			if (args[i].equals("-w" )) {
				setW(1);
			}
			if (args[i].equals("-o")) {
				setNom_fichier(args[i+1]);
			}
			if (args[i].equals("-a")) {
				setDel(true);
				setNom_fichier(args[i+1]);
			}
					
		}	
	}
	
	

}