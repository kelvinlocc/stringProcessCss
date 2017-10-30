package Function;

import com.sun.org.apache.bcel.internal.classfile.Constant;

import java.util.ArrayList;

import static Function.AssiFunc.clearNonClassBlock_subClass;


public class ExtractFuc {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    static void PrintTEst123(){

        System.out.println(ANSI_RED+"123test123"+ANSI_RESET);
    }



    public static ArrayList<String> extractBlockFromLevelOne(ArrayList<String> myArrayL, String className) {//todo //todo


        System.out.printf("extractBlockFromLevelOne ");
        System.out.println("length " + myArrayL.size());
        ArrayList<String> myNewArrayList = new ArrayList<>();
        String blockClass = " ";


        int bracketOpen = 0;
        boolean getNextBlock = true;
        for (int i = 0; i < myArrayL.size(); i++) {
            String string = myArrayL.get(i);
            //very important to clear out the string property
            string = string.replace("\n", "").replace("\r", "");
            System.out.printf("line " + i + " :");
            System.out.print(myArrayL.get(i) + "\r\n");

            blockClass = blockClass + string;


            if (string.contains("{")) {
                bracketOpen++;

            }
            if (string.contains("}")) {
                bracketOpen--;
                if(bracketOpen == 0){
                    System.out.printf(" >>>  ");

                    System.out.println(ANSI_RED+blockClass+ANSI_RESET);
                    myNewArrayList.add(blockClass);
                    blockClass = " ";
                    getNextBlock = false;
                }
            }
        }

        System.out.println("end of extractBlockFromLevelOne function");

        clearNonClassBlock_subClass(myNewArrayList);
        return myNewArrayList;
    }
}
