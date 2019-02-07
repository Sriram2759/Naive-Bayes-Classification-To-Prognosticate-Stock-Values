//package com.java2novice.algos;
import java.sql.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;
 
public class MaxDuplicateWordCount {
     
    public Map<String, Integer> getWordCount(String fileName){
        
        FileInputStream fis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        System.out.println(fileName);
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            String line = null;
            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, " ");
                while(st.hasMoreTokens()){
                    String tmp = st.nextToken().toLowerCase();
                    if(wordMap.containsKey(tmp)){
                        wordMap.put(tmp, wordMap.get(tmp)+1);
                    } else {
                        wordMap.put(tmp, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return wordMap;
    }
     
    public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap){
         
        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list;
    }
     
    public static void main(String argv[]){
        String bagofwords[] = new String[1000] ;int hitcount=0;
        int timeperiod = 1;
        MaxDuplicateWordCount mdc = new MaxDuplicateWordCount(); int flag=0,count=0,oc=0;
        String stopwords[] = {"a","abb", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"};
        Map<String, Integer> wordMap = mdc.getWordCount(argv[0]+"StemmedHeadlines.txt");
        List<Entry<String, Integer>> list = mdc.sortByValue(wordMap);
        System.out.println("\n length = " + stopwords.length);
        for(Map.Entry<String, Integer> entry:list)
        {
             for(int i=0;i<stopwords.length;i++)
             {
                if(entry.getKey().equals(stopwords[i]))
                {
                   flag=1;
                   break;
                }
             }
             if(flag==0 && entry.getValue()>3)                 
             {
                 bagofwords[count++] = entry.getKey();
                            
             }
                    
             flag=0;
         }
         oc = count;
         count=0;
         
        try
        {
                String temp;  String bow;  int freq=0;  String sorday;  String submon;  String submon1;String desday;
                float sornum=0,desnum=0; String dupsorday;String dupdesday;String sym=argv[0];
                int poscount=0 , negcount=0;
                
        	Class.forName("com.mysql.jdbc.Driver");  
        	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","ramu","ramu1234");
                
                
       		PreparedStatement pst1=  con.prepareStatement("select * from stemmedheadlines where symbol = ?" ); 
                PreparedStatement pst2 = con.prepareStatement("select * from marketdata where mDate like ? and symbol = ?" );   
                PreparedStatement pst3 = con.prepareStatement("select * from marketdata where mDate like ? and symbol = ?" );  
                PreparedStatement pst4 = con.prepareStatement("update stemmedheadlines set impact = ? where headlineid = ?" );  
                
                
                 
                ResultSet prs1;
                ResultSet prs2;
                ResultSet prs3;
                
                int impact = -1;
                int hdlineid=0;

                
                String mon[] = {"Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" ,
                                "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec" };
                while(count!=oc)
                { 
                       pst1.setString(1,argv[0]);
                       prs1=pst1.executeQuery();
                       bow = bagofwords[count++];negcount=0;poscount=0;freq = 0;

                       while(prs1.next())
                       {
                          
                           temp = prs1.getString(2);
                           if(temp.contains(bow))
                           {
                              
                              hdlineid = prs1.getInt(6);
                              sorday = prs1.getString(1);
                              submon = sorday.substring(0,3);
                              submon1 = sorday.substring(3,11);
                              for(int j=0;j<mon.length;j++)
                              {
                                  if(mon[j].equals(submon))
                                  {
                                     submon = mon[(j+1) % mon.length];
                                     break;
                                  }
                              }
                            desday = submon + submon1;int x=0;
                            dupsorday = sorday;
                            dupsorday = dupsorday.replace(" ","");
                            dupdesday = desday;
                            dupdesday = dupdesday.replace(" ","");
                            String dupdesday1 = "%"+dupdesday+"%";
                            String dupsorday1 = "%"+dupsorday+"%";
                            System.out.println("58398173981391831");
                            
                            pst2.setString(1,dupsorday1);
                            pst2.setString(2,argv[0]);
                            prs2 = pst2.executeQuery();
                            
                            if(!prs2.next())
                             sornum=0;
                            else
                            {
                             System.out.println("\n hereee in source else" + dupsorday1);
                             if(prs2.getString(2).isEmpty() || prs2.getString(2).equals("None") || prs2.getString(2)==null)
                             sornum = 0;
                             else
                             sornum = Float.parseFloat(prs2.getString(2));
                             }
                            
                            
                            pst3.setString(1,dupdesday1);
                            pst3.setString(2,argv[0]);
                            
                            prs3 = pst3.executeQuery();
                            
                         
                            if(!prs3.next())
                             desnum=0;
                            else
                            {
                                System.out.println("here in destination else " + prs3.getString(1));
                                if(prs3.getString(5).isEmpty() || prs3.getString(5).equals("None") || prs3.getString(5)==null)
                                desnum=0;
                                else
                                desnum = Float.parseFloat(prs3.getString(5));
                            
                            }   
                          
         
                           System.out.println(dupsorday);
                           System.out.println(dupdesday);
                           System.out.println(sornum);
                           System.out.println(desnum);
             
                           float diff = desnum - sornum;
                           if (sornum!=0 && desnum!=0)
                           { 
                            freq++;      
                            if(diff <= 0)
                            {
                                 negcount = negcount + 1;
                                 pst4.setInt(1,2);
                                 pst4.setInt(2,hdlineid);
                                 pst4.executeUpdate();
                                
                             }
                            else
                            {
                                poscount = poscount + 1;
                                pst4.setInt(1,1);
                                pst4.setInt(2,hdlineid);
                                pst4.executeUpdate();
                                
                             }
                       
                          }
                   }}
                String query = " insert into keywordinfo(keyword , frequency , positivecount , negativecount,timeperiod,symbol)"
                                 + " values (?, ? , ? , ? , ?, ?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString (1, bow);
                preparedStmt.setString (2, Integer.toString(freq));
                preparedStmt.setString (3, Integer.toString(poscount));
                preparedStmt.setString (4, Integer.toString(negcount));
                preparedStmt.setString (5, Integer.toString(timeperiod));
                preparedStmt.setString (6, sym);
                preparedStmt.execute();  
           }
             
        }
   
        catch(Exception e)
        {
            e.printStackTrace();
            
        }                                    
                                                             
                                          
                                              
        
    }
}
