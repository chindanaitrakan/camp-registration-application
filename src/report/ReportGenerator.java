package report;

import camps.CampList;
import models.User;

/**
 * Interface for reports
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public interface ReportGenerator {
	/**
	 * Abstract method to be overriden with different reports to be generated.
	 * @param user The user generating the report.
	 * @param campList The list of camps.
	 */
	public void generate(User user, CampList campList);

}
