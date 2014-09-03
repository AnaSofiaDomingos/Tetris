import scala.swing._
import scala.swing.BorderPanel.Position._ 
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import javax.swing.{Icon, ImageIcon}
import javax.swing.BorderFactory


// http://docs.oracle.com/javase/tutorial/uiswing/components/border.html

object GridFinal extends SimpleSwingApplication{
	println("Test2")
	def top = new MainFrame{ 
		title = "Tetris"
		preferredSize = new Dimension(700, 800)

		/*--------------------------------------------------
		-----------------------	GRID -----------------------
		---------------------------------------------------- */

		var state:Int = 2  // 0 : en cours  1 : perdu  2 : Gagné

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
			if(state == 0){
				layout(gridPanel) = Center
			}else if(state == 1){
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
			icon = new ImageIcon("O.png")
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

		var scoreInt:Int = 1000
		var scoreString:String = scoreInt.toString

		val labelSCORE = new Label{
			text = "SCORE"	
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val txtSCORE = new Label{
			text = scoreString	
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
		
		var lineInt:Int = 5
		var lineString:String = lineInt.toString

		val labelLINE = new Label{
			text = "LINES"	
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val txtLINE = new Label{
			text = lineString	
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


		var port = "port"
		var ip = "aaa.bbb.ccc.ddd"

		val labelConnexion = new Label{
			text = "CONNEXION"
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val txtPort = new TextField{
			preferredSize = new Dimension(120, 50)
			background = (new Color(50, 50, 50))
			foreground = Color.white
			text = port
		}

		val txtIP = new TextField{
			preferredSize = new Dimension(120, 50)
			background = (new Color(50, 50, 50))
			foreground = Color.white
			text = ip
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
			text = port
		}

		val labelIP = new Label{
			text = ip
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


