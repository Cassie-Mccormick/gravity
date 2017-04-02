package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {
	private final static double EARTH_GRAVITY = 9.80665;
	private final static double JUPITER_GRAVITY = 25;
    
	public static void main (String [] args) {
	
		NumberFormat nf = NumberFormat.getInstance ();
		nf.setMaximumFractionDigits (3);
		GravityModel earth = new GravityConstant(EARTH_GRAVITY);
		GravityModel jupiter = new GravityConstant(JUPITER_GRAVITY);
		double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
		double sLen = 10, pMass = 10, theta0 = Math.PI/30;
		RegularPendulum rp1 = new RegularPendulum (sLen, pMass, theta0, delta, earth);
		SimplePendulum sp1 = new SimplePendulum (sLen, pMass, theta0, earth);
		RegularPendulum rpCoarse1 = 
		    new RegularPendulum (sLen, pMass, theta0, .1, earth);
	
		// print out difference in displacement in 1 second intervals
		// for 20 seconds
		int iterations = (int) (1/delta);
		System.out.println("Earth:");
		System.out.println ("analytical vs. numerical displacement (fine, coarse)");
		for (int second = 1; second <= 20; second++) {
		    for (int i = 0; i < iterations; i++) rp1.step ();
		    for (int i = 0; i < 10; i++) rpCoarse1.step (); 
		    System.out.println ("t=" + second + "s: \t" + 
					nf.format (Math.toDegrees (sp1.getTheta (second))) 
					+ "\t" +
					nf.format (Math.toDegrees (rp1.getLastTheta ()))
					+ "\t" + 
					nf.format (Math.toDegrees (rpCoarse1.getLastTheta ())));
		}
		
		RegularPendulum rp2 = new RegularPendulum (sLen, pMass, theta0, delta, jupiter);
		SimplePendulum sp2 = new SimplePendulum (sLen, pMass, theta0, jupiter);
		RegularPendulum rpCoarse2 = 
		    new RegularPendulum (sLen, pMass, theta0, .1, jupiter);
		System.out.println("\nJupiter:");
		System.out.println ("analytical vs. numerical displacement (fine, coarse)");
			for (int second = 1; second <= 20; second++) {
			    for (int i = 0; i < iterations; i++) rp2.step();
			    for (int i = 0; i < 10; i++) rpCoarse2.step (); 
			    System.out.println ("t=" + second + "s: \t" + 
			    		nf.format (Math.toDegrees (sp2.getTheta (second))) 
						+ "\t" +
						nf.format (Math.toDegrees (rp2.getLastTheta ()))
						+ "\t" + 
						nf.format (Math.toDegrees (rpCoarse2.getLastTheta ())));
			}
	}
}

