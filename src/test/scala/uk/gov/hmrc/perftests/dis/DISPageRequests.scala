/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.dis

import io.gatling.core.Predef._
import io.gatling.core.session.Expression
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.util.Random


object DISPageRequests extends BaseRequest {

  private val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  private val random = new Random()
  private val usedReferences = scala.collection.mutable.Set[String]()


  def getClearData: HttpRequestBuilder =
    http("Clear Data")
      .get(s"$baseUrl/$route/test-only/clear-all": String)
      .check(status.is(200))


  def getAuthLoginPage: HttpRequestBuilder =
    http("Navigate to auth login stub page")
      .get(s"$authUrl/auth-login-stub/gg-sign-in": String)
      .check(status.is(200))
      .check(saveCsrfToken())

  def postAuthloginToDIS: HttpRequestBuilder =
    http("Login to DIS via Auth Stub")
      .post(ggSignInUrl)
      .formParam("csrfToken", csrfTokenExpr)
      .formParam("redirectionUrl", _ => s"$baseUrl$route")
      .formParam("affinityGroup", _ => "Organisation")
      .formParam("credentialStrength", _ => "strong")
      .formParam("confidenceLevel", _ => "50")
      .formParam("nino", _ => "")
      .formParam("authorityId", _ => "")
      .formParam("enrolment[0].name", _ => "HMRC-PODS-ORG")
      .formParam("enrolment[0].taxIdentifier[0].name", _ => "PsaID")
      .formParam("enrolment[0].taxIdentifier[0].value", _ => "A2100005")
      .formParam("enrolment[0].state", _ => "Activated")
      .check(status.is(303))

  def getWhatWillYouNeedPage: HttpRequestBuilder =
    http("Get What Will You Need Page")
      .get(s"$baseUrl$WhatWillYouNeed")
      .check(status.is(200))
  //.check(saveCsrfToken())

  def postWhatWillYouNeedPage: HttpRequestBuilder =
    http("Post What Will You Need Page")
      .post(s"$baseUrl$WhatWillYouNeed")
      .formParam("csrfToken", csrfTokenExpr)
      .check(status.is(303))

}
