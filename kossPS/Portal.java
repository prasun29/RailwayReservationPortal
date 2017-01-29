//MAIN PORTAL WHERE ALL THE ACTION TAKES PLACE
import java.io.*;
import java.util.*;
public class Portal
{
    static String stations[]={"howrah","kharagpur","midnapur","raigarh","raipur","bilaspur","bhopal","pune","mumbai","goa"};
    public static void main(String args[])throws IOException  //function to be executed
    {
        String c="",d="";
        do
        {
            System.out.println();
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\t \t M A I N - M E N U : RAILWAY RESERVATION PORTAL");
            System.out.println();
            System.out.println(" 1. Create new account");
            System.out.println(" 2. Login account");
            System.out.println(" 3. Delete account");
            System.out.println(" 4. Exit");
            System.out.println();
            String aN;
            Train obj;
            Account ob;   
            boolean t;
            long atm;
            //performing all the input output operations further on and calling the desired function to do task
            try
            {
                System.out.println("Enter choice:");
                int ch=Integer.parseInt(in.readLine());
                switch(ch)
                {
                    case 1:System.out.println("\t \t CREATE  NEW  ACCOUNT");
                    System.out.println();                
                    System.out.println("Enter name of account holder: ");
                    String nam=in.readLine();
                    System.out.println("Enter ATM card number without any space:  ");
                    atm=Long.parseLong(in.readLine());
                    System.out.println("Confirm account signup Y/N");
                    c=in.readLine();
                    if(c.equalsIgnoreCase("n"))   break;
                    Account ac=new Account(nam, atm);
                    break;

                    case 2:System.out.println("Enter account number:");
                    aN=in.readLine();
                    t=exists(aN);
                    if(t==false)
                    {
                        System.out.println("The specified account does not exist.");
                        System.out.println("Terminating...");
                        break;
                    }
                    ob=new Account(aN);
                    if(ob.ATMcard==0)
                    {
                        System.out.println("The account has already been deleted.");
                        System.out.println("The account was closed on "+ob.name);
                        break;
                    }
                    System.out.println("Enter ATM card number to confirm login.");
                    atm=Long.parseLong(in.readLine());
                    if(ob.ATMcard!=atm)
                    {
                        System.out.println("Wrong ATM card number entered.");
                        break;
                    }
                    do
                    {
                        System.out.println(" 1. Book Ticket");
                        System.out.println(" 2. Cancel Ticket");
                        System.out.println(" 3. Check PNR status");
                        System.out.println(" 4. Exit");
                        System.out.println();
                        System.out.println("Enter choice:");
                        int a=Integer.parseInt(in.readLine());
                        switch(a)
                        {
                            case 1:System.out.println("Enter the train number: ");
                            int tn=Integer.parseInt(in.readLine());
                            System.out.println("Enter number of senior citizens travelling:");
                            int NS=Integer.parseInt(in.readLine());
                            System.out.println("Enter number of adults travelling:");
                            int NA=Integer.parseInt(in.readLine());
                            System.out.println("Enter number of children travelling");
                            int NC=Integer.parseInt(in.readLine());
                            int tc=NS+NA+NC;                             
                            System.out.println("Enter the date of travelling in DD-MM-YYYY format :");
                            String date=in.readLine();
                            if(!validateDate(date))
                            {
                                System.out.println("Please enter a valid date which is within 4 months from today");
                                break;
                            }
                                
                            System.out.println("Code for different stations");
                            System.out.println("1-howrah");
                            System.out.println("2-kharagpur");
                            System.out.println("3-midnapur");
                            System.out.println("4-raigarh");
                            System.out.println("5-raipur");
                            System.out.println("6-bilaspur");
                            System.out.println("7-bhopal");
                            System.out.println("8-pune");
                            System.out.println("9-mumbai");
                            System.out.println("10-goa");
                            System.out.println("Enter source station code:");
                            int s=Integer.parseInt(in.readLine());
                            if(s<1||s>10)
                            {
                                System.out.println("The train runs from station 1(howrah) to station 10(goa)");
                                break;
                            }    
                            System.out.println("Enter destination source code:");
                            int ds=Integer.parseInt(in.readLine());
                            if(ds<1||ds>10)
                            {
                                System.out.println("The train runs from station 1(howrah) to station 10(goa)");
                                break;
                            }
                            if(ds<s)
                            {
                                System.out.println("The train runs from station 1 (howrah)to station 10(goa) and not in the opposite direction");
                                break;
                            }
                            if(ds==s)
                            {
                                System.out.println("Enter different stations.");
                                break;
                            }
                            System.out.println("Confirm booking Y/N");
                            c=in.readLine();
                            if(c.equalsIgnoreCase("n"))
                                break;
                            double amount=(0.5*(NS+NC)+NA)*(Math.abs(s-ds))*50;
                            long PNR=getPNR();
                            toFileUser(aN,PNR,tn,date);
                            Train obb=new Train(tn,tc,aN,s,ds,PNR,date,amount,atm);

                            break;

                            case 2:System.out.println("Enter your PNR number");
                            long PNRc=Long.parseLong(in.readLine());
                            if(!checkPNR(PNRc,aN))
                                System.out.println("Invalid PNR number entered");
                            else
                            {
                                System.out.println("Confirm cancellation Y/N?");
                                String cn=in.readLine();
                                if(c.equalsIgnoreCase("y"))
                                    if(delete(PNRc,aN))
                                        System.out.println("Tickets cancelled successfully");
                            }
                            break;

                            case 3:System.out.println("Enter your PNR number");
                            long PNRd=Long.parseLong(in.readLine());
                            if(!checkPNR(PNRd,aN))
                                System.out.println("Invalid PNR number entered");
                            break;

                            case 4:break;
                            default:System.out.println("Wrong input");
                        }
                        if(a==4)
                        {
                            System.out.println("\n Logging out and going back to Main Menu");break;
                        }   
                        System.out.println();
                        System.out.println("Perform another operation? Y/N");
                        d=in.readLine();
                        if(!(d.equalsIgnoreCase("Y")))
                            System.out.println("\n Logging out and going back to Main Menu");
                    }while(d.equalsIgnoreCase("Y"));
                    break;

                    case 3:System.out.println("\t \t \t DELETE - ACCOUNT");
                    System.out.println();
                    System.out.println(" Enter account number to delete:");
                    aN=in.readLine();
                    t=exists(aN);
                    if(t==false)
                    {
                        System.out.println("The specified account does not exist.");
                        System.out.println("Cannot delete");
                        System.out.println("Terminating...");
                        break;
                    }
                    ob=new Account(aN);
                    if(ob.ATMcard==0)
                    {
                        System.out.println("The account has already been deleted.");
                        System.out.println("The account was closed on "+ob.name);
                        break;
                    }
                    System.out.println("Enter ATM card number to confirm delete.");
                    atm=Long.parseLong(in.readLine());
                    if(ob.ATMcard!=atm)
                    {
                        System.out.println("Wrong ATM card number entered.");
                        break;
                    }
                    System.out.println("Name of account holder: "+ob.name);
                    System.out.println("Delete account? Y/N");
                    c=in.readLine();
                    if(c.equalsIgnoreCase("y"))
                    {
                        System.out.println("Are you sure? Y/N");
                        c=in.readLine();
                        if(c.equalsIgnoreCase("Y"))  ob.del();
                        System.out.println("Account successfully deleted.");
                    }
                    break;

                    case 4:break;
                    default:System.out.println("Wrong input");       
                }

                if(ch==4)
                    break;
                System.out.println();
            }
                catch(Exception e)
                {
                    System.out.println("Enter valid inputs");
                }
            System.out.println("Perform another operation? Y/N");
            c=in.readLine();

        }while(c.equalsIgnoreCase("Y"));
    }

