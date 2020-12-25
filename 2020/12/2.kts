import java.io.File
import java.util.Scanner

/* 
 * calculate manhattan distance with a waypoint
 */

class Solution(val inputStrings: List<String>) {

	fun solution(): Int {

		var e = 0
		var n = 0

		var we = 10
		var wn = 1

		inputStrings.forEach {
			val cmd = it.substring(0, 1)
			val a = it.substring(1).toInt()

			when (cmd) {
				"N" -> wn = wn + a
				"S" -> wn = wn - a
				"E" -> we = we + a
				"W" -> we = we - a
				"F" -> {
					e = e + (we * a)
					n = n + (wn * a)
				}
				"L" -> {
					val d = a / 90
					for (i in 0 until d) {
						val tmp = -wn
						wn = we
						we = tmp

						// rotate left 90 degrees
						// (e, n) -> (-n, e)
					}
				}
				"R" -> {
					val d = a / 90
					for (i in 0 until (d * 3)) {
						val tmp = -wn
						wn = we
						we = tmp
					}
				}
			}
		}

		return Math.abs(e) + Math.abs(n)
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