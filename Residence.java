/*
  CMPT 270 Course material
  Copyright (c) 2020
  All rights reserved.

  This document contains resources for homework assigned to students of
  CMPT 270 and shall not be distributed without permission.  Posting this
  file to a public or private website, or providing this file to a person
  not registered in CMPT 270, constitutes Academic Misconduct, according
  to the University of Saskatchewan Policy on Academic Misconduct.

  Synopsis:
     Solution file for Assignment 3 Question 3
 */
 

/**
 * A residence of a hospital with a specified number of beds with consecutive labels.
 */
public class Residence
{
    /**
     * The name of this residence.
     */
    private String name;

    /**
     * The (external) label of the first bed of the residence.
     */
    private int minBedLabel;

    /**
     * The (external) label of the last bed of the residence.
     */
    private int maxBedLabel;

    /**
     * An array to represent the beds of the residence.  Each bed is empty (null)
     * or else has a Person in it.
     */
    private Person[] beds;

    /**
     * Initialize the residence with the name given, and with beds those labels are
     * the consecutive integers from minBedLabel to maxBedLabel.
     * @param wName    the name of the residence
     * @param wMinBedLabel the label of the first bed in the residence
     * @param wMaxBedLabel the label of the last bed in the residence
     * @precond wName != null && !wName.equals("") && wMinBedLabel >= 0 && wMaxBedLabel >= wMinBedLabel
     */
    public Residence(String wName, int wMinBedLabel, int wMaxBedLabel)
    {
        if (wName == null || wName.equals(""))
            throw new IllegalArgumentException("The name of a residence cannot be null or empty.  "
                    + "It is " + wName);
        if (wMinBedLabel < 0 || wMaxBedLabel < wMinBedLabel)
            throw new IllegalArgumentException("The bed labels " + wMinBedLabel + " and " + wMaxBedLabel
                    + " are invalid as they cannot be negative, and must have at least one bed.");

        name = wName;
        minBedLabel = wMinBedLabel;
        maxBedLabel = wMaxBedLabel;
        beds = new Person[wMaxBedLabel - wMinBedLabel + 1];
    }

    /**
     * Return the name of this residence.
     * @return  the name of this residence
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return the smallest label for a bed on the residence.
     * @return  the smallest Label for a bed on the residence
     */
    public int getMinBedLabel()
    {
        return minBedLabel;
    }

    /**
     * Return the largest label for a bed on the residence.
     * @return  the largest label for a bed on the residence
     */
    public int getMaxBedLabel()
    {
        return maxBedLabel;
    }

    /**
     * Return the internal/array index of the bed corresponding to the external label.
     * @param bedLabel the label of a bed from the external/user perspective
     * @precond isValidLabel(bedLabel)
     * @return the internal/array index of the bed corresponding to the external label
     */
    private int externalToInternalIndex(int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        return bedLabel - minBedLabel;
    }

    /**
     * Return the external/user label of the bed corresponding to the internal index.
     * @param arrayIndex the index of a location in the beds array
     * @precond 0 <= arrayIndex < beds.length
     * @return the external/user label of the bed corresponding to the internal index
     */
    private int internalToExternalLabel(int arrayIndex)
    {
        if (arrayIndex < 0 || arrayIndex >= beds.length)
            throw new IllegalArgumentException("The value " + arrayIndex +
                    " is not a valid index for an array of length " + beds.length + ".");

        return arrayIndex + minBedLabel;
    }

    /**
     * Is the specified bed occupied?
     * @param bedLabel  the label of the bed to be tested for being occupied
     * @precond isValidLabel(bedLabel)
     * @return  is the specified bed occupied?
     */
    public boolean isOccupied(int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        return beds[externalToInternalIndex(bedLabel)] != null;
    }

    /**
     * Return the patient in the specified bed.
     * @param bedLabel  the label of the bed that has the patient to be retrieved
     * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
     * @return  the patient in the specified bed
     */
    public Person getPerson(int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        if (! isOccupied(bedLabel))
            throw new IllegalStateException("Bed " + bedLabel + " is not currently occupied"
                    + " so cannot get its patient");
        return beds[externalToInternalIndex(bedLabel)];
    }

