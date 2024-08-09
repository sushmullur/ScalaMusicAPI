package com.sush.musicapi.data

import com.sush.musicapi.data.Artist

case class Song(title: String, 
                artist: Artist, 
                features: Option[List[Artist]] = None
                )
