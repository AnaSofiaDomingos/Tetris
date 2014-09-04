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

				val SocketP1 = SocketS.accept()
				val Player1 = new game.Player(SocketP1, "1")
				println("player 1 connected")
				
				val SocketP2 = SocketS.accept()
				val Player2 = new game.Player(SocketP2,"2") 
				println("player 2 connected")
		
				Player1.start
				Player2.start

				// Server closin
				Read(SocketP1, "1")
				Read(SocketP2, "2")
				
				//SocketS.close()

				###
			}
		}catch {
			case e: Exception => println("Exception caught: " + e)
		}
	}

	def ### = println("##################################################\n")

	def Read(socket : Socket , user : String) {
		//var input : BufferedReader = null
		//while(true){
			val in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
			//input = in
			val donnee = in.readLine()			
			println(user + " :  " + donnee)
		//}
	}
}

class Game {

	class Player(socket : Socket , user : String) extends Thread {
		
		override def run() = {
			try {	
				val output = new PrintWriter(socket.getOutputStream(), true)
				output.println("startGame")
				output.flush()
			}catch {
				case e: Exception => println("Exception caught: " + e)
			}
		}
	}
	
	
}

