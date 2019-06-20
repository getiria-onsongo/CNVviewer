/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;

/**
 *
 * @author Carrie
 */
public class DisplayContactList extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         Label response = new Label("");
        Label title = new Label("CNVviewer: User interface for \n validating CNV calls from CNVRF \n");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.CADETBLUE);
       
        
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root, 450, 450);
        
        ObservableList<Contact> contactList = FXCollections.observableArrayList(
                new Contact("Peggy", "Fisher", "717-555-1212"), 
                new Contact("Jim", "Freed", "441-456-1345"), 
                new Contact("Pat", "Keegan", "717-363-1432"), 
                new Contact("Jane", "Slattery", "441-478-4488"), 
                new Contact("Cy", "Young", "970-554-1265"), 
                new Contact("Rob", "Jones", "570-655-1587"), 
                new Contact("Carol", "King", "215-547-5958"), 
                new Contact("Bob", "Kauffman", "215-456-6345"), 
                new Contact("Gloria", "Shilling", "717-785-6092"), 
                new Contact("Bill", "Sigler", "441-444-1345")
                );
    
        TableView<Contact> tvContacts;
        
        tvContacts = new TableView<Contact>(contactList);
        tvContacts.setEditable(true);
        
        TableColumn<Contact, String> fName = new TableColumn<>("First Name");
        fName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tvContacts.getColumns().add(fName);
        
        TableColumn<Contact, String> lName = new TableColumn<>("Last Name");
        lName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tvContacts.getColumns().add(lName);

        TableColumn<Contact, String> cell = new TableColumn<>("Cell Phone Number");
        cell.setCellValueFactory(new PropertyValueFactory<>("cellPhone"));
        tvContacts.getColumns().add(cell);

        cell.setCellFactory(TextFieldTableCell.<Contact>forTableColumn());
        cell.setOnEditCommit(
                (CellEditEvent<Contact, String> t) -> {
                    ((Contact) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCellPhone(t.getNewValue());
                });

        
        tvContacts.setPrefWidth(300);
        tvContacts.setPrefHeight(300);
        
        TableView.TableViewSelectionModel<Contact> tvSelContact = 
                tvContacts.getSelectionModel();
        
        tvSelContact.selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> changed, 
                    Number oldVal, Number newVal) {
                int index = (int)newVal;
                response.setText("The cell number for the contact selected is "
                        +contactList.get(index).getCellPhone());
            }
        });
        
        response.setFont(Font.font("Arial", 14));
        root.getChildren().addAll(title,tvContacts, response);
        primaryStage.setTitle("Contact List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
