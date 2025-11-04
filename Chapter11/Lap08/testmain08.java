package Chapter11.Lap08;

import java.util.Scanner;

public class testmain08 {
    public static void main(String[] args) {
        try {

            System.out.println("helooo");
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập vào username");
            String username = sc.nextLine();
            System.out.println("Nhập vào password");
            String password = sc.nextLine();
            System.out.println("Nhập vào Số dư ban đầu: ");
            double balance = sc.nextDouble();
            sc.nextLine();
            System.out.println("Nhập vào số tiền cần rút");
            String amout = sc.nextLine();

            BankAccount bank = new BankAccount(username,password,balance);
            bank.withdraw(Double.parseDouble(amout));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("kết thúc chương trình");
        }

    }
}
