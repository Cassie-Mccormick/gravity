package dal.gravity;

public class GravityConstant implements GravityModel {
	private double g;
	public GravityConstant(double gravitationalConstant){
		g = gravitationalConstant;
	}
	public double getGravitationalField(){
		return g;
	}
}
