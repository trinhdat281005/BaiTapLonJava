package Chapter11.Lap08;

public class BankAccount {
    private  String username;
    private String password;
    private double balance;

    public BankAccount(String username, String password, double balance) {
        this.username = username;
        if(password.length() < 6){
            throw new WeakPasswordException("Dùng khi mật khẩu có độ dài < 6 ký tự.");
        }
        this.password = password;
        this.balance = balance;
    }
public void withdraw(double amount) throws NegativeBalanceException{
        if( amount > this.balance){
            throw new NegativeBalanceException("Không đủ số dư để rút tiền");
        }
        double NewBalance = this.balance - amount;
        this.setBalance(NewBalance);
    System.out.println("Rứt tiền thành công . Số dư còn lại là "+ NewBalance);

}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
