Code for CS 561 AI Assignment

Note:
Expanded Order is North, East, South, West. This means that BFS will visit nodes in order N,E,W,S and DFS would visit it in order West, South, East, North from the LIFO queue.
Hence, on an infinitely large grid with no walls, it would go west,west,west,west...
To change this  behaviour to north,north,north, uncomment the line before returning in Solver.java, expand function. (//Collections.reverse(retVal);)

The search log iteration does not contain the goal node since goal isnt expanded