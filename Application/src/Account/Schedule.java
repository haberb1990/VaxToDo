package Account;

/**
 * Classe permettant de créer une plage horaire
 * @author Benjamin Roy
 * @version 1.0
 */
public class Schedule {

	
	private String date;
	private String startTime;
	private String endTime;
	
	
	/**
	 * Constructeur permettant de créer une plage horaire
	 * @author Benjamin Roy
	 * @param date Date (YYYY-MM-DD)
	 * @param startTime Heure de début de la plage horaire (HH:mm)
	 * @param endTime Heure de fin de la plage horaire (HH:mm)
	 */
	public Schedule(String date, String startTime, String endTime) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}