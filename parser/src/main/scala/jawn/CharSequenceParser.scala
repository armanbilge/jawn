package org.typelevel.jawn

/**
 * Lazy character sequence parsing.
 *
 * This is similar to StringParser, but acts on character sequences.
 */
final private[jawn] class CharSequenceParser[J](cs: CharSequence) extends SyncParser[J] with CharBasedParser[J] {
  var line = 0
  var offset = 0
  final def column(i: Int) = i - offset
  final def newline(i: Int): Unit = { line += 1; offset = i + 1 }
  final def reset(i: Int): Int = i
  final def checkpoint(state: Int, i: Int, context: RawFContext[J], stack: List[RawFContext[J]]): Unit = ()
  final def at(i: Int): Char = cs.charAt(i)
  final def at(i: Int, j: Int): CharSequence = cs.subSequence(i, j)
  final def atEof(i: Int) = i == cs.length
  final def close() = ()
}
