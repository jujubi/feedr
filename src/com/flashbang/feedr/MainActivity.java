package com.flashbang.feedr;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends Activity  {

	  private ListView listView;
	  private ActivityAdapter adapter ;
	  Feed  model[];
	  Context ctx;
	  private static ProgressBar progress;
	  
	  
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        ctx=this;
        progress = (ProgressBar)findViewById(R.id.progressBar);
        listView = (ListView)findViewById(R.id.list);
    	
        //Getting Feed from the internet
        new AsyncTask<String[], Long, Long>(){
 			  @Override
				protected Long doInBackground(String[]... params) {
		
				/*  model = new Feed[]{
			        		
				        	new Feed("Aerobics",""),
				        	new Feed("Biking","e.g. stationary or road"),
				        	new Feed("Boxing",""),
				        	new Feed("Calisthenics","e.g. Push ups, sit ups, jumping jacks"),
				        	new Feed("Circuit training",""),
				        	new Feed("Dance Aerobics","e.g. Zumba"),
				        	new Feed("Elliptical Machine",""),
				        	new Feed("Exercise Video Games","e.g. Wii Sports, Wii Fit"),
				        	new Feed("Inline Sports","Rollerskating/rollerblading/ice skating/snow skiing"),
				        	new Feed("Jumping Rope",""),
				        	new Feed("Racquet Sports","e.g. tennis, badminton"),
				        	new Feed("Rowing",""),
				        	new Feed("Running",""),
				        	new Feed("Sports","e.g. basketball, soccer, football"),
				        	new Feed("Stair Climber",""),
				        	new Feed("Swimming laps",""),
				        	new Feed("Walking",""),
				        	new Feed("Water aerobics",""),
				        	new Feed("OTHER","")
				        	
				        };
 				  	*/
 				  		GetFeedData obj = new GetFeedData("temp.xml",getApplicationContext());
 				  		ArrayList<Feed> feed = obj.getData();
 				  		int ct=0;
 				  		model = new Feed[feed.size()];
 				  		for(int i=0;i<feed.size();i++)
 				  			model[ct++]=feed.get(i);
 				  		
 				  		return null;
				}
				protected void onPreExecute() {
					
					progress.setVisibility(View.VISIBLE);
					listView.setVisibility(View.INVISIBLE);
					
				}
              
				@Override
		        public void onProgressUpdate(Long... value) {
		            
		        }
				@Override
				protected void onPostExecute(Long result){
					
					 progress.setVisibility(View.GONE);
					 listView.setVisibility(View.VISIBLE);
					 adapter = new ActivityAdapter(ctx,R.layout.act_listmd_row,model);
					 listView.setAdapter(adapter);
			  }
		
   	}.execute();
         
        listView.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				
				//Toast.makeText(getApplicationContext(), "Selected  item " + position  , Toast.LENGTH_SHORT).show();
				String url = model[position].link;
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			
				
			}

			
            

        });
        
        
  
   	
   	
	}



	
	
	
}
