import java.io.File
import java.util.Scanner

class Solution(val inputStrings: List<List<String>>) {
	val boards = mutableListOf<List<List<Node>>>()
	val finishedBoards = MutableList(inputStrings.size - 1) { false }
	fun solution(): Int {
		val draws = inputStrings[0][0].split(',')

		inputStrings.forEachIndexed { i, td ->
			if (i != 0) {
				val board = mutableListOf<List<Node>>()
				td.forEach {
					val row = mutableListOf<Node>()
					it.split(' ').forEach { num ->
						if (num.toIntOrNull() != null) {
							row.add(Node(num.toInt(), false))
						}
					}
					board.add(row)
				}
				boards.add(board)
			}
		}

		draws.forEach {
			markBoards(it.toInt())
			val winner = checkBoards()
			if (winner != null && finishedBoards.filter { it == false }.isEmpty()) {
				return winner * it.toInt()
			}
		}
		return 0
	}

	private fun markBoards(num: Int) {
		boards.forEach { board ->
			board.forEach { row ->
				row.forEach {
					if (num == it.value) {
						it.marked = true
					}
				}
			}
		}
	}

	private fun checkBoards(): Int? {
		var toReturn: Int? = null
		boards.forEachIndexed { index, board ->
			if (!finishedBoards[index]) {

				// check each row
				board.forEach { row ->
					if (row.filter { it.marked }.size == row.size) {
						finishedBoards[index] = true
						toReturn = sum(board)
					}
					
				}

				for (i in 0 until board[0].size) { // flip would be better
					val temp = mutableListOf<Node>()
					board.forEach {
						temp.add(it[i])
					}
					if (temp.filter { it.marked }.size == temp.size) {
						finishedBoards[index] = true
						toReturn = sum(board)
					}
				}
			}
		}
		return toReturn
		
	}

	private fun sum(board: List<List<Node>>): Int {
		var result = 0
		board.forEach {
			it.forEach { node ->
				if (!node.marked) {
					result += node.value
				}
			}
		}
		return result
	}
}

data class Node(
	val value: Int,
	var marked: Boolean)

/* ------------ */

println(Solution(populateInputStringsAndBreaks()).solution())

fun populateInputStringsAndSplit(split: String = " "): List<List<String>> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<List<String>>()

	while (inputScanner.hasNextLine()) {
		val next = inputScanner.nextLine()
		inputStrings.add(next.split(split))
	}
	return inputStrings
}

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