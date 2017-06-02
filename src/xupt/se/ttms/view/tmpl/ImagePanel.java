package xupt.se.ttms.view.tmpl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	public ImagePanel(String imgName) {
		super();
		setOpaque(true);
		image = Toolkit.getDefaultToolkit().getImage(imgName);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		if (image != null) {
			int height = image.getHeight(this);
			int width = image.getWidth(this);
			if (height != -1 && height > getHeight())
				height = getHeight();
			if (width != -1 && width > getWidth())
				width = getWidth();
			int x = (int) (((double) (getWidth() - width)) / 2.0);
			int y = (int) (((double) (getHeight() - height)) / 2.0);
			g.drawImage(image, x, y, width, height, this);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBackground(Color.GREEN);
		frame.setBounds(200, 200, 1200, 800);
		frame.setLayout(null);
		ImagePanel panel = new ImagePanel("resource/image/header.jpg");
		panel.setBounds(0,0,1200,200);
		panel.setLayout(null);
		JButton b = new JButton("按钮");
		b.setBounds(200, 600, 200, 200);
		b.setLayout(null);
		frame.add(b);
		frame.add(panel);
		frame.setVisible(true);

	}

}
