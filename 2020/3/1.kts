import java.io.File
import java.util.Scanner

/* 
 * How many trees encountered going down slope right 3, down 1
 */

fun solution(inputStrings: List<String>): Int {
	var maxSize = inputStrings[0].length
	var currPos = 0
	var trees = 0

	inputStrings.forEachIndexed { index, line ->
		if (index != 0) {
			currPos = currPos + 3
			if (currPos >= maxSize) {
				currPos = currPos % maxSize
			}

			if (line[currPos] == '#') {
				trees = trees + 1
			}
		}
		
	}
	
	return trees
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