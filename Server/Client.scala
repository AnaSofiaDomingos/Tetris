import java.io.IOException;
import java.net.{InetAddress, Socket}

object Client extends App{
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
	        val NbConnectionStr : String = in.readLine()
	       	val NbConnection : Int = NbConnectionStr.toInt

		// Client playing

		(new GiveGrid(SocketC)).run

		if (NbConnection == 2 ){
			println("play")
		}else{
			println("Wait for a second player")
		}
		
	

		// Client closing
		SocketC.close();	
	} catch {
		case e: Exception => println("Exception caught: " + e)
	}
	
	def RecupIP() = "129.194.184.109"
	def RecupPort() = 2014

	
}

class GiveGrid(s : Socket) extends Runnable{
	def run(){
		println("Clients are playing")
		val args  = Array("1", "2")
		Grid.startup(args)
	
	}
}


