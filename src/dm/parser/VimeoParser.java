package dm.parser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dm.abstractclass.Parser;
import dm.abstractclass.URLInfo;
import dm.info.VimeoInfo;
import dm.info.VimeoInfo.VimeoQuality;
import dm.util.GetFileAttribute;
import dm.util.GetHtml;

public class VimeoParser extends Parser{
	
	private String vimeoPrefix;
	private String title="videoplayback";
	private GetFileAttribute fileAttribute;
	
	public static boolean prove(URL webUrl){
		return webUrl.toString().contains("vimeo.com");
	}
	
	//get vimeo video id
	private String getVimeoId(String url){
		 {
	         Pattern u = Pattern.compile("vimeo.com.*/(\\d+)");
	         Matcher um = u.matcher(url.toString());

	         if (um.find())
	             return um.group(1);
	     }
		 
		 
	     // rss feed url. format:
	     // "http://vimeo.com/moogaloop.swf?clip_id=49243107"
	     {
	         Pattern u = Pattern.compile("vimeo.com.*=(\\d+)");
	         Matcher um = u.matcher(url.toString());

	         if (um.find())
	             return um.group(1);
	     }
	     return null;
	}
	
	public VimeoParser(){
		
	}
	
	public URLInfo info(URL webUrl){
		return new VimeoInfo(webUrl);
	}
	
	public List<URLInfo> extract(URLInfo info){
		
		List<URLInfo> downloadList=new ArrayList<URLInfo>();
		//VimeoInfo vinfo=(VimeoInfo) info;
		String id=getVimeoId("https://vimeo.com/211466752");
		vimeoPrefix="https://player.vimeo.com/video/";
		//for video quality
		VimeoQuality vq=null;
		String quality;
		
		try{
				URL webUrl=new URL(vimeoPrefix+id);
				GetHtml getHtml=new GetHtml(webUrl);
				
				///download one link 
				String downloadUrl=null;
				
				String html=getHtml.getHtmlpage();
				
				//get title static block
				{
					Pattern p=Pattern.compile("<title>(.*)</title>");
					Matcher m=p.matcher(html);
					if(m.find()){
						title=m.group(1);
					}
				}
				
				Pattern p=Pattern.compile("\"url\":\"([^\"]+.mp4)([^}]+)}");
				Matcher m=p.matcher(html);
				while(m.find()){
					
					VimeoInfo vinfo=new VimeoInfo(info.getWeb());
					downloadUrl=m.group(1);
					quality=m.group(2);
					
					// static block for vimeo video quality
					{
						Pattern pq=Pattern.compile("\"quality\":\"([^\"]+)\"");
						Matcher mq=pq.matcher(quality);
						if(mq.find()){
							String q=mq.group(1);
							if(q.equals("1080p")) vq=VimeoQuality.p1080;
							if(q.equals("720p")) vq=VimeoQuality.p720;
							if(q.equals("540p")) vq=VimeoQuality.p540;
							if(q.equals("360p")) vq=VimeoQuality.p360;
						}
					}
					URL url=new URL(downloadUrl);
					
					// for file size and unit
					GetFileAttribute.setdonwloadLink(url);
					fileAttribute=GetFileAttribute.getInstance();
					
					// inner FileSize class contain file size and unit 
					GetFileAttribute.FileSize fileSize=fileAttribute.getSize();
					
					
					vinfo.setVimeoQuality(vq);
					vinfo.setDownloadUrl(url);
					vinfo.setFileType(".mp4");
					vinfo.setFileSize(fileSize.size);
					vinfo.setSizeUnit(fileSize.unit);
					vinfo.setTitle(title);
					downloadList.add(vinfo);
			}
		}catch(Exception e){
		
	 }
		return downloadList;
	}
	
}
