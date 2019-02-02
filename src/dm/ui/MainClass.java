package dm.ui;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.beans.value.ObservableValue;

public class MainClass extends Application implements EventHandler<ActionEvent>{

	private double dragOffSetX;
	private double dragOffSetY;
	private Stage stage;
	Button minimizeB,maximizeB,closeB;
	TaskBarBox taskItem=new TaskBarBox();
	HomeSection home=new HomeSection();
	ToolBarBox tool=new ToolBarBox();
	HBox operationBox=new HBox();
	HBox toolBarP=new HBox();
	HBox 	mainWindowP=new HBox();
	HBox mainPanel=new HBox();
	DownloadBox dBox=new DownloadBox();
	AddUrlDialogBox adb=new AddUrlDialogBox();
	SettingBox setting;
	String webUrl;
	Task<Void> task;
	Button cancel;
	DownloadLinkDialog d;
	public void start(Stage MainStage) throws Exception{
		
		tool.refreshB.setOnAction(e->home.reloadWeb());
		tool.urlField.setOnAction(home.buttonAction(tool.urlField, home.progressBar,dBox.DBox,tool.downloadOP));
		tool.goB.setOnAction(home.buttonAction(tool.urlField, home.progressBar,dBox.DBox,tool.downloadOP));
		//home.getPageLink(tool.urlField);
		
		this.stage=MainStage;
		setting=new SettingBox(stage, Modality.NONE);
		Rectangle2D visualBounds=Screen.getPrimary().getVisualBounds();
		MainStage.setX(visualBounds.getMinX());
		MainStage.setY(visualBounds.getMinY());
		MainStage.setWidth(visualBounds.getWidth());
		MainStage.setHeight(visualBounds.getHeight());
		MainStage.initStyle(StageStyle.UNDECORATED);
		
		minimizeB=new Button();
		minimizeB=setImage(minimizeB,"minimize.png");
		minimizeB.setOnAction(this);
		
		maximizeB=new Button();
		maximizeB=setImage(maximizeB,"maximize.png");
		maximizeB.setOnAction(this);
		
		closeB=new Button();
		closeB=setImage(closeB,"close.jpg");
		closeB.setOnAction(this);

		operationBox.getChildren().addAll(minimizeB,maximizeB,closeB);
		
		toolBarP.setLayoutX(MainStage.getX());
		toolBarP.setLayoutY(MainStage.getY());
		toolBarP.setPrefSize(MainStage.getWidth(), MainStage.getHeight()/10);
		toolBarP.setMinHeight(MainStage.getHeight()/10);
		toolBarP.getChildren().addAll(tool.toolBox,operationBox);
		toolBarP.setAlignment(Pos.TOP_RIGHT);
		toolBarP.setOnMousePressed(e->handleMousePressed(e));
		toolBarP.setOnMouseDragged(e->handleMouseDragged(e));
		
		tool.downloadB.setOnAction(e->{
			
			System.out.println("downloaded button clicked");
			d=new DownloadLinkDialog(MainStage,Modality.NONE);
			
			task=new Task<Void>(){

				@Override
				protected Void call(){
					DownloadLink.setWeb(webUrl);
					System.out.println("i am main class");
					return null;
				}
				
			};
			
			new Thread(task).start();
	
			d.indicator.progressProperty().bind(task.progressProperty());
			task.setOnSucceeded(event->{
				if(d.searchStage.isShowing()){
					d.searchStage.close();
				}
				d.indicator.progressProperty().unbind();
				d.downloadInfoList=DownloadLink.getInstance().getDownloadInfo();
				d.downloadList=DownloadLink.getInstance().getDownloadLink();
				
				d.listView.getItems().addAll(d.downloadInfoList);
				//d.listView.setPrefSize(d.listSize.getWidth(),d.listSize.getHeight());
				
				d.hbox.getChildren().clear();
				d.hbox.getChildren().add(d.listView);
				d.vbox.getChildren().clear();
				d.vbox.getChildren().addAll(d.hbox,d.bbox);
				d.searchStage.show();
				
				
				d.downloadB.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
						int i=d.listView.getSelectionModel().getSelectedIndex();
						//String str="downloading.....";
						Label s=new Label(d.listView.getSelectionModel().getSelectedItem());
						dBox.downloadSectList.getItems().add(s);
						if(i>=0)
							System.out.println(i);
							//DownloadFile.setDownloadBox(dBox);
							d.createDownload(i,dBox);
							d.searchStage.close();
					}
					
					});
			});
					
			
		});
		
		
		home.webEngine.locationProperty().addListener(
				(ObservableValue<? extends String> prop,
				String oldValue, String newValue) ->{
					tool.urlField.setText(newValue);
					//home.webEngine.load(newValue);
					System.out.println("i am webengine");
					if(DownloadLink.isParserlink(newValue)){
								webUrl=newValue;
								tool.downloadB.setVisible(true);
								tool.downloadB.setManaged(true);
					}else{
						tool.downloadB.setVisible(false);
						tool.downloadB.setManaged(false);
					}
					System.out.println("out of webengine ");
				}
				);

		
		
		HBox downloadToolP=new HBox();
		downloadToolP.setLayoutX(MainStage.getX());
		downloadToolP.setLayoutY(MainStage.getY()+MainStage.getHeight()/10);
		downloadToolP.setPrefSize(MainStage.getWidth(),MainStage.getHeight()/21);
		downloadToolP.setMinHeight(MainStage.getHeight()/21);
		downloadToolP.getChildren().addAll(home.menuButtonBox,home.progressBar,tool.downloadOP);
		downloadToolP.setAlignment(Pos.BOTTOM_RIGHT);
	
		taskItem.homeB.setOnAction(this);
		taskItem.downloadB.setOnAction(this);
		taskItem.addUrlB.setOnAction(this);
		taskItem.settingB.setOnAction(this);
		tool.menu.addUrl.setOnAction(this);
		tool.menu.option.setOnAction(this);
		tool.menu.dmHome.setOnAction(this);

		mainPanel.getChildren().addAll(home.videoChannelBox,home.webView);
		mainPanel.setVisible(true);
		mainPanel.setManaged(true);
		mainWindowP.setLayoutX(MainStage.getX());
		mainWindowP.setLayoutY(MainStage.getY()+(MainStage.getHeight()/6.5));
		mainWindowP.setPrefSize(MainStage.getWidth(),((MainStage.getHeight()/4)*3) +MainStage.getHeight()/30);
		mainWindowP.getChildren().addAll(mainPanel,dBox.DBox);
		mainWindowP.setAlignment(Pos.CENTER);		
		ScrollPane sPane=new ScrollPane(mainWindowP);
		sPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		sPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		HBox	taskBarP=new HBox();
		taskBarP.getChildren().add(taskItem.hBox);
		taskBarP.setAlignment(Pos.CENTER);
		taskBarP.setLayoutX(MainStage.getX());
		taskBarP.setLayoutY(MainStage.getY()+(((MainStage.getHeight()/4)*3)+MainStage.getHeight()/6.5));
		taskBarP.setPrefSize(MainStage.getWidth(),MainStage.getHeight()/12);
		taskBarP.setMinHeight(MainStage.getHeight()/12);
		
		HBox hBox=new HBox();
		hBox.setPrefSize(MainStage.getWidth(),MainStage.getHeight());
		
		VBox vBox=new VBox();
		vBox.setPrefSize(hBox.getWidth(),hBox.getHeight());
		vBox.getChildren().addAll(toolBarP,downloadToolP,sPane,taskBarP);
		VBox.setVgrow(toolBarP, Priority.NEVER);
		VBox.setVgrow(downloadToolP, Priority.NEVER);
		vBox.setAlignment(Pos.BASELINE_CENTER);
		VBox.setVgrow(sPane, Priority.ALWAYS);
		VBox.setVgrow(taskBarP, Priority.NEVER);
		hBox.getChildren().addAll(vBox);
		HBox.setHgrow(vBox, Priority.ALWAYS);

		toolBarP.setStyle("-fx-padding: 0;" +
				"-fx-background-color:black;");
		downloadToolP.setStyle("-fx-background-color:black;");
		mainWindowP.setStyle("-fx-padding: 0;" +
				"-fx-background-color:white;");
		taskBarP.setStyle("-fx-padding: 0;" +
				"-fx-background-color:black;");
		vBox.setStyle("-fx-padding: 5;" +
				"-fx-background-color:black;");
		
		Scene scene=new Scene(hBox);
		MainStage.setScene(scene);
		ResizeHelper.addResizeListener(MainStage);
		MainStage.show();
	}
	private void handleMousePressed(MouseEvent e) {
		this.dragOffSetX=e.getScreenX()-stage.getX();
		this.dragOffSetY=e.getScreenY()-stage.getY();
	}
	private void handleMouseDragged(MouseEvent e) {
		stage.setX(e.getScreenX()-this.dragOffSetX);
		stage.setY(e.getScreenY()-this.dragOffSetY);
	}
	public Button setImage(Button imageButton,String imageUrl){
		Image image=new Image(getClass().getResourceAsStream(imageUrl));
		imageButton.setGraphic(new ImageView(image));
		imageButton.setPrefSize(10,10);
		imageButton.setStyle("-fx-background-color:black");
		return imageButton;
	}
	public static void main(String[] dm) {
		launch(dm);
	}
	
	public void handle(ActionEvent e) {
		if(e.getSource()==minimizeB){
			Stage minimizeStage=(Stage)(((Button)e.getSource()).getScene().getWindow());
			minimizeStage.setIconified(true);
		}
		if(e.getSource()==maximizeB){
			if(stage.isMaximized()){
				stage.setMaximized(false);
				stage.setWidth(900);
				stage.setHeight(700);
			}else
			{
				stage.setMaximized(true);
				stage.setWidth(stage.getWidth());
				stage.setHeight(stage.getHeight());
			}
		}
		if(e.getSource()==closeB){
			stage.close();
		}
		if(e.getSource()==taskItem.homeB||e.getSource()==tool.menu.dmHome){
			mainPanel.setVisible(true);
			mainPanel.setManaged(true);
			if(home.webView.isVisible()||dBox.DBox.isVisible()){
				home.webView.setVisible(false);
				home.webView.setManaged(false);
				dBox.DBox.setVisible(false);
				dBox.DBox.setManaged(false);
				home.videoChannelBox.setVisible(true);
				home.videoChannelBox.setManaged(true);
				tool.downloadOP.setVisible(false);
				tool.downloadOP.setManaged(false);
				taskItem.deleteItemB.setVisible(false);
				taskItem.deleteItemB.setManaged(false);
				taskItem.pauseB.setVisible(false);
				taskItem.pauseB.setManaged(false);
				taskItem.resumeB.setVisible(false);
				taskItem.resumeB.setManaged(false);
				home.progressBar.setVisible(false);
				home.progressBar.setManaged(false);
				home.menuButtonBox.setVisible(false);
				home.menuButtonBox.setManaged(false);
				tool.urlField.setText("");
			}
		}
		if(e.getSource()==taskItem.downloadB){
			if(mainPanel.isVisible()||home.webView.isVisible()){
				home.webView.setVisible(false);
				home.webView.setManaged(false); 
				mainPanel.setVisible(false);
				mainPanel.setManaged(false);
				dBox.DBox.setVisible(true);
				dBox.DBox.setManaged(true);
				tool.downloadOP.setVisible(true);
				tool.downloadOP.setManaged(true);
			taskItem.deleteItemB.setVisible(true);
			taskItem.deleteItemB.setManaged(true);
			taskItem.pauseB.setVisible(true);
			taskItem.pauseB.setManaged(true);
			taskItem.resumeB.setVisible(true);
			taskItem.resumeB.setManaged(true);
			home.progressBar.setVisible(false);
			home.progressBar.setManaged(false);
			home.menuButtonBox.setVisible(false);
			home.menuButtonBox.setManaged(false);
			tool.urlField.setText("");
			}
		}
		if(e.getSource()==taskItem.addUrlB||e.getSource()==tool.menu.addUrl){
			try {
				adb.display(stage,Modality.NONE,dBox);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==taskItem.settingB||e.getSource()==tool.menu.option){
			try {
				setting.display();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
}
