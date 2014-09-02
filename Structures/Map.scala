import Array._

class Map {

	private var grid    = Array[Array[Int]]()
	private val height  = 22
	private val width   = 10

	// Creates a random piece
	def invoke: Piece = new Piece

	// Checks if the piece is playable (game not over)
	def isValid(f: Piece): Boolean = {

		var ret = true

		for (i <- 0 until f.dimension(0)) 
			for (j <- 0 until f.dimension(1)) 
				if ((f.matrix(i)(j) != 0) && (grid(i+f.position(0)-1)(j+f.position(1)) != 0))
					ret = false

		ret

	}

	// Places a piece at its saved position
	def draw(f: Piece): Unit = {

		for (i <- 0 until f.dimension(0))
			for (j <- 0 until f.dimension(1))
				if (f.matrix(i)(j) != 0)
					grid(i+f.position(0)-f.dimension(0)+1)(j+f.position(1)) = f.matrix(i)(j) 

	}

	// Clear the piece
	def clear(f: Piece): Unit = {

		var fi = 0
		for (i <- (f.position(0)-f.dimension(0)+1) to f.position(0)) {

			var fj = 0
			for (j <- f.position(1) until (f.position(1)+f.dimension(1))) {

				if ((grid(i)(j) != 0) && (f.matrix(fi)(fj) != 0)) grid(i)(j) = 0
				fj = fj + 1

			} 

			fi = fi + 1

		}
	}

	// Binary display
	def display: Unit = {

		for (i <- 0 until grid.size) { 
			for (j <- 0 until grid(i).size)
				print(" " + grid(i)(j))
			println
		}
		println

	}

	// Moves the current shape downwards
	def down(f: Piece): Unit = {

		clear(f)
		f.down
		draw(f)
		
	}

	// Moves the current shape downwards
	def right(f: Piece): Unit = {

		clear(f)
		f.right
		draw(f)
		
	}
	
	// Moves the current shape downwards
	def left(f: Piece): Unit = {

		clear(f)
		f.left
		draw(f)
		
	}

	// Moves the current shape downwards
	def rotate(f: Piece): Unit = {

		clear(f)
		f.rotate
		draw(f)
		
	}

	// Checks if the piece can move downwards
	def canGoDown(f: Piece): Boolean = {

		var ret = true

		if (f.position(0) == (this.height - 1)) ret = false
			
		else {
		
			for (j <- 0 until f.dimension(1)) {
	
				var last1 = 0	
				for (i <- 0 until f.dimension(0))
					if (f.matrix(i)(j) != 0) last1 = i 

				if (grid(last1+f.position(0)-f.dimension(0)+2)(j+f.position(1)) != 0)
					ret = false
		
			}
			
		}
		
		ret

	}

	// Checks if the piece can move right
	def canGoRight(f: Piece): Boolean = {

		var ret = true

		if ((f.position(1) + f.dimension(1)) == this.width) ret = false

		else {

			for (i <- 0 until f.dimension(0)) {

				var last1 = 0
				for (j <- 0 until f.dimension(1))
					if (f.matrix(i)(j) != 0) last1 = j

				if (grid(i+f.position(0)-f.dimension(0)+1)(f.position(1)+last1+1) != 0)
					ret = false

			}

		}

		ret


	}

	// Checks if the piece can move left
	def canGoLeft(f: Piece): Boolean = {

		var ret = true

		if (f.position(1) == 0) ret = false

		else {
			
			for (i <- 0 until f.dimension(0)) {
	
				var first1 = 0
				while (f.matrix(i)(first1) == 0) first1 = first1 + 1
				
				if (grid(i+f.position(0)-f.dimension(0)+1)(f.position(1)+first1-1) != 0)
					ret = false

			}

		}

		ret

	}

	// Checks if the piece can rotate (clockwise)
	def canRotate(f: Piece): Boolean = {
		
		clear(f)
		f.rotate

		var ret = true

		for (i <- 0 until f.dimension(0)) 
			for (j <- 0 until f.dimension(1)) 
				if ((f.matrix(i)(j) != 0) && (grid(i+f.position(0)-f.dimension(0)+1)(j+f.position(1)) != 0))
					ret = false

		for (k <- 0 until 3) f.rotate
		
		ret

	}

	// Checks if a whole row is full
	def isFull(r: Int): Boolean = (0 until grid(r).size).forall( grid(r)(_) != 0 ) 

	// Deletes a row and moves the rest downwards
	def removeRow(r: Int): Unit = {

		for (i <- r to 1 by -1) {

			for (j <- 0 until grid(i).size)
				grid(i)(j) = grid(i-1)(j)

		}
		grid(0) = Array.fill(width){0}

	}

	// Checks the grid and suppresses full rows
	def clean: Unit = for (i <- 0 until this.height) if (isFull(i)) removeRow(i)

	// Getters
	def getGrid: Array[Array[Int]] = this.grid
	def dimension: Array[Int] = Array(height, width)

	// Constructor
	for (k <- 0 until height) { grid = grid ++ Array(Array.fill(width){0}) }

}
