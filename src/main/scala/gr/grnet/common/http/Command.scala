/*
 * Copyright (C) 2010-2014 GRNET S.A.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package gr.grnet.common.http

import com.twitter.finagle.httpx.{Status, Response, Method}
import gr.grnet.common.key.{HeaderKey, ResultKey}
import typedkey.env.ImEnv

/**
 * A command is executed against an HTTP server and returns a [[gr.grnet.common.http.Result]].
 */
trait Command[T] {
  /**
   * The application domain of this command.
   */
  def appDomain: String

  /**
   * The name of this command. Command names are domain-specific.
   */
  def name: String = getClass.getSimpleName

  /**
   * The HTTP method by which the command is implemented.
   */
  def httpMethod: Method

  /**
   * The HTTP request headers that are set by this command.
   */
  def requestHeaders: ImEnv

  /**
   * The HTTP query parameters that are set by this command.
   */
  def queryParameters: ImEnv

  /**
   * Type-safe keys for `HTTP` response headers that are specific to this command.
   * These usually correspond to Pithos-specific headers, not general-purpose
   * `HTTP` response headers but there may be exceptions.
   *
   * Each command must document which keys it supports.
   */
  def responseHeaderKeys: Seq[HeaderKey[_]]

  /**
   * The keys for extra result data pertaining to this command.
   * Normally, the data that the keys refer to will be parsed
   * from the `HTTP` response body (`XML` or `JSON`).
   *
   * Each command must document which keys it supports.
   */
  def resultDataKeys: Seq[ResultKey[_]]

  /**
   * A set of all the HTTP statuses that are considered a success for this command.
   */
  def successStatuses: Set[Status]

  /**
   * A set of all the HTTP status codes that are considered a failure for this command.
   */
  def failureStatuses: Set[Status]

  /**
   * Validates this command. Returns some error iff there is any.
   */
  def validate: Option[String] = None

  /**
   * Computes the URL that will be used in the HTTP call.
   * The URL does not contain any needed parameters.
   */
  def serverURLExcludingParameters: String

  /**
   * Computes that URL path parts that will follow the Pithos+ server URL
   * in the HTTP call.
   */
  def serverRootPathElements: Seq[String]

  def serverRootPath: String = serverRootPathElements.mkString("/")

  def buildResultData(response: Response, startMillis: Long, stopMillis: Long): T

  /**
   * Builds the domain-specific result of this command. Each command knows how to parse the HTTP response
   * in order to produce domain-specific objects.
   */
  def buildResult(response: Response, startMillis: Long, stopMillis: Long): TResult[T]
}
