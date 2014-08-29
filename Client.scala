import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


object Client extends App {
	try {
		// Client asking for a connection
		val SocketC = new Socket(InetAddress.getLocalHost(),2010);	
		println("Asking for connection");

		// Client connected	
		import java.io.{BufferedReader, InputStreamReader}
		val in = new BufferedReader (new InputStreamReader (SocketC.getInputStream()));
                val Connection : String = in.readLine();
                println(Connection);

		// Client playing
		

		// Client closing
		SocketC.close();	
	} catch {
		case e: Exception => println("Exception caught: " + e)
	}

}
