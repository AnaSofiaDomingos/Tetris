import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

object Server extends App{
	import java.net.InetAddress
        val SAddress = InetAddress.getLocalHost();
        println("The server address is : "+SAddress ); 

	try {
		// Server waiting for a connection
		val SocketS = new ServerSocket(2012);
		println("Server listening on "+ SocketS.getLocalPort());
      		
		// Server accepting the client
		val SocketConnect = SocketS.accept(); 
		println("Client asking for connection!");

		// Server giving answer to client
		import java.io.PrintWriter
		val out = new PrintWriter(SocketConnect.getOutputStream());
                out.println("You are connected to the Server");
                out.flush();

		// Server doing something
				


		// Server closing
		SocketS.close();
		SocketConnect.close();

	}catch {
		case e: Exception => println("Exception caught: " + e);
	}
	
}
