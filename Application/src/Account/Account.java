package Account;
import java.util.Random;
import lib.Validator;
import com.opencsv.bean.CsvBindByName;


/**
 * Classe permettant de créer un compte, de modifier ses attributs et
 * d'envoyé un courriel de confirmation.
 * @author Benjamin Roy
 * @version 1.0
 */
public class Account {
	//Attributes
	@CsvBindByName(column = "accountNumber")
	private String accountNumber;
	@CsvBindByName(column = "firstName")
	private String firstName;
	@CsvBindByName(column = "lastName")
	private String lastName;
	@CsvBindByName(column = "birthDate")
	private String birthDate;
	@CsvBindByName(column = "email")
	private String email;
	@CsvBindByName(column = "phone")
	private String phone;
	@CsvBindByName(column = "adress")
	private String address;
	@CsvBindByName(column = "zipCode")
	private String zipCode;
	@CsvBindByName(column = "city")
	private String city;
	
	
	//Getters and Setters
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Constructeur permettant de créer un nouveau compte avec un numéro de compte
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
	 */
	public Account(String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city) {
		generateAccountNumber();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		validate();
	}
	
	/**
	 * Constructeur permettant de créer un compte avec un numéro de compte
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
	 */
	public Account(String accountNumber, String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city) {
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	/**
	 * Méthode permettant de générer un numéro de compte à 12 chiffre aléatoirement.
	 * @author Benjamin Roy
	 */
	private void generateAccountNumber() {
		Random rand = new Random();
		accountNumber = "";
		for(int i = 0; i < 12; i++) {
			accountNumber += rand.nextInt(10);
		}
	}
	
	
	/**
	 * Méthode permettant de valider les différents attributs de la classe.
	 * @author Benjamin Roy
	 * @throws Error si un ou plusieurs attributs n'est pas sous un bon format.
	 */
	private void validate() {
		String message = "";
		if(!Validator.match("^[a-zA-ZÀ-ý '-]{1,50}$", firstName)) {
			message += "Le prénom n'est pas valide.\n";
		}
		if(!Validator.match("^[a-zA-ZÀ-ý '-]{1,50}$", lastName)) {
			message += "Le nom n'est pas valide.\n";
		}
		if(!Validator.match("^[0-9]{4}-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", birthDate)) {
			message += "La date n'est pas valide.";
		}
		if(!Validator.match("^[A-Z0-9.]+@[A-Z0-9]+\\.[A-Z0-9.]+", email)) {
			message += "Le email n'est pas valide.\n";
		}
		if(!Validator.match("^[0-9]{10}$", phone)) {
			message += "Le numéro de téléphone doit contenir exactement 10 caractère.\n";
		}
		if(address.length() > 100) {
			message += "L'adresse peut contenir 100 caractère maximum\n";
		}
		if(zipCode.length() != 6) {
			message += "Le code postal doit contenir exactement 6 caractère\n";
		}
		if(city.length() > 100) {
			message += "La ville peut contenir 50 caractère maximum\n";
		}
		
		if(message.length() > 0) {
			throw new Error(message);
		}
	}
	
	
	/**
	 * Méthode permettant d'envoyé un courriel de confirmation de création de compte
	 * au courriel du compte.
	 * @author Benjamin Roy
	 * @return Un String de message de succès
	 */
	public String sendAccountNumber() {
		return "Le courriel a été envoyé à: " + email + " avec succès!";
	}
	
}
