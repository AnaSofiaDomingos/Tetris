import java.io.IOException;
import java.net.{InetAddress, Socket}

object Client extends App{
	
	// giving the grid
	println("Clients are playing")
	val args1  = Array("1", "2")
	Tetris.startup(args1)
	
	// connecting to the server
	var ip = Tetris.Interface.ip
	var port = (Tetris.Interface.port).toInt
	var connected = isConnected

	while (!connected){
		ip = Tetris.Interface.ip
		port = (Tetris.Interface.port).toInt
		connected = isConnected
	}
	var SocketC = new Socket()
	var NbConnection = 0

	try {

		// Client asking for a connection
		println(InetAddress.getLocalHost())
		SocketC = new Socket(ip, 2014)	
		println("Asking for connection")

		// Client connected	
		import java.io.{BufferedReader, InputStreamReader}
		val in = new BufferedReader (new InputStreamReader (SocketC.getInputStream()))
		val s : String = in.readLine()
		if (s.startsWith("startGame"))
			Tetris.startGame

		// Client playing

		// Client closing
		SocketC.close();
	} catch {
		case e: Exception => println("Exception caught: " + e)
	}

	if (NbConnection == 2 ){
		println("play")
		//Tetris.Actions()
		
	}else{
		println("Wait for a second player")
	}

	
	def isConnected() = { if ((ip != "aaa.bbb.ccc.ddd") && (port != 0)) true else false}

	def getSocket() = {println("geting socket") 
				SocketC}

	
}


