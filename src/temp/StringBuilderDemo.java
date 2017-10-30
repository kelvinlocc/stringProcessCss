import java.util.ArrayList;

public class StringBuilderDemo {
    public static void main(String[] args) {
        String palindrome = "Dot saw I was Tod";
        String string = "what?";
        StringBuilder sb = new StringBuilder(palindrome);
        sb.append(string);
        sb.append(string);
        sb.append(string);
        sb.append(string);
//        sb.reverse();  // reverse it

        System.out.println(sb);
        ArrayList<String> myArrayL = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            myArrayL.add(Integer.toString(i)+"\n");
        }
        for (int i = 0; i < myArrayL.size(); i++) {

            System.out.println("my string "+myArrayL.get(i));

        }

        extraction(myArrayL);
    }

    static void printTest(){
        System.out.println("123");
    }


    static void extraction(ArrayList<String> myArrayL) {
        System.out.println("the original list:");
//        debugPrintList(myArrayL);

        String[] temp = new String[myArrayL.size()];

        for (int i = 0; i < myArrayL.size(); i++) {
            temp[i] = myArrayL.get(i);
        }
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("\n")){
                System.out.println("error!");
                temp[i] = temp[i].replace("\n","");
            }
            string.append(temp[i]);
        }
        System.out.printf("my string ");

        System.out.printf(string.toString());

    }

}