package japan

import scala.collection.mutable.ArrayBuffer

object Japan extends App {
  override def main(args : Array[String]) {
    handle()
  }

  def handle () : Unit  = {
    val input = Big
    var set: List[JapanCross] = List(new JapanCross(input.rows, input.columns, input.field))

    while (set.size > 0 && set.count(x => x.currentField.isFinal) == 0) {
      val newSet = (for (x <- set) yield {
        val move = x.move()
        if (move.isDefined && !x.currentField.isFinal) {
          val unknown = x.currentField.selectUnknown
          val newField1 = x.currentField.setElem(Elem.Unset, unknown(0)._1, unknown(0)._2)
          val newField2 = x.currentField.setElem(Elem.Set, unknown(0)._1, unknown(0)._2)
          List[JapanCross](new JapanCross(input.rows, input.columns, newField1), new JapanCross(input.rows, input.columns, newField2))
        } else
        if (move.isDefined && x.currentField.isFinal) {
          List[JapanCross](x)
        } else {
          List[JapanCross]()
        }
      }).flatten
      set = newSet
    }

    println(set.filter(x => x.currentField.isFinal)(0).currentField)
  }
}

case class Field(field : Array[Array[Elem.Value]]) {
  lazy val rowsCount = field.length
  lazy val columnsCount = field(0).length

  def getColumn(col : Int) = {
    val res = new Array[Elem.Elem](rowsCount)
    for (i <- 0 to rowsCount - 1) {
      res(i) = field(i)(col)
    }

    res
  }

  override def toString() = {
    field.map(_.mkString("")).mkString("\n")
  }

  def setColumn(a : Array[Elem.Elem], col : Int) : Field = {
    val res = clone()
    for (i <- 0 to rowsCount - 1) {
      res.field(i)(col) = a(i)
    }

    res
  }

  def setRow(a : Array[Elem.Elem], row : Int) : Field = {
    val res = clone
    res.field(row) = a

    res
  }

  def isFinal : Boolean = {
    field.forall(x => x.forall(y => y != Elem.Unknown))
  }

  def selectUnknown : Seq[(Int, Int)] = {
    for (row <- 0 to rowsCount - 1; col <- 0 to columnsCount - 1; if field(row)(col) == Elem.Unknown) yield (row, col)
  }

  def setElem(a : Elem.Elem, row : Int, column : Int) : Field = {
    val res = clone
    res.field(row)(column) = a

    res
  }

  override def clone() : Field = {
    val res = Array.fill(rowsCount, columnsCount)(Elem.Unknown)
    for (row <- 0 to rowsCount - 1; column <- 0 to columnsCount - 1) {
      res(row)(column) = this.field(row)(column)
    }

    Field(res)
  }
}

class JapanCross(val rows : Array[List[Int]], val columns : Array[List[Int]], field : Field) {

  var currentField: Field = field
  val history : ArrayBuffer[Field] = ArrayBuffer(field)

  def printHistory() : Unit = {
    history.foreach(x => {println(x); println})
  }

  def setField(newField : Field) : Unit = {
    history.append(newField)
    currentField = newField
  }

  override def toString() = {
    currentField.toString()
  }

  def moveRows() : Boolean = {
    var res = false
    for (i <- 0 to rows.length - 1) {
      val row: Array[Elem.Value] = currentField.field(i)
      val newRow = Cross.find(row, rows(i))
      newRow match {
        case Some(ar) => {
          setField(currentField.setRow(ar, i))
          res = true
        }
        case None =>
      }
    }

    res
  }

  def moveColumns() : Boolean = {
    var res = false
    for (i <- 0 to columns.length - 1) {
      val column: Array[Elem.Elem] = currentField.getColumn(i)
      val newRow = Cross.find(column, columns(i))
      newRow match {
        case Some(ar) => {
          setField(currentField.setColumn(ar, i))
          res = true
        }
        case None =>
      }
    }

    res
  }

  def possibleCorrect() : Boolean = {
    var res = true
    for (i <- 0 to rows.length - 1) {
      if (!Cross.recBool(currentField.field(i), 0, rows(i).toArray, 0, Cross.createCache)) {
        res = false
      }
    }
    for (i <- 0 to columns.length - 1) {
      if (!Cross.recBool(currentField.getColumn(i), 0, columns(i).toArray, 0, Cross.createCache)) {
        res = false
      }
    }

    res
  }

  def move() : Option[Boolean] = {
    var res : Option[Boolean] = Some(true)
    while (res.isDefined && res.get) {
      val hasRowMoved = moveRows
      val hasColumnMoved = moveColumns
      if (!possibleCorrect) {
        history.clear()
        res = None
      } else {
        res = Some(hasColumnMoved || hasRowMoved)
      }
    }

    res
  }
}

object Cross {
  type RowState = Seq[Int]

  def createField(rows : Int, columns : Int) : Field = {
    Field(Array.fill(rows, columns)(Elem.Unknown))
  }

  def check(a: Array[Elem.Elem], start: Int, length: Int): Boolean = {
    var res = false
    val prev = (start + length <= a.length) && (start == 0 || (a(start - 1) != Elem.Set))
    if (prev) {
      val cur = (0 to length - 1).forall(x => a(start + x) != Elem.Unset)
      if (cur) {
        res = start + length == a.length || a(start + length) != Elem.Set
      }
    }

    res
  }

  def createCache = scala.collection.mutable.HashMap[(Int, Int), Boolean]()

  def find(a : Array[Elem.Elem], pos : List[Int]) : Option[Array[Elem.Elem]] = {
    val resAr = a.clone
    var res = false
    for (i <- 0 to a.length - 1) {
      if (a(i) == Elem.Unknown) {
        val copy = a.clone()
        copy(i) = Elem.Set
        val test = recBool(copy, 0, pos.toArray, 0, createCache)
        copy(i) = Elem.Unset
        val test1 = recBool(copy, 0, pos.toArray, 0, createCache)

        if (test && !test1) {
          resAr(i) = Elem.Set
          a(i) = Elem.Set
          res = true
        }
        else if (!test && test1) {
          resAr(i) = Elem.Unset
          a(i) = Elem.Unset
          res = true
        }
      }
    }
    if (res) {
      Some(resAr)
    }
    else {
      None
    }
  }

  def recBool(a: Array[Elem.Elem], i: Int, pos: Array[Int], posIdx : Int, cache : scala.collection.mutable.HashMap[(Int, Int), Boolean]): Boolean = {

    val res =
    if (cache.contains((i, posIdx))) {
      cache((i, posIdx))
    } else
    if (posIdx >= pos.length) {
      if (a.indexWhere(x => x == Elem.Set, i) != -1) {
        false
      }
      else {
        true
      }
    }
    else if (i >= a.length) {
      false
    }
    else {
      val pass =
        if ((a(i) == Elem.Unset) || (a(i) == Elem.Unknown)) {
          recBool(a, i + 1, pos, posIdx, cache)
        }
        else {
          false
        }

      val cur = pos(posIdx)
      val current =
        if (check(a, i, cur)) {
          recBool(a, i + cur + 1, pos, posIdx + 1, cache)
        }
        else {
          false
        }

      pass || current
    }

    cache((i, posIdx)) = res

    res
  }
}

object Elem extends Enumeration {
  type Elem = Value
  val Set = Value("*")
  val Unset = Value(" ")
  val Unknown = Value("?")
}

