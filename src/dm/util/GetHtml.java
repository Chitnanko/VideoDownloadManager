package dm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GetHtml implements Runnable {
	
	
	 HttpURLConnection conn = null;
	 private StringBuilder contents;
	 
	 @SuppressWarnings("unused")
	private GetHtml(){
		 
	 }
	 
	 public GetHtml(URL Url){
		 System.out.println("i am getHtml");
		 
		 try {
		        conn = (HttpURLConnection)Url.openConnection();
		        conn.setConnectTimeout(10000);
		       
		 }catch(Exception e){
			 
		 }
	 }
	 
	public String getHtmlpage(){
		
		 Thread thread=new Thread(this,"getHtmlThread");
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("eroor thread");
				e.printStackTrace();
			}
			
			System.out.println("i am GetHtml");
			return contents.toString();
	}
		
		   
	@Override
	public void run() {
		
		 contents= new StringBuilder();
		    try {
		        //conn = (HttpURLConnection) conn.openConnection();
		        //conn.setConnectTimeout(5000);
		        //conn.setReadTimeout(READ_TIMEOUT);

		        InputStream is = conn.getInputStream();

		        String enc = conn.getContentEncoding();

		        if (enc == null) {
		            Pattern p = Pattern.compile("charset=(.*)");
		            Matcher m = p.matcher(conn.getHeaderField("Content-Type"));
		            if (m.find()) {
		                enc = m.group(1);
		            }
		        }

		        if (enc == null)
		            enc = "UTF-8";

		        BufferedReader br = new BufferedReader(new InputStreamReader(is, enc));

		        String line = null;


		        while ((line = br.readLine()) != null) {
		            contents.append(line);
		            contents.append("\n");

		        }
		    }catch(IOException e){
		    	System.out.println("get link error");
		    	//e.printStackTrace();
		    	Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						Alert alert=new Alert(AlertType.ERROR);
						alert.setHeaderText("Connection timeout");
						alert.setContentText("Please check your connection");
						alert.show();
						
					}
				});
		    }
		 }

		
	}		
