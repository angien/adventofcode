import java.io.File
import java.util.Scanner

/* 
 * Finds the number of valid passwords where 1-3 a: abcde (valid) means there are 1-3 occurrences of a in the password
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
	val min = occurrences[0].toInt()
	val max = occurrences[1].toInt()

	val letter = line[1][0]

	val count = line[2].filter { it == letter }.count()

	return count >= min && count <= max
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