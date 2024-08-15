package com.sush.musicapi

import com.amazonaws.services.lambda.runtime.Context
import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory

import scala.jdk.CollectionConverters._

class LambdaHandlerTest extends AnyFunSuite with MockFactory {

    private val mockString = "mock"

    test("Should return bad request for unsupported input") {
        val handler = new LambdaHandler()
        val mockContext = mock[Context]
        
        val input = Map(mockString -> mockString).asJava
        val expectedOutput = s"Bad request $mockString"

        assert(handler.handleRequest(input, mockContext) == expectedOutput)
    }
}