    /**
     * Assign the specified patient to the specified bed.
     * @param p  the patient to be assigned a bed
     * @param bedLabel  the label of the bed that the patient is to be assigned
     * @precond  isValidLabel(bedLabel) && !isOccupied(bedLabel)
     */
    public void assignPersonToBed(Person p, int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        if (isOccupied(bedLabel))
            throw new IllegalStateException("Bed " + bedLabel + " is currently occupied by "
                    + beds[externalToInternalIndex(bedLabel)]
                    + " so cannot be assigned to another patient");

        beds[externalToInternalIndex(bedLabel)] = p;
    }

    /**
     * Return a String representation of the properties of the residence.
     * @return a String representation of the properties of the residence
     */
    public String toString()
    {
        String result = "\nResidence " + name + " with capacity " + beds.length
                + " has the following patients: ";
        for (int i = 0; i < beds.length; i++)
        {
            result = result + "\nbed " + internalToExternalLabel(i) + ": ";
            if (beds[i] != null)
                result = result + beds[i].getName();
        }
        return result + "\n";
    }


    /**
     * Is bedLabel a valid external label for a bed?
     * @param bedLabel  an int to be tested to determined whether it is a valid label
     *             for a bed (from the external/user perspective)
     * @return  is bedLabel a valid external label for a bed?
     *
     * This is a helper method for testing pre-conditions students were not required to implement it
     */
    public boolean isValidLabel(int bedLabel)
    {
        return bedLabel >= minBedLabel && bedLabel <= maxBedLabel;
    }


