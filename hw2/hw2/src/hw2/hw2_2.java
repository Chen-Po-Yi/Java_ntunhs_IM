package hw2;
import java.util.Scanner;
import java.util.Random;

public class hw2_2 {
    public static void main(String[] args) {
        Random rand = new Random();
        int minNumber = 1; // 最小數字
        int maxNumber = 100; // 最大數字
        int secretNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber; // 隨機生成的秘密數字
        int guess = 0; // 玩家猜測的數字
        int numGuesses = 0; // 猜測次數
        Scanner sc = new Scanner(System.in);

        System.out.println("歡迎來到終極密碼遊戲！我們已經生成了一個介於 " + minNumber + " 和 " + maxNumber + " 之間的數字，請你猜測它是多少。");

        while (true) {
            System.out.print("請輸入你的猜測：");
            guess = sc.nextInt();
            numGuesses++;

            if (guess < secretNumber) {
                minNumber = guess; // 更新最小範圍
                System.out.println("你猜的數字太小了，我們已經將範圍縮小到 " + minNumber + " 和 " + maxNumber + " 之間，請再試一次。");
            } else if (guess > secretNumber) {
                maxNumber = guess; // 更新最大範圍
                System.out.println("你猜的數字太大了，我們已經將範圍縮小到 " + minNumber + " 和 " + maxNumber + " 之間，請再試一次。");
            } else {
                System.out.println("恭喜你猜對了！答案是 " + secretNumber + "。你一共猜了 " + numGuesses + " 次。");
                break;
            }

            if (minNumber > maxNumber) {
                System.out.println("範圍錯誤，請確保你的猜測正確。");
                break;
            }
        }
    }
}
