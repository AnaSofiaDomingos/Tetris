import java.util.{ArrayList, Random}

class Form {

	private var matrix = Array[Array[Boolean]]()
	private val max = 7

	def swap: Unit = {

	}

	var rnd = Math.round(new Random().nextDouble * (max-1))
	
	rnd match {

		case 0 => {
			matrix = Array(Array(true), Array(true), Array(true), Array(true))
		}

		case 1 => {
			matrix = Array( Array(true, true),
					Array(true, true) )
		}

		case 2 => {
			matrix = Array( Array(true,  true, true),
					Array(false, true, false) )
		}

		case 3 => {
			matrix = Array( Array(true, true,  true),
					Array(true, false, false) )
		}

		case 4 => {
			matrix = Array( Array(true,  true,  true),
					Array(false, false, true) )
		}

		case 5 => {
			matrix = Array( Array(false, true), 
					Array(true,  true), 
					Array(true,  false) )
		}

		case 6 => {
			matrix = Array( Array(true, false), 
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

object FormTest extends App {

	for (i <- 0 until 10) { val test = new Form
				test.display }

}
