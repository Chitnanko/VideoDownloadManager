package dm.ui;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class AddUrlDialogBox{
	
	private double dragOffSetX;
	private double dragOffSetY;
	public Stage stage;
	
		public  void display(Window owmer,Modality madality,DownloadBox d) throws Exception{
			//BufferedReader br=new BufferedReader(new FileReader("filePath.txt"));
			String s="";
			HBox dialogToolBox=new HBox(),
					dialogMainBox=new HBox(),
					dialogTaskBox=new HBox(50),
					addressBox=new HBox(20),
					fileBox=new HBox(50);
			DownloadBox dBox=d;
			HBox opBox=new HBox();
			HBox hBox=new HBox();
			
			VBox allVBox=new VBox(),
					mainVBox=new VBox(40);
			
			Button minimizeD=new Button(),
					closeD=new Button(),
					downloadBD=new Button("Download"),
					cancelBD=new Button("Cancel");
			Button chooseFilePath=new Button("Directory");
			
			Label title=new Label("New Download"),
					address=new Label("Address:"),
					file=new Label("File:");
			Line line=new Line(),
					line1=new Line();
			
			TextField addressField=new TextField(),
					fileAddressField=new TextField(DownloadFile.DEFAULT_OUT_FOLDER);
			
			/*while((s=br.readLine()) != null){
				fileAddressField.setText(s);
			}*/
			//br.close();
			Rectangle2D visualBounds=Screen.getPrimary().getVisualBounds();
			
			final DirectoryChooser directoryChooser=new DirectoryChooser();
			configuringDirectory(directoryChooser);

		Stage stage=new Stage();
		this.stage=stage;
		stage.initOwner(owmer);
		stage.initModality(madality);
		stage.setWidth(visualBounds.getWidth()/3);
		stage.setHeight(visualBounds.getHeight()/3);
		
		dialogToolBox.setPrefWidth(stage.getWidth());
		dialogToolBox.setPrefHeight(stage.getHeight()/8);
		title.setPrefWidth((stage.getWidth()/4)*3);
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Zawgyi-One",FontWeight.BOLD,20));
		
		HBox.setMargin(title, new Insets(0,0,0,5));
		
		closeD=setImage(closeD,"close1.jpg");
		closeD.setOnAction(e->stage.close());
		minimizeD=setImage(minimizeD,"minimize1.png");
		minimizeD.setOnAction(e->{
			Stage minimizeStage=(Stage)(((Button)e.getSource()).getScene().getWindow());
			minimizeStage.setIconified(true);
		});
		
		chooseFilePath.setOnAction(e->{
			File dir=directoryChooser.showDialog(stage);
			if(dir!=null){
				fileAddressField.setText(dir.getAbsolutePath());
			}
			else{
				fileAddressField.setText(null);
			}
		});
		
		downloadBD.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String address=addressField.getText();
				if(address!=null){
				URL url=null;
				try {
					url=new URL(address);
				} catch (MalformedURLException e) {
	
					System.out.println("url error");
				}
				String title=address.substring(address.lastIndexOf('/')+1,address.length());
				String fileType=address.substring(address.lastIndexOf('.'),address.lastIndexOf('.')+3);
				dBox.downloadSectList.getItems().add(new Label(title));
				new DownloadFile(url,title,fileType);
			}
			}
		});
		
		dialogToolBox.setStyle("-fx-background-color:gray;");
		opBox.getChildren().addAll(minimizeD,closeD);
		opBox.setPrefWidth(stage.getWidth()/4);
		opBox.setAlignment(Pos.TOP_RIGHT);
		dialogToolBox.getChildren().addAll(title,opBox);
		dialogToolBox.setOnMousePressed(e->handleMousePressed(e));
		dialogToolBox.setOnMouseDragged(e->handleMouseDragged(e));
		
		dialogMainBox.setPrefWidth(stage.getWidth());
		dialogMainBox.setPrefHeight((stage.getHeight()/4)*3);
		dialogMainBox.setStyle("-fx-background-color:gray;-fx-padding:5;");
		address.setTextFill(Color.WHITE);
		address.setFont(Font.font(15));
		file.setTextFill(Color.WHITE);
		file.setFont(Font.font(15));
		hBox.getChildren().addAll(fileAddressField,chooseFilePath);
		addressBox.getChildren().addAll(address,addressField);
		fileBox.getChildren().addAll(file,hBox);
		mainVBox.setAlignment(Pos.CENTER_LEFT);
		mainVBox.getChildren().addAll(addressBox,fileBox);
		VBox.setMargin(mainVBox, new Insets(20,23,43,23));
		dialogMainBox.setAlignment(Pos.CENTER_LEFT);
		dialogMainBox.getChildren().addAll(mainVBox);
		
		dialogTaskBox.setPrefWidth(stage.getWidth());
		dialogTaskBox.setPrefHeight(stage.getHeight()/4);
		dialogTaskBox.setStyle("-fx-padding:3;");
		downloadBD.setFont(Font.font(15));
		cancelBD.setFont(Font.font(15));
		dialogTaskBox.getChildren().addAll(cancelBD,downloadBD);
		dialogTaskBox.setAlignment(Pos.CENTER);
		dialogTaskBox.setStyle("-fx-background-color:gray;");
		
		HBox.setHgrow(dialogMainBox, Priority.ALWAYS);
		HBox.setHgrow(fileAddressField, Priority.ALWAYS);
		HBox.setHgrow(addressField, Priority.ALWAYS);
		HBox.setHgrow(address, Priority.NEVER);
		HBox.setHgrow(file, Priority.NEVER);
		HBox.setHgrow(chooseFilePath, Priority.NEVER);
		HBox.setHgrow(hBox, Priority.ALWAYS);
		HBox.setHgrow(addressBox, Priority.ALWAYS);
		HBox.setHgrow(fileBox, Priority.ALWAYS);
		VBox.setVgrow(allVBox, Priority.ALWAYS);
		
		cancelBD.setOnAction(e->stage.close());
		
		allVBox.setStyle("-fx-padding:3;");
		allVBox.getChildren().addAll(dialogToolBox,line,dialogMainBox,line1,dialogTaskBox);
		Scene scene=new Scene(allVBox);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.showAndWait();
	}
		
	private void handleMousePressed(MouseEvent e) {
		this.dragOffSetX=e.getScreenX()-stage.getX();
		this.dragOffSetY=e.getScreenY()-stage.getY();
	}
	private void handleMouseDragged(MouseEvent e) {
		stage.setX(e.getScreenX()-this.dragOffSetX);
		stage.setY(e.getScreenY()-this.dragOffSetY);
	}
	private void configuringDirectory(DirectoryChooser directoryChooser){
		directoryChooser.setTitle("Select Some Directories");
		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}
	public Button setImage(Button imageButton,String imageUrl){
		Image image=new Image(getClass().getResourceAsStream(imageUrl));
		imageButton.setGraphic(new ImageView(image));
		imageButton.setPrefSize(10,10);
		imageButton.setStyle("-fx-background-color:gray;");
		return imageButton;
	}

}
