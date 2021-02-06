package tictactoe

import java.util.*

val scanner = Scanner(System.`in`)
var userInputStr : String = ""
var gameStatus = "start"
var playerMove : Char = 'X'

fun main() {
    userInputStr = "_________"
    battlefield(userInputStr)
    takeCoordinates()
}

fun takeCoordinates() {
    gameStatus = "in progress"
    println("Enter the coordinates:")
    val coordinates = arrayOf(scanner.next()) + arrayOf(scanner.next())
    checkCoordinates(coordinates,userInputStr)
}

fun checkCoordinates(userCoordinates: Array<String>, userInput: String) {
    var validation = false
    val limit = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val occupiedCell = "XO"
    val userInputArray = userInput.toCharArray()
    var x = 0
    var y = 0

    if (userCoordinates[0] in limit && userCoordinates[1] in limit) {
        x = userCoordinates[0].toInt()
        y = userCoordinates[1].toInt()
        when {
            x > 3 -> {
                println("Coordinates should be from 1 to 3!")
                takeCoordinates()
            }
            y > 3 -> {
                println("Coordinates should be from 1 to 3!")
                takeCoordinates()
            }
            else -> validation = true
        }
    } else {
        println("You should enter numbers!")
        takeCoordinates()
    }

    if (validation) {
        if (x == 1) {
            if (userInputArray[(x + y) - 2] in occupiedCell) {
                println("This cell is occupied! Choose another one!")
                takeCoordinates()
            } else {
                userInputArray[(x + y) - 2] = playerMove
                playerMove = if (userInputArray[(x + y) - 2] == 'X') 'O' else 'X'
                battlefield(String(userInputArray))
            }
        }
        if (x == 2) {
            if (userInputArray[x + y] in occupiedCell) {
                println("This cell is occupied! Choose another one!")
                takeCoordinates()
            } else {
                userInputArray[x + y] = playerMove
                playerMove = if (userInputArray[x + y] == 'X') 'O' else 'X'
                battlefield(String(userInputArray))
            }
        }
        if (x == 3) {
            if (userInputArray[(x + y) + 2] in occupiedCell) {
                println("This cell is occupied! Choose another one!")
                takeCoordinates()
            } else {
                userInputArray[(x + y) + 2] = playerMove
                playerMove = if (userInputArray[(x + y) + 2] == 'X') 'O' else 'X'
                battlefield(String(userInputArray))
            }
        }
    }
}

fun dashes() {
    repeat(9) {
        print("-")
    }
}
//print the game moves
fun battlefield(userInput : String) {
    dashes()
    println()
    println("| " + userInput[0] + " " + userInput[1] + " " + userInput[2] + " |")
    println("| " + userInput[3] + " " + userInput[4] + " " + userInput[5] + " |")
    println("| " + userInput[6] + " " + userInput[7] + " " + userInput[8] + " |")
    dashes()
    val arrayInput = arrayOfNulls<String>(9)
    for (i in 0..8) {
        arrayInput[i] = userInput[i].toString()
    }
    userInputStr = userInput

    println()
    println(arrayInput.joinToString())

    if (gameStatus == "in progress") {
        gameStatus(arrayInput)
    }
}

//check the winner
fun gameStatus(arrayInput: Array<String?>) {
    val winningX = "XXX"
    val winningO = "OOO"
    var rowStatus = ""
    var colStatus = ""
    var diagStatus = ""
    var index: Int
    var countX = 0
    var countO = 0
//check each row
    for (i in 0..8 step 3) {
        index = i
        repeat(3) {
            rowStatus += arrayInput[index].toString()
            if (arrayInput[index]?.contains("X") == true) {
                countX++
            } else if (arrayInput[index]?.contains("O") == true) {
                countO++
            }
            index++
        }
        rowStatus += " "
    }
//check each column
    for (i in 0..2) {
        index = i
        repeat(3) {
            colStatus += arrayInput[index].toString()
            index += 3
        }
        colStatus += " "
    }
//check diagonal left to right
    index = 0
    repeat(3) {
        diagStatus += arrayInput[index].toString()
        index += 4
    }
    diagStatus += " "
//check diagonal right to left
    index = 2
    repeat(3) {
        diagStatus += arrayInput[index].toString()
        index += 2
    }
    diagStatus += " "

    val field = rowStatus + colStatus + diagStatus

    when {
        '_' !in field && winningX !in field && winningO !in field -> print("Draw")
        winningX in field && winningO in field -> print("Impossible")
        winningX in field -> {
            gameStatus = "end"
            print("X wins")
        }
        winningO in field -> {
            gameStatus = "end"
            print("O wins")
        }
        else -> takeCoordinates()
    }
}