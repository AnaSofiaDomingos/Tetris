import java.io.IOException;
import java.net.{InetAddress, Socket}

object Client extends App{
	
	// giving the grid
	println("Clients are playing")
	val args1  = Array("1", "2")
	Grid.startup(args1)
	
	// connecting to the server
	var ip = Grid.getIP
	var port = (Grid.getPort).toInt
	var connected = isConnected
	println(connected)

	while (!connected){
		ip = Grid.getIP
		port = (Grid.getPort).toInt
		connected = isConnected
	}
	println(connected)
	
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
	
	def isConnected() = { if ((ip != "aaa.bbb.ccc.ddd") && (port != 0)) true else false}

	
}


