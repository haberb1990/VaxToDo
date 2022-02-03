package Account;

/**
 * Classe permettant de gérer la connexion d'un utilisateur
 * @author Benjamin Roy
 * @version 1.0
 */
public class LogInControler {

	private static Agent loggedAgent;
	
	/**
	 * Méthode qui récupère un agent dans la liste des comptes d'agent
	 * à partir du code d'identifiant et du mot de passe passé en 
	 * paramètre
	 * @author Benjamin Roy
	 * @param id Code d'identifiant à 9 chiffre
	 * @param password Mot de passe composé d'au moins 8 caractères contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caractère spécial.
	 * @return Un agent
	 */
	public static Agent login(int id, String password) {
		loggedAgent = AccountRepository.getAgent(id, password);
		return loggedAgent;
	}

	public static Agent getLoggedAgent() {
		return loggedAgent;
	}
}
