package com.bartoszwalter.students.taxes;

import java.text.DecimalFormat;

public class EmploymentContractClass extends Contract{
    private static int taxPaid = 0;
    private static int advanceTax = 0;
    private static int taxFreeIncome = 4633;
    static int taxedIncome = 0;
    static int taxedIncomeRounded = 0;
    public static final String INCOME_LC = "income ";
    public static final String ROUNDED = " rounded = ";
    public static final String TAX_FREE_INCOME = "Tax free income = ";
    public static final String REDUCED_TAX = "Reduced tax = ";
    static DecimalFormat df = new DecimalFormat("#");
    static DecimalFormat df00 = new DecimalFormat("#.00");

    public static String formatCentsToDollars(int amount) {
        int dollars = amount / 100;
        int cents = Math.abs(amount % 100);
        
        return dollars + "." + String.format("%02d", cents);
    }
    
    public EmploymentContractClass(char contractType) {
        super(contractType);
    }
    @Override
    public String getFullName() {
        return "EMPLOYMENT";
    }

    public int calculateTaxPaid() {
        return taxPaid = advanceTax - taxFreeIncome;
    }

    @Override
    public void printIncomeToBeTaxed() {
        System.out.println(INCOME_LC + taxedIncome + ROUNDED + df.format(taxedIncomeRounded));
    }

    @Override
    public void printReducedAndAlreadyPaidTax() {
        System.out.println(TAX_FREE_INCOME + formatCentsToDollars(taxFreeIncome));
        System.out.println(REDUCED_TAX
                + formatCentsToDollars(taxPaid));
    }

}
