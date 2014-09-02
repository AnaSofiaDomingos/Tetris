import scala.swing._
import scala.swing.BorderPanel.Position._ 
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import javax.swing.{Icon, ImageIcon}
import javax.swing.BorderFactory


// http://docs.oracle.com/javase/tutorial/uiswing/components/border.html

object Grid3 extends SimpleSwingApplication{
	println("Test2")
	def top = new MainFrame{ 
		title = "Tetris"
		preferredSize = new Dimension(700, 800)
		

		var tabButtons =  Array.ofDim[Button](22,10)
		val gridPanel = new GridPanel(22,10){
			val x:Int = 22
       			var y:Int = 10

        		for( i <- 0 until x){
          			for( j <- 0 until y) {
            				tabButtons(i)(j) = new Button()
					tabButtons(i)(j).borderPainted = false
					tabButtons(i)(j).enabled = false
            				contents += tabButtons(i)(j)
		  		}
       			}
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black)

		}
		

		val imageNEXT = new Label{
			icon = new ImageIcon("O.png")
		}


		val labelNEXT = new Label{
			text = "NEXT PIECE"
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)	
		}

		val labelSCORE = new Label{
			text = "SCORE"	
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val txtSCORE = new Label{
			text = "0"	
			font = new Font("Ariel", java.awt.Font.BOLD, 35)
			foreground = Color.white
		}

		val labelLINE = new Label{
			text = "LINES"	
			font = new Font("Ariel", java.awt.Font.ITALIC, 24)
		}

		val txtLINE = new Label{
			text = "0"	
			font = new Font("Ariel", java.awt.Font.BOLD, 35)
			foreground = Color.white
		}

		
		
		val panNEXT = new BorderPanel {
      			preferredSize = new Dimension(200, 200)
      			background = (new Color(100, 100, 100))
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black) 
			layout(labelNEXT) = North
			layout(imageNEXT) = Center
		}

		val panSCORE = new BorderPanel {
      			preferredSize = new Dimension(200, 100)
      			background = (new Color(100, 100, 100))
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black) 
			layout(labelSCORE) = North
			layout(txtSCORE) = South
		}

		val panLINE = new BorderPanel {
      			preferredSize = new Dimension(200, 100)
      			background = (new Color(100, 100, 100))
			border = BorderFactory.createMatteBorder(5,5,5,5,Color.black) 
			layout(labelLINE) = North
			layout(txtLINE) = South
		}


		val panel = new Panel {
      			preferredSize = new Dimension(250, 800)
      			background = (new Color(155, 150, 200))
			border = BorderFactory.createMatteBorder(5,1,5,5,Color.black) 
			_contents += panNEXT
			_contents += panSCORE
			_contents += panLINE
		}

		contents = new BorderPanel{
			layout(gridPanel)= Center
			layout(panel)= East
		}
	}

	override def startup(args: Array[String]) {
	    val t = top
	    if (t.size == new Dimension(0,0)) t.pack()
	    t.visible = true
	}
}


