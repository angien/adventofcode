import java.io.File
import java.util.Scanner

/* 
 * Finds the number of valid passwords
 */

fun solution(inputStrings: List<String>): Int {
	var valid = 0

	inputStrings.forEach {
		if (isValid(it.split(" "))) {
			valid = valid + 1
		}
	}
	
	return valid
}

fun isValid(line: List<String>): Boolean {
	val occurrences = line[0].split("-")
	val pos1 = occurrences[0].toInt() - 1
	val pos2 = occurrences[1].toInt() - 1

	val letter = line[1][0]

	val pos1letter = line[2][pos1]
	val pos2letter = line[2][pos2]

	return (pos1letter == letter && pos2letter != letter) || 
(pos2letter == letter && pos1letter != letter)
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

println(solution(populateInputStrings()))