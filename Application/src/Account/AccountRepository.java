package Account;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import csv.CsvEmployee;

/**
 * Classe permettant de g�rer les comptes agents en interagissant avec la base de donn�es
 * @author Benjamin Roy
 * @version 1.0
 */
public class AccountRepository {

	private static List<Agent> agents = new ArrayList<Agent>();
	
	
	/**
	 * M�thode qui initialise la liste des agents en r�cup�rant tout les empoy�s existant
	 * dans la base de donn�es.
	 * @author Benjamin Roy
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws ParseException Signal une erreur si le parse �choue
	 */
	public static void initializer() throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		agents = CsvEmployee.readEmployeeDetails(".\\csv\\employeeDatabase.csv");
	}
	
	/**
	 * M�thode qui v�rifie si un agents existe.
	 * @author Benjamin Roy
	 * @param id Le code d'iditifiant d'un agent � 9 chiffre
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean hasAgent(int id) {
		return agents.stream().anyMatch(agent -> agent.getId() == id);
	}
	
	/**
	 * M�thode qui r�cup�re un agent associ� au code d'identifiant et du mot de passe
	 * pass� en param�tre.
	 * @author Benjamin Roy
	 * @param id Code d'identifiant � 9 chiffre
	 * @param password Mot de passe compos� d'au moins 8 caract�res contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caract�re sp�cial.
	 * @return Un agent
	 */
	public static Agent getAgent(int id, String password) {
		return agents.stream().filter(agent -> 
			agent.getId() == id && agent.getPassword().equals(password)
		).findAny().orElse(null);
	}

	/**
	 * M�thode qui ajoute un agent � la liste
	 * @author Benjamin Roy
	 * @param account Agent a ajouter
	 * @return Un boolean qui indique le succ�s de l'op�ration. 
	 */
	protected static boolean addAgent(Agent account) {
		return agents.add(account);
	}

	/**
	 * M�thode qui supprimer un b�n�vole de la liste
	 * @author Benjamin Roy
	 * @param account B�n�vole a Supprimer
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	protected static boolean deleteAgent(Volunteer account) {
		return agents.remove(account);
	}
}
