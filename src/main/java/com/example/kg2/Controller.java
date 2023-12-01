package com.example.kg2;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;
import java.io.File;

public class Controller {
    public ImageView from;
    public ImageView to;
    public static Stage mainStage;
    public String path;
    public void selectSecondFilter(ActionEvent actionEvent) {
        Image image;

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img = Imgcodecs.imread(path);

        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, buf);
        image = new Image(new ByteArrayInputStream(buf.toArray()));

        Mat to_img = new Mat();
        Imgproc.GaussianBlur(img, to_img, new Size (15,15), 0);
         buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", to_img, buf);

        image = new Image(new ByteArrayInputStream(buf.toArray()));

        to.setImage(image);
        to.setFitHeight(600);
        to.setFitWidth(400);
        to.setVisible(true);
    }

    public void selectFirstFilter(ActionEvent actionEvent) {
        Image image;

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img = Imgcodecs.imread(path);

        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, buf);
        image = new Image(new ByteArrayInputStream(buf.toArray()));

        from.setImage(image);
        from.setFitHeight(600);
        from.setFitWidth(400);

        Mat to_img = new Mat();
        Imgproc.medianBlur(img, to_img, 35);
        buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", to_img, buf);

        image = new Image(new ByteArrayInputStream(buf.toArray()));

        to.setImage(image);
        to.setFitHeight(600);
        to.setFitWidth(400);
        to.setVisible(true);
    }

    public void erodePic(ActionEvent actionEvent) {
        Image image;

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img = Imgcodecs.imread(path);
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, buf);
        image = new Image(new ByteArrayInputStream(buf.toArray()));

        from.setImage(image);
        from.setFitHeight(600);
        from.setFitWidth(400);

        Mat to_img = new Mat();
        Imgproc.erode(img, to_img, kernel);
        buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", to_img, buf);

        image = new Image(new ByteArrayInputStream(buf.toArray()));

        to.setImage(image);
        to.setFitHeight(600);
        to.setFitWidth(400);
        to.setVisible(true);
    }

    public void dilatePic(ActionEvent actionEvent) {
        Image image;

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img = Imgcodecs.imread(path);

        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, buf);
        image = new Image(new ByteArrayInputStream(buf.toArray()));
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));

        from.setImage(image);
        from.setFitHeight(600);
        from.setFitWidth(400);

        Mat to_img = new Mat();
        Imgproc.dilate(img, to_img, kernel);
        buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", to_img, buf);

        image = new Image(new ByteArrayInputStream(buf.toArray()));

        to.setVisible(true);
        to.setImage(image);
        to.setFitHeight(600);
        to.setFitWidth(400);
    }
    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        this.path = selectedFile.getAbsolutePath();

        this.from.setImage(new Image(selectedFile.getAbsolutePath()));
        from.setFitWidth(400);
        from.setFitHeight(600);
        this.to.setVisible(false);
    }
}
