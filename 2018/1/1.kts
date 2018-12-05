import java.io.File
import java.util.Scanner

/* 
 * Calculates the total given an input file
 */

fun calculateTotal(file: File): Int {
val inputScanner = Scanner(file)
	var total = 0
	while (inputScanner.hasNextLine()) {
		val currentLine = inputScanner.nextLine()
		val value = currentLine.substring(1).toInt()
		when (currentLine[0].toString()) {
			"+" -> total += value
			"-" -> total -= value
		}
	}
	return total
}

fun getInputFile(): File {
	val input = if (args.isNotEmpty() && args[0] != "") { 
		args[0] 
	} else { 
		"input" 
	}
	return File(input)
}

println(calculateTotal(getInputFile()))