package lib;
import java.io.IOException;

import csv.CsvReservation;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import csv.CsvClient;
/**
 * Classe permettant de gérer les réservations en interagissant 
 * avec la base de données
 * @author Hani Berchan
 * @version 1.0
 */
public class ReservationControler {

	private static List<Reservation> reservations = new ArrayList<Reservation>();
	private static Calendar calendar;
	/**
	 * Méthode qui initialise la liste des réservations en récupérant tous les comptes existant
	 * dans la base de données.
	 * @author Benjamin Roy
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static void initializer() throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		reservations = CsvReservation.readReservationDetails(".\\csv\\RSVPDatabase.csv");
	}
	
	/**
	 * Méthode qui ajoute une réservation à la liste
	 * @author Benjamin Roy
	 * @param reservation Réservation à ajouter
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean addReservation(Reservation reservation) {
		return reservations.add(reservation);
	}
	
	/**
	 * Méthode qui récupère une réservation présente dans la liste
	 * @author Benjamin Roy
	 * @param reservationNumber Numéro de réservation à 12 chiffres
	 * @return Une réservation associé au numéro de réservation.
	 */
	public static Reservation getReservation(String reservationNumber) {
		return reservations.stream().filter(reservation -> 
			reservation.getReservationNumber().equals(reservationNumber)
		).findAny().orElse(null);
	}
	
	/**
	 * Méthode qui supprime une réservation présente dans la liste
	 * @author Benjamin Roy
	 * @param reservationNumber Numéro de réservation à 12 chiffres
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean cancelReservation(String reservationNumber) {
		Reservation reservation = getReservation(reservationNumber);
		return reservations.remove(reservation);
	}
	
	public static Calendar getCalendar() {
		return calendar;
		
	}
	/**
	 * Méthode qui récupère une réservation présente dans la liste
	 * @param date Date (YYYY-MM-DD)
	 * @param time Heure (HH:mm)
	 * @return Une réservation associé à la date et heure.
	 */
	public static List<Reservation> getReservations(String date, String time) {
		List<Reservation> reservationsFiltered = new ArrayList<Reservation>();
		for(Reservation reservation : reservations) {
			if(reservation.getVisitDate().equals(date) && reservation.getTime().equals(time)) {
				reservationsFiltered.add(reservation);
			}
		}
		return reservationsFiltered;
	}
}
