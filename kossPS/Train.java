import java.util.*;
import java.io.*;
import java.io.File;
class Train
{
    int trnmbr;
    int tickets;
    String acn;
    int src,dest;
    int ar[];
    long PNR;
    String date;
    double amount;
    long ATMcard;
    String stations[]={"howrah","kharagpur","midnapur","raigarh","raipur","bilaspur","bhopal","pune","mumbai","goa"};
    
    Train(int n,int tkt,String st,int s,int d,long a,String da,double amt,long b) throws IOException
    {
        trnmbr=n;
        tickets=tkt;
        acn=st;
        src=s;
        dest=d;
        PNR=a;
        date=da;
        amount=amt;
        ATMcard=b;
        PrintWriter opf=new PrintWriter(new BufferedWriter(new FileWriter("train"+this.trnmbr+"-"+this.date+".txt",true)));
        opf.close();
        for(int j=1;j<=tickets;j++)
        {
            int ate[]=new int[10];
            ar=ate;
            if(checkSeat())
                BookTicket();
            else
                waitList();
        }
        showTickets();
    }

    public boolean checkSeat()throws IOException  //checking whether the seats are available from the source station to destination station or not
    {
        int flag=0;
        BufferedReader br=new BufferedReader(new FileReader("train"+this.trnmbr+"-"+this.date+".txt"));
        String s=br.readLine();
        while(s!=""&&s!=null)
        {
            s=br.readLine();
            s=br.readLine();
            Integer suc=Integer.valueOf(br.readLine());
            Integer dt=Integer.valueOf(br.readLine());
            for(int j=suc;j<dt;j++)
            {
                ar[j-1]++;
            }
            s=br.readLine();
        }
        br.close();
        for(int i=this.src;i<this.dest;i++)
            if(this.ar[i-1]>=50)
                flag=1;
        return (flag==0);
    }

    public void BookTicket()throws IOException  //if availabilty of seat is there then booking ticket
    {
        int mark[]=new int[50];
        int j;
        BufferedReader br=new BufferedReader(new FileReader("train"+this.trnmbr+"-"+this.date+".txt"));
        String s=br.readLine();
        while(s!=""&&s!=null)
        {
            s=br.readLine();
            s=br.readLine();
            Integer ser=Integer.valueOf(br.readLine());
            Integer dst=Integer.valueOf(br.readLine());
            if(dest<=ser||src>=dst);
            else
            {
                if(s.charAt(0)!='R'&&s.charAt(0)!='W')
                {
                    Integer aa=Integer.valueOf(s);
                    mark[aa-1]=1;
                }
            }    
            s=br.readLine();
        }
        br.close();
        for(j=0;j<50;j++)
            if(mark[j]!=1)
            {
                toFile(j+1);
                break;
            }   

    }     

    public void showTickets()   //showing the details of the tickets after booking
    {
        System.out.println();
        System.out.println("   T I C K E T - D E T A I L S");
        System.out.println();
        System.out.println("PNR NUMBER: "+this.PNR);
        System.out.println("Train Number: "+this.trnmbr);
        System.out.println("Number of tickets booked: "+this.tickets);
        System.out.println("Date of travelling: "+this.date);
        System.out.println("Station travelling from: "+stations[src-1]);
        System.out.println("Station travelling to: "+stations[dest-1]);
        System.out.println("COACH 1");
        System.out.println("Ticket booked and amount of rupees "+this.amount+" paid by ATM card number "+this.ATMcard);
    }

    public void toFile(int seat) throws IOException  //writing to the train file the details of booking
    {  
        PrintWriter opf=new PrintWriter(new BufferedWriter(new FileWriter("train"+this.trnmbr+"-"+this.date+".txt",true)));
        opf.println(this.PNR+"\n"+this.acn+"\n"+seat+"\n"+this.src+"\n"+this.dest);
        opf.close(); 
    }

    public void waitList()throws IOException  //implementing the waitlist function to book RAC and waitlist in case of non-availabilty of seats
    {
        BufferedReader br=new BufferedReader(new FileReader("train"+this.trnmbr+"-"+this.date+".txt"));
        int count=0,countw=0;
        String s=br.readLine();
        while(s!=""&&s!=null)
        {

            s=br.readLine();
            s=br.readLine();
            Integer ser=Integer.valueOf(br.readLine());
            Integer dst=Integer.valueOf(br.readLine());
            if(dest<=ser||src>=dst);
            else
            {
                if(s.charAt(0)=='R')
                    count++;
                if(s.charAt(0)=='W')
                    countw++;
            }
            s=br.readLine();
        }
        br.close();
        PrintWriter opf=new PrintWriter(new BufferedWriter(new FileWriter("train"+this.trnmbr+"-"+this.date+".txt",true)));
        if(count<20)
            opf.println(this.PNR+"\n"+this.acn+"\nRAC"+(count+1)+"\n"+this.src+"\n"+this.dest);
        else
            opf.println(this.PNR+"\n"+this.acn+"\nWL"+(countw+1)+"\n"+this.src+"\n"+this.dest); 
        opf.close();
    }
}