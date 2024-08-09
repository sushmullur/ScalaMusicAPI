package com.sush.musicapi.data

import com.sush.musicapi.data.{Artist, Song}

case class Album(artist: Artist, songs: List[Song])