    public static long getPNR() //function to generate PNR while booking a ticket
    {                          
        Calendar c=Calendar.getInstance();
        String n=new String();
        n=n.valueOf(c.get(Calendar.MILLISECOND));
        n=n.concat(n.valueOf(c.get(Calendar.SECOND)));
        n=n.concat(n.valueOf(c.get(Calendar.MINUTE)+c.get(Calendar.HOUR)));
        n=n.concat(n.valueOf(c.get(Calendar.DATE)));
        n=n.concat(n.valueOf(c.get(Calendar.MONTH)+1));
        n=n.concat(n.valueOf(c.get(Calendar.YEAR)));
        long num=Long.parseLong(n);
        return(num);
    } 

    public static void toFileUser(String acn,long PNR,int trn,String date)throws IOException //writing details of booking to user file
    {
        PrintWriter opf=new PrintWriter(new BufferedWriter(new FileWriter("acc"+acn+".txt",true)));
        opf.println(PNR);
        opf.println("train"+trn+"-"+date);
        opf.close();
    }

    public static boolean checkPNR(long a,String acn) throws IOException   //checking whether the entered PNR is valid or not and printing its details
    {
        BufferedReader inf=new BufferedReader(new FileReader("acc"+acn+".txt"));
        String s,u,t;
        int flag=0,seaa,dist1,dist2;
        s=inf.readLine();
        s=inf.readLine();
        u=inf.readLine();             
        while(u!=""&&u!=null)
        {  
            Long p=Long.valueOf(u);
            if(p==a)
            {
                if(flag==0)
                {
                    System.out.println(" TICKET DETAILS ");
                    System.out.println("PNR: "+p);
                    System.out.println("COACH 1");
                }
                flag=1;
                t=inf.readLine();
                System.out.println(t);
                BufferedReader br=new BufferedReader(new FileReader(t+".txt")); 
                t=br.readLine();
                if(t!=""&&t!=null)
                {
                    Long pn=Long.valueOf(t);
                    do
                    {                  
                        String det=br.readLine();
                        String det1=br.readLine();
                        String det2=br.readLine();
                        if(det2.length()==1)
                            dist1=(int)det2.charAt(0)-49;
                        else
                            dist1=9;
                        String det3=br.readLine();  
                        if(det3.length()==1)
                            dist2=(int)det3.charAt(0)-49;
                        else
                            dist2=9;
                        if(pn==a)
                        {                   
                            System.out.println("Seat number- "+det1+"  source station - "+stations[dist1]+"   destination source - "+stations[dist2]);
                        }
                        t=br.readLine();
                        if(t!=""&&t!=null)
                            pn=Long.valueOf(t);
                    }while(t!=""&&t!=null);  
                }
                br.close();
            }
            else
            {
                t=inf.readLine();
            }
            u=inf.readLine();
            if(u!=""&&u!=null)
                p=Long.valueOf(u);
        }
        inf.close();
        return (flag==1);
    }

