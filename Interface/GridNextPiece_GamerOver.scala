
import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import javax.swing.{Icon, ImageIcon}
import javax.swing.BorderFactory
// http://docs.oracle.com/javase/tutorial/uiswing/components/border.html


object  Tetris extends SimpleSwingApplication{
  private var grid = new Map
  private var p = grid.invoke
  grid.draw(p)
  private var p2 = grid.invoke
  private var nbLine        = 0
  private var currentScore  = 0
  private var mainScoreLine = 0
  private var mainScore     = 0
  private var stateGame:Int = 0 // 0 = en cours ; 1 = perdu ; 2 = Gagné
  val x:Int = 22
  var y:Int = 10
  var a:Int = 2
  var b:Int = 4
  var tabButtons = Array.ofDim[Button](x,y)
  var tabNext = Array.ofDim[Button](a,b)

  val top = Interface

  object Interface extends MainFrame{

    title = "Tetris"
    preferredSize = new Dimension(700, 800)


    val gridPanel = new GridPanel(22,10){
      this.preferredSize = new Dimension(y*40, x*40)
      for( i <- 0 until x){
        for( j <- 0 until y) {
          tabButtons(i)(j) = new Button()
          tabButtons(i)(j).borderPainted = false
          tabButtons(i)(j).enabled = false
          contents += tabButtons(i)(j)
        }
      }

      border = BorderFactory.createMatteBorder(5,5,5,5,Color.black)


      listenTo(keys)

      reactions += {
        case KeyPressed(_, Key.Right, _, _) => {
          println("right")
          if ((stateGame == 0) && (grid.canGoRight(p))) grid.right(p)
          TimerFunction
        }

        case KeyPressed(_, Key.Left, _, _) => {
          if ((stateGame == 0) && (grid.canGoLeft(p))) grid.left(p)
          TimerFunction
        }

        case KeyPressed(_, Key.Down, _, _) => {
          if ((stateGame == 0) && (grid.canGoDown(p))) {
            grid.down(p)
            RunningGame
            TimerFunction
            timer.restart()
          }
        }

        case KeyPressed(_, Key.Up, _, _) => {
          println("up")
         // if (stateGame==3) stateGame = 0
          if ((stateGame == 0) && (grid.canRotate(p))) grid.rotate(p)
          TimerFunction

        }

        case KeyPressed(_, Key.Space, _, _) => {
          while (grid.canGoDown(p)) grid.down(p)
          TimerFunction
        }
      }

      focusable = true
      requestFocus


    } // Fin panel



    val labelGAMEOVER = new Label{
      text = "LOOSER"
      font = new Font("Ariel", java.awt.Font.BOLD, 30)
      border = BorderFactory.createMatteBorder(5,5,5,5,Color.black)
      foreground = Color.red
      background = new Color(0,0,0,0)
      opaque_=(false)
    }


    val panGRID = new BoxPanel(Orientation.Horizontal){   		
	contents += new BorderPanel{
		add(gridPanel, BorderPanel.Position.Center)
		add(labelGAMEOVER, BorderPanel.Position.South)
		labelGAMEOVER.opaque_=(false)
		labelGAMEOVER.visible_=(false)
	}
	
    }

    val imageNEXT = new GridPanel(a,b){
       this.preferredSize = new Dimension(a*40, b*40)
       background =(new Color(100, 100, 100))
       for( i <- 0 until a){
        for( j <- 0 until b) {
          tabNext(i)(j) = new Button()
	  tabNext(i)(j).borderPainted = false
	  tabNext(i)(j).background =(new Color(100, 100, 100))
          contents += tabNext(i)(j)
        }
      }
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
  }

  def TimerFunction: Unit = {

    for( i <- 0 until x){
      for( j <- 0 until y) {
        tabButtons(i)(j).background = RefreshColors(i, j)
        tabButtons(i)(j).borderPainted = grid.get(i,j) match { case 0 => false
        case _ => true }
      }
    }
  }

  def RunningGame(): Unit = {
    if (grid.canGoDown(p))
      grid.down(p)
    else {
      nbLine = grid.clean
      mainScoreLine += nbLine
      Interface.txtLINE.text = mainScoreLine.toString
      currentScore = nbLine match {
        case 1 => 10
        case 2 => 30
        case 3 => 60
        case 4 => 100
        case _ => 0
      }
      mainScore   += currentScore
      Interface.txtSCORE.text = mainScore.toString

      p = p2
      p2 = grid.invoke
      if (!grid.isValid(p)) {
        //GAMEOVER
        println("!! Game Over !!")
        stateGame = 1
	top.labelGAMEOVER.opaque_=(true)
	top.labelGAMEOVER.visible_=(true)
	top.gridPanel.opaque_=(true)
        timer.stop
      }
      else grid.draw(p)
    }

  }

  val timer=new javax.swing.Timer(500, Swing.ActionListener(e => {
    println(stateGame.toString)
    if(stateGame == 0) {
      RunningGame
      TimerFunction
    }
  }))

  //timer.start()

  def startGame: Unit = timer.start()


  override def startup(args: Array[String]) {
    val t = top
    if (t.size == new Dimension(0,0)) t.pack()
    t.visible = true
  }

  def RefreshColors(i: Int, j: Int): Color = grid.get(i,j) match {
    //lastGrid = grid.getGrid
    case 0 => null
    case 1 => Color.red
    case 2 => Color.blue
    case 3 => Color.green
    case 4 => Color.pink
    case 5 => Color.cyan
    case 6 => Color.orange
    case 7 => Color.magenta
  }

}
