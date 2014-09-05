import java.util.Random
import Array._

class Piece {

	private var mat  = Array[Array[Int]]()
	private val max  = 7
	private var posx = 0
	private var posy = 4

	// 90° clockwise
	def rotate: Unit = {

		var newarr = Array[Array[Int]]()

		for (j <- 0 until mat(0).size) {

			var sub = Array[Int]()

			for (i <- (mat.size-1) to 0 by -1) sub = sub ++ Array(mat(i)(j))

			newarr = newarr ++ Array(sub)

		}

		var diff = 0
		if (mat.size > mat(0).size)
			diff = -1
		if (mat.size < mat(0).size)
			diff = 1
		posx     = posx + diff
		mat      = newarr
				
	}

	// Binary display
	def display: Unit = {

		for (i <- 0 until mat.size) {
			for (j <- 0 until mat(i).size) {

				val x = mat(i)(j)
				print(" " + x)

			}
			println
		}
		println

	}

	// Getters 

	def matrix:    Array[Array[Int]] = mat
	def position:  Array[Int]	 = Array(posx, posy)
	def dimension: Array[Int]        = Array(mat.size, mat(0).size)

	// Setters

	def down: Unit  = { posx = posx + 1 }
	def left: Unit  = { posy = posy - 1 }
	def right: Unit = { posy = posy + 1 }


	// Constructor

	var rnd = Math.round(new Random().nextDouble * (max-1))

	rnd match {

		case 0 => { mat = Array( Array(1), Array(1), Array(1), Array(1) ) }

		case 1 => { mat = Array( Array(2, 2),
					 Array(2, 2) )
			  }

		case 2 => { mat = Array( Array(0, 3),
					 Array(0, 3),
					 Array(3, 3) )
			  }

		case 3 => { mat = Array( Array(4, 0),
				 	 Array(4, 0),
				 	 Array(4, 4) )
			  }

		case 4 => { mat = Array( Array(0, 5),
				         Array(5, 5),
				         Array(0, 5) )
			  }

		case 5 => { mat = Array( Array(0, 6),
					 Array(6, 6),
					 Array(6, 0) )
			  }

		case 6 => { mat = Array( Array(7, 0),
					 Array(7, 7),
					 Array(0, 7) )
			  }

	}

	posx = mat.size - 1

}
