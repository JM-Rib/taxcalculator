package com.bartoszwalter.students.taxes;

public abstract class Contract {
    public char contractType; //TODO introduce inheritance on contract class
    public Contract(char contractType){
        this.contractType = contractType;
    }
    public abstract String getFullName();
    public char getContractType(){
        return contractType;
    }
    public abstract int calculateTaxPaid();

    public abstract void printIncomeToBeTaxed(); //TODO change name. I'm not sure about this one

    public abstract void printReducedAndAlreadyPaidTax();

}
