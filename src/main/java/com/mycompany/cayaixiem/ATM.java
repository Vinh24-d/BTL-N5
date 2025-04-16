/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cayaixiem;

//import java.awt.List;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MyPC
 */
public class ATM {
    
    private Map<String, Account> accounts;  // Danh sách các tài khoản
    private Account currentAccount;         // Tài khoản hiện tại
    private boolean isLoggedIn = false;
    
    
    // Constructor
    public ATM() {
        this.accounts = new HashMap<>();
        
        // Thêm tài khoản mẫu sẵn có
        accounts.put("1001", new Account("1001", "1234", "Nguyễn Văn A", 5000000));
        accounts.put("1002", new Account("1002", "5678", "Trần Thị B", 3000000));
        accounts.put("1003", new Account("1003", "9999", "Lê Văn C", 10000000));
        accounts.put("0", new Account("0", "0", "Nhân Viên ", 0));

    }
    
    // Getter và Setter cho accounts
    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }
    
    // Getter và Setter cho currentAccount
    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }
    
    // Getter và Setter cho isLoggedIn
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    
    // Đăng nhập
    public boolean  login(String accountNumber, String enteredPin) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.login(enteredPin)) {
            isLoggedIn = true;
            currentAccount = account;
//            return("Đăng nhập thành công! Chào mừng, " + currentAccount.getName());
            return true;
        } else {
//            return("Mã PIN hoặc số tài khoản không đúng.");
            return false;
        }
    }
    
    // Đổi mã PIN
    public String  changePin(String oldPin, String newPin) {
        if (isLoggedIn) {
            if (currentAccount.login(oldPin)) {
                // Kiểm tra xem mã PIN cũ và mã PIN mới có giống nhau không
                if (oldPin.equals(newPin)) {
                    return ("Mã PIN cũ và mã PIN mới giống nhau. Vui lòng nhập mã PIN khác.");
//                    return false;
                } else {
                    currentAccount.setPin(newPin);
                    return ("Mã PIN đã được thay đổi thành công.");
//                    return true;
                }
            } else {
                return ("Mã PIN cũ không đúng.");
//                return false;
            }
        
        } else {
            return ("Vui lòng đăng nhập trước.");
//            return false;
        }
    }
    
    // Gửi tiền
    public String deposit( double amount,String Pin) {
        if (isLoggedIn) {
            if (currentAccount.login(Pin)) {
            // Gửi tiền vào tài khoản nếu mã PIN đúng
            currentAccount.deposit(amount);
            return("Gửi tiền thành công. Số dư hiện tại: " + currentAccount.getBalance());
        } else {
            return("Mã PIN không đúng. Giao dịch bị hủy.");
        }
        } else {
            return("Vui lòng đăng nhập trước.");
        }
    }
    
     // Rút tiền
    public String withdraw(double amount,String Pin) {
        if (isLoggedIn) {
            if (currentAccount.login(Pin)) {
                if (currentAccount.withdraw(amount)) {
                    
                    return("Rút tiền thành công. Số dư còn lại: " + currentAccount.getBalance());
                } else {
                    return("Số dư không đủ.");
                }
            } else {
            return("Mã PIN không đúng. Giao dịch bị hủy.");
        }
        } else {
            return("Vui lòng đăng nhập trước.");
        }
    }
    
    // Chuyển khoản
    public String transfer(String recipientAccountNumber, double amount,String Pin) {
        if (isLoggedIn) {
            if (currentAccount.login(Pin)) {
                Account recipient = accounts.get(recipientAccountNumber);
                if (recipient != null) {
                    if (currentAccount.transfer(recipient, amount)) {
                        
                        return("Chuyển khoản thành công. Số dư hiện tại: " + currentAccount.getBalance());
                        
                    } else {
                        return("Số dư không đủ.");
                    }
                } else {
                    return("Tài khoản người nhận không tồn tại.");
                }
            } else {
            return("Mã PIN không đúng. Giao dịch bị hủy.");
        }
        } else {
            return("Vui lòng đăng nhập trước.");
        }
    }
    
    
    // Xem lịch sử giao dịch
    public String checkTransactionHistory() {
        if (isLoggedIn) {
            
                List<String> history = currentAccount.getTransactionHistory();
                if (history.isEmpty()) {
                    return "Lịch sử giao dịch trống.";
                } else {
                    StringBuilder historyString = new StringBuilder("Lịch sử giao dịch:\n");
                    for (String transaction : history) {
                        historyString.append(transaction).append("\n");
                    }
                    return historyString.toString();
                }
            
            
        } else {
            return "Vui lòng đăng nhập trước.";
        }
    }
    
        // Hiển thị thông tin người đăng nhập
    public String viewAccountInfo() {
        if (isLoggedIn) {
            
                return "Thông tin tài khoản:\n" +
                       "Tên chủ tài khoản: " + currentAccount.getName() + "\n" +
                       "Số tài khoản: " + currentAccount.getAccountNumber() + "\n" +
                       "Số dư hiện tại: " + currentAccount.getBalance() + " VND";
            
        } else {
            return "Vui lòng đăng nhập trước.";
        }
    }
    
     // Đăng xuất
    public String logout() {
        isLoggedIn = false;
        currentAccount = null;
        return("Đã đăng xuất.");
    }
    
    // Hiển thị thông tin tất cả các khách hàng
    public String viewAllCustomerInfo() {
        if (accounts.isEmpty()) {
            return "Không có tài khoản nào trong hệ thống.";
        } else {
            StringBuilder customerInfo = new StringBuilder("Danh sách tất cả các khách hàng:\n");
            for (Account account : accounts.values()) {
                if (account.getAccountNumber().equals("0")) {
            continue; // Bỏ qua tài khoản nhân viên
        }
                customerInfo.append("Tên chủ tài khoản: ").append(account.getName())
                            .append(", Số tài khoản: ").append(account.getAccountNumber())
                            .append(", Số dư: ").append(account.getBalance()).append(" VND\n");
            }
            return customerInfo.toString();
        }
    }
    
    // Tìm kiếm thông tin khách hàng theo số tài khoản
    public String searchCustomerByAccountNumber(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            
        
            return "Thông tin tài khoản:\n" +
                   "Tên chủ tài khoản: " + account.getName() + "\n" +
                   "Số tài khoản: " + account.getAccountNumber() + "\n" +
                   "Số dư: " + account.getBalance() + " VND";
        } else {
            return "Tài khoản không tồn tại.";
        }
    }
    
     // Hiển thị danh sách các khách hàng đã gửi tiền kèm theo thời gian
    public String displayCustomersDeposited() {
        List<String> customers = Account.getCustomersDeposited();
        if (customers.isEmpty()) {
            return "Không có khách hàng nào đã gửi tiền.";
        } else {
            StringBuilder customerList = new StringBuilder("Danh sách khách hàng đã gửi tiền:\n");
            for (String customer : customers) {
                customerList.append(customer).append("\n");
            }
            return customerList.toString();
        }
    }
    
    // Hiển thị danh sách khách hàng đã rút tiền kèm theo thời gian
    public String displayCustomersWithdrawn() {
        List<String> customers = Account.getCustomersWithdrawn();
        if (customers.isEmpty()) {
            return "Không có khách hàng nào đã rút tiền.";
        } else {
            StringBuilder customerList = new StringBuilder("Danh sách khách hàng đã rút tiền:\n");
            for (String customer : customers) {
                customerList.append(customer).append("\n");
            }
            return customerList.toString();
        }
    }

    // Hiển thị danh sách khách hàng đã chuyển khoản kèm theo thời gian
    public String displayCustomersTransferred() {
        List<String> customers = Account.getCustomersTransferred();
        if (customers.isEmpty()) {
            return "Không có khách hàng nào đã chuyển khoản.";
        } else {
            StringBuilder customerList = new StringBuilder("Danh sách khách hàng đã chuyển khoản:\n");
            for (String customer : customers) {
                customerList.append(customer).append("\n");
            }
            return customerList.toString();
        }
    }
    
    // Phương thức tạo tài khoản khách hàng mới
    public String createAccount(String accountNumber, String pin, String name, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            return "Số tài khoản này đã tồn tại. Vui lòng chọn số tài khoản khác.";
        }

        // Tạo tài khoản mới và thêm vào hệ thống
        Account newAccount = new Account(accountNumber, pin, name, initialBalance);
        accounts.put(accountNumber, newAccount);
        return "Tạo tài khoản thành công! Tài khoản của mới là: " + accountNumber;
    }

}
