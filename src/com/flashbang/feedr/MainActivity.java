package com.flashbang.feedr;
import android.app.Activity;

import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends Activity  {

	  private ListView listView;
	  private ActivityAdapter adapter ;
	  ActivityModel  model[];
	  
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
     
       
        
          model = new ActivityModel[]{
        		
        	new ActivityModel("Aerobics",""),
        	new ActivityModel("Biking","e.g. stationary or road"),
        	new ActivityModel("Boxing",""),
        	new ActivityModel("Calisthenics","e.g. Push ups, sit ups, jumping jacks"),
        	new ActivityModel("Circuit training",""),
        	new ActivityModel("Dance Aerobics","e.g. Zumba"),
        	new ActivityModel("Elliptical Machine",""),
        	new ActivityModel("Exercise Video Games","e.g. Wii Sports, Wii Fit"),
        	new ActivityModel("Inline Sports","Rollerskating/rollerblading/ice skating/snow skiing"),
        	new ActivityModel("Jumping Rope",""),
        	new ActivityModel("Racquet Sports","e.g. tennis, badminton"),
        	new ActivityModel("Rowing",""),
        	new ActivityModel("Running",""),
        	new ActivityModel("Sports","e.g. basketball, soccer, football"),
        	new ActivityModel("Stair Climber",""),
        	new ActivityModel("Swimming laps",""),
        	new ActivityModel("Walking",""),
        	new ActivityModel("Water aerobics",""),
        	new ActivityModel("OTHER","")
        	
        };
        
        
         adapter = new ActivityAdapter(this,R.layout.act_listmd_row,model);
         listView = (ListView)findViewById(R.id.list);
         listView.setAdapter(adapter);
        
        
         listView.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				
				Toast.makeText(getApplicationContext(), "Selected  item " + position  , Toast.LENGTH_SHORT).show();	
			
				
			}
            	
            

        });
        
        
        
	}



	
	
	
}
