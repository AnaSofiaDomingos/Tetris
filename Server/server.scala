import java.io.IOException
import java.net.{ServerSocket, Socket}
import Thread._
import java.io.{BufferedReader, InputStreamReader, PrintWriter}


object LaunchServer extends App {
	val s = new Server(2014)
	s.start()	
}

class Server(port:Int) extends Thread {
	
	import java.net.InetAddress
        val SAddress = InetAddress.getLocalHost()
        println("\nThe server address is : "+SAddress )

        ###
	var NbClientConnect : Int = 1

	
	// Server waiting for a connection
	val SocketS = new ServerSocket(port)
	println("Server listening on "+ SocketS.getLocalPort())
	override def run() = {

		try {
			while (true){
				val game = new Game
		      		
				// Server accepting the client	
				val Player1 = new game.Player(SocketS.accept(), "1")
				println("player 1 connected")
				val Player2 = new game.Player(SocketS.accept(),"2") 
				println("player 2 connected")
		
				Player1.start
				Player2.start

				// Server closin
				SocketS.close()

				###
			}
		}catch {
			case e: Exception => println("Exception caught: " + e)
		}
	}

	def ### = println("##################################################\n")
}

class Game {

	class Player(socket : Socket , user : String) extends Thread {
		
		override def run() = {
			try {	
				val input = new BufferedReader(new InputStreamReader(socket.getInputStream()))
				val output = new PrintWriter(socket.getOutputStream(), true)
				output.println("startGame")
			}catch {
				case e: Exception => println("Exception caught: " + e)
			}
		}
	}
}

