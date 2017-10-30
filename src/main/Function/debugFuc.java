package main.Function;

import java.util.ArrayList;
import java.util.List;

public class debugFuc {
    public static void debugPrintList(ArrayList<String> myArrayL, String msn, Boolean output) {
        System.out.printf("debugPrintList==================== " + msn);
        System.out.println("length " + myArrayL.size());

        for (int i = 0; i < myArrayL.size(); i++) {
            if (!output) {
                System.out.printf("line " + i + " :");
            }

            System.out.println(myArrayL.get(i));
        }
        System.out.printf("debugPrintList==================== end");

    }

    public static void debugPrintList(List<String> myArrayL, String msn, Boolean output) {
        System.out.printf("debugPrintList==================== " + msn);
        System.out.println("length " + myArrayL.size());

        for (int i = 0; i < myArrayL.size(); i++) {
            if (!output) {
                System.out.printf("line " + i + " :");
            }

            System.out.println(myArrayL.get(i));
        }
        System.out.printf("debugPrintList==================== end");

    }


}
