import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;
import java.io.*;  // Using AWT event classes and listener interfaces
 
// An AWT program inherits from the top-level container java.awt.Frame
public class Integrator extends Frame implements ActionListener {
   private Label lblCount;    // Declare a Label component 
   private TextField tfCount; // Declare a TextField component 
   private Button btnCount;
   private Button check;   // Declare a Button component1
   private int count = 0;     // Counter's value
   private Label l1,l2,l3,l4;
   Process p;
   StringBuffer op=new StringBuffer() ;
   String text;
   int headline=0,pricedata=0,infoproc=0,accuracy=0,flag=0;
   String s[] = new String[10];
   // Constructor to setup GUI components and event handlers
   public Integrator () {
      setLayout(new FlowLayout());
        
 
     
 
      tfCount = new TextField(" ", 10); // construct the TextField component
      tfCount.setBounds(500,265,45,45);
      
      tfCount.setEditable(true);       // set to read-only
      add(tfCount);                     // "super" Frame adds TextField
 
      btnCount = new Button("Click");   // construct the Button component
      btnCount.setBounds(500,325,45,45);
      check = new Button("Check");
      check.setBounds(500,550,45,45);
      check.setBackground(Color.orange);
      btnCount.setBackground(Color.orange);
      add(btnCount);
      add(check);
      check.setVisible(false);
      Font myFont = new Font("Serif",Font.BOLD,30);
      l1 = new Label("Collected headlines and price data from websites");
      l1.setBounds(500,150,700,100);
      l1.setFont(myFont);
      l2 = new Label("Classified the headlines based on keywords done");
      l2.setBounds(500,250,700,100);
      l2.setFont(myFont);
      l3 = new Label("Applying Naive bayes classification to examine the accuracy of classifier ");
      l3.setBounds(500,350,700,100);
      l3.setFont(myFont);
      l4 = new Label("click the button to check the accuracy");
      l4.setBounds(500,450,700,100);
      l4.setFont(myFont);
      l1.setForeground(Color.GREEN);
      l2.setForeground(Color.GREEN);
      l3.setForeground(Color.GREEN);
      l4.setForeground(Color.GREEN);
      l1.setVisible(false);
      l2.setVisible(false);
      l3.setVisible(false);
      l4.setVisible(false);
      add(l1);
      add(l2);
      add(l3);
      add(l4);
      setLayout(new BorderLayout());                    // "super" Frame adds Button
 
      btnCount.addActionListener(this);
      check.addActionListener(this);
       
 
      setTitle("AWT Counter");  // "super" Frame sets its title
     setBackground(Color.BLUE);

      setSize(250, 100);        // "super" Frame sets its initial window size
 
      
 
      setVisible(true);         // "super" Frame shows
 
     
   }
   
   public void paint(Graphics g)
      {
        super.paint(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.GREEN);
        try{
       // System.out.println("in paint method");
        if(count==0)
        {
          g.drawString( "Our project has 3 modules", 500,75);
          Thread.sleep(1000);
          g.drawString( "Module no 1: Web Scraping", 500,115);
          Thread.sleep(1000);
          g.drawString( "Module no 2: Info Processing", 500,165);
          Thread.sleep(1000);
          g.drawString( "Module no 3: Accuracy Classification", 500,195);
          Thread.sleep(1000);
          g.drawString( "click button to initiate process", 500,225);
          Thread.sleep(1000);
        }  
          
        else         
        {
          int x=200,y=200;
          for(int i=0;i<s.length;i++)
          { g.drawString(s[i],x,y);x=x+35;y=y+35;}
        System.out.println("inside else paint"+count);}
        //System.out.println("flag = " + flag + "count = " + count);
        
      }
        catch(Exception e){}
      }
 
   // The entry main() method
   public static void main(String[] args) {
      // Invoke the constructor to setup the GUI, by allocating an instance
      Integrator app = new Integrator();
         // or simply "new AWTCounter();" for an anonymous instance
   }
 
   public void actionPerformed(ActionEvent evt) 
   {
      ++count; // increase the counter value
      
      try
      { String q = tfCount.getText();
       if(count == 1) 
       { 
            count=100;
            btnCount.setVisible(false);
            tfCount.setVisible(false);
            //String q = tfCount.getText();
            p = Runtime.getRuntime().exec("python headline.py "+q);
            p.waitFor();
            //p = Runtime.getRuntime().exec("python pricedata.py "+ q +" "+"\""+"Jan 27, 2001 - Feb 27, 2017"+"\"");
            //p.waitFor();
            l1.setVisible(true);
            Thread.sleep(15000);
            l2.setVisible(true);
            Thread.sleep(1000);
            l3.setVisible(true);
            p = Runtime.getRuntime().exec("java accuracytest "+q);
            p.waitFor();
            Thread.sleep(1000);
            check.setVisible(true);
            l4.setVisible(true);
            
       } 
        else
        {
          tfCount.setVisible(false);
          check.setVisible(false);
          l1.setVisible(false);
          l2.setVisible(false);
          l3.setVisible(false);
          l4.setVisible(false);
          InputStream is = Runtime.getRuntime().exec("java accuracytest "+q).getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buff = new BufferedReader (isr);
            String line;int i=0;
            while((line = buff.readLine()) != null)
           { op.append(line); 
             s[i++]=line;
             op.append(System.lineSeparator());
             System.out.print(line);
           }          
          repaint();
        }

      }
       catch(Exception e){ System.out.println(e);}
    }
   
}