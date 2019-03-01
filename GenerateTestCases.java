import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GenerateTestCases {

	static int numOfParameters;
	static String[] stringParameters;
	static int[] startOfIntParameters;
	static int[] endOfIntParameters;
	static int[] type;
	
	public static void writeDatabaseAndFile(String writeTestCaseToDB)
    {
        System.out.println(writeTestCaseToDB.substring(0,writeTestCaseToDB.length()-1));
        
        try
        {
            BufferedWriter append = new BufferedWriter(new FileWriter("Testing.csv", true));
            append.write(writeTestCaseToDB.substring(0,writeTestCaseToDB.length()-1));
            append.newLine();
            append.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }

    public static void testCasesForIntegerParameter(int value, int i, int k, int l, int parametersParsed, String writeTestCaseToDB)
    {
        if(parametersParsed == numOfParameters)
        {
            //write test case to db as well as in a file
            writeDatabaseAndFile(writeTestCaseToDB);
            return;
        }

        for(int j = parametersParsed; j < numOfParameters; j++)
        {
            if(j == i)
            {
                writeTestCaseToDB += Integer.toString( value ) + ",";
                parametersParsed++;
                if(parametersParsed == numOfParameters)
                {
                    //write test case to db as well as in a file
                    writeDatabaseAndFile(writeTestCaseToDB);
                    return;
                }
                continue;
            }

            if(type[j] == 1)	//int parameter
            {
            	if( l <= endOfIntParameters[j]/2)
            	{
            		l++;
            		testCasesForIntegerParameter(value, i, k, l, parametersParsed, writeTestCaseToDB);
            		writeTestCaseToDB += Integer.toString(l-1) + ",";
            		parametersParsed++;
                    if(parametersParsed == numOfParameters)
                    {
                        //write test case to db as well as in a file
                        writeDatabaseAndFile(writeTestCaseToDB);
                        return;
                    }
            	}
            }
            else	//string parameter
            {
                String[] splitString = stringParameters[j].split(",");

                if( k < splitString.length)
                {
                    k++;
                    testCasesForIntegerParameter(value,i,k,l,parametersParsed,writeTestCaseToDB);
                    writeTestCaseToDB += splitString[k-1] + ",";
                    parametersParsed++;
                    if(parametersParsed == numOfParameters)
                    {
                        //write test case to db as well as in a file
                        writeDatabaseAndFile(writeTestCaseToDB);
                        return;
                    }
                }

            }
        }



    }

    public static void testCasesForStringParameter(String value, int i, int k, int parametersParsed, String writeTestCaseToDB)
    {
        if(parametersParsed == numOfParameters)
        {
            //write test case to db as well as in a file
            writeDatabaseAndFile(writeTestCaseToDB);
            return;
        }

        for(int j = parametersParsed; j < numOfParameters; j++)
        {
            if(j == i)
            {
                writeTestCaseToDB += value + ",";
                parametersParsed++;
                if(parametersParsed == numOfParameters)
                {
                    //write test case to db as well as in a file
                    writeDatabaseAndFile(writeTestCaseToDB);
                    return;
                }
                continue;
            }

            if(type[j] == 1)	//int parameter
            {
            	writeTestCaseToDB += Integer.toString( endOfIntParameters[j]/2 ) + ",";	//nominal value
                parametersParsed++;
                if(parametersParsed == numOfParameters)
                {
                    //write test case to db as well as in a file
                    writeDatabaseAndFile(writeTestCaseToDB);
                    return;
                }

            }
            else	//string parameter
            {
                String[] splitString = stringParameters[j].split(",");

                //can go out of bound for splitString length
                if( k < splitString.length)
                {
                    k++;
                    testCasesForStringParameter(value,i,k,parametersParsed,writeTestCaseToDB);
                    writeTestCaseToDB += splitString[k-1] + ",";
                    parametersParsed++;
                    if(parametersParsed == numOfParameters)
                    {
                        //write test case to db as well as in a file
                        writeDatabaseAndFile(writeTestCaseToDB);
                        return;
                    }
                }

            }
        }



    }
    
	public static void main(String[] args) 
	{
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Give total no. of parameters: ");
		numOfParameters = input.nextInt();
		
		stringParameters = new String[numOfParameters];
		startOfIntParameters = new int[numOfParameters];
		endOfIntParameters = new int[numOfParameters];
		type = new int[numOfParameters];
 		
		String parametersName = "";
		
		for(int i = 1; i <= numOfParameters; i++)
		{
			System.out.println("Enter " + i + "st parameter name(without space): ");
			parametersName += input.next() + ",";
			
			System.out.println("Give type of " + i + "st parameter(1 for int, 2 for string): ");
			type[i-1] = input.nextInt();
			
			if(type[i-1] == 1)
			{
				System.out.println("Give starting value of range of your integer parameter: ");
				startOfIntParameters[i-1] = input.nextInt();
				
				System.out.println("Give ending value of range of your integer parameter: ");
				endOfIntParameters[i-1] = input.nextInt();
			}
			else if(type[i-1] == 2)
			{
				
				System.out.println("Give the strings in your string parameter");
				System.out.println("Format(comma separated with no space). Eg: a,b,c");
				stringParameters[i-1] = input.next();
			}
			else 
			{
				System.out.println("Please enter valid value(1 for int or 2 for string):");
				type[i-1] = input.nextInt();
			}
			
		}
		
		//file name of txt file
		String fileName = "Testing.csv";
		try 
		{
			BufferedWriter testCases = new BufferedWriter(new FileWriter(fileName));
			testCases.write(parametersName.substring(0, parametersName.length() - 1));
			testCases.newLine();
			testCases.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for(int i = 0; i < numOfParameters; i++) 
        {
            //int parameters
            if (type[i] == 1) 
            {
                for(int j = startOfIntParameters[i]-1; j <= endOfIntParameters[i]+1; j++)
                {
                    testCasesForIntegerParameter(j,i,0,startOfIntParameters[i],0,"");
                }

            }
            else
            {
                String[] splitString = stringParameters[i].split(",");
                testCasesForStringParameter("invalid1",i,0,0,"");					//invalid #1
                for(int j = 0; j < splitString.length; j++)
                {
                    testCasesForStringParameter(splitString[j], i,0,0,"");
                }
                testCasesForStringParameter("invalid2", i,0,0,"");					//invalid #2
            }
        }
		
	}

}
