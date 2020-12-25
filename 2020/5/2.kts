import java.io.File
import java.util.Scanner

/* 
 * 0-127, F is lower half, B is upper half
 * 0-7, R is upper half, L is lower half
 */

fun solution(inputStrings: List<String>) {
	val seats = mutableMapOf<Int, List<Int>>()
	inputStrings.forEach {
		val rowString = it.take(7)
		val colString = it.takeLast(3)
		val row = calculate(rowString, 0, 127)
		val col = calculate(colString, 0, 7)

		val curr = seats[row] ?: listOf()

		seats.put(row, curr + listOf(col))
	}


	val filtered = seats.filter { 
		 it.value.size < 8 && it.value.size > 0
	}

	filtered.forEach {
		val r = it.key
		val c = getColumn(it.value)
		val id = r * 8 + (28 - c)
		println("$r: $id")
	}
}

fun getColumn(cols: List<Int>): Int {
	var sum = 0
	cols.forEach {
		sum = sum + it
	}
	return sum
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

solution(populateInputStrings())