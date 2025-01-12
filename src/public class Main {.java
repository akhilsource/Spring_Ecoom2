public class Main {
    public static void main(String[] args)
    {
        File f1=new File("Sample.txt");
        System.out.println(f1.createNewFile());
        FileOutputStream f2=new FileOutputStream(f1,a);
    }
}
