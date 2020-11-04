package com.doublerobot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Example of how to bind a dynamic set of data to a TableView.
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        TableView<DataLine> tableView = new TableView<>();

        DataDocument dataDocument = DataDocument.generateData(new String[]{"First", "Second", "Third", "Fourth", "Fifth"});

        tableView.setEditable(true);

        // Let's set up all of the columns defined by the dataDocument
        for (int i = 0; i < dataDocument.getColumns().length; i++) {
            // Set the label for the column from the dataDocument column name
            TableColumn<DataLine, String> tableColumn = new TableColumn<>(dataDocument.getColumns()[i]);
            // Set the index of the column as user data for the column.
            tableColumn.setUserData(i);
            // Tell the column we'd like to use editable cells.
            tableColumn.setEditable(true);
            // Set a preferred width. We might want to change this if we know more about the data.
            tableColumn.setMinWidth(200.0f);
            // This is where we set a factory for the cells for display. Here, we are returning
            // a SimpleStringProperty value. This is a very common scenario, and so the TableView automatically
            // generates a String display label.
            tableColumn.setCellValueFactory(c -> c.getValue().values.get((Integer) c.getTableColumn().getUserData()));
            // Here we tell the column to just use a TextField to edit the cell. The TableView will swap out the
            // label for a TextField.
            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            // Here we specify that when the cell edit is committed, update the underlying data value. Because of the
            // Property bindings, this will automatically kick off the UI to update in response to the change.
            // Note that by default, the user has to press return to cause an edit to commit. Clicking off the cell
            // will cause the edit to be discarded. You can modify that behavior with other events.
            tableColumn.setOnEditCommit(
                    (TableColumn.CellEditEvent<DataLine, String> t) -> {
                        int column = (Integer) t.getTableColumn().getUserData();
                        t.getRowValue().values.get(column).set(t.getNewValue());
                    });

            // Add the column to the table view.
            tableView.getColumns().add(tableColumn);
        }

        // Here is where we actually set the items to be displayed. Note that TableView automatically manages the cells
        // displayed, so even if we have a *huge* data set underlying the TableView, it will only update the data
        // actually shown in the cells.
        tableView.setItems(dataDocument.data);
        // This is needed to allow the columns to return to the default sort after the user clicks on the columns
        // a few times to change the sorting.
        dataDocument.data.comparatorProperty().bind(tableView.comparatorProperty());

        // Create the scene and display it!
        Scene scene = new Scene(new StackPane(tableView), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

}