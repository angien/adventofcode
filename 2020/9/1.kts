import java.io.File
import java.util.Scanner

/* 
 * finds first number that doesnt have 2 sum in the previous 25 
 */

fun solution(inputStrings: List<String>): Int {
	val preamble = 25
	var curr = preamble
	
	while (curr < inputStrings.size) {
		val start = curr - preamble
		val window = inputStrings.subList(start, curr)
		if (hasSum(inputStrings[curr].toInt(), window).not()) {
			return inputStrings[curr].toInt()
		}
		curr = curr + 1
	}
	
	return 0
}

fun hasSum(target: Int, window: List<String>): Boolean {

        val map = mutableMapOf<Int, Int>()
        window.forEachIndexed { index, value ->
        	val num = value.toInt()
            val diff = target - num
            map.get(diff)?.let {
                return true
            }
            map.put(num,index)
        }
        return false
    
}

fun populateInputStrings(): List<String> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine())
	}
	if (inputStrings.isEmpty()) {
		throw RuntimeException("No input data")
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