import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class SwingControlDemo {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   public StringBuilder sb;
   
   public SwingControlDemo(){
      prepareGUI();
   }
   
   public SwingControlDemo(StringBuilder sb1)
   {
	   sb = sb1;
	   prepareGUI();
   }
   
   public void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.LEFT);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,100);
      
     // headerLabel.setVerticalAlignment(SwingConstants.WEST);
      
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   
   public void showLabelDemo(){
      headerLabel.setText("Control in action: JLabel");      
      JLabel label  = new JLabel("", JLabel.CENTER);
      JLabel test_label = new JLabel("",JLabel.RIGHT);
      
      
      
      //String[] string_array = sb.toString().split("|");
      
      //System.out.println("String array:  " + string_array[1]);
     
      
      label.setText("<html>Lovely today isn't it?<br></html>");
      test_label.setText("This is a test.");
    
      
      //label.setText(sb.toString());
     
      
      label.setOpaque(true);
      label.setBackground(Color.GRAY);
      label.setForeground(Color.WHITE);
      controlPanel.add(label);
      
      
      
      test_label.setOpaque(true);
      test_label.setBackground(Color.WHITE);
      test_label.setForeground(Color.BLUE);
      controlPanel.add(test_label);
      
      
      
      mainFrame.setVisible(true);  
   }
   
   
   
   public static void main(String[] args){
	      SwingControlDemo  swingControlDemo = new SwingControlDemo();      
	      swingControlDemo.showLabelDemo();
	   }
}