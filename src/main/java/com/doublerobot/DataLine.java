package com.doublerobot;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The most simple possible dynamic data store. This version uses Strings for all of the columns.
 * <p>
 * A fancier version might declare different data types for each column. For example, type information for
 * each column might be stored in the DataDocument.
 */
public class DataLine {

    /**
     * A single "row" of data. In a fancier system, you might have different data types than String, but this
     * gives a (relatively) simple starting place.
     */
    final public ObservableList<SimpleStringProperty> values = FXCollections.observableArrayList();

    /**
     * Not used in this example, but provides a mechanism for getting back from the data to the owning dataDocument
     */
    final public DataDocument dataDocument;

    public DataLine(DataDocument dataDocument) {
        this.dataDocument = dataDocument;
    }

}