    /**
     * A method to test the class.
     * @param args  not used
     */
    public static void main(String[] args)
    {

		// variables for the expected response from methods
		String expected_string;
		int expected_int;
		boolean expected_bool;
		
		// variabels for the actual response from methods
		String result_string;
		int result_int;
		boolean result_bool;
		
		// describe something about the test case
		String reason;
		
		//  TEST CASES BEGIN 
	    // testing all the methods with one instance of a Residence
        String rname = "Maple Hall";
        int minLabel = 200;
        int maxLabel = 210;
        Residence res = new Residence(rname, minLabel, maxLabel);

		// test case 1
		reason = "The constructor + getName()";
		expected_string = "Maple Hall";
		result_string = res.getName();
		
        if(! result_string.equals(expected_string)) {
            System.out.println("Expected: " + expected_string + "\nObtained: "+ result_string 
                               + "\n(" + reason + ")");        
        }


		// test case 2
		reason = "The constructor + getMinBedLabel()";
		expected_int = minLabel;
		result_int = res.getMinBedLabel();
		
        if( result_int != expected_int) {
            System.out.println("Expected: " + expected_int + "\nObtained: "+ result_int 
                               + "\n(" + reason + ")");        
        }



		// test case 3
		reason = "The constructor + getMaxBedLabel()";
		expected_int = maxLabel;
		result_int = res.getMaxBedLabel();
		
        if( result_int != expected_int) {
            System.out.println("Expected: " + expected_int + "\nObtained: "+ result_int 
                               + "\n(" + reason + ")");        
        }


		// test case 4
		reason = "The constructor + isValidLabel()";
		int inputLabel = minLabel;
		result_bool = res.isValidLabel(inputLabel);
		expected_bool = true;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 5
		reason = "The constructor + isValidLabel()";
		inputLabel = maxLabel;
		result_bool = res.isValidLabel(inputLabel);
		expected_bool = true;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 6
		reason = "The constructor + isValidLabel()";
		inputLabel = minLabel+1;
		result_bool = res.isValidLabel(inputLabel);
		expected_bool = true;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 7
		reason = "The constructor + isValidLabel()";
		inputLabel = maxLabel-1;
		result_bool = res.isValidLabel(inputLabel);
		expected_bool = true;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 8
		reason = "The constructor + isValidLabel()";
		inputLabel = minLabel-1;
		result_bool = res.isValidLabel(inputLabel);
		expected_bool = false;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 9
		reason = "The constructor + isValidLabel()";
		inputLabel = maxLabel+1;
		result_bool = res.isValidLabel(inputLabel);
		expected_bool = false;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 10
		reason = "The constructor + isValidLabel()";
		inputLabel = (maxLabel+minLabel) / 2;
		result_bool = res.isValidLabel(inputLabel);
		expected_bool = true;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 11
		reason = "The constructor + internalToExternalLabel()";
		inputLabel = 0;
		result_int = res.internalToExternalLabel(inputLabel);
		expected_int = minLabel;
		
        if(result_int != expected_int) {
            System.out.println("Expected: " + expected_int + "\nObtained: "+ result_int 
                               + "\n(" + reason + ")");        
        }


		// test case 12
		reason = "The constructor + internalToExternalLabel()";
		inputLabel = maxLabel-minLabel+1;
		try {
			expected_bool = true;	
			result_bool = false;
			result_int = res.internalToExternalLabel(inputLabel);
		}
		catch (IllegalArgumentException e) {
			result_bool = true;
		}
        if(result_bool != expected_bool) {
            System.out.println("Translating label: " + inputLabel + "\nDidn't throw exception." 
                               + "\n(" + reason + ")");        
        }


		// test case 13
		reason = "The constructor + internalToExternalLabel()";
		inputLabel = (maxLabel+minLabel)/2;
		try {
			// this test should throw an exception
			expected_bool = true;	
			result_bool = false;
			result_int = res.internalToExternalLabel(inputLabel);
		}
		catch (IllegalArgumentException e) {
			// if the right exception is thrown, look for the result
			result_bool = true;
		}
        if(result_bool != expected_bool) {
            System.out.println("Translating label: " + inputLabel + "\nDidn't throw exception." 
                               + "\n(" + reason + ")");        
        }
        

		// test case 14
		reason = "The constructor + externalToInternalIndex()";
		inputLabel = minLabel;
		result_int = res.externalToInternalIndex(inputLabel);
		expected_int = 0;
		
        if(result_int != expected_int) {
            System.out.println("Expected: " + expected_int + "\nObtained: "+ result_int 
                               + "\n(" + reason + ")");        
        }


		// test case 15
		reason = "The constructor + externalToInternalIndex() 1";
		inputLabel = maxLabel;
		result_int = res.externalToInternalIndex(inputLabel);
		expected_int = res.beds.length-1;
		
        if(result_int != expected_int) {
            System.out.println("Expected: " + expected_int + "\nObtained: "+ result_int 
                               + "\n(" + reason + ")");        
        }


		// test case 16
		reason = "The constructor + externalToInternalIndex()";
		inputLabel = (maxLabel+minLabel)/2;
		result_int = res.externalToInternalIndex(inputLabel);
		expected_int = res.beds.length / 2;
		
        if(result_int != expected_int) {
            System.out.println("Expected: " + expected_int + "\nObtained: "+ result_int 
                               + "\n(" + reason + ")");        
        }
        

		// test case 17: test the assignment of beds functionality
		
		reason = "The constructor + isOccupied()";
		inputLabel = 205;
		result_bool = res.isOccupied(inputLabel);
		expected_bool = false;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking occupation for label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 18
        Person p = new Person("Ophelia Mazingtonfordshire", "123456");
        res.assignPersonToBed(p, inputLabel);
		result_bool = res.isOccupied(inputLabel);
		expected_bool = true;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking occupation for label: " + inputLabel + "\nObtained: "+ result_bool 
                               + "\n(" + reason + ")");        
        }


		// test case 19
       	Person result_person  = res.getPerson(inputLabel);
		Person expected_person = p;
		
        if(result_bool != expected_bool) {
            System.out.println("Checking Person for label: " + inputLabel + "\nObtained: "+ result_person.getName()
                               + "\n(" + reason + ")");        
        }
 

		// test case 18
        expected_string = "\nResidence Maple Hall with capacity 11 has the following patients: \n" +
                "bed 200: \n" +
                "bed 201: \n" +
                "bed 202: \n" +
                "bed 203: \n" +
                "bed 204: \n" +
                "bed 205: Ophelia Mazingtonfordshire\n" +
                "bed 206: \n" +
                "bed 207: \n" +
                "bed 208: \n" +
                "bed 209: \n" +
                "bed 210: \n";
        
        result_string = res.toString();
        if(!result_string.equals(expected_string)) {
            System.out.println("Expected: " + expected_string + "\nObtained: "+ result_string 
                               + "\n(" + reason + ")");        
        }


        System.out.println("Testing complete");
    }
}
