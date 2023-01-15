package uebung10.test;

public class BoundingBoxFactory {
    public static MyPrettyRectangle createBoundingBox(MyPrettyRectangle[] rectangles) {
        if (rectangles != null) {
            if (rectangles.length > 0) {
                MyPoint lowestLeft = rectangles[0].getBottomLeft();
                MyPoint highestRight = rectangles[0].getTopRight();
                for (MyPrettyRectangle myPrettyRectangle : rectangles) {
                    if (myPrettyRectangle.getBottomLeft().compareTo(lowestLeft) < 0) {
                        lowestLeft = myPrettyRectangle.getBottomLeft();
                    }
                    if (myPrettyRectangle.getTopRight().compareTo(highestRight) > 0) {
                        highestRight = myPrettyRectangle.getTopRight();
                    }
                }
                return new MyPrettyRectangle(lowestLeft, highestRight);
            }
            else {
                return new MyPrettyRectangle(0.0, 0.0, 0.0, 0.0);
            }
        }
        throw new IllegalArgumentException();
    }
}
