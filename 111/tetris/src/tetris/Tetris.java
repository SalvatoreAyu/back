package tetris;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.PrimitiveIterator.OfDouble;

public class Tetris extends JPanel implements Runnable {
	File file = new File("src/record.txt");
	Object lockobj = new Object();
	JButton pause = new JButton("暂停(P)");
	JButton restart = new JButton("重新开始(C)");
	JButton record = new JButton("最高记录(R)");
	JButton fast = new JButton("当前难度：简易(F)");
	JButton help = new JButton("操作说明(H)");
	int width = 15;
	int height = 25;
	int size = 25;
	boolean end = false;
	boolean Pause = false;
	int speed = 500;
	int score = 0;
	String recordfile;
	int recordnow = 0;
	boolean[][] cell;
	boolean[][] nextcell;
	boolean[][] map = new boolean[25][15];
	Point point = new Point();
	int yes;
	int count_block = 0;
	String select;
	Date date = new Date();
	ImageIcon iconmap = new ImageIcon("src/blocks.jpg");
	ImageIcon ba = new ImageIcon("src/back1.png");
	ImageIcon ba2 = new ImageIcon("src/ba5.png");
	ImageIcon fenge = new ImageIcon("src/line.gif");
	ImageIcon fenge1 = new ImageIcon("src/line2.gif");
	ImageIcon rbi = new ImageIcon("src/rbackr.png");
	Image line = fenge.getImage();
	Image blocks1 = new ImageIcon("src/blocks1.jpg").getImage();
	Image blocks2 = new ImageIcon("src/blocks2.jpg").getImage();
	Image blocks3 = new ImageIcon("src/blocks3.jpg").getImage();
	Image firedragon = new ImageIcon("src/firedragon.gif").getImage();
	Image purdragon = new ImageIcon("src/purgragon.gif").getImage();
	Image fox = new ImageIcon("src/fox.gif").getImage();
	Image lovelybird = new ImageIcon("src/lovelybird.gif").getImage();
	Image duck = new ImageIcon("src/duck.gif").getImage();
	Image wugui = new ImageIcon("src/wugui.gif").getImage();
	Image jienigui = new ImageIcon("src/jienigui.gif").getImage();
	Image lion = new ImageIcon("src/lion.gif").getImage();
	Image scorepic = new ImageIcon("src/score.png").getImage();
	Image line1 = fenge1.getImage();
	Image back2 = ba2.getImage();
	Image back = ba.getImage();
	Image rImageback = rbi.getImage();
	Image image = iconmap.getImage();

