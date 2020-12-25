import java.io.File
import java.util.Scanner

/* 
 * parse tickets and filter invalid ones for all ranges
 */

class Solution(val inputStrings: List<List<String>>) {

	val validMinMax = mutableMapOf<Int, Int>()
	
	fun solution(): Int {

		inputStrings[0].forEach {
			val labelled = it.split(": ")
			if (labelled.size > 1) {
				val ranges = labelled[1].split(" or ")
				ranges.forEach { r ->
					val m = r.split("-")
					validMinMax.put(m[0].toInt(), m[1].toInt())
				}
			}
		}

		var invalids = 0

		inputStrings[2].forEach {
			if (it.contains(":").not()) {
				it.split(",").forEach { ticket ->
					val i = ticket.toInt()
					if (inRange(i).not()) {
						invalids = invalids + i
					}
				}
			}
		}
		
		return invalids
	}


	private fun inRange(v: Int): Boolean {
		validMinMax.forEach {
			if (v >= it.key && v <= it.value) {
				return true
			}
		}
		return false
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