//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.JFrame;
//
//public class MainClass {
//	public static void main(String[] args) {
//		MainCanvas meuCanvas = new MainCanvas();
//		
//		JFrame f = new JFrame();
//		f.setSize(640, 480);
//		f.setVisible(true);
//		f.getContentPane().add(meuCanvas);
//		
//	
//		f.addWindowListener(new WindowAdapter() {
//		    @Override
//		    public void windowClosing(WindowEvent e) {
//		        System.exit(0);
//		    }  
//		});
//		
//		meuCanvas.start();
//	}
//}

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;


public class MainClass {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Bresenham Line Drawing");
		MainCanvas canvas = new MainCanvas();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(new Dimension(640, 480));
	}
}
