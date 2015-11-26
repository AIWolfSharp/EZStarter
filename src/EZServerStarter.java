import java.io.IOException;
import java.util.Random;

import org.aiwolf.common.net.GameSetting;
import org.aiwolf.server.AIWolfGame;
import org.aiwolf.server.net.TcpipServer;

public class EZServerStarter implements Runnable {
	private int port;
	private int playerNum;

	public EZServerStarter(int port, int playerNum) {
		this.port = port;
		this.playerNum = playerNum;
	}

	@Override
	public void run() {
		System.out.printf("Start AiWolf Server port:%d playerNum:%d\n", port, playerNum);
		GameSetting gameSetting = GameSetting.getDefaultGame(playerNum);
		TcpipServer gameServer = new TcpipServer(port, playerNum, gameSetting);
		try {
			gameServer.waitForConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AIWolfGame game = new AIWolfGame(gameSetting, gameServer);
		game.setRand(new Random());
		game.start();
	}
}
