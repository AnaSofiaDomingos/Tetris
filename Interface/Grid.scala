import scala.swing._
import scala.swing.BorderPanel.Position._ 
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import javax.swing.{Icon, ImageIcon}
import javax.swing.BorderFactory


// http://docs.oracle.com/javase/tutorial/uiswing/components/border.html

object GridFinal extends SimpleSwingApplication{


//---------------------------------------------------------------- //
	var state:Int = 0  	// 0 : en cours  1 : perdu  2 : Gagné
	def getState() = state
	def setState(v:Int){ state = v }

	var scoreInt:Int = 0
	var scoreString:String = scoreInt.toString
	def getScore() = scoreString
	def setScore(v:Int){ scoreInt = v }

	var lineInt:Int = 0
	var lineString:String = lineInt.toString
	def getLine() = lineString
	def setLine(v:Int){ LineInt = v }

	var port = "port"
	def getPort() = port
	var ip = "aaa.bbb.ccc.ddd"
	def getIP() = ip

	var NumImage = 1
	def getImage() = NumImage
//---------------------------------------------------------------- //

	def top = new MainFrame{ 
		title = "Tetris"
		preferredSize = new Dimension(700, 800)

		/*--------------------------------------------------
		-----------------------	GRID -----------------------
		---------------------------------------------------- */
		var tabButtons =  Array.ofDim[Button](22,10)
		val gridPanel = new GridPanel(22,10){
			val x:Int = 22
       			var y:Int = 10

        		for( i <- 0 until x){
          			for( j <- 0 until y) {
            				tabButtons(i)(j) = new Button()
					tabButtons(i)(j).borderPainted = true
					tabButtons(i)(j).enabled = false
            				contents += tabButtons(i)(j)
		  		}
       			}
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black)
			
		}
		

		val labelGAMEOVER = new Label{
			text = "LOOSER"
			font = new Font("Ariel", java.awt.Font.BOLD, 50)
			foreground = Color.red
		}

		val labelGAMEWIN = new Label{
			text = "WINNER"
			font = new Font("Ariel", java.awt.Font.BOLD, 50)
			foreground = Color.red
		}

		
		val panGRID = new BorderPanel{
			if (getState() == 0){
				layout(gridPanel) = Center	
			}else if(getState() == 1){
				layout(labelGAMEOVER) = Center
			}else{
				layout(labelGAMEWIN) = Center
			}
			
		}

		/*--------------------------------------------------
		---------------------- NEXT PIECE ------------------
		---------------------------------------------------- */

		val labelNEXT = new Label{
			text = "NEXT PIECE"
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)	
		}
		
		val imageNEXT = new Label{
			val img = NumImage match {
				case 1 => "O.png"
				case 2 => "T.png"
				case 3 => "J.png"
				case 4 => "L.png"
				case 5 => "S.png"
				case 6 => "Z.png"
				case 7 => "I.png"
			}	
			icon = new ImageIcon(img)
		}

		val panNEXT = new BorderPanel {
      			preferredSize = new Dimension(200, 200)
      			background = (new Color(100, 100, 100))
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black) 
			layout(labelNEXT) = North
			layout(imageNEXT) = Center
		}

		/*--------------------------------------------------
		------------------------ SCORE ---------------------
		---------------------------------------------------- */		

		val labelSCORE = new Label{
			text = "SCORE"	
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val txtSCORE = new Label{
			text = getScore()	
			font = new Font("Ariel", java.awt.Font.BOLD, 35)
			foreground = Color.white
		}

		val panSCORE = new BorderPanel {
      			preferredSize = new Dimension(200, 100)
      			background = (new Color(100, 100, 100))
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black) 
			layout(labelSCORE) = North
			layout(txtSCORE) = South
		}

		/*--------------------------------------------------
		------------------------ LINE ----------------------
		---------------------------------------------------- */
		
	
		val labelLINE = new Label{
			text = "LINES"	
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val txtLINE = new Label{
			text = getLine	
			font = new Font("Ariel", java.awt.Font.BOLD, 35)
			foreground = Color.white
		}

			
		val panLINE = new BorderPanel {
      			preferredSize = new Dimension(200, 100)
      			background = (new Color(100, 100, 100))
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black) 
			layout(labelLINE) = North
			layout(txtLINE) = South
		}


		/*--------------------------------------------------
		--------------------- CONNEXION --------------------
		---------------------------------------------------- */

		val labelConnexion = new Label{
			text = "CONNEXION"
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val txtPort = new TextField{
			preferredSize = new Dimension(120, 50)
			background = (new Color(50, 50, 50))
			foreground = Color.white
			text = getPort()
		}

		val txtIP = new TextField{
			preferredSize = new Dimension(120, 50)
			background = (new Color(50, 50, 50))
			foreground = Color.white
			text = getIP()
		}

		val button = new Button {
			text = "VALIDATE"
		}

		val panCONNEXION = new Panel {
      			preferredSize = new Dimension(200, 200)
      			background = (new Color(100, 100, 100))
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black) 
			_contents += labelConnexion
			_contents += txtIP
			_contents += txtPort			
			_contents += button
		}
		
		/*--------------------------------------------------
		--------------------- IS CONNECTED -----------------
		---------------------------------------------------- */


		val labelWelcome = new Label{
			text = "WELCOME"
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val labelPort= new Label{
			text = getPort()
		}

		val labelIP = new Label{
			text = getIP()
		}

		val panCONNECTED = new Panel {
      			preferredSize = new Dimension(200, 200)
      			background = (new Color(100, 100, 100))
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black) 
			_contents += labelWelcome
			_contents += labelIP
			_contents += labelPort
		}



		/*--------------------------------------------------
		------------------ BIG PANEL RIGHT -----------------
		---------------------------------------------------- */

		
		val panel = new Panel {
      			preferredSize = new Dimension(250, 800)
      			background = (new Color(155, 150, 200))
			border = BorderFactory.createMatteBorder(5,1,5,5,Color.black) 
			_contents += panNEXT
			_contents += panSCORE
			_contents += panLINE
			_contents += panCONNEXION
			
			listenTo(button)
			reactions += {
				case ButtonClicked(b) => port = txtPort.text
							 ip = txtIP.text				
							 println("Pseudo : "+port+"\n AdresseIP : "+ip)
							 panCONNEXION.visible_=(false)
							 _contents += panCONNECTED
							
			}

		}
			
		

		contents = new BorderPanel{
			layout(panGRID)= Center
			layout(panel)= East
		}

	} //end def top

	override def startup(args: Array[String]) {
	    val t = top
	    if (t.size == new Dimension(0,0)) t.pack()
	    t.visible = true
	}
}


