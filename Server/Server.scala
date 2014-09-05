import java.io.IOException
import java.net.{ServerSocket, Socket}
import Thread._
import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import java.util.ArrayList


object LaunchServer extends App {
	val s = new Server(args(0).toInt)
	s.start()	
}

class Server(port:Int) extends Thread {
	
	import java.net.InetAddress
        val SAddress = InetAddress.getLocalHost()
        println("\nThe server address is : "+SAddress )

        ###
	var NbClientConnect : Int = 1

	
	//var scores = new Score(null,null,null)
	var scoreFinal : ArrayList[Int] = new ArrayList[Int]()

	// Server waiting for a connection
	val SocketS = new ServerSocket(port)
	println("Server listening on "+ SocketS.getLocalPort())

	override def run() = {

		try {
			while (true){
				val game = new Game
		      		
				// Server accepting the client	

				val SocketP1 = SocketS.accept()
				val Player1 = new game.Player(SocketP1, Client.user)
				println("player 1 connected")
				
				val SocketP2 = SocketS.accept()
				val Player2 = new game.Player(SocketP2, Client.user) 
				println("player 2 connected")
		
				Player1.start
				Player2.start

				// Server closin
				while(true){
					Read(SocketP1, Client.user, 1)
					Read(SocketP2, Client.user, 2)
				}
				
				SocketS.close()

				###
			}
		}catch {
			case e: Exception => println("Exception caught: " + e)
		}
	}

	def ### = println("##################################################\n")

	def Read(socket  : Socket , user : String, id : Int) = {
	
		val in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
		val s = new StringBuilder()

		var userOver : String = null

		if (in.ready){
			val paquet = in.readLine()
			println(paquet)
			/*if (paquet.title.startsWith("GAME OVER")){
				val winnerS = Win(scoreFinal)
				for (i <- scoreFinal.size){
					if (scoreFinal.get(i) == winnerS)
        					Tetris.top.labelGAMEOVER.text="WINNER"
					else
        					Tetristop.labelGAMEOVER.text="LOOSER"
						
				}

 			} else if (paquet.title.startsWith("Score")){

				if (paquet.state == 1){
					scoreFinal.set(id,paquet.score)
					userOver = paquet.user
				}else{
					val scoreCourant = paquet.score
					val userCourant = paquet.user
				}			
			}*/
		}
	}
/*
	def Win(score : ArrayList[Int]) : Int = {
		var max : Int = 0
		for (i <- score.size){
			if (scoreFinal.get(i) > max)	
				max = scoreFinal.get(i)
		}
		max			
	}*/

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

