package GUIModule;

import processing.core.*;

/**
 * Created by lulu on 25/10/2015.
 */

public class MyDisplay extends PApplet{

    public void setup()
    {
        size(400,400);
        //background(0,0,0); //black, search online for rgb to get colours
        background(200,200,200); // grey

    }
    public void draw()
    {
        fill(255,255,0);
        ellipse(200,200,390,390);
        fill(0,0,0);
        ellipse(120,130,50,70);
        ellipse(280,130,50,70);
        arc(200,280,75,75,0,PI);
        noFill();
    }

}
