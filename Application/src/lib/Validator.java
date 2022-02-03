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
	 * M�thode permettant de valider le format d'un String � partir 
	 * d'une expression r�guli�re
	 * @author Benjamin Roy
	 * @param regex L'expression r�guli�re
	 * @param value Le String a valider
	 * @return Un Boolean indiquant si la valeur est accept�
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
	 * M�thode permettant de v�rifier si un int est entre 2 valeur inclusive
	 * @author Benjamin Roy
	 * @param value Le int a v�rifier
	 * @param min La borne inf�rieur
	 * @param max La borne sup�rieur
	 * @return Un Boolean indiquant si la valeur est accept�
	 */
	public static boolean matchInt(int value, int min, int max) {
		return value >= min && value <= max;
	}
}
