package dm.parser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.abstractclass.Parser;
import dm.abstractclass.URLInfo;
import dm.info.FacebookInfo;
import dm.util.GetFileAttribute;
import dm.util.GetHtml;

public class FacebookParser extends Parser {
	private GetFileAttribute fileAttribute;
	
	public FacebookParser(){
		
	}
	
	public URLInfo info(URL webUrl){
		return new FacebookInfo(webUrl);
	}

	public static boolean prove(URL webUrl){
		return webUrl.toString().contains("facebook.com");
	}
	@Override
	public List<URLInfo> extract(URLInfo info) {
		
		List<URLInfo> downloadList=new ArrayList<URLInfo>();
		
		//FacebookInfo finfo=(FacebookInfo)vinfo;
		GetHtml getHtml=new GetHtml(info.getWeb());
		
		///download one link 
		String downloadUrl=null;
		
		Pattern p=Pattern.compile("sd_src:\"([^\"]*)\"");
		String html=getHtml.getHtmlpage();
		Matcher mat=p.matcher(html);
		while(mat.find()){
			
			FacebookInfo finfo=new FacebookInfo(info.getWeb());
			downloadUrl=mat.group(1);
			
			try{
				URL url=new URL(downloadUrl);
				
				// for file size and unit
				GetFileAttribute.setdonwloadLink(url);
				fileAttribute=GetFileAttribute.getInstance();
				
				// inner FileSize class contain file size and unit 
				GetFileAttribute.FileSize fileSize=fileAttribute.getSize();
				
				finfo.setDownloadUrl(url);
				finfo.setFileType(".mp4");
				finfo.setFileSize(fileSize.size);
				finfo.setSizeUnit(fileSize.unit);
				String title=downloadUrl.substring(downloadUrl.lastIndexOf('/')+1,downloadUrl.lastIndexOf('?'));
				finfo.setTitle(title);
				downloadList.add(finfo);
			}catch(Exception e){
				
			}
			
		}
		
		return downloadList;
	}

}
