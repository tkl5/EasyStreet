# EasyStreet


TCSS 305: Programming Practicum, Spring 2016



# Assignment Overview:
Our task was to write several classes to model vehicles in a city. Since many of the classes will have similarities, we are to use inheritance to effectively design these classes. The instructor has provided starter code for a graphical user interface that will draw the vehicles onto the map. 

Since each vehicle is different, they will have unique characteristics and have their own different movement patterns. The assignment will also allow us to properly use inheritance, polymorphism, and encapsulation.


# Technical Impression:
Getting started with the assignment as fairly simple. I started with creating the abstract class and methods and each individual vehicle class. Shortly I realized the only methods that the child class would use are canPass() and chooseDirection(), and everything else would be inherited from the parent class. 

I took a few days before writing any code to really understand the assignment. Reading and grasping what was required was a difficult aspect, which I still had to refer to the document even when I was nearly finished. Perhaps the most difficult task I had with this assignment was implementing random direction. Many vehicles shared a random direction functionality, but the key was to only have it reverse as a last resort. Unfortunately, the random method inside the Direction class included reverse, so I had to figure out a way to exclude this direction. 

I tried using arrays and only adding in left, right, and forward directions, but each time the city moved the vehicles would roll which direction to go so there would be a pause in some turns and vehicles would not travel smoothly. Vehicles such as the truck, human, and ATV would constantly go in reverse direction. This problem took several days for me to solve and I ended up using while loops in my implementation that are complicated and am not very happy with. 

After that, I quickly knocked out the test classes and fixed some further random issues. Implementing priority movements, such as in the Taxi and Bicycle classes also took a lengthy amount of time to figure out. 


# Unresolved problems:
The Truck test class was not able to achieve 100% code coverage. It also may not have covered every possible test. I was not sure whether to include override tags in all my methods, so I only overrode my toString class. 

Some of the logic, especially in the truck and human class can be simplified immensely. I did have some private helper methods that I thought about sending them up to the parent class, but they were unique depending on the vehicle so I decided to leave them in their respective child classes. 

Taxi and Bicycle classes do not pass every test. 

