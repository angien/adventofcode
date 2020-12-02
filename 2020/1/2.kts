import java.io.File
import java.util.Scanner

/* 
 * Finds the 3 numbers that add up to 2020 and multiplies them together
 */

fun solution(inputStrings: List<String>): Int {
	val map = mutableMapOf<Int, Int>()
	var toReturn = 0

	inputStrings.forEach {
		val value = it.toInt()

		val difference = 2020 - value
		map.put(value, difference)

		map.forEach { key, diff ->
			val newMultiplier = findSum(diff, inputStrings)
			if (newMultiplier != 0) {
				toReturn = key * newMultiplier
			}
		}
	}
	return toReturn
}

fun findSum(sum: Int, inputStrings: List<String>): Int {
	val map = mutableMapOf<Int, Int>()

	inputStrings.forEach {
		val value = it.toInt()

		val difference = sum - value
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