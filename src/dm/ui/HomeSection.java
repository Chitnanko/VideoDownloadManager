package dm.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;

public class HomeSection extends HBox{
	
	Label youtubeB=new Label(),
			facebookB=new Label(),
			dailymotionB=new Label(),
			vimeoB=new Label(),
			googleB=new Label();
	HBox videoChannelBox=new HBox(30);
	WebView webView=new WebView();
	WebEngine webEngine=webView.getEngine();
	ProgressBar progressBar=new ProgressBar(10);
	ToolBarBox tool=new ToolBarBox();
	//DownloadBox downloadBox=new DownloadBox();
	ScrollPane scrollpane=new ScrollPane(webView);
	HBox menuButtonBox=new HBox();
	
	MenuBar menubar=new MenuBar();
	Menu categoryMenu=new Menu("Category");
	Menu PCMenu=new Menu("Programming Tutorials Channel");
	Menu OfficeMenu=new Menu("Micosoft Office Tutorials Channel");
	Menu allPC=new Menu("All in One Programming Channel");
	Menu ELCMenu=new Menu("English Learning Channel");
	
	MenuItem javaFxM=new MenuItem("JavaFx Tutorials in Myanmar");
	MenuItem javaBasicM=new MenuItem("Java Basic Programming in Myanmar");
	MenuItem pythonBasicM=new MenuItem("Python basic Programming in Myanmar");
	MenuItem phpBasicM=new MenuItem("PHP Basic Programming in Myanmar");
	MenuItem dbmsM=new MenuItem("My Sql and Database tutorials in Myanmar");
	MenuItem cplusM=new MenuItem("C++ basic programming in Myanmar");
	MenuItem androidE=new MenuItem("Android Development Tutorials in English");
	MenuItem WCmdE=new MenuItem("Windows Command Line Tutorials in English");
	MenuItem linuxE=new MenuItem("Linux Tutorial for Beginners in English");
	MenuItem CPE=new MenuItem("C Programming Tutorials in English");
	MenuItem networkE=new MenuItem("Computer Networking in English");
	MenuItem javaScriptE=new MenuItem("JavaScript Tutorials in English");
	MenuItem pythonE=new MenuItem("Python Game Development in English");
	MenuItem jqueryE=new MenuItem("jQuery Tutorials in English");
	MenuItem mysqlE=new MenuItem("MySQL Database Tutorial in English");
	MenuItem chashE=new MenuItem("C# Beginners Tutorials in English");
	MenuItem phpE=new MenuItem("PHP Tutorials in English");
	MenuItem visualE=new  MenuItem("Visual Basic Tutorials in English");

	MenuItem excelM=new MenuItem("Excel tutorial in Myanmar");
	MenuItem excelE=new MenuItem("Excel tutorial advanced in English");
	MenuItem wordM=new MenuItem("Office word tutorial in Myanmar");
	MenuItem wordE=new MenuItem("Office word tutorial in English");
	MenuItem photoshopM=new MenuItem("Photoshop tutorials in Myanmar");
	MenuItem photoshopE=new MenuItem("Photoshop tutorials in English");
	MenuItem powerpointE=new MenuItem("Power Point tutorials in English");
	
	MenuItem all1=new MenuItem("Thenewboston");
	MenuItem all2=new MenuItem("SlideNerd");
	MenuItem all3=new MenuItem("LevelUpTuts");
	MenuItem all4=new MenuItem("Treehouse");
	MenuItem all5=new MenuItem("Codecourse");
	MenuItem all6=new MenuItem("Google Developers");
	MenuItem all7=new MenuItem("LearnCode.academy");
	MenuItem all8=new MenuItem(" Derek Banas");
	MenuItem all9=new MenuItem("ProgrammingKnowledge");
	MenuItem all10=new MenuItem("Adam Khoury");
	
	MenuItem english1=new MenuItem("English Learning in Myanmar 1");
	MenuItem english2=new MenuItem("English Learning in Myanmar 2");
	MenuItem english3=new MenuItem("Listening for beginner in Myanmar");
	MenuItem english4=new MenuItem("Myanmar Basic English Speaking");
	MenuItem english5=new MenuItem("The best English 4 You");

