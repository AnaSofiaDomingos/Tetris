import java.io.IOException;
import java.net.{InetAddress, Socket}
import java.io.{BufferedReader, InputStreamReader, PrintWriter, ObjectOutputStream}

object LaunchClient extends App{
	
	// giving the grid
	val args1  = Array("1", "2")
	Tetris.startup(args1)
	
	// connecting to the server
	val user = Tetris.Interface.pseudo
	var ip = Tetris.Interface.ip
	var port = (Tetris.Interface.port).toInt
	var connected = isConnected

	// check until not connected
	while (!connected){
		ip = Tetris.Interface.ip
		port = (Tetris.Interface.port).toInt
		connected = isConnected
	}

	var SocketC = new Socket()
	var out : PrintWriter = null

	try {

		// Client asking for a connection
		println(InetAddress.getLocalHost())
		SocketC = new Socket(ip, port)	

		// Client connected
		// Receive infos from the server	
		val in = new BufferedReader (new InputStreamReader (SocketC.getInputStream()))
		val s : String = in.readLine()
		if (s.startsWith("startGame"))
			Tetris.startGame

		// Send infos to the server by the client
		val o = new PrintWriter(SocketC.getOutputStream(),true)
		out = o
		
		/* Send infos to the server by the client with the class Paquets
		DOESN'T WORK
    		val oos : ObjectOutputStream = new ObjectOutputStream(LaunchClient.SocketC.getOutputStream())
		out = oos */

	} catch {
		case e: Exception => println("Exception caught: " + e)
	}

	// check the connection	
	def isConnected() = { if (ip != "129.194.184.xxx") true else false}
	
}




