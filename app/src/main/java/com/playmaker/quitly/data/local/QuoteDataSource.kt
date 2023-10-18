package com.playmaker.quitly.data.local

import com.playmaker.quitly.data.model.Quote
import kotlin.random.Random

object QuoteDataSource {
    private val quoteList = listOf(
        Quote("Believe in yourself, and you are halfway there."),
        Quote("The greatest mistake you can make in life is to continually be afraid you will make one."),
        Quote("Man never made any material as resilient as the human spirit."),
        Quote("Strength does not come from physical capacity. It comes from as indomitable will."),
        Quote("It always seems impossible until it's done."),
        Quote("If you don't know where you are going, you'll end up someplace else."),
        Quote("You must do the things you think you cannot do."),
        Quote("Success is the sum of small efforts, repeated day in and day out."),
        Quote("Nothing is impossible; the word itself says, I'm possible!"),
        Quote("The only person you are destined to become is the person you decide to be."),
        Quote("Whether you think you can or you think you can't, you're right."),
        Quote("Life doesn't get easier or more forgiving, we get stronger and more resilient."),
        Quote("The best way out is always through."),
    )

    fun getRandomQuote(): Quote{
        val randomIndex = Random.nextInt(quoteList.size)
        return  quoteList[randomIndex]
    }
}

