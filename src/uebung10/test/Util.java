package uebung10.test;

public class Util {
    public static boolean checkArrayEmpty(Object[] array) {
        for (Object o: array) {
            if (o != null) {
                return false;
            }
        }
         return true;
        }
}
