import java.io.File
import java.util.Scanner

/* 
 * Print acc from program
 */

fun solution(inputStrings: List<String>): Int {
	var curr = 0
	var acc = 0
	val visited = mutableSetOf<Int>()
	while (curr < inputStrings.size) {
		if (visited.contains(curr)) {
			break
		}
		val line = inputStrings[curr].split(" ")
		val cmd = line[0]
		val value = line[1].toInt()

		visited.add(curr)
		
		when (cmd) {
			"jmp" -> curr = curr + value
			"acc" -> { 
				acc = acc + value
				curr = curr + 1
			}
			"nop" -> curr = curr + 1
		}
	}
	
	return acc
}

fun canContain(rules: Map<String, Map<String, Int>>, values: Map<String, Int>): Boolean {
	if (values.contains("shiny gold")) {
		return true
	}
	values.forEach {
		val newValues = rules[it.key] ?: mapOf<String, Int>()
		if (canContain(rules, newValues)) {
			return true
		}
	}

	return false
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