package br.com.seniocaires.healthcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimerTask;
import java.util.logging.Level;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

/**
 * @author Senio Caires
 */
public class CheckerTask extends TimerTask {

	/**
	 * @author Senio Caires
	 */
	public void run() {
		for (String host : Util.HOSTS) {
			try {
				URL url = new URL(host);
				HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

				conexao.setRequestMethod("GET");

				int responseCode;

				responseCode = conexao.getResponseCode();

				Util.APPLOG.log(Level.FINEST, "Request para " + host + " - Response Code: " + responseCode);

				if (responseCode != 200) {
					BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					Util.APPLOG.log(Level.SEVERE, response.toString());
					notificacaoSlack("Olá. Parece que " + host + " está offline. \n Talvez esta mensagem de erro possa ajudar: " + responseCode + " - " + response.toString());
				}

			} catch (ConnectException e) {
				notificacaoSlack("Olá. Parece que " + host + " está offline. \n Talvez esta mensagem de erro possa ajudar: ConnectException " + e.getMessage());
				Util.APPLOG.log(Level.SEVERE, e.getMessage(), e);
			} catch (IOException e) {
				notificacaoSlack("Olá. Parece que " + host + " está offline. \n Talvez esta mensagem de erro possa ajudar: IOException " + e.getMessage());
				Util.APPLOG.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

	/**
	 * @author Senio Caires
	 * @param mensagem
	 */
	private void notificacaoSlack(String mensagem) {
		SlackSession sessao = SlackSessionFactory.createWebSocketSlackSession(Util.SLACK_TOKEN);
		try {
			sessao.connect();
			for (String canal : Util.SLACK_CANAIS) {
				SlackChannel channel = sessao.findChannelByName(canal);
				sessao.sendMessage(channel, mensagem);
			}
		} catch (IOException ioException) {
			Util.APPLOG.log(Level.SEVERE, ioException.getMessage(), ioException);
		}
	}
}
