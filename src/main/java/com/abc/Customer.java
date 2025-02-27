package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.Enums.TransactionType;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account account) {
        String s = "";

       //Translate to pretty account type
        switch(account.getAccountType()){
            case CHECKING:
                s += "Checking Account\n";
                break;
            case SAVINGS:
                s += "Savings Account\n";
                break;
            case MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : account.transactions) {
            s += "  " + (t.getTransactionType() == TransactionType.WITHDRAW  ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }
    public void transferBetween(Account a, Account b, double amount ) {
        a.withdraw(amount, TransactionType.WITHDRAW);
        b.deposit(amount,TransactionType.DEPOSIT);
    }
    //transfer function (account a, account b, double amount) {}
    //withdraws from account a and deposits to account b



    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
