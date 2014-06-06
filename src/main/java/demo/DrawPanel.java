package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
    int[][] data = new int[8][8];

    DrawPanel()
    {
        setPreferredSize(new Dimension(80,80));
        setSize(80, 80);
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        clear();
    }

    public int[][] getData()
    {
        return data;
    }

    public void clear()
    {
        for (int i=0; i<8; ++i)
            for (int j=0; j<8; ++j)
                data[i][j] = 0;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("?,");
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
                sb.append(data[j][i]+",");
        }
        return sb.toString();
    }

    public void fillPixel(int x, int y)
    {
        if (x > 80 || x < 0 || y > 80 || y < 0) return;
        data[(x+9)/10 - 1][(y+9)/10 - 1] = 16;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                if (data[i][j] == 16)
                {
                    drawPixel(g,i,j);
                }
            }
        }
    }

    private void drawPixel(Graphics g, int i, int j)
    {
        g.setColor(Color.BLACK);
        int x = i * 10;
        int y = j * 10;
        g.fillRect(x, y, 10, 10);
    }
}