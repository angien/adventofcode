import java.io.File
import java.util.Scanner

/* 
 * check seats for first adjacency
 */

class Solution(val inputStrings: List<String>) {

	var ogrid = mutableListOf<List<String>>()

	fun solution(): Int {
	
		val grid = mutableListOf<MutableList<String>>()

		inputStrings.forEach { s ->
			val row = mutableListOf<String>()
			s.forEach {
				row.add(it.toString())
			}
			grid.add(row)
		}

	 	ogrid = mutableListOf<List<String>>()
		grid.forEach {
			ogrid.add(it.toMutableList())
		}

		var changeState = true
		while(changeState) {
			changeState = false
			for (i in 0 until grid.size) {
				for (j in 0 until grid[0].size) {
					if (ogrid[i][j] == "#" && count(j, i) >= 5) {
						changeState = true
						grid[i][j] = "L"
					} else if (ogrid[i][j] == "L" && count(j, i) == 0) {
						changeState = true
						grid[i][j] = "#"

					}
				}
			}

			ogrid = mutableListOf<List<String>>()
			grid.forEach {
				ogrid.add(it.toMutableList())
			}
		}

		var sum = 0
		grid.forEach { row ->
			row.forEach {
				if (it == "#") {
					sum = sum + 1
				}
			}
		}

		return sum
	}

	fun count(x: Int, y: Int): Int {
		return l(x, y) + r(x, y) + u(x, y) + d(x, y) + ul(x, y) + dl(x, y) + ur(x, y) + dr(x, y)
	}

	fun ul(x: Int, y: Int): Int {
		var nx = x - 1
		var ny = y - 1
		while (nx >= 0 && ny >= 0) {
			ogrid.getOrNull(ny)?.getOrNull(nx)?.let {
				if (it != ".") {
					if (it == "#") {
					 	return 1
					} else {
						return 0
					}
				}
			}
			nx = nx - 1
			ny = ny - 1
		}
		return 0
	}

	fun dl(x: Int, y: Int): Int {
		var nx = x - 1
		var ny = y + 1
		while (nx >= 0 && ny < ogrid.size) {
			ogrid.getOrNull(ny)?.getOrNull(nx)?.let {
				if (it != ".") {
					if (it == "#") {
					 	return 1
					} else {
						return 0
					}
				}
			}
			nx = nx - 1
			ny = ny + 1
		}
		return 0
	}

	fun dr(x: Int, y: Int): Int {
		var nx = x + 1
		var ny = y + 1
		while (nx < ogrid[0].size && ny < ogrid.size) {
			ogrid.getOrNull(ny)?.getOrNull(nx)?.let {
				if (it != ".") {
					if (it == "#") {
					 	return 1
					} else {
						return 0
					}
				}
			}
			nx = nx + 1
			ny = ny + 1
		}
		return 0
	}

	fun ur(x: Int, y: Int): Int {
		var nx = x + 1
		var ny = y - 1
		while (nx < ogrid[0].size && ny >= 0) {
			ogrid.getOrNull(ny)?.getOrNull(nx)?.let {
				if (it != ".") {
					if (it == "#") {
					 	return 1
					} else {
						return 0
					}
				}
			}
			nx = nx + 1
			ny = ny - 1
		}
		return 0
	}

	fun l(x: Int, y: Int): Int {
		var nx = x - 1
		while (nx >= 0) {
			ogrid.getOrNull(y)?.getOrNull(nx)?.let {
				if (it != ".") {
					if (it == "#") {
					 	return 1
					} else {
						return 0
					}
				}
			}
			nx = nx - 1
		}
		return 0
	}

	fun r(x: Int, y: Int): Int {
		var nx = x + 1
		while (nx < ogrid[0].size) {
			ogrid.getOrNull(y)?.getOrNull(nx)?.let {
				if (it != ".") {
					if (it == "#") {
					 	return 1
					} else {
						return 0
					}
				}
			}
			nx = nx + 1
		}
		return 0
	}

	fun u(x: Int, y: Int): Int {
		var ny = y - 1
		while (ny >= 0) {
			ogrid.getOrNull(ny)?.getOrNull(x)?.let {
				if (it != ".") {
					if (it == "#") {
					 	return 1
					} else {
						return 0
					}
				}
			}
			ny = ny - 1
		}
		return 0
	}

	fun d(x: Int, y: Int): Int {
		var ny = y + 1
		while (ny < ogrid.size) {
			ogrid.getOrNull(ny)?.getOrNull(x)?.let {
				if (it != ".") {
					if (it == "#") {
					 	return 1
					} else {
						return 0
					}
				}
			}
			ny = ny + 1
		}
		return 0
	}
}

fun populateInputStrings(): List<String> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine())
	}
	return inputStrings
}

fun getInputFile(): File {
	val input = if (args.isNotEmpty() && args[0] != "") { 
		args[0] 
	} else { 
		"input" 
	}
	return File(input)
}

println(Solution(populateInputStrings()).solution())