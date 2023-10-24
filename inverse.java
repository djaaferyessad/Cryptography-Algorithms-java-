public class inverse {
    public static void main(String[] args) {
        System.out.println(inverse(3, 7));


    }
    public static int inverse(int number ,int modenumber)
    {
        for(int i=0;i<modenumber;i++)
        {
            if( (number*i) % modenumber == 1)
            {
                return i ;
            }
        }
        return 0;
    }
}
