package org.typelevel.jawn

/**
 * Basic in-memory string parsing.
 *
 * This is probably the simplest Parser implementation, since there is
 * no UTF-8 decoding, and the data is already fully available.
 *
 * This parser is limited to the maximum string size (~2G). Obviously
 * for large JSON documents it's better to avoid using this parser and
 * go straight from disk, to avoid having to load the whole thing into
 * memory at once. So this limit will probably not be a problem in
 * practice.
 */
final private[jawn] class StringParser[J](s: String) extends SyncParser[J] with CharBasedParser[J] {
  var line = 0
  var offset = 0
  final def column(i: Int) = i - offset
  final def newline(i: Int): Unit = { line += 1; offset = i + 1 }
  final def reset(i: Int): Int = i
  final def checkpoint(state: Int, i: Int, context: RawFContext[J], stack: List[RawFContext[J]]): Unit = {}
  final def at(i: Int): Char = s.charAt(i)
  final def at(i: Int, j: Int): CharSequence = s.substring(i, j)
  final def atEof(i: Int) = i == s.length
  final def close() = ()
}
