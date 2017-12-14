package at.fhj.swengb.apps.calculator

import org.scalatest.WordSpecLike

import java.nio.file.{Files, Paths}


class TimesheetSpec extends WordSpecLike {

  "reading the Timesheet file" in {
    val p = Paths.get("C:\\workspace2\\fhj.swengb2017.assignments\\calculator\\timesheet-calculator.adoc")
    val lines = Files.readAllLines(p)
    println(lines.size)
  }

}
