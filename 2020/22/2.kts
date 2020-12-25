import java.io.File
import java.util.Scanner

/* 
 * get corners of the tiles when they are put together after rotations and flips
 */

data class Deck(
	val player1: List<String>,
	val player2: List<String>
)

class Solution(val inputStrings: List<List<String>>) {

	fun solution(): Long {

		val player1 = inputStrings[0].filter { it.contains("Player").not() }.toMutableList()
		val player2 = inputStrings[1].filter { it.contains("Player").not() }.toMutableList()

		val endDeck = findWinner(Deck(player1, player2))

		var r = if (endDeck.player1.isEmpty()) endDeck.player2 else endDeck.player1

		var sum = 0L
		var count = r.size
		r.forEach {
			sum = sum + (it.toLong() * count)
			count = count - 1
		}

		return sum
	}

}

fun findWinner(deck: Deck): Deck {
	val player1 = deck.player1.toMutableList()
	val player2 = deck.player2.toMutableList()
	val allPlays = mutableListOf<Deck>(deck.copy())
	while (player1.isNotEmpty() && player2.isNotEmpty()) {
		val p1 = player1.removeFirst().toInt()
		val p2 = player2.removeFirst().toInt()

		var winner: String
		if (player1.size >= p1 && player2.size >= p2) {
			val endDeck = findWinner(Deck(player1.subList(0,p1), player2.subList(0,p2)))
			winner = if (endDeck.player1.isEmpty()) "p2" else "p1"
		} else {
			winner = if (p1 > p2) {
				"p1"
			} else {
				"p2"
			}
		}

		if (winner == "p1") {
			player1.add(p1.toString())
			player1.add(p2.toString())
		} else {
			player2.add(p2.toString())
			player2.add(p1.toString())
		}

		if (allPlays.contains(Deck(player1.toMutableList(), player2.toMutableList()))) {
			return Deck(player1.toMutableList(), mutableListOf())
		} else {
			allPlays.add(Deck(player1.toMutableList(), player2.toMutableList()))
		}
	}

	return Deck(player1.toMutableList(), player2.toMutableList())

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

println(Solution(populateInputStringsAndBreaks()).solution())