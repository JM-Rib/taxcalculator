package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {
	
	public static final String ENTER_INCOME = "Enter income ";
    public static final String CONTRACT_TYPE = "Contract Type: (E)mployment, (C)ivil: ";
    public static final String INCORRECT = "Incorrect";
    public static final String INCOME = "Income";
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
    public static final String UNKNOWN_TYPE_CONTTRACT = "Unknown type of contract!";
    public static final String ADVANCE_TAX_18_PCT = "Advance tax 18 % = ";
    public static final String TAX_FREE_INCOME = "Tax free income = ";
    public static final String REDUCED_TAX = "Reduced tax = ";
    public static final String ALREADY_PAID_TAX = "Already paid tax = ";

    
	static double income = 0;
	static char contractType = 0;
	static double socSecurity = 0; //fixed positional variable
	static double socHealthSecurity = 0;
	static double socSickSecurity = 0;
	static double socHealth1 = 0; // social health tax (9%)
	static double socHealth2 = 0; // social health tax (7.5%)
	static double taxDeductibleExpenses = 111.25;  
	static double advanceTax = 0;
	static double taxFreeIncome = 46.33;
	static double advanceTaxPaid = 0;
	static double advanceTaxPaidRounded = 0;
	static double taxPaid = 0;
	static double socSickSecur = 0;
	
	static double taxedIncome = 0;
	static double taxedIncomeRounded = 0;

	static double netIncome = 0;
	
	static double SOCIALHEALTHTAX = 9;
	static double REDUCEDSOCIALHEALTHTAX = 7.75;
	
	static DecimalFormat df00 = new DecimalFormat("#.00");
	static DecimalFormat df = new DecimalFormat("#");
	
public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.print(ENTER_INCOME);
			income = Double.parseDouble(br.readLine());

			System.out.print(CONTRACT_TYPE);
			contractType = br.readLine().charAt(0);

		} catch (Exception ex) {
			System.out.println(INCORRECT);
			System.err.println(ex);
			return;
		}


		calculations(contractType);
        displayAllInfo(contractType);
		
		if (contractType != 'E' && contractType != 'C') {
			System.out.println("Unknown type of contract!");
		}
		
		
	}

	/*
 	* Displays all of the information for the program.
 	*/
	public static void displayAllInfo(char contractType) {
		System.out.println(contractType);

		if(contractType == 'E') {
			System.out.println("EMPLOYMENT");
		} else if (contractType == 'C') {
			System.out.println("CIVIL");
		}
	
    	System.out.println(INCOME + income);
    	System.out.println(SOCIAL_SECURITY_TAX
    			+ df00.format(socSecurity));

        System.out.println(HEALTH_SOCIAL_SECURITY_TAX
                + df00.format(socHealthSecurity));
        System.out.println(SICKNESS_SOCIAL_SECURITY_TAX
                + df00.format(socSickSecurity));
        System.out
                .println(INCOME_BASIS
                        + income); 
        
        System.out.println(HEALTH_SOCIAL_SECURITY_TAX_9
                + df00.format(socHealth1) + " 7,75% = " + df00.format(socHealth2));
        System.out.println(TAX_DEDUCTABLE_EXPENSES + taxDeductibleExpenses);
		
        if (contractType == 'E') {
        	System.out.println(INCOME_LC + taxedIncome + ROUNDED + df.format(taxedIncomeRounded));
        } else if (contractType == 'C') {
			System.out.println(INCOME_TO_BE_TAXED + taxedIncome
					+ ROUNDED + df.format(taxedIncomeRounded));
        }
        
        System.out.println(ADVANCE_TAX_18_PCT
                + advanceTax);
        
        if (contractType == 'E') {
            System.out.println(TAX_FREE_INCOME + taxFreeIncome);
            System.out.println(REDUCED_TAX
                    + df00.format(taxPaid));
        } else if (contractType == 'C') {
			System.out.println(ALREADY_PAID_TAX + df00.format(taxPaid)); 
        }
        
        System.out.println(ADVANCED_TAX_PAID
                + df00.format(advanceTaxPaid) + ROUNDED
                + df.format(advanceTaxPaidRounded));
        
		System.out.println();
		System.out
				.println(NET_INCOME
						+ df00.format(netIncome)); 
	}
	
	/*
	 * Runs all main calculations for the program.
	 */
	public static void calculations(char contractType) {
        calculateIncome(income); 
        calculateSocHealthTaxes(income);
		        
		if (contractType == 'C') {
			taxFreeIncome = 0;
			taxDeductibleExpenses  = (income * 20) / 100;
		}
		
        taxedIncome = income - taxDeductibleExpenses;
        taxedIncomeRounded = Double.parseDouble(df.format(taxedIncome));
		
		calculateTax(income);
		
		if (contractType == 'E') {
			taxPaid = advanceTax - taxFreeIncome; 
		}
		
		advanceTaxPaid = socHealth2 - taxFreeIncome;
        advanceTaxPaidRounded = Double.parseDouble(df.format(advanceTaxPaid));
        
        calculateAdvanceTaxPaid(); 

        netIncome = income - ((socSecurity + socHealthSecurity + socSickSecurity) + socHealth1 + advanceTaxPaidRounded);   
	}

	public static void calculateTaxedIncome(double income) {
        taxedIncome = income - taxDeductibleExpenses;
        taxedIncomeRounded = Double.parseDouble(df.format(taxedIncome));
	}
	
	public static void calculateTax(double income) {
		advanceTax = percentageOfIncome(income, 18);
	}
	
	public static void calculateAdvanceTaxPaid() {
		advanceTaxPaid = socHealth2 - taxFreeIncome;
        advanceTaxPaidRounded = Double.parseDouble(df.format(advanceTaxPaid));
	}

	public static void calculateIncome(double income) {
		socSecurity = percentageOfIncome(income, 9.76);
		socHealthSecurity = percentageOfIncome(income, 1.5);
		socSickSecur = percentageOfIncome(income, 2.45);
	}

	public static void calculateSocHealthTaxes(double income) {
		socHealth1 = percentageOfIncome(income, SOCIALHEALTHTAX);
		socHealth2 = percentageOfIncome(income, REDUCEDSOCIALHEALTHTAX);
	}
	

	/*
	 * Calculates a percentage for a specific income.
	 */
	public static double percentageOfIncome(double income, double percentage) {
		double percentageOfIncome = 0;
		try {
			if((percentage < 0) || (percentage > 100)) {
				throw new IndexOutOfBoundsException("Percentage has wrong value");
			}
			
			percentageOfIncome = (income * percentage) / 100;
		} catch (Exception e) { }
		return percentageOfIncome;
	}
}


