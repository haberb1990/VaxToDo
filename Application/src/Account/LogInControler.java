package Account;

/**
 * Classe permettant de g�rer la connexion d'un utilisateur
 * @author Benjamin Roy
 * @version 1.0
 */
public class LogInControler {

	private static Agent loggedAgent;
	
	/**
	 * M�thode qui r�cup�re un agent dans la liste des comptes d'agent
	 * � partir du code d'identifiant et du mot de passe pass� en 
	 * param�tre
	 * @author Benjamin Roy
	 * @param id Code d'identifiant � 9 chiffre
	 * @param password Mot de passe compos� d'au moins 8 caract�res contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caract�re sp�cial.
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
