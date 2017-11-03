package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.inputAccountCodeToScript.inputAccountCodeToScriptMain;
import application.inputAccountCodeToScript.setAccountCodeToScriptMain;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;




public class mainFormController extends Main implements Initializable  {
	
	private double initialX;
	private double initialY;
	
	@FXML
	private VBox titleBar;
	
	@FXML
	private AnchorPane mainFormPane;
	
	@FXML
	private WebView webViewYouTube;
	
	@FXML
	private Button btn;
	
	@FXML
	private Button trayIcon;
	
	@FXML
	private Button exitIcon;
	
	@FXML
	private TextField txt;
	
	@FXML
	private ListView<String> listView1Tab1;
	
	@FXML
	private ListView<String> listView2Tab1;
	
	@FXML
	private ListView<String> listView3Tab1;
	
	@FXML
	private ListView<String> listView4Tab1;
	
	@FXML
	private ListView<String> listView5Tab1;
	
	@FXML
	public void test(ActionEvent event) {
		txt.setText("test");
		System.out.println("test");

	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		WebEngine webEngine = webViewYouTube.getEngine();
		//String content_Url = "<iframe width=\"560\" height=\"315\" src=\"//www.youtube.com/embed/1pBgMBBsv4k?autoplay=1&showinfo=0&fs=0\" frameborder=\"0\" allowfullscreen></iframe>";
		//webEngine.loadContent(content_Url);				
		webEngine.load("http://www.youtube.com/embed/1pBgMBBsv4k?autoplay=1");
		AddItemListView(listView1Tab1,1);
		AddItemListView(listView2Tab1,2);
		addDragListeners(titleBar);

		exitIcon.addEventFilter(MouseEvent.MOUSE_PRESSED,
		        new EventHandler<MouseEvent>() {
		          public void handle(final MouseEvent mouseEvent) {
		        	  System.out.println("test");
		        	  System.exit(0);
		        	  
		          } 		
	    });	
	    
		
		
	}
	
	
/*
	 private final ChangeListener<String> itemSelected = new ChangeListener<String>() {
	
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue,
				String newValue) {
			// TODO Auto-generated method stub
			itemSelected(newValue);
			System.out.println(newValue);
			
		}
	 };
	    
	 private void itemSelected(String listItemName) {
	    if (listItemName != null) {	            
	    	  System.out.println(listItemName);
	    	  //showForm(listItemName);
	    }
	 }*/
	 
	 private void AddItemListView(ListView<String> listView, int subNum) {			
		 	ObservableList<String> listItem = FXCollections.observableArrayList();
		 	 switch (subNum) {
				case 1:				
					for (enumSub1Tab1 i : enumSub1Tab1.values()) {
						listItem.add(i.getItemName().toString());
						//System.out.println(i.getItemName().toString());
					}
					break;
				case 2:				
					for (enumSub2Tab1 i : enumSub2Tab1.values()) {
						listItem.add(i.getItemName().toString());
						//System.out.println(i.getItemName().toString());
					}
					break;	
				default : 				
					break;
				}
			
			listView.setItems(listItem);
			
			/*listView.addEventFilter(MouseEvent.MOUSE_PRESSED,
			        new EventHandler<MouseEvent>() {
			          public void handle(final MouseEvent mouseEvent) {
			        	  //System.out.println(listView.getSelectionModel().);
			          } 		
		    });	
		    */
			listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			          public void handle(final MouseEvent mouseEvent) {
			        	  
			        	  listItemSeleted(mouseEvent.getSource().toString(), 
			        			  listView.getSelectionModel().getSelectedItem());
			        	  
			        	  System.out.println(titleBar.getHeight());
			          } 		
		    });	
			
		    //istView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		    //listView.getSelectionModel().selectedItemProperty().addListener(itemSelected);
		    
		    listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {				
				public ListCell<String> call(ListView<String> param) {
					// TODO Auto-generated method stub
		            final Tooltip tooltip = new Tooltip();
		            final ListCell<String> cell = new ListCell<String>() {
		              @Override
		              public void updateItem(String item, boolean empty) {
		                super.updateItem(item, empty);
		                if (item != null) {
		                  setText(item);
		                  tooltip.setText(item);
		                  setTooltip(tooltip);
		                }
		              }
		            }; 
		            return cell;
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
	 
	 
	private void listItemSeleted(String listName, String itemName) {
		
		
		
		if (listName.equals("ListView[id=listView2Tab1, styleClass=list-view]")) {
			switch (itemName) {
			case "매입매출전표 적요 입력":
				inputAccountCodeToScriptMain  e = new inputAccountCodeToScriptMain(Main.primaryStage, Main.root, Main.scene);
				break;
			case "적요설정":
				setAccountCodeToScriptMain  s11 = new setAccountCodeToScriptMain(Main.primaryStage, Main.root, Main.scene);
				break;
			}
		}
		
	}
}

