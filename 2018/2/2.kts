import java.io.File
import java.util.Scanner

/*
 * Gets the string of the two input strings that have exactly 1 character difference
 */

fun findExactlyOneCharDifference(inputStrings: List<String>): String {
	inputStrings.forEach { input ->
		inputStrings.forEach { 
			if (input.length == it.length) {
				val sameChars = getSameCharacters(input, it)
				if (sameChars.length == input.length-1 ) { // exactly 1 difference
					return sameChars
				} 
			}
		}
	}

	return ""
}

fun getSameCharacters(a: String, b: String): String {
	var sameChars = ""
	for (i in 0..a.length-1) {
		if (a[i] == b[i]) {
			sameChars += a[i]
		}
	}

	return sameChars
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

println(findExactlyOneCharDifference(populateInputStrings()))