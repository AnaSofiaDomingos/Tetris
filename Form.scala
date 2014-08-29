import java.util.Random
import Array._

class Form {

	private var mat = Array[Array[Boolean]]()
	private val max    = 7
	private var posx   = 0
	private var posy   = 4

	/** 90° clockwise */
	def rotate: Unit = {

		var newarr = Array[Array[Boolean]]()

		for (j <- 0 until mat(0).size) {

			var sub = Array[Boolean]()

			for (i <- (mat.size-1) to 0 by -1) {	sub = sub ++ Array(mat(i)(j))}

			newarr = newarr ++ Array(sub)

		}

		mat = newarr
				
	}

	/** Binary display */
	def display: Unit = {

		for (i <- 0 until mat.size) {
			for (j <- 0 until mat(i).size) {

				val x = if (mat(i)(j)) 1 else 0
				print(" " + x)

			}
			println
		}
		println

	}

	/** Getters */

	def matrix:    Array[Array[Boolean]] = mat
	def position:  Array[Int]	     = Array(posx, posy)
	def dimension: Array[Int]            = Array(mat.size, mat(0).size)

	/** Setters */

	def down: Unit  = { posx = posx + 1 }
	def left: Unit  = { posy = posy - 1 }
	def right: Unit = { posy = posy + 1 }


	/** Constructor */

	var rnd = Math.round(new Random().nextDouble * (max-1))

	rnd match {

		case 0 => { mat = Array(Array(true), Array(true), Array(true), Array(true)) }

		case 1 => { mat = Array( Array(true,  true),
					    Array(true,  true) )
			  }

		case 2 => { mat = Array( Array(false, true),
					    Array(false, true),
					    Array(true,  true) )
			  }

		case 3 => { mat = Array( Array(true, false),
				 	    Array(true, false),
				 	    Array(true,  true) )
			  }

		case 4 => { mat = Array( Array(false, true),
				            Array(true,  true),
				            Array(false, true) )
			  }

		case 5 => { mat = Array( Array(false, true),
					    Array(true,  true),
					    Array(true, false) )
			  }

		case 6 => { mat = Array( Array(true,  false),
					    Array(true,  true),
					    Array(false, true) )
			  }

	}

	posx = mat.size - 1

}
