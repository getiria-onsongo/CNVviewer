package sample;

import javafx.fxml.FXML;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class Controller {


    DropShadow shadow = new DropShadow();

    @FXML private Text actiontarget;


    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");

    }
    @FXML protected void handleClearButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");

    }

    @FXML protected void signInButtonShadowAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");

    }

}
