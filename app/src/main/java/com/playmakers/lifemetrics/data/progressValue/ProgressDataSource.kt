package com.playmakers.lifemetrics.data.progressValue

class ProgressDataSource {
    val progressList = listOf(
        Progress("Starter", daysInSeconds(0), daysInSeconds(1)),
        Progress("Scout", daysInSeconds(1), daysInSeconds(2)),
        Progress("Private", daysInSeconds(2), daysInSeconds(4)),
        Progress("Corporal", daysInSeconds(4), daysInSeconds(7)),
        Progress("Sergeant", daysInSeconds(7), daysInSeconds(11)),
        Progress("Warrior", daysInSeconds(11), daysInSeconds(15)),
        Progress("Master Sergeant", daysInSeconds(15), daysInSeconds(21)),
        Progress("Knight", daysInSeconds(21), daysInSeconds(30)),
        Progress("Knight-Lieutenant", daysInSeconds(30), daysInSeconds(40)),
        Progress("Captain", daysInSeconds(40), daysInSeconds(53)),
        Progress("Champion", daysInSeconds(53), daysInSeconds(68)),
        Progress("Commander", daysInSeconds(68), daysInSeconds(98)),
        Progress("Conqueror", daysInSeconds(98), daysInSeconds(158)),
        Progress("Marshal", daysInSeconds(158), daysInSeconds(278)),
        Progress("Master Conqueror", daysInSeconds(278), daysInSeconds(478)),
        Progress("Grand Marshal", daysInSeconds(478), daysInSeconds(843)),
        Progress("Grand Warrior", daysInSeconds(843), daysInSeconds(1573)),
        Progress("Overlord", daysInSeconds(1573), daysInSeconds(3033)),
        Progress("High Overlord", daysInSeconds(3033), daysInSeconds(5223)),
        Progress("The Immortal", daysInSeconds(5223), daysInSeconds(8873))
    )
}
fun daysInSeconds(days: Int) : Long{
    return days * 86400L // 1 day = 86,400 seconds
}