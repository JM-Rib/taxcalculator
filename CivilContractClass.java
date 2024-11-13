package com.bartoszwalter.students.taxes;

import java.text.DecimalFormat;

public class CivilContractClass extends Contract{
    static int income;
    static int taxDeductibleExpenses;
    static int taxFreeIncome;
    static int taxedIncome = 0;
    static int taxedIncomeRounded = 0;
    static int taxPaid = 0;
    static DecimalFormat df = new DecimalFormat("#");
    static DecimalFormat df00 = new DecimalFormat("#.00");
    public static final String ROUNDED = " rounded = ";
    public static final String INCOME_TO_BE_TAXED = "income to be taxed = ";
    public static final String ALREADY_PAID_TAX = "Already paid tax = ";
    public CivilContractClass(char contractType) {
        super(contractType);
    }
    
    public static String formatCentsToDollars(int amount) {
        int dollars = amount / 100;
        int cents = Math.abs(amount % 100);
        
        return dollars + "." + String.format("%02d", cents);
    }

    @Override
    public String getFullName() {
        return "CIVIL";
    }

    public int calculateTaxPaid(){
        taxFreeIncome = 0;
        return taxDeductibleExpenses  = (income * 20) / 100;
    }

    @Override
    public void printIncomeToBeTaxed() {
        System.out.println(INCOME_TO_BE_TAXED + taxedIncome
                + ROUNDED + df.format(taxedIncomeRounded));
    }

    @Override
    public void printReducedAndAlreadyPaidTax() {
        System.out.println(ALREADY_PAID_TAX + formatCentsToDollars(taxPaid));
    }
}
