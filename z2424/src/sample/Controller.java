package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldTableCell;

public class Controller {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<TextRobot, String> col;
    @FXML
    private Button buttonCorrectText;
    @FXML
    private Button buttonAddRow;
    @FXML
    private TextArea textArea;
    private final int countRow = 10;
    private final String TEXT = "Но действия представителей $  являются только методом $ участия и ограничены исключительно $ образом мышления.";
    private int count = 0;
    ObservableList<TextRobot> items = FXCollections.observableArrayList();
    public Controller() {
    }

    public void initialize() {
        this.tableView.itemsProperty().setValue(this.items);
        this.tableView.setEditable(true);

        for(int i = 0; i < 10; ++i) {
            this.items.add(new TextRobot(""));
        }

        this.col.setCellValueFactory((param) -> {
            return new SimpleStringProperty(((TextRobot)param.getValue()).getWord());
        });
        this.col.setCellFactory(TextFieldTableCell.forTableColumn());
        this.col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TextRobot, String>>() {
            public void handle(TableColumn.CellEditEvent<TextRobot, String> event) {
                ((TextRobot)event.getTableView().getItems().get(event.getTablePosition().getRow())).setWord((String)event.getNewValue());
            }
        });
        this.textArea.setText("Но действия представителей $  являются только методом $ участия и ограничены исключительно $ образом мышления.");
    }

    public void buttonCorrectTextAction() {
        String[] arr = this.textArea.getText().split(" ");
        String result = "";

        int i;
        for(i = 0; i < arr.length; ++i) {
            if (arr[i].contains("$")) {
                String[] arrLetters = arr[i].split("");

                int j;
                for(j = 0; j < arrLetters.length; ++j) {
                    if (arrLetters[j].equals("$")) {
                        arrLetters[j] = ((TextRobot)this.items.get(this.count)).getWord();
                        ++this.count;
                    }
                }

                arr[i] = "";

                for(j = 0; j < arrLetters.length; ++j) {
                    arr[i] = arr[i] + arrLetters[j];
                }
            }
        }

        for(i = 0; i < arr.length; ++i) {
            result = result + arr[i] + " ";
        }

        this.textArea.setText(result);

        for(i = 0; i < this.items.size(); ++i) {
            ((TextRobot)this.items.get(i)).setWord("");
        }

        this.count = 0;
        this.tableView.refresh();
    }

    public void buttonAddRowAction() {
        this.items.add(new TextRobot(""));
    }

}
