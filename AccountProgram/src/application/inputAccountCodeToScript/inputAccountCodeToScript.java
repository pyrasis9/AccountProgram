package application.inputAccountCodeToScript;

import java.io.IOException;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;

import application.testVO;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class inputAccountCodeToScript implements Initializable,  NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {

	double initialX;
	double initialY;
	private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
	
	
	@FXML
	private VBox titleBar;
  	
  	@FXML
  	private Button setProductBtn;
  	
  	@FXML
  	private Button setAccountBtn;
  	
  	@FXML
  	private Button setStartBtn;
  	
  	@FXML
  	private TabPane subTab;
  	
  	@FXML
	private TextField productTxt;
	
	@FXML
	private TextField cardTabText;
	
	@FXML
	private TextField mainFormText;
	
	@FXML
	private TextField productCellText;
	
	@FXML
	private Button excuteButton;
	
	@FXML
	private ToggleButton toggleBtn;
	
	@FXML
	private ProgressIndicator progressIndicator;
	
	@FXML
	public TextArea stateTxt;
	
	final User32 libUser32 = User32.INSTANCE;	
	private static volatile boolean seachHwndQuit;
	private static volatile boolean findWindowQuit;
	windowMethod Wmethod = new windowMethod();
	
	private static HWND productHwnd;
	private static HWND accountHwnd;
	private static HWND cardFormHwnd;
	private static HWND mainTabHwnd;
	private static HWND mainFormHwnd;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		addDragListeners(titleBar);
		addActionListeners(setProductBtn);
		addActionListeners(setAccountBtn);
		addActionListeners(setStartBtn);
		progressIndicator.setVisible(false);
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
		/*// Add our custom formatter to a console handler.
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		handler.setLevel(Level.WARNING);
		logger.addHandler(handler);*/
					
		GlobalScreen.setEventDispatcher(new VoidDispatchService());
		
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//GlobalScreen.unregisterNativeHook();
		GlobalScreen.addNativeKeyListener(this);
		//GlobalScreen.removeNativeKeyListener(this);
		GlobalScreen.addNativeMouseListener(this);
		//GlobalScreen.removeNativeMouseListener(this);

		GlobalScreen.addNativeMouseMotionListener(this);
		//GlobalScreen.removeNativeMouseMotionListener(this);	
	
		GlobalScreen.addNativeMouseWheelListener(this);
		//GlobalScreen.removeNativeMouseWheelListener(this);
		

		final ToggleGroup group = new ToggleGroup();

		
		toggleBtn.setToggleGroup(group);
		toggleBtn.setSelected(false);
		
		group.selectedToggleProperty().addListener(
		(ObservableValue<? extends Toggle> ov,
			Toggle toggle, Toggle new_toggle) -> {
				if (new_toggle == null) {
					//false
					seachHwndQuit = true;
					progressIndicator.setVisible(false);
				} else {
					//true
					seachHwndQuit = false;							
					test();
					progressIndicator.setVisible(true);
					progressIndicator.setProgress(-1);
					
				}	

		});
		
	     
		
		excuteButton.setOnAction((event) -> {										 
            			

		});
		

	}
	
	private void createWindow(HWND hwnd) {
		new Thread()  {
			@Override
			public void run() {
				while (!findWindowQuit) {
					if (libUser32.FindWindow(null, hwnd.toString()) == null ) {	
						String windowTitle = Wmethod.getWindowname(hwnd);
		                System.out.println((windowTitle));
						//try {            	
							/*Parent root = FXMLLoader.load(getClass().getResource("/application/inputAccountCodeToScript/cardTabWindow.fxml"));
			                Scene secondScene = new Scene(root, 230, 100);
			                
			                // New window (Stage)
			                Stage newWindow = new Stage();
			                
			                newWindow.setScene(secondScene);
			                //newWindow.initStyle(StageStyle.UNDECORATED);
			                // Set position of second window, related to primary window.
			                newWindow.setX(0);
			                newWindow.setY(0);
			                newWindow.show();
			               */
			                //String windowTitle = Wmethod.getWindowname(hwnd);
			                System.out.println((windowTitle));
			                //ewWindow.setTitle(windowTitle);
							//JFrame jf = new JFrame(windowTitle);
							//jf.setVisible(true);
						/*} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					}
					else {
						String windowTitle = Wmethod.getWindowname(hwnd);
		                System.out.println((windowTitle));
						findWindowQuit= true;
					}
				}
			}
		}.start();
	}
	
	
	private void test() {
	
			new Thread()  {
				@Override
				public void run() {
					while (!seachHwndQuit) {
						
						try {
							Thread.sleep(100);
							//stateTxt.clear();
							final HWND mainHwnd;
							HWND parentHwnd, childHwnd;
							String className, windowName;
							DWORD gwCHILD = new DWORD(User32.GW_CHILD);
							DWORD gwNEXT = new DWORD(User32.GW_HWNDNEXT);
							mainHwnd = libUser32.GetForegroundWindow();														
							
							if (mainHwnd != null) {
								//className = Wmethod.getClassname(mainHwnd);
								//stateTxt.appendText("Title : " + className  + "\n");
								childHwnd = mainHwnd;
								
								do {
									Thread.sleep(1);
									childHwnd = Wmethod.getHWND(childHwnd, mainHwnd);
									className = Wmethod.getClassname(childHwnd);																		
									windowName = Wmethod.getWindowname(childHwnd);																		
									
									className = className == null ? "" : className;
									
									if (className.equals("SPR32DU80EditHScroll") && libUser32.IsWindowVisible(childHwnd)){
										//stateTxt.appendText("ClassName: " +className + "\t" + "WindowName: "+ windowName + "\t" + "Time: " + LocalDateTime.now() + "\n");
										productCellText.setText(childHwnd.toString());										
										mainFormText.setText(libUser32.GetAncestor(childHwnd, 1).toString());
										
										//createWindow(childHwnd);
										//findWindowQuit= false;

									}
									else if (className.equals("#32770") && windowName.equals("57.매입카과")){										
										//stateTxt.appendText("ClassName: " +className + "\t" + "WindowName: "+ windowName + "\t" + "Time: " + LocalDateTime.now() + "\n");
										cardTabText.setText(childHwnd.toString());
									}
									
									
									//stateTxt.appendText(className);
									
									//System.err.println(windowName + "       "+ LocalDateTime.now());
								} while (!mainHwnd.equals(childHwnd) && childHwnd != null);
																
					
							}
							
							else if (mainHwnd == null) {
								
							}
						
						} catch (InterruptedException e) {
						
						}		
					}
				}
			}.start();
	
	}

	
	private void addActionListeners(final Node n) {
		// TODO Auto-generated method stub
		n.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				System.out.println(n.getId());
				if (n.getId().equals("setProductBtn")) {
					subTab.getSelectionModel().select(0);
				}
				else if (n.getId().equals("setAccountBtn")) {
					subTab.getSelectionModel().select(1);
				}
				else if (n.getId().equals("setStartBtn")) {
					subTab.getSelectionModel().select(2);
				}				
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

	private class VoidDispatchService extends AbstractExecutorService {
		private boolean running = false;

		public VoidDispatchService() {
			running = true;
		}

		public void shutdown() {
			running = false;
		}

		public List<Runnable> shutdownNow() {
			running = false;
			return new ArrayList<Runnable>(0);
		}

		public boolean isShutdown() {
			return !running;
		}

		public boolean isTerminated() {
			return !running;
		}

		public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
			return true;
		}

		public void execute(Runnable r) {
			r.run();
		}
	}
	
	private final class LogFormatter extends Formatter {
		@Override
		public String format(LogRecord record) {
			StringBuilder line = new StringBuilder();

			line.append(new Date(record.getMillis()))
				.append(" ")
				.append(record.getLevel().getLocalizedName())
				.append(":\t")
				.append(formatMessage(record));

			if (record.getThrown() != null) {
				try {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					record.getThrown().printStackTrace(pw);
					pw.close();
					line.append(sw.toString());
					sw.close();
				}
				catch (Exception ex) { /* Do Nothing */ }
			}

			return line.toString();
		}
	}
	
	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseWheelMoved(NativeMouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == NativeKeyEvent.VC_B) {			
			try {
				Field f = NativeInputEvent.class.getDeclaredField("reserved");
				f.setAccessible(true);
				f.setShort(arg0, (short) 0x01);				
				
				
				
			}
			catch (Exception ex) {
				System.out.print("[ !! ]\n");
				//ex.printStackTrace();
			}
		}
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		/*if (arg0.getKeyCode() == NativeKeyEvent.VC_B) {
			System.out.print("Attempting to consume B event...\t");
			try {
				Field f = NativeInputEvent.class.getDeclaredField("reserved");
				f.setAccessible(true);
				f.setShort(arg0, (short) 0x01);				
				System.out.print("[ Press OK ]\n");
			}
			catch (Exception ex) {
				System.out.print("[ !! ]\n");
				//ex.printStackTrace();
			}
		}*/
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




}
