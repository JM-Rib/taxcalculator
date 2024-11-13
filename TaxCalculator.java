package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

    public static final String ENTER_INCOME = "Enter income ";
    public static final String CONTRACT_TYPE = "Contract Type: (E)mployment, (C)ivil: ";
    public static final String INCORRECT = "Incorrect";
    public static final String INCOME = "Income ";
    public static final String INCOME_LC = "income ";
    public static final String SOCIAL_SECURITY_TAX = "Social security tax =";
    public static final String HEALTH_SOCIAL_SECURITY_TAX = "Health social security tax    ";
    public static final String HEALTH_SOCIAL_SECURITY_TAX_9 = "Health social security tax: 9% = ";
    public static final String SICKNESS_SOCIAL_SECURITY_TAX = "Sickness social security tax  ";
    public static final String INCOME_BASIS = "Income basis for health social security: ";
    public static final String TAX_DEDUCTABLE_EXPENSES = "Tax deductible expenses ";
    public static final String ROUNDED = " rounded = ";
    public static final String NET_INCOME = "Net income = ";
    public static final String INCOME_TO_BE_TAXED = "income to be taxed = ";
    public static final String ADVANCED_TAX_PAID = "Advance tax paid = ";
    public static final String UNKNOWN_TYPE_CONTRACT = "Unknown type of contract!";
    public static final String ADVANCE_TAX_18_PCT = "Advance tax 18 % = ";
    public static final String TAX_FREE_INCOME = "Tax free income = ";
    public static final String REDUCED_TAX = "Reduced tax = ";
    public static final String ALREADY_PAID_TAX = "Already paid tax = ";
    public static double SOCIALHEALTHTAX = 9;
    public static double REDUCEDSOCIALHEALTHTAX = 7.75;


    static int income = 0;
    static int socSecurity = 0; //fixed positional variable
    static int socHealthSecurity = 0;
    static int socSickSecurity = 0;
    static int socHealth9pct = 0; // social health tax (9%)
    static int socHealth775pct = 0; // social health tax (7.5%)
    static int taxDeductibleExpenses = 11125; // 111.25
    static int advanceTax = 0;
    static int taxFreeIncome = 4633; // 463.33
    static int advanceTaxPaid = 0;
    static int advanceTaxPaidRounded = 0;
    static int taxPaid = 0;
    static int socSickSecur = 0;
    static int taxedIncome = 0;
    static int taxedIncomeRounded = 0;
    static int netIncome = 0;
    
    static char contractTypeInput;
    private static Contract userContract;
    
    static DecimalFormat df00 = new DecimalFormat("#.00");
    static DecimalFormat df = new DecimalFormat("#");
    

    
    public static String formatCentsToDollars(int amount) {
        int dollars = amount / 100;
        int cents = Math.abs(amount % 100);
        
        return dollars + "." + String.format("%02d", cents);
    }

    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.print(ENTER_INCOME);
            income = Integer.parseInt(br.readLine());

            System.out.print(CONTRACT_TYPE);
            contractTypeInput = br.readLine().charAt(0);

            userContract = createContract(contractTypeInput);

        } catch (Exception ex) {
            System.out.println(INCORRECT);
            System.err.println(ex);
            return;
        }

        calculations(userContract);
        displayContractInfo(userContract);

        if (contractTypeInput != 'E' && contractTypeInput != 'C') {
            System.out.println("Unknown type of contract!");
        }
    }

    public static Contract createContract(char contractTypeChar) {
        if (contractTypeChar == 'E') {
            return new EmploymentContractClass(contractTypeChar);
        } else if (contractTypeChar == 'C') {
            return new CivilContractClass(contractTypeChar);
        } else {
            System.out.println("Unknown type of contract!");
            return null;
        }
    }

    /**
     * Displays income info
     */
    public static void displayIncomeInfo(){

        System.out.println(INCOME + income);
        System.out.println(SOCIAL_SECURITY_TAX + formatCentsToDollars(socSecurity));
        System.out.println(HEALTH_SOCIAL_SECURITY_TAX + formatCentsToDollars(socHealthSecurity));
        System.out.println(SICKNESS_SOCIAL_SECURITY_TAX + formatCentsToDollars(socSickSecurity));
        System.out.println(INCOME_BASIS + income);
        System.out.println(HEALTH_SOCIAL_SECURITY_TAX_9
                + formatCentsToDollars(socHealth9pct) + " 7,75% = " + formatCentsToDollars(socHealth775pct));
        System.out.println(TAX_DEDUCTABLE_EXPENSES + formatCentsToDollars(taxDeductibleExpenses));

    }

    /**
     * Displays tax info
     */
    public static void displayTaxInfo(){
        System.out.println(ADVANCED_TAX_PAID
                + formatCentsToDollars(advanceTaxPaid) + ROUNDED + formatCentsToDollars(advanceTaxPaidRounded));
        System.out.println();
        System.out.println(NET_INCOME + formatCentsToDollars(netIncome));
    }

    /**
     * Checks the type of contract and displays information accordingly
     */
    private static String displayTypeOfContract(Contract contract){
        return contract.getFullName();
    }

    /**
     * Displays all the information for the program.
     */
    public static void displayContractInfo(Contract contract) { 
        System.out.println(contract.contractType);

        displayTypeOfContract(contract);

        displayIncomeInfo();

        contract.printIncomeToBeTaxed();

        System.out.println(ADVANCE_TAX_18_PCT + formatCentsToDollars(advanceTax));

        contract.printReducedAndAlreadyPaidTax();

        displayTaxInfo();
    }

    /**
     * Runs all main calculations for the program.
     * @param contractType - Type of contract depending on the person
     */
    public static void calculations(Contract contractType) {
        calculateIncome(income);
        calculateSocHealthTaxes(income);

        taxPaid = contractType.calculateTaxPaid();

        calculateTaxedIncome();
        taxedIncomeRounded = Integer.parseInt(df.format(taxedIncome));

		calculateTax(taxedIncomeRounded);

        advanceTax = calculateAdvanceTax();
        advanceTaxPaidRounded = Integer.parseInt(df.format(advanceTaxPaid));
        calculateAdvanceTaxPaid();
        netIncome = calculateNetIncome();
    }

    /**
     * Calculates the taxed income of a person
     * @return calculated taxed income
     */
    private static int calculateTaxedIncome(){
        return income - taxDeductibleExpenses;
    }

    /**
     * Calculates the total net income of a person
     * @return net income of a person
     */
    private static int calculateNetIncome(){
        return income - ((socSecurity + socHealthSecurity + socSickSecurity) + socHealth9pct + advanceTaxPaidRounded);
    }

    /**
     * Calculates the advance tax rate.
     * @return advanced tax
     */
    private static int calculateAdvanceTax(){
        return socHealth775pct - taxFreeIncome;
    }
    public static void calculateTaxedIncome(int income) {
        taxedIncome = income - taxDeductibleExpenses;
        taxedIncomeRounded = Integer.parseInt(df.format(taxedIncome));
    }

    /**
     * Calculates tax
     * @param income - Person's income
     */
    public static void calculateTax(int income) {
        advanceTax = percentageOfIncome(income, 18);
    }

    /**
     * Calculates the advance tax of a person.
     */
    public static void calculateAdvanceTaxPaid() {
        advanceTaxPaid = socHealth775pct - taxFreeIncome;
        advanceTaxPaidRounded = Integer.parseInt(df.format(advanceTaxPaid));
    }

    /**
     * Calculates the income of a person
     * @param income - Person's income
     */
    public static void calculateIncome(int income) {
        socSecurity = percentageOfIncome(income, 9.76);
        socHealthSecurity = percentageOfIncome(income, 1.5);
        socSickSecur = percentageOfIncome(income, 2.45);
    }

    /**
     * Calculates social health taxes depending on the income.
     * @param income - Person's income
     */
    public static void calculateSocHealthTaxes(int income) {
        socHealth9pct = percentageOfIncome(income, SOCIALHEALTHTAX);
        socHealth775pct = percentageOfIncome(income, REDUCEDSOCIALHEALTHTAX);
    }


    /**
     * Calculates a percentage for a specific income.
     * @param income - Person's income
     * @param percentage - Person's income percentage
     */
    public static int percentageOfIncome(int income, double percentage) {
        int percentageOfIncome = 0;
        try {
            if((percentage < 0) || (percentage > 100)) {
                throw new IndexOutOfBoundsException("Percentage has wrong value");
            }

            percentageOfIncome = (income * ((int)(percentage*100)) ) / 100;
        } catch (Exception e) { }
        return percentageOfIncome;
    }
}