/*
Talha Rashid
Assignment 1
Question 3
*/

package question_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.shape.Line;

public class Question_3 extends Application {    
    @Override
    public void start(Stage primaryStage) {
        Pane pane=new Pane();
        
        //Main circle
        Circle cir=new Circle(200,120,100);
        cir.setFill(Color.TRANSPARENT);
        cir.setStroke(Color.BLACK);
        
        
        //3 different lines,texts,and points as circles
        Line[] lines=new Line[3];
        Circle[] points=new Circle[3];
        Text[] angs=new Text[3];
        
        for(int j=0;j<3;j++){//3 random points and mouse dragging
            angs[j]=new Text();//initialize text
            points[j]=new Circle(0,0,5);//initialize point
            Random rand=new Random();
            int ang=rand.nextInt(360);     
            points[j].setFill(Color.RED);//fills each circle with red and black stroke
            points[j].setStroke(Color.BLACK);
            double x = cir.getCenterX() + cir.getRadius() * Math.cos(Math.toRadians(ang));
            double y = cir.getCenterY() + cir.getRadius() * Math.sin(Math.toRadians(ang));
            points[j].setCenterX(x);
            points[j].setCenterY(y);
            final int d=j;
            points[j].setOnMouseDragged(e->{
                double rad = Math.atan2(e.getY() - cir.getCenterY(), e.getX() - cir.getCenterX());
                double h = cir.getCenterX() + cir.getRadius() * Math.cos(rad);
                double k = cir.getCenterY() + cir.getRadius() * Math.sin(rad);
                points[d].setCenterX(h);
                points[d].setCenterY(k);
                lines(lines,points,angs);//so it updates exactly when you move the point
            });
        }
        for(int x=0;x<3;x++){//3 lines
            int ind=0;
            if(x + 1 >=3){
                ind=0;
            }
            else{
                ind=x+1;
            }
            lines[x] = new Line(points[x].getCenterX(), points[x].getCenterY(),
                    points[ind].getCenterX(), points[ind].getCenterY());   //initialize lines
        }
        lines(lines,points,angs);//So the screen shows text at the begining 
        pane.getChildren().addAll(angs);
        pane.getChildren().addAll(lines);
        pane.getChildren().addAll(cir);
        pane.getChildren().addAll(points);
        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Question3"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    private void lines(Line[] lines,Circle[] points,Text[] txtangs){
        for(int d=0;d<3;d++){
            int ind=0;
            if(d + 1 >=3){
                ind=0;
            }
            else{
                ind=d+1;
            }
            lines[d].setStartX(points[d].getCenterX());//lines that connect each point
            lines[d].setStartY(points[d].getCenterY());
            lines[d].setEndX(points[ind].getCenterX());
            lines[d].setEndY(points[ind].getCenterY());
            txtangs[d].setX(points[d].getCenterX()+10);//text stays with point even when its moved
            txtangs[d].setY(points[d].getCenterY()+10);
        }
        double a=lenght(lines[0]);
        double b=lenght(lines[1]);
        double c=lenght(lines[2]);
        
        double A=Math.toDegrees(Math.acos((a*a-b*b-c*c)/(-2*b*c)));
        double B=Math.toDegrees(Math.acos((b*b-a*a-c*c)/(-2*a*c)));
        double C=Math.toDegrees(Math.acos((c*c-b*b-a*a)/(-2*a*b)));
        
        txtangs[0].setText(String.format("%.2f",B));
        txtangs[1].setText(String.format("%.2f",C));
        txtangs[2].setText(String.format("%.2f",A));
        
        
    }
    private static double lenght(Line line){//lenght of each line from point to point
        double x=line.getStartX();
        double y=line.getStartY();
        double z=line.getEndX();
        double h=line.getEndY();
        return Math.sqrt(Math.pow(x-z,2)+Math.pow(y-h,2));       
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
