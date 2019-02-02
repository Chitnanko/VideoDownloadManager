package dm.info;

import java.net.URL;
import dm.abstractclass.URLInfo;

public class YoutubeInfo extends URLInfo{
	
	public YoutubeInfo(URL webUrl){
		super(webUrl);
		super.setSite(Site.YOUTUBE);
	}
	
	public enum YoutubeQuality {
        p3072, p2304, p2160, p1440, p1080, p720, p520, p480, p360, p270, p240, p224, p144
    }

    public enum Container {
        FLV, GP3, MP4, WEBM
    }

    public enum Encoding {
        H263, H264, VP8, VP9, MP4, MP3, AAC, VORBIS, OPUS
    }

    public enum AudioQuality {
        k256, k192, k160, k128, k96, k70, k64, k50, k48, k36, k24
    }
    
    public static class StreamInfo {
        public Container c;

        public StreamInfo() {
        }

        public StreamInfo(Container c) {
            this.c = c;
        }

        public String toString() {
            return c.toString();
        }
    }

    //both video and audio
    
    public static class StreamCombined extends StreamInfo {
        public Encoding video;
        public YoutubeQuality vq;
        public Encoding audio;
        public AudioQuality aq;

        public StreamCombined() {
        }

        public StreamCombined(Container c, Encoding v, YoutubeQuality vq, Encoding a, AudioQuality aq) {
            super(c);

            this.video = v;
            this.vq = vq;
            this.audio = a;
            this.aq = aq;
        }

        public String toString() {
            return c.toString() + " " + video.toString() + "(" + vq.toString() + ") " + audio.toString() + "("
                    + aq.toString() + ")";
        }
    }

    //only video
    
    public static class StreamVideo extends StreamInfo {
        public Encoding video;
        public YoutubeQuality vq;

        public StreamVideo() {
        }

        public StreamVideo(Container c, Encoding v, YoutubeQuality vq) {
            super(c);

            this.vq = vq;
            this.video = v;
        }

        public String toString() {
            return c.toString() + " " + video.toString() + "(" + vq.toString() + ")";
        }
    }

    //only audio
    
    public static class StreamAudio extends StreamInfo {
        public Encoding audio;
        public AudioQuality aq;

        public StreamAudio() {
        }

        public StreamAudio(Container c, Encoding a, AudioQuality q) {
            super(c);
            this.audio = a;
            this.aq = q;
        }

        public String toString() {
            return c.toString() + " " + audio.toString() + " " + aq.toString();
        }
    }
    
    // video file format,video quality,audio quality,echding technique
    private StreamInfo  stream;
    
    public StreamInfo getStreamInfo(){
    	return this.stream;
    }
    
    public void setStreamInfo(StreamInfo stream){
    	this.stream=stream;
    }


}
