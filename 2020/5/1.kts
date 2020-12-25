import java.io.File
import java.util.Scanner

/* 
 * 0-127, F is lower half, B is upper half
 * 0-7, R is upper half, L is lower half
 */

fun solution(inputStrings: List<String>): Int {
	var max = 0
	inputStrings.forEach {
		val rowString = it.take(7)
		val colString = it.takeLast(3)
		val row = calculate(rowString, 0, 127)
		val col = calculate(colString, 0, 7)

		max = Math.max(max, row * 8 + col)
	}
	
	return max
}

fun calculate(s: String, start: Int, end: Int): Int {
	var min = start
	var max = end
	s.forEach {
		val mid = (max + min) / 2
		if (it == 'F' || it == 'L') {
			max = mid
		} else {
			min = mid
		}
	}
	return max
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