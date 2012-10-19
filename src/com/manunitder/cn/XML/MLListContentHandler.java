package com.manunitder.cn.XML;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.manunitder.cn.Model.MLInfo;

public class MLListContentHandler extends DefaultHandler {
	private List<MLInfo> infos = null;
	private MLInfo mlInfo = null;
	private String tagName = null;
	
 	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
 		String temp = new String(ch, start, length);
 		if(tagName.equals("id")){
 			mlInfo.setId(temp);
 		}
 		else if(tagName.equals("ml.name")){
 			mlInfo.setId(temp);
 		}
 		else if(tagName.equals("ml.content")){
 			mlInfo.setId(temp);
 		}
 		
 	}
	@Override
	public void endDocument() throws SAXException {
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(tagName.equals("position")){
			infos.add(mlInfo);
		}
		tagName = "";
	}
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		this.tagName = localName;
		if(tagName.equals("position")){
			mlInfo = new MLInfo();
		}
	}
	public MLListContentHandler(List<MLInfo> infos) {
		super();
		this.infos = infos;
	}
	public List<MLInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<MLInfo> infos) {
		this.infos = infos;
	}
	
	
}
