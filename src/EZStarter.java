import java.util.ArrayList;

import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.net.TcpipClient;

public class EZStarter {

	public static void usage() {
		System.err.println("Usage:" + EZStarter.class + " -p port -n playerNum -c clientClass [requestRole]");
	}

	public static void main(String[] args) {
		int port = 10000;
		int playerNum = 12;
		ArrayList<String> clsNameList = new ArrayList<String>();
		ArrayList<String> roleRequestList = new ArrayList<String>();

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				if (args[i].equals("-p")) {
					i++;
					port = Integer.parseInt(args[i]);
				} else if (args[i].equals("-n")) {
					i++;
					playerNum = Integer.parseInt(args[i]);
				} else if (args[i].equals("-c")) {
					i++;
					clsNameList.add(args[i]);
					i++;
					if (i > args.length - 1 || args[i].startsWith("-")) {
						i--;
						roleRequestList.add("none");
						continue;
					}
					roleRequestList.add(args[i]);
				} else if (args[i].equals("-h")) {
					usage();
					System.exit(0);
				}
			}
		}
		if (port < 0) {
			usage();
			System.exit(-1);
		}

		Thread th = new Thread(new EZServerStarter(port, playerNum));
		th.start();

		for (int i = 0; i < clsNameList.size(); i++) {
			Player player = null;
			try {
				player = (Player) Class.forName(clsNameList.get(i)).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			Role roleRequest;
			if (roleRequestList.get(i).equals("none")) {
				roleRequest = null;
			} else {
				roleRequest = Role.valueOf(roleRequestList.get(i));
			}
			TcpipClient client = new TcpipClient("localhost", port, roleRequest);
			if (client.connect(player)) {
				System.out.println("Player connected to server:" + player);
			}
		}
	}
}
