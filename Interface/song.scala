import javax.swing._
import sun.audio._
import java.awt.event._
import java.io._
import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

object PlaySong extends App{
	var song = new MySong
	song.music

}
		


class MySong(){

	def music(){
		var MGP:AudioPlayer = AudioPlayer.player
		try{
			var BGM = new AudioStream(new FileInputStream("Tetris.wav"))
			var MD:AudioData = BGM.getData()
			var loop:ContinuousAudioDataStream = new ContinuousAudioDataStream(MD)
			MGP.start(loop)
		}catch{
			case ex: FileNotFoundException =>{
           			 println("Missing file exception")
         		}
         		case ex: IOException => {
            			println("IO Exception")
         		}
		}
		
		
	}

}
