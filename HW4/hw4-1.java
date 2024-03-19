import java.util.Scanner;
public class hw4 {
    public static void main(String[] args) {
        String temp = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("這是可以陪你聊程式! 你可以問我一些問題");
        while (true) {
            System.out.print("你說:");
            temp = sc.nextLine();
            if (temp.equals("0")) {
                break;}
            temp = handleQuestion(temp);
            System.out.println( temp);       }
        sc.close();   }
    public static String handleQuestion(String question) {
        question = question.replace("嗎", "");
        question = question.replace("？", "！");
        question = question.replace("會不會", "會"); question = question.replace("能不能", "能");
        question = question.replace("你能", "我能");question = question.replace("你會", "我會");
        return question;}}
