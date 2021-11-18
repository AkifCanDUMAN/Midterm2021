package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;



public class Animation extends Application {
    static ArrayList<double[]> ballsizeArraylist = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Circle btn = new Circle(60);
        int width = 800;
        int height = 800;
        Canvas canvas=new Canvas(width,height);
        Group group = new Group(canvas);
        Scene scene = new Scene(group, width, height);


        for (int i = 0; i<100; i++){
            addRandomShapeandTrans((int) (Math.random()*25),primaryStage,group,scene);

//            Thread.sleep(100);
        }
        saveToFile();


        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
    private void addRandomShapeandTrans(int radius, Stage primaryStage, Group grup, Scene scen){


        int random = (int) (Math.random()*2);

        if (random == 0){
            Polygon my_ball = addPoly(primaryStage,grup,scen);
            transition(my_ball);
        }
        else if (random == 1 ){
            Rectangle my_ball = addRect(primaryStage,grup,scen);
            transition(my_ball);
        }

    }
    private Polygon addPoly(Stage primaryStage, Group grup, Scene scen){

        int numofpoints = (int) (1 + Math.random()*9);
        Double[] polygonpoints = new Double[numofpoints];

        for (int i =0; i < numofpoints; i++){
            polygonpoints[i] = (double) (300 + new Random().nextInt(200));

        }
        Polygon poly = new Polygon();

        poly.getPoints().addAll(polygonpoints);
        poly.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        poly.setStrokeWidth(Math.random()*7);
        poly.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        grup.getChildren().add(poly);

        return poly;
    }

    private Rectangle addRect(Stage primaryStage, Group grup, Scene scen){


        Rectangle ball = new Rectangle();
        double r1 = Math.random()*75;
        double r2 = Math.random()*75;
        ball.setHeight(r1);
        ball.setWidth(r2);
        double random = Math.random()*800;
        double random2 = Math.random()*800;
        ball.setX(random);
        ball.setY(random2);
        double[] ballxy = new double[] {random,random2};
        ballsizeArraylist.add(ballxy);
        ball.setFill(Color.color(Math.random(), Math.random(), Math.random()));
//        ball.setFill(Paint.valueOf("green"));
        grup.getChildren().add(ball);
        return ball;
    }


    private void transition(Shape shape){
        Duration duration = Duration.millis(Math.random()*10000);
        //Create new translate transition
        TranslateTransition transition = new TranslateTransition(duration, shape);
        //Move in X axis by +200
        int rand1 = -500 + new Random().nextInt(1000);
        int rand2 = -500 + new Random().nextInt(1000);

        transition.setByX(rand1);

        transition.setByY(rand2);

        transition.setAutoReverse(true);

        transition.setCycleCount((int) (Math.random()*50));
        transition.play();
    }
    public String saveToFile() {
        try {
            FileWriter writer = new FileWriter("sizes.txt");
            for (double[] ballxy:ballsizeArraylist
                 ) {
                int i = 0;
                for (double xy:ballxy
                     ) {

                    String xy1 = Double.toString(xy);
                    if (i ==0){
                    String thii = "x: "+ xy + "  ";
                    writer.write(thii);
                    System.out.print(thii);
                    }
                    else{String thii = "y: "+ xy + "  ";
                    System.out.print(thii);
                    writer.write(thii);}
                    i++;

                }
                writer.write("\n");

            }

            writer.close();
            System.out.println("Success");
            return "Success";
        }

        catch (Exception e){
            System.out.println("gg");
            return "Failed";
        }


    }
}
