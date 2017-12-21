package at.fhj.swengb.apps.battleship

import java.{lang, util}

import scala.collection.JavaConverters._
import at.fhj.swengb.apps.battleship.model._

object BattleShipProtocol {

  def convert(g : BattleShipGame) : BattleShipProtobuf.BattleShipGame = BattleShipProtobuf.BattleShipGame.newBuilder().build()

  def convert(g : BattleShipProtobuf.BattleShipGame) : BattleShipGame = {
    val width = g.getBattleField.getWidth
    val height = g.getBattleField.getHeight
    val fleet = g.getBattleField.getFleet.getVesselList.asScala.map(convert).toSet
    val battleField: BattleField = BattleField(width,height,Fleet(fleet))
    BattleShipGame(battleField, null, null, null)
  }

  def convert(g: BattleShipProtobuf.Vessel) : Vessel = {
    val name = g.getName
    val direction = {
      g.getDirection match {
        case "H" => Horizontal
        case "V" => Vertical
      }
    }
    Vessel(NonEmptyString(name),convert(g.getStartPos),direction,g.getSize)
  }
  def convert(pos: BattlePos): BattleShipProtobuf.Pos = BattleShipProtobuf.Pos.newBuilder().setX(pos.x).setY(pos.y).build()
  def convert(pos: BattleShipProtobuf.Pos) : BattlePos = BattlePos(pos.getX,pos.getY)
}
