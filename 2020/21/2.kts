import java.io.File
import java.util.Scanner

/* 
 * return finalized ingredients associated to allergen, sorted by allergens name
 */

data class Recipe(
	val allergens: List<String>,
	val ingredients: List<String>
)

class Solution(val inputStrings: List<String>) {

	val recipes = mutableListOf<Recipe>()

	fun solution(): String {

		var allIngredients = listOf<String>()

		var allAllergens = listOf<String>()
		inputStrings.forEach {
			val line = it.split(" (contains ")
			val ingredients = line[0].split(" ")
			val allergens = line[1].replace(",", "").replace(")", "").split(" ")
			recipes.add(Recipe(allergens, ingredients))
			allIngredients = allIngredients + ingredients
			allAllergens = (allAllergens + allergens).distinct()
		}

		val map = mutableMapOf<String, MutableList<String>>()

		recipes.forEach { r ->
			r.allergens.forEach {
				val possibleIngredients = map[it]
				if (possibleIngredients == null) {
					map[it] = r.ingredients.toMutableList()
				} else {
					map[it] = possibleIngredients.filter { r.ingredients.contains(it) }.toMutableList()
				}
			}
		}

		val sortedAllergens = allAllergens.toMutableList()
		sortedAllergens.sort()

		for (i in 0 until 1679) {
			map.values.forEach {
				if (it.size == 1) {
					map.forEach { _, v ->
						if (v.size > 1) {
							v.remove(it[0])
						}
					}
				}
			}
		}

		val toReturn = mutableListOf<String>()
		sortedAllergens.forEach {
			toReturn.add(map[it]!![0])
		}

		return toReturn.joinToString(",")
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