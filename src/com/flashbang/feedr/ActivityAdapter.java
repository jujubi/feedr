package com.flashbang.feedr;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
public class ActivityAdapter extends ArrayAdapter<Feed>{

	

	Context context; 
	    int layoutResourceId;  
	    Feed data[] = null;
	    DisplayImageOptions options;
	    protected ImageLoader imageLoader = ImageLoader.getInstance();
		 public ActivityAdapter(Context context, int layoutResourceId, Feed data[]) {
		
			super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        this.data = data;
	     
	        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context.getApplicationContext())
   	 	 .build();
   	 	 ImageLoader.getInstance().init(config);
        
        
   	 	 	options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_stub)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.considerExifParams(true)
			.imageScaleType(ImageScaleType.EXACTLY)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();

	        
	        
	        
		}
	    
	    
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	
	    	   View row = convertView;
	    	   ActHolder holder = null;
	    	   if(row == null)
	           {
	    		   
	    		   LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	               row = inflater.inflate(layoutResourceId, parent, false);
	    		   
	               holder = new ActHolder();
	               holder.txtTitle = (TextView)row.findViewById(R.id.title);
	               holder.description = (TextView)row.findViewById(R.id.descr);
	               holder.image = (ImageView) row.findViewById(R.id.img);
	               
	               
	               
	             /*  holder.icon.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View view) {
							mSelectedRow = position;
							
							
						}
	    		     });
	               */
	               row.setTag(holder);
	               
	           }else{
	        	   
	        	   holder = (ActHolder)row.getTag();
	           }
	    	   
	    	  
	    	   Feed model = data[position];
	    	   holder.txtTitle.setText(model.title);
	    	   holder.description.setText(Html.fromHtml(model.description));
	    	   
	    	//   ImageSize targetSize = new ImageSize(104, 70); // result Bitmap will be fit to this size
	    		imageLoader.displayImage(model.img, holder.image, options, new SimpleImageLoadingListener() {
					 @Override
					 public void onLoadingStarted(String imageUri, View view) {
						// holder.progressBar.setProgress(0);
						// holder.progressBar.setVisibility(View.VISIBLE);
					 }

					 @Override
					 public void onLoadingFailed(String imageUri, View view,
							 FailReason failReason) {
						// holder.progressBar.setVisibility(View.GONE);
					 }

					 @Override
					 public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						 //holder.progressBar.setVisibility(View.GONE);
						// holder.image.setImageBitmap(loadedImage);
						 
						 
					 }
				 }, new ImageLoadingProgressListener() {
					 @Override
					 public void onProgressUpdate(String imageUri, View view, int current,
							 int total) {
						// holder.progressBar.setProgress(Math.round(100.0f * current / total));
					 }
				 }
);
	    	   
	    	   return row;
	    	
	    }
	    
	  static class ActHolder
	    {
	      //  ImageView imgIcon;
	        TextView txtTitle;
	        TextView description;
	        ImageView image;
	    } 
	    
	
	
}
