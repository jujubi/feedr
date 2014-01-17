package com.flashbang.feedr;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

public class GetFeedData {

	private String url;
	Context ctx;
	
	public GetFeedData(String str,Context ctx)
	{
		this.url=str;
		this.ctx=ctx;
	}
	
	public ArrayList<Feed> getData()
	{
		XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

			    //Getting from local Assets folder
				/*InputStream in_s = ctx.getAssets().open("temp.xml");
		        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in_s, null);
		      */
	            //Getting from URL
	             try {
					parser.setInput(new InputStreamReader(getUrlData(url)));
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  

	            return parseXML(parser);
	            

		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	//Parsing XML
	private ArrayList<Feed> parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
		
		ArrayList<Feed> products = null;
        int eventType = parser.getEventType();
        Feed currentProduct = null;
        String name = null;
        while (eventType != XmlPullParser.END_DOCUMENT){
            
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	products = new ArrayList<Feed>();
                	break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("item")){
                        currentProduct = new Feed("b","b");
                    } else if (currentProduct != null){
                        if (name.equals("title")){
                            currentProduct.setTitle(parser.nextText());
                        } else if (name.equals("link")){
                        //	currentProduct.link=parser.nextText();
                                                  
                        } else if (name.equals("description")){
                        	currentProduct.setDescription(parser.nextText());
                        	// Log.d("LOL",currentProduct.description);
                        }  
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("item") && currentProduct != null){
                    	
                    	String lol= currentProduct.title.substring(0, 9);
                    	
                    	//Not adding the last site news :/
                    	if(!lol.equals("SITE NEWS"))
                    	products.add(currentProduct);
                    } 
            }
            eventType = parser.next();
        }

     return products;
	}
	
	//Getting XML data from Online
	public InputStream getUrlData(String url) 

			throws URISyntaxException, ClientProtocolException, IOException {

			  DefaultHttpClient client = new DefaultHttpClient();

			  HttpGet method = new HttpGet(new URI(url));

			  HttpResponse res = client.execute(method);

			  return  res.getEntity().getContent();

			}
	
}
