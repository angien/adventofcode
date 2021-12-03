import java.io.File
import java.util.Scanner

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		val counts = MutableList(inputStrings[0].length) { 0 }

		inputStrings.forEach {
			it.forEachIndexed { index, num ->
				if (num == '0') {
					counts[index] += 1
				}
			}
		}

		val majority = inputStrings.size / 2
		var g = ""
		var e = ""
		counts.forEach {
			if (it > majority) {
				g += "1"
				e += "0"
			} else {
				g += "0"
				e += "1"
			}
		}
		return Integer.parseInt(g, 2) * Integer.parseInt(e, 2)
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