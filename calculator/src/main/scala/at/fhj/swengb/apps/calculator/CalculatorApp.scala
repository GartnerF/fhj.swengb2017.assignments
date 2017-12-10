package at.fhj.swengb.apps.calculator

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.{Failure, Success}
import scala.util.control.NonFatal

object CalculatorApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[CalculatorFX], args: _*)
  }
}

class CalculatorFX extends javafx.application.Application {

  val fxml = "/at/fhj/swengb/apps/calculator/calculator.fxml"
  val css = "/at/fhj/swengb/apps/calculator/calculator.css"

  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Calculator")
      setSkin(stage, fxml, css)
      stage.show()
      stage.setMinWidth(stage.getWidth)
      stage.setMinHeight(stage.getHeight)
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

  def setSkin(stage: Stage, fxml: String, css: String): Boolean = {
    val scene = new Scene(mkFxmlLoader(fxml).load[Parent]())
    stage.setScene(scene)
    stage.getScene.getStylesheets.clear()
    stage.getScene.getStylesheets.add(css)
  }

}

class CalculatorFxController extends Initializable {

  val calculatorProperty: ObjectProperty[RpnCalculator] = new SimpleObjectProperty[RpnCalculator](RpnCalculator())

  def getCalculator() : RpnCalculator = calculatorProperty.get()

  def setCalculator(rpnCalculator : RpnCalculator) : Unit = calculatorProperty.set(rpnCalculator)

  @FXML var textNumber1 : TextField = _
  @FXML var textNumber2 : TextField = _
  @FXML var textResult : TextField = _

  override def initialize(location: URL, resources: ResourceBundle) = {

  }

  def Enter(): Unit = {
    getCalculator().push(Op(if (getCalculator().stack.size==0) textNumber1.getText else textNumber2.getText)) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => // show warning / error
    }
    getCalculator().stack foreach println
  }
  def BtAdd(): Unit = {
    getCalculator().push(Add) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => // show warning / error
    }
    textResult.setText(getCalculator().peek().toString.drop(4).dropRight(1))
  }
  def BtSub(): Unit = {
    getCalculator().push(Sub) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => // show warning / error

  }
    textResult.setText(getCalculator().peek().toString.drop(4).dropRight(1))
  }

  def BtMul(): Unit = {
    getCalculator().push(Mul) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => // show warning / error

    }
    textResult.setText(getCalculator().peek().toString.drop(4).dropRight(1))
  }
  def BtDiv(): Unit = {
    getCalculator().push(Div) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => // show warning / error
    }
    textResult.setText(getCalculator().peek().toString.drop(4).dropRight(1))
  }
  def Comma(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++",")
    else
      textNumber2.setText(textNumber2.getText()++",")
  }

  def Clear(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText("0")
    else
      textNumber2.setText("0")
  }
  def ChangeSign(): Unit = {
    if(getCalculator().stack.size==0)
    textNumber1.setText(if (textNumber1.getText().head == '-') textNumber1.getText().tail else "-" ++ textNumber1.getText() )
  else
    textNumber2.setText(if (textNumber2.getText().head == '-') textNumber2.getText().tail else "-" ++ textNumber2.getText() )

  }
  def numberZero(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"0")
    else
      textNumber2.setText(textNumber2.getText()++"0")
  }
  def numberOne(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"1")
    else
      textNumber2.setText(textNumber2.getText()++"1")
  }
  def numberTwo(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"2")
    else
      textNumber2.setText(textNumber2.getText()++"2")
  }
  def numberThree(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"3")
    else
      textNumber2.setText(textNumber2.getText()++"3")
  }
  def numberFour(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"4")
    else
      textNumber2.setText(textNumber2.getText()++"4")
  }
  def numberFive(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"5")
    else
      textNumber2.setText(textNumber2.getText()++"5")
  }
  def numberSix(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"6")
    else
      textNumber2.setText(textNumber2.getText()++"6")
  }
  def numberSeven(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"7")
    else
      textNumber2.setText(textNumber2.getText()++"7")
  }
  def numberEight(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"8")
    else
      textNumber2.setText(textNumber2.getText()++"8")
  }
  def numberNine(): Unit = {
    if(getCalculator().stack.size==0)
      textNumber1.setText(textNumber1.getText()++"9")
    else
      textNumber2.setText(textNumber2.getText()++"9")
  }





}