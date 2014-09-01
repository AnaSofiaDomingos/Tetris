import scala.swing._

object Grid2 extends SimpleSwingApplication{
	println("Test2")
	def top = new MainFrame{ 
		title = "Tetris"
		preferredSize = new Dimension(600, 800)
		contents = new GridPanel(22,10){

			val j:Int = 22
			var i:Int = 1

			while(i <= j){
				contents += new Button()
      				contents += new Button()
				contents += new Button()
				contents += new Button()
				contents += new Button()
				contents += new Button()
				contents += new Button()
				contents += new Button()
				contents += new Button()
				contents += new Button()
				i = i+1
      			}

		}
	}
}
