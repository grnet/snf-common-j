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

package gr.grnet.common.json

import com.fasterxml.jackson.core.util.{DefaultIndenter, DefaultPrettyPrinter}
import com.fasterxml.jackson.databind.{ObjectReader, ObjectWriter, JsonNode, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
 * JSON utilities
 */
object Json {
  final val Mapper = new ObjectMapper().registerModule(DefaultScalaModule)

  final val PrettyPrinter = {
    val printer = new DefaultPrettyPrinter
    val indenter = new DefaultIndenter()
    printer.indentArraysWith(indenter)
    printer.indentObjectsWith(indenter)
    printer
  }

  final val Writer = Mapper.writer[ObjectWriter](PrettyPrinter)
  final val Reader = Mapper.reader[ObjectReader]()

  def objectToJsonString[A <: AnyRef](obj: A): String =
    Writer.writeValueAsString(obj)

  def jsonStringToTree(json: String): JsonNode =
    Reader.readTree(json)
}
