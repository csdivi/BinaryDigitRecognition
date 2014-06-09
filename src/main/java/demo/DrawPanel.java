package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
    static int COLOR_BLACK = 16;
    static int COLOR_WHITE = 0;
    
    static int PIXEL_WIDTH = 10;
    static int NUMBER_OF_PIXELS = 8;
    int[][] data = new int[NUMBER_OF_PIXELS][NUMBER_OF_PIXELS];

    DrawPanel()
    {
        setPreferredSize(new Dimension(NUMBER_OF_PIXELS * PIXEL_WIDTH, NUMBER_OF_PIXELS * PIXEL_WIDTH));
        setSize(NUMBER_OF_PIXELS * PIXEL_WIDTH, NUMBER_OF_PIXELS * PIXEL_WIDTH);
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        clear();
    }

    public int[][] getData()
    {
        return data;
    }

    public void clear()
    {
        for (int i=0; i<NUMBER_OF_PIXELS; ++i)
            for (int j=0; j<NUMBER_OF_PIXELS; ++j)
                data[i][j] = COLOR_WHITE;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("?,");
        for (int i=0; i<NUMBER_OF_PIXELS; ++i)
        {
            for (int j=0; j<NUMBER_OF_PIXELS; ++j)
                sb.append(data[j][i]+",");
        }
        return sb.toString();
    }

    public void fillPixel(int x, int y)
    {
        if (x > NUMBER_OF_PIXELS * PIXEL_WIDTH || x < 0 || y > NUMBER_OF_PIXELS * PIXEL_WIDTH || y < 0) return;
        data[(x+PIXEL_WIDTH)/PIXEL_WIDTH - 1][(y+PIXEL_WIDTH)/PIXEL_WIDTH - 1] = COLOR_BLACK;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int i=0; i<NUMBER_OF_PIXELS; ++i)
        {
            for (int j=0; j<NUMBER_OF_PIXELS; ++j)
            {
                if (data[i][j] == COLOR_BLACK)
                {
                    drawPixel(g,i,j);
                }
            }
        }
    }

    private void drawPixel(Graphics g, int i, int j)
    {
        g.setColor(Color.BLACK);
        int x = i * PIXEL_WIDTH;
        int y = j * PIXEL_WIDTH;
        g.fillRect(x, y, PIXEL_WIDTH, PIXEL_WIDTH);
    }
}