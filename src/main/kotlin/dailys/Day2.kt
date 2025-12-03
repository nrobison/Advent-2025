package dailys

import java.io.File

data class ProductRange(val start: Long, val end: Long)

class Day2(){
    var invalidNumbers = 0L
    fun getAllInvalidIds( input: File,isPartTwo: Boolean){
        val ranges = input.readLines().first().split(',')
        ranges.forEach{
            it ->
                val range = it.split('-')
                val product = ProductRange(range[0].toLong(), range[1].toLong())
                //println("Checking Range of ${product.start} to ${product.end}")
                if(!isPartTwo) {
                    checkRangeForInvalidPartOne(product)
                }
                else{
                    checkRangeForInvalidPartTwo(product)
                }
            //productRanges.add(ProductRange(range[0].toInt(), range[1].removeRange(0,1).toInt()))

        }
        println("Total of invalids is $invalidNumbers")


    }
    //Original Brute Force
    fun checkRangeForInvalidPartOne(product: ProductRange){
        val numberRange = product.start..product.end
        numberRange.forEach{ item ->
            val stringOfItem = item.toString()
            if(stringOfItem.length % 2 == 0){
                val partOne = stringOfItem.substring(0,stringOfItem.length/2)
                val partTwo = stringOfItem.substring(stringOfItem.length/2)
                if(partOne == partTwo){
                    invalidNumbers += item
                   // println("We found an invalid number: $stringOfItem")
                }
            }
        }
    }

    fun checkRangeForInvalidPartTwo(product: ProductRange){
        val numberRange = product.start..product.end
        numberRange.forEach { item ->
            val stringOfItem = item.toString()
            //\d+ Checks for any digit (0-9) 1 or unlimited times, \1+$ then matches the first group 1 to unlimited times end of line
            val regexForRepeatDigits = Regex("^(\\d+)\\1+$")
            if(regexForRepeatDigits.matches(stringOfItem)) {
                invalidNumbers += stringOfItem.toLong()
              //  println("We found an invalid number $stringOfItem")
            }

        }
    }

}