	Rectangle2D visualBounds=Screen.getPrimary().getVisualBounds();
	
	public void reloadWeb(){
		
		webEngine.reload();
	}
	
	
	public HomeSection(){
		
		youtubeB=setImage(youtubeB,"youtube.jpg");
		facebookB=setImage(facebookB,"facebook.png");
		dailymotionB=setImage(dailymotionB,"dailymotion.jpg");
		vimeoB=setImage(vimeoB,"vimeo.jpg");
		googleB=setImage(googleB,"google.jpg");
		
		PCMenu.getItems().addAll(javaFxM,javaBasicM,pythonBasicM,dbmsM,phpBasicM,cplusM,androidE,WCmdE,linuxE,CPE,networkE,
				javaScriptE,pythonE,jqueryE,mysqlE,chashE,phpE,visualE);
		OfficeMenu.getItems().addAll(excelM,excelE,wordM,wordE,photoshopM,photoshopE,powerpointE);
		allPC.getItems().addAll(all1,all2,all3,all4,all5,all6,all7,all8,all9,all10);
		ELCMenu.getItems().addAll(english1,english2,english3,english4,english5);
		categoryMenu.getItems().addAll(PCMenu,OfficeMenu,allPC,ELCMenu);
		menubar.getMenus().add(categoryMenu);
		menubar.setStyle("-fx-font-size:15;");
		menuButtonBox.getChildren().add(menubar);
		menuButtonBox.setPrefWidth(visualBounds.getWidth());
		menuButtonBox.setAlignment(Pos.BOTTOM_LEFT);
		menuButtonBox.setVisible(false);
		menuButtonBox.setManaged(false);

		scrollpane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollpane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		Label javaFxML=new Label("https://m.youtube.com/playlist?list=PLG6U31JzuVBbPIxFSSEIt5SGGOK7wS1j-");
		javaFxM.setOnAction(MenuAction(javaFxML, progressBar));
		
		Label javaBasicL=new Label("https://m.youtube.com/playlist?list=PLG6U31JzuVBZushEbmG2NbADVq-YEealA");
		javaBasicM.setOnAction(MenuAction(javaBasicL,progressBar));
		
		Label cplusL=new Label("https://m.youtube.com/playlist?list=PLG6U31JzuVBaYsiENWgtCybeCHfH8wPpa");
		cplusM.setOnAction(MenuAction(cplusL, progressBar));
		
		Label pythonL=new Label("https://m.youtube.com/playlist?list=PLG6U31JzuVBbwFVAW2_x6PBa7I4JNvter");
		pythonBasicM.setOnAction(MenuAction(pythonL, progressBar));
		
		Label phpL=new Label("https://m.youtube.com/playlist?list=PLG6U31JzuVBYwejqTcJe1CyiKGPeLCPY3");
		phpBasicM.setOnAction(MenuAction(phpL, progressBar));
		
		Label androidL=new Label("https://www.youtube.com/playlist?list=PL2F07DBCDCC01493A");
		androidE.setOnAction(MenuAction(androidL, progressBar));
		
		Label cmdL=new Label("https://www.youtube.com/playlist?list=PL6gx4Cwl9DGDV6SnbINlVUd0o2xT4JbMu");
		WCmdE.setOnAction(MenuAction(cmdL, progressBar));
		
		Label linuxL=new Label("https://www.youtube.com/playlist?list=PL6gx4Cwl9DGCkg2uj3PxUWhMDuTw3VKjM");
		linuxE.setOnAction(MenuAction(linuxL, progressBar));
		
		Label cpl=new Label("https://www.youtube.com/playlist?list=PL6gx4Cwl9DGAKIXv8Yr6nhGJ9Vlcjyymq");
		CPE.setOnAction(MenuAction(cpl, progressBar));
		
		Label networkL=new Label("https://www.youtube.com/playlist?list=PL6gx4Cwl9DGBpuvPW0aHa7mKdn_k9SPKO");
		networkE.setOnAction(MenuAction(networkL, progressBar));
		
		Label javaScriptL=new Label("https://www.youtube.com/playlist?list=PL46F0A159EC02DF82");
		javaScriptE.setOnAction(MenuAction(javaScriptL, progressBar));
		
		Label pGameL=new Label("https://www.youtube.com/playlist?list=PL6gx4Cwl9DGAjkwJocj7vlc_mFU-4wXJq");
		pythonE.setOnAction(MenuAction(pGameL, progressBar));
		
		Label jqueryL=new Label("https://www.youtube.com/playlist?list=PL6B08BAA57B5C7810");
		jqueryE.setOnAction(MenuAction(jqueryL, progressBar));
		
		Label mysqlL=new Label();
		mysqlE.setOnAction(MenuAction(mysqlL, progressBar));
		
		Label chashL=new Label("https://www.youtube.com/playlist?list=PL0EE421AE8BCEBA4A");
		chashE.setOnAction(MenuAction(chashL, progressBar));
		
		Label phpEl=new Label("https://www.youtube.com/playlist?list=PL442FA2C127377F07");
		phpE.setOnAction(MenuAction(phpEl, progressBar));
		
		Label visualL=new Label("https://www.youtube.com/playlist?list=PLC601DEA22187BBF1");
		visualE.setOnAction(MenuAction(visualL, progressBar));
		
		Label excelML=new Label("https://www.youtube.com/watch?v=yNIQwjMJXgc&list=PL-FUiZjXie1CF-5Zxm_dMBO6AxqOvrkwA");
		excelM.setOnAction(MenuAction(excelML, progressBar));
		
		Label excelEL=new Label("https://www.youtube.com/watch?v=1eZDerepYmA&list=PL55E3ACEB25ACD567");
		excelE.setOnAction(MenuAction(excelEL, progressBar));
		
		Label wordML=new Label("https://www.youtube.com/watch?v=Q5EG8erRwjQ");
		wordM.setOnAction(MenuAction(wordML, progressBar));
		
		Label wordEL=new Label("https://www.youtube.com/watch?v=6xGxcKQXqG4&list=PLwBNdcufIBPubhTb-t0tMdVoZvqndrOBG");
		wordE.setOnAction(MenuAction(wordEL, progressBar));
		
		Label photoshopML=new Label("https://www.youtube.com/watch?v=VxW9_YCc1wo&list=PLFwzW-B4q8fmA4z9V9gnBf2fuwNLPe5nD");
		photoshopM.setOnAction(MenuAction(photoshopML, progressBar));
		
		Label photoshopEL=new Label("https://www.youtube.com/user/NewWorldOps");
		photoshopE.setOnAction(MenuAction(photoshopEL, progressBar));
		
		Label powerpointEL=new Label("https://www.youtube.com/watch?v=TVW1T-kUVlY&list=PLC6FA6351E0F0F888");
		powerpointE.setOnAction(MenuAction(powerpointEL, progressBar));
		
		Label all1L=new Label("https://www.youtube.com/user/thenewboston/playlists");
		all1.setOnAction(MenuAction(all1L, progressBar));
		
		Label all2L=new Label("https://www.youtube.com/user/slidenerd/videos");
		all2.setOnAction(MenuAction(all2L, progressBar));
		
		Label all3L=new Label("https://www.youtube.com/user/LevelUpTuts");
		all3.setOnAction(MenuAction(all3L, progressBar));
		
		Label all4L=new Label("https://www.youtube.com/user/gotreehouse");
		all4.setOnAction(MenuAction(all4L, progressBar));
		
		Label all5L=new Label("https://www.youtube.com/user/phpacademy#p/p");
		all5.setOnAction(MenuAction(all5L, progressBar));
		
		Label all6L=new Label("https://www.youtube.com/user/GoogleDevelopers");
		all6.setOnAction(MenuAction(all6L, progressBar));
		
		Label all7L=new Label("https://www.youtube.com/user/learncodeacademy");
		all7.setOnAction(MenuAction(all7L, progressBar));
		
		Label all8L=new Label("https://www.youtube.com/user/derekbanas");
		all8.setOnAction(MenuAction(all8L, progressBar));
		
		Label all9L=new Label("https://www.youtube.com/user/ProgrammingKnowledge");
		all9.setOnAction(MenuAction(all9L, progressBar));
		
		Label all10L=new Label("https://www.youtube.com/user/flashbuilding");
		all10.setOnAction(MenuAction(all10L, progressBar));
		
		Label english1L=new Label("https://www.youtube.com/watch?v=im5guhbA6tQ&list=PLeOeYFmCGByPtwxFbzdaKcW_gYWiiNiiI");
		english1.setOnAction(MenuAction(english1L, progressBar));
		
		Label english2L=new Label("https://www.youtube.com/watch?v=Ro7s1_arpSs&list=PL7qbWgbNC_qdEy1GnPSSqumHB87m3vBi_");
		english2.setOnAction(MenuAction(english2L, progressBar));
		
		Label english3L=new Label("https://www.youtube.com/watch?v=SJlu--hdFQU&list=PL4IJAxR6Bqq8vP0kEeQRgU6lUe4s4u2DW");
		english3.setOnAction(MenuAction(english3L, progressBar));
		
		Label english4L=new Label("https://www.youtube.com/watch?v=szuK5ewiUTI&list=PLqYhx-J4mrY48LoUAZFtr3IUXTCattySK");
		english4.setOnAction(MenuAction(english4L, progressBar));
		
		Label english5L=new Label("https://www.youtube.com/watch?v=MyqIWFrxc44&list=PL5bG6LtHZVXGSRqQkYsgD8tiXszCR90v_");
		english5.setOnAction(MenuAction(english5L, progressBar));
		

		
		Label youtubeLink=new Label("http://www.youtube.com");
		youtubeB.setOnMousePressed(YoutubeAction(youtubeLink,progressBar));
		youtubeB.setStyle("-fx-cursor:hand");
		
		Label facebookLink=new Label("http://www.facebook.com");
		facebookB.setOnMousePressed(linkAction(facebookLink, progressBar));
		facebookB.setStyle("-fx-cursor:hand");
		
		Label vimeoLink=new Label("http://www.vimeo.com");
		vimeoB.setOnMousePressed(linkAction(vimeoLink, progressBar));
		vimeoB.setStyle("-fx-cursor:hand");
		
		Label dailymotionLink=new Label("http://www.dailymotion.com");
		dailymotionB.setOnMousePressed(linkAction(dailymotionLink, progressBar));
		dailymotionB.setStyle("-fx-cursor:hand");
		
		Label googleLink=new Label("http://www.google.com");
		googleB.setOnMousePressed(linkAction(googleLink, progressBar));
		googleB.setStyle("-fx-cursor:hand");
		
		webView.setPrefWidth(visualBounds.getWidth());
		progressBar.setPrefWidth(visualBounds.getWidth());
		progressBar.setVisible(false);
		progressBar.setManaged(false);
		progressBar.setPrefHeight(10);
		progressBar.setMinHeight(10);

		webView.setVisible(false);
		webView.setManaged(false);
		videoChannelBox.getChildren().addAll(youtubeB,dailymotionB,facebookB,vimeoB,googleB);
		videoChannelBox.setAlignment(Pos.CENTER);
		videoChannelBox.setVisible(true);
	}
	
