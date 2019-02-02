package dm.parser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.abstractclass.Parser;
import dm.abstractclass.URLInfo;
import dm.info.DailymotionInfo;
import dm.info.DailymotionInfo.DailymotionQuality;
import dm.util.GetFileAttribute;
import dm.util.GetHtml;

public class DailymotionParser extends Parser{
	
	private final String p1080="H264-1920x1080";
	private final String p720="H264-1280x720";
	private final String p480="H264-848x480";
	private final String p384="H264-512x384";
	private final String p240="H264-320x240";
	private final String p144="H264-176x144-2";
	private DailymotionQuality dq;
	private GetFileAttribute.FileSize fileSize;
	private GetFileAttribute fileAttribute;
	private String title="videoplayback";
	
	/*
	 * empty set
	 */
	public DailymotionParser(){
		
	}
	
	public URLInfo info(URL webUrl){
		System.out.println(webUrl.toString());
		return new DailymotionInfo(webUrl);
	}
	
	/*
	 * @param URL
	 * @return boolean
	 */
	public static boolean prove(URL webUrl){
		return webUrl.toString().contains("dailymotion.com");
	}

	@Override
	public List<URLInfo> extract(URLInfo info) {
		List<URLInfo> downloadList=new ArrayList<URLInfo>();
		
		//DailymotionInfo dinfo=(DailymotionInfo)vinfo;
		
		GetHtml getHtml=new GetHtml(info.getWeb());
	
		
		//video download url
		String downloadUrl=null;
		String html=getHtml.getHtmlpage();
		
		//serarch title
		{
			Pattern pt=Pattern.compile("data-title=\"(.*)\"");
			Matcher mt=pt.matcher(html);
			if(mt.find()){
				title=mt.group(1);
				
			}
		}
		
		Pattern p=Pattern.compile("\"url\":\"([^\"]+.mp4[^&\"]+)\"");
		Matcher m=p.matcher(html);
		while(m.find()){
			DailymotionInfo dinfo=new DailymotionInfo(info.getWeb());
			String raw_url=m.group(1);
			downloadUrl=raw_url.replaceAll("\\\\","");
			System.out.println(downloadUrl);
			try{
				URL url=new URL(downloadUrl);
				
				// for file size and unit
				GetFileAttribute.setdonwloadLink(url);
				fileAttribute=GetFileAttribute.getInstance();
				
				// inner FileSize class contain file size and unit 
				fileSize=fileAttribute.getSize();
				
				dinfo.setDownloadUrl(url);
				dinfo.setFileType(".mp4");
				dinfo.setFileSize(fileSize.size);
				dinfo.setSizeUnit(fileSize.unit);
				dinfo.setTitle(title);
				
				//set dailymotion video quality
				if(downloadUrl.contains(p1080)){
					dq=DailymotionQuality.p1080;
				}
				if(downloadUrl.contains(p720)){
					dq=DailymotionQuality.p720;
				}
				if(downloadUrl.contains(p480)){
					dq=DailymotionQuality.p480;
				}
				if(downloadUrl.contains(p384)){
					dq=DailymotionQuality.p384;
				}
				if(downloadUrl.contains(p240)){
					dq=DailymotionQuality.p240;
				}
				if(downloadUrl.contains(p144)){
					dq=DailymotionQuality.p144;
				}
				dinfo.setDailymotionQuality(dq);
				
				//add dailymotion download link 
				downloadList.add(dinfo);
			}catch(Exception e){
				
			}
			
		}
		
		return downloadList;
	}
	
	

}
