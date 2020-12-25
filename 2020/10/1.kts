import java.io.File
import java.util.Scanner

/* 
 * Count differences of 1s and 3s
 */

fun solution(inputStrings: List<Int>): Int {
	var sum = 0

	val all = inputStrings.toMutableList()
	all.sort()

	var threes = 0
	var ones = 0

	all.forEach {
		val diff = it - sum
		if (diff == 3) {
			threes = threes + 1
		} else {
			ones = ones + 1
		}

		sum = sum + diff
	}

	println(ones)
	println(threes)
	
	return ones * (threes + 1)
}

fun populateInputStrings(): List<Int> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<Int>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine().toInt())
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