package elevator.system

object Main extends App{
  val servicePlan = Map(1->(1,20), 2->(1,20))
  val system = new ElevatorControlSystem(servicePlan)
  system.pickUp(10, true)
  println(system.status)
  system.step
  println(system.status)
  system.step
  println(system.status)
  
  system.pickUp(5,true)
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