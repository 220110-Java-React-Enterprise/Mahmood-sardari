package Persistence;

public class AccountClientsModel {

    private Integer userId;
    private Integer accountId;
    private Double balance;
    private String account_name;

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public AccountClientsModel() {
    }

    public AccountClientsModel(Integer userId, Double balance, String account_name) {
        this.userId = userId;
        this.balance = balance;
        this.account_name = account_name;
    }

    public AccountClientsModel(Integer userId, Integer accountId, Double balance, String account_name) {
        this.userId = userId;
        this.accountId = accountId;
        this.balance = balance;
        this.account_name = account_name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}




