import Array._

class Map {

	private var grid    = Array[Array[Boolean]]()
	private val height  = 22
	private val width   = 10

	/** Places a random shape at the top of the grid */
	def invoke: Form = {

		val f = new Form
		for (i <- 0 until f.matrix.size)
			for (j <- 0 until f.matrix(i).size) 
				grid(i)(j+f.position(0)) = f.matrix(i)(j)

		f

	}

	/** Binary display */
	def display: Unit = {

		for (i <- 0 until grid.size) {
			for (j <- 0 until grid(i).size) {
				
				val x = if (grid(i)(j)) 1 else 0
				print(" " + x)

			}
			println
		}
		println

	}

	/** Moves the current shape downwards 
	def down(f: Form): Unit = {
		
		for (i <- 
		f.down
		
	}*/

	/** Checks if the shape has reached the bottom of the grid */
	def isBottom(f: Form): Boolean = ???

	/** Checks if a whole row is full */
	def isFull(r: Int): Boolean = ???

	/** Deletes a row and moves the rest downwards */
	def removeRow(r: Int): Unit = ???

	/** Constructor */

	for (k <- 0 until height) { grid = grid ++ Array(Array.fill(width){false}) }

}
