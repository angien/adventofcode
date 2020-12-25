import java.io.File
import java.util.Scanner

/* 
 * hexagon flipping based off of directions
 */

class Solution(val inputStrings: List<String>) {

	fun solution(): Int {
		val directions = mutableListOf<List<String>>()

		inputStrings.forEach {
			directions.add(parse(it))
		}

		val tiles = mutableListOf<Pair<Int, Int>>()
		directions.forEach { d ->
			var curr = Pair(0, 0) // x, y
			d.forEach {
				when (it) {
					"e" -> curr = Pair(curr.first + 2, curr.second)
					"w" -> curr = Pair(curr.first - 2, curr.second)
					"ne" -> curr = Pair(curr.first + 1, curr.second + 1)
					"nw" -> curr = Pair(curr.first - 1, curr.second + 1)
					"se" -> curr = Pair(curr.first + 1, curr.second - 1)
					"sw" -> curr = Pair(curr.first - 1, curr.second - 1)
				}
			}
			tiles.add(curr)
		}

		var toReturn = 0

		tiles.groupingBy { it }.eachCount().forEach {
			if (it.value % 2 == 1) {
				toReturn = toReturn + 1
			}
		}

		return toReturn
	}

	private fun parse(s: String): List<String> {
		val toReturn = mutableListOf<String>()

		var curr = 0
		while (curr < s.length) {
			when (s.get(curr)) {
				'e', 'w' -> {
					toReturn.add(s.get(curr).toString())
					curr = curr + 1
				}
				's', 'n' -> {
					toReturn.add(s.get(curr).toString() + s.get(curr + 1).toString())
					curr = curr + 2
				}
			}
		}

		return toReturn
	}
}

/* ------------ */

fun populateInputStringsAndBreaks(): List<List<String>> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<List<String>>()
	var innerString = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		val next = inputScanner.nextLine()
		if (next == "") {
			inputStrings.add(innerString)
			innerString = mutableListOf<String>()
		} else {
			innerString.add(next)
		}
	}
	inputStrings.add(innerString)
	return inputStrings
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