package main;

import main.Function.assiFunc;
import main.Function.debugFuc;
import main.Function.extractFuc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class  mainClass {
    private static InputStream myInputStream = null;
    static String absoluteFilePath = "C://Users/chichiu/Desktop/test.txt"; // absolute path
    //    static String relativeFilePath = "./file/test.txt"; // relative path // not working
    private static ArrayList<String> myLineArrayList = new ArrayList<>();

    private static String cssClassToFind = "";
    private static ArrayList<String> tempAL = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("mainClass running");
        System.out.println(System.getProperty("user.dir"));
        myInputStream = main.mainClass.class.getResourceAsStream("/test.txt");



        String myString = convertStreamToString(myInputStream);
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

        //pure array list:
        pureAL = assiFunc.clearEmptyLine( assiFunc.clearComment(myLineArrayList));
        List<List<String>> tempTwoD = new ArrayList<>();
        tempTwoD = extractFuc.ConvertLines_To_TwoDiList(pureAL, "");

//        tempAL = extractFuc.extractBlockFromLevelOneBackUp(pureAL, "");//todo

        // get class block without media //todo
        extractFuc.extractBlockSimple(tempTwoD,".header");

        debugFuc.debugPrintList(extractFuc.extractBlockSimple(tempTwoD,".header"),"2d simple block ",false,"");

        debugFuc.debugPrintList(tempAL,"extract block without @media",false);

        //get class block with media

//        debugFuc.debugPrintList(extractFuc.extractClassFromEveryBlock(pureAL,".marketing")," my test ",false);

//        tempAL = extractFuc.ConvertLines_To_TwoDiList(pureAL, "");//todo

    }





    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}