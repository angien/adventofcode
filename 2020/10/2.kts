import java.io.File
import java.util.Scanner

/* 
 * Find all paths to end adapter
 */

fun solution(inputStrings: List<Long>): Long {
	val sorted = inputStrings.toMutableList()
	sorted.add(0)
	sorted.sort()

	val map = mutableMapOf<Long, Long>()

	sorted.forEachIndexed { currIndex, currValue ->
		if (currIndex == 0) {
			map.put(currValue, 1)
		} else {
			sorted.filterIndexed { index, _ -> index < currIndex}.forEach { value ->
				if (currValue - value <= 3) {
					val currSum = map.get(currValue) ?: 0
					val toAdd = map.get(value) ?: 0
	
					map.put(currValue, currSum + toAdd)
				}
			}
		}
	}

	return map.get(sorted.last()) ?: 0
}

fun populateInputStrings(): List<Long> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<Long>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine().toLong())
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