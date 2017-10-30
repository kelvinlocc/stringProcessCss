

import Function.AssiFunc;
import Function.DebugFuc;
import Function.ExtractFuc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class mainClass {
    private static InputStream myInputStream = null;
    static String absoluteFilePath = "C://Users/chichiu/Desktop/test.txt"; // absolute path
    //    static String relativeFilePath = "./file/test.txt"; // relative path // not working
    private static ArrayList<String> myLineArrayList = new ArrayList<>();

    private static String cssClassToFind = "";
    private static ArrayList<String> tempAL = new ArrayList<>();

    public void main(String[] args) throws Exception {
        System.out.println("mainClass ");

        System.out.println(System.getProperty("user.dir"));

//        myInputStream = this.getClass().getResource("/textfiles/test.txt");

//        myInputStream = mainClass.class.getResourceAsStream("/test.txt");

//        myInputStream = new FileInputStream(relativeFilePath);
//        printOriginalFile(); // print whole original file text


        String myString = convertStreamToString(myInputStream);
//        System.out.print(myString);
        String[] myLinesArray = myString.split("\\n");


        System.out.println("myLinesArray.length " + myLinesArray.length);
        // add each line into array list
        for (int i = 0; i < myLinesArray.length; i++) {
            myLineArrayList.add(myLinesArray[i]);
        }

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("input a css class name to extract: ");
        String userInput = reader.nextLine(); // Scans the next token of the input as an int.
        //once finished

        System.out.println("Find class:  '" + userInput + "' ? (y/n)");
        while (reader.nextLine().contains("n")) {
            System.out.println("input a css class name to extract: ");
            userInput = reader.nextLine();
            System.out.println("Find class: " + userInput + " ? (y/n)");
        }
        reader.close();

        cssClassToFind = userInput;
        ArrayList<String> pureAL = new ArrayList<>();

        pureAL = AssiFunc.clearEmptyLine( AssiFunc.clearComment(myLineArrayList));

        tempAL = ExtractFuc.extractBlockFromLevelOne(pureAL, "");//todo


    }


    static ArrayList<String> extractClassFromEveryBlock(ArrayList<String> myArrayL, String className) {
        System.out.printf("extractClassFromEveryBlock ");
        System.out.println("length " + myArrayL.size());
        ArrayList<String> myNewArrayList = new ArrayList<>();

        int bracketOpen = 0;
        boolean findFirstBracket = false;
        for (int i = 0; i < myArrayL.size(); i++) {

            System.out.printf("line " + i + " :");
            String string = myArrayL.get(i);

//            System.out.print(myArrayL.get(i) + "\r\n");


            if (myArrayL.get(i).contains(className)) {
                //String string = myArrayL.get(i) + Integer.toString(bracketOpen);
                myNewArrayList.add(myArrayL.get(i));


                if (myArrayL.get(i).contains("{")) {
                    bracketOpen++;
                } else {
                    findFirstBracket = true;
                }
                if (myArrayL.get(i).contains("}")) {
                    bracketOpen--;
                }

            } else if (bracketOpen > 0) {
                myNewArrayList.add(myArrayL.get(i));
                if (myArrayL.get(i).contains("{")) {
                    bracketOpen++;
                }

                if (myArrayL.get(i).contains("}")) {
                    bracketOpen--;

                }

            } else if (findFirstBracket) {
                myNewArrayList.add(myArrayL.get(i));

                if (myArrayL.get(i).contains("{")) {

                    bracketOpen++;
                    findFirstBracket = false;

                } else {
                    findFirstBracket = true;
                }
            }


        }

//        debugPrintList(myNewArrayList);

        return myNewArrayList;
    }








    // print array list




public static void  mainClassTest(){
        System.out.println("123");
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