package org.example.sikulix;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import static org.example.controller.QuizController.operationCounts;

public class TestDFCS {
    public static String dfcsmock() {
//        StringBuilder sb = new StringBuilder();

        Screen screen = new Screen(1);
        String path = TestDFCS.class.getResource("/op/").getPath();
        try {
            Pattern chromePattern = new Pattern(path + "/chrome.png");
            Pattern signalIntensityPattern = new Pattern(path + "/截收訊號強度");
            Pattern limitFrequencyPattern = new Pattern(path + "/終止頻率.png");

            Pattern startFrequencyPattern = new Pattern(path + "/起始頻率.png");
            screen.find(chromePattern).click();
            switch (operationCounts) {
                case 1:
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
                        return extractedText;
                    }
                case 2:
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
                        System.out.println("操作結果"+extractedText);
                        return extractedText;
                    }
                case 3:
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
                        System.out.println("操作結果"+extractedText);
                        return extractedText;
                    }
            }

        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}