	public Label setImage(Label imageLabel,String imageUrl){
		Image image=new Image(getClass().getResourceAsStream(imageUrl));
		ImageView imageView=new ImageView(image);
		imageLabel.setGraphic(imageView);
		return imageLabel;
	}
	public EventHandler<ActionEvent> buttonAction(final TextField pageUrl,
			 final ProgressBar progressBar,HBox DBox,HBox dOp) {
			 	return new EventHandler<ActionEvent>() {
					 @Override
					 public void handle(ActionEvent event) {
						 progressBar.setVisible(true);
						 progressBar.setManaged(false);
						 videoChannelBox.setVisible(false);
						 videoChannelBox.setManaged(false);
						 webView.setVisible(true);
						 webView.setManaged(true);
						 DBox.setVisible(false);
						 DBox.setManaged(false);
						 dOp.setVisible(false);
						 dOp.setManaged(false);
						 progressBar.setVisible(true);
						 progressBar.setManaged(true);
						 progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
						 webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
							 @Override
							 public void changed(ObservableValue<? extends State> value,
									 State oldState, State newState) {
								 		if(!webEngine.getLocation().isEmpty()){
								 			progressBar.setVisible(true);
											 progressBar.setManaged(true);
											 if(newState == State.SUCCEEDED){
										 			progressBar.setVisible(false);
										 			 progressBar.setManaged(false);
										 		}
								 		}
							 }
						  });
						  webEngine.load(pageUrl.getText());
					  }
			  };
		  }

	private EventHandler<MouseEvent> linkAction(final Label pageUrl,
			 final ProgressBar progressBar) {
			 	return new EventHandler<MouseEvent>() {
					 @Override
					 public void handle(MouseEvent event) {
						 videoChannelBox.setVisible(false);
						 videoChannelBox.setManaged(false);
						 webView.setVisible(true);
						 webView.setManaged(true);
						 progressBar.setVisible(true);
						 progressBar.setManaged(true);
						 progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
						 webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
							 @Override
							 public void changed(ObservableValue<? extends State> value,
									 State oldState, State newState) {
								 		if(!webEngine.getLocation().isEmpty()){
								 			progressBar.setVisible(true);
											 progressBar.setManaged(true);
											 if(newState == State.SUCCEEDED){
										 			progressBar.setVisible(false);
										 			 progressBar.setManaged(false);
										 			menuButtonBox.setVisible(false);
													 menuButtonBox.setManaged(false);
										 		}
								 		}
							 }
						  });
						  webEngine.load(pageUrl.getText());
					  }
			  };
		  }
	private EventHandler<ActionEvent> MenuAction(final Label pageUrl,
			 final ProgressBar progressBar) {
			 	return new EventHandler<ActionEvent>() {
					 @Override
					 public void handle(ActionEvent event) {
						 videoChannelBox.setVisible(false);
						 videoChannelBox.setManaged(false);
						 webView.setVisible(true);
						 webView.setManaged(true);
						 progressBar.setVisible(true);
						 progressBar.setManaged(true);
						 progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
						 webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
							 @Override
							 public void changed(ObservableValue<? extends State> value,
									 State oldState, State newState){
								 menuButtonBox.setVisible(false);
								 menuButtonBox.setManaged(false);
								 		if(!webEngine.getLocation().isEmpty()){
								 			progressBar.setVisible(true);
											 progressBar.setManaged(true);
											 if(newState == State.SUCCEEDED){
										 			progressBar.setVisible(false);
										 			 progressBar.setManaged(false);
										 			 menuButtonBox.setVisible(true);
										 			 menuButtonBox.setManaged(true);
										 		}
								 		}
							 }
						  });
						  webEngine.load(pageUrl.getText());
					  }
			  };
	}
	private EventHandler<MouseEvent> YoutubeAction(final Label pageUrl,
			 final ProgressBar progressBar) {
			 	return new EventHandler<MouseEvent>() {
					 @Override
					 public void handle(MouseEvent event) {
						 videoChannelBox.setVisible(false);
						 videoChannelBox.setManaged(false);
						 webView.setVisible(true);
						 webView.setManaged(true);
						 progressBar.setVisible(true);
						 progressBar.setManaged(true);
						 progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
						 webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
							 @Override
							 public void changed(ObservableValue<? extends State> value,
									 State oldState, State newState){
								 menuButtonBox.setVisible(false);
								 menuButtonBox.setManaged(false);
								 		if(!webEngine.getLocation().isEmpty()){
								 			progressBar.setVisible(true);
											 progressBar.setManaged(true);
											 if(newState == State.SUCCEEDED){
										 			progressBar.setVisible(false);
										 			 progressBar.setManaged(false);
										 			 menuButtonBox.setVisible(true);
										 			 menuButtonBox.setManaged(true);
										 		}
								 		}
							 }
						  });
						  webEngine.load(pageUrl.getText());
					  }
			  };
		  }


}
