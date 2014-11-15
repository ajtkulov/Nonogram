package japan

trait Input {
  val columns: Array[List[Int]]
  val rows: Array[List[Int]]

  lazy val field = Cross.createField(rows.length, columns.length)

  require(columns.flatten.sum == rows.flatten.sum)
}

object Big extends Input {
  lazy val columns: Array[List[Int]] = Array(
    List(9,7,3,3,2),
    List(13,11,4,4,3,3),
    List(16,13,7,7),
    List(30,3,2,1),
    List(19,4,6,4,1,2),

    List(6,4,3,2,2,3,7,7),
    List(5,2,2,3,2,3,9,3,3),
    List(4,2,3,8,16),
    List(5,4,24,6,5),
    List(26,5,2,5,7),

    List(12,2,3,3,3,2,3,3,7),
    List(4,6,1,2,2,4,6,2,6),
    List(4,5,2,2,2,12,4,7),
    List(4,6,2,2,5,10,4,2),
    List(4,5,4,4,3,5,3,2),

    List(4,4,4,4,3,4,2,2),
    List(4,4,3,3,2,6,2),
    List(4,5,3,3,2,8,1),
    List(4,2,1,3,2,2,10,2),
    List(4,1,2,1,3,2,5,4,1),

    List(5,1,1,1,2,2,4,5,2),
    List(5,1,4,2,2,3,6,2),
    List(4,1,4,2,2,2,7,2,4),
    List(4,5,2,3,8,2,2,3),
    List(3,5,1,18,2,4),

    List(2,5,3,3,13),
    List(2,6,1,11),
    List(2,1,1,1,2,1,8),
    List(3,2,3,1,1,5),
    List(2,2,3,1,1,1,2),

    List(4,1,1,1,1,1,1),
    List(6,2,2,4,1,1,1),
    List(5,1,4,2,10,1,1),
    List(6,11,2,3,8),
    List(6,4,2,1,2,6,1,3,1),

    List(4,1,3,3,1,9,5,2),
    List(4,1,4,2,2,4,2,4,3,1,1),
    List(4,1,2,1,3,3,3,1,3,5,2),
    List(4,1,1,3,4,4,2,2,5,2),
    List(4,3,4,13,1,5,2),

    List(5,3,2,1,12,1,6),
    List(5,5,2,1,12,1,5),
    List(5,10,12,2,2,1),
    List(27,2,2),
    List(9,14,3,3,4),

    List(3,3,11,7,2,2),
    List(3,3,10,2,1,2,5,1),
    List(6,13,1,2,10),
    List(18,7,6,3),
    List(12,3,3,4,3)
  )

  lazy val rows: Array[List[Int]] = Array(
    List(1),
    List(3),
    List(6),
    List(8),
    List(10),

    List(12, 1),
    List(13, 2),
    List(6, 6, 3),
    List(5, 6, 5),
    List(5, 2, 4, 7),

    List(5, 2, 4, 5, 3),
    List(6, 4, 4, 5, 2),
    List(12, 4, 6, 2),
    List(13, 4, 8, 3),
    List(5, 5, 4, 5, 7),

    List(3,5,4,5,7),
    List(3,2,3,3,4,2,4),
    List(8,3,3,4,2,3),
    List(10,3,3,4,3,4),
    List(4,10,3,3,9),

    List(4,2,6,2,3,9),
    List(5,3,8,2,4,2,10),
    List(11,3,3,5,11),
    List(12,1,3,2,4,2,10),
    List(5,7,1,1,3,2,3,4,1,6),

    List(4,3,2,1,4,6,4,1,7),
    List(5,4,3,7,5,5,7),
    List(10,4,5,1,3,2,4),
    List(5,13,5,1,1,1,3,4),
    List(3,14,3,1,1,1,11),

    List(3,2,6,2,7,3),
    List(6,3,2,4,4),
    List(2,8,2,4,5),
    List(3,20,13),
    List(4,19,2,2,8),

    List(3,3,7,2,2,2,7),
    List(2,3,4,2,1,1,1,2,5),
    List(2,5,3,3,1,2,1,2,1,4),
    List(3,9,4,2,2,1,1,8),
    List(3,5,11,2,1,3,9),

    List(2,4,7,2,2,1,2,8),
    List(5,6,2,2,6,4,2),
    List(9,2,2,4,3,3,2),
    List(6,2,2,3,5,3,6),
    List(4,2,2,4,1,6,1,2),

    List(4,4,2,6,1,2,2,1),
    List(21,4,7),
    List(17,1,2,3,3,3),
    List(18,7,2),
    List(7,4,1,7),

    List(2,7,8,4,3),
    List(3,2,7,3,4,2,5),
    List(6,6,3,8,1,4,1,3),
    List(1,2,5,3,7,1,4,2,1,4),
    List(2,1,4,5,2,4,2,5,1,1,4),

    List(7,9,1,3,6,6),
    List(3,3,6,5,11),
    List(2,3,4,1,3,4),
    List(2,2,1,5,3),
    List(1,1,9,4)
  )
}

