import java.io.File
import java.util.Scanner

/* 
 * Count unique answers per group
 */

fun solution(inputStrings: List<String>): Int {
	val groups = getGroups(inputStrings)

	var sum = 0
	groups.forEach {
		sum = sum + it.toCharArray().distinct().size
	}
	
	return sum
}

fun getGroups(inputStrings: List<String>): List<String> {
	val groups = mutableListOf<String>()

	var curr = ""
	inputStrings.forEach { line ->
		if (line.length != 0) {
			curr = curr + line
		} else {
			groups.add(curr)
			curr = ""
		}
	}
	groups.add(curr)

	return groups
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