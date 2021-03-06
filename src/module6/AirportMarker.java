package module6;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.util.List;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(150, 30, 30);
		pg.ellipse(x, y, 5, 5);
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {

		String title = getInfo();
		pg.pushStyle();

		pg.rectMode(PConstants.CORNER);

		pg.stroke(110);
		pg.fill(255,255,255);
		pg.rect(x, y - 55, pg.textWidth(title) +6, 60, 5);

		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(0);
		pg.text(title, x + 3 , y -55);

		pg.popStyle();
	}
	public String getInfo() {
		return "Country : " + getProperty("country") + "\n" + "Altitude : " + getProperty("altitude") + "\n" + "City : " + getProperty("city") + "\n" + "Name : " + getProperty("name") + "\n";

	}
	
}
