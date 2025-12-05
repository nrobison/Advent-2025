package dailys

import java.io.File

class Day4(){

    fun partOne(input: File){
        val paperInput: List<List<Char>> = input.readLines().map { it -> it.toList() }
        var numberOfValidMovements = 0
        var currentY = 0
        var currentX = 0
        paperInput.forEach { line ->
            line.forEach {
                if(paperInput[currentY][currentX] == '@'){
                    //println("Checking point ($currentX,$currentY)")
                    val addPoint = checkOtherDirections(Pair(currentX,currentY),paperInput)
                    if(addPoint) numberOfValidMovements++
                }
                currentX++
            }
            currentY++
            currentX = 0
        }
        println("Number of Valid paper moves: $numberOfValidMovements")
    }

    fun partTwo(input:File){
        val paperInput: MutableList<MutableList<Char>> = input.readLines().map { it -> it.toMutableList() }.toMutableList()
        var canContinue = true
        var totalOfMovements = 0
        while(canContinue) {
            var numberOfValidMovements = 0
            var currentY = 0
            var currentX = 0
            paperInput.forEach { line ->
                line.forEach {
                    if (paperInput[currentY][currentX] == '@') {
                        //println("Checking point ($currentX,$currentY)")
                        val addPoint = checkOtherDirections(Pair(currentX, currentY), paperInput)
                        if (addPoint) {
                            numberOfValidMovements++
                            paperInput[currentY][currentX] = '.'
                        }
                    }
                    currentX++
                }
                currentY++
                currentX = 0
            }
            totalOfMovements += numberOfValidMovements
            //If we go through it enough times and get no new valids we stop
            if(numberOfValidMovements == 0) canContinue = false
        }
        println("Total number after passes is $totalOfMovements")
    }

    fun checkOtherDirections(checkPoint: Pair<Int,Int>, input: List<List<Char>>) : Boolean
    {
        val mapOfPoints = (-1..1).flatMap{xChange ->
            (-1..1).map{ yChange ->
                (xChange + checkPoint.first ) to (yChange + checkPoint.second)
            }.filter { it != checkPoint }
        }
        var countsOfRolls = 0
        mapOfPoints.forEach { point ->
            //Are we in bounds?
            if( point.first >= 0 &&  point.first < input[0].size && point.second >= 0 && point.second < input.size ){
                //Check char of point
                if(input[point.second][point.first] == '@') countsOfRolls +=1
            }
        }
       // if(countsOfRolls < 4) println("Found a valid point at $checkPoint")
        return countsOfRolls < 4

    }
}