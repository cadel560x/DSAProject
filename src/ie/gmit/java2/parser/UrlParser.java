package ie.gmit.java2.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import ie.gmit.java2.parser.Parser;


public class UrlParser extends Parser {
//	Member attributes/fields
	private URL site;
	private HttpsURLConnection secSite;
	private InputStream is;
//	private MalformedURLException mue;
	
	
	
	
//	Constructors
	public UrlParser() {

	}
	
	
	public UrlParser(String site) throws IOException {
		
		if ( site.startsWith("https://") ) {
			this.site =  new URL(site);
			secSite = (HttpsURLConnection)this.site.openConnection();
			is = secSite.getInputStream();
		}
		else {
			if ( ! site.startsWith("http://") )
				site = "http://" + site;
			
			this.site = new URL(site);
			is = this.site.openStream();
		}
		
//		'(BufferedReader) br' is inherited from the parent abstract class 'Parser' with 'protected' access mode
		br = new BufferedReader( new InputStreamReader(is) );
		parse(br);

	}
	
	
	
	
//	Getters & setters
	public URL getSite() {
		return site;
	}


	public void setSite(URL site) {
		this.site = site;
	}	
	
	
	public HttpsURLConnection getSecSite() {
		return secSite;
	}	


	public void setSecSite(HttpsURLConnection secSite) {
		this.secSite = secSite;
	}


	public InputStream getIs() {
		return is;
	}


	public void setIs(InputStream is) {
		this.is = is;
	}
	
	
} // class UrlParser
