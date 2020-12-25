import java.io.File
import java.util.Scanner

/* 
 * How many trees encountered going down slope: 
Right 1, down 1.
Right 3, down 1.
Right 5, down 1.
Right 7, down 1.
Right 1, down 2.
 */

fun solution(inputStrings: List<String>) {

	val a = helper(inputStrings, 1, 1)
	val b = helper(inputStrings, 3, 1)
	val c = helper(inputStrings, 5, 1)
	val d = helper(inputStrings, 7, 1)
	val e = helper(inputStrings, 1, 2)
	
	println("$a $b $c $d $e")
}

fun helper(inputStrings: List<String>, right: Int, down: Int): Int {
	var maxSize = inputStrings[0].length
	var currPos = 0
	var trees = 0

	for (i in 0 until inputStrings.size step down) {
		if (i != 0) {
			currPos = currPos + right
			if (currPos >= maxSize) {
				currPos = currPos % maxSize
			}

			if (inputStrings[i][currPos] == '#') {
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

solution(populateInputStrings())