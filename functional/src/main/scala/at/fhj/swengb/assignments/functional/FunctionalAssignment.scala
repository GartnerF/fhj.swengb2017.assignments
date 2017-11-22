package at.fhj.swengb.assignments.functional

/**
  * In this assignment you have the chance to demonstrate basic understanding of
  * functions like map/filter/foldleft a.s.o.
  **/
object FunctionalAssignment {

  /**
    * A function which returns its parameters in a changed order. Look at the type signature.
    */
  def flip[A, B](t: (A, B)): (B, A) = t.swap

  /**
    * given a Seq[A] and a function f : A => B, return a Seq[B]
    */
  def unknown[A, B](as: Seq[A], fn: A => B): Seq[B] = as.map(fn)

  /**
    * Returns the absolute value of the parameter i.
    *
    * @param i a value, either with a positive or a negative sign.
    * @return
    */
  def abs(i: Int): Int = if (i >= 0) i else  -i


  // Describe with your own words what this function does.
  // in the comment below, add a description what this function does - in your own words - and give
  // the parameters more descriptive names.
  //
  // Afterwards, compare your new naming scheme with the original one.
  // What did you gain with your new names? What did you loose?
  //
  /**
    * Die Funktion op geht die liste as durch und führt für jedes element die Funktion fn zusammen mit
    * einem accumalator aus.
    * Beim ersten mal ist der accumalator der Wert b, danach immer das Ergebnis des vorherigen Funktionsaufrufes von fn.
    * Dies wird solange wiederholt bis alle Elemente abgearbeitet sind und das letzte Ergebnis von fn ist somit das
    * Ergebnis von op.
    *
    * @param as list: Die Liste auf der die Funktion op angewendet wird.
    * @param b start: Ist der Wert mit dem fn startet, Startwert
    * @param fn funktion: Funktion die auf die Liste angewendet wird
    * @tparam A Element: Ist ein Element der Liste
    * @tparam B  accumalator :Start und Endergebnis, Wert der sich durch die Funktion fn ändert.
    * @return : Die Liste reduziert mittels der Funktion fn auf einen Wert mit dem Startwert b
    */
  def op[A, B](as: Seq[A], b: B)(fn: (B, A) => B): B = as.foldLeft(b)(fn)

  /**
    * implement the summation of the given numbers parameter.
    * Use the function 'op' defined above.
    *
    * @param numbers
    * @return
    */
  def sum(numbers: Seq[Int]): Int = op(numbers,0)((a :Int, b: Int) => a +b)


  /**
    * calculate the factorial number of the given parameter.
    *
    * for example, if we pass '5' as parameter, the function should do the following:
    *
    * 5! = 5 * 4 * 3 * 2 * 1
    *
    * @param i parameter for which the factorial must be calculated
    * @return i!
    */
  def fact(i: Int): Int = if (i == 0) 1 else fact(i - 1)*i

  /**
    * compute the n'th fibonacci number
    *
    * hint: use a internal loop function which should be written in a way that it is tail
    * recursive.
    *
    * https://en.wikipedia.org/wiki/Fibonacci_number
    */
  def fib(n: Int): Int = {
    def fib2(a:Int,b:Int,i:Int):Int =
      if
        (i==0) 0
      else if (i == 1) b
    else
        fib2(b,a+b,i-1)
    fib2(0,1,n)
  }

  /**
    * Implement a isSorted which checks whether an Array[A] is sorted according to a
    * given comparison function.
    *
    * Implementation hint: you always have to compare two consecutive elements of the array.
    * Elements which are equal are considered to be ordered.
    */
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
    def sort(list:Array[A]):Boolean =
      if (list.length < 2) true
    else
        gt(list.head,list.tail.head)&& sort(list.tail)
    sort(as)

  }

  /**
    * Takes both lists and combines them, element per element.
    *
    * If one sequence is shorter than the other one, the function stops at the last element
    * of the shorter sequence.
    */
  def genPairs[A, B](as: Seq[A], bs: Seq[B]): Seq[(A, B)] = as.zip(bs)

  // a simple definition of a linked list, we define our own list data structure
  sealed trait MyList[+A]

  case object MyNil extends MyList[Nothing]

  case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

  // the companion object contains handy methods for our data structure.
  // it also provides a convenience constructor in order to instantiate a MyList without hassle
  object MyList {

    def sum(list: MyList[Int]): Int = list match {
      case MyNil =>0
      case Cons(head,tail) => head + sum(tail)
    }

    def product(list: MyList[Int]): Int = list match {
      case MyNil =>1
      case Cons(head,tail) => head* product(tail)
    }

    def apply[A](as: A*): MyList[A] = {
      if (as.isEmpty)  MyNil
      else Cons(as.head, apply(as.tail: _*))
    }

  }

}

