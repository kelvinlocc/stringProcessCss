package main.Function;

import java.util.ArrayList;




public class assiFunc {
    public static ArrayList<String> clearComment(ArrayList<String> myArrayL) {
        System.out.printf("debugPrintList ");
        System.out.println("length " + myArrayL.size());
//        System.out.println(main.myConstants.ANSI_RED + "This text is red!" + main.myConstants.ANSI_RESET);

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

//                System.out.println(myArrayL.get(i));

            }

        }


        return myArrayL;
    }

    public static ArrayList<String> clearEmptyLine(ArrayList<String> myArrayL) {
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

    static ArrayList<String> clearNonClassBlock_subClass(ArrayList<String> myArrayL) {

        for (int i = myArrayL.size() - 1; i > 0; i--) {
            if (!myArrayL.get(i).contains("{")) {

                System.out.printf("remove: ");
                System.out.println(myArrayL.get(i));
                myArrayL.remove(i);
            }
        }

        debugFuc.debugPrintList(myArrayL, "clear up non class block check check----", true);

        return myArrayL;
    }

}
