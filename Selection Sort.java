import java.util.Scanner;

class SelectionSort
{
	public static void main(String[] args) 
	{
		
		int number = 0;
		Scanner input = new Scanner(System.in);
        Scanner input_num = new Scanner(System.in); 
		
		// input for number of elements
		System.out.println("How many numbers do you want to sort for unsorted array?");
		number = input_num.nextInt();
		
		// initialize array with number of elements
		int array[]=new int[number];
		
		System.out.println("Please enter " + number + " elements for unsorted array.");
		for(int i=0; i < number; i++)
		{
			array[i]=input.nextInt();
		}

		//Sorting
		for(int i=0; i < number; i++)
		{
			int min=i;
			for(int j=i+1; j < number; j++)
			{
				if(array[j]<array[min])
				{
					min=j;
				}
			}
			int temp=array[i];
			array[i]=array[min];
			array[min]=temp;
		}

		//Output
		System.out.print("The sorted array is:  ");
		for(int i=0; i < number; i++)
		{
			if (i == number - 1)
			{
				System.out.print(array[i] + ".");
			}
			else
			{
				System.out.print(array[i] + "    ");
			}
			
		}
		
		
		
		// close inputs
		input.close();
		input_num.close();
		
	}
}