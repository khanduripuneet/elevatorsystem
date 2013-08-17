elevatorsystem
==============

The code is organized into a single package - elevator.system which contains the following scala classes:

Elevator - 
An elevator serves all floors between a lowest floor and a highest floor defined in the constructor.
It maintains a list of stops(floors that it needs to visit) based on incoming pick-up requests as well as stop requests made from within the elevator.
The ordering of the stops can be understood as an ordered circular linked-list. This helps avoid starvation scenarios and guarantees that a pending stop will be visited in less steps than 2 * # of floors.

ElevatorControlSystem - 

This serves as a manager for all elevators within a building.
An ElevatorControlSystem can be instantiated using a serviceMap that indicates the highest and lowest floors served by each elevator.
The ElevatorControlSystem internally maintains a map of elevator ids and the corresponding Elevator instances.
The API for ElevatorControlSystem includes the following methods
	
	status - This returns the status of all the elevators including ids, current floors, and planned stops for each Elevator.
	
	step - Allows all elevators to optionally move one floor up or down based on their current floor and their respective next stop.
	
	pickUp - Allows a user to request an elevator from any floor the request will routed to the temporally "closest" elevator.
	
	addStop - At any point a user from inside an elevator might request a stop at any floor served by the elevator using this method.
	
	update - This method overrides the internal state (current floor and planned stops) of any targeted elevators. This method is only to be used for testing and debugging purposes.
	
Main - 

This is an example of how to instantiate and use ElevatorControlSystem
