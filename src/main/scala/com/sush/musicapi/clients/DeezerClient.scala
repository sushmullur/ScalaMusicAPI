package com.sush.musicapi.clients

import com.sush.musicapi.clients.helpers.APIRequestHelper
import play.api.libs.json.{JsObject, Json, Reads}

import java.net.URL

object DeezerClient {

    private val DeezerURL = "https://api.deezer.com/"

    def default(): DeezerClient = {
        new DeezerClient(DeezerURL)
    }

}

class DeezerClient(url: String) {

    implicit val deezerArtistResponseReads: Reads[DeezerArtistResponse] = Json.reads[DeezerArtistResponse]
    
    def getArtist(id: Int): DeezerArtistResponse = {
        val artistURL = s"$url/artist/$id"
        APIRequestHelper.fetch(artistURL).as[DeezerArtistResponse]
    }
}

case class DeezerArtistResponse(id: Int, 
                                name: String, 
                                link: String, 
                                share: String, 
                                picture: String, 
                                picture_small: String, 
                                picture_medium: String, 
                                picture_big: String, 
                                picture_xl: String, 
                                nb_album: Int, 
                                nb_fan: Int, 
                                radio: Boolean,
                                tracklist: String)
