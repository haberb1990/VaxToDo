package lib;

import com.opencsv.bean.CsvBindByName;

import Account.ClientControler;
/**
 * Classe permettant de créer un profil de vaccination, de modifier ses attributs et
 * d'envoyer le fichier pdf incluant le code QR par courriel au client.
 * @author Benjamin Roy et Hani Berchan
 * @version 1.0
 */
public class VaccinationProfile {
	@CsvBindByName(column = "accountNumber")
	private String accountNumber;
	@CsvBindByName(column = "vaccinationDate")
	private String vaccinationDate;
	@CsvBindByName(column = "dose")
	private int dose;
	@CsvBindByName(column = "vaccineName")
	private Vaccine vaccineName;
	@CsvBindByName(column = "vaccineCode")
	private String vaccineCode;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getVaccinationDate() {
		return vaccinationDate;
	}

	public void setVaccinationDate(String vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	public Vaccine getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(Vaccine vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getVaccineCode() {
		return vaccineCode;
	}

	public void setVaccineCode(String vaccineCode) {
		this.vaccineCode = vaccineCode;
	}
	
	/**
	 * Constructeur permettant de créer un nouveau profil de vaccination 
	 * et valide si les attributs sont sous un format valide.
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte du client
	 * @param vaccinationDate La date de vaccination (YYYY-MM-DD)
	 * @param dose Le numéro de dose (1 ou 2)
	 * @param vaccineName Le nom du vaccin
	 * @param vaccineCode Le code du vaccin, limite de 24 caractères
	 */
	public VaccinationProfile(String accountNumber, String vaccinationDate, int dose, Vaccine vaccineName,
			String vaccineCode) {
		super();
		this.accountNumber = accountNumber;
		this.vaccinationDate = vaccinationDate;
		this.dose = dose;
		this.vaccineName = vaccineName;
		this.vaccineCode = vaccineCode;
		validate();
	}
	
	/**
	 * Méthode permettant de valider les différents attributs de la classe.
	 * @author Benjamin Roy et Hani Berchan
	 * @throws Error si un ou plusieurs attributs n'est pas sous un bon format.
	 */
	public void validate() {
		String message = "";
		if(!Validator.match("^[0-9]{12}$", accountNumber)) {
			message += "Le numéro de compte doit contenir 12 chiffre.\n";
		}
		if(ClientControler.getClient(accountNumber) == null) {
			message += "Le compte n'existe pas.\n"; 
		}
		if(!Validator.match("^[0-9]{4}-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", vaccinationDate)) {
			message += "La date n'est pas valide.";
		}
		if(!Validator.matchInt(dose, 1, 2)) {
			message += "Le choix de dose n'est pas valide (1 ou 2).\n";
		}
		if(vaccineName == null) {
			message += "Vous devez choisir l'un des vaccin disponible.\n";
		}
		if(!Validator.match("^.{1,24}", vaccineCode)) {
			message += "La code du vaccin n'est pas valide. Limite de 24 caractères.\n";
		}
		if(message.length() > 0) {
			throw new Error(message);
		}
	}
	
	/**
	 * Méthode permettant l'envoie de la preuve de vaccination
	 * @author Benjamin Roy
	 * @param email courriel du client
	 * @return un String d'envoie du rapport de vaccination par courriel
	 */
	public String sendProofOfVaccination(String email) {
		return "Le rapport de vaccination est envoyé à : " + email;
	}

	@Override
	public String toString() {
		return "#" + accountNumber + ", " + vaccinationDate + ", dose=" + dose + ", vaccin=" + vaccineName;
	}
	
	
}
