package dm.abstractclass;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLInfo {
	
	public enum Site{
		YOUTUBE,FACEBOOK,DAILYMOTION,VIMEO;
	}
	
	// web page url and video download url
	private URL webUrl;
	private URL downloadUrl;
	
	private List<URLInfo> info=new ArrayList<URLInfo>();
	private String title;
	private String fileType;
	private Site site;
	private float fileSize;
	private String sizeUnit;
	
	public URLInfo(URL webUrl){
		this.setWeb(webUrl);
		reset();
	}
	
	synchronized public void reset(){
		info=null;
		title=null;
		fileType=null;
	}
	
	synchronized public String getTitle(){
		return this.title;
	}
	
	synchronized public void setTitle(String title){
		this.title=title;
	}
	
	synchronized public String getFileType(){
		return this.fileType;
	}
	
	synchronized public void setFileType(String fileType){
		this.fileType=fileType;
	}
	
	synchronized public URL getWeb(){
		return this.webUrl;
	}
	
	synchronized public void setWeb(URL webUrl){
		this.webUrl=webUrl;
		reset();
	}
	
	synchronized public URL getDownloadUrl(){
		return this.downloadUrl;
	}
	
	synchronized public void setDownloadUrl(URL downloadUrl){
		this.downloadUrl=downloadUrl;
	}
	
	synchronized public List<URLInfo> getInfo(){
		return this.info;
	}
	
	synchronized public void setInfo(List<URLInfo> info){
		this.info=info;
	}
	
	synchronized public Site getSite(){
		return this.site;
	}
	
	synchronized public void setSite(Site site){
		this.site=site;
	}
	
	synchronized public float getFileSize(){
		return this.fileSize;
	}
	
	synchronized public void setFileSize(float fileSize){
		this.fileSize=fileSize;
	}
	
	synchronized public String getSizeUnit(){
		return this.sizeUnit;
	}
	
	synchronized public void setSizeUnit(String unit){
		this.sizeUnit=unit;
	}
}