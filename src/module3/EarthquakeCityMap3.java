package module3;

//Java utilities libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

//import java.util.Collections;
//import java.util.Comparator;
//Processing library
//Unfolding libraries
//Parsing library

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap3 extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);

        int yellow = color(255, 255, 0);
        int red = color(255, 0, 0);
        int blue = color(0, 0, 255);
        float big = 15;
        float medium = 10;
        float small = 5;

			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    if (earthquakes.size() > 0) {
            for (PointFeature myFeature : earthquakes)
            {
                SimplePointMarker myMarker = createMarker(myFeature);
                Object magObj = myFeature.getProperty("magnitude");
                float mag = Float.parseFloat(magObj.toString());
                if(mag < 4.0)
                {
                    myMarker.setColor(blue);
                    myMarker.setRadius(small);
                }
                else if ( mag >=4.0 && mag <=4.9)
                {
                    myMarker.setColor(yellow);
                    myMarker.setRadius(medium);
                }
                else
                {
                    myMarker.setColor(red);
                    myMarker.setRadius(big);
                }
                //myMarker.setColor(color(150,150,150));
                markers.add(myMarker);
                /*
                PointFeature f = earthquakes.get(0);
                System.out.println(f.getProperties());
                Object magObj = f.getProperty("magnitude");
                float mag = Float.parseFloat(magObj.toString());
                // PointFeatures also have a getLocation method
                 */
            }

	    }
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  

	    
	    //TODO: Add code here as appropriate
        for (Marker myMarker : markers) {
            map.addMarker(myMarker);
        }
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// finish implementing and use this method, if it helps.
		return new SimplePointMarker(feature.getLocation());
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
        fill(255,255,240); //white
        textSize(12);
        rect(30,50,130, 255);
        //fill(255, 0, 0);
        fill(0, 0, 0);
        text("Earthquake Key", 40, 70);
        fill(255, 0, 0);  //red
        ellipse(45, 90, 15, 15);
        fill(0, 0, 0);
        text("5.0+ Magnitude", 60, 95);
        fill(255, 255, 0);  // yellow
        ellipse(45, 150, 10, 10);
        fill(0, 0, 0);
        text("4.0+ Magnitude", 60, 155);
        fill(0, 0, 255);  // blue
        ellipse(45, 200, 5, 5);
        fill(0, 0, 0);
        text("Below 4.0", 60, 205);
	
	}
}
