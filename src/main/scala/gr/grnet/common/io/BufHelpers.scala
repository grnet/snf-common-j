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

import java.io.{RandomAccessFile, File}
import java.nio.channels.FileChannel.MapMode

import com.twitter.finagle.netty3.ChannelBufferBuf
import com.twitter.io.Buf
import org.jboss.netty.buffer.ChannelBuffers

/**
 *
 */
object BufHelpers {
  def bufOfFile(file: File): Buf = {
    val raf = new RandomAccessFile(file, "r")
    val channel = raf.getChannel
    val byteBuffer = channel.map(MapMode.READ_ONLY, 0, file.length())
    val channelBuffer = ChannelBuffers.wrappedBuffer(byteBuffer)
    channel.close()
    raf.close()

    ChannelBufferBuf.Owned.apply(channelBuffer)
  }
}
