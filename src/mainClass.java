
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class mainClass {
    static InputStream myInputStream = null;
    static String absoluteFilePath = "C://Users/chichiu/Desktop/test.txt"; // absolute path
    //    static String relativeFilePath = "./file/test.txt"; // relative path // not working
    static ArrayList<String> myLineArrayList = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    static String cssClassToFind = "";
    static ArrayList<String> tempAL = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        System.out.println(System.getProperty("user.dir"));

        myInputStream = mainClass.class.getResourceAsStream("test.txt");

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

//        debugPrintList(myLineArrayList);
//        clearComment(myLineArrayList);

//        debugPrintList(clearEmptyLine(clearComment(myLineArrayList)));

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

        tempAL = extractClassFromEveryBlock(clearEmptyLine(clearComment(myLineArrayList)), "@media");

        tempAL = removeClassPlus(tempAL, ".jumbotron");
//        removeClass(tempAL, ".header");
    }


    // get clear css data and class name, return new css data
    static ArrayList<String> extractClassFromEveryBlock(ArrayList<String> myArrayL, String className) {
        System.out.printf("extractClassFromEveryBlock ");
        System.out.println("length " + myArrayL.size());
        ArrayList<String> myNewArrayList = new ArrayList<>();

        int bracketOpen = 0;
        boolean findFirstBracket = false;
        for (int i = 0; i < myArrayL.size(); i++) {

            System.out.printf("line " + i + " :");


            System.out.print(myArrayL.get(i) + "\r\n");


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

    static ArrayList<String> extractBlockFromLevelOne(ArrayList<String> myArrayL, String className) {//todo //todo
        System.out.printf("extractClassFromEveryBlock ");
        System.out.println("length " + myArrayL.size());
        ArrayList<String> myNewArrayList = new ArrayList<>();

        int bracketOpen = 0;
        boolean findFirstBracket = true;
        String blockClass = "";
        for (int i = 0; i < myArrayL.size(); i++) {
            String string = myArrayL.get(i);
            System.out.printf("line " + i + " :");


            System.out.print(myArrayL.get(i) + "\r\n");


            if (string.contains("{")) {
                bracketOpen++;
                blockClass += string;
            }
            if (string.contains("}")) {
                bracketOpen--;
                blockClass += string;
            }

            if (bracketOpen == 0) {
                findFirstBracket = true;
                myNewArrayList.add(blockClass);
                blockClass = "";
            }

            if (findFirstBracket) {
                blockClass += myArrayL.get(i);
            }


        }

//        debugPrintList(myNewArrayList);

        return myNewArrayList;
    }


    static ArrayList<String> extractClassWithoutMediaBlock(ArrayList<String> myArrayL, String className) {
        System.out.printf("extractClassFromEveryBlock ");
        System.out.println("length " + myArrayL.size());
        ArrayList<String> myNewArrayList = new ArrayList<>();

        int bracketOpen = 0;
        boolean eatFirstBracket = false;
        boolean skip = false;
        for (int i = 0; i < myArrayL.size(); i++) {

            System.out.printf("line " + i + " :");


            System.out.print(myArrayL.get(i) + "\r\n");

            if (myArrayL.get(i).contains("@media")) {


                if (myArrayL.get(i).contains("{")) {
                    bracketOpen++;
                } else {
                    eatFirstBracket = true;
                }
                if (myArrayL.get(i).contains("}")) {
                    bracketOpen--;
                }


            } else if (bracketOpen > 0) {

                if (myArrayL.get(i).contains("{")) {
                    bracketOpen++;
                } else {
                    eatFirstBracket = true;
                }
                if (myArrayL.get(i).contains("}")) {
                    bracketOpen--;
                }

                if (!skip) {
                    if (myArrayL.get(i).contains(className)) {
                        //String string = myArrayL.get(i) + Integer.toString(bracketOpen);
                        myNewArrayList.add(myArrayL.get(i));


                        if (myArrayL.get(i).contains("{")) {
                            bracketOpen++;
                        } else {
                            eatFirstBracket = true;
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

                    } else if (eatFirstBracket) {
                        if (myArrayL.get(i).contains("{")) {
                            myNewArrayList.add(myArrayL.get(i));
                            bracketOpen++;
                            eatFirstBracket = false;
                        }
                    }
                }
            }


        }
        debugPrintList(myNewArrayList);

        return myArrayL;
    }

    static ArrayList<String> removeClassPlus(ArrayList<String> myArrayL, String className) {
        System.out.println("removeClassPlus: ");

        ArrayList<String> lineToRemove = new ArrayList<>();

        lineToRemove = extractClassFromEveryBlock(myArrayL, className);  // contain line to remove
        debugPrintList(lineToRemove);

        int counter = 0;
        for (int i = 0; i < myArrayL.size(); i++) {
            for (int j = 0; j < lineToRemove.size(); j++) { //todo
                if (counter < lineToRemove.size()) {
                    if (myArrayL.get(i).contains(lineToRemove.get(counter))) {
                        myArrayL.set(i, "");
                        counter++;
                    }
                }

//                if(myArrayL.get(i).contains(lineToRemove.get(j))){
//                    myArrayL.set(i,"");
//                }
            }

        }

        debugPrintList(myArrayL);


        return myArrayL;
    }


    static ArrayList<String> removeClassRE(ArrayList<String> myArrayL, String className) {
        System.out.println("removeClassPlus: ");

        ArrayList<String> lineToRemove = new ArrayList<>();

        lineToRemove = extractClassFromEveryBlock(myArrayL, className);  // contain line to remove
        debugPrintList(lineToRemove);

        int counter = 0;
        for (int i = 0; i < myArrayL.size(); i++) {
            for (int j = 0; j < lineToRemove.size(); j++) { //todo
                if (counter < lineToRemove.size()) {
                    if (myArrayL.get(i).contains(lineToRemove.get(counter))) {
                        myArrayL.set(i, "");
                        counter++;
                    }
                }

//                if(myArrayL.get(i).contains(lineToRemove.get(j))){
//                    myArrayL.set(i,"");
//                }
            }

        }

        debugPrintList(myArrayL);


        return myArrayL;
    }


    static ArrayList<String> removeClass(ArrayList<String> myArrayL, String className) {
        System.out.printf("removeClass ");
        System.out.println("length " + myArrayL.size());

        int bracketOpen = 0;
        boolean eatFirstBracket = false;
        for (int i = 0; i < myArrayL.size(); i++) {

            System.out.printf("line " + i + " :");


            System.out.print(myArrayL.get(i) + "\r\n");


            if (myArrayL.get(i).contains(className)) {


                if (myArrayL.get(i).contains("{")) {
                    bracketOpen++;
                } else {
                    eatFirstBracket = true;
                }
                if (myArrayL.get(i).contains("}")) {
                    bracketOpen--;
                }
                myArrayL.set(i, "");
            } else if (bracketOpen > 0) {

                if (myArrayL.get(i).contains("{")) {
                    bracketOpen++;
                }

                if (myArrayL.get(i).contains("}")) {
                    bracketOpen--;

                }
                myArrayL.set(i, "");
            } else if (eatFirstBracket) {
                if (myArrayL.get(i).contains("{")) {
                    myArrayL.set(i, "");
                    bracketOpen++;
                    eatFirstBracket = false;
                }
            }


        }
        debugPrintList(myArrayL);

        return myArrayL;
    }

    static void checkBracket(String string) {
        boolean bracketOpen = false;
        if (string.contains("{")) {
            bracketOpen = true;
            if (string.contains("}")) {
                System.out.printf(ANSI_RED + "* { " + ANSI_RESET);
                System.out.println(ANSI_RED + "* }" + ANSI_RESET);
            } else {

                System.out.println("* { ");
            }

        } else {
            System.out.println(string);
        }
    }


    static void debugPrintList(ArrayList<String> myArrayL) {
        System.out.printf("debugPrintList==================== ");
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

//            System.out.printf("line " + i + " :");

            //remove empty line
            if (myArrayL.get(i).length() == 1) {

//                System.out.println(ANSI_RED + "***empty line!" + ANSI_RESET);

            } else if (myArrayL.get(i).contains("/*")) {

                commentEnded = false;
                if (myArrayL.get(i).contains("*/")) {
                    commentEnded = true;
                }
                myArrayL.set(i, "");

//                System.out.println(ANSI_RED + "***comments.!" + ANSI_RESET);

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

        for (int i = myArrayL.size() - 1; i > 0; i--) {
            if (myArrayL.get(i).length() <= 1) {
//                System.out.println(ANSI_RED + "length " + myArrayL.get(i).length() + " " + ANSI_RESET);
//                System.out.printf(ANSI_RED+"empty");
                myArrayL.remove(i);
            } else {
//                System.out.println("length " + myArrayL.get(i).length() + " " + myArrayL.get(i));

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