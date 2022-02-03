package lib;

import java.awt.Color;
import java.util.Date;

import com.toedter.calendar.IDateEvaluator;

/**
 * Cette classe permet d'évaluer une date
 * @author Benjamin Roy
 * @version 1.0
 */
public class DateEvaluator implements IDateEvaluator {

	@Override
	public Color getInvalidBackroundColor() {
		return null;
	}

	@Override
	public Color getInvalidForegroundColor() {
		return null;
	}

	@Override
	public String getInvalidTooltip() {
		return null;
	}

	@Override
	public Color getSpecialBackroundColor() {
		return null;
	}

	@Override
	public Color getSpecialForegroundColor() {
		return null;
	}

	@Override
	public String getSpecialTooltip() {
		return null;
	}

	/**
	 * Méthode qui indique si la date est invalide. Elle est invalide
	 * si c'est un samedi ou un dimanche.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean isInvalid(Date date) {
		boolean isSunday = date.getDay() == 0;
		boolean isSaturday = date.getDay() == 6;
		return isSunday || isSaturday;
	}

	@Override
	public boolean isSpecial(Date date) {
		return false;
	}

}
