package demo;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class BinaryDigitRecognizer extends JFrame
{
    static int COMPONENT_GAP = 20;
    String template;
    OneZeroClassifier oneZero;

    public BinaryDigitRecognizer() throws Exception
    {
        oneZero = new OneZeroClassifier();
        InputStream is = getClass().getResourceAsStream("/OneZero_template.arff");
        byte[] buf = new byte[is.available()];
        is.read(buf);
        template = new String(buf);
    }

    public void launch() throws Exception 
    {
        final DrawPanel panel = new DrawPanel();
        JButton clear = new JButton("Clear");
        JButton predict = new JButton("Predict");
        JLabel label = new JLabel("Write 0 or 1 in the below blue color box");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt)
            {
                int currentX = evt.getX();
                int currentY = evt.getY();
                panel.fillPixel(currentX, currentY);
                panel.repaint();
            }
        });

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                panel.clear();
                panel.repaint();
            }
        });

        predict.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int result;
                    String str = template + "\n" + panel.toString();
                    Instances pred = DataSource.read(new ByteArrayInputStream(str.getBytes()));

                    pred.setClassIndex(0);
                    result = (int)oneZero.getClassifier().classifyInstance(pred.firstInstance());
                    double prob[] = oneZero.getClassifier().distributionForInstance(pred.firstInstance());
                    System.out.println(panel.toString());
                    System.out.println("The result is: " + result + ", with "+prob[result]*100+"% confidence.");
                    JOptionPane.showMessageDialog(BinaryDigitRecognizer.this, "The written digit is: " + result,
                            "Prediction", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        Container cc = getContentPane();
        GroupLayout gl = new GroupLayout(cc);
        cc.setLayout(gl);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                    .addGap(COMPONENT_GAP)
                    .addComponent(label)
                    .addGap(COMPONENT_GAP))
                .addComponent(panel, Alignment.CENTER, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(Alignment.CENTER, gl.createSequentialGroup()
                    .addComponent(clear)
                    .addGap(COMPONENT_GAP)
                    .addComponent(predict)));

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(COMPONENT_GAP)
                .addComponent(label)
                .addGap(COMPONENT_GAP)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(COMPONENT_GAP)
                .addGroup(gl.createParallelGroup()
                        .addComponent(clear)
                        .addComponent(predict))
                        .addGap(COMPONENT_GAP));

        pack();
        setVisible(true);
    }
    public static void main(String[] args) throws Exception
    {
        BinaryDigitRecognizer demo = new BinaryDigitRecognizer();
        demo.launch();
    }
}

