package dailys

import java.io.File
import kotlin.math.abs

data class dialMove(val direction: Char, var rotation: Int)

class Day1 {
    val max = 99
    val min = 0
    var currentPosition = 50
    var countOfZeros = 0

    fun rotateDial(movement: dialMove, isDayTwo: Boolean){
        val isCurrentZero = currentPosition == 0
        if(movement.rotation >= 100) {
            if(isDayTwo){
                countOfZeros += ((movement.rotation - (movement.rotation %100)) / 100)
            }
            movement.rotation %= 100
        }
        if(movement.direction == 'L'){
            //println("Current position $currentPosition , moving L ${movement.rotation}")
            //If it's over 100 we can reduce it
            if((currentPosition - movement.rotation) < min ) {
                currentPosition = max - abs(currentPosition - movement.rotation) + 1
                if(isDayTwo && currentPosition != 0 && !isCurrentZero) countOfZeros += 1
            }
            else{
                currentPosition -= movement.rotation
            }
            //println("Now at $currentPosition")
        }
        if(movement.direction == 'R'){
            //println("Current position $currentPosition , moving L ${movement.rotation},")
            if(movement.rotation >= 100) movement.rotation %= 100
            if(currentPosition + movement.rotation > max ) {
                currentPosition = (currentPosition + movement.rotation) - max - 1
                if(isDayTwo && currentPosition != 0 && !isCurrentZero) countOfZeros += 1
            }
            else{
                currentPosition += movement.rotation
            }
            //println("Now at $currentPosition")
        }
        //println("Current count of Zeros : ${countOfZeros}")
        if(currentPosition == 0) {
           // println("At 0 so adding another ")
            countOfZeros +=1
        }
    }

    fun decryptPassword(filePath: File, isDayTwo: Boolean){
        for(line in filePath.readLines()){
            val direction:Char = line.first()
            val movement = line.substring(1).toInt()
            val dial = dialMove(direction, movement)
            rotateDial(dial,isDayTwo)
        }
        println("Finished dial movements. The number of zeros : $countOfZeros times")
    }
}