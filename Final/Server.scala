import java.io.IOException
import java.net.{ServerSocket, Socket}
import Thread._
import java.io.{BufferedReader, InputStreamReader, PrintWriter, ObjectInputStream}
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
				val Player1 = new game.Player(SocketP1, LaunchClient.user)
				println("player 1 connected")
				
				val SocketP2 = SocketS.accept()
				val Player2 = new game.Player(SocketP2, LaunchClient.user) 
				println("player 2 connected")
		
				Player1.start
				Player2.start

				// Server closin
				while(true){
					Read(SocketP1, 1)
					Read(SocketP2, 2)

					// check the winner
					if (scoreFinal.size() == 2) {
						
						for (i <- 1 until 3)
							println(scoreFinal.get(i))

						val winnerS = Win(scoreFinal)	
						println(winnerS)
						for (i <- 1 until scoreFinal.size()){
							if (scoreFinal.get(i) == winnerS){
								println("WINNER")
								Tetris.top.labelGAMEOVER.text="WINNER"
							}else{
								Tetris.top.labelGAMEOVER.text="LOOSER"
								println("LOOSER")
							}	
						}
					}
				}
				
				SocketS.close()

				###
			}
		}catch {
			case e: Exception => println("Exception caught: " + e)
		}
	}

	def ### = println("##################################################\n")

	def Read(socket  : Socket , id : Int) = {
		val in = new BufferedReader (new InputStreamReader (socket.getInputStream()))
		if (in.ready()){
			val s : String = in.readLine()
			if (s.startsWith("GAME OVER")){
				for (score <- s.split(";", 2)){	
					if (score != "GAME OVER") {
				  		scoreFinal.add(score.toInt)
					}
				}	
			}	
		}
	}

	def Win(score : ArrayList[Int]) : Int = {
		var max : Int = 0
		for (i <- 1 until score.size()){
			if (score.get(i) > max)	
				max = score.get(i)
		}
		max			
	}


	/* read data with the class 
	DOESN'T WORK
	def Read(socket  : Socket , id : Int) = {
		val in = new ObjectInputStream(socket.getInputStream())

		val obj =  in.readObject()
		val paquet : Paquets = obj.asInstanceOf[Paquets]
		println(paquet.title + " " + paquet.score  + " " + paquet.user  + " " + paquet.state)
		
		if (paquet.title == "GAME OVER"){	
			scoreFinal.set(id,paquet.score)
			val userOver = paquet.user
			if (scoreFinal.size() == 2) {
				val winnerS = Win(scoreFinal)	
				println(winnerS)
				for (i <- 1 until scoreFinal.size()){
					if (scoreFinal.get(i) == winnerS){
						println("WINNER" + user)
						Tetris.top.labelGAMEOVER.text="WINNER"
					}else{
						Tetris.top.labelGAMEOVER.text="LOOSER"
						println("LOOSER" + user)
					}	
				}
			}

		}		
		
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

