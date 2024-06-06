
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FaalGUI extends JFrame implements ActionListener {


    private static final String GET_URL = "https://faal.spclashers.workers.dev/api";
    JButton getFaal;
    JButton backButton;

    public FaalGUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("فال حافظ");
        this.setFont(new Font("Arial", Font.PLAIN, 14));

        HomePage();

        this.setSize(700, 800);
        this.setVisible(true);

    }

    private static Faal getFaal() throws IOException {

        URL url = new URL("https://faal.spclashers.workers.dev/api");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        int indexOfI =0;
        for(int i =0; i<content.length(); i++) {
            if (content.charAt(i) == 'I') {
                indexOfI = i;
                continue;
            }
        }
        String poemBad = content.substring(9,indexOfI-3);
        //the following line replaces the \\r\\n in the given String and instead goes to the next line
        String poem = poemBad.replace("\\\\r\\\\n", "\n");
        String interpretation = content.substring(indexOfI+17, content.length()-2);
        Faal faal = new Faal();
        faal.setPoem(poem);
        faal.setInterpretation(interpretation);
        return faal;

    }


    public void HomePage(){

        JPanel HomePage = new JPanel();
        HomePage.setBackground(new Color(200, 236, 235));

        HomePage.setLayout(null);

        JLabel label2 = new JLabel("نیت کنید ...");
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setBounds(300, 150, 200, 50);

        JLabel label3 = new JLabel("در صورت اتمام نیت خود، روی دکمه‌ی زیر کلیک کنید.");
        label3.setFont(new Font("Arial", Font.PLAIN, 14));
        label3.setBounds(200, 350, 400, 50);

        getFaal = new JButton("دریافت فال");
        getFaal.setForeground(new Color(67, 134, 133));
        getFaal.setFont(new Font("Arial", Font.PLAIN, 14));
        getFaal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        getFaal.setBounds(265, 600, 200, 50);
        getFaal.addActionListener(this);

        HomePage.add(label2);
        HomePage.add(label3);
        HomePage.add(getFaal);

        this.add(HomePage);
    }


    public void FaalPage(String poem, String interpretation){
        JPanel faalPage = new JPanel();
        faalPage.setBackground(new Color(200, 236, 235));
        faalPage.setLayout(new BoxLayout(faalPage, BoxLayout.Y_AXIS));
        faalPage.setAlignmentX(Component.CENTER_ALIGNMENT);
        faalPage.setAlignmentY(Component.CENTER_ALIGNMENT);

        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel label = new JLabel("شعر");
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);



        JPanel poemPanel = new JPanel();
        poemPanel.setLayout(new GridBagLayout());
        poemPanel.setBackground(new Color(200, 236, 235));


        JTextArea label2 = new JTextArea(poem);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label2.setFont(new Font("Arial", Font.PLAIN, 14));

        label2.setEditable(false);
        label2.setAlignmentY(Component.CENTER_ALIGNMENT);
        label2.setBackground(null);



        JLabel label3 = new JLabel("تفسیر");
        label3.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        label3.setFont(new Font("Arial", Font.PLAIN, 20));


        JTextArea label4 = new JTextArea(interpretation);
        label4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label4.setEditable(false);
        label4.setBackground(null);
        label4.setLineWrap(true);
        label4.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        label4.setFont(new Font("Arial", Font.PLAIN, 14));


        backButton = new JButton("بازگشت");
        backButton.setForeground(new Color(67, 134, 133));
        backButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));

        backButton.addActionListener(this);

        faalPage.add(label);

        poemPanel.add(label2);
        faalPage.add(poemPanel);

        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));

        faalPage.add(label3);
        faalPage.add(Box.createRigidArea(new Dimension(0, 40)));

        faalPage.add(label4);
        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));

        faalPage.add(backButton);
        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));


        this.add(faalPage);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getFaal) {
            this.getContentPane().removeAll();

            try {
                Faal faal = getFaal();
                FaalPage(faal.getPoem(), faal.getInterpretation());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            this.revalidate();
            this.repaint();
        }

        if (e.getSource() == backButton) {
            this.getContentPane().removeAll();
            HomePage();
            this.revalidate();
            this.repaint();
        }
    }




}