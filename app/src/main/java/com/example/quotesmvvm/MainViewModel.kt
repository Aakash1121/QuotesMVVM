package com.example.quotesmvvm

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {


    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote()=quoteList[index]
    fun nextQuote(): Quote {
        if(quoteList[index]==quoteList[quoteList.lastIndex]){
             return quoteList[index]
        }else{
            return quoteList[++index]
        }
    }
    fun previousQuote(): Quote {
        if(quoteList[index]==quoteList[0]){
            return quoteList[0]
        }else{
           return quoteList[--index]
        }
    }

}