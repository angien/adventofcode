import java.io.File
import java.util.Scanner

/* 
 * calculate manhattan distance
 */

class Solution(val inputStrings: List<String>) {

	val time = inputStrings[0].toInt()
	val busses = busSchedule()

	fun busSchedule(): List<Int> {
		val b = mutableListOf<Int>()
		inputStrings[1].split(',').forEach {
			if (it != "x") {
				b.add(it.toInt())
			}
		}
		return b
	}

	fun solution(): Int {

		val nextBusses = mutableMapOf<Int, Int>()

		busses.forEach {
			var next = 0
			while (next < time) {
				next = next + it
			}
			nextBusses.put(it, next)
		}

		var s = 0
		var min = Int.MAX_VALUE
		nextBusses.forEach {
			if (it.value < min) {
				min = it.value
				s = it.key
			}
		}

		return s * (min - time)
	}
}

/* ------------ */

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

println(Solution(populateInputStrings()).solution())