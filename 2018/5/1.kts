import java.io.File
import java.util.Scanner

/*
 * Returns the length of the string without the reactive ("Aa", "bB", etc) characters
 * AKA the stable characters of the string
 */

fun getStableCharCount(inputString: String): Int {
	var input = inputString

	var i = 0
	while (i < input.length-2) {
		if (isReactive(input[i], input[i+1])) {
			input = input.removeRange(i, i+2)
			i = 0
		} else {
			i++
		}
	}
	return input.length
}

fun isReactive(a: Char, b: Char): Boolean {
	if (a.isLowerCase()) {	
		return (a.toUpperCase() == b)
	} else {
		return (a.toLowerCase() == b)
	}
}


fun populateInputString(): String {
	val inputScanner = Scanner(getInputFile())
	var inputString = ""

	while (inputScanner.hasNextLine()) {
		inputString = inputScanner.nextLine()
	}
	return inputString
}

fun getInputFile(): File {
	val input = if (args.isNotEmpty() && args[0] != "") { 
		args[0] 
	} else { 
		"input" 
	}
	return File(input)
}

println(getStableCharCount(populateInputString()))