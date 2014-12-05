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

package gr.grnet.common.key

import gr.grnet.common.http.IRequestParam
import typedkey.impl.TagKey

/**
 * A typed key for HTTP request parameters.
 */
class RequestParamKey(name: String) extends TagKey[String](name)

/**
 * Factory for [[gr.grnet.common.key.RequestParamKey]]s.
 */
object RequestParamKey {
  /**
   * Factory method for a [[gr.grnet.common.key.RequestParamKey]], given
   * the key's name.
   */
  def apply(name: String): RequestParamKey = new RequestParamKey(name)

  /**
   * Factory method for a [[gr.grnet.common.key.RequestParamKey]], given
   * a [[IRequestParam]].
   */
  def apply(param: IRequestParam): RequestParamKey = new RequestParamKey(param.requestParam())
}
