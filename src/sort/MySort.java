package sort;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class MySort extends JFrame implements Runnable {
	private static final long serialVersionUID = -5746491244480180618L;
	private static final int SPACE = 1;
	
	boolean done = false;
	int[] nums = new int[1000];

	public MySort() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1800, 600);
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, 2000, 2000);
		for (int a = 0; a < 999; a++) {
			g.setColor(new Color(nums[a]));
			g.drawLine(a * SPACE, 90, a * SPACE, 90 + nums[a]);
		}
	}

	public void run() {
		Random r = new Random();

		for (int i = 0; i < 1000; i++) {
			nums[i] = r.nextInt(500);
			System.out.print(nums[i] + ",");
		}
		System.out.println("\n");

		for (int index1 = 999; index1 > 0; index1--) {
			for (int index2 = 0; index2 < index1; index2++) {
				if (nums[index2] < nums[index2 + 1]) {
					int buffer = nums[index2];
					nums[index2] = nums[index2 + 1];
					nums[index2 + 1] = buffer;
				}
			}

			if (index1 % 10 == 0) {
				System.out.println("repainting ...");
				repaint();
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}

		System.out.println("\nAfter sorting: \n");
		for (int i = 0; i < 1000; i++) {
			System.out.print(nums[i] + ",");
		}
	}

	public void update(Graphics g) {
		g.clearRect(0, 0, 1000, 1000);
		paint(g);
	}

	public static void main(String[] args) {
		MySort s = new MySort();
		new Thread(s).start();
	}
}
