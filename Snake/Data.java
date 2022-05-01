//package Snake;
//import javax.swing.*;
//import java.net.URL;
//
//// 数据中心
//public class Data {
//    // 相对路径
//    public static URL headerURL = Data.class.getResource("statics/header.png");
//    public static ImageIcon header = new ImageIcon(headerURL);
//
//    public static URL upURL = Data.class.getResource("statics/up.png");
//    public static URL downURL = Data.class.getResource("statics/down.png");
//    public static URL leftURL = Data.class.getResource("statics/left.png");
//    public static URL rightURL = Data.class.getResource("statics/right.png");
//
//    public static ImageIcon up = new ImageIcon(upURL);
//    public static ImageIcon down= new ImageIcon(downURL);
//    public static ImageIcon left = new ImageIcon(leftURL);
//    public static ImageIcon right = new ImageIcon(rightURL);
//
//    public static URL bodyURL = Data.class.getResource("static/body.png");
//    public static URL foodURL = Data.class.getResource("static/food.png");
//
//    public static ImageIcon body = new ImageIcon(bodyURL);
//    public static ImageIcon food = new ImageIcon(foodURL);
//}
package Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

//数据中心
public class Data {
    //   ----"/"代表当前项目的意思
    //头部图片
    public static URL headerUrl = Data.class.getResource("statics/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);
    //头部: 上下左右
    public static URL upUrl = Data.class.getResource("statics/up.png");
    public static URL downUrl = Data.class.getResource("statics/down.png");
    public static URL leftUrl = Data.class.getResource("statics/left.png");
    public static URL rightUrl = Data.class.getResource("statics/right.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);
    //身体
    public static URL bodyUrl = Data.class.getResource("statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);

    //食物
    public static URL foodUrl = Data.class.getResource("statics/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}
