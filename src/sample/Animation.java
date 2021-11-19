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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;



public class Animation extends Application {
    static ArrayList<double[]> ballsizeArraylist = new ArrayList<>();
    static int countofrectangle = 0;
    int numofShapes = 100;




    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override

    public void start(Stage primaryStage) {
//        Circle btn = new Circle(60);
        int width = 800;
        int height = 800;
        Canvas canvas=new Canvas(width,height);
        Group group = new Group(canvas);
        Scene scene = new Scene(group, width, height);

        double[][] rectPositionsList = readFile();
        for (int i = 0; i<numofShapes; i++){
//            addRandomShapeandTrans(group,rectPositionsList); // uncomment if you want rectangles with predefined positions
            addRandomShapeandTrans(group);
        }
        System.out.println("rectangle pos list num:" +rectPositionsList.length);
        if (rectPositionsList[0][0] == 0){
            saveToFile();
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addRandomShapeandTrans(Group grup, double[]... HeightWidth){


        int random = (int) (Math.random()*2);

        if (random == 0){
            Polygon myShape = addPoly(grup);
            transition(myShape);
        }
        else if (random == 1 ){
            Rectangle myShape = addRect(grup, HeightWidth);
            transition(myShape);
        }


    }
    private Rectangle addRect(Group grup, double[]... HeightWidth){


        Rectangle rectangle = new Rectangle();
        double r1 = Math.random()*75;
        double r2 = Math.random()*75;
        rectangle.setHeight(r1);
        rectangle.setWidth(r2);



        if (HeightWidth.length == 0){
            double x  = Math.random()*800;
            double y = Math.random()*800;
            rectangle.setX(x);
            rectangle.setY(y);
            double[] ballxy = new double[] {x,y};
            ballsizeArraylist.add(ballxy);


        }
        else {
            double x = HeightWidth[countofrectangle][0];
            double y = HeightWidth[countofrectangle][1];
            rectangle.setX(x);
            rectangle.setY(y);
            System.out.println(x);
        }

        rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
//        rectangle.setFill(Paint.valueOf("green"));
        grup.getChildren().add(rectangle);
        countofrectangle++;
        return rectangle;
    }


    private Polygon addPoly(Group grup){

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



    private void transition(Shape my_ball){
        Duration duration = Duration.millis(Math.random()*20000);
        //Create new translate transition
        TranslateTransition transition = new TranslateTransition(duration, my_ball);
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
            FileWriter writer = new FileWriter("ballsizes.txt");
            for (double[] ballxy:ballsizeArraylist
                 ) {
                int i = 0;
                for (double xy:ballxy
                     ) {

                    String xy1 = Double.toString(xy);
                    if (i ==0){
                    String thii = String.valueOf(xy);
                    writer.write(thii);
                    System.out.print(thii);
                    }
                    else{String thii = " "+ xy ;
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

    public double[][] readFile() {
        double[][] places = new double[numofShapes][2];
        try {

            String location = "ballsizes.txt";
            String all = Files.readString(Path.of(location));
            String[] lines = all.split("\n");
            int i = 0;
            for (String line:lines
                 ) {

                String[] valuesList = line.split(" ");
                double x = Double.parseDouble(valuesList[0]);
                double y = Double.parseDouble(valuesList[1]);
                places[i] = new double[] {x,y};
                i++;

            }
            System.out.println("biyudy");
            System.out.println(all);
            System.out.println("buydu");

        }
        catch (Exception e){
            System.out.println("bruh");


        }
        for (double[]a :places
             ) {
            for (double b:a
                 ) {
                System.out.println(b);

            }

        }
        return places;

    }
}
