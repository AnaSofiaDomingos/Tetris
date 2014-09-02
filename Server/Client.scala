import java.io.IOException;
import java.net.{InetAddress, Socket}


object Client extends App{
	try {
		// Client asking for a connection
		val SocketC = new Socket(InetAddress.getLocalHost(), 2014)	
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



}

class PlayingGame(s : Socket) extends Runnable{
	def run(){
		println("Clients are playing")
		val args  = Array("1", "2")
		Grid.startup(args)
	
	}
}
