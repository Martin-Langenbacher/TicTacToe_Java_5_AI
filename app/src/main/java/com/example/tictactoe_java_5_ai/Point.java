package com.example.tictactoe_java_5_ai;

class Point {

    public int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "[" + x + ", " +y + "]";
    }

}

