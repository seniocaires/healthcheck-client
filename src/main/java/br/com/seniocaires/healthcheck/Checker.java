package br.com.seniocaires.healthcheck;

import java.util.Timer;

/**
 * @author Senio Caires
 */
public class Checker {

	/**
	 * @author Senio Caires
	 */
	private Timer timer;

	/**
	 * @author Senio Caires
	 */
	private int segundos;

	/**
	 * @param segundos - Intervalo
	 * @author Senio Caires
	 */
	public Checker(int segundos) {
		timer = new Timer();
		this.segundos = segundos;
	}

	/**
	 * @author Senio Caires
	 */
	public void start() {
		timer.scheduleAtFixedRate(new CheckerTask(), 0, segundos * 1000);
	}

	/**
	 * @author Senio Caires
	 */
	public void stop() {
		timer.cancel();
	}
}
