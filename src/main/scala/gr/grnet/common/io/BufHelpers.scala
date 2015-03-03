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
