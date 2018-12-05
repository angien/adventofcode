import java.io.File
import java.util.Scanner

/* 
 * Finds the repeat total given the list of input strings
 */


fun findFirstRepeat(inputStrings: List<String>): Int {
	val seenTotals = mutableListOf<Int>()
	var total = 0
	
	seenTotals.add(total)

	while (true) {
		for (input: String in inputStrings) {
			val value = input.substring(1).toInt()
			when (input[0].toString()) {
				"+" -> total += value
				"-" -> total -= value
			}

			if (seenTotals.contains(total)) {
				return total
			} else {
				seenTotals.add(total)
			}
		}
	}
}

fun getInputFile(): File {
	val input = if (args.isNotEmpty() && args[0] != "") { 
		args[0] 
	} else { 
		"input" 
	}
	return File(input)
}

fun populateInputStrings(file: File): List<String> {
	val inputScanner = Scanner(file)
	val inputStrings = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine())
	}
	return inputStrings
}


println(findFirstRepeat(populateInputStrings(getInputFile())))