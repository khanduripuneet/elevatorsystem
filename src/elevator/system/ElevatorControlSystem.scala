package elevator.system

class ElevatorControlSystem(servicePlan: Map[Int, (Int, Int)]) {
  val elevators: Map[Int, Elevator] = servicePlan.map(plan => plan._1 -> new Elevator(plan._2._1, plan._2._2))

  def status = for (elevator <- elevators) yield (elevator._1 -> elevator._2.status)

  def step = elevators.map(_._2.step)

  def pickUp(pickUpFloor: Int, up: Boolean) {
    val applicableElevators = elevators.filter(e =>
      e._2.highestFloor > pickUpFloor && e._2.lowestFloor < pickUpFloor
        || e._2.highestFloor == pickUpFloor && !up
        || e._2.lowestFloor == pickUpFloor && up)
    elevators(findBestElevatorForPickUp(pickUpFloor, up)).addStop(pickUpFloor)
  }

  def addStop(elevatorId: Int, stopFloor: Int) {
    elevators(elevatorId).addStop(stopFloor)
  }

  def update(elevatorId: Int, currentFloor: Int, queuedStops: List[Int]) {
    elevators(elevatorId).update(currentFloor, queuedStops)
  }

  def findBestElevatorForPickUp(pickUpFloor: Int, up: Boolean): Int = {
    val pickUpScores: Map[Int, Int] = elevators.map(elevator =>
      elevator._1 -> elevator._2.pickUpScore(pickUpFloor, up))
    pickUpScores.toList.sortBy(_._2).head._1
  }
}