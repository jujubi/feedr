package com.flashbang.feedr;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
		
		//Removing Author name from the feed
		if(title.contains("("))
		{
		int lol=title.indexOf('(');
		title=title.substring(0,lol);
		}
	}
	
	public void setDescription(String a)
	{
		description=a;
		
		//Getting Image link
		String imgRegex = "<IMG VSPACE=\"4\" HSPACE=\"4\" BORDER=\"0\" ALIGN=\"RIGHT\" SRC=\\s*['\"]([^'\"]+)['\"][^>]*>";
		Pattern pattern = Pattern.compile(imgRegex);
		Matcher matcher = pattern.matcher(a);
		if (matcher.find())
			img=matcher.group(1);
	
		//Getting link to goto on Press
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
			description=description.substring(st+28,description.length());
		}
				
		
	}
	
}
