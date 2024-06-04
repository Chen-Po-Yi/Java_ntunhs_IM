package hw13;

import java.util.HashMap;
import java.util.Map;

// 定義 Student 類別
public class Student {
    private String name; // 學生姓名
    private double height; // 身高
    private double weight; // 體重


    public Student(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    // 獲取學生姓名
    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    // 設定身高
    public void setHeight(double height) {
        this.height = height;
    }

    // 獲取體重
    public double getWeight() {
        return weight;
    }

    // 設定體重
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // 計算並獲取BMI
    public double getBMI() {
        double heightinmeter = height/100.0;
        double bmi = weight / (heightinmeter * heightinmeter);
        return Math.round(bmi * 100.0) / 100.0; // 四捨五入到小數點後兩位
    }

    // 覆蓋 toString 方法，返回學生姓名、身高、體重和BMI的字符串表示
    @Override
    public String toString() {
        return String.format("Name: %s, Height: %.2f cm, Weight: %.2f kg, BMI: %.2f", name, height, weight, getBMI());
    }
}