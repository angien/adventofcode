import java.io.File
import java.util.Scanner

/* 
 * calculate manhattan distance
 */

class Solution(val inputStrings: List<String>) {

	fun solution(): Int {
		var currDirection = 0

		var e = 0
		//var w = 0
		//var s = 0
		var n = 0

		inputStrings.forEach {
			val cmd = it.substring(0, 1)
			val a = it.substring(1).toInt()

			when (cmd) {
				"N" -> n = n + a
				"S" -> n = n - a
				"E" -> e = e + a
				"W" -> e = e - a
				"F" -> {
					when (currDirection) {
						0 -> e = e + a
						1 -> n = n - a
						2 -> e = e - a
						3 -> n = n + a
					}
				}
				"L" -> {
					currDirection = getNewDirection(cmd, currDirection, a)
				}
				"R" -> {
					currDirection = getNewDirection(cmd, currDirection, a)
				}
			}
		}

		return Math.abs(e) + Math.abs(n)
	}

	private fun getNewDirection(s: String, curr: Int, degrees: Int): Int {
		val r = degrees / 90
		if (s == "L") {
			val n = curr - r
			if (n < 0) {
				return 4 + n
			} else {
				return n
			}
		} else {
			return (curr + r) % 4
		}
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