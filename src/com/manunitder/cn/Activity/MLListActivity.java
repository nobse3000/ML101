package com.manunitder.cn.Activity;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.manunitder.cn.R;
import com.manunitder.cn.Model.MLInfo;
import com.manunitder.cn.Service.DownloadService;
import com.manunitder.cn.Utils.HttpDownloader;
import com.manunitder.cn.XML.MLListContentHandler;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MLListActivity extends ListActivity {
	private static final int UPPER = 1;	//最上面按钮
	private static final int DOWN = 2;	//最下面按钮
	private List<MLInfo> mlInfos = null;
	private static final String strURL =
		"http://localhost:8080/ml101/positiones.xml";
//	private static final String[] ACTIVITY_CHOICES = 
//		new String[]{"1","2","3","4","5","6","7","8","9","10",
//		"11","12","13","14","15","16","17","18","19","20",
//		"21","22","23","24","25","26","27","28","29","30",
//		"31","32","33","34","35","36","37","38","39","40",
//		"41","42","43","44","45","46","47","48","49","50",
//		"51","52","53","54","55","56","57","58","59","60",
//		"61","62","63","64","65","66","67","68","69","70",
//		"71","72","73","74","75","76","77","78","79","80",
//		"81","82","83","84","85","86","87","88","89","90",
//		"91","92","93","94","95","96","97","98","99","100",
//	};      
	/** 
     * ML主列表画面，加载所有方式
     * */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, UPPER, 1, R.string.mllist_upper);
		menu.add(0, DOWN, 2, R.string.mllist_down);
		return super.onCreateOptionsMenu(menu);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        updateListView(strURL);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == UPPER){
			//用户点击了最上面
		}
		if(item.getItemId() == DOWN){
			//用户点击了最下面
		}
		return super.onOptionsItemSelected(item);
	}
	private SimpleAdapter buildSimpleAdater(List<MLInfo> mlInfos){
		//生成一个List对象，并按照SimpleAdapter的标准，将mlInfos当中的数据添加
		List<HashMap<String, String>> List = new ArrayList<HashMap<String, String>>();
		for (MLInfo mlInfo : mlInfos) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ml_name", mlInfo.getMlName());
			map.put("ml_diffcult", mlInfo.getMlDiffcult());
			List.add(map);
		}
		//创建一个SimpleAdapter对象
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, List, 
				R.layout.main, null, null);
		//将这个SimpleAdapter对象设置到ListActivity当中
		return simpleAdapter;
	}
	private void updateListView(String strURL){
		//用户点击了更新列表按钮
		//下载包含所有Mp3基本信息的xml文件
		String xml = downloadXML(strURL);
		//对XML文件进行解析，并将解析的结果放置到MLInfo对象当中，最后将这些MLInfo
		mlInfos = parse(xml);
		SimpleAdapter simpleAdapter = buildSimpleAdater(mlInfos);
		setListAdapter(simpleAdapter);
 	}
	private String downloadXML(String strUrl){
		HttpDownloader httpDownloader = new HttpDownloader();
		String result = httpDownloader.download(strUrl);
		return result;
	}
	private List<MLInfo> parse(String xmlStr){
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try{
			XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
			List<MLInfo> infos = new ArrayList<MLInfo>();
			MLListContentHandler mlListContentHandler = new MLListContentHandler(infos);
			xmlReader.setContentHandler(mlListContentHandler);
			xmlReader.parse(new InputSource(new StringReader(xmlStr)));
			Iterator<MLInfo> it = infos.iterator();
			while(it.hasNext()){
				MLInfo mlInfo = (MLInfo)it.next();
				System.out.println(mlInfo);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//根据用户点击列表当中的位置来得到响应的MLInfo对象
		MLInfo mlInfo = mlInfos.get(position);
		//生成Intent对象
		Intent intent = new Intent();
		//将MLInfo对象存入intent对象当中
		intent.putExtra("mlInfo", mlInfo);
		intent.setClass(this, DownloadService.class);
		//启动Service
		this.startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}
	
}
