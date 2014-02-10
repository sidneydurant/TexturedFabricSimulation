
// Panel
import javax.swing.JPanel;

// Graphics
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

// Events
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

// refactor to have an ArrayList (for now, will never be removing fabrics, so slow remove doesn't matter)
// what about if exit area. have to remove then, if streaming, have to remove and add more as you go. if screen switch,
// then you can delete arraylist and build a new one
// make it so that a dead fabric dies. no need to continue thinking about the fabric as a single coherent piece (?)

public class Simulation extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

	private static int WIDTH;
	private static int HEIGHT;

	private static BufferedImage simulationImage;
	private static int[] simulationRaster;

	private ArrayList<Fabric> fabrics;
	private Render ren;

	public Simulation(int width, int height) {

		addKeyListener(this);
	    addMouseListener(this);
	    addMouseMotionListener(this);
		
		WIDTH = width;
		HEIGHT = height;

		simulationImage = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_ARGB);
		simulationRaster = ((DataBufferInt) simulationImage.getRaster().getDataBuffer()).getData();

		ren = new Render(WIDTH, HEIGHT, simulationRaster);

		init();
	}

	public void init(){

		int vertexCountX = 200/8; //172
		int vertexCountY = 100/4; //80
		int linkLength = 6; // 10
		int linkStrength = 24; // 10*2

		int xPos = 100;
		int yPos = 80;
		fabrics = new ArrayList<Fabric>();
		
		// building multiple fabrics
		for( int fabricCountX = 0; fabricCountX < 8; fabricCountX++ ){
			fabrics.add(new Fabric( xPos + 200*fabricCountX, yPos, vertexCountX, vertexCountY, linkLength, linkStrength, ren));
		}

	}
	
	public void tick() {
		for( Fabric f: fabrics){
			f.tick();
		}
	}

	public void paint(Graphics g) {

		ren.clear();
		for( Fabric f: fabrics){
			f.draw( ren );
		}
		
		g.drawImage(simulationImage, 0, 0, WIDTH, HEIGHT, null);

	}

	public int oldX, oldY ;
	
	public void mouseDragged(MouseEvent me) {
		for( Fabric f: fabrics){
			
			int dx = oldX - me.getX();
			int dy = oldY - me.getY();
			
			f.drag( me.getX(), me.getY(), me.getX() + dx/2, me.getY() + dy/2 );
			f.drag( me.getX() + dx/2, me.getY() + dy/2, oldX, oldY );
		}
		oldX = me.getX();
		oldY = me.getY();
		
	}

	public void mouseMoved(MouseEvent me) {
		oldX = me.getX();
		oldY = me.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		requestFocus();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if( e.getKeyChar() == 'r' ){
			init();
		}
		
	}

}
