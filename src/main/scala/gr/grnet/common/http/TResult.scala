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

import com.twitter.finagle.httpx.{HeaderMap, Status}

/**
 * The result of a [[gr.grnet.common.http.Command]].
 */
trait TResult[+T] {
  def status: Status

  def statusCode = status.code
  
  def startMillis: Long

  def stopMillis: Long

  def dtMillis = stopMillis - startMillis

  def responseHeaders: HeaderMap

  def successData: Option[T]

  def errorDetails: Option[String]

  def completionMillis = stopMillis - startMillis

  def successStatuses: Set[Status]

  def isSuccess: Boolean = successStatuses(status)

  def is200 = statusCode == 200

  def is201 = statusCode == 201

  def is204 = statusCode == 204

  def is(status: Status) = this.statusCode == status.code
}
