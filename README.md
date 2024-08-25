# CST370-HW3_2_Java

Write a Java program for hw3_2 that reads an input graph data from a user. Then, it should present a path for the travelling salesman problem (TSP). In the assignment, you can assume that the maximum number of vertices in the input graph is less than or equal to 15.

Input format: This is a sample input from a user. 

4
12
0 1 2
0 3 7
0 2 5
1 0 2
1 2 8
1 3 3
2 0 5
2 1 8
2 3 1
3 0 7
3 1 9
3 2 1
0


The first line (= 4 in the example) indicates that there are four vertices in the graph. The next line (= 12 in the example) indicates the number of edges in the graph. The remaining 12 lines are the edge information with the “source vertex”, “destination vertex”, and “cost”. The last line (= 0 in the example) indicates the starting vertex of the travelling salesman problem. 



Sample Run 0: Assume that the user typed the following lines

4
12
0 1 2
0 3 7
0 2 5
1 0 2
1 2 8
1 3 3
2 0 5
2 1 8
2 3 1
3 0 7
3 1 9
3 2 1
0


This is the correct output. Your program should present the path and total cost in separate lines.

Path:0->1->3->2->0
Cost:11 


Sample Run 1: Assume that the user typed the following lines

5
6
0 2 7
3 1 20
0 4 3
1 0 8
2 4 100
3 0 19
3

This is the correct output.

Path:
Cost:-1 

Note that if there is no path for the TSP, your program should present empty path and -1 cost.



Sample Run 2: Assume that the user typed the following lines

5
7
0 2 8
2 1 7
2 4 3
1 4 100
3 0 20
3 2 19
4 3 50
3

This is the correct output of your program.

Path:3->0->2->1->4->3
Cost:185



[Hint]: To solve this problem, you can use all permutations of the vertices, except the starting vertex. For example, there are three vertices 1, 2, and 3, in the first sample run, except the starting vertex 0. This is all permutations with the three vertices
	1, 2, 3
	1, 3, 2
	2, 1, 3,
	2, 3, 1
	3, 1, 2
	3, 2, 1

For each permutation, you can calculate the cost. For example, in the case of the permutation “1, 2, 3”, add the starting vertex city at the very beginning and end to the permutation which generates “0, 1, 2, 3, 0”. Therefore, the cost of this path is “18”. This way, you can check all possible paths for the TSP.
