package ru.vsu.cs.pekshev.T1;

class Maze {
    private char[][] mazeData;// Поле лабиринта

    public Maze(char[][] mazeData) {
        this.mazeData = mazeData;

    }

    public char[][] getMazeData() {
        return mazeData;
    }

    public void setMazeData(char[][] mazeData) {
        this.mazeData = mazeData;


    }

    private boolean entranceFoundAlready;

    public boolean isEntranceFoundAlready() {
        return entranceFoundAlready;
    }

    public void setEntranceFoundAlready(boolean entranceFoundAlready) {
        this.entranceFoundAlready = entranceFoundAlready;
    }

    private Position entrance;

    public Position getEntrance() {
        return entrance;
    }

    public void setEntrance(Position entrance) {
        this.entrance = entrance;
    }

    private Position end;

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }


    public static void printMaze(Maze maze) {
        for (int i = 0; i < maze.getMazeData().length; i++) {
            for (int j = 0; j < maze.getMazeData()[0].length; j++) {
                System.out.print(maze.getMazeData()[i][j]);
            }
            System.out.println();
        }

    }


    public static void findEntranceAndEnd(Maze maze) {
        Position entrance = new Position(0, 0);
        Position end = new Position(0, 0);
        for (int i = 0; i < maze.getMazeData()[0].length; i++) {
            if (maze.getMazeData()[0][i] == '0') {
                if (!maze.isEntranceFoundAlready()) {
                    entrance.setX(i);
                    entrance.setY(0);

                    maze.setEntranceFoundAlready(true);
                    maze.setEntrance(entrance);
                    break;
                } else {
                    end.setX(i);
                    end.setY(0);
                    maze.setEnd(end);
                    return;
                }
            }
        }
        for (int i = 1; i < maze.getMazeData().length; i++) {
            if (maze.getMazeData()[i][maze.getMazeData().length - 1] == '0') {
                if (!maze.isEntranceFoundAlready()) {
                    entrance.setX(maze.getMazeData().length - 1);
                    entrance.setY(i);

                    maze.setEntranceFoundAlready(true);
                    maze.setEntrance(entrance);
                    break;
                } else {
                    end.setX(maze.getMazeData().length - 1);
                    end.setY(i);
                    maze.setEnd(end);
                    return;
                }
            }
        }
        for (int i = 0; i < maze.getMazeData()[0].length - 1; i++) {
            if (maze.getMazeData()[maze.getMazeData().length - 1][i] == '0') {
                if (!maze.isEntranceFoundAlready()) {
                    entrance.setX(i);
                    entrance.setY(maze.getMazeData().length - 1);

                    maze.setEntranceFoundAlready(true);
                    maze.setEntrance(entrance);
                    break;
                } else {
                    end.setX(i);
                    end.setY(maze.getMazeData().length - 1);
                    maze.setEnd(end);
                    return;
                }
            }
        }
        for (int i = 1; i < maze.getMazeData().length - 1; i++) {
            if (maze.getMazeData()[i][0] == '0') {
                if (!maze.isEntranceFoundAlready()) {
                    entrance.setX(0);
                    entrance.setY(i);

                    maze.setEntranceFoundAlready(true);
                    maze.setEntrance(entrance);
                    break;
                } else {
                    end.setX(0);
                    end.setY(i);
                    maze.setEnd(end);
                    return;
                }
            }
        }
    }

    public static void findSolution(Maze maze) {
        char[][] mazeWithSolution = maze.getMazeData();
        final char free = '0', path = '+', deadEnd = '#';
        int x = maze.getEntrance().getX();
        int y = maze.getEntrance().getY();
        while ((y != maze.end.getY()) || (x != maze.end.getX())) { // Условие выхода - найден финиш
            mazeWithSolution[y][x] = path;
            if (mazeWithSolution[y][x + 1] == free) {
                mazeWithSolution[y][x + 1] = path;
                x++;
                continue;
            }
            if (mazeWithSolution[y][x - 1] == free) {
                mazeWithSolution[y][x - 1] = path;
                x--;
                continue;
            }

            if (mazeWithSolution[y + 1][x] == free) {
                mazeWithSolution[y + 1][x] = path;
                y++;
                continue;
            }

            if (mazeWithSolution[y - 1][x] == free) {
                mazeWithSolution[y - 1][x] = path;
                y--;
                continue;
            }
            if (mazeWithSolution[y][x + 1] != free && // Если поиск пути не может продолжаться - это тупик,
                    mazeWithSolution[y][x - 1] != free && // возврат по пути назад до свободного перекрёстка
                    mazeWithSolution[y + 1][x] != free &&
                    mazeWithSolution[y - 1][x] != free) {
                mazeWithSolution[y][x] = deadEnd;

                if (mazeWithSolution[y][x + 1] == path) {
                    mazeWithSolution[y][x + 1] = deadEnd;
                    x++;
                    continue;
                }

                if (mazeWithSolution[y][x - 1] == path) {
                    mazeWithSolution[y][x - 1] = deadEnd;
                    x--;
                    continue;
                }

                if (mazeWithSolution[y + 1][x] == path) {
                    mazeWithSolution[y + 1][x] = deadEnd;
                    y++;
                    continue;
                }

                if (mazeWithSolution[y - 1][x] == path) {
                    mazeWithSolution[y - 1][x] = deadEnd;
                    y--;
                }
            }
        }
        for (int i = 0; i < mazeWithSolution.length; i++) { // тупики убираются, не оставляя следа в лабиринте
            for (int j = 0; j < mazeWithSolution.length; j++) {
                if (mazeWithSolution[i][j] == deadEnd) {
                    mazeWithSolution[i][j] = free;
                }
            }
        }
        Maze solvedMaze = new Maze(mazeWithSolution);
        printMaze(solvedMaze);
    }
}


