package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

// 游戏面板     implements KeyListener, ActionListener

/**
 * 这里一节小蛇占的面积是25*25
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    // 定义蛇的数据结构
    int length; // 蛇的长度
    int[] snakeX = new int[600];  // 蛇的x坐标
    int[] snakeY = new int[500];  // 蛇的y坐标
    String direction; //初始方向
    // 游戏当前状态： 开始   结束
    boolean isStart = false; // 默认不开始

    // 游戏是否失败
    boolean isFail = false;  // 游戏失败状态

    //初始化 积分
    int score;

    //定时器 //以ms为单位 1000ms = 1s
    Timer timer = new Timer(100, this); //100毫秒执行一次！
    //Timer timer = new Timer(1000,this); //1秒执行一次！

    // 食物的坐标
    int foodx;
    int foody;
    Random random = new Random();


    // 构建构造器 ， 构造器调用方法
    public GamePanel() {
        init();
        //获得焦点 和 键盘事件
        this.setFocusable(true); // 获得焦点事件
        this.addKeyListener(this); // 获得键盘监听事件
    }

    // 初始化方法
    public void init() {
        length = 3; //初始化蛇的长度
        //蛇头坐标
        snakeX[0] = 100;
        snakeY[0] = 100;

        //第一个身体坐标
        snakeX[1] = 75;
        snakeY[1] = 100;

        //第二个身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100;
        direction = "R"; //初始化方向向右
        timer.start();//游戏开始，定时器就启动

        //初始化食物
        foodx = 25 + 25 * random.nextInt(34); //这里34 = 850/25  是边界限制
        foody = 25 + 25 * random.nextInt(24); //这里24 = 600/25  是边界限制

        // 初始化积分
        score = 0;
    }

    // 绘制面板
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);// 清屏作用
        this.setBackground(Color.WHITE);
        // 绘制静态面板
        Data.header.paintIcon(this, g, 25, 11); //头部广告栏

        g.fillRect(25, 75, 850, 600); // 默认的游戏界面

        // 画积分
        g.setColor(Color.WHITE);//设置画笔颜色
        g.setFont(new Font("黑体",Font.BOLD,18)); //设置字体大小
        g.drawString("长度" + length, 750,35); //绘制文字
        g.drawString("分数" + score,750,55);

        // 画食物
        Data.food.paintIcon(this, g, foodx, foody);


        //把小蛇画上去
        Data.right.paintIcon(this, g, snakeX[0], snakeY[0]); //蛇头初始化 向右
//        Data.body.paintIcon(this,g,snakeX[1],snakeY[2]); //蛇第一个身体坐标
//        Data.body.paintIcon(this,g,snakeX[2],snakeY[2]); //蛇第二个身体坐标
        // 因为身体会变长，所以 采用length，循环的方式画蛇
        for (int i = 1; i < length; i++) { //按蛇的长度 length
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]); //第一个身体的坐标
        }

        //控制蛇头的移动方向
        if (this.direction.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]); // 蛇头初始化右，通过方向来判断
        } else if (this.direction.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (this.direction.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (this.direction.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }


        // 游戏状态
        if (isStart == false) { // 游戏没有开始
            g.setColor(Color.WHITE); // 画笔 设为 白色
            // 设置字体
            g.setFont(new Font("黑体", Font.BOLD, 40)); // 设置字体
            g.drawString("按下空格开始游戏", 300, 300); //绘制字体
        }

        if (isFail == true) {
            g.setColor(Color.RED);
            g.setFont(new Font("黑体", Font.BOLD, 40)); //设置字体
            g.drawString("失败，按下空格重新开始", 300, 300);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // 键盘监听事件
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); //获得键盘事件是哪一个
        if (keyCode == KeyEvent.VK_SPACE) {//如果按下的是空格键
            if (isFail) {
                //重新开始
                isFail = false;
                init();
            } else {
                isStart = !isStart; //取反 开始变结束 结束变开始
            }
            repaint(); //重新绘制
        }
        /**
         * if(keyCode == KeyEvent.VK_UP && fx!="D"){//fx!="D"
         * 就是当小蛇的上一步是向下的时候，小蛇下一步不能向下
         */
        //小蛇移动
        if (keyCode == KeyEvent.VK_UP && direction != "D") {
            direction = "U";
        } else if (keyCode == KeyEvent.VK_DOWN && direction != "U") {
            direction = "D";
        } else if (keyCode == KeyEvent.VK_LEFT && direction != "R") {
            direction = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT && direction != "L") {
            direction = "R";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    //事件监听--- 需要通过固定事件来刷新， 1秒10次
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isFail == false) { //如果游戏是开始状态，就让小蛇动起来！

            //吃食物
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                length++; // 长度加1

                //分数+10
                score += 10;

                //再次随机食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }

            //右移
            for (int i = length - 1; i > 0; i--) {//后一节移动到前一节的位置 snakeX[1] = snakeX[0]
                snakeX[i] = snakeX[i - 1]; //向前移动一节
                snakeY[i] = snakeY[i - 1];
            }

            // 走向
            if (direction.equals("R")) {
                snakeX[0] = snakeX[0] + 25; //移动头
                if (snakeX[0] > 850) { //边界判断
                    snakeX[0] = 25;
                }
            } else if (direction.equals("L")) {
                snakeX[0] = snakeX[0] - 25; //移动头
                if (snakeX[0] < 25) { //边界判断
                    snakeX[0] = 850;
                }
            } else if (direction.equals("U")) {
                snakeY[0] = snakeY[0] - 25; //移动头
                if (snakeY[0] < 75) { //边界判断
                    snakeY[0] = 650;
                }
            } else if (direction.equals("D")) {
                snakeY[0] = snakeY[0] + 25; //移动头
                if (snakeY[0] > 650) { //边界判断
                    snakeY[0] = 75;
                }
            }
            // 失败判断，撞到自己就算失败
            for (int i = 1; i < length; i++){
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                    isFail = true;
                }
            }

            repaint();//重画页面
        }
    }
}

