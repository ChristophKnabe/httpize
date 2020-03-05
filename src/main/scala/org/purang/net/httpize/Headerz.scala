package org.purang.net.httpize

import org.http4s.syntax.StringSyntax
import org.http4s.util.CaseInsensitiveString
import org.http4s.{Header, HeaderKey}

object Headerz {
  object `Execution-Mode` extends HeaderKey.Singleton with StringSyntax {
    override type HeaderT = Header

    override val name: CaseInsensitiveString = "Execution-Mode".ci //case insensitive

    override def matchHeader(header: Header): Option[Header] =
      if (header.name == name) Some(header)
      else None

    // TODO: should be implemented
    override def parse(s: String): Nothing = ???
  }
}
