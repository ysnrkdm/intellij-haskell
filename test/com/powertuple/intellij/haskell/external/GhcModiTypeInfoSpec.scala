/*
 * Copyright 2015 Rik van der Kleij

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.powertuple.intellij.haskell.external

import org.scalatest.{BeforeAndAfterEach, FunSpec, GivenWhenThen, Matchers}

class GhcModiTypeInfoSpec extends FunSpec with Matchers with GivenWhenThen with BeforeAndAfterEach {

  val ghcModiTypeInfo = GhcModiTypeInfo

  describe("Parsing ghc-modi type info output") {
    Given("type info output of ghc-modi")
    val output = List(
      """12 10 12 19 "[Integer] -> [Integer]"""",
      """12 10 12 25 "[Integer]"""",
      """12 1 12 25 "[Integer]"""")

    When("converted to list of type info")
    val typeInfos = ghcModiTypeInfo.ghcModiOutputToTypeInfo(output).get.toSeq

    Then("it should contain right info")
    typeInfos should have length 3

    typeInfos(0) shouldEqual TypeInfo(12, 10, 12, 19, "[Integer] -> [Integer]")
    typeInfos(1) shouldEqual TypeInfo(12, 10, 12, 25, "[Integer]")
    typeInfos(2) shouldEqual TypeInfo(12, 1, 12, 25, "[Integer]")
  }
}
