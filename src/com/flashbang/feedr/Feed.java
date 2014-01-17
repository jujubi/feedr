package com.flashbang.feedr;

import android.util.Log;

/**
 * @author Gleb Iakovlev
 *
 */
public class Feed {

	
	public String title;
	public String description;
	public String link;
	public String img;
	
	public Feed(String a,String b)
	{
		title=a;
		description=b;
	}
	
	public void setTitle(String a)
	{
		title=a;
		
		if(title.contains("("))
		{
		int lol=title.indexOf('(');
		title=title.substring(0,lol);
		}
	}
	
	public void setDescription(String a)
	{
		description=a;
		
		
		//Getting link
		if(description.contains("\""))
		{
			int first=description.indexOf("\"");
			int second=description.indexOf("\"",first+1);
			link=description.substring(first+1,second);
		}
		
		
		//Getting actual content
		if(description.contains("</SPAN>"))
		{
			int st=description.indexOf("</SPAN>");
			
			//int end=description.indexOf("</P>");
			description=description.substring(st+28,description.length());
		}
				
		
	}
	
}
