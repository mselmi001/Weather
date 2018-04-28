package weather;

@SuppressWarnings("serial")
public class MyException extends Exception {
	public MyException(String arg) {
		System.out.println("Votre argument "+ arg + " est faux");
	}
}
