package Account;
import java.util.Random;
import com.opencsv.bean.CsvBindByName;
import lib.Validator;

/**
 * Classe permettant de créer un compte agent et de modifier ses attributs.
 * @author Benjamin Roy
 * @version 1.0
 */
public class Agent extends Account {
	//Attributes
	@CsvBindByName(column = "id")
	private int id;
	@CsvBindByName(column = "password")
	private String password;
	
	
	public int getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}

	
	/**
	 * Constructeur permettant de créer un nouveau compte agent avec un numéro de compte
	 * générer aléatoirement puis valide si les attributs sont sous un format valide.
	 * @author Benjamin Roy
	 * @throws Error si un ou plusieurs attributs n'est pas sous un bon format.
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Numéro de téléphone à 10 chiffre
	 * @param address L'addresse 
	 * @param zipCode Code postal, sans espace, 6 caractères
	 * @param city Nom de la ville
	 * @param password Mot de passe composé d'au moins 8 caractères contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caractère spécial.
	 */
	public Agent(String firstName, String lastName, String birthDate, String email, String phone, 
			String address, String zipCode, String city, String password) {
		super(firstName, lastName, birthDate, email, phone, address, zipCode, city);
		generateID();
		this.password = password;
		validate();
	}

	/**
	 * Constructeur permettant de créer un compte agent avec un numéro de compte
	 * déjà existant.
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte à 12 chiffre
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Numéro de téléphone à 10 chiffre
	 * @param address Addresse de domicile du client
	 * @param zipCode Code postal, sans espace, 6 caractères
	 * @param city Nom de la ville
	 * @param id Code d'identification à 9 chiffre
	 * @param password Mot de passe composé d'au moins 8 caractères contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caractère spécial.
	 */
	public Agent(String accountNumber, String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city, int id, String password) {
		super(accountNumber, firstName, lastName, birthDate, email, phone, address, zipCode, city);
		this.id = id;
		this.password = password;
	}

	/**
	 * Méthode permettant de générer un code d'identifiant à 9 chiffre aléatoire.
	 * @author Benjamin Roy
	 */
	private void generateID() {
		Random rand = new Random();
		id = 100000000 + rand.nextInt(900000000);
	}
	
	
	/**
	 * Méthode permettant de valider les différents attributs de la classe.
	 * @author Benjamin Roy
	 * @throws Error si un ou plusieurs attributs n'est pas sous un bon format.
	 */
	private void validate() {
		String message = "";
		if(!Validator.matchInt(id, 100000000, 999999999)) {
			message += "Le code d'identification doit contenir exactement 9 chiffre\n";
		}
		if(!Validator.match("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W)[a-zA-Z0-9\\W]{8,}" , password)) {
			message += "Le mot de passe doit contenir au moins 1 chiffre, 1 majuscule, " +
					"1 minuscule et 1 caractère spécial.\n";
		}
		if(message.length() > 0) {
			throw new Error(message);
		}
	}
}
