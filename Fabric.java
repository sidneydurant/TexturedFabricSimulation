import java.util.LinkedList;
import java.util.Random;

/* @author Sidney Durant
 * This class simulates a fabric composed of a grid like arrangement of points
 * each point is connected to four neighbors, and with every tick each spring
 * attempts to return to its resting length
 */

public class Fabric {

	LinkedList<Link> allLinks;
	LinkedList<PointMass> allPointMasses;

	public Fabric(int x, int y, int pointMassCountX, int pointMassCountY,
			int linkLength, int linkStrength, Render ren) {

		int xPos;
		int yPos;

		Random r = new Random();
		pointMassCountX = 24;
		pointMassCountY = 37;
		int l = new Color( 255, 191, 85, 66).getColor();
		int k = new Color( 255, 91, 19, 26 ).getColor();
		int m = new Color( 255, 132, 39, 40 ).getColor();
		int[] colorMap = {
				l,m,m,m,k,m,k,m,m,k,m,m,m,m,k,m,m,k,m,k,m,m,m,l,
				m,l,l,l,m,l,m,l,l,m,l,l,l,l,m,l,l,m,l,m,l,l,l,m,
				m,l,l,m,l,l,m,l,l,m,l,l,l,l,m,l,l,m,l,l,m,l,l,m,
				k,m,k,m,m,m,k,m,l,k,m,m,m,m,k,l,m,k,m,m,m,k,m,k,
				k,k,m,m,m,k,m,l,l,k,m,l,l,m,k,l,l,m,k,m,m,m,k,k,
				k,m,m,m,k,m,l,l,k,m,m,l,l,m,m,k,l,l,m,k,m,m,m,k,
				k,m,m,k,m,l,l,k,m,l,m,l,l,m,l,m,k,l,l,m,k,m,m,k,
				k,m,k,m,l,l,k,m,l,l,m,l,l,m,l,l,m,k,l,l,m,k,m,k,
				k,m,k,l,l,k,m,m,l,m,m,m,m,m,m,l,m,m,k,l,l,k,m,k,
				k,m,k,l,k,m,l,m,m,m,l,l,l,l,l,m,m,l,m,k,l,k,m,k,
				k,m,k,k,m,l,l,m,l,l,k,k,k,k,k,l,l,m,l,m,k,k,m,k,
				k,m,k,m,m,l,m,l,k,k,k,k,k,k,k,k,k,l,m,m,m,k,m,k,
				k,m,k,m,m,m,l,k,k,k,k,k,k,k,k,k,k,k,l,m,m,k,m,k,
				k,m,k,k,m,l,k,k,k,k,k,k,k,k,l,l,l,k,l,m,k,k,m,k,
				k,m,m,k,m,l,k,k,k,k,k,k,k,l,m,m,m,l,l,m,k,m,m,k,
				k,m,k,k,l,k,k,k,k,k,k,k,l,m,l,l,m,m,m,m,k,k,m,k,
				k,m,k,m,l,k,k,k,k,k,k,k,l,m,l,l,m,l,l,m,m,k,m,k,
				k,m,k,k,l,k,k,k,k,k,k,k,l,m,l,l,m,m,m,m,k,k,m,k,
				k,m,m,k,l,k,k,k,k,k,k,k,m,l,m,m,m,l,l,m,k,m,m,k,
				k,m,k,k,m,l,k,k,k,k,k,k,k,m,l,l,l,m,l,m,k,k,m,k,
				k,m,k,m,m,l,k,k,k,k,k,k,k,k,m,m,m,k,l,m,m,k,m,k,
				k,m,k,k,m,m,l,k,k,k,k,k,k,k,k,k,k,k,l,m,k,k,m,k,
				k,m,m,k,m,l,m,l,k,k,k,k,k,k,k,k,k,l,m,m,k,m,m,k,
				k,m,k,k,m,l,l,m,l,l,k,k,k,k,k,l,l,m,l,m,k,k,m,k,
				k,m,k,m,m,l,l,m,m,m,l,l,l,l,l,m,m,l,l,m,m,k,m,k,
				k,m,k,k,m,l,l,m,l,l,m,m,m,m,m,l,m,l,l,m,k,k,m,k,
				k,m,m,k,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,k,m,m,k,
				k,m,k,k,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,k,k,m,k,
				k,m,k,m,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,m,k,m,k,
				k,m,k,k,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,k,k,m,k,
				k,m,m,k,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,k,m,m,k,
				k,m,k,k,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,k,k,m,k,
				k,m,k,m,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,m,k,m,k,
				k,m,k,k,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,k,k,m,k,
				k,m,m,k,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,k,m,m,k,
				k,m,k,k,m,l,l,m,l,l,m,l,l,m,l,l,m,l,l,m,k,k,m,k,
				k,k,k,k,k,m,m,k,m,m,k,m,m,k,m,m,k,m,m,k,k,k,k,k
				};

		// construct pointMasses in an array, and then copy into allPointMasses
		PointMass[][] pointMassArray = new PointMass[pointMassCountX][pointMassCountY];

		for (int xi = 0; xi < pointMassCountX; xi++) {
			for (int yi = 0; yi < pointMassCountY; yi++) {
				xPos = x + (linkLength * xi);
				yPos = y + (linkLength * yi);

				//pointMassArray[xi][yi] = new PointMass( xPos, yPos, 0, 0, new Color( 255, r.nextFloat()*255, r.nextFloat()*255, r.nextFloat()*255 ));
				pointMassArray[xi][yi] = new PointMass( xPos, yPos, 0, 0, new Color( colorMap[xi+(yi*pointMassCountX)]) );
				// pointMassArray[xi][yi] = new PointMass( xPos, yPos, 0, 0, new Color( 0xaa, yPos, xPos, 0 ));
				if (yi == 0) {
					pointMassArray[xi][yi].setPin(true);
				}
			}
		}

		allPointMasses = new LinkedList<PointMass>();

		for (PointMass[] subArray : pointMassArray) {
			for (PointMass p : subArray) {
				allPointMasses.add(p);
			}
		}

		allLinks = new LinkedList<Link>();
		// construct all of the links and add them to the list of allLinks
		for (int xi = 0; xi < pointMassCountX; xi++) {
			for (int yi = 0; yi < pointMassCountY; yi++) {

				if (xi != 0) {
					allLinks.add(new Link(pointMassArray[xi][yi],
							pointMassArray[xi - 1][yi], linkLength,
							linkStrength));
				}
				if (yi != 0) {
					allLinks.add(new Link(pointMassArray[xi][yi],
							pointMassArray[xi][yi - 1], linkLength,
							linkStrength));
				}
			}
		}
	}

