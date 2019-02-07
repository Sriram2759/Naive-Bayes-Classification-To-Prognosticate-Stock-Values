import java.io.*;
import java.sql.*;
import java.util.*;
public class accuracytest
{
    
    public static void main(String argv[])
    {
     int count = 0;float totalpos=0,totalneg=0,total=0;float finalpos=1,finalneg=1;
    int tp=0,tn=0,fp=0,fn=0;
    Map<String,Integer> map=new HashMap<String,Integer>();
    try
    {
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","ramu","ramu1234");
        PreparedStatement pst = con.prepareStatement("select * from keywordinfo where keyword = ?  and symbol = ?" ); 
        PreparedStatement sh = con.prepareStatement("select * from stemmedheadlines where symbol = ? " ); 
        Statement stmt1 = con.createStatement();
        Statement stmt2 = con.createStatement();
         PreparedStatement sh1 = con.prepareStatement("select * from keywordinfo where symbol = ? " );
        sh1.setString(1,argv[0]); 
        ResultSet rs1=sh1.executeQuery();
        String s=argv[0];
        argv[0] =argv[0];
        sh.setString(1,argv[0]);
        ResultSet rs2=sh.executeQuery();
        ResultSet prs;
     //  System.out.println("323111111111");
        while(rs2.next())
        {
          count++;
        }
        rs2.beforeFirst();
        int i=0;
       // System.out.println("9999999999999999999999");
        while( i != count- count + 2)
        {
          i++;
          rs2.next();
        }
        i=0;
        //System.out.println("3434");
        while(rs1.next())
        {
           map.put(rs1.getString(1),1);
           totalpos = totalpos + rs1.getInt(3);
           totalneg = totalneg + rs1.getInt(4);
        }
       //System.out.println("erkfkrehfkrhferfre");
        total = totalpos + totalneg;
        while(rs2.next())
        {
           String headline = rs2.getString(2);
           //System.out.println(headline);
           String[] arr = headline.split(" "); 
          //System.out.println("00808080989");
           i=0;   
           while(i!=arr.length)
           {
              //System.out.println(headline);
             // System.out.println("arr.length=" + arr.length + "  i=  " + i);
              if( map.containsKey(arr[i]))
              {
                    pst.setString(1,arr[i]); 
                   //System.out.println(arr[i]);
                   pst.setString(2,s);              
                    prs = pst.executeQuery();
                    prs.next();
                    //System.out.println("jgsjksag");
                    float temp1 =  (float)prs.getInt(3)/totalpos;
                    //System.out.println("shdkjashdkhakdh");
                    float temp2 =  (float)totalpos/total;
                    float temp3 =  (float)prs.getInt(2)/total;
                    float temp4 = (temp1 * temp2)/temp3;
                    float temp5 =  (float)prs.getInt(4)/totalneg;
                    float temp6 =  (float)totalneg/total;
                    float temp7 = (temp5 * temp6)/temp3;
                    finalpos = finalpos * temp4;
                    finalneg = finalneg * temp7;
              }
             i++;
            }
   	        int flag ;
        	   if(finalpos>=finalneg)
           		flag =1;
           	   else
           		flag=2;
                int impact = rs2.getInt(5);
              if(impact ==0)
              continue;
             // System.out.println("impact = " + impact + "flag=" + flag);
              if(impact==1 && flag==1)
              tp++;
              if(impact==2 && flag==2)
              tn++;
              if(impact ==2 && flag==1)
              fp++;
              if (impact ==1 && flag==2)
              fn++;
           // System.out.println("fop= " + finalpos  + "  fon= " + finalneg);
            finalpos=finalneg=1;
          }
          System.out.println("true positives = " + tp);
          System.out.println("true negatives = " + tn);
          System.out.println("false positives = " + fp);
          System.out.println("false negatives = " + fn);
          System.out.println("accuracy = " + (float)(tp+tn)*100/(tp+tn+fp+fn));
    }
   
   catch(Exception e)
   {
       System.out.println("Exception = " + e);
   }
 }
}                        