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
}
