package projectstart.shubham.com.projectstart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


public class MainActivity extends ActionBarActivity {

    //@Override
    Button addplan;
    int[] pieChartValues={90,2,4,4};

    public static final String TYPE = "type";
    private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN };
    private CategorySeries mSeries = new CategorySeries("");
    private DefaultRenderer mRenderer = new DefaultRenderer();
    private GraphicalView mChartView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabViewer = (TabHost) findViewById(R.id.tabHost);

        tabViewer.setup();

        //Adding 1 tab
        TabHost.TabSpec tabSpecPie = tabViewer.newTabSpec("pieChart");
        tabSpecPie.setContent(R.id.pieTab);
        tabSpecPie.setIndicator("Quick View");
        tabViewer.addTab(tabSpecPie);

        TabHost.TabSpec tabSpecBill = tabViewer.newTabSpec("billAnalyze");
        tabSpecBill.setContent(R.id.billTab);
        tabSpecBill.setIndicator("Bill Analyze");
        tabViewer.addTab(tabSpecBill);

        TabHost.TabSpec tabSpecList = tabViewer.newTabSpec("list");
        tabSpecList.setContent(R.id.plansTab);
        tabSpecList.setIndicator("My Plans");
        tabViewer.addTab(tabSpecList);



        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
        mRenderer.setChartTitleTextSize(20);
        mRenderer.setLabelsTextSize(15);
        mRenderer.setLegendTextSize(15);
        mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setStartAngle(90);

        if (mChartView == null) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
            mRenderer.setClickEnabled(true);
            mRenderer.setSelectableBuffer(10);
            layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT));
        } else {
            mChartView.repaint();
        }
        fillPieChart();

        addplan = (Button)findViewById(R.id.btnAddPlan);

        addplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openHelpActivity = new Intent("projectstart.shubham.com.projectstart.AddPlansPage");
                startActivity(openHelpActivity);
            }
        });


    }

    public void fillPieChart(){
        for(int i=0;i<pieChartValues.length;i++)
        {
            mSeries.add(" Share Holder " + (mSeries.getItemCount() + 1), pieChartValues[i]);// names of sectors
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
            mRenderer.addSeriesRenderer(renderer);
            if (mChartView != null)
                mChartView.repaint();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
