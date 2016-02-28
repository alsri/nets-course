
public class Circle implements GeometricShape{
	
	private double radius;
	private double x,y;
	
	public Circle(double radius, double x,double y){
		this.radius=radius;
		this.x=x;
		this.y=y;
	}

	@Override
	public double getSurface() {
		return this.radius*this.radius*Math.PI;
	}

	@Override
	public double getXCoordinate() {
		return this.x;
	}

	@Override
	public double getYCoordinate() {
		return this.y;
	}
	
	public static void main(String[] args){
		GeometricShape shape= new Circle(13,0,0);
		ShapeHandler h = new ShapeHandler();
		h.handle(shape);
		
		
	}

}
