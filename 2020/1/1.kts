import java.io.File
import java.util.Scanner

/* 
 * Finds the 2 numbers that add up to 2020 and multiplies them together
 */

fun solution(inputStrings: List<String>): Int {
	val map = mutableMapOf<Int, Int>()

	inputStrings.forEach {
		val value = it.toInt()

		val difference = 2020 - value
		map.put(value, difference)

		if (map.contains(difference)) {
			return value * difference
		}
	}
	return 0
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