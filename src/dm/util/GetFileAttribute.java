package dm.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class GetFileAttribute{ 
	
	private HttpURLConnection conn;
	private static GetFileAttribute fileAttribute;
	
	
	@SuppressWarnings("unused")
	private GetFileAttribute(){
		
	}
	private GetFileAttribute(URL downloadUrl){
		
		System.out.println("i am GetFileAttribute"); 
		
			try {
			 	
		        conn = (HttpURLConnection)downloadUrl.openConnection();
		        conn.connect();
		 }catch(Exception e){
			 System.out.println("Connection timeout");
			 
		 }
	}
	
	public static class FileSize{
		public float size;
		public String unit;
	}
	
	static long Kb=1*1000;
	static long Mb=Kb*1000;
	static long Gb=Mb*1000;
	

	public String getContentType(){
		return conn.getContentType();
	}
	
	public float getFormat(float value){
		DecimalFormat df=new DecimalFormat("#.##");
		float f=Float.valueOf(df.format(value));
		return f;
	}
	public FileSize getSize(){
			
			 FileSize fsize=new FileSize();
			 float size=(float)conn.getContentLengthLong();
			
			if(size==-1){
				return null;
			}
			if(size<Kb){
				fsize.size=size;
				fsize.unit="bytes";
			}
			
			if(size>=Kb && size<Mb){
				fsize.size=(getFormat(size/Kb));
				fsize.unit="Kb";
			}
			
			if(size>=Mb && size<Gb){
				fsize.size=(getFormat(size/Mb));
				fsize.unit="Mb";
			}
			
			if(size>=Gb){
				fsize.size=(getFormat(size/Gb));
				fsize.unit="Gb";
			}
			
			return fsize;

	 }
	public static void setdonwloadLink(URL url){
		
		fileAttribute=new GetFileAttribute(url);
	}
	public static GetFileAttribute getInstance(){
		return fileAttribute;
	}
	
}
