import Thread._
import java.net.{ServerSocket, Socket}
import java.io.{BufferedReader, PrintWriter}
import javax.swing.*;

class ServerTCP extends Thread {
	val DISCONNECTED = 1
	val DISCONNECTING = 2
	val BEGIN_CONNECT = 3
	val CONNECTED = 4
	
	val tcpObj = new ServerTCP()
	val hostIP = "129.194.184.109"
	val port = 2014
	val connectionStatus = DISCONNECTED
	

	def changeStatusTS(newConnectStatus : Int, noError : Boolean) : Unit = {
		if (newConnectStatus != NULL) connectionStatus = newConnectStatus
		
		//if (noError) statusString = 
		
		SwingUtilities.invokeLater(tcpObj)
	}
		
	def changeStatusTS(newConnectStatus : Int, noError : Boolean) : Unit = {
		if (newConnectStatus != NULL) connectionStatus = newConnectStatus
		tcpObj.run()
	}
	
	def appendTo(s : String) = {
		synchronized (toAppend) { toAppend.append(s)}
	}
	
	def sendString(s : String) = {
		synchronized (toSend) { toSend.append(s + "\n")}
	}
	
	override def run() = {
			try {
					val hostServer = new ServerSocket(port)
					val socket = hostServer.accept()
					val in = new BufferReader(new InputStreamReader(socket.getInputStream()))
					val out = new PrintWriter(socket.getOutputStream(), true)
					changeStatusTS(CONNECTED, true)
			} catch {
				changeStatusTS(DISCONNECTED, true)
			}
		
		}
	}
}