    public static boolean delete(long a,String acn) throws IOException  
    //cancelling ticket from PNR entered and deleting its information from the user file as well as the train file
    {
        int flag=0,count=0;
        File acnfile=new File("acc"+acn+".txt");
        File temp=new File("temp.txt");
        PrintWriter opf=new PrintWriter(new BufferedWriter(new FileWriter(temp,true)));
        BufferedReader inf=new BufferedReader(new FileReader(acnfile));
        String s=inf.readLine();
        String t,send="",send1="",send2="";
        opf.println(s);
        s=inf.readLine();
        opf.println(s);       
        s=inf.readLine();
        while(s!=""&&s!=null)
        {
            Long p=Long.valueOf(s);
            s=inf.readLine();
            if(p==a&&flag==0)
            {              
                flag=1;
                send=s;
                File trn=new File(s+".txt");
                File tem=new File("tem.txt");
                PrintWriter op=new PrintWriter(new BufferedWriter(new FileWriter(tem,true)));
                BufferedReader in=new BufferedReader(new FileReader(trn));
                String pin=in.readLine();
                while(pin!=""&&pin!=null)
                {
                    Long pn=Long.valueOf(pin);
                    if(pn==a)
                    {
                        pin=in.readLine();
                        pin=in.readLine();
                        pin=in.readLine();
                        send1=pin;
                        pin=in.readLine();
                        send2=pin;
                        pin=in.readLine();
                        count++;
                        continue;
                    }//skipped printing it in the new file as in deleting from the train file
                    op.println(pin);
                    pin=in.readLine();
                    op.println(pin);
                    pin=in.readLine();
                    op.println(pin);
                    pin=in.readLine();
                    op.println(pin);
                    pin=in.readLine();
                    op.println(pin);
                    pin=in.readLine();                           
                }
                op.close();
                in.close();
                tem.renameTo(trn);
                s=inf.readLine();
                continue;
            }
            else
            {
                opf.println(p);
                opf.println(s);
            }
            s=inf.readLine();
        }
        opf.close();
        inf.close();
        temp.renameTo(acnfile);
        if(flag==1)
            moveWait(send,send1,send2,count);  //calling the function to confirm the waitlist and RAC as seats are available after cancellation
        return (flag==1);

    }   

