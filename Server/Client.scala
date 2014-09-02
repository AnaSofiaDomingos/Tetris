import java.io.IOException;
import java.net.{InetAddress, Socket}


object LaunchClient extends App{
	val ip = RecupIP
	val port = RecupPort
	
	try {
		// Client asking for a connection
		println(InetAddress.getLocalHost())
		val SocketC = new Socket(ip, port)	
		println("Asking for connection")

		// Client connected	
		import java.io.{BufferedReader, InputStreamReader}
		val in = new BufferedReader (new InputStreamReader (SocketC.getInputStream()))
                val Connection : String = in.readLine()
                println(Connection);

		// Client playing
		(new PlayingGame(SocketC)).run
		

		// Client closing
		SocketC.close();	
	} catch {
		case e: Exception => println("Exception caught: " + e)
	}
	
	def RecupIP() = "129.194.184.109"
	def RecupPort() = 2014
}

class PlayingGame(s : Socket) extends Runnable{
	def run(){
		println("Clients are playing")
		val args  = Array("1", "2")
		Tetris.startup(args)
	
	}
}


