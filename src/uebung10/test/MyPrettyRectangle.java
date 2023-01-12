package uebung10.test;

public class MyPrettyRectangle {
    private MyPoint topRight;
    private MyPoint bottomLeft;
    public MyPrettyRectangle(double v, double v1, double v2, double v3) {
        bottomLeft = new MyPoint(v,v1);
        topRight = new MyPoint(v2,v3);
    }

    public MyPoint getTopRight() {
        return topRight;
    }

    public void setTopRight(MyPoint topRight) {
        this.topRight = topRight;
    }

    public MyPoint getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(MyPoint bottomLeft) {
        this.bottomLeft = bottomLeft;
    }


    public MyPoint getCenter() {
        return new MyPoint((bottomLeft.getX() - topRight.getX()), (topRight.getY() - bottomLeft.getY()));
    }

    public boolean contains(MyPrettyRectangle middle) {
        return  middle.getBottomLeft().getX() >= bottomLeft.getX() &
                middle.getBottomLeft().getY() >= bottomLeft.getY() &
                middle.getTopRight().getX() <= topRight.getX() &
                middle.getTopRight().getY() <= topRight.getY();
    }
}
