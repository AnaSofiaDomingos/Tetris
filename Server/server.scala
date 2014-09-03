import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import Runnable._
import Thread._


object LaunchServer extends App {
	val s = new Server(2014)
	s.start	
}

class Server(port:Int) extends Thread {
	
	import java.net.InetAddress
        val SAddress = InetAddress.getLocalHost()
        println("\nThe server address is : "+SAddress )

        ###
	var NbClientConnect : Int = 1

	override def run() = {
		try {
			while (true)
				if (NbClientConnect < 3){
			
					// Server waiting for a connection
					val SocketS = new ServerSocket(port)
					println("Server listening on "+ SocketS.getLocalPort())
			      		
					// Server accepting the client	
					val SocketConnect = SocketS.accept()
					println("Client " + NbClientConnect + " asking for connection")

					// Server giving answer to client
					import java.io.PrintWriter
					val out = new PrintWriter(SocketConnect.getOutputStream())	
					out.println(NbClientConnect)
					out.flush()			
					println("Client " + NbClientConnect + " connected!")


					// Server doing something
					import java.io.{BufferedReader, InputStreamReader}
					val in = new BufferedReader (new InputStreamReader (SocketConnect.getInputStream()))
					val test : String = in.readLine()
					println(test)

					// Server closing
					out.close()
					SocketConnect.close()
					SocketS.close()

					###
					NbClientConnect += 1
				}
		}catch {
			case e: Exception => println("Exception caught: " + e)
		}	
	}

	def ### = println("##################################################\n")

	
}



