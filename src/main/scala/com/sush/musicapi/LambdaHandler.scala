package com.sush.musicapi

import com.sush.musicapi.clients.DeezerClient
import com.sush.musicapi.data.{Album, Artist, Song}

object LambdaHandler {

    val tempArtistId = 4495513
    val client = DeezerClient.default()
    
    def main(args: Array[String]): Unit = {
        val artist = client.getArtist(tempArtistId)
        println(artist.name)
    }
}
