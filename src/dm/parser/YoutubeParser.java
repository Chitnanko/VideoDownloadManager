package dm.parser;

import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dm.abstractclass.Parser;
import dm.abstractclass.URLInfo;
import dm.info.YoutubeInfo;
import dm.info.YoutubeInfo.StreamInfo;
import dm.info.YoutubeInfo.Container;
import dm.info.YoutubeInfo.Encoding;
import dm.info.YoutubeInfo.AudioQuality;
import dm.info.YoutubeInfo.StreamCombined;
import dm.info.YoutubeInfo.StreamAudio;
import dm.info.YoutubeInfo.StreamVideo;
import dm.info.YoutubeInfo.YoutubeQuality;
import dm.util.GetFileAttribute;
import dm.util.GetHtml;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class YoutubeParser extends Parser{
	
	private String title="videoplayback";
	private GetFileAttribute fileAttribute;
	
	public YoutubeParser(){
		
	}
	
	public static boolean prove(URL webUrl){
		return webUrl.toString().contains("youtube.com");
	}

	@Override
	public URLInfo info(URL webUrl) {
		return new YoutubeInfo(webUrl);
	}

    // http://en.wikipedia.org/wiki/YouTube#Quality_and_codecs
	 public static final HashMap<Integer, StreamInfo> itagMap = new HashMap<Integer, StreamInfo>() {
	        private static final long serialVersionUID = -6925194111122038477L;

	        {
	            put(120, new StreamCombined(Container.FLV, Encoding.H264, YoutubeQuality.p720, Encoding.AAC,
	                    AudioQuality.k128));
	            put(102, new StreamCombined(Container.WEBM, Encoding.VP8, YoutubeQuality.p720, Encoding.VORBIS,
	                    AudioQuality.k192));
	            put(101, new StreamCombined(Container.WEBM, Encoding.VP8, YoutubeQuality.p360, Encoding.VORBIS,
	                    AudioQuality.k192)); // webm
	            put(100, new StreamCombined(Container.WEBM, Encoding.VP8, YoutubeQuality.p360, Encoding.VORBIS,
	                    AudioQuality.k128)); // webm
	            put(85, new StreamCombined(Container.MP4, Encoding.H264, YoutubeQuality.p1080, Encoding.AAC,
	                    AudioQuality.k192)); // mp4
	            put(84, new StreamCombined(Container.MP4, Encoding.H264, YoutubeQuality.p720, Encoding.AAC,
	                    AudioQuality.k192)); // mp4
	            put(83, new StreamCombined(Container.MP4, Encoding.H264, YoutubeQuality.p240, Encoding.AAC,
	                    AudioQuality.k96)); // mp4
	            put(82, new StreamCombined(Container.MP4, Encoding.H264, YoutubeQuality.p360, Encoding.AAC,
	                    AudioQuality.k96)); // mp4
	            put(46, new StreamCombined(Container.WEBM, Encoding.VP8, YoutubeQuality.p1080, Encoding.VORBIS,
	                    AudioQuality.k192)); // webm
	            put(45, new StreamCombined(Container.WEBM, Encoding.VP8, YoutubeQuality.p720, Encoding.VORBIS,
	                    AudioQuality.k192)); // webm
	            put(44, new StreamCombined(Container.WEBM, Encoding.VP8, YoutubeQuality.p480, Encoding.VORBIS,
	                    AudioQuality.k128)); // webm
	            put(43, new StreamCombined(Container.WEBM, Encoding.VP8, YoutubeQuality.p360, Encoding.VORBIS,
	                    AudioQuality.k128)); // webm
	            put(38, new StreamCombined(Container.MP4, Encoding.H264, YoutubeQuality.p3072, Encoding.AAC,
	                    AudioQuality.k192)); // mp4
	            put(37, new StreamCombined(Container.MP4, Encoding.H264, YoutubeQuality.p1080, Encoding.AAC,
	                    AudioQuality.k192)); // mp4
	            put(36, new StreamCombined(Container.GP3, Encoding.MP4, YoutubeQuality.p240, Encoding.AAC,
	                    AudioQuality.k36)); // 3gp
	            put(35, new StreamCombined(Container.FLV, Encoding.H264, YoutubeQuality.p480, Encoding.AAC,
	                    AudioQuality.k128)); // flv
	            put(34, new StreamCombined(Container.FLV, Encoding.H264, YoutubeQuality.p360, Encoding.AAC,
	                    AudioQuality.k128)); // flv
	            put(22, new StreamCombined(Container.MP4, Encoding.H264, YoutubeQuality.p720, Encoding.AAC,
	                    AudioQuality.k192)); // mp4
	            put(18, new StreamCombined(Container.MP4, Encoding.H264, YoutubeQuality.p360, Encoding.AAC,
	                    AudioQuality.k96)); // mp4
	            put(17, new StreamCombined(Container.GP3, Encoding.MP4, YoutubeQuality.p144, Encoding.AAC,
	                    AudioQuality.k24)); // 3gp
	            put(6, new StreamCombined(Container.FLV, Encoding.H263, YoutubeQuality.p270, Encoding.MP3,
	                    AudioQuality.k64)); // flv
	            put(5, new StreamCombined(Container.FLV, Encoding.H263, YoutubeQuality.p240, Encoding.MP3,
	                    AudioQuality.k64)); // flv

	            put(133, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p240));
	            put(134, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p360));
	            put(135, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p480));
	            put(136, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p720));
	            put(137, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p1080));
	            put(138, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p2160));
	            put(160, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p144));
	            put(242, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p240));
	            put(243, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p360));
	            put(244, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p480));
	            put(247, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p720));
	            put(248, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p1080));
	            put(264, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p1440));
	            put(271, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p1440));
	            put(272, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p2160));
	            put(278, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p144));
	            put(298, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p720));
	            put(299, new StreamVideo(Container.MP4, Encoding.H264, YoutubeQuality.p1080));
	            put(302, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p720));
	            put(303, new StreamVideo(Container.WEBM, Encoding.VP9, YoutubeQuality.p1080));

	            put(139, new StreamAudio(Container.MP4, Encoding.AAC, AudioQuality.k48));
	            put(140, new StreamAudio(Container.MP4, Encoding.AAC, AudioQuality.k128));
	            put(141, new StreamAudio(Container.MP4, Encoding.AAC, AudioQuality.k256));
	            put(171, new StreamAudio(Container.WEBM, Encoding.VORBIS, AudioQuality.k128));
	            put(172, new StreamAudio(Container.WEBM, Encoding.VORBIS, AudioQuality.k192));

	            put(249, new StreamAudio(Container.WEBM, Encoding.OPUS, AudioQuality.k50));
	            put(250, new StreamAudio(Container.WEBM, Encoding.OPUS, AudioQuality.k70));
	            put(251, new StreamAudio(Container.WEBM, Encoding.OPUS, AudioQuality.k160));
	        }
	    };
	    
	    
	@Override
	public List<URLInfo> extract(URLInfo info) {
		
		List<URLInfo> downloadList=new ArrayList<URLInfo>();
		//YoutubeInfo yinfo=(YoutubeInfo) info;
		GetHtml getHtml=new GetHtml(info.getWeb());
		try {
			extractHtmlInfo(getHtml.getHtmlpage(),downloadList,info);
			//System.out.println("i am here");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return downloadList;
	}
	
	public  void extractHtmlInfo(String html,final List<URLInfo> downloadList,URLInfo info) throws Exception{
	

	            // get video title
	            {
	                Pattern titleP = Pattern.compile("<meta name=\"title\" content=(.*)");
	                Matcher titleMatch = titleP.matcher(html);
	                if (titleMatch.find()) {
	                    String sline = titleMatch.group(1);
	                    title = sline.replaceFirst("<meta name=\"title\" content=", "").trim();
	                    title= StringUtils.strip(title, "\">");
	                    title = StringEscapeUtils.unescapeHtml4(title);
	                   // System.out.println("......................"+title+"......................");
	                    
	                }
	            }
	            
	            {
	                Pattern urlencod = Pattern.compile("\"url_encoded_fmt_stream_map\":\"([^\"]*)\"");
	                Matcher urlencodMatch = urlencod.matcher(html);
	                if (urlencodMatch.find()) {
	                    String url_encoded_fmt_stream_map;
	                    url_encoded_fmt_stream_map = urlencodMatch.group(1);

	                    // normal embedded video, unable to grab age restricted videos
	                    Pattern encod = Pattern.compile("url=(.*)");
	                    Matcher encodMatch = encod.matcher(url_encoded_fmt_stream_map);
	                    if (encodMatch.find()) {
	                        String sline = encodMatch.group(1);

	                        extractUrlEncodedVideos(sline,downloadList,info);
	                    }

	                    // stream video
	                    Pattern encodStream = Pattern.compile("stream=(.*)");
	                    Matcher encodStreamMatch = encodStream.matcher(url_encoded_fmt_stream_map);
	                    if (encodStreamMatch.find()) {
	                        String sline = encodStreamMatch.group(1);

	                        String[] urlStrings = sline.split("stream=");

	                        for (String urlString : urlStrings) {
	                            urlString = StringEscapeUtils.unescapeJava(urlString);

	                            Pattern link = Pattern.compile("(sparams.*)&itag=(\\d+)&.*&conn=rtmpe(.*),");
	                            Matcher linkMatch = link.matcher(urlString);
	                            if (linkMatch.find()) {

	                                String sparams = linkMatch.group(1);
	                                String itag = linkMatch.group(2);
	                                String url = linkMatch.group(3);

	                                url = "https" + url + "?" + sparams;

	                                url = URLDecoder.decode(url,"UTF8");

	                                //filter(sNextVideoURL, itag, new URL(url));
	                            }
	                        }
	                    }
	                }
	            }

	 }
	
	//youtube video file type
	public String getType(StreamInfo info){
		
		String encoding="??";
		if(info instanceof StreamCombined){
			StreamCombined sc=(StreamCombined) info;
			encoding=sc.c.toString();
			return encoding;
		}
		if(info instanceof StreamVideo){
			StreamVideo sv=(StreamVideo)info;
			encoding=sv.c.toString();
		}
		if(info instanceof StreamAudio){
			StreamAudio sa=(StreamAudio) info;
			encoding=sa.audio.toString();
			return encoding;
		}
		return encoding;
	}
	
	public  void extractUrlEncodedVideos(String sline,List<URLInfo> downloadList,URLInfo info) throws Exception{
		String[] urlStrings = sline.split("url=");
        for (String urlString : urlStrings) {
        	
        	//create new download link
        	YoutubeInfo yinfo=new YoutubeInfo(info.getWeb());
        	
            urlString = StringEscapeUtils.unescapeJava(urlString);

            String urlFull = URLDecoder.decode(urlString,"UTF8");

            // universal request
            {
                String url = null;
                {
                    Pattern link = Pattern.compile("([^&,]*)[&,]");
                    Matcher linkMatch = link.matcher(urlString);
                    if (linkMatch.find()) {
                        url = linkMatch.group(1);
                        url = URLDecoder.decode(url,"UTF8");
                    }
                }

                String itag = null;
                {
                    Pattern link = Pattern.compile("itag=(\\d+)");
                    Matcher linkMatch = link.matcher(urlFull);
                    if (linkMatch.find()) {
                        itag = linkMatch.group(1);
                    }
                }

                String sig = null;

                if (sig == null) {
                    Pattern link = Pattern.compile("&signature=([^&,]*)");
                    Matcher linkMatch = link.matcher(urlFull);
                    if (linkMatch.find()) {
                        sig = linkMatch.group(1);
                    }
                }

                if (sig == null) {
                    Pattern link = Pattern.compile("sig=([^&,]*)");
                    Matcher linkMatch = link.matcher(urlFull);
                    if (linkMatch.find()) {
                        sig = linkMatch.group(1);
                    }
                }
                
                if (url != null && itag != null && sig != null) {
                    try {
                        url += "&signature=" + sig;
                        
                        
                        // set Youtube video infomation 
                        URL downloadUrl=new URL(url);
                        yinfo.setDownloadUrl(downloadUrl);
                       
        				// for file size and unit
                        GetFileAttribute.setdonwloadLink(downloadUrl);
    					fileAttribute=GetFileAttribute.getInstance();
        				
        				// inner FileSize class contain file size and unit 
        				GetFileAttribute.FileSize fileSize=fileAttribute.getSize();
        				yinfo.setFileSize(fileSize.size);
        				yinfo.setSizeUnit(fileSize.unit);
                        
        				//set youtube video quality
        				 Integer i = Integer.decode(itag);
        			     StreamInfo vd = itagMap.get(i);
        			     yinfo.setStreamInfo(vd);
        			     
        			     yinfo.setFileType("."+getType(vd));
        			     yinfo.setTitle(title);
        			     
        			     // add youtubeInfo object
        			     downloadList.add(yinfo);
        			     continue;
                    } catch (Exception e) {
                        // ignore bad urls
                    }
                }
            }
        }
	}
}
