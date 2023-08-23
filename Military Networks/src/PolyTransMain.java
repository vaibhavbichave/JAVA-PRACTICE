import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;


public class PolyTransMain extends JFrame
{

    /** Creates a new instance of DesMain */
    public PolyTransMain()
    {
		DesPanel  mm = new DesPanel();
        mm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mm.setVisible(true);

    }

    public static void main(String [] rr)
    {
	    PolyTransMain e=new PolyTransMain();

    }
}
class DesPanel extends JFrame
{
JMenuBar Mb=new JMenuBar();


JMenu Menul=new JMenu("Menu");
JMenu About=new JMenu("About");



JMenuItem Help=new JMenuItem("HELP");
JMenuItem Exit=new JMenuItem("EXIT");


  public DesPanel()
    {
        this.setTitle("Kalpesh");
        this.setSize(800,800);
        this.setVisible(true);

				Mb.add(Menul);
		setJMenuBar(Mb);
      getContentPane().setBackground(Color.BLACK);

DesPanel f;


      JOptionPane.showMessageDialog(null, "Create By Kalpesh", "title",  JOptionPane.INFORMATION_MESSAGE);
//_~~~~
Menul.add(Help);
Menul.add(Exit);
Exit.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent e)
											{

											}
										});

//
      GenerateGUI();
    }
    PolyTrans p;
    public static   JTextArea StepsText = new JTextArea(10,40);


    private    Container c=this.getContentPane();
    private    JButton btnCihper = new JButton("Cipher");
    private    JButton btnDeCihper = new JButton("Decipher");

    private   JTextArea CipherText = new JTextArea(4,40);
    private   JTextArea OriginalText = new JTextArea(4,40);
    private   JTextArea DeCipherText = new JTextArea(4,40);


    private    JScrollPane OriginalScorl=new JScrollPane(OriginalText,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private    JScrollPane CipherScorl=new JScrollPane(CipherText,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JScrollPane DecipherScorl=new JScrollPane(DeCipherText,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private   JScrollPane StepScorl=new JScrollPane(StepsText,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTextField KeyWord = new JTextField(16);
    private  JTextField KeyWordTwo = new JTextField(16);
    private  JPanel PanelCipher = new JPanel();
    private  JPanel PanelDecipher = new JPanel();
    private  JPanel PanelKeyWord = new JPanel();

    private  JPanel PanelOriginaltxt = new JPanel();
    private JPanel jpstep=new JPanel();

    private  JLabel lblKeyWord= new JLabel("HASH KEY: ");
					//	private jpstep.setBackground(Color.red);

    private void GenerateGUI(){
        c.setLayout(new GridLayout(4,1));

        c.setLayout(new FlowLayout());

        PanelKeyWord.setLayout(new FlowLayout());
        PanelKeyWord.add(lblKeyWord);
        PanelKeyWord.add(KeyWord);


       // PanelOriginaltxt.setBorder(BorderFactory.createTitledBorder("Original Text"));
        PanelOriginaltxt.setLayout(new FlowLayout());
        PanelOriginaltxt.add(OriginalScorl);

        //PanelCipher.setBorder(BorderFactory.createTitledBorder("Cipher Text"));
        PanelCipher.setLayout(new FlowLayout());
        PanelCipher.add(CipherScorl);
        PanelCipher.add(btnCihper);

       // PanelDecipher.setBorder(BorderFactory.createTitledBorder("Decipher Text"));
        PanelDecipher.setLayout(new FlowLayout());
        PanelDecipher.add(DecipherScorl);
        PanelDecipher.add(btnDeCihper);
     //   jpstep.setBorder(BorderFactory.createTitledBorder("Mointor"));
        jpstep.setLayout(new FlowLayout());
        jpstep.add(StepScorl);


        c.add(PanelKeyWord);

        c.add(PanelOriginaltxt);
        c.add(PanelCipher);
        c.add(PanelDecipher);
        c.add(jpstep);

        DoActions();


    }
    private void DoActions(){
        ActionHandler action = new ActionHandler();

        btnCihper.addActionListener(action);
        btnDeCihper.addActionListener(action);

    }

    private class ActionHandler implements ActionListener{


        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btnCihper){
                String plaintext=OriginalText.getText();
                String keyworD=KeyWord.getText();
                StepsText.append("keyword : "+keyworD+'\n');
                StepsText.append("PlainText : "+plaintext+'\n');
                p= new PolyTrans(keyworD,plaintext);
                p.Encrypt();
                p.FillCharacMatrix();


                CipherText.append(p.getCipher());

            }
            if(e.getSource()==btnDeCihper){
                p.Decrypt();


                DeCipherText.append("\n"+p.getDeCipher());
            }
        }

    }
    private String fina;

}

class DocumentSizeFilter extends DocumentFilter {
    int maxCharacters;

    public DocumentSizeFilter(int maxChars) {
        maxCharacters = maxChars;
    }

    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)

throws BadLocationException {

        if ((fb.getDocument().getLength() + str.length()) <= maxCharacters)
            super.insertString(fb, offs, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }

    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)

throws BadLocationException {

        if ((fb.getDocument().getLength() + str.length()- length) <= maxCharacters)
            super.replace(fb, offs, length, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }

}

