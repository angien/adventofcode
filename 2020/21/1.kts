import java.io.File
import java.util.Scanner

/* 
 * encoded ingredients list but allergens are not encoded
 * count number of recipes that cant be allergens
 */

data class Recipe(
	val allergens: List<String>,
	val ingredients: List<String>
)

class Solution(val inputStrings: List<String>) {

	val recipes = mutableListOf<Recipe>()

	fun solution(): Int {

		var allIngredients = listOf<String>()
		inputStrings.forEach {
			val line = it.split(" (contains ")
			val ingredients = line[0].split(" ")
			val allergens = line[1].replace(",", "").replace(")", "").split(" ")
			recipes.add(Recipe(allergens, ingredients))
			allIngredients = allIngredients + ingredients
		}

		val map = mutableMapOf<String, List<String>>()

		recipes.forEach { r ->
			r.allergens.forEach {
				val possibleIngredients = map[it]
				if (possibleIngredients == null) {
					map[it] = r.ingredients
				} else {
					map[it] = possibleIngredients.filter { r.ingredients.contains(it) }
				}
			}
		}

		var mappedIngredients = listOf<String>()
		map.forEach {
			mappedIngredients = mappedIngredients + it.value
		}

		val noAllergies = allIngredients.distinct().minus(mappedIngredients.distinct())

		var count = 0
		allIngredients.forEach {
			if (noAllergies.contains(it)) {
				count = count + 1
			}
		}
		return count
	}
}

/* ------------ */

fun populateInputStringsAndBreaks(): List<List<String>> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<List<String>>()
	var innerString = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		val next = inputScanner.nextLine()
		if (next == "") {
			inputStrings.add(innerString)
			innerString = mutableListOf<String>()
		} else {
			innerString.add(next)
		}
	}
	inputStrings.add(innerString)
	return inputStrings
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

println(Solution(populateInputStrings()).solution())