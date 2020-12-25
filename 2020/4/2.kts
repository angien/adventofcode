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
		map.containsKey("eyr") && map.containsKey("hgt") && map.containsKey("hcl") && map.containsKey("ecl") && map.containsKey("pid") && validateValues(map)) {
			valid = valid + 1
		}
	}
	
	return valid
}

fun validateValues(m: Map<String, String>): Boolean {

	return (checkRange(m.get("byr") ?: "",1920,2002))
	&& (checkRange(m.get("iyr") ?: "",2010,2020))
	&& (checkRange(m.get("eyr") ?: "",2020,2030))
	&& (checkHeight(m.get("hgt") ?: ""))
	&& ("#[\\da-f]{6}".toRegex() matches (m.get("hcl") ?: ""))
	&& (m.get("ecl") in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth"))
	&& ("\\d{9}".toRegex() matches (m.get("pid") ?: ""))
}

fun checkHeight(height: String): Boolean {
	try {
		if (height.endsWith("cm")) {
			return checkRange(height.removeSuffix("cm"), 150, 193)
		} else if (height.endsWith("in")) {
			return checkRange(height.removeSuffix("in"), 59, 76)
		} else {
			return false
		}
	} catch (e: Exception) {
		return false
	}
}

fun checkRange(s: String, min: Int, max: Int): Boolean {
	try {
		return s.toInt() >= min && s.toInt() <= max
	} catch (e: Exception) {
		return false
	}
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