object Example extends Input {
  lazy val columns: Array[List[Int]] = Array(
    List(1),
    List(2),
    List(1)
  )

  lazy val rows: Array[List[Int]] = Array(
    List(1),
    List(1),
    List(1),
    List(1)
  )
}

object Example1 extends Input {
  lazy val columns: Array[List[Int]] = Array(
    List(2),
    List(1,4),
    List(1,4),
    List(1,2,2),
    List(2,2,3),

    List(2,2,5,1),
    List(1,9,1),
    List(2,7,1),
    List(10,2,1),
    List(10,2,1),

    List(10,1),
    List(1,9,3),
    List(2,16),
    List(19),
    List(18),

    List(17),
    List(2,17),
    List(18),
    List(17),
    List(15),

    List(19),
    List(16),
    List(14),
    List(16),
    List(3,11),

    List(12),
    List(3,9),
    List(2,9),
    List(3,2,2),
    List(2,2,1)
  )

  lazy val rows = Array(
    List(2,1,1),
    List(2,2,2,1,1),
    List(3,2,2,2,2),
    List(3,6,2,2,1),
    List(2,16,2),

    List(20,2,1),
    List(22,2),
    List(20,2),
    List(28),
    List(25),

    List(22,1),
    List(24),
    List(1,19),
    List(2,16),
    List(1,2,18),

    List(1,1,2,17),
    List(3,1,15),
    List(3,2,13),
    List(4,10),
    List(15)
  )
}

object Example2 extends Input {
  lazy val columns = Array(
    List(5,3),
    List(2,7,1,2),
    List(2,8,2,1),
    List(2,9,2,1),
    List(2,9,2,1),

    List(2,9,2,1),
    List(3,9,2,1),
    List(14,2,1),
    List(3,4,3,3,1),
    List(2,4,6,1),

    List(2,12,1),
    List(2,12,1),
    List(2,10,2),
    List(1,10,2),
    List(9,2),

    List(8,2),
    List(8,2),
    List(7,2),
    List(6,2),
    List(5,2),

    List(5,2),
    List(3,3),
    List(3,3),
    List(2,4),
    List(1,3,4),

    List(2,3,5),
    List(2,2,5),
    List(3,5),
    List(2,5),
    List(2,5)
  )

  lazy val rows = Array(
    List(3,3),
    List(10),
    List(3,3,2),
    List(1,1,2),
    List(1),

    List(1),
    List(9,2,1),
    List(12,2,2),
    List(14,3),
    List(17,1),

    List(8,8,2),
    List(9,9,2),
    List(21,2),
    List(26),
    List(7,16),

    List(15,5),
    List(20,7),
    List(1,10,9),
    List(2,18),
    List(30)
  )
}