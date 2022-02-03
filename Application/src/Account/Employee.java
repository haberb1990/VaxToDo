package Account;

/**
 * Classe permettant de cr�er un compte employ�.
 * @author Benjamin Roy
 * @version 1.0
 */
public class Employee extends Agent {

	/**
	 * Constructeur permettant de cr�er un compte employ� avec un 
	 * num�ro de compte d�j� existant.
	 * @author Benjamin Roy
	 * @param accountNumber Num�ro de compte � 12 chiffre
	 * @param firstName Pr�nom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Num�ro de t�l�phone � 10 chiffre
	 * @param address Addresse de domicile du client
	 * @param zipCode Code postal, sans espace, 6 caract�res
	 * @param city Nom de la ville
	 * @param id Code d'identification � 9 chiffre
	 * @param password Mot de passe compos� d'au moins 8 caract�res contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caract�re sp�cial.
	 */
	public Employee(String accountNumber, String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city, int id, String password) {
		super(accountNumber, firstName, lastName, birthDate, email, phone, address, zipCode, city, id, password);
	}
}
