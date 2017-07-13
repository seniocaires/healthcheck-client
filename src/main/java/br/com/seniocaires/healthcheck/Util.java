package br.com.seniocaires.healthcheck;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Senio Caires
 */
public class Util {

	/**
	 * @author Senio Caires
	 */
	public static final String[] HOSTS = System.getenv("HOSTS").split(",");

	/**
	 * @author Senio Caires
	 */
	public static final String[] SLACK_CANAIS = System.getenv("SLACK_CANAIS").split(",");

	/**
	 * @author Senio Caires
	 */
	public static final String SLACK_TOKEN = System.getenv("SLACK_TOKEN");

	/**
	 * @author Senio Caires
	 */
	public static final int SLEEP = Integer.valueOf(System.getenv("SLEEP"));

	public static final Logger APPLOG = Logger.getGlobal();

	static {
		Handler systemOut = new ConsoleHandler();
		systemOut.setLevel(Level.INFO);
		APPLOG.addHandler(systemOut);
		APPLOG.setLevel(Level.INFO);
		APPLOG.setUseParentHandlers(false);
	}

	/**
	 * @author Senio Caires
	 */
	private Util() {
		// classe utilitária
	}
}
