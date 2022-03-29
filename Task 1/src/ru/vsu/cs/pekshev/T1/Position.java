package ru.vsu.cs.pekshev.T1;


class Position {
    private int x;// абсцисса
    private int y;// ось Y
    public Position(int x, int y) {//структура
        this.x = x;
        this.y = y;
    }

    public Position(Position entrance) {
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

}

