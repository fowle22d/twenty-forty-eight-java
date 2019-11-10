import processing.core.PApplet;

public class ProcessingTest extends PApplet {

    public void setUp(){
        background(423);
    }

    public void draw(){
        //stroke(0);
        fill(255, 0, 255);
        ellipse(width/2, height/2, 100, 100);
    }

    public void settings(){
        size(400, 600);
    }
}
