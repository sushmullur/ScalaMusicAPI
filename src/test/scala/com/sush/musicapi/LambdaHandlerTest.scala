package com.sush.musicapi

import com.amazonaws.services.lambda.runtime.Context
import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import com.sush.musicapi.clients.DeezerClient

import scala.jdk.CollectionConverters._

class LambdaHandlerTest extends AnyFunSuite with MockFactory {

    private val mockString = "mock"
    private val mockDeezer = mock[DeezerClient]

    test("Should return bad request for unsupported input") {
        val handler = new LambdaHandler()
        val input = Map(mockString -> mockString).asJava
        val expectedOutput = s"Bad request $mockString"

        assert(handler.callAPI(input, mockDeezer) == expectedOutput)
    }

    test("Should return bad request for empty input") {
        val handler = new LambdaHandler()
        val input: Map[String, String] = Map.empty
        val expectedOutput = "Bad request"

        assert(handler.callAPI(input.asJava, mockDeezer) == expectedOutput)
    }

    test("Should call DeezerClient to search for artist") {
        // TODO: Implement this test case
    }
}
