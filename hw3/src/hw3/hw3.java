package hw3;
import java.util.Scanner; 
public class hw3 { 
public static void main(String[] args) { 
Scanner sc = new Scanner(System.in); System.out.print("請輸入陣列大小: ");
int size = sc.nextInt();
int[] originalArray = new int[size]; System.out.println("請輸入" + size + "個整數："); 
for (int i = 0; i < size; i++) { 
originalArray[i] = sc.nextInt();
} System.out.println("原始陣列："); printArray(originalArray); reverseArray(originalArray); System.out.println("\n反轉後的陣列："); printArray(originalArray); } 
private static void reverseArray(int[] arr) { 
int start = 0;
int end = arr.length - 1; 
while (start < end) { 
int temp = arr[start]; 
arr[start] = arr[end];
arr[end] = temp; 
start++; end--; } } 
private static void printArray(int[] arr) { 
for (int value : arr) { 
System.out.print(value + " "); } System.out.println();  } }