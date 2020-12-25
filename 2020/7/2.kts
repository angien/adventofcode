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

	return countBags(rules, "shiny gold") - 1
}

fun countBags(rules: Map<String, Map<String, Int>>, bag: String): Int {
	var sum = 1
	val values = rules[bag] ?: mapOf<String, Int>()
	values.forEach {
		val innerBags = countBags(rules, it.key)
		sum = sum + (innerBags * it.value)
	}

	return sum
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