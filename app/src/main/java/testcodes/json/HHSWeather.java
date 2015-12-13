package testcodes.json;

import com.anson.acode.ALog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by anson on 15-12-12.
 */
public class HHSWeather {
    public static final String TAG = "HHSWeather";
    public static void testAnaWeather(){
        try {
            //Document mDocument = Jsoup.parse(new File(file), "GBK");
            Document mDocument = Jsoup.parse(new URL("http://www.weather.com.cn/weather1d/101280601.shtml"), 1000);
            if(mDocument != null){
                ALog.d(TAG, "got document");
                /** today **/
                Element eleTodayRoot = mDocument.select("div.t").first();
                if(eleTodayRoot == null){
                    ALog.e("could not find element eleTodayRoot");
                } else {
                    String today_main = eleTodayRoot.select("p.wea").first().text();
                    ALog.d(TAG, "today_main=" + today_main);

                    String today_temp = eleTodayRoot.select("p.tem").select("span").first().text();
                    ALog.d(TAG, "today_temp=" + today_temp);

                    String today_win = eleTodayRoot.select("p.win").select("span").first().text();
                    ALog.d(TAG, "today_win=" + today_win);

                    String today_sunSet = eleTodayRoot.select("p.sun.sunDown").select("span").first().text();
                    ALog.d(TAG, "today_sunSet=" + today_sunSet);

                    String today_sunRise = eleTodayRoot.select("p.sun.sunUp").select("span").first().text();
                    ALog.d(TAG, "today_sunRise=" + today_sunRise);

                    Elements eleTodayHalfs = eleTodayRoot.select("ul.clearfix").select("li");
                    if(eleTodayHalfs.size() == 2) {
                        Element half0 = eleTodayHalfs.get(0);
                        Element half1 = eleTodayHalfs.get(1);

                        String wea0 = half0.select("p.wea").first().text();
                        String wea1 = half1.select("p.wea").first().text();
                        String temp0 = half0.select("p.tem").first().text();
                        String temp1 = half1.select("p.tem").first().text();
                        String win0 = half0.select("p.win").select("span").first().text();
                        String win1 = half1.select("p.win").select("span").first().text();

                        ALog.d(TAG, wea0 + ", " + temp0 + ", " + win0);
                        ALog.d(TAG, wea1 + ", " + temp1 + ", " + win1);
                    }

                    //for Live
                    Element live0 = mDocument.select("li.li1.hot").first();
                    String live0_level = live0.select("span").first().text();
                    String live0_name = live0.select("em").first().text();
                    String live0_tip = live0.select("p").first().text();
                    ALog.d(TAG, "live0:" + live0_level + ", " + live0_name + "," + live0_tip);

                    Element live1 = mDocument.select("li.li2.hot").first();
                    String live1_level = live1.select("span").first().text();
                    String live1_name = live1.select("em").first().text();
                    String live1_tip = live1.select("p").first().text();
                    ALog.d(TAG, "live1:" + live1_level + ", " + live1_name + "," + live1_tip);

                    Element live2 = mDocument.select("li.li3.hot").first();
                    String live2_level = live2.select("span").first().text();
                    String live2_name = live2.select("em").first().text();
                    String live2_tip = live2.select("p").first().text();
                    ALog.d(TAG, "live2:" + live2_level + ", " + live2_name + "," + live2_tip);

                    Element live3 = mDocument.select("li.li4.hot").first();
                    String live3_level = live3.select("span").first().text();
                    String live3_name = live3.select("em").first().text();
                    String live3_tip = live3.select("p").first().text();
                    ALog.d(TAG, "live3:" + live3_level + ", " + live3_name + "," + live3_tip);

                    Element live4 = mDocument.select("li.li5.hot").first();
                    String live4_level = live4.select("span").first().text();
                    String live4_name = live4.select("em").first().text();
                    String live4_tip = live4.select("p").first().text();
                    ALog.d(TAG, "live4:" + live4_level + ", " + live4_name + "," + live4_tip);

                    Element live5 = mDocument.select("li.li6.hot").first();
                    String live5_level = live5.select("span").first().text();
                    String live5_name = live5.select("em").first().text();
                    String live5_tip = live5.select("p").first().text();
                    ALog.d(TAG, "live5:" + live5_level + ", " + live5_name + "," + live5_tip);

                }

                /** Future **/
                mDocument = Jsoup.parse(new URL("http://www.weather.com.cn/weather/101280601.shtml"), 1000);
                if(mDocument == null){
                    ALog.e(TAG, "could not getdocument for Future");
                }else{
                    Element eleFutureRoot = mDocument.select("ul.t.clearfix").first();
                    Elements futureDays = eleFutureRoot.select("li");
                    if(futureDays != null && futureDays.size() > 0){
                        ALog.d(TAG, "get future " + futureDays.size() + " day");
                        for(Element day : futureDays){
                            String dayTitle = day.select("h1").first().text();
                            String dayWea = day.select("p.wea").first().text();
                            String dayTemp = day.select("p.tem").first().text();
                            String dayWin = day.select("p.win").select("i").first().text();
                            ALog.d(TAG, dayTitle + "," + dayWea + "," + dayTemp + "," + dayWin);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
