//package org.example.sikulix;
//
//import org.example.utils.ImagePath;
//import org.sikuli.script.*;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.example.controller.QuizController.operationCounts;
//import static org.example.controller.TsController.jsonNodeForUser;
//import static org.example.controller.UnitController.unitsData;
//
//public class ScreenForTest {
//    public static List<String> testScreenShot() {
//        List<String> extractedTextList = new ArrayList<>();
//        Screen screen = new Screen(1);
//        String path = TestDFCS.class.getResource("/op/").getPath();
//        path = "/op/";
////        String chromeImage = path +"chrome.png";
////        String signalIntensityImage = path +"截收訊號強度.png";
////        String limitFrequencyImage = path +"終止頻率.png";
////        String startFrequencyImage = path +"起始頻率.png";
////        String subtractionImage = path +"無廣播抑制.png";
//
//        ImagePath imagePath = new ImagePath();
//        System.out.println(path);
//
////        BufferedImage signalIntensityImage = imagePath.loadImage(path+"截收訊號強度real.png");
//        BufferedImage signalIntensityImage = imagePath.loadImage(path+"截收訊號強度.png");
//        BufferedImage limitFrequencyImage = imagePath.loadImage(path + "終止頻率.png");
//        BufferedImage startFrequencyImage = imagePath.loadImage(path + "起始頻率.png");
//        BufferedImage subtractionImage = imagePath.loadImage(path + "無廣播抑制.png");
//        BufferedImage deleteAngleImage = imagePath.loadImage(path + "剔除角度.png");
//        if (chromeImage == null || signalIntensityImage == null || limitFrequencyImage == null
//                || startFrequencyImage == null || subtractionImage == null) {
//            // 處理無法讀取圖像的情況
//            System.err.println("無法讀取一或多個圖像");
//            return extractedTextList;
//        }
//        try {
//            Pattern chromePattern = new Pattern(chromeImage);
//
//            Pattern signalIntensityPattern = new Pattern(signalIntensityImage);
////            Pattern limitFrequencyPattern = new Pattern(limitFrequencyImage);
////            Pattern startFrequencyPattern = new Pattern(startFrequencyImage);
//            Pattern subtractionPattern = new Pattern(subtractionImage);
//            Pattern deleteAnglePattern = new Pattern(deleteAngleImage);
////            System.out.println(chromePattern);
//
//            screen.find(chromePattern).click();
//            if (screen.exists(subtractionPattern.similar(0.7), 6) != null) {
//                System.out.println("找到廣播sub的圖");
//                screen.wait(subtractionPattern, 5);
////                screen.find(subtractionPattern).click();
//                Region subtractionRegion = screen.find(subtractionPattern);
//                int offsetX = 218;  // 向右位移的像素值
//                int offsetY = 11;  // 向下位移的像素值
//                int newX = subtractionRegion.getX() + offsetX;//抓到位置後位移到該xy
//                int newY = subtractionRegion.getY() + offsetY;
//                int newWidth = 70;//先給個default宣告
//                int newHeight = 38;
//                Region newRegion = new Region(newX, newY, newWidth, newHeight);//設定最後要擷取的範圍
//                newRegion.highlight(3);
//                String extractedText = newRegion.text();
//                System.out.println("操作結果低" + extractedText);
//                offsetX = 370;  // 向右位移的像素值
//                offsetY = 11;  // 向下位移的像素值
//                newX = subtractionRegion.getX() + offsetX;//抓到位置後位移到該xy
//                newY = subtractionRegion.getY() + offsetY;
//                newWidth = 80;//先給個default宣告
//                newHeight = 38;
//                Region highRegion = new Region(newX, newY, newWidth, newHeight);
//                highRegion.highlight(3);
//                String extractedHighText = highRegion.text();
//                System.out.println("操作結果高" + extractedHighText);
////                        screen.find(signalIntensityPattern).click();
//                extractedTextList.add(extractedText);
//                extractedTextList.add(extractedHighText);
//
//                newX = subtractionRegion.getX() - 10;
//                newY = subtractionRegion.getY() - 180;
//                Region screenRegion = new Region(newX, newY, 625, 530);
//                screenRegion.highlight(3);
//                String screenshotsPath = "C:\\serverdb\\screenshots";
//
//                File screenshotsDirectory = new File(screenshotsPath);
//                if (!screenshotsDirectory.exists()) {
//                    screenshotsDirectory.mkdirs();
//                }
//                Screen screenShot = new Screen();
//                ScreenImage regionImage = screenShot.capture(screenRegion);
//                String screenshotName = jsonNodeForUser.get("name").asText() + "UnitFirst" + unitsData.get(0).getUnitId();
//                long timestamp = System.currentTimeMillis();
////                        String customScreenshotName = generateCustomScreenshotName(screenshotName);
////                        String screenshotPath = screenshotsPath + File.separator + customScreenshotName + ".png";
//                String screenshotPath = screenshotsPath + File.separator + screenshotName + timestamp + ".png";
//
//                String codingName = screenshotName + timestamp;
//                screenshotMap.put(screenshotName, screenshotPath);
//                System.out.println(screenshotMap);
//                System.out.println(screenshotPath + "::::screenshotPath");
//                regionImage.save(screenshotsPath, codingName);
//
//                return extractedTextList;
//            }
//        }catch (FindFailed e) {
////            throw new RuntimeException(e);
//        return null;
//    }catch (NullPointerException e) {
//        e.printStackTrace();
//        List<String> errorList = new ArrayList<>();
//        errorList.add("NullPointerException");
//        return errorList;
//    }
//        return null;
//}
