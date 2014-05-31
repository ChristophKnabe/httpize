package org.purang.net

import org.http4s._
import argonaut._, Argonaut._
import org.http4s.Header.`Content-Type`
import scodec.bits.ByteVector

import Header.`Content-Type`.`application/json`
import org.http4s


package object httpize {

  implicit def TWritable[T](implicit charset: CharacterSet = CharacterSet.`UTF-8`, encode: EncodeJson[T]) =
    new SimpleWritable[T] {
      def contentType: `Content-Type` = `application/json`.withCharset(charset)

      def asChunk(t: T) = ByteVector.view(encode(t).nospaces.getBytes(charset.charset))
    }

  //IP address of remote client:
  case class IP(remote: String, `x-forwarded-for`: String)

  object IP {
    def apply(r: Request): IP = {
      IP(r.remoteAddr.getOrElse("xx.xx.xx.xx"),
        s"${r.headers.get(org.http4s.Header.`X-Forwarded-For`)}")
    }

    implicit def IPCodecJson: CodecJson[IP] =
      casecodec2(IP.apply, IP.unapply)("remote", "x-forwarded-for")
  }

  //User agent of client:
  case class UserAgent(`user-agent`: String)

  object UserAgent {
    def apply(r: Request): UserAgent = {
      UserAgent(r.headers.get(org.http4s.Header.`User-Agent`).toString)
    }

    implicit def UserAgentCodecJson: CodecJson[UserAgent] = casecodec1(UserAgent.apply(_: String), UserAgent.unapply)("user-agent")
  }

  //All headers sent by the client:
  case class HeadersContainer(headers: Headers)

  implicit def HeaderCodecJson: EncodeJson[Header] = EncodeJson(
    (h: Header) => (h.toRaw.name.toString := h.toRaw.value) ->: jEmptyObject
  )

  implicit def HeadersContainerCodecJson: EncodeJson[HeadersContainer] = EncodeJson(
    (hc: HeadersContainer) =>  ("headers" := hc.headers.toList)    ->: jEmptyObject
  )


}
