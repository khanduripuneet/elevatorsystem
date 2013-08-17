
package elevator.system

sealed abstract class Direction
case object UP extends Direction
case object DOWN extends Direction
case object STOPPED extends Direction

class Elevator(val lowestFloor: Int, val highestFloor: Int) {

  val totalFloors = highestFloor - lowestFloor
  var currentFloor: Int = lowestFloor;
  var queuedStops: List[Int] = List();

  def status = (currentFloor, queuedStops)

  def step = if (!queuedStops.isEmpty) {
    if (queuedStops.head > currentFloor) {
      currentFloor = currentFloor + 1
    } else if (queuedStops.head < currentFloor) {
      currentFloor = currentFloor - 1
    }
    if (currentFloor == queuedStops.head) {
      queuedStops = queuedStops.drop(1);
    }
  }

  def pickUpScore(pickFloor: Int, up: Boolean): Int = {
    val distance = Math.abs(currentFloor - pickFloor)
    direction match {
      case UP => if (currentFloor <= pickFloor) distance else 2 * totalFloors - distance
      case DOWN => if (currentFloor >= pickFloor) distance else 2 * totalFloors - distance
      case STOPPED => distance
    }
  }
  def addStop(stopFloor: Int) {
    queuedStops = updateSchedule(stopFloor)
  }

  def updateSchedule(stopFloor: Int) = {
    if (stopFloor > highestFloor || stopFloor < lowestFloor) {
      throw new RuntimeException("Invalid Floor Requested")
    }
    val allStops = ((queuedStops.toSet + stopFloor) - currentFloor).toList
    def higherStops = allStops.filter(_ > currentFloor).sorted
    def lowerStops = allStops.filter(_ < currentFloor).sorted.reverse

    direction match {
      case UP => higherStops ::: lowerStops
      case DOWN => lowerStops ::: higherStops
      case STOPPED => allStops
    }
  }

  def direction: Direction = queuedStops match {
    case Nil => STOPPED
    case x :: xs => if (currentFloor < x) UP else DOWN
  }

  def update(floor: Int, stops: List[Int]) {
    if (floor > highestFloor || floor < lowestFloor) {
      throw new RuntimeException("Invalid Floor Requested")
    }
    currentFloor = floor
    queuedStops = stops
    if (!queuedStops.isEmpty && queuedStops.head == currentFloor) {
      queuedStops = queuedStops.drop(1)
    }
  }
}