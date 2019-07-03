package org.hl.nnagent.bug;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

public class BugDraw2D implements Observer {

    private static final int PIX_SIZE = 4;

    private Graphics2D canvasGraphics;

    public BugDraw2D() {
        JFrame myFrame = new JFrame();
        myFrame.setSize(800, 600);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//表示能够叉掉

        // 局中显示
        myFrame.setLocationRelativeTo(null);
        myFrame.setLayout(new BorderLayout());// 向左对齐,水平间距是20，垂直间距为40像素

        // 按钮
        JPanel btnPanel = new JPanel();
        myFrame.getContentPane().add(btnPanel, BorderLayout.NORTH);

        JPanel canvasPanel = new JPanel();
        canvasPanel.setSize(500, 500);
        myFrame.getContentPane().add(canvasPanel, BorderLayout.CENTER);

        btnPanel.setLayout(new FlowLayout());
        JButton btn = new JButton("1bug 1food");
        btnPanel.add(btn);
        btn.addActionListener((e) -> {
            BugEmulation.oneBugOneFood(this);
        });

        btn = new JButton("1bug *food");
        btnPanel.add(btn);
        btn.addActionListener((e) -> {
            BugEmulation.oneBugManyFood(this);
        });

        btn = new JButton("*bug *food");
        btnPanel.add(btn);
        btn.addActionListener((e) -> {
            BugEmulation.manyBugManyFood(this);
        });

        myFrame.setVisible(true);
        canvasGraphics = (Graphics2D) canvasPanel.getGraphics();
    }

    private static Rectangle2D makePix(int x, int y) {
        return new Rectangle2D.Double(x * PIX_SIZE, y * PIX_SIZE, PIX_SIZE, PIX_SIZE);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof BugMap)) {
            return;
        }
        BugMap bugMap = (BugMap) o;
        if (bugMap.getMarks().size() == 0) {
            // clear map
            canvasGraphics.clearRect(0, 0, bugMap.getWidth() * PIX_SIZE, bugMap.getHeight() * PIX_SIZE);
            return;
        }

        if (!(arg instanceof BugMark)) {
            return;
        }
        BugMark mark = (BugMark) arg;
        Rectangle2D pix = makePix(mark.x, mark.y);
        canvasGraphics.setColor(mark.type.getColor());
        canvasGraphics.fill(pix);
    }
}
