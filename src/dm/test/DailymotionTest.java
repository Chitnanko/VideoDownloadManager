package dm.test;

import java.net.URL;
import java.util.List;

import dm.abstractclass.URLInfo;
import dm.abstractclass.URLInfo.Site;
import dm.framework.Dm;
import dm.info.DailymotionInfo;
import dm.info.DailymotionInfo.DailymotionQuality;
import dm.info.VimeoInfo;
import dm.info.VimeoInfo.VimeoQuality;
import dm.info.YoutubeInfo;
import dm.info.YoutubeInfo.StreamCombined;
import dm.info.YoutubeInfo.StreamInfo;
import dm.info.YoutubeInfo.StreamVideo;
import dm.info.YoutubeInfo.YoutubeQuality;

public class DailymotionTest {
	
	public static void main(String args[]){
		try{
			URL webUrl=new URL("https://www.youtube.com/watch?v=Rp8HIf5eUpo");
			Dm dm=new Dm(webUrl);
			URLInfo info=dm.getInfo();
			List<URLInfo> downloadLink=dm.getParser().extract(info);
			System.out.println(downloadLink.size());
			for(URLInfo uinfo:downloadLink){
				
				System.out.println("title:"+uinfo.getTitle());
				System.out.println("file type:"+uinfo.getFileType());
				System.out.println("file size"+uinfo.getFileSize()+uinfo.getSizeUnit());
				System.out.println("download link:"+uinfo.getDownloadUrl());
				
				if(uinfo.getSite()==Site.DAILYMOTION){
					DailymotionInfo dinfo=(DailymotionInfo)uinfo;
					DailymotionQuality dq=dinfo.getDailymotionQuality();
					System.out.println(dq.toString());
				}
				
				if(uinfo.getSite()==Site.VIMEO){
					VimeoInfo vinfo=(VimeoInfo) uinfo;
					VimeoQuality vq=vinfo.getVimeoQuality();
					System.out.println(vq.toString());
					
				}
				
				if(uinfo.getSite()==Site.YOUTUBE){
					//System.out.println("i am here");
					YoutubeInfo yinfo=(YoutubeInfo)uinfo;
					StreamInfo stream=yinfo.getStreamInfo();
					//System.out.println(stream.getClass());
					if(stream instanceof StreamCombined){
						 StreamCombined sc=(StreamCombined)stream;
						System.out.println(sc.video.toString()+" "+sc.vq.toString());
						System.out.println("Vidoe quality:"+sc.vq.toString());
					}
				}
				
			}
		}catch(Exception e){
			
		}
	}

}
