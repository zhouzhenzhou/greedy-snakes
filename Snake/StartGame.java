package Snake;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ///正常游戏界面都在Pannel上
        frame.add(new GamePanel());


        frame.setResizable(false);//窗口大小不可变
        frame.setBounds(10,10,900,720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
