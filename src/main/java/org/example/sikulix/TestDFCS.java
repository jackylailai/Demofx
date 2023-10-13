package org.example.sikulix;

import org.example.utils.ImagePath;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.example.controller.QuizController.operationCounts;


public class TestDFCS {
    public static List<String> dfcsmock() {
//        StringBuilder sb = new StringBuilder();
        List<String> extractedTextList = new ArrayList<>();
        Screen screen = new Screen(1);
        String path = TestDFCS.class.getResource("/op/").getPath();
        path = "/op/";
//        String chromeImage = path +"chrome.png";
//        String signalIntensityImage = path +"截收訊號強度.png";
//        String limitFrequencyImage = path +"終止頻率.png";
//        String startFrequencyImage = path +"起始頻率.png";
//        String subtractionImage = path +"無廣播抑制.png";

        ImagePath imagePath = new ImagePath();
        System.out.println(path);
        BufferedImage chromeImage = imagePath.loadImage( path+"chrome.png");
        System.out.println(chromeImage);

        BufferedImage signalIntensityImage = imagePath.loadImage(path+"截收訊號強度.png");
        BufferedImage limitFrequencyImage = imagePath.loadImage(path + "終止頻率.png");
        BufferedImage startFrequencyImage = imagePath.loadImage(path + "起始頻率.png");
        BufferedImage subtractionImage = imagePath.loadImage(path + "無廣播抑制.png");
        if (chromeImage == null || signalIntensityImage == null || limitFrequencyImage == null
                || startFrequencyImage == null || subtractionImage == null) {
            // 處理無法讀取圖像的情況
            System.err.println("無法讀取一或多個圖像");
            return extractedTextList;
        }
        try {
            Pattern chromePattern = new Pattern(chromeImage);

            Pattern signalIntensityPattern = new Pattern(signalIntensityImage);
            Pattern limitFrequencyPattern = new Pattern(limitFrequencyImage);
            Pattern startFrequencyPattern = new Pattern(startFrequencyImage);
            Pattern subtractionPattern = new Pattern(subtractionImage);
            System.out.println(chromePattern);

            screen.find(chromePattern).click();
            switch (operationCounts) {
                case 1:
                    if (screen.exists(subtractionPattern.similar(0.7), 5) != null) {
                        System.out.println("找到廣播sub");
                        screen.wait(subtractionPattern, 5);
//                screen.find(subtractionPattern).click();
                        Region subtractionRegion = screen.find(subtractionPattern);
                        int offsetX =218;  // 向右位移的像素值
                        int offsetY = 11;  // 向下位移的像素值
                        int newX = subtractionRegion.getX() + offsetX;//抓到位置後位移到該xy
                        int newY = subtractionRegion.getY() + offsetY;
                        int newWidth = 70;//先給個default宣告
                        int newHeight = 38;
                        Region newRegion = new Region(newX, newY, newWidth, newHeight);//設定最後要擷取的範圍
                        newRegion.highlight(3);
                        String extractedText = newRegion.text();
                        System.out.println("操作結果低"+extractedText);
                        offsetX =370;  // 向右位移的像素值
                        offsetY = 11;  // 向下位移的像素值
                        newX = subtractionRegion.getX() + offsetX;//抓到位置後位移到該xy
                        newY = subtractionRegion.getY() + offsetY;
                        newWidth = 80;//先給個default宣告
                        newHeight = 38;
                        Region highRegion = new Region(newX,newY,newWidth,newHeight);
                        highRegion.highlight(3);
                        String extractedHighText = highRegion.text();
                        System.out.println("操作結果高"+extractedHighText);
//                        screen.find(signalIntensityPattern).click();
                        extractedTextList.add(extractedText);
                        extractedTextList.add(extractedHighText);
                        return extractedTextList;
                    }
//                    if (screen.exists(signalIntensityPattern.similar(0.7), 5) != null) {
//                        System.out.println("找到signalintensity");
//                        screen.wait(signalIntensityPattern, 5);
////                        screen.find(signalIntensityPattern).click();
//                        Region signalIntensityRegion = screen.find(signalIntensityPattern);
//                        int offsetX = 115;  // 向右位移的像素值
//                        int offsetY = 0;  // 向下位移的像素值
//                        int newX = signalIntensityRegion.getX() + offsetX;//抓到位置後位移到該xy
//                        int newY = signalIntensityRegion.getY() + offsetY;
//                        int newWidth = 70;//先給個default宣告
//                        int newHeight = 40;
//                        Region newRegion = new Region(newX, newY, newWidth, newHeight);//設定最後要擷取的範圍
//                        newRegion.highlight(3);
//                        String extractedText = newRegion.text();
//                        System.out.println("操作結果"+extractedText);
//                        return extractedText;
//                    }
                case 2:
                    if (screen.exists(signalIntensityPattern.similar(0.7), 5) != null) {
                        System.out.println("找到signalintensity");
                        screen.wait(signalIntensityPattern, 5);
//                        screen.find(signalIntensityPattern).click();
                        Region signalIntensityRegion = screen.find(signalIntensityPattern);
                        int offsetX = 115;  // 向右位移的像素值
                        int offsetY = 0;  // 向下位移的像素值
                        int newX = signalIntensityRegion.getX() + offsetX;//抓到位置後位移到該xy
                        int newY = signalIntensityRegion.getY() + offsetY;
                        int newWidth = 70;//先給個default宣告
                        int newHeight = 40;
                        Region newRegion = new Region(newX, newY, newWidth, newHeight);//設定最後要擷取的範圍
                        newRegion.highlight(3);
                        String extractedText = newRegion.text();
                        System.out.println("操作結果"+extractedText);
                        extractedTextList.add(extractedText);
                        return extractedTextList;
                    }
//                    if (screen.exists(startFrequencyPattern.similar(0.7), 5) != null) {
//                        System.out.println("found startFrequencyPattern");
//                        screen.wait(startFrequencyPattern, 5);
////                        screen.find(startFrequencyPattern).click();
//                        Region startFrequencyRegion = screen.find(startFrequencyPattern);
//                        int offsetX = 85;  // 向右位移的像素值
//                        int offsetY = 5;  // 向下位移的像素值
//                        int newX = startFrequencyRegion.getX() + offsetX;//抓到位置後位移到該xy
//                        int newY = startFrequencyRegion.getY() + offsetY;
//                        int newWidth = 70;//先給個default宣告
//                        int newHeight = 40;
//                        Region newRegion = new Region(newX, newY, newWidth, newHeight);//設定最後要擷取的範圍
//                        newRegion.highlight(3);
//                        String extractedText = newRegion.text();
//                        System.out.println("操作結果"+extractedText);
//                        return extractedText;
//                    }
                case 3:
                    if (screen.exists(startFrequencyPattern.similar(0.7), 5) != null) {
                        System.out.println("found startFrequencyPattern");
                        screen.wait(startFrequencyPattern, 5);
//                        screen.find(startFrequencyPattern).click();
                        Region startFrequencyRegion = screen.find(startFrequencyPattern);
                        int offsetX = 85;  // 向右位移的像素值
                        int offsetY = 5;  // 向下位移的像素值
                        int newX = startFrequencyRegion.getX() + offsetX;//抓到位置後位移到該xy
                        int newY = startFrequencyRegion.getY() + offsetY;
                        int newWidth = 70;//先給個default宣告
                        int newHeight = 40;
                        Region newRegion = new Region(newX, newY, newWidth, newHeight);//設定最後要擷取的範圍
                        newRegion.highlight(3);
                        String extractedText = newRegion.text();
                        System.out.println("操作結果起始頻率:"+extractedText);
                        extractedTextList.add(extractedText);
                    }
                    if (screen.exists(limitFrequencyPattern.similar(0.7), 5) != null) {
                        System.out.println("found limitFrequencyPattern");
                        screen.wait(limitFrequencyPattern, 5);
//                        screen.find(limitFrequencyPattern).click();
                        Region limitFrequencyRegion = screen.find(limitFrequencyPattern);
                        int offsetX = 75;  // 向右位移的像素值
                        int offsetY = 0;  // 向下位移的像素值
                        int newX = limitFrequencyRegion.getX() + offsetX;//抓到位置後位移到該xy
                        int newY = limitFrequencyRegion.getY() + offsetY;
                        int newWidth = 70;//先給個default宣告
                        int newHeight = 40;
                        Region newRegion = new Region(newX, newY, newWidth, newHeight);//設定最後要擷取的範圍
                        newRegion.highlight(3);
                        String extractedText = newRegion.text();
                        System.out.println("操作結果終止頻率:"+extractedText);
                        extractedTextList.add(extractedText);
                        return extractedTextList;
                    }

            }

        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}