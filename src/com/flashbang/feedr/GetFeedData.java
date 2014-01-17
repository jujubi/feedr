package com.flashbang.feedr;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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

			    InputStream in_s = ctx.getAssets().open(url);
		        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in_s, null);

	            return parseXML(parser);
	            

		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
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
                            //Log.d("LOL",currentProduct.title);
                        } else if (name.equals("link")){
                        //	currentProduct.link=parser.nextText();
                           // Log.d("LOL",currentProduct.link);
                       
                        } else if (name.equals("description")){
                        	currentProduct.setDescription(parser.nextText());
                        	// Log.d("LOL",currentProduct.description);
                        }  
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("item") && currentProduct != null){
                    	products.add(currentProduct);
                    } 
            }
            eventType = parser.next();
        }

     return products;
	}
	
	
	
}