	public Tetris() {
		help.setBounds(375, 330, 171, 50);
		fast.setBounds(375, 380, 171, 50);
		record.setBounds(375, 430, 171, 50);
		pause.setBounds(376, 525, 170, 50);
		restart.setBounds(376, 575, 170, 50);
		fast.setContentAreaFilled(false);// 填充透明
		fast.setFocusPainted(false);// 美化不显示方框
		record.setContentAreaFilled(false);
		record.setFocusPainted(false);
		help.setContentAreaFilled(false);
		help.setFocusPainted(false);
		pause.setContentAreaFilled(false);
		pause.setFocusPainted(false);
		restart.setContentAreaFilled(false);
		restart.setFocusPainted(false);
		help.setMnemonic(java.awt.event.KeyEvent.VK_H);
		fast.setMnemonic(java.awt.event.KeyEvent.VK_F);
		record.setMnemonic(java.awt.event.KeyEvent.VK_R);
		pause.setMnemonic(java.awt.event.KeyEvent.VK_P);
		restart.setMnemonic(java.awt.event.KeyEvent.VK_C);
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restart.setFocusable(false);
				play();
			}
		});
		try {
			FileReader fir = new FileReader("src/record.txt");
			char[] data = new char[23]; // 定义char数组
			int length = 0;
			while ((length = fir.read()) > 0) { // 循环读取文件中的数据
				recordfile = new String(data, 0, length); // 根据读取文件的内容创建String 对象
			}
			String[] parts = recordfile.split(",");
			recordnow = Integer.valueOf(parts[0]);// 用逗号分割得到的结果
			fir.close(); // 关闭流
		} catch (Exception e2) {
			System.out.println(e2);
		}
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Pause) {
					suspend(); // 阻塞进程直至resume调用
				} else {
					resume();// 进程恢复
				}
			}
		});

		record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				suspend();
				System.out.println(recordnow);
				JOptionPane.showMessageDialog(null, "最高记录" + recordfile);
			}

		});
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				suspend();
				JOptionPane.showMessageDialog(null, "↑旋转   ←向左    →向右\n↓加速掉落    space直接掉落", "操作说明",
						JOptionPane.QUESTION_MESSAGE); // ？图标
			}
		});

		fast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				suspend();
				Object[] possibleValues = { "简单", "中级", "困难" };// 难度定义 为后面选择框做铺垫
				Object selectedValue = JOptionPane.showInputDialog(null, "请选择游戏难度", "", JOptionPane.PLAIN_MESSAGE, null,
						possibleValues, possibleValues[0]); // 选择框
				if (selectedValue == null) {
					System.out.println("用户取消选择");
				} else {
					select = (String) selectedValue;
					if (select.equals("简单")) {
						speed = 500;
					} else if (select.equals("中级")) {
						speed = 350;
					} else {
						speed = 200;
					}

				}
				System.out.println("当前速度:" + speed);
				if (speed == 500)
					fast.setText("当前难度：简单(F)");
				if (speed == 350)
					fast.setText("当前难度：中级(F)");
				if (speed == 200)
					fast.setText("当前难度：困难(F)");
				fast.setFocusable(false);
			}
		});
		add(record);
		add(fast);
		add(help);
		add(pause);
		add(restart);
		addKeyListener(keylistener);
		addMouseListener(mouseListener);
		setLayout(null);
		setFocusable(true);
		play(); // 正式开始
	}

	public void suspend() {
		Pause = true;
		pause.setText("继续(P)");// 修改按钮文本为继续
		pause.setFocusable(false);
	}

	public void resume() {
		Pause = false;
		requestFocus();
		pause.setText("暂停(P)");
		synchronized (lockobj) {
			lockobj.notify();
		}
	}

	public int random_num() {
		int x = 11;
		Random r = new Random();
		return r.nextInt(x) + 1;
	}

	public void play() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j] = false;
			}
		}
		point = new Point(random_num(), 0); // 获得随机数 使方块从顶部随机位置掉落
		score = 0; // 初始化分数
		end = false; // 初始化未结束
		cell = get_newcell();
		nextcell = get_newcell();
		repaint();
		Thread thread = new Thread(this); // 创建线程
		thread.start(); // 进入run
	}

	// 下落线程
	public void run() {
		synchronized (lockobj) {
			while (!end) {
				try {
					Thread.sleep(speed); // speed的大小来控制掉落的速度
				} catch (InterruptedException e) {
					System.out.println(e);
				}
				while (Pause) {
					try {
						lockobj.wait(); // 进程等待
					} catch (InterruptedException e) {
						System.out.println(e);
					}
				}
				down(); // 否则不停止降落
			}
		}
		// 游戏结束
		if (score > recordnow) {
			yes = JOptionPane.showConfirmDialog(null, "恭喜打破最高记录,得分:" + score + ",是否继续游戏", "game over",
					JOptionPane.YES_NO_OPTION); // 选择是否继续
			try {
				FileWriter fw = new FileWriter(file); // 创建FileWriter对象
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm"); // 日期格式
				String s = String.valueOf(score);
				System.out.println(s);
				fw.write(s + ",创建于" + simpleDateFormat.format(date)); // 向文件写入数据
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			yes = JOptionPane.showConfirmDialog(null, "得分:" + score + ",是否继续游戏", "game over",
					JOptionPane.YES_NO_OPTION);
		}
		if (yes == JOptionPane.YES_OPTION) {
			play();
		} else {
			System.exit(0);
		}

	}

	public Image getImage1() {
		return blocks1;
	}

	public Image getImage2() {
		return blocks2;
	}

	public Image getImage3() {
		return blocks3;
	}

	public Image getImage0() {
		return image;
	}

	public void paintleft(Graphics g) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cell[i][j]) {
//					System.out.println(point.x + " " + point.y);
					if (count_block % 3 == 0)
						g.drawImage(getImage0(), (point.x + j) * size, (point.y + i) * size, this);
					if (count_block % 3 == 1)
						g.drawImage(getImage1(), (point.x + j) * size, (point.y + i) * size, this);
					if (count_block % 3 == 2)
						g.drawImage(getImage3(), (point.x + j) * size, (point.y + i) * size, this);
				}
			}
		}
	}

	public void paintright(Graphics g) {
		g.setFont(new Font("宋体", Font.PLAIN, 13));
		g.drawString("下一个方块", 403, 30);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (this.nextcell[i][j]) {
					if (count_block % 3 == 0)
						g.drawImage(getImage1(), 400 + j * size, 50 + i * size, this);
					if (count_block % 3 == 1)
						g.drawImage(getImage3(), 400 + j * size, 50 + i * size, this);
					if (count_block % 3 == 2)
						g.drawImage(getImage0(), 400 + j * size, 50 + i * size, this);
				}
			}
		}
		g.setFont(new Font("宋体", Font.PLAIN, 60));
		g.setColor(Color.blue);
		g.drawString("" + score, 420, 295);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.drawImage(rImageback, 375, 0, this);
		g.drawImage(back, 0, 0, this);
		g.drawImage(line1, 378, 150, this);
		g.drawImage(line, 378, 490, this);
		g.drawImage(scorepic, 390, 175, this);
		g.drawImage(firedragon, 375, 8, this);
		g.drawImage(purdragon, 375, 180, this);
		g.drawImage(wugui, 380, 438, this);
		g.drawImage(duck, 380, 382, this);
		g.drawImage(lovelybird, 380, 340, this);
		g.drawImage(jienigui, 382, 530, this);
		g.drawImage(lion, 380, 580, this);
		g.drawImage(fox, 380, 300, this);
