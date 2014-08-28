import java.util.Random
import Array._

class Form {

	private var matrix = Array[Array[Boolean]]()
	private val max = 7

	def rotate: Unit = {

		var newarr = Array[Array[Boolean]]()

		for (j <- 0 until matrix(0).size) {

			var sub = Array[Boolean]()

			for (i <- (matrix.size-1) to 0 by -1) {	sub = sub ++ Array(matrix(i)(j))}

			newarr = newarr ++ Array(sub)

		}

		matrix = newarr
				
	}

	var rnd = Math.round(new Random().nextDouble * (max-1))

	rnd match {

		case 0 => { matrix = Array(Array(true), Array(true), Array(true), Array(true)) }

		case 1 => { matrix = Array( Array(true,  true),
					    Array(true,  true) )
			  }

		case 2 => { matrix = Array( Array(false, true),
					    Array(false, true),
					    Array(true,  true) )
			  }

		case 3 => { matrix = Array( Array(true, false),
				 	    Array(true, false),
				 	    Array(true,  true) )
			  }

		case 4 => { matrix = Array( Array(false, true),
				            Array(true,  true),
				            Array(false, true) )
			  }

		case 5 => { matrix = Array( Array(false, true),
					    Array(true,  true),
					    Array(true, false) )
			  }

		case 6 => { matrix = Array( Array(true, false),
					    Array(true,  true),
					    Array(false, true) )
			  }
	}

	def display: Unit = {

		for (i <- 0 until matrix.size) {
			for (j <- 0 until matrix(i).size) {

				val x = if (matrix(i)(j)) 1 else 0
				print(" " + x)

			}
			println
		}
		println

	}

}
