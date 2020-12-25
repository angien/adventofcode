import java.io.File
import java.util.Scanner

/* 
 * get corners of the tiles when they are put together after rotations and flips
 */

data class Tile(
	val value: List<String>
) {
	fun t() = value[0]
	fun b() = value[value.size - 1]
	fun l(): String {
		var toReturn = ""
		value.forEach {
			toReturn = toReturn + it[0]
		}
		return toReturn
	}
	fun r(): String {
		var toReturn = ""
		value.forEach {
			toReturn = toReturn + it[value.size - 1]
		}
		return toReturn
	}

	fun allConfigurations(): List<String> {
		return listOf(t(), b(), l(), r(), t().reversed(), b().reversed(), l().reversed(), r().reversed())
	}

	fun matches(tile: Tile): Boolean {
		if (tile == this) {
			return false
		}

		val a = this.allConfigurations()
		val b = tile.allConfigurations()

		return a.filter { b.contains(it) }.count() > 0
	}
}

class Solution(val inputStrings: List<List<String>>) {

	val tiles = mutableMapOf<String, Tile>()

	fun solution(): Long {

		inputStrings.forEach {
			val tileValue = mutableListOf<String>()
			it.forEach {
				if (it.contains("Tile").not()) {
					tileValue.add(it)
				}
			}
			
			tiles.put(it[0].replace("Tile ", "").replace(":",""), Tile(value = tileValue))
		}

		// find the 4 squares that have 2 matches only
		var m = 1L
		tiles.forEach {
			val matches = getMatches(it.value)
			if (matches.size == 2) {
				m = m * it.key.toLong()
				println(it.key)
			}
		}

		return m
	}

	fun getMatches(t: Tile): List<String> {
		val matches = mutableListOf<String>()

		tiles.forEach {
			if (t.matches(it.value)) {
				matches.add(it.key)
			}
		}

		return matches
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

println(Solution(populateInputStringsAndBreaks()).solution())