package com.wyl.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import java.io.IOException;

/**
 * Created by wangyulin on 27/02/2017.
 */
public class ImageUtils {

    public static void main(String[] args) throws IOException {
        String path = "/Users/wangyulin/Desktop/";
        Thumbnails.of(path+"zhuti.jpeg").size(100, 100).toFile(path+"zhuti.jpeg_small.jpeg");
        //Thumbnails.of(path+"/image/blue.jpg").size(2560, 2048).toFile(path+"/thumb/thumb_large_blue.jpg");

        Thumbnails.of(path+"zhuti.jpeg").sourceRegion(Positions.CENTER, 400,400).size(200, 200).keepAspectRatio(false).toFile(path+"thumb_regioncenter_zhuti.jpg");
        Thumbnails.of(path+"zhuti.jpeg").sourceRegion(Positions.BOTTOM_RIGHT, 400,400).size(200, 200).keepAspectRatio(false).toFile(path+"thumb_regionbr_zhuti.jpg");
        //Thumbnails.of(path+"zhuti.jpeg").sourceRegion(650 , 500 , 400 , 400).size(200, 200).keepAspectRatio(false).toFile(path+"thumb_realregion_zhuti.jpg");
    }

}
