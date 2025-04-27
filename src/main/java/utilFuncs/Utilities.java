package utilFuncs;

public class Utilities {


    public static int[] stringArrToIntArr(String[] stringArr, int arrLength) {
        int[] ret = new int[arrLength];

        for (int i = 0; i < arrLength; i++) {
            ret[i] = Integer.parseInt(stringArr[i]);
        }
        return ret;
    }

    public static int[] getIntArrFromLine(String line, int length) {

        return stringArrToIntArr(line.split(" "), length);
    }
}
