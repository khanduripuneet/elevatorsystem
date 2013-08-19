package elevator.system

object Main {
  def main(args: Array[String]) = {
    val servicePlan = Map(1 -> (1, 20), 2 -> (10, 20))
    val system = new ElevatorControlSystem(servicePlan)
    system.pickUp(10, true)
    println(system.status)
    system.step
    println(system.status)
    system.step
    println(system.status)

    system.pickUp(5, false)
    println(system.status)

    system.step
    println(system.status)
    system.step
    println(system.status)

    system.addStop(1, 8)
    println(system.status)

    system.step
    println(system.status)
    system.step
    println(system.status)

    system.pickUp(5, true)
    println(system.status)

    system.step
    println(system.status)
    system.step
    println(system.status)
    system.step
    println(system.status)
    system.step
    println(system.status)
    system.step
    println(system.status)
  }
}