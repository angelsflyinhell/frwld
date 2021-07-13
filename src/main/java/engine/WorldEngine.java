package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WorldEngine extends JFrame {

    int size = 70;
    boolean[][] cellsMap;
    JButton[][] cells;

    public WorldEngine() {
        Random r = new Random();

        cellsMap = new boolean[size][size];
        cells = new JButton[size][size];
        setSize(500, 500);
        setLayout(new GridLayout(size, size));

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cellsMap[x][y] = r.nextInt(100) < 90;
                JButton temporary = new JButton();
                if(cellsMap[x][y])
                    temporary.setBackground(Color.GREEN);
                else
                    temporary.setBackground(Color.BLACK);

                add(temporary);
                cells[x][y] = temporary;
            }
        }

        runtime(100);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void runtime(int tickspeed) {
        Timer ticks = new Timer(tickspeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean[][] temp = new boolean[size][size];

                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        int count = countNeighbours(x, y);

                        if(cellsMap[x][y]) {
                            if(count < 2)
                                temp[x][y] = false;
                            if(count == 3 || count == 2)
                                temp[x][y] = true;
                            if(count > 3)
                                temp[x][y] = false;
                        }else {
                            if(count == 3)
                                temp[x][y] = true;
                        }
                    }
                }

                cellsMap = temp;

                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        if(cellsMap[x][y])
                            cells[x][y].setBackground(Color.RED);
                        else
                            cells[x][y].setBackground(Color.BLACK);
                    }
                }
            }
        });

        ticks.start();
    }
    
    private int countNeighbours(int x, int y) {
        int count = 0;

        for (int i = x - 1; i < x + 1; i++) {
            for (int j = y - 1; j < y + 1; j++) {
                try {
                    if(cellsMap[i][j]) {
                        count++;
                    }
                }catch (Exception e) {

                }
            }
        }

        if(cellsMap[x][y])
            count--;

        System.out.println(count);
        return count;
    }

}
