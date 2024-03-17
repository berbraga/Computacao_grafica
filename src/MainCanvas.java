/*import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainCanvas extends JPanel implements Runnable{
	int W = 640;
	int H = 480;
	
	Thread runner;
	boolean ativo = true;
	int paintcounter = 0;
	
	BufferedImage imageBuffer;
	byte bufferDeVideo[];
	
	Random rand = new Random();
	
	byte memoriaPlacaVideo[];
	short paleta[][];
	
	int framecount = 0;
	int fps = 0;
	
	Font f = new Font("", Font.PLAIN, 30);
	
	int clickX = 0;
	int clickY = 0;
	int mouseX = 0;
	int mouseY = 0;
	
	int pixelSize = 0;
	int Largura = 0;
	int Altura = 0;
	
	BufferedImage imgtmp = null;
	
	float posx = 00;
	float posy = 00;
	
	boolean LEFT = false;
	boolean RIGHT = false;
	boolean UP = false;
	boolean DOWN = false;
	
	float filtroR = 1;
	float filtroG = 1;
	float filtroB = 1;
	
	public MainCanvas() {
		setSize(640,480);
		setFocusable(true);
		
		Largura = 640;
		Altura = 480;
		
		pixelSize = 640*480;
		
		
//		try {
//			imgtmp = ImageIO.read(getClass().getResource("fundo.jpg"));
//			System.out.println(""+imgtmp.toString());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		

		imgtmp = loadImage("fundo.jpg");
		if (imgtmp == null) {
			System.out.println("Falha ao carregar a imagem. Verifique o caminho e tente novamente.");
			// Talvez você queira parar a execução ou carregar uma imagem padrão
		}
		imageBuffer = new BufferedImage(640,480, BufferedImage.TYPE_4BYTE_ABGR);
		//imageBuffer.getGraphics().drawImage(imgtmp, 0, 0, null);
		
		
		bufferDeVideo = ((DataBufferByte)imageBuffer.getRaster().getDataBuffer()).getData();
		
		System.out.println("Buffer SIZE "+bufferDeVideo.length );
		
		
//		File f = new File("t1.bmp");
//		try {
//			DataInputStream din = new DataInputStream(new FileInputStream(f));
//			byte b[] = new byte[128];
//			int quant = 0;
//			int cont = 0;
//			while((quant = din.read(b))>=0) {
//				for(int i = 0; i < quant;i++) {
//					System.out.print(""+(b[i]&0xff)+" ");
//				}
//				System.out.println();
//				cont++;
//				if(cont==10) {
//					break;
//				}
//			}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		for(int i = 0; i < H;i++) {
//			for(int j = 0; j < W;j++) {
//				int pos = (i*W*4)+(j*4);
//				
//				int soma = bufferDeVideo[pos+1]&0xff;
//				soma += bufferDeVideo[pos+2]&0xff;
//				soma += bufferDeVideo[pos+3]&0xff;
//				
//				int media = soma/3;
//				//System.out.println(""+media);
//				
//				bufferDeVideo[pos+1] = (byte)(Math.min((media*20)/100,255)&0x00ff);
//				bufferDeVideo[pos+2] = (byte)(Math.min((media*60)/100,255)&0x00ff);
//				bufferDeVideo[pos+3] = (byte)(Math.min((media*20)/100,255)&0x00ff);
//			}
//		}
		
		//memoriaPlacaVideo = new byte[W*H];
		
		
//		paleta = new short[255][3];
//
//		for(int i = 0; i < 255;i++){
//			paleta[i][0] = (short)rand.nextInt(255);
//			paleta[i][1] = (short)rand.nextInt(255);
//			paleta[i][2] = (short)rand.nextInt(255);
//
//		}
		
		//Seta Bugfeer com noise
//		for(int i = 0; i < bufferDeVideo.length;i+=4){
//			int r = rand.nextInt(255);
//			int g = rand.nextInt(255);
//			int b = rand.nextInt(255);
//
//			bufferDeVideo[i] = (byte)0x00ff;
//			bufferDeVideo[i+1] = (byte)(0x00ff&b);
//			bufferDeVideo[i+2] = (byte)(0x00ff&g);
//			bufferDeVideo[i+3] = (byte)(0x00ff&r);
//		}
		
//		// 100,20 200,20
//		for(int i = 0; i < 100;i++){
//			int x = 100+i;
//			int y = 20;
//			int bt = x*4+y*640*4;
//			bufferDeVideo[bt] = (byte)0x00ff;
//			bufferDeVideo[bt+1] = (byte)0;
//			bufferDeVideo[bt+2] = (byte)0;
//			bufferDeVideo[bt+3] = (byte)0x00ff;
//		}
		
//		for(int y = 0; y < H;y++){
//			for(int x = 0; x < W;x++){
//				memoriaPlacaVideo[x+y*W] = (byte)((y%255)&0x00ff);
//			}
//		}
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_W) {
					UP = false;
				}
				if(key == KeyEvent.VK_S) {
					DOWN = false;
				}
				if(key == KeyEvent.VK_A) {
					LEFT = false;
				}
				if(key == KeyEvent.VK_D) {
					RIGHT = false;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				//System.out.println("CLICO "+key);
				if(key == KeyEvent.VK_W) {
					UP = true;
				}
				if(key == KeyEvent.VK_S) {
					DOWN = true;
				}
				if(key == KeyEvent.VK_A) {
					LEFT = true;
				}
				if(key == KeyEvent.VK_D) {
					RIGHT = true;
				}
			}
		});		
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				clickX = e.getX();
				clickY = e.getY();
				
				System.out.println("CLICO ");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mouseX = arg0.getX();
				mouseY = arg0.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		
	}
	private void drawImageToBuffer(BufferedImage image,int x,int y, float fr, float fg, float fb) {
		byte[] imgBuffer = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		
		
		int iw = image.getWidth();
		int ih = image.getHeight();
		
		for(int yi = 0; yi < ih; yi++) {
			for(int xi = 0; xi < iw; xi++) {
				int pixi = yi*iw*4 + xi*4;
				int pixb = (yi+y)*W*4 + (xi+x)*4;
				bufferDeVideo[pixb] = imgBuffer[pixi];
				
				//BW
//				int soma = (imgBuffer[pixi+1]&0xff) + (imgBuffer[pixi+2]&0xff) + (imgBuffer[pixi+3]&0xff);
//				int res = (int)(soma/3);
//				
//				bufferDeVideo[pixb+1] = (byte)(res&0xff);
//				bufferDeVideo[pixb+2] = (byte)(res&0xff);
//				bufferDeVideo[pixb+3] = (byte)(res&0xff);
				
				
				int b = (imgBuffer[pixi+1]&0xff);
				int g =	(imgBuffer[pixi+2]&0xff);
				int r = (imgBuffer[pixi+3]&0xff);
				
				b = (int)(b*fb);
				g = (int)(g*fg);
				r = (int)(r*fr);
				
				b = Math.min(255, b);
				g = Math.min(255, g);
				r = Math.min(255, r);
				
				bufferDeVideo[pixb+1] = (byte)(b&0xff);
				bufferDeVideo[pixb+2] = (byte)(g&0xff);
				bufferDeVideo[pixb+3] = (byte)(r&0xff);
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		
		for(int i = 0; i < bufferDeVideo.length; i++) {
			bufferDeVideo[i] = 0;
		}
		
		drawImageToBuffer(imgtmp,(int)posx,(int)posy,filtroR,filtroG,filtroB);
		
		//desenhaLinhaHorizontal((int)posx-100,(int)posy,200);
		
		//desenhaLinhaVertical((int)posx,(int)posy-100,200);
		
		
		
		//desenhaLinhaVertical(300,200,200);
		
		// TODO Auto-generated method stub
		//super.paint(g);
		
//		for(int i = 0; i < bufferDeVideo.length;i+=4){
//			int rr = rand.nextInt(255);
//			int gg = rand.nextInt(255);
//			int bb = rand.nextInt(255);
//			
//			bufferDeVideo[i] = (byte)0x00ff;
//			bufferDeVideo[i+1] = (byte)(0x00ff&bb);
//			bufferDeVideo[i+2] = (byte)(0x00ff&gg);
//			bufferDeVideo[i+3] = (byte)(0x00ff&rr);
//		}
		
//		for(int i = 0; i < memoriaPlacaVideo.length;i++){
//			int bufferindex = i*4;
//			bufferDeVideo[bufferindex] = (byte)0x00ff;
//			bufferDeVideo[bufferindex+1] = (byte)(paleta[memoriaPlacaVideo[i]&0x00ff][2]&0x00ff);
//			bufferDeVideo[bufferindex+2] = (byte)(paleta[memoriaPlacaVideo[i]&0x00ff][1]&0x00ff);
//			bufferDeVideo[bufferindex+3] = (byte)(paleta[memoriaPlacaVideo[i]&0x00ff][0]&0x00ff);
//		}
		
		g.setFont(f);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 640, 480);
//		g.setColor(Color.black);
//		g.drawLine(0, 0, 640, 480);
		
		g.drawImage(imageBuffer,0,0,null);
		
		//g.setColor(Color.BLUE);
		//g.drawLine(clickX, clickY, mouseX, mouseY);
		
		g.setColor(Color.black);
		g.drawString("FPS "+fps, 10, 25);
	}
	
	public void desenhaLinhaHorizontal(int x, int y,int w) {
		int pospix = y*(W*4)+x*4;
		
		for(int i = 0; i < w;i++) {
			
			bufferDeVideo[pospix] = (byte)255;
			bufferDeVideo[pospix+1] = (byte)0;
			bufferDeVideo[pospix+2] = (byte)0;
			bufferDeVideo[pospix+3] = (byte)0;
			pospix+=4;
		}
	}
	
	public void desenhaLinhaVertical(int x, int y,int h) {
		int pospix = y*(W*4)+x*4;
		
		for(int i = 0; i < h;i++) {
			
			bufferDeVideo[pospix] = (byte)255;
			bufferDeVideo[pospix+1] = (byte)0;
			bufferDeVideo[pospix+2] = (byte)0;
			bufferDeVideo[pospix+3] = (byte)255;
			pospix+=(W*4);
		}
	}
	
	public void desenhaPixel(int x, int y,int r,int g,int b) {
		int pospix = y*(W*4)+x*4;
			
		bufferDeVideo[pospix] = (byte)255;
		bufferDeVideo[pospix+1] = (byte)(b&0xff);
		bufferDeVideo[pospix+2] = (byte)(g&0xff);
		bufferDeVideo[pospix+3] = (byte)(r&0xff);
	
	}
	
	public void start(){
		runner = new Thread(this);
		runner.start();
	}
	
	int timer = 0;
	public void simulaMundo(long diftime){
		
		float difS = diftime/1000.0f;
		float vel = 50;
		
		timer+=diftime;
		if(timer>=1000) {
			timer = 0;
			filtroR = rand.nextFloat();
			filtroG = rand.nextFloat();
			filtroB = rand.nextFloat();
		}
		
		if(UP) {
			posy -= vel*difS;
		}
		if(DOWN) {
			posy += vel*difS;
		}
		if(LEFT) {
			posx -= vel*difS;
		}
		if(RIGHT) {
			posx += vel*difS;
		}
		
	}
	
	
	@Override
	public void run() {
		if (imgtmp == null) {
			System.out.println("A imagem não foi carregada corretamente. O loop de execução será encerrado.");
			return; // Encerra a execução do método run se a imagem não foi carregada
		}
		long time = System.currentTimeMillis();
		long segundo = time/1000;
		long diftime = 0;
		while(ativo){
			simulaMundo(diftime);
			paintImmediately(0, 0, 640, 480);
			paintcounter+=100;
			
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long newtime = System.currentTimeMillis();
			long novoSegundo = newtime/1000;
			diftime = System.currentTimeMillis() - time;
			time = System.currentTimeMillis();
			framecount++;
			if(novoSegundo!=segundo) {	
				fps = framecount;
				framecount = 0;
				segundo = novoSegundo;
			}
		}
	}

	public BufferedImage loadImage(String filename) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filename));
			if (img != null) {
				BufferedImage imgout = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
				Graphics g = imgout.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose(); // Boa prática para liberar recursos do sistema gráfico explicitamente
				return imgout;
			}
		} catch (IOException e) {
			System.out.println("Erro ao carregar a imagem: " + e.getMessage());
			// Você pode querer logar o erro ou reagir de forma diferente
		}
		return null; // Retorna null caso a imagem não possa ser carregada
	}
}
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainCanvas extends JPanel implements Runnable, MouseListener {
	int W = 640;
	int H = 480;

	Thread runner;
	boolean ativo = true;
	int fps = 0;

	BufferedImage imageBuffer;
	byte[] bufferDeVideo;

	Point firstClick = null;

	public MainCanvas() {
		setSize(W, H);
		setFocusable(true);
		setBackground(Color.WHITE);
		addMouseListener(this);

		imageBuffer = new BufferedImage(W, H, BufferedImage.TYPE_4BYTE_ABGR);
		bufferDeVideo = ((DataBufferByte)imageBuffer.getRaster().getDataBuffer()).getData();

		runner = new Thread(this);
		runner.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Não é necessário implementar
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (firstClick == null) {
			firstClick = e.getPoint();
		} else {
			Point secondClick = e.getPoint();
			bresenhamLine(firstClick.x, firstClick.y, secondClick.x, secondClick.y);
			firstClick = null; // Reset para o próximo primeiro clique
			repaint(); // Solicita o redesenho do painel
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Não é necessário implementar
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Não é necessário implementar
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Não é necessário implementar
	}

	public void bresenhamLine(int x1, int y1, int x2, int y2) {
		int d = 0;

		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);

		int dx2 = 2 * dx; // slope scaling factors to avoid floating
		int dy2 = 2 * dy; // point

		int ix = x1 < x2 ? 1 : -1; // increment direction
		int iy = y1 < y2 ? 1 : -1;

		int x = x1;
		int y = y1;

		if (dx >= dy) {
			while (true) {
				desenhaPixel(x, y, 0, 0, 0);
				if (x == x2)
					break;
				x += ix;
				d += dy2;
				if (d > dx) {
					y += iy;
					d -= dx2;
				}
			}
		} else {
			while (true) {
				desenhaPixel(x, y, 0, 0, 0);
				if (y == y2)
					break;
				y += iy;
				d += dx2;
				if (d > dy) {
					x += ix;
					d -= dy2;
				}
			}
		}
	}

	public void desenhaPixel(int x, int y, int r, int g, int b) {
		int index = (y * W + x) * 4;
		bufferDeVideo[index] = (byte) 255; // Alpha
		bufferDeVideo[index + 1] = (byte) b;
		bufferDeVideo[index + 2] = (byte) g;
		bufferDeVideo[index + 3] = (byte) r;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageBuffer, 0, 0, this);
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60; // 60 times per second
		double delta = 0;

		while (ativo) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				// Update the frame
				delta--;
				fps++;
			}

			repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
