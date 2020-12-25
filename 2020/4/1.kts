import java.io.File
import java.util.Scanner

/* 
 * How many valid passports
 */

fun solution(inputStrings: List<String>): Int {
	val passports = getPassports(inputStrings)

	var valid = 0
	passports.forEach { map ->

		if (map.containsKey("byr") && map.containsKey("iyr") &&
		map.containsKey("eyr") && map.containsKey("hgt") && map.containsKey("hcl") && map.containsKey("ecl") && map.containsKey("pid")) {
			valid = valid + 1
		}
	}
	
	return valid
}

fun getPassports(inputStrings: List<String>): List<Map<String, String>> {
	val passports = mutableListOf<Map<String, String>>()

	var currPassport = ""
	inputStrings.forEach { line ->
		if (line.length != 0) {
			currPassport = currPassport + " " + line
		} else {
			passports.add(mapFields(currPassport))
			currPassport = ""
		}
	}
	passports.add(mapFields(currPassport))

	return passports
}

fun mapFields(passport: String): Map<String, String> {
	val fields = mutableMapOf<String, String>()

	passport.replace("\n", "").split(" ").filter { it.isNotEmpty() }.forEach {
		val keyValue = it.split(":")
		fields.put(keyValue[0], keyValue[1])
	}
	return fields
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