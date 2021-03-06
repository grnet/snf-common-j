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

package gr.grnet.common.io

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files

import okio.ByteString

object Base64 {
  def encodeArray(bytes: Array[Byte]): String = ByteString.of(bytes, 0, bytes.length).base64()

  def encodeFile(file: File): String = encodeArray(Files.readAllBytes(file.toPath))

  def encodeString(s: String): String = encodeArray(s.getBytes(StandardCharsets.UTF_8))

  def decodeString(encoded: String): Array[Byte] = ByteString.decodeBase64(encoded).toByteArray
}
