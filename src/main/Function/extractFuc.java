package main.Function;

import java.util.ArrayList;
import java.util.List;

import static main.myConstants.ANSI_RED;
import static main.myConstants.ANSI_RESET;
import static main.Function.assiFunc.clearNonClassBlock_subClass;


public class extractFuc {

    static void PrintTEst123() {

        System.out.println(ANSI_RED + "123test123" + ANSI_RESET);
    }


    //change to 2 d array to store data


    // ConvertLines_To_TwoDiList by 2D list array
    public static List<List<String>> ConvertLines_To_TwoDiList(ArrayList<String> myArrayL, String className) {//todo //todo


        List<List<String>> listOfLists = new ArrayList<List<String>>();
        List<String> OneList = new ArrayList<String>();

        System.out.printf("ConvertLines_To_TwoDiList ");
        System.out.println("length " + myArrayL.size());
        ArrayList<String> myNewArrayList = new ArrayList<>();
        String blockClass = " ";


        int bracketOpen = 0;
        boolean getNextBlock = true;
        for (int i = 0; i < myArrayL.size(); i++) {
            String string = myArrayL.get(i);

            OneList.add(string);
//
//            System.out.printf("line " + i + " :");
//            System.out.print(myArrayL.get(i) + "\r\n");

//            blockClass = blockClass + string;


            if (string.contains("{")) {
                bracketOpen++;

            }
            if (string.contains("}")) {
                bracketOpen--;
                if (bracketOpen == 0) {
                    System.out.printf(" >>>  ");

//                    System.out.println(ANSI_RED + blockClass + ANSI_RESET);
//                    debugFuc.debugPrintList(OneList,"myInsideFunction ",false);

                    listOfLists.add(OneList);
                    OneList = new ArrayList<>();
                }
            }
        }

        System.out.println("end of ConvertLines_To_TwoDiList function");

//        clearNonClassBlock_subClass(myNewArrayList);
        return myNewArrayList;
    }


    //extract block without media block;
    public static ArrayList<String> extractBlockSimple(List<List<String>> tempLL, String className) {



        System.out.println("extractBlockSimple... ");
        List<List<String>> newLL = new ArrayList<>();

        for (int i = 0; i < tempLL.size(); i++) {
            if (isListSimpleBlock(tempLL.get(i),className)){
//                List<String> strings = new ArrayList<>();

                newLL.add(tempLL.get(i));
            }
        }

        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < myArrayL.size(); i++) {
            if (!myArrayL.get(i).contains("@media") && myArrayL.get(i).contains(className)) {
                temp.add(myArrayL.get(i));
                System.out.printf("add: ");
                System.out.println(myArrayL.get(i));
            }
        }


        return temp;
    }


    // return true if block is valid and not inside media
    static boolean isListSimpleBlock(List<String> tempL,String className) {
        boolean going = false;
        String sumString = "";
        for (int i = 0; i < tempL.size(); i++) {
            String temp = tempL.get(i);
            temp =temp.replace("\n", "").replace("\r", "");
            sumString += temp;
        }
        return !sumString.contains("@media") && sumString.contains(className);
    }

    public static ArrayList<String> extractClassFromEveryBlock(ArrayList<String> myArrayL, String className) {
        System.out.printf("extractClassFromEveryBlock ");
        System.out.println("length " + myArrayL.size());
        ArrayList<String> myNewArrayList = new ArrayList<>();

        int bracketOpen = 0;
        boolean eatFirstBracket = false;
        for (int i = 0; i < myArrayL.size(); i++) {

            System.out.printf("line " + i + " :");


            System.out.print(myArrayL.get(i) + "\r\n");


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

        return myNewArrayList;
    }


    //not using
    public static ArrayList<String> extractBlockFromLevelOneBackUp(ArrayList<String> myArrayL, String className) {//todo //todo


        System.out.printf("ConvertLines_To_TwoDiList ");
        System.out.println("length " + myArrayL.size());
        ArrayList<String> myNewArrayList = new ArrayList<>();
        String blockClass = " ";


        int bracketOpen = 0;
        boolean getNextBlock = true;
        for (int i = 0; i < myArrayL.size(); i++) {
            String string = myArrayL.get(i);
            //very important to clear out the string property
            string = string.replace("\n", "\\n").replace("\r", "\\r");
            System.out.printf("line " + i + " :");
            System.out.print(myArrayL.get(i) + "\r\n");

            blockClass = blockClass + string;


            if (string.contains("{")) {
                bracketOpen++;

            }
            if (string.contains("}")) {
                bracketOpen--;
                if (bracketOpen == 0) {
                    System.out.printf(" >>>  ");

                    System.out.println(ANSI_RED + blockClass + ANSI_RESET);
                    myNewArrayList.add(blockClass);
                    blockClass = " ";
                    getNextBlock = false;
                }
            }
        }

        System.out.println("end of ConvertLines_To_TwoDiList function");

        clearNonClassBlock_subClass(myNewArrayList);
        return myNewArrayList;
    }


}
