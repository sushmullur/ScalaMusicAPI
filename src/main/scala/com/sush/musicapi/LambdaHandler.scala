package com.sush.musicapi

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import com.sush.musicapi.clients.DeezerClient
import com.sush.musicapi.data.{Album, Artist, Song}

import java.util.Map
import scala.collection.JavaConverters._

object LocalHandler {

    val Deezer = DeezerClient.default()
    
    // For local testing
    def main(args: Array[String]): Unit = {
        println(Deezer.searchArtist("juice"))
    }
}

class LambdaHandler extends RequestHandler[Map[String, String], String] {
    import LocalHandler._

    override def handleRequest(input: Map[String, String], context: Context): String = {
        callAPI(input, Deezer)
    }

    private [musicapi] def callAPI(input: Map[String, String], deezer: DeezerClient): String = {
        input.asScala.headOption match {
            case Some(("artist", query)) =>
                deezer.searchArtist(query).toString
            case Some((key, _)) =>
                s"Bad request $key" 
            case None =>
                "Bad request"
        }
    }
}
