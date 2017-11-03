package application.inputAccountCodeToScript;

import java.net.URL;
import java.util.ResourceBundle;

import application.inputAccountCodeToScript.model.Account;
import application.inputAccountCodeToScript.model.addAccountData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class setAccountCodeToScript implements Initializable {

	double initialX;
	double initialY;
	
	@FXML
    VBox titleBar;
		
	@FXML
    TableView<Account> tableAccount;
    
	@FXML
    TableColumn<Account, Integer> codeCol;
    
	@FXML
    TableColumn<Account, String> nameCol;
	
	@FXML
    TableColumn<Account, String> scriptCol;
    
	@FXML
    TextField filterText;
	
	@FXML
	Button btn;
    
    private ObservableList<Account> masterData = FXCollections.observableArrayList();
    
    
	public setAccountCodeToScript() {
		addAccountData add = new addAccountData();
		masterData = add.addAccountData(masterData);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		addDragListeners(titleBar);
		
		codeCol.setCellValueFactory(cellData -> cellData.getValue().AccountCodeProperty().asObject());
		nameCol.setCellValueFactory(cellData -> cellData.getValue().AccountNameProperty());
		scriptCol.setCellValueFactory(cellData -> cellData.getValue().ScriptProperty());
		
		FilteredList<Account> filteredData = new FilteredList<>(masterData, a -> true);

		
		filterText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(accountcode -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                                
                else if (String.valueOf(accountcode.getAccountCode()).contains(newValue)) {
                    return true; // Filter matches first name.
                }

                return false; // Does not match.
            });
        });		
		
		SortedList<Account> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableAccount.comparatorProperty());
		tableAccount.setItems(sortedData);
		tableAccount.setEditable(true);
	       
		scriptCol.setCellFactory(TextFieldTableCell.<Account> forTableColumn());
	 
		scriptCol.setMinWidth(200);
	 
		scriptCol.setOnEditCommit((CellEditEvent<Account, String> event) -> {
	            TablePosition<Account, String> pos = event.getTablePosition();
	 
	            String script = event.getNewValue();
	 
	            int row = pos.getRow();
	            
	            Account account = event.getTableView().getItems().get(row);
	 
	            account.setScript(script);
	    });

		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				
			}
		});

			
		
	}
	
	

	

	 
	private void addDragListeners(final Node n){
		
	       n.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent me) {
	                if(me.getButton()!=MouseButton.MIDDLE)
	                {
	                    initialX = me.getSceneX();
	                    initialY = me.getSceneY();
	                }
	                else
	                {
	                    n.getScene().getWindow().centerOnScreen();
	                    initialX = n.getScene().getWindow().getX();
	                    initialY = n.getScene().getWindow().getY();
	                }

	            }
	       });

	       n.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent me) {
	                if(me.getButton()!=MouseButton.MIDDLE)
	                {
	                    n.getScene().getWindow().setX( me.getScreenX() - initialX );
	                    n.getScene().getWindow().setY( me.getScreenY() - initialY);
	                }
	            }
	        });
	}

}

