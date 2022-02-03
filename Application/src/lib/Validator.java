package lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe permettant de valider si une valeur est bonne ou non selon certaines conditions
 * @author Benjamin Roy
 * @version 1.0
 */
public class Validator {

	/**
	 * Méthode permettant de valider le format d'un String à partir 
	 * d'une expression régulière
	 * @author Benjamin Roy
	 * @param regex L'expression régulière
	 * @param value Le String a valider
	 * @return Un Boolean indiquant si la valeur est accepté
	 */
	public static boolean match(String regex, String value) {
		if(value == null || regex == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(value);
	    return matcher.find();
	}
	
	/**
	 * Méthode permettant de vérifier si un int est entre 2 valeur inclusive
	 * @author Benjamin Roy
	 * @param value Le int a vérifier
	 * @param min La borne inférieur
	 * @param max La borne supérieur
	 * @return Un Boolean indiquant si la valeur est accepté
	 */
	public static boolean matchInt(int value, int min, int max) {
		return value >= min && value <= max;
	}
}
