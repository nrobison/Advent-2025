package dailys

import java.io.File

class Day3(){
    var totalValues = 0
    fun partOne(input: File){
        val batteries = input.readLines()
        batteries.forEach{ battery ->
            var values = battery.toCharArray().map { it -> it.digitToInt() }
            val max = values.subList(0,values.size-1).max()
            val maxIndex = values.indexOf(max)
            val secondMax = values.subList(maxIndex+1, values.size).max()
            totalValues += (max*10) + secondMax
        }
        println("Total Value: $totalValues")
    }

    fun partTwo(input: File) {
        val batteries = input.readLines()
        var total = 0L
        batteries.forEach { battery ->
           val value = findLargest12(battery)
            println("Value returned: $value")
            total += value.toLong()
        }
        println("total value: ${total}")
    }

    fun findLargest12(numbers: String): String {
        val neededLength = 12
        val selectedNumbers = StringBuilder()
        val numbersLength = numbers.length

        for (i in numbers.indices) {
            val currentDigit = numbers[i]
            //Look while have the picked digit is less current one and if we remove one we still have 12 or neededlength
            while (selectedNumbers.isNotEmpty() &&
                selectedNumbers.last() < currentDigit &&
                (selectedNumbers.length - 1 + (numbersLength - i) >= neededLength)) {
                selectedNumbers.deleteCharAt(selectedNumbers.length - 1)
            }
            if (selectedNumbers.length < neededLength) {
                selectedNumbers.append(currentDigit)
            }
        }
        return selectedNumbers.toString()
    }
}