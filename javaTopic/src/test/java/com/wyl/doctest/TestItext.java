package com.wyl.doctest;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * 
 */
public class TestItext {

	public static void main(String[] args) throws Exception {
		// 创建word文档,并设置纸张的大小
		Document document = new Document(PageSize.A4);
		RtfWriter2.getInstance(document, new FileOutputStream("word.doc"));
		document.open();

		Image image1 = Image.getInstance("/Users/wangyulin/Desktop/A.JPG");
		image1.scaleAbsolute(400f, 300f);
		image1.setAlignment(Image.MIDDLE);
		document.add(image1);

		Image image2 = Image.getInstance("/Users/wangyulin/Desktop/B.JPG");
		image2.scaleAbsolute(400f, 300f);
		image2.setAlignment(Image.MIDDLE);
		document.add(image2);

		document.close();
	}
}
