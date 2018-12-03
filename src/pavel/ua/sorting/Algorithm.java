package pavel.ua.sorting;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pavel.ua.sorting.strategy.Bubble;
import pavel.ua.sorting.strategy.ISortingStrategy;
import pavel.ua.sorting.strategy.Insertion;
import pavel.ua.sorting.strategy.Selection;

public class Algorithm {
    private boolean random = false;

    private int color = 1;
    private int[] array = new int[Paramethers.WIDTH];

    private ISortingStrategy strategy = new Insertion();

    private Canvas canv;

    public Algorithm(Pane root) {
        fillMassive();
        canv = new Canvas(Paramethers.WIDTH, Paramethers.HEIGHT);
        root.getChildren().add(canv);

        VBox box = new VBox(4);
        ToggleGroup tg = new ToggleGroup();

        RadioButton ch = new RadioButton("Bubble sort");
        ch.setToggleGroup(tg);
//        ch.setSelected(true);
        ch.setPadding(new Insets(5));
        ch.setOnAction(e -> {
            if (strategy != null) {
                strategy = new Bubble(backward);
            }else {
                strategy = new Bubble();
            }
        });
        RadioButton ch2 = new RadioButton("Selection");
        ch2.setToggleGroup(tg);
        ch2.setPadding(new Insets(5));

        ch2.setOnAction(e -> {
            if (strategy != null) {
                strategy = new Selection(backward);
            }else {
                strategy = new Selection();
            }
        });

        RadioButton ch3 = new RadioButton("Insertion");
        ch3.setToggleGroup(tg);
        ch3.setPadding(new Insets(5));

        ch3.setOnAction(e -> {
            if (strategy != null) {
                strategy = new Insertion(forward);
            } else {
                strategy = new Insertion();
            }
        });

        box.getChildren().addAll(ch, ch2, ch3);
        box.setLayoutX(501);
        box.setLayoutY(10);
        root.getChildren().addAll(box);

        VBox boxColor = new VBox(4);
        ToggleGroup tgCol = new ToggleGroup();

        RadioButton chCol = new RadioButton("Blue-ish");
        chCol.setToggleGroup(tgCol);
        chCol.setSelected(true);
        chCol.setPadding(new Insets(5));
        chCol.setOnAction(e -> {
            color = 1;
        });
        RadioButton ch2Col = new RadioButton("Red-ish");
        ch2Col.setToggleGroup(tgCol);
        ch2Col.setPadding(new Insets(5));

        ch2Col.setOnAction(e -> {
            color = 2;
        });

        RadioButton ch3Col = new RadioButton("Psycho");
        ch3Col.setToggleGroup(tgCol);
        ch3Col.setPadding(new Insets(5));

        ch3Col.setOnAction(e -> {
            color = 3;
        });

        boxColor.getChildren().addAll(chCol, ch2Col, ch3Col);
        boxColor.setLayoutX(501);
        boxColor.setPrefWidth(98);
        boxColor.setLayoutY(120);
        root.getChildren().addAll(boxColor);

        ToggleGroup refreshGr = new ToggleGroup();
        RadioButton line = new RadioButton("Plain");
        line.setOnAction(e -> {
            random = false;
        });
        line.setPadding(new Insets(5));

        line.setSelected(true);
        line.setToggleGroup(refreshGr);
        RadioButton rand = new RadioButton("Random");
        rand.setPadding(new Insets(5));

        rand.setOnAction(e -> {
            random = true;
        });
        rand.setToggleGroup(refreshGr);

        VBox box2 = new VBox(4);
        box2.setPrefWidth(98);
        box2.getChildren().addAll(line, rand);

        root.getChildren().add(box2);
        Button but = new Button("Refresh");
        but.setPrefWidth(98);
        but.setLayoutX(501);
        but.setLayoutY(465);
        box2.setLayoutX(501);
        box2.setLayoutY(390);
        box.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
        boxColor.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));

        box2.setBorder(new Border(
                new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
        but.setOnAction(e -> {
            if(strategy!=null) {
                strategy.refresh();
            }
            forward = 0;
            backward = 0;
            fillMassive();
        });
        root.getChildren().add(but);
        root.requestFocus();
    }

    private void fillMassive() {
        for (int i = 0; i < array.length; i++) {
            if (random) {
                array[i] = (int) (Math.random() * Paramethers.HEIGHT);
            } else {
                array[i] = i;
            }
        }
        // shuffle
        if (!random) {
            int temp;
            for (int i = 0; i < 1000; i++) {
                int random1 = (int) (Math.random() * array.length);
                int random2 = (int) (Math.random() * array.length);
                temp = array[random2];
                array[random2] = array[random1];
                array[random1] = temp;
            }
        }
    }


    //	private int i = 0;
    private void calculate() {
        if (strategy != null) {
            strategy.calculate(array);
        }

    }

    private void render() {
//		System.out.println("rendering");
        GraphicsContext g2d = canv.getGraphicsContext2D();

        // fill bg
//		g2d.setFill(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        g2d.clearRect(0, 0, Paramethers.WIDTH, Paramethers.HEIGHT);

        // draw lines
        for (int i = 0; i < array.length; i++) {
            switch (color) {
                case 1:
                    g2d.setStroke(Color.rgb(30, 30, map(array[i], 0, Paramethers.HEIGHT, 0, 255)));
                    break;
                case 2:
                    int gray = map(array[i], 0, Paramethers.HEIGHT, 0, 255);
                    g2d.setStroke(Color.rgb(255, gray, 0));
                    break;
                case 3:
                    g2d.setStroke(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),
                            (int) (Math.random() * 255)));
                    break;
            }
            g2d.strokeLine(i, Paramethers.HEIGHT, i, Paramethers.HEIGHT - array[i]);

        }
    }

    private int map(int x, int in_min, int in_max, int out_min, int out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public void init() {
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTime.get() > 0) {
                    calculate();
                    render();
                }
                lastUpdateTime.set(timestamp);

            }
        };
        timer.start();
    }

    private static int forward, backward;

    public static void progressForward(int progress) {
        forward = progress;
    }

    public static void progressBackward(int progress) {
        backward = progress;
    }
}
