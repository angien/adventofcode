import java.io.File
import java.util.Scanner

/*
 * Gets the checksum of the input strings by multiplying total strings with 2 and 3 character repeats
 */

fun getWordCounts(inputStrings: List<String>): Int {
	var numTwos = 0
	var numThrees = 0

	inputStrings.forEach { input ->
		val characterCounts = HashMap<Char, Int>()
		input.forEach {
			characterCounts.put(it, (characterCounts.get(it) ?: 0) + 1)
		}
		if (characterCounts.containsValue(2)) {
			numTwos++
		}
		if (characterCounts.containsValue(3)) {
			numThrees++
		}
	}

	return numTwos * numThrees 
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

println(getWordCounts(populateInputStrings()))