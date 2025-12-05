package dailys

import java.io.File

class Day5 {

    fun partOne(input: File){
        val lines = input.readLines()
        //Originally used HashMap but this was a problem. Keep ranges and use the contains check
        val validProducts = mutableListOf<LongRange>()
        var numberOfFreshIngredients = 0
        lines.forEach { line ->
            //Find the ranges and skip
            if(line.contains("-")){
                val ranges = line.split('-')
                val rangeToAdd = ranges[0].toLong()..ranges[1].toLong()
                validProducts.add(rangeToAdd)
            }
            else if(line.isNotEmpty() && line.isNotEmpty()){
                //Check if item is in range
                val itemCheck = line.toLong()
                for(item in validProducts){
                    if(item.contains(itemCheck)){
                        numberOfFreshIngredients++
                        break
                    }
                }
            }
        }
        println("We found $numberOfFreshIngredients fresh ingredients")
    }

    fun partTwo(input:File){
        val lines = input.readLines()
        var totalNumberOfAvailableFreshIngredients = 0L
        val validProducts = mutableListOf<LongRange>()
        lines.forEach { line ->
            //Find the ranges and don't worry about the other list of products
            if (line.contains("-")) {
                val ranges = line.split('-')
                val rangeToAdd = ranges[0].toLong()..ranges[1].toLong()
                validProducts.add(rangeToAdd)
            }
        }
        //Must sort the list for this to work so we can check current and adjacent
        validProducts.sortBy { it.first}
        val mergedList = mutableListOf<LongRange>()
        var currentRange = validProducts[0]
        //Combine the ranges so all ranges are unique and we don't have any overlaps. This allows us to count the items in the range
        for (index in 1 until validProducts.size) {
            val nextRange = validProducts[index]
            // Check if ranges overlap OR are adjacent
            if (nextRange.first <= currentRange.last + 1) {
                // Merge them so start stays the same but new end
                val newEnd = maxOf(currentRange.last, nextRange.last)
                currentRange = currentRange.first..newEnd
            } else {
                // No overlap so add the range and move on to next
                mergedList.add(currentRange)
                totalNumberOfAvailableFreshIngredients+= (currentRange.last - (currentRange.first-1) )
                currentRange = nextRange
            }
        }
        // Add the final range
        mergedList.add(currentRange)
        totalNumberOfAvailableFreshIngredients+= (currentRange.last - (currentRange.first-1) )
//      Original check but can do it when we combine ranges since we are sorted. Removes an additional N cost
//        mergedList.forEach{ range ->
//            //We are 1 off here so add 1 or minus one from first
//            val itemsToAdd = range.last - (range.first-1)
//            totalNumberOfAvailableFreshIngredients+= itemsToAdd
//        }
        println("Total number of fresh products : $totalNumberOfAvailableFreshIngredients")
    }
}