package com.sush.musicapi.clients.helpers

import play.api.libs.json.{Json, JsObject}
import sttp.client4.quick._
import sttp.client4.Response
import sttp.model.{StatusCode, Uri}

import java.net.URL

// Should take in a request url as string and receive a JSON object
object APIRequestHelper {
    def fetch(fetchURL: String): JsObject = {
        val uri = Uri.parse(fetchURL).getOrElse(throw new IllegalArgumentException("Invalid URL"))
        val response = quickRequest
        .get(uri)
        .send()
        if(response.code == StatusCode.Ok) {
            Json.parse(response.body).as[JsObject]
        } else {
            // TODO: Don't hard code this
            Json.parse(s"{\"Error\": ${response.code}}").as[JsObject]
        }
    }
}
