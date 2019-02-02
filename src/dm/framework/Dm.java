package dm.framework;

import java.net.URL;

import dm.abstractclass.Parser;
import dm.abstractclass.URLInfo;
import dm.parser.DailymotionParser;
import dm.parser.FacebookParser;
import dm.parser.VimeoParser;
import dm.parser.YoutubeParser;

public class Dm {
	private URLInfo info;
	private Parser parser;
	private Dm(){
		
	}
	/*
	 * @param URL
	 */
	public Dm(URL webUrl){
		parser=parser(null,webUrl);
		info=parser.info(webUrl);
	}
	
	public static Parser parser(Parser parser,URL webUrl){
		
		Parser pa=parser;
		if(pa==null && YoutubeParser.prove(webUrl)) 
			return new YoutubeParser();
		if(pa==null && DailymotionParser.prove(webUrl))
			return new DailymotionParser();
		if(pa==null && FacebookParser.prove(webUrl))
			return new FacebookParser();
		if(pa==null && VimeoParser.prove(webUrl))
			return new VimeoParser();
		return null;
	}
	
	public URLInfo getInfo(){
		return this.info;
	}
	
	public Parser getParser(){
		return this.parser;
	}

}