	public void tick() {

		for (PointMass p : allPointMasses) {
			p.update();
		}

		LinkedList<Link> allTornLinks = new LinkedList<Link>();

		int relaxationCount = 15; // number of times to relax the fabric

		for (int i = 1; i <= relaxationCount; i++) {
			for (Link l : allLinks) {
				// relax, then if link is torn, and this is the last relaxation prepare to remove the link
				if (l.solve() && i == relaxationCount) {
					allTornLinks.add(l);
				}
			}
		}

		// remove all torn links
		for (Link tornLink : allTornLinks) {
			allLinks.remove(tornLink);
		}

	}

	public void draw(Render r) {
		
		// Draw links:
		//for( Link l : allLinks){ l.draw( r ); }
		
		// Draw Pointmasses
		for (PointMass p : allPointMasses) { p.draw(r); }
	}

	public void drag(int x, int y, int xOld, int yOld) {

		float dx = x - xOld;
		float dy = y - yOld;
		float distanceSquared = 0;

		for (PointMass p : allPointMasses) {
			// if distance between p and x,y < certain value;
			distanceSquared = (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
			if (distanceSquared < 900) {
				p.translate(dx, dy);
			} else if (distanceSquared < 1600) {
				p.translate(dx / 2, dy / 2);
			}

		}

	}
}
