import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GUI extends JFrame implements WindowListener{
    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenuItem quitOption;
    //For character select
    JFrame f = new JFrame("Password Operator");
    JTextArea ta = new JTextArea();
    JPanel outerPanel;
    JTextArea textArea = new JTextArea();
    ArrayList<JRadioButton> buttonOptions = new ArrayList<>();
    JLabel questionTitle = new JLabel();

    public static void main(String[] args){
        GUI g = new GUI();
        g.ta.setBounds(75, 30, 100, 20);
        g.f.add(g.ta);
        String[] stopThread = new String[1];
        stopThread[0]="no";
        JButton but = new JButton( (new AbstractAction("Confirm") {
            public void actionPerformed(ActionEvent e) {
                stopThread[0] ="yes";
            }
        }));
        but.setBounds(75, 150, 100, 30);
        g.questionTitle=new JLabel(" What is your general password? ");
        g.questionTitle.setBounds(20, 50, 200, 50);
        g.f.add(but);
        g.f.add(g.questionTitle);
        g.f.setSize(300,400);
        g.f.setLayout(null);
        g.f.setVisible(true);
        while(stopThread[0]!= null && !stopThread[0].equals("yes")){
            try {
                Thread.sleep(50);
            }
            catch(InterruptedException e){

            }
        }
        String generalPassword=g.ta.getText();
        g.ta.setText("");
        g.questionTitle.setText("What do you need a password for?");

        //Asks again for purpose
        stopThread[0] = "no";
        while(stopThread[0]!= null && !stopThread[0].equals("yes")){
            try {
                Thread.sleep(50);
            }
            catch(InterruptedException e){

            }
        }
        String purposePassword=g.ta.getText();
        g.ta.setText("");
        g.questionTitle.setText("");

        String continuing = g.askQuestions("Do you want to use the regular settings?", new ArrayList<String>(){{add("Yes");add("No");}});

        if(continuing.equals("Yes")){
            try {
                PasswordManager pm = new PasswordManager();
                pm.main(new String[]{generalPassword, purposePassword, "n"});
                g.ta.setText(pm.output(pm.findStart()));
            }
            catch(IOException ie){

            }
        }
        else{
            String passLength = g.askQuestions("How long should your password be?", new ArrayList<String>(){{add("1");add("2");add("3");add("4");add("5");add("6");add("7");add("8");add("9");add("10");add("11");add("12");add("13");add("14");add("15");add("16");add("17");add("18");add("19");add("20");}});
            String numNumerals = g.askQuestions("How many digits (at minimum) should be in your password?", new ArrayList<String>(){{add("1");add("2");add("3");add("4");add("5");}});
            String numCapitals = g.askQuestions("How many capital letters (at minimum) should be in your password?", new ArrayList<String>(){{add("1");add("2");add("3");add("4");add("5");}});
            String numLowercase = g.askQuestions("How many lowercase letters (at minimum) should be in your password?", new ArrayList<String>(){{add("1");add("2");add("3");add("4");add("5");}});
            String numSpecial = g.askQuestions("How many 'special' characters (!#$%) (at minimum) should be in your password?", new ArrayList<String>(){{add("1");add("2");add("3");add("4");add("5");}});
            try {
                PasswordManager pm = new PasswordManager();
                pm.main(new String[]{generalPassword, purposePassword, "y", passLength, numNumerals, numCapitals, numLowercase, numSpecial});
                g.ta.setText(pm.output(pm.findStart()));
            }
            catch(IOException ie){

            }
        }
    }

    public GUI() {
        super("Password Operator");
        setJMenuBar(menuBar);
        initMenu();
        {
            //======== mainMenu ========
            {
                mainMenu.setText("Menu");

                //---- quitOption ----
                quitOption.setText("Quit");
                mainMenu.add(quitOption);
            }
            menuBar.add(mainMenu);
        }
        setJMenuBar(menuBar);
        outerPanel= new JPanel(new GridLayout(1, 0));
        makePanel();
        getContentPane().add(outerPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
        pack();
        setVisible(true);
    }

    private void initMenu() {
        menuBar = new JMenuBar();
        mainMenu = new JMenu();
        final GUI[] disposable = {this};
        quitOption = new JMenuItem(new AbstractAction("Quit") {
            public void actionPerformed(ActionEvent e) {
                // Ask the user to confirm they wanted to do this
                int r = JOptionPane.showConfirmDialog(outerPanel,
                        new JLabel("Exit Password Operator?"), "Confirm Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (r == JOptionPane.YES_OPTION)
                    System.exit(0);

            }
        });
        ;
    }

    private void makePanel() {
        outerPanel.removeAll();
        textArea.setEditable(false);
        outerPanel.add(textArea, BorderLayout.SOUTH);
    }

    public String askQuestions(String s, ArrayList<String> options){
        if(options==null || options.isEmpty()){
            return null;
        }
        return (String) JOptionPane.showInputDialog(null, s, "Narrator", JOptionPane.PLAIN_MESSAGE, null, options.toArray(), options.get(0));
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        // Ask the user to confirm they wanted to do this
        int r = JOptionPane.showConfirmDialog(this,
                new JLabel("Exit Password Operator?"), "Confirm Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (r == JOptionPane.YES_OPTION)
            System.exit(0);

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

