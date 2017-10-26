
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Objects;

public class mainClass {
    static InputStream myInputStream = null;
    static String filePath = "C://Users/chichiu/Desktop/test.txt";
    static ArrayList<String> myLineArrayList = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";


    public static void main(String[] args) throws Exception {

        myInputStream = new FileInputStream(filePath);
//        printOriginalFile(); // print whole original file text


        String myString = convertStreamToString(myInputStream);
//        System.out.print(myString);
        String[] myLinesArray = myString.split("\\n");


        System.out.println("myLinesArray.length " + myLinesArray.length);
        // add each line into array list
        for (int i = 0; i < myLinesArray.length; i++) {
//            System.out.printf("line "+i+" :");
//            System.out.println(myLinesArray[i]);
            myLineArrayList.add(myLinesArray[i]);

        }

//        debugPrintList(myLineArrayList);
//        clearComment(myLineArrayList);
//        System.out.print("==========================================");
        debugPrintList(clearEmptyLine(clearComment(myLineArrayList)));
//        debugPrintList(clearComment(myLineArrayList));

//
//        String line;
//
//        try (
//                InputStreamReader isr = new InputStreamReader(myInputStream, Charset.forName("UTF-8"));
//                BufferedReader br = new BufferedReader(isr);
//        ) {
//            while ((line = br.readLine()) != null) {
//                // Do your thing with line
//                String[] words = line.split(" ");
//                System.out.print(words);
//
//
//            }
//        }


    }


    static void debugPrintList(ArrayList<String> myArrayL) {
        System.out.printf("debugPrintList ");
        System.out.println("length " + myArrayL.size());

        for (int i = 0; i < myArrayL.size(); i++) {
            System.out.printf("line " + i + " :");
            System.out.println(myArrayL.get(i));
        }
    }

    static ArrayList<String> clearComment(ArrayList<String> myArrayL) {
        System.out.printf("debugPrintList ");
        System.out.println("length " + myArrayL.size());
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);

        boolean commentEnded = false;
        for (int i = 0; i < myArrayL.size(); i++) {

            System.out.printf("line " + i + " :");

            //remove empty line
            if (myArrayL.get(i).length() == 1) {

                System.out.println(ANSI_RED + "***empty line!" + ANSI_RESET);

            } else if (myArrayL.get(i).contains("/*")) {

                commentEnded = false;
                if (myArrayL.get(i).contains("*/")) {
                    commentEnded = true;
                }
                myArrayL.set(i, "");

                System.out.println(ANSI_RED + "***comments.!" + ANSI_RESET);

            } else if (!commentEnded) {
                if (myArrayL.get(i).contains("*/")) {
                    commentEnded = false;
                }
                myArrayL.set(i, "");
            } else {

                System.out.println(myArrayL.get(i));

            }

        }


        return myArrayL;
    }

    static ArrayList<String> clearEmptyLine(ArrayList<String> myArrayL) {
        System.out.printf("debugPrintList ");
        System.out.println("length " + myArrayL.size());

        for (int i = myArrayL.size()-1; i > 0; i--) {
            if(myArrayL.get(i).length()<=1){
                System.out.println(ANSI_RED+"length "+myArrayL.get(i).length()+" "+ANSI_RESET);
//                System.out.printf(ANSI_RED+"empty");
                myArrayL.remove(i);
            }else {
                System.out.println("length "+myArrayL.get(i).length()+" "+myArrayL.get(i));

            }
//            System.out.printf("line " + i + " :");
//            System.out.println(myArrayL.get(i));
        }

        return myArrayL;
    }


    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static void printOriginalFile() throws IOException {

        int i;
        char c;
        try {

            // new input stream created //C:\Users\chichiu\Desktop\


            // reads till the end of the stream
            while ((i = myInputStream.read()) != -1) {

                // converts integer to character
                c = (char) i;


                // prints character

                System.out.print(c);

            }


        } catch (Exception e) {

            // if any I/O error occurs
            e.printStackTrace();
        } finally {

            // releases system resources associated with this stream
            if (myInputStream != null)
                myInputStream.close();
        }
    }
}