package testcodes.json;

import com.anson.acode.ALog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

    public static void testDahongWeather(){
        String SUG = "suggesition";
        String SUG_BRF = "brf";
        String SUG_NAME = "name";
        String SUG_DETAIL = "txt";

        String WEA = "weather";
        String WEA_DATE = "date";
        String WEA_RISE = "sun_rise";
        String WEA_SET = "sun_set";
        String WEA_HIGH = "temp_high";
        String WEA_LOW = "temp_low";
        String WEA_INFO = "weather";
        String WEA_WIN0 = "wing";
        String WEA_WIN1 = "wingp";
        String json = "{\"suggesition\":[{\"brf\":\"适宜\",\"name\":\"旅游\",\"txt\":\"有降水，虽然风稍大，但温度适宜，适宜旅游，可不要错过机会呦！\"},"
                + "{\"brf\":\"较不宜\",\"name\":\"运动\",\"txt\":\"有降水，且风力较强，较适宜在户内进行各种健身休闲运动；若坚持户外运动，请注意保暖。\"}," +
                "{\"brf\":\"较少开启\",\"name\":\"空调\",\"txt\":\"您将感到很舒适，一般不需要开启空调。\"}," +
                "{\"brf\":\"良\",\"name\":\"污染\",\"txt\":\"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。\"}," +
                "{\"brf\":\"不宜\",\"name\":\"洗车\",\"txt\":\"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。\"}," +
                "{\"brf\":\"舒适\",\"name\":\"舒适度\",\"txt\":\"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。\"}," +
                "{\"brf\":\"极易发\",\"name\":\"感冒\",\"txt\":\"将有一次强降温过程，天气寒冷，且空气湿度较大，极易发生感冒，请特别注意增加衣服保暖防寒。\"}," +
                "{\"brf\":\"较冷\",\"name\":\"穿衣\",\"txt\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\"}," +
                "{\"brf\":\"最弱\",\"name\":\"紫外线\",\"txt\":\"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。\"}]," +
                "\"weather\":[{\"date\":\"2016-03-20\",\"sun_rise\":\"06:09\",\"sun_set\":\"18:16\",\"temp_high\":\"17\",\"temp_low\":\"12\",\"weather\":\"小雨\",\"wind\":\"东北风\",\"winp\":\"3-4\"}," +
                "{\"date\":\"2016-03-21\",\"sun_rise\":\"06:08\",\"sun_set\":\"18:17\",\"temp_high\":\"15\",\"temp_low\":\"12\",\"weather\":\"中雨\",\"wind\":\"东北风\",\"winp\":\"3-4\"}," +
                "{\"date\":\"2016-03-22\",\"sun_rise\":\"06:07\",\"sun_set\":\"18:17\",\"temp_high\":\"17\",\"temp_low\":\"14\",\"weather\":\"小雨\",\"wind\":\"无持续风向\",\"winp\":\"微风\"}," +
                "{\"date\":\"2016-03-23\",\"sun_rise\":\"06:06\",\"sun_set\":\"18:18\",\"temp_high\":\"18\",\"temp_low\":\"11\",\"weather\":\"中雨\",\"wind\":\"东北风\",\"winp\":\"3-4\"}," +
                "{\"date\":\"2016-03-24\",\"sun_rise\":\"06:05\",\"sun_set\":\"18:18\",\"temp_high\":\"15\",\"temp_low\":\"10\",\"weather\":\"小雨\",\"wind\":\"东北风\",\"winp\":\"4-5\"}," +
                "{\"date\":\"2016-03-25\",\"sun_rise\":\"06:04\",\"sun_set\":\"18:19\",\"temp_high\":\"12\",\"temp_low\":\"10\",\"weather\":\"中雨\",\"wind\":\"东北风\",\"winp\":\"3-4\"}," +
                "{\"date\":\"2016-03-26\",\"sun_rise\":\"06:02\",\"sun_set\":\"18:19\",\"temp_high\":\"12\",\"temp_low\":\"10\",\"weather\":\"阵雨\",\"wind\":\"无持续风向\",\"winp\":\"微风\"}]}";
        try {
            JSONObject root = new JSONObject(json);

            JSONArray suggs = root.getJSONArray(SUG);
            int count = suggs.length();
            if(count > 0){
                for(int i = 0; i < count; i ++){
                    JSONObject sugg = suggs.getJSONObject(i);
                    String brf = sugg.getString(SUG_BRF);
                    String name = sugg.getString(SUG_NAME);
                    String detail = sugg.getString(SUG_DETAIL);

                }
            }

            JSONArray weas = root.getJSONArray(WEA);
            count = weas.length();
            if(count > 0) {
                for (int i = 0; i < count; i++) {
                    JSONObject w = weas.getJSONObject(i);
                    String weather = w.getString(WEA_INFO);
                    String date = w.getString(WEA_DATE);
                    String high = w.getString(WEA_HIGH);
                    String low = w.getString(WEA_LOW);
                    String rise = w.getString(WEA_RISE);
                    String set = w.getString(WEA_SET);
                    String wing0 = w.getString(WEA_WIN0);
                    String wing1 = w.getString(WEA_WIN1);
                }
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
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
