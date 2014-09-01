import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import Runnable._
import swing._

class Server(port:Int) extends Runnable {
	
	import java.net.InetAddress
        val SAddress = InetAddress.getLocalHost()
        println("\nThe server address is : "+SAddress )

        println("##################################################\n")
	var NbClientConnect : Int = 1

	def run(){
		try {
			while (true)
				if (NbClientConnect < 3){
			
					// Server waiting for a connection
					val SocketS = new ServerSocket(port);
					println("Server listening on "+ SocketS.getLocalPort())
			      		
					// Server accepting the client	
					val SocketConnect = SocketS.accept()
					println("Client " + NbClientConnect + " asking for connection")

					// Server giving answer to client
					import java.io.PrintWriter
					val out = new PrintWriter(SocketConnect.getOutputStream())
					out.println("You are connected to the Server")
					out.flush();				
					println("Client " + NbClientConnect + " connected!")

					// Server doing something
					(new PlayingGame(SocketConnect)).run

					// Server closing
					out.close()
					SocketConnect.close()
					SocketS.close()

					println("##################################################\n")
					NbClientConnect += 1
				
				}
		}catch {
			case e: Exception => println("Exception caught: " + e)
		}	
	}
}


class PlayingGame(s : Socket) extends Runnable{
	def run(){
		println("Clients are playing")
		import java.io.{BufferedReader, InputStreamReader}
		val in = new BufferedReader (new InputStreamReader (s.getInputStream()));
                val opStr : String = in.readLine() 
		val op = opStr match {

			case "left" => println("left")
			case "right" => println("right")
			case "down" => println("down")
			case "rotate" => println("rotate")			
		}
		
	
	}
}