    static boolean exists(String n)throws IOException  //checking whether a provide account number exists or not
    {   
        boolean test=false;
        BufferedReader inf=new BufferedReader(new FileReader("list.txt"));
        String in=new String();
        try
        {
            do
            {
                in=inf.readLine();
                if(in.equals(n))
                {
                    test=true;
                    break;
                }
            }
            while(!(in.equals(n) && in.equals(null)));
        }
        catch(Exception e){}
        inf.close();
        return(test);
    }

    public static void moveWait(String trn,String sou,String desti,int mve) throws IOException  //moving waitlist after cancellation of any ticket
    {
        int a[]=new int[50];
        int r[]=new int[20];
        int count=0,countw=0,countr=0,countwl=0;
        File tr=new File(trn+".txt");
        File tem=new File("tem.txt");
        PrintWriter op=new PrintWriter(new BufferedWriter(new FileWriter(tem,true)));
        BufferedReader in=new BufferedReader(new FileReader(tr));
        String s=in.readLine();
        Integer sour=Integer.valueOf(sou);
        Integer destine=Integer.valueOf(desti);
        int j,rcm,wm;
        while(s!=""&&s!=null)
        {
            s=in.readLine();
            s=in.readLine();
            Integer ser=Integer.valueOf(in.readLine());
            Integer dist=Integer.valueOf(in.readLine());
            if(destine<=ser||sour>=dist);
            else
            {
                if(s.charAt(0)=='R')
                {
                    if(s.length()!=4)
                    {
                        Integer RAcnm=(Integer.valueOf(s.charAt(3))-48)*10+Integer.valueOf(s.charAt(4))-48;
                        r[RAcnm-1]=1;
                    }
                    else
                    {
                        Integer RAcnm=Integer.valueOf(s.charAt(3))-48;
                        r[RAcnm-1]=1;
                    }
                }
                else if(s.charAt(0)=='W');
                else
                {
                    Integer seatnm=Integer.valueOf(s);
                    a[seatnm-1]=1;
                }
            }
            s=in.readLine();
        }
        in.close();
        BufferedReader br=new BufferedReader(new FileReader(tr));
        s=br.readLine();      

        while(s!=""&&s!=null)
        {
            op.println(s);
            s=br.readLine();          
            op.println(s);
            s=br.readLine();
            Integer srcn=Integer.valueOf(br.readLine());
            Integer detn=Integer.valueOf(br.readLine());
            if(destine<=srcn||sour>=detn)
            {
                op.println(s);
                op.println(srcn);
                op.println(detn);
                s=br.readLine();
                continue;
            }
            else
            {
                if(s.charAt(0)=='R')
                {
                    if(s.length()!=4)
                    {
                        Integer RAcnm=(Integer.valueOf(s.charAt(3))-48)*10+Integer.valueOf(s.charAt(4))-48;
                        rcm=RAcnm;
                    }
                    else
                    {
                        Integer RAcnm=Integer.valueOf(s.charAt(3))-48;
                        rcm=RAcnm;
                    }
                    for(j=0;j<50;j++)
                    {
                        if(a[j]!=1)
                        {
                            op.println(j+1);
                            a[j]=1;
                            r[rcm-1]=0;
                            break;
                        }
                    }
                    if(j==50)
                    {
                        for(j=0;j<20;j++)
                        {
                            if(r[j]!=1)
                            {
                                op.println("RAC"+(j+1));
                                r[j]=1;
                                r[rcm-1]=0;
                                break;
                            }
                        }
                    }
                }  
                else if(s.charAt(0)=='W')
                {               
                    if(s.length()!=3)
                    {
                        Integer wnm=(Integer.valueOf(s.charAt(2))-48)*10+Integer.valueOf(s.charAt(3))-48;
                        wm=wnm;
                    }
                    else
                    {
                        Integer wnm=Integer.valueOf(s.charAt(2))-48;
                        wm=wnm;
                    }
                    if(wm>mve)
                    {
                        op.println("WL"+(wm-mve));
                    }
                    else
                    {
                        for(j=0;j<50;j++)
                        {
                            if(a[j]!=1)
                            {
                                op.println(j+1);
                                a[j]=1;
                                break;
                            }
                        }
                        if(j==50)
                        {
                            for(j=0;j<20;j++)
                            {
                                if(r[j]!=1)
                                {
                                    op.println("RAC"+(j+1));
                                    r[j]=1;
                                    break;
                                }
                            }
                        }
                    }               
                }
                else
                {
                    op.println(s);                
                }
            }
            op.println(srcn);
            op.println(detn);
            s=br.readLine();
        }
        op.close();
        br.close();
        tem.renameTo(tr);
    }
    
