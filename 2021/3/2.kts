import java.io.File
import java.util.Scanner

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {

		var loop = inputStrings[0].length

		var o = inputStrings
		var c = inputStrings

		for (i in 0 until loop) {
			val ocount = o.filter { it[i] == '0'} .size // count of all 0s
			val omajority = o.size / 2
			if (o.size > 1) {
				o = o.filter { it[i] == if (ocount > omajority) '0' else '1' }
			}

			val ccount = c.filter { it[i] == '0'} .size // count of all 0s
			val cmajority = c.size / 2
			if (c.size > 1) {
				c = c.filter { it[i] == if (ccount > cmajority) '1' else '0' }
			}
		}

		return Integer.parseInt(o[0], 2) * Integer.parseInt(c[0], 2)
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