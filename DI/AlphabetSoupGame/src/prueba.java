public class prueba {


//        String array[][] =  new String[][]{
//                {"a","b","c"},
//                {"d","e","f"},
//                {"g","h","i"},
//                {"j","k","l"},
//                {"m","n","o"},
//                {"p","r","s"},
//                {"t","u","v"},
//        };
//
//        String array2[][] = new String[][]{
//
//                {"c","b","a"},
//                {"f","e","d"},
//                {"i","h","g"},
//                {"l","k","j"},
//                {"o","n","m"},
//                {"s","r","p"},
//                {"v","u","t"}
//        };


    //
//
//        int c = 0;
//        int count = array.length + array[0].length -1;
//        int i = 0, j = 0;
//        //There can be at most  m + n -1 diagonals to be printed
//        while (c < count) {
//            //Start printing diagonals from i and j
//            printDiagonal(i, j, array);
//            if (i < array.length -1) {
//                //We increment row index until we reach the max number of rows
//                i++;
//            } else if (j < array[0].length - 1) {
//                //We are at maximum index of row; so its time to increment col index
//                //We increment column index until we reach the max number of columns
//                j++;
//            }
//            c++;
//        }
//        foo(array2);
//
//
//    }
//
//    static void foo(String [][] array2){
//        int c = 0;
//        int count = array2.length + array2[0].length -1;
//        int i = 0, j = 0;
//        //There can be at most  m + n -1 diagonals to be printed
//        while (c < count) {
//            //Start printing diagonals from i and j
//            printDiagonal(i, j, array2);
//            if (i < array2.length -1) {
//                //We increment row index until we reach the max number of rows
//                i++;
//            } else if (j < array2[0].length - 1) {
//                //We are at maximum index of row; so its time to increment col index
//                //We increment column index until we reach the max number of columns
//                j++;
//            }
//            c++;
//        }
//    }
//
//    static void printDiagonal(int i, int j, String[][] m) {
//        while (i >=0 && j< m[0].length ) {
//            System.out.print(m[i][j] + " ");
//            i--;
//            j++;
//        }
//        System.out.println("");
//    }
    public static void main(String[] args) {
        String str1 = "hola";
        String str2 = "haiholabe";
        String str3 = "ebalohiah";
        if (str2.contains(str1) || str3.contains(str1)) {
            System.out.println("true");

        }
    }

}
