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

import gr.grnet.common.http.IHeader
import typedkey.impl.TagKey

import scala.reflect.ClassTag

class ResultKey[T: ClassTag](name: String) extends TagKey[T](name)

/**
 * A typed key for domain-specific results of HTTP [[gr.grnet.common.http.Command]]s.
 */
object ResultKey {
  def apply[T: ClassTag](name: String): ResultKey[T] = new ResultKey[T](name)

  def apply[T: Manifest](header: IHeader): ResultKey[T] = new ResultKey[T](header.headerName())
}
