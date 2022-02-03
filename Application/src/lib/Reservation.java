package lib;
import java.util.Random;

import com.opencsv.bean.CsvBindByName;

/**
 * Classe permettant de créer une réservation pour une visite planifiée et
 * une visite spontanée, de modifier ses attributs et d'envoyer une notification.
 * @author Benjamin Roy et Hani Berchan
 * @version 1.0
 */
public class Reservation {

	@CsvBindByName(column = "accountNumber")
	private String reservationNumber;
	@CsvBindByName(column = "fisrtName")
	private String firstName;
	@CsvBindByName(column = "lastName")
	private String lastName;
	@CsvBindByName(column = "visitDate")
	private String visitDate;
	@CsvBindByName(column = "time")
	private String time;
	@CsvBindByName(column = "dose")
	private int dose;
	@CsvBindByName(column = "email")
	private String email;
	
	public String getVisitDate() {
		return visitDate;
	}


	public String getTime() {
		return time;
	}

	public String getReservationNumber() {
		return reservationNumber;
	}
	
	/**
	 * Constructeur permettant de créer une nouvelle réservation et valide
	 * si les attributs sont sous un format valide.
	 * @author Benjamin Roy
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param visitDate Date de visite (YYYY-MM-DD)
	 * @param time Heure de visite
	 * @param dose La dose (1 ou 2)
	 * @param email le courriel du visiteur
	 */
	public Reservation(String firstName, String lastName, String visitDate, 
			String time, int dose, String email) {
		generateReservationNumber();
		this.firstName = firstName;
		this.lastName = lastName;
		this.visitDate = visitDate;
		this.time = time;
		this.dose = dose;
		this.email = email;
		validate();
		sendNotification();
	}
	/**
	 * Constructeur permettant de créer une nouvelle réservation et valide
	 * si les attributs sont sous un format valide.
	 * @author Benjamin Roy
	 * @param reservationNumber Numéro de réservation
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param visitDate Date de visite (YYYY-MM-DD)
	 * @param time Heure de visite
	 * @param dose La dose (1 ou 2)
	 * @param email le courriel du visiteur
	 */
	public Reservation(String reservationNumber, String firstName, String lastName, String visitDate, String time,
			int dose, String email) {
		this.reservationNumber = reservationNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.visitDate = visitDate;
		this.time = time;
		this.dose = dose;
		this.email = email;
	}

	/**
	 * Méthode permettant de valider les différents attributs de la classe.
	 * @author Benjamin Roy et Paul Nguimeya
	 * @throws Error si un ou plusieurs attributs n'est pas sous un bon format.
	 */
	public void validate() {
		String message = "";
		if(!Validator.match("^[a-zA-ZÀ-ý '-]{1,50}$", firstName)) {
			message += "Le prénom n'est pas valide.\n";
		}
		if(!Validator.match("^[a-zA-ZÀ-ý '-]{1,50}$", lastName)) {
			message += "Le nom n'est pas valide.\n";
		}
		if(!Validator.match("^[0-9]{4}-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", visitDate)) {
			message += "La date n'est pas valide.";
		}
		if(!Validator.match("^(0[8-9]|1[0-7]):00$", time)) {
			message += "L'heure choisit n'est pas valide.\n";
		}
		if(!Validator.matchInt(dose, 1, 2)) {
			message += "Le choix de dose n'est pas valide (1 ou 2).\n";
		}
		if(!Validator.match("^[A-Z0-9.]+@[A-Z0-9]+\\.[A-Z0-9.]+", email)) {
			message += "Le email n'est pas valide.\n";
		}
		if(message.length() > 0) {
			throw new Error(message);
		}
	}
	/**
	 * Méthode permettant d'envoyé une notification de création d'une
	 * réservation.
	 * @author Benjamin Roy
	 * @return Un String de message de succès
	 */
	public String sendNotification() {
		return "Le courriel de réservation a été envoyé avec succès!";
	}
	/**
	 * Méthode permettant de générer un numéro de réservation 
	 * à 12 chiffre aléatoirement.
	 * @author Benjamin Roy
	 */
	private void generateReservationNumber() {
		Random rand = new Random();
		reservationNumber = "";
		for(int i = 0; i < 12; i++) {
			reservationNumber += rand.nextInt(10);
		}
	}
	
	@Override
	public String toString() {
		return "#" + reservationNumber + ", " + firstName + " " + lastName + ", visit=" + visitDate + ", heure=" + time + ", dose=" + dose + ", email=" + email;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getEmail() {
		return email;
	}


	public int getDose() {
		return dose;
	}
}
