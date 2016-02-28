
public class ShapeHandler {
	public void handle(GeometricShape s){
		System.out.format("I am working with "
				+ "a shape located at (%f,%f), "
				+ "with a surface of %f", 
				s.getXCoordinate(),
				s.getYCoordinate(),
				s.getSurface());
	}
}
