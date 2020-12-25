import java.io.File
import java.util.Scanner

/* 
 * check seat adjacent (including diagonals)
 */

fun solution(inputStrings: List<String>): Int {
	
	val grid = mutableListOf<MutableList<String>>()

	inputStrings.forEach { s ->
		val row = mutableListOf<String>()
		s.forEach {
			row.add(it.toString())
		}
		grid.add(row)
	}

	var ogrid = mutableListOf<List<String>>()
	grid.forEach {
		ogrid.add(it.toMutableList())
	}

	var changeState = true
	while(changeState) {
		changeState = false
		for (i in 0 until grid.size) {
			for (j in 0 until grid[0].size) {
				if (ogrid[i][j] == "#" && count("#", i, j, ogrid) >= 4) {
						changeState = true
						grid[i][j] = "L".toString()
				} else if (ogrid[i][j] == "L" && count("#", i, j, ogrid) == 0) {
						changeState = true
						grid[i][j] = "#".toString()

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

fun count(s: String, x: Int, y: Int, grid: List<List<String>>): Int {
	return singleCount(s, x-1, y, grid) + singleCount(s, x+1, y, grid) + singleCount(s, x, y-1, grid) + singleCount(s, x, y+1, grid) + singleCount(s, x-1, y-1, grid) + singleCount(s, x+1, y-1, grid) + singleCount(s, x-1, y+1, grid) + singleCount(s, x+1, y+1, grid)
}

fun singleCount(s: String, x: Int, y: Int, grid: List<List<String>>): Int {
	grid.getOrNull(x)?.getOrNull(y)?.let {
		if (it == s) {
			return 1
		}
	}
	return 0
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

println(solution(populateInputStrings()))