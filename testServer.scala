/* Test serveur */

import java.net.{ServeurSocket, Socket}
import java.io._
import ressource._ // manage ressources

class Server extends Thread {
	override def run() : Unit = {
		for {
			server <- managed(new ServerSocket())		
		}	
	}
}
