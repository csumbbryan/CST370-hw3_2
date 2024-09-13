// A sample program to generate permutations using a recursive function.
// Reference: https://stackoverflow.com/questions/13218019/generating-permutations-of-an-int-array-using-java-error

public class All_Permutations //Renamed from Main
{
    public static void Permute(int[] input, int startindex) 
    {
        int size = input.length;

        if (size == startindex + 1) 
        {
            for (int i = 0; i < size; i++) 
            {
                System.out.print(input[i] + "  ");
            }
            System.out.println();
        } 
        else 
        {
            for (int i = startindex; i < size; i++) 
            {
                int temp = input[i];
                input[i] = input[startindex];
                input[startindex] = temp;
                System.out.println("i: " + i + " startindex: " + startindex);

                Permute(input, startindex + 1);
                temp = input[i];
                input[i] = input[startindex];
                input[startindex] = temp;
            }
        }
    }


    public static void main(String[] args) 
    {
        int arraylength = 3;
        int[] input = new int[arraylength];
        for (int i = 0; i < arraylength; i++) {
            input[i] = i+1;
        }
        Permute(input, 0);
    }

}

