package dailys

import java.io.File

class Day7 {
    data class Point(val x: Int, val y: Int)
    val visitedNodes = mutableListOf<Point>()
    var manifoldMap = mutableMapOf<Point,Char>()
    var numberOfSplits = 0
    //HashMap for fast look up and don't care about order
    val pathHashMap = hashMapOf<Point,Long>()


    fun fullSolution(input: File){
        val manifold = input.readLines()
        //Generate a map of key, pairs of Point , Char
        manifoldMap = manifold.flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
               Point(x,y) to char
            }
            //I don't like doing this. need to revisit
        }.toMap().toMutableMap()
        //Get the S position
        val startPosition = manifoldMap.entries.find { it.value == 'S' }?.key
        if (startPosition != null) {
            //Start Part one
            progressNodePartOne(startPosition)
            println("Searched and found number of splits: $numberOfSplits")
            //Start on Part Two
            val result = recursivePartTwo(startPosition)
            println("Using recursion to find all paths we have $result")
        }
    }
    
    fun progressNodePartOne(point : Point){
        //Look down at next, if out of bounds (no point exists we are done)
        //Check the split node too if we already visited don't continue
        if(manifoldMap.containsKey(point) && !visitedNodes.contains(point)){
            if(manifoldMap[point] == '^'){
                numberOfSplits += 1
                //Left
                progressNodePartOne(Point(point.x -1, point.y))
                visitedNodes.add(Point(point.x -1, point.y))
                //Right
                progressNodePartOne(Point(point.x +1, point.y))
                visitedNodes.add(Point(point.x +1, point.y))
            }
            //Continue down
            else{
                progressNodePartOne(Point(point.x,point.y +1))
            }
            
        }
    }
    
    //DFS blew up. Had to find a way to keep track of successful paths at a point
    fun recursivePartTwo(point: Point) : Long{
        //If we already have it return it
        pathHashMap[point]?.let { return it }
        //Move down one 
        val moveNext = Point(point.x,point.y+1)
        val nextChar = manifoldMap[moveNext]
        var result = 0L
        //If it's not a split just use it and go down
        if(nextChar == '.') result = recursivePartTwo(moveNext)
        else if(nextChar == '^'){
            //Get the results of Left AND Right. 
            val leftResult = recursivePartTwo(Point(moveNext.x-1, moveNext.y))
            val rightResult = recursivePartTwo(Point(moveNext.x +1, moveNext.y))
            result = leftResult + rightResult
        }
        else result = 1L
        //Add our results to the hash map 
        pathHashMap[moveNext] = result
        //Return the result either 1L or combination of Left + Right recursive 
        return result
    }
}
