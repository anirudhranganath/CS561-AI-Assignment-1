Code for CS 561 AI Assignment

Note:
Expanded Order is North, East, South, West. This means that BFS will visit nodes in order N,E,W,S and DFS would visit it in order West, South, East, North from the LIFO queue.
Hence, on an infinitely large grid with no walls, it would go west,west,west,west...
To change this  behaviour to north,north,north, uncomment the line before returning in Solver.java, expand function. (//Collections.reverse(retVal);)

The search log iteration does not contain the goal node since goal isn't expanded. Expansion of goal node is not counted as an iteration.

Manhattan distance is the heuristic implemented for AStar and Beam Search. To implement euclidean distance, change lines 44 and 49 in Search.java to Algorithm.AS1 and Algorithm.BS1

Compile:
make
make clean (to clean)

Run:
java search {BFS|DFS|AStar|Beam [-k value]} -s initialspeed -i inputfilename –o outputfilename