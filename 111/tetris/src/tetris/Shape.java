package tetris;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Shape {
	public static  boolean[][] get_random_shape() {
		return  shape[new Random().nextInt(7)];
	}
    public  static boolean [][][] shape = {
    		{{false,true,false,false},
             {false,true,false,false},
             {false,true,false,false},
             {false,true,false,false}},//长条
    		
    		{{false,false,false,false},
             {false,true,false,false},
             {true,true,true,false},
             {false,false,false,false}},//反T
    		
    		{{false,false,false,false},
             {false,true,false,false},
             {false,true,true,true},
             {false,false,false,false}},//L
           
    		{{false,false,false,false},
             {false,false,false,true},
             {false,true,true,true},
             {false,false,false,false}},//反L
            
    		{{false,false,false,false},
             {false,true,true,false},
             {false,true,true,false},
             {false,false,false,false}},//正方形
            
    		{{false,false,false,false},
             {false,true,true,false},
             {false,false,true,true},
             {false,false,false,false}},//Z
    		
            {{false,false,false,false},
             {false,true,true,false},
             {true,true,false,false},
             {false,false,false,false}}//反Z
    };
    

}
