package com.doublerobot;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

/**
 * Represents a set of data. The columns define the number of columns and the labels for the columns.
 * <p>
 * The rows are represented with the data. A SortedList allows for column sorting to be reset to a default
 * value.
 */

public class DataDocument {

    /**
     * The data in the document
     */
    public SortedList<DataLine> data;
    private final String[] columns;

    public DataDocument(String[] columns) {
        this.columns = columns;
    }

    /**
     * Primitive data generation. This version just generates a hundred lines, with semi-random data in each cell
     */
    public static DataDocument generateData(String[] columns) {

        ObservableList<DataLine> observableList = FXCollections.observableArrayList();

        DataDocument dataDocument = new DataDocument(columns);

        for (int i = 0; i < 100; i++) {
            DataLine dataLine = new DataLine(dataDocument);
            for (int ii = 0; ii < dataDocument.getColumns().length; ii++) {
                dataLine.values.add(new SimpleStringProperty("" + i * ii));
            }
            observableList.add(dataLine);
        }

        dataDocument.data = new SortedList<>(observableList);

        return dataDocument;
    }

    /**
     * The column labels (and implicitly, the number of columns)
     */
    public String[] getColumns() {
        return columns;
    }

}
