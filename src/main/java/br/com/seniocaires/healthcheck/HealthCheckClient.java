package br.com.seniocaires.healthcheck;

import java.util.logging.Level;

/**
 * @author Senio Caires
 */
public class HealthCheckClient {

	/**
	 * @author Senio Caires
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Util.APPLOG.log(Level.INFO, "Health Check Client iniciado.");

		Checker checker = new Checker(Util.SLEEP);

		checker.start();
	}

}
