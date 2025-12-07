package dailys

import java.io.BufferedReader
import java.io.File

class Day6 {

    fun partOne(input: File){
        val lines = input.readLines()
        val values = lines.map { it -> it.split(" ").filter { it != "" }}
        val extractedValues = mutableListOf<String>()
        val maxX = values[0].size
        var totalValues = 0L
        for(x in 0..<maxX) {
            values.forEach { line ->
                extractedValues.add(line[x])
            }
            if(extractedValues.last() == "*") totalValues += multiplyValues(extractedValues)
            else totalValues += addValues(extractedValues)
            extractedValues.clear()
        }
        println("Total values: $totalValues")

    }

    fun partTwo(input: File){
        val lines = input.readLines()
        val symbolRegex = Regex("""[+*]\s*""")
        val symbols =  symbolRegex.findAll(lines[lines.size-1]).map { it.value.length}.toMutableList()
        //Account for last one not having a space between
        symbols[symbols.size-1] = symbols[symbols.size-1]
        var totalValues = 0L
        val numbersRightToLeft = mutableListOf<String>()
        var position = 0
        //println("Lines are: $lines")
        for(sizes in symbols){
            //We need to grab each char
            for(char in position..<(sizes + position)){
                var number = ""
                for(line in 0..<lines.size-1 ){
                   // println("Currnet number: $number and attempting to add [$line][$char]" )
                    number = number.plus(lines[line][char])
                }
                numbersRightToLeft.add(number)
            }
            if(lines[lines.size-1].substring(position,sizes+position).contains('*')){
                totalValues += multiplyValues(numbersRightToLeft)
            }
            else totalValues += addValues(numbersRightToLeft)
            numbersRightToLeft.clear()
            position += sizes
        }
        println("Total values: $totalValues")

    }
    
    fun multiplyValues(values: List<String>): Long{
        var total = 1L
       //println("Multiplying : $values")
        values.forEach { value ->
            if(value.isNotBlank() && value != "*") total *= value.trim().toLong()
        }
        return total
    }
    fun addValues(values: List<String>): Long{
        var total = 0L
        //println("Adding : $values")
        values.forEach { value ->
            if(value.isNotBlank() && value != "+") total += value.trim().toLong()
        }
        return total
    }
}