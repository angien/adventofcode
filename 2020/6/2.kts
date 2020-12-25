import java.io.File
import java.util.Scanner

/* 
 * Count answers per group that everyone in that group answered
 */

fun solution(inputStrings: List<String>): Int {
	val groups = getGroups(inputStrings)

	var sum = 0
	groups.forEach { group ->
		val count = group.second.groupingBy { it }.eachCount()
		sum = sum + count.filter { it.value == group.first }.size
	}
	
	return sum
}

fun getGroups(inputStrings: List<String>): List<Pair<Int, String>> {
	val groups = mutableListOf<Pair<Int, String>>()

	var curr = ""
	var people = 0
	inputStrings.forEach { line ->
		if (line.length != 0) {
			people = people + 1
			curr = curr + line
		} else {
			groups.add(Pair(people, curr))
			curr = ""
			people = 0
		}
	}
	groups.add(Pair(people, curr))

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