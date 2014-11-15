package japan

import org.scalatest._

class Japan extends FunSuite {
  test("test5-3 of 10") {
    val fill: Array[Elem.Value] = Array.fill(10)(Elem.Unknown)

    val res = Cross.find(fill, List(5,3))

    assert(res.get.deep === Array(Elem.Unknown, Elem.Set, Elem.Set, Elem.Set, Elem.Set, Elem.Unknown, Elem.Unknown, Elem.Set, Elem.Set, Elem.Unknown).deep)
  }

  test("test3-5 of 10") {
    val fill: Array[Elem.Value] = Array.fill(10)(Elem.Unknown)

    val res = Cross.find(fill, List(3, 5))

    assert(res.get.deep === Array(Elem.Unknown, Elem.Set, Elem.Set, Elem.Unknown, Elem.Unknown, Elem.Set, Elem.Set, Elem.Set, Elem.Set, Elem.Unknown).deep)
  }

  test("test3") {
    val fill: Array[Elem.Value] = Array(Elem.Unknown, Elem.Set, Elem.Set, Elem.Unknown, Elem.Unset, Elem.Set, Elem.Unknown, Elem.Unknown, Elem.Unknown)

    val lens: List[Int] = List(3, 2)
    val res = Cross.find(fill, lens)

    assert(res.get.deep === Array(Elem.Unknown, Elem.Set, Elem.Set, Elem.Unknown, Elem.Unset, Elem.Set, Elem.Set, Elem.Unset, Elem.Unset).deep)
  }

//  test("test4") {
//    val fill: Array[Elem.Value] = Array(Elem.Unset, Elem.Unknown, Elem.Unknown, Elem.Unknown, Elem.Unknown, Elem.Unset, Elem.Unset, Elem.Unset, Elem.Set, Elem.Unset, Elem.Unset, Elem.Unset, Elem.Unset)
//
//    val lens: List[Int] = List(1)
//    val res = Cross.find(fill, lens)
//
//    val inter = Cross.rec(fill, 0, lens, Nil)
//
//    println(inter.mkString(""))
//
//    assert(res.get.deep === Array(Elem.Unset, Elem.Unset, Elem.Unset, Elem.Unset, Elem.Unset, Elem.Unset, Elem.Unset, Elem.Unset, Elem.Set, Elem.Unset, Elem.Unset, Elem.Unset, Elem.Unset).deep)
//  }

//  test("some") {
//    val fill: Array[Elem.Value] = Array(Elem.Set, Elem.Unset, Elem.Unknown)
//    val res = Cross.rec(fill, 0, List(2), Nil)
//
//    assert(res.isEmpty)
//  }
}