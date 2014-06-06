package demo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class BinaryDigitRecognizer extends JFrame
{

    OneZeroClassifier oneZero;

    public BinaryDigitRecognizer() throws Exception
    {
        oneZero = new OneZeroClassifier();
    }

    public void launch() throws Exception 
    {
        final DrawPanel panel = new DrawPanel();
        JButton clear = new JButton("Clear");
        JButton predict = new JButton("Predict");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setPreferredSize(new Dimension(80,80));

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
                byte[] buf = null;
                try {

                    InputStream is = getClass().getResourceAsStream("/OneZero_template.arff");
                    buf = new byte[is.available()];
                    is.read(buf);
                    String str = new String(buf);
                    str = str + "\n" + panel.toString();
                    Instances pred = DataSource.read(new ByteArrayInputStream(str.getBytes()));
                    pred.setClassIndex(0);
                    double fDistribution = oneZero.getClassifier().classifyInstance(pred.firstInstance());
                    System.out.println(fDistribution + " = Pred");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        Container cc = getContentPane();
        GroupLayout gl = new GroupLayout(cc);
        cc.setLayout(gl);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl.createSequentialGroup()
                    .addComponent(clear)
                    .addComponent(predict)));

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(clear)
                        .addComponent(predict)));

        pack();
        setVisible(true);
    }
    public static void main(String[] args) throws Exception
    {
        BinaryDigitRecognizer demo = new BinaryDigitRecognizer();
        demo.launch();
    }
}

