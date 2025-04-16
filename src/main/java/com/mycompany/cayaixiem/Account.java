/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cayaixiem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyPC
 */
public class Account {
    private String accountNumber; // Số tài khoản
    private String pin;          // Mã PIN
    private String name;         // Tên tài khoản
    private double balance;      // Số dư tài khoản
    private List<String> transactionHistory; // Lưu trữ lịch sử giao dịch
    private static List<String> customersDeposited; // Lưu trữ danh sách khách hàng đã gửi tiền
    private static List<String> customersWithdrawn; // Lưu trữ danh sách khách hàng đã rút tiền
    private static List<String> customersTransferred; // Lưu trữ danh sách khách hàng đã chuyển khoản

    
    // Constructor
    public Account(String accountNumber, String pin, String name, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>(); // Khởi tạo danh sách lịch sử giao dịch
        customersDeposited = new ArrayList<>(); // Khởi tạo danh sách khách hàng đã gửi tiền
        customersWithdrawn = new ArrayList<>(); // Khởi tạo danh sách khách hàng đã rút tiền
        customersTransferred = new ArrayList<>(); // Khởi tạo danh sách khách hàng đã chuyển khoản
   
    }
    
    // Getter và Setter cho accountNumber
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Getter và Setter cho pin
    public String getPin() {
        return pin;
    }

//    public void setPin(String pin) {
//        this.pin = pin;
//    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho balance
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    // Phương thức kiểm tra mã PIN
    public boolean login(String enteredPin) {
        return this.pin.equals(enteredPin);
    }
    
    // Phương thức để thay đổi mã PIN
    public void setPin(String newPin) {
        this.pin = newPin;  // Thay đổi mã PIN
    }
    
    // Phương thức gửi tiền
    public void deposit(double amount) {
        
        
        balance += amount;  // Cộng tiền vào số dư tài khoản
        addTransaction("Gửi tiền: " + amount + " VND");
        // Lưu khách hàng vào danh sách đã gửi tiền kèm thời gian
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String depositInfo = name + " đã gửi tiền " + amount + " VND vào lúc " + timestamp;
        customersDeposited.add(depositInfo);
    }
    
    // Rút tiền
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addTransaction("Rút tiền: " + amount + " VND");
            // Lưu khách hàng vào danh sách đã rút tiền kèm thời gian
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String withdrawInfo = name + " đã rút tiền " + amount + " VND vào lúc " + timestamp;
            customersWithdrawn.add(withdrawInfo);
            return true;
        } else {
            return false;  // Không đủ tiền để rút
        }
    }
    
    // Chuyển khoản
    public boolean transfer(Account recipient, double amount) {
        if (this.withdraw(amount)) {
            recipient.deposit(amount);
            addTransaction("Chuyển khoản: " + amount + " VND đến tài khoản " + recipient.getAccountNumber());
            // Lưu khách hàng vào danh sách đã chuyển khoản kèm thời gian
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String transferInfo = name + " đã chuyển khoản " + amount + " VND đến tài khoản " + recipient.getAccountNumber() + " vào lúc " + timestamp;
            customersTransferred.add(transferInfo);
            return true;
        }
        return false;
    }
    
     // Thêm giao dịch vào lịch sử
    private void addTransaction(String transaction) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        transactionHistory.add(transaction + " vào lúc " + timestamp);
    }
    
     // Lấy lịch sử giao dịch
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
    
    // Lấy danh sách khách hàng đã gửi tiền cùng thời gian
    public static List<String> getCustomersDeposited() {
        return customersDeposited;
    }
    
     // Lấy danh sách khách hàng đã rút tiền cùng thời gian
    public static List<String> getCustomersWithdrawn() {
        return customersWithdrawn;
    }

    // Lấy danh sách khách hàng đã chuyển khoản cùng thời gian
    public static List<String> getCustomersTransferred() {
        return customersTransferred;
    }
    
}

