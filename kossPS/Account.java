import java.util.*;
import java.io.*;
class Account
{
    String acn;
    String name;
    long ATMcard;

    Account(String s) throws IOException
    {
        try
        {
            BufferedReader br=new BufferedReader(new FileReader("acc"+s+".txt"));
            acn=s;
            name=br.readLine();
            ATMcard=Long.parseLong(br.readLine());            
        }
        catch(Exception e)
        {
            System.out.println();
        }
    }

    Account(String n,long atm) throws IOException  //accepting the details of user and assigning account number
    {
        name=n;
        ATMcard=atm;
        BufferedReader br=new BufferedReader(new FileReader("list.txt"));
        String text=new String();
        String ans=new String();
        text=br.readLine();

        String s=new String();
        StringBuffer str=new StringBuffer(s);

        try
        {
            do
            {
                ans=text;
                text=br.readLine();
            }
            while(!(text.equals("")||text.equals(null)));
        }
        catch(Exception e){}
        if(ans.equals("0")||ans==null)
            ans="0";
        br.close();

        int ac=Integer.parseInt(ans);
        ac+=1;
        acn=s.valueOf(ac);
        toFile();

        PrintWriter opf=new PrintWriter(new BufferedWriter(new FileWriter("list.txt",true)));
        opf.println(acn);
        opf.close();

        System.out.println("New account created.");
        show();
    }

    public void toFile() throws IOException  //writing the information of user to his file
    {
        PrintWriter opf=new PrintWriter(new BufferedWriter(new FileWriter("acc"+acn+".txt")));
        opf.println(this.name);
        opf.println(this.ATMcard);
        opf.close();
    }

    public void show()   //function to show the details of a newly created account
    {
        System.out.println();
        System.out.println("   A C C O U N T - D E T A I L S");
        System.out.println();
        System.out.println("Name of account holder: "+this.name);
        System.out.println("Account number: "+this.acn);
        System.out.println("ATM card number: "+this.ATMcard);
    }

    public void del()throws IOException  //function to delete an account
    {
        PrintWriter opf=new PrintWriter(new BufferedWriter(new FileWriter("acc"+this.acn+".txt")));
        String date=getDate();
        opf.println(date);
        opf.close();
    }

    public static String getDate()  //fucntion to get present date
    {
        Calendar c=Calendar.getInstance();
        String d=new String();
        d=d.valueOf(c.get(Calendar.DATE)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR));
        return(d);
    }
}