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
	
	public static void testCasesForIntegerParameter(int value, int i, String fileName) 
	{
		String writeTestCaseToFile = ""; 
		boolean flag = false; //true = string parameters present
		for(int j = 0; j < numOfParameters; j++)
		{
			if(j == i)
			{
				writeTestCaseToFile += Integer.toString( value ) + ",";	
				continue;
			}
			
			if(type[j] == 1)	//int parameter
			{
				writeTestCaseToFile += Integer.toString( endOfIntParameters[j]/2 ) + ",";	//nominal value
				
			}
			else	//string parameter
			{
				flag = true;
				
				String[] splitString = stringParameters[j].split(",");
				
				for(int k = 0; k < splitString.length; k++)
				{
					
					String temp = writeTestCaseToFile + splitString[k] + ",";
					try 
					{ 
						// Open given file in append mode. 
						BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
						out.write(temp.substring(0, temp.length() - 1)); //substring to remove last comma
						out.newLine();
						out.close(); 
					} 
					catch (IOException e) 
					{ 
						System.out.println("exception occoured" + e); 
					} 
					
				}
			}
		}
		
		//works only when no string parameter present
		if(flag == false) 
		{
			try 
			{ 
				// Open given file in append mode. 
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
				out.write(writeTestCaseToFile.substring(0, writeTestCaseToFile.length() - 1)); //substring to remove last comma
				out.newLine();
				out.close(); 
			} 
			catch (IOException e) 
			{ 
				System.out.println("exception occoured" + e); 
			} 
		}
		
		
	}
	
	public static void testCasesForStringParameter(String string, int i, String fileName) 
	{
		String writeTestCaseToFile = ""; 
		boolean flag = false; //true = string parameters present
		for(int j = 0; j < numOfParameters; j++)
		{
			if(j == i)
			{
				writeTestCaseToFile += string + ",";	
				continue;
			}
			
			if(type[j] == 1)	//int parameter
			{
				writeTestCaseToFile += Integer.toString( endOfIntParameters[j]/2 ) + ",";	//nominal value
				
			}
			else	//string parameter
			{
				flag = true;
				
				String[] splitString = stringParameters[j].split(",");
				
				for(int k = 0; k < splitString.length; k++)
				{
					
					String temp = writeTestCaseToFile + splitString[k] + ",";
					try 
					{ 
						// Open given file in append mode. 
						BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
						out.write(temp.substring(0, temp.length() - 1)); //substring to remove last comma
						out.newLine();
						out.close(); 
					} 
					catch (IOException e) 
					{ 
						System.out.println("exception occoured" + e); 
					} 
					
				}
			}
		}
		
		//works only when no string parameter present
		if(flag == false) 
		{
			try 
			{ 
				// Open given file in append mode. 
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
				out.write(writeTestCaseToFile.substring(0, writeTestCaseToFile.length() - 1)); //substring to remove last comma
				out.newLine();
				out.close(); 
			} 
			catch (IOException e) 
			{ 
				System.out.println("exception occoured" + e); 
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
		String fileName = "Test_Cases.csv";
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
			if(type[i] == 1)
			{
				testCasesForIntegerParameter(startOfIntParameters[i]-1, i, fileName);	//invalid #1
				testCasesForIntegerParameter(startOfIntParameters[i], i, fileName);
				testCasesForIntegerParameter(startOfIntParameters[i]+1, i, fileName);
				testCasesForIntegerParameter(endOfIntParameters[i]-1, i, fileName);
				testCasesForIntegerParameter(endOfIntParameters[i], i, fileName);
				testCasesForIntegerParameter(endOfIntParameters[i]+1, i, fileName);		//invalid #2
			}
			else
			{
				String[] splitString = stringParameters[i].split(",");
				testCasesForStringParameter("invalid1", i, fileName);					//invalid #1
				for(int j = 0; j < splitString.length; j++)
				{
					testCasesForStringParameter(splitString[j], i, fileName);
				}
				testCasesForStringParameter("invalid2", i, fileName);					//invalid #2

				
			}
			
		}
		
	}

}
