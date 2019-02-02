package dm.ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.ProgressBar;

public class DmProgress{
	
	private ProgressBar pb;
	private HBox hbox,lbox,bbox;
	private VBox vbox;
	private Label progressText;
	private Button pause,cancel,resume;
	private String st;
	private Rectangle2D listSize;
	private DownloadFile callObj;
	private Stage stage;
	
	public DmProgress(String status,String title,DownloadFile caller){
		
		callObj=caller;
		progressText=new Label(status);
		
		pb=new ProgressBar(0);
		hbox=new HBox();
		lbox=new HBox();
		bbox=new HBox();
		vbox=new VBox();
		pause=new Button("pause");
		cancel=new Button("cancel");
		resume=new Button("resume");
		stage=new Stage();
		listSize=Screen.getPrimary().getVisualBounds();
		
		hbox.getChildren().addAll(pb);
		lbox.getChildren().add(progressText);
		bbox.getChildren().addAll(pause,resume,cancel);
		resume.setVisible(false);
		resume.setManaged(false);
		bbox.setAlignment(Pos.CENTER);
		bbox.setSpacing(10);
		
		//pi.setPrefSize(listSize.getWidth()/20,hbox.getHeight()/10);
		HBox.setHgrow(hbox, Priority.ALWAYS);
		HBox.setHgrow(pb,Priority.ALWAYS);
		HBox.setMargin(pb,new Insets(30,0,0,0));
		
		
		vbox.getChildren().addAll(hbox,lbox,bbox);
		vbox.setSpacing(20);
	
		Scene scene=new Scene(vbox);
		stage.setScene(scene);
		stage.setWidth(listSize.getWidth()/3);
		stage.setHeight(listSize.getHeight()/4);
		pb.setPrefSize(stage.getWidth(),20);
		stage.setResizable(false);
		stage.show();
		
		pause.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				callObj.pause();
				System.out.println("clicked pause");
				
				pause.setVisible(false);
				pause.setManaged(false);
				resume.setVisible(true);
				resume.setManaged(true);
				
			}
		});
		
		resume.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				callObj.resume();
				System.out.println("clicked resume");
				resume.setVisible(false);
				resume.setManaged(false);
				pause.setVisible(true);
				pause.setManaged(true);
				
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				closeStage();
				System.out.println(" clicked cancel");
				callObj.cancel();
				
			}
		});
		
		
	}

	public void update(String status,float progress) {
		
			
			pb.setProgress(progress);
			//pi.setProgress(progress);
			st=status;
			progressText.setText(st);
			
	}
	
	public void closeStage(){
		stage.close();
	}

}
