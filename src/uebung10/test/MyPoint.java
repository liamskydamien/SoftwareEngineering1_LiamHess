package uebung10.test;

public class MyPoint implements Comparable<MyPoint>{

    private final double x,y;
    public MyPoint(double v, double v1) {
        x = v;
        y =v1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(MyPoint o) {
        if(getX() == o.getX() & getY() == o.getY()){
            return 0;
        }
        else if(getX() <= o.getX() & getY() <= o.getY()){
            return -1;
        }
        else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if((o instanceof MyPoint test)) {
            return getX() == test.getX() & getY() == test.getY();
        }
        else {
            return false;
        }
    }
}
