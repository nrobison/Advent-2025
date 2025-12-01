import java.io.File

fun main() {
    var dayOne = dailys.Day1()
    dayOne.decryptPassword(File("InputFiles/day1.txt"),false)
    dayOne = dailys.Day1()
    dayOne.decryptPassword(File("InputFiles/day1.txt"),true)
}