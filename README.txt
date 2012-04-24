Objectives

To implement Priority Queue ADTs
To apply priority queues to a difficult problem and measure the time and performance
To implement a classic search algorithm to solve a problem
Background

Searching has been used as an effective method to solve complex problems whose solutions are within a huge space (i.e. there are lots of possibilities which may or may not be a solution). A search typically starts with the initial state S0 that satisfies the initial problem conditions and continuously generates new states from S0 and its descendents until the problem's goal state Sg that satisfies the solution conditions is generated. (In some problems there may be more than one goal state, but not in our project). A new state Sj is generated from a known state Si (initially only S0 is known) by an applicable "generation operator", and we say that Sj is a child of Si. A state is called an open state if none of its children have been generated, a closed state if all of its children have been generated, and a dead state if it is not a goal state and it cannot have a child (i.e., no generation operator is applicable to that state). The path (i.e. the set of states) from S0 to Sg is a solution to the problem. In this project we will be implementing a search method known as best-first to solve a child's puzzle.

Description

A simple but challenging child's hand-held toy is a small square plastic board known as the 8-puzzle. (A larger 15-puzzle is also available). In an 8-puzzle, there are 9 positions, one of which is empty with the others occupied by small, movable "tiles" numbered 1 — 8. The tiles may be moved horizontally or vertically to the empty position (thereby creating a new empty position). When the tiles are arranged in some predetermined ending position you win.

In this project, you are asked to implement a search method, called the best-first method to solve the 8-puzzle problem. This search method is supported by a priority queue. Given an initial configuration, S0, of 8 numbered tiles, 1, 2, —, 8, on a 3 x 3 board, move the tiles in such a way so as to produce a desired goal configuration, of the tiles, Sg, using the smallest number of moves (states).