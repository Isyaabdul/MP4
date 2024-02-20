import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator extends Application {

    private Text message;
    private DatePicker datePicker;
    private ColorPicker colorPicker;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        datePicker = new DatePicker(LocalDate.now());
        datePicker.setOnAction(this::processDateChoice);

        colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(this::processColorChoice);

        Button calculateButton = new Button("Show Age");
        calculateButton.setOnAction(this::calculateAge);

        message = new Text();
        message.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 24));

        HBox pickers = new HBox(10, datePicker, colorPicker, calculateButton);
        pickers.setAlignment(Pos.CENTER);


        VBox root = new VBox(20, pickers, message);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: blue");

        Scene scene = new Scene(root, 400, 150);

        primaryStage.setTitle("Age Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void processDateChoice(ActionEvent event) {
        LocalDate date = datePicker.getValue();
        message.setText("You were born on a " + date.getDayOfWeek());
    }

    private void processColorChoice(ActionEvent event) {
        Color selectedColor = colorPicker.getValue();
        message.setFill(selectedColor);
    }

    private void calculateAge(ActionEvent event) {
        LocalDate birthDate = datePicker.getValue();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        message.setText("Hurray! You are "  +  age  + "Years Old!");
    }
}
