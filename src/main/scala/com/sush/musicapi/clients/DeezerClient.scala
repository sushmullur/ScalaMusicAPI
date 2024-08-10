package com.sush.musicapi.clients

import com.sush.musicapi.clients.helpers.APIRequestHelper
import play.api.libs.json.{JsObject, Json, Reads}

object DeezerArtist {
    implicit val reads: Reads[DeezerArtist] = Json.reads[DeezerArtist]
}

object DeezerAlbum {
    implicit val reads: Reads[DeezerAlbum] = Json.reads[DeezerAlbum]
}

object DeezerTrack {
    implicit val reads: Reads[DeezerTrack] = Json.reads[DeezerTrack]
}

object DeezerSearchResponse {
    implicit val reads: Reads[DeezerSearchResponse] = Json.reads[DeezerSearchResponse]
}

object DeezerClient {

    private val DeezerURL = "https://api.deezer.com/"

    def default(): DeezerClient = {
        new DeezerClient(DeezerURL)
    }
    
    private def getArtistFromResponse(artist: String, response: DeezerSearchResponse): DeezerArtist = {
        val pattern = ("(?i).*" + artist + ".*").r
        
        response.data.find(
            track => pattern.findFirstIn(track.artist.name).isDefined
        ) match {
            case Some(track) => track.artist
            case None => throw new IllegalArgumentException("Artist not found")
        }
    }
}

class DeezerClient(url: String) {
    import DeezerClient._

    def getArtistById(id: Int): DeezerArtist = {
        val artistURL = s"$url/artist/$id"
        APIRequestHelper.fetch(artistURL).as[DeezerArtist]
    }

    def searchArtist(queryString: String): DeezerArtist = {
        val searchURL = s"$url/search?q=$queryString"
        val response = APIRequestHelper.fetch(searchURL).as[DeezerSearchResponse]
        getArtistFromResponse(queryString, response)
    }
}

// Case classes to represent the JSON data
case class DeezerTrack(
    id: Long,
    readable: Boolean,
    title: String,
    title_short: String,
    title_version: String,
    link: String,
    duration: Int,
    rank: Int,
    explicit_lyrics: Boolean,
    explicit_content_lyrics: Int,
    explicit_content_cover: Int,
    preview: String,
    md5_image: String,
    artist: DeezerArtist,
    album: DeezerAlbum,
    `type`: String
)

case class DeezerArtist(
    id: Long,
    name: String,
    link: String,
    picture: String,
    picture_small: String,
    picture_medium: String,
    picture_big: String,
    picture_xl: String,
    tracklist: String,
    `type`: String
)

case class DeezerAlbum(
    id: Long,
    title: String,
    cover: String,
    cover_small: String,
    cover_medium: String,
    cover_big: String,
    cover_xl: String,
    md5_image: String,
    tracklist: String,
    `type`: String
)

case class DeezerSearchResponse(data: Array[DeezerTrack])