//		g.drawImage(line, 375, 292, this);
//		g.drawImage(line, 375, 372, this);
//		g.drawImage(line, 375, 452, this);
//		g.drawImage(line, 375, 532, this);
		paintleft(g);
		paintright(g);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (map[i][j]) {
					g.drawImage(getImage2(), j * size, i * size, this); // 如果为真 则绘制
				}
			}
		}

	}

	public boolean[][] get_newcell() {
		return Shape.get_random_shape(); // 获得随机形状
	}

	public boolean[][] rotatecell() {
		boolean[][] rotate = new boolean[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				rotate[j][3 - i] = cell[i][j];
			}
		}
		return rotate;
	}

	public boolean touch(Point point, boolean[][] cell) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cell[i][j]) {
					if (point.y + i > height - 1 || point.x + j < 0 || point.x + j > width - 1) {
//						System.out.println("test触碰边界");
						return true;
					}
					if (map[point.y + i][point.x + j]) {
//						System.out.println("test触碰其他方块");
						return true;
					}
				}
			}
		}
		return false;
	}

	public void down() {
		if (touch(new Point(point.x, point.y + 1), cell)) { // 判断是否固定
//			System.out.println(point.x+"  "+point.y);
			count_block++;
			fix_cell(); // 判断消行
			cell = nextcell;
			nextcell = get_newcell();
			point = new Point(random_num(), 0);
			if (touch(point, cell)) {
				end = true;
			}
			repaint();
		} else {
			point.y++;
			repaint();
		}
	}

	public void deleteline() {
		int i = 0, j = 0, k = 0, row = 0, line = 0;
		for (i = 24; i >= 0; i--) {
			for (j = 0; j < 15; j++) {
				if (!map[i][j]) { // 如果有一列未被填满 则退出循环
					break;
				}
			}
			if (j == 15) {
				line++; // 需要删行的行数
				if (line == 1) {
					row = i; // row删行从哪行开始删
				}
			}
		}
		if (line != 0) {
			System.out.println("消除" + line + "行,从 " + row + "行数开始");
			for (j = row; j - line >= 0; j--) {
				for (k = 0; k < 15; k++) {
					map[j][k] = map[j - line][k];
				}
			}
			score = score + line * 100;
		}
	}

	public void fix_cell() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cell[i][j]) {
					map[point.y + i][point.x + j] = true;
				}
			}
		}
		deleteline();
	}

	MouseListener mouseListener = new MouseListener() {
		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			if (e.getButton() == e.BUTTON3) {
				System.out.println(e.getX() + "," + e.getY());
			}
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
		}
	};

	KeyListener keylistener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN: {
				down(); // 加速下降
				break;
			}
			case KeyEvent.VK_RIGHT: {
				Point testPoint = new Point(point.x + 1, point.y);
				if (touch(testPoint, cell)) { // 判断是否触碰边界
					break;
				} else {
					point.x++;
					break;
				}
			}
			case KeyEvent.VK_LEFT: {
				Point testPoint = new Point(point.x - 1, point.y);
				if (touch(testPoint, cell)) {
					break;
				} else {
					point.x--;
					break;
				}
			}
			case KeyEvent.VK_UP: {
				if (touch(point, rotatecell())) { // 调用的是旋转后的cell 判断是否触碰边界
					break;
				} else {
					cell = rotatecell();
					break;
				}
			}

			case KeyEvent.VK_SPACE: {
				do {
					down();
				} while (!(touch(new Point(point.x, point.y + 1), cell))); // 只要未触碰到下部的其他方块 一直下降

				break;
			}
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	};

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Tetris());
		frame.setSize(560, 660);
		frame.setTitle("Tetris game");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