   public static boolean validateDate(String Date)
   {
       Calendar c=Calendar.getInstance();
       int i=0,j=0,dates=0,months=0,years=0;
       int date=c.get(Calendar.DATE);
       int month=c.get(Calendar.MONTH)+1;
       int year=c.get(Calendar.YEAR);
       while(i<Date.length())
       {
           if(Date.charAt(i)=='-')
           {
               if(j==0)
               {
                   dates=Integer.valueOf(Date.substring(0,i));
                   j=i;
               }
               else
               {
                   months=Integer.valueOf(Date.substring(j+1,i));
                   j=i;
                }
            }
            i++;
        }
        years=Integer.valueOf(Date.substring(j+1,i));        
        if(dates<=0||months<=0)
            return false;
        if(months==1||months==3||months==5||months==7||months==8||months==10||months==12)
            if(dates>31)
                return false;
        if(months==4||months==6||months==9||months==11)    
            if(dates>30)
                return false;
        if(months==2)
        {
            if((years%4==0)&&((years%100!=0)||(years%400==0)))
            {
                if(dates>29)
                    return false;
            }        
            else
                if(dates>28)
                    return false;
        }            
        if(years<year||years>(year+1))
            return false;
        if((months==month&&dates<date)||months>12)
            return false;
        if(month<=8)
        {
            if((months<month)||(months>(month+4))||((months==month+4)&&dates>date))
                return false;
        }
        else
        {
            if((months<=(month+4)%12)||months>=month)
            {
                if((months==(month+4)%12)&&dates>date)
                    return false;
            }
            else 
                return false;
        }       
            
        return true;    
   }     
        
        

}