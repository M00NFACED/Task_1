package ru.vsu.cs.pekshev.T1;


import ru.vsu.cs.pekshev.util.ArrayUtils;

import java.io.FileNotFoundException;

import static ru.vsu.cs.pekshev.T1.Maze.*;

public class task1 {
/*
26. Реализовать класс для представления лабиринта на клеточном поле. Должен быть
реализован метод поиска путей в лабиринте.
*/
    public static void main(String[] args) throws FileNotFoundException {
        Maze maze = new Maze(ArrayUtils.readCharArray2FromFile("src/ru/vsu/cs/pekshev/T1/Maze.txt"));
        printMaze(maze);
        System.out.println("Решение:");
        findEntranceAndEnd(maze);
        findSolution(maze);


    }
}

