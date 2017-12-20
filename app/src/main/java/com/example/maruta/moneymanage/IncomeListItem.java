package com.example.maruta.moneymanage;

public class IncomeListItem {

    private String budgetName;
    private String budget;
    private String day;

    public IncomeListItem(String bn, String b, String d){

        this.budgetName = bn;
        this.budget = b;
        this.day = d;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
