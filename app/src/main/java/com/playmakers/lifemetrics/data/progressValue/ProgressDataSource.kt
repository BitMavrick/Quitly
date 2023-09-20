package com.playmakers.lifemetrics.data.progressValue

object ProgressDataSource {
    val progressList = listOf(
        Progress("Starter", "Scout", daysInSeconds(0), daysInSeconds(1), 0),
        Progress("Scout", "Private", daysInSeconds(1), daysInSeconds(2), 1),
        Progress("Private", "Corporal", daysInSeconds(2), daysInSeconds(4), 2),
        Progress("Corporal", "Sergeant", daysInSeconds(4), daysInSeconds(7), 4),
        Progress("Sergeant", "Warrior", daysInSeconds(7), daysInSeconds(11), 7),
        Progress("Warrior", "Master Sergeant", daysInSeconds(11), daysInSeconds(15), 11),
        Progress("Master Sergeant", "Knight", daysInSeconds(15), daysInSeconds(21), 15),
        Progress("Knight", "Knight-Lieutenant", daysInSeconds(21), daysInSeconds(30), 21),
        Progress("Knight-Lieutenant", "Captain", daysInSeconds(30), daysInSeconds(40), 30),
        Progress("Captain", "Champion", daysInSeconds(40), daysInSeconds(53), 40),
        Progress("Champion", "Commander", daysInSeconds(53), daysInSeconds(68), 53),
        Progress("Commander", "Conqueror", daysInSeconds(68), daysInSeconds(98), 68),
        Progress("Conqueror", "Marshal", daysInSeconds(98), daysInSeconds(158), 98),
        Progress("Marshal", "Master Conqueror", daysInSeconds(158), daysInSeconds(278), 158),
        Progress("Master Conqueror", "Grand Marshal", daysInSeconds(278), daysInSeconds(478), 278),
        Progress("Grand Marshal", "Grand Warrior", daysInSeconds(478), daysInSeconds(843), 478),
        Progress("Grand Warrior", "Overlord", daysInSeconds(843), daysInSeconds(1573), 843),
        Progress("Overlord", "High Overlord", daysInSeconds(1573), daysInSeconds(3033), 1573),
        Progress("High Overlord", "The Immortal", daysInSeconds(3033), daysInSeconds(5223), 3033),
        Progress("The Immortal", "Success!", daysInSeconds(5223), daysInSeconds(8873), 5223)
    )
}
fun daysInSeconds(days: Int) : Long{
    return days * 86400L // 1 day = 86,400 seconds
}