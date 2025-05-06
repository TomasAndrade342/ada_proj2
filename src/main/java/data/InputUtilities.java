package data;

/*
 * Authors:
 *  Tomás Fonseca de Andrade, Nº 66196
 *  Paulo André Adriano Aires, Nº 71521
 */
public class InputUtilities {


    /**
     * Convert a string array full of integers to an int array of the same length
     * @param stringArr - the string array
     * @param arrLength - the size of the string array
     * @return the int array
     */
    public static int[] stringArrToIntArr(String[] stringArr, int arrLength) {
        int[] ret = new int[arrLength];

        for (int i = 0; i < arrLength; i++) {
            ret[i] = Integer.parseInt(stringArr[i]);
        }
        return ret;
    }

    /**
     * Converts a string full of integers interpolated by spaces into an int array given the number of integers
     * @param line - the string
     * @param length - the number of integers
     * @return the int array
     */
    public static int[] getIntArrFromLine(String line, int length) {
        return stringArrToIntArr(line.split(" "), length);
    }
}
