package dm.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.jmx.snmp.tasks.ThreadService;

import dm.abstractclass.URLInfo;
import dm.download.DownloadManager;
import dm.framework.Dm;

public class DownloadLink{
	
	private static DownloadLink downloadLink;
	private static URL webUrl;
	private List<URLInfo> downloadList;
	private final String fileType[]={"3GP","7Z","AAC","ACE","AIF","ARJ","ASF","AVI","BIN","BZ2", 
										"EXE","DEB","DMG","GZ","GZIP","ISO","LZH","M4A","M4V","MOV","MP3","MP4",
										"MPA","MPE","MPEG","MPG","MSI","MSU","OGG","PDF","PLJ","PPS","PPT","QT",
										"RA","RAR","RM","RPM","SEA","SIT","SITX","TAR","TIF","TIFF","WAV","WMA",
										"WMV","Z","ZIP","JAR","TLZ","TBZ2","TXZ","XZ","CBZ","PKG","RUN"
										};
	private static final String parseLinkType[]={"https://www.youtube.com/watch?","http://www.dailymotion.com/video/",
											"https://vimeo.com/","https://www.facebook.com/"
									      };
	private DownloadManager dmanager;
	private Dm dm;
	private List<String> downloadInfoList;
		/*
		 * initialize download class
		 */
	private DownloadLink()
		
		{
			downloadInfoList=new ArrayList<String>();
		
			System.out.println("i am download Link");
			if(isParserlink(webUrl.toString())){
	
			/*
			 * initialize parser class
			 */
			dm=new Dm(webUrl);
			/*
			 * get download url list
			 */
				downloadList=dm.getParser().extract(dm.getInfo());
			
			if(downloadList.size()!=0){
				System.out.println("i am here");
				
				String title=downloadList.get(0).getTitle();
				for(URLInfo info:downloadList){
					
					System.out.println("for loop URLInfo");
					System.out.println(info.getFileSize()+" "+info.getSizeUnit());
					System.out.println(info.getFileType());
					String size=Float.toString(info.getFileSize());
					String unit=info.getSizeUnit();
					downloadInfoList.add(title+"\n"+size+" "+unit);
					
				}
				System.out.println("exited");
			}
		}
		
	}
	public static boolean isParserlink(String url){
		
		if(url.contains(parseLinkType[0]) || url.contains(parseLinkType[1]) || url.contains(parseLinkType[2]) ||
		    url.contains(parseLinkType[3])){
			return true;
		}
		return false;
	}
	
	public List<String> getDownloadInfo(){
		return downloadInfoList;
	}
	
	public List<URLInfo> getDownloadLink(){
		return downloadList;
	}
	
	public static void setWeb(String web){
		try {
			webUrl=new URL(web);
			downloadLink=new DownloadLink();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static DownloadLink getInstance(){
		return downloadLink;
	}

}
