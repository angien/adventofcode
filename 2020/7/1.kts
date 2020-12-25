import java.io.File
import java.util.Scanner

/* 
 * Count unique answers per group
 */

fun solution(inputStrings: List<String>): Int {
	val rules = mutableMapOf<String, Map<String, Int>>()
	inputStrings.forEach {
		val mapping = it.split(" contain ")
		val key = mapping[0].replace(" bags", "")
		val values = mapping[1].split(", ")
		val updatedValues = mutableMapOf<String, Int>()
		values.forEach { value ->
			val newValue = value.replace(" bags", "").replace(" bag", "").replace(", ", "").replace(".", "")
			if (newValue != "no other") {
				val count = newValue.substring(0, 1).toInt()
				var color = newValue.substring(2)

				updatedValues.put(color, count)
			}
		}
		rules.put(key, updatedValues)
	}
	
	var sum = 0

	rules.filter { it.value.isNotEmpty() }.forEach {
		if (canContain(rules, it.value)) {
			sum = sum + 1
		}
	}
	
	return sum
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