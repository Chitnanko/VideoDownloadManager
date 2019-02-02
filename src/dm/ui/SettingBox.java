package dm.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

public class SettingBox {
	private double dragOffSetX;
	private double dragOffSetY;
	public Stage stage;
	
	
	
		public SettingBox(Window owmer,Modality madality) throws Exception{
			
			
			HBox dialogToolBox=new HBox(),
					dialogMainBox=new HBox(),
					dialogTaskBox=new HBox(50),
					formatBox=new HBox(),
					fileBox=new HBox();
			HBox opBox=new HBox();
			HBox hBox=new HBox();
			
			VBox allVBox=new VBox(),
					mainVBox=new VBox(30);
			
			Button minimizeD=new Button(),
					closeD=new Button(),
					defaultB=new Button("Set Default");
			
			Button chooseFilePath=new Button("Directory");
			
			Label title=new Label("Options"),
					folder=new Label("Default Folder:  "),
					fileType=new Label("File Type:  ");
			Line line=new Line(),
					line1=new Line();
			
			TextField fileAddressField=new TextField();
			TextArea formatArea=new TextArea();
			formatArea.setText("3GP 7Z AAC ACE AIF ARJ ASF AVI BIN BZ2 "+"\n"
								+ "EXE DEB DMG GZ GZIP ISO LZH M4A M4V MOV MP3 MP4 MPA MPE"+"n"
								+ " MPEG MPG MSI MSU OGG PDF PLJ PPS PPT QT RA RAR RM RPM S"+"\n"
								+ "EA SIT SITX TAR TIF TIFF WAV WMA WMV Z ZIP JAR TLZ TBZ2 TXZ"+"\n"
								+ " XZ CBZ PKG RUN ");
			formatArea.setPrefColumnCount(3);
			formatArea.setPrefRowCount(4);
			
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
		
		

		try{
			
			BufferedReader br=new BufferedReader(new FileReader("config.txt"));
			String dir=br.readLine();
			if(dir!=null){
				DownloadFile.DEFAULT_OUT_FOLDER=dir;
				System.out.println(dir);
				fileAddressField.setText(dir);
			}
			br.close();
			
		}catch(Exception e){
			
		}
		
		minimizeD.setOnAction(e->{
			Stage minimizeStage=(Stage)(((Button)e.getSource()).getScene().getWindow());
			minimizeStage.setIconified(true);
		});
		
		chooseFilePath.setOnAction(e->{
			File dir=directoryChooser.showDialog(stage);
			if(dir!=null){
				//pw.println(dir.getAbsolutePath());
				fileAddressField.setText(dir.getAbsolutePath());
			}
			else{
				//pw.println("");
				fileAddressField.setText(null);
			}
		});
		defaultB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String dir=fileAddressField.getText();
				System.out.println(dir);
				if(dir!=null){
					try {
						dir=dir+"/";
						DownloadFile.DEFAULT_OUT_FOLDER=dir;
						new File(dir+"vdm").mkdir();
							
						PrintWriter br=new PrintWriter(new FileWriter("config.txt"),false);
						br.println(dir);
						System.out.println(dir);
						br.close();
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
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
		folder.setTextFill(Color.WHITE);
		folder.setFont(Font.font(15));
		fileType.setTextFill(Color.WHITE);
		fileType.setFont(Font.font(15));
		hBox.getChildren().addAll(fileAddressField,chooseFilePath);
		formatBox.getChildren().addAll(fileType,formatArea);
		fileBox.getChildren().addAll(folder,hBox);
		mainVBox.setAlignment(Pos.TOP_LEFT);
		mainVBox.getChildren().addAll(fileBox,formatBox);
		VBox.setMargin(mainVBox, new Insets(20,23,43,23));
		dialogMainBox.setAlignment(Pos.TOP_LEFT);
		dialogMainBox.getChildren().addAll(mainVBox);
		
		dialogTaskBox.setPrefWidth(stage.getWidth());
		dialogTaskBox.setPrefHeight(stage.getHeight()/4);
		dialogTaskBox.setStyle("-fx-padding:3;");
		dialogTaskBox.getChildren().addAll(defaultB);
		dialogTaskBox.setAlignment(Pos.CENTER);
		dialogTaskBox.setStyle("-fx-background-color:gray;");
		
		HBox.setHgrow(dialogMainBox, Priority.ALWAYS);
		HBox.setHgrow(fileAddressField, Priority.ALWAYS);
		HBox.setHgrow(chooseFilePath, Priority.NEVER);
		HBox.setHgrow(hBox, Priority.ALWAYS);
		HBox.setHgrow(formatArea, Priority.ALWAYS);
		HBox.setHgrow(fileType, Priority.NEVER);
		HBox.setHgrow(fileBox, Priority.ALWAYS);
		VBox.setVgrow(allVBox, Priority.ALWAYS);
		
		allVBox.setStyle("-fx-padding:3;");
		allVBox.getChildren().addAll(dialogToolBox,line,dialogMainBox,line1,dialogTaskBox);
		Scene scene=new Scene(allVBox);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		
	}
		
	public void display(){
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
