import java.io.File
import java.util.Scanner

/* 
 * sliding window
 */

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		var count = 0
		var checkAgainst = Integer.MAX_VALUE

		inputStrings.forEachIndexed { index, it ->
			val w1 = it.toInt()
			val w2 = inputStrings.getOrNull(index + 1)?.toInt() ?: 0
			val w3 = inputStrings.getOrNull(index + 2)?.toInt() ?: 0

			val window = w1 + w2 + w3

			if (window > checkAgainst) {
				count = count + 1
			}
			checkAgainst = window
		}
		return count
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