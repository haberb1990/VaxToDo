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
 * Classe permettant de g�rer les r�servations en interagissant 
 * avec la base de donn�es
 * @author Hani Berchan
 * @version 1.0
 */
public class ReservationControler {

	private static List<Reservation> reservations = new ArrayList<Reservation>();
	private static Calendar calendar;
	/**
	 * M�thode qui initialise la liste des r�servations en r�cup�rant tous les comptes existant
	 * dans la base de donn�es.
	 * @author Benjamin Roy
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws ParseException Signal une erreur si le parse �choue
	 */
	public static void initializer() throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		reservations = CsvReservation.readReservationDetails(".\\csv\\RSVPDatabase.csv");
	}
	
	/**
	 * M�thode qui ajoute une r�servation � la liste
	 * @author Benjamin Roy
	 * @param reservation R�servation � ajouter
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean addReservation(Reservation reservation) {
		return reservations.add(reservation);
	}
	
	/**
	 * M�thode qui r�cup�re une r�servation pr�sente dans la liste
	 * @author Benjamin Roy
	 * @param reservationNumber Num�ro de r�servation � 12 chiffres
	 * @return Une r�servation associ� au num�ro de r�servation.
	 */
	public static Reservation getReservation(String reservationNumber) {
		return reservations.stream().filter(reservation -> 
			reservation.getReservationNumber().equals(reservationNumber)
		).findAny().orElse(null);
	}
	
	/**
	 * M�thode qui supprime une r�servation pr�sente dans la liste
	 * @author Benjamin Roy
	 * @param reservationNumber Num�ro de r�servation � 12 chiffres
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean cancelReservation(String reservationNumber) {
		Reservation reservation = getReservation(reservationNumber);
		return reservations.remove(reservation);
	}
	
	public static Calendar getCalendar() {
		return calendar;
		
	}
	/**
	 * M�thode qui r�cup�re une r�servation pr�sente dans la liste
	 * @param date Date (YYYY-MM-DD)
	 * @param time Heure (HH:mm)
	 * @return Une r�servation associ� � la date et heure.
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
