package top.jfunc.validation.core;


import top.jfunc.validation.utils.CharUtil;
import top.jfunc.validation.utils.CommonUtil;
import top.jfunc.validation.utils.IpUtil;
import top.jfunc.validation.utils.RegexUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * ValidateProcess
 * Created by ZhangGang on 2016/9/21.
 */
class ValidateProcess {

    private final static int[] factorArr = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
    private final static char[] parityBit = {'1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2'};

    private final static Pattern REGEX_AREA = Pattern.compile("^[0-9]{2}$");
    private final static Pattern REGEX_DATE8 = Pattern.compile("^[0-9]{8}$");

    //private final static Pattern REGEX_IP = Pattern.compile("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)");
    private static Pattern REGEX_IPV6_PATTERN = Pattern.compile(
            "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))$");
    private static Pattern REGEX_IPV4_PATTERN = Pattern
            .compile("^(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$");



    private final static Pattern REGEX_ENGLISH = Pattern.compile("^[a-zA-z]{1,}$");
    private final static Pattern REGEX_PHONE = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$");
    private final static Pattern REGEX_EMAIL = Pattern.compile("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");

    private final static int MIN_YEAR = 1700;
    private final static int MAX_YEAR = 2500;

    private final static Map<Integer, String> zoneNum = new HashMap<>();

    private final static String NOT_NULL_ERROR_MSG = "非空校验失败";
    private final static String REGEX_ERROR_MSG = "正则校验失败";
    private final static String MAX_ERROR_MSG = "最大值校验失败";
    private final static String MIN_ERROR_MSG = "最小值校验失败";
    private final static String MAX_LENGTH_ERROR_MSG = "最大长度校验失败";
    private final static String MIN_LENGTH_ERROR_MSG = "最小长度校验失败";
    private final static String DATE_FORMAT_ERROR_MSG = "日期格式校验失败";
    private final static String ID_CARD_ERROR_MSG = "身份证校验失败";
    private final static String IP_ERROR_MSG = "IP地址校验失败";
    private final static String CHINESE_ERROR_MSG = "中文校验失败";
    private final static String ENGLISH_ERROR_MSG = "英文校验失败";
    private final static String EMAIL_ERROR_MSG = "邮箱校验失败";
    private final static String PHONE_NUM_ERROR_MSG = "手机号校验失败";

    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
    }

    static void notNull(Object value) {
        if (null == value) throw new IllegalArgumentException(NOT_NULL_ERROR_MSG);
    }

    static void notNull(String value) {
        if (CommonUtil.isNull(value)) throw new IllegalArgumentException(NOT_NULL_ERROR_MSG);
    }

    static void notNull(Number number) {
        if (null == number) throw new IllegalArgumentException(NOT_NULL_ERROR_MSG);
    }

    static void notNull(Collection value) {
        if (CommonUtil.isNull(value)) throw new IllegalArgumentException(NOT_NULL_ERROR_MSG);
    }

    static void notNull(Map value) {
        if (CommonUtil.isNull(value)) throw new IllegalArgumentException(NOT_NULL_ERROR_MSG);
    }

    static void notNull(Object[] value) {
        if (CommonUtil.isNull(value)) throw new IllegalArgumentException(NOT_NULL_ERROR_MSG);
    }

    static void regex(String regex, String value) {
        regex(Pattern.compile(regex), value, REGEX_ERROR_MSG + ", regex:" + regex + ", value:" + value);
    }

    private static void regex(Pattern regex, String value, String msg) {
        if (!CommonUtil.isNull(value)) {
            if (!RegexUtil.test(regex, value)) {
                throw new IllegalArgumentException(msg);
            }
        }
    }

    static void max(int max, int value) {
        if (value > max) throw new IllegalArgumentException(MAX_ERROR_MSG + ", max:" + max + ", value:" + value);
    }

    static void max(long max, long value) {
        if (value > max) throw new IllegalArgumentException(MAX_ERROR_MSG + ", max:" + max + ", value:" + value);
    }

    static void max(float max, float value) {
        if (value > max) throw new IllegalArgumentException(MAX_ERROR_MSG + ", max:" + max + ", value:" + value);
    }

    static void max(double max, double value) {
        if (value > max) throw new IllegalArgumentException(MAX_ERROR_MSG + ", max:" + max + ", value:" + value);
    }

    static void max(byte max, byte value) {
        if (value > max) throw new IllegalArgumentException(MAX_ERROR_MSG + ", max:" + max + ", value:" + value);
    }

    static void max(short max, short value) {
        if (value > max) throw new IllegalArgumentException(MAX_ERROR_MSG + ", max:" + max + ", value:" + value);
    }

    static void maxLength(int max, String value) {
        if (!CommonUtil.isNull(value)) {
            if (value.length() > max)
                throw new IllegalArgumentException(MAX_LENGTH_ERROR_MSG + ", max:" + max + ", value:" + value);
        }
    }

    static void maxLength(int max, Collection value) {
        if (null != value) {
            if (value.size() > max)
                throw new IllegalArgumentException(MAX_LENGTH_ERROR_MSG + ", max:" + max + ", value:" + value.size());
        }
    }

    static void maxLength(int max, Map value) {
        if (null != value) {
            if (value.size() > max)
                throw new IllegalArgumentException(MAX_LENGTH_ERROR_MSG + ", max:" + max + ", value:" + value.size());
        }
    }

    static void maxLength(int max, Object[] value) {
        if (null != value) {
            if (value.length > max)
                throw new IllegalArgumentException(MAX_LENGTH_ERROR_MSG + ", max:" + max + ", value:" + value.length);
        }
    }

    static void min(int min, int value) {
        if (value < min) throw new IllegalArgumentException(MIN_ERROR_MSG + ", min:" + min + ", value:" + value);
    }

    static void min(long min, long value) {
        if (value < min) throw new IllegalArgumentException(MIN_ERROR_MSG + ", min:" + min + ", value:" + value);
    }

    static void min(float min, float value) {
        if (value < min) throw new IllegalArgumentException(MIN_ERROR_MSG + ", min:" + min + ", value:" + value);
    }

    static void min(double min, double value) {
        if (value < min) throw new IllegalArgumentException(MIN_ERROR_MSG + ", min:" + min + ", value:" + value);
    }

    static void min(byte min, byte value) {
        if (value < min) throw new IllegalArgumentException(MIN_ERROR_MSG + ", min:" + min + ", value:" + value);
    }

    static void min(short min, short value) {
        if (value < min) throw new IllegalArgumentException(MIN_ERROR_MSG + ", min:" + min + ", value:" + value);
    }

    static void minLength(int min, String value) {
        if (!CommonUtil.isNull(value)) {
            if (value.length() < min) throw new IllegalArgumentException(MIN_ERROR_MSG + ", min:" + min + ", value:" + value);
        }
    }

    static void minLength(int min, Collection value) {
        if (null != value) {
            if (value.size() < min)
                throw new IllegalArgumentException(MIN_LENGTH_ERROR_MSG + ", min:" + min + ", value:" + value.size());
        }
    }

    static void minLength(int min, Map value) {
        if (null != value) {
            if (value.size() < min)
                throw new IllegalArgumentException(MIN_LENGTH_ERROR_MSG + ", min:" + min + ", value:" + value.size());
        }
    }

    static void minLength(int min, Object[] value) {
        if (null != value) {
            if (value.length < min)
                throw new IllegalArgumentException(MIN_LENGTH_ERROR_MSG + ", min:" + min + ", value:" + value.length);
        }
    }

    static void date(String format, String value) {
        if (!CommonUtil.isNull(value)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                simpleDateFormat.parse(value);
            } catch (ParseException e) {
                throw new IllegalArgumentException(DATE_FORMAT_ERROR_MSG + ", format:" + format + ", value:" + value);
            }
        }
    }

    /**
     * 身份证15位编码规则：dddddd yymmdd xx p
     * dddddd：地区码
     * yymmdd: 出生年月日
     * xx: 顺序类编码
     * p: 性别，奇数为男，偶数为女
     * <p/>
     * 身份证18位编码规则：dddddd yyyymmdd xxx y
     * dddddd：地区码
     * yyyymmdd: 出生年月日
     * xxx:顺序类编码，奇数为男，偶数为女
     * y: 校验码，该位数值可通过前17位计算获得
     * <p/>
     * 18位号码加权因子为(从右到左) wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ]
     * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
     * 校验位计算公式：Y_P = mod( ∑(Ai×wi),11 )
     * i为身份证号码从右往左数的 2...18 位; Y_P为校验码所在校验码数组位置
     */
    static void idCard(String value) {
        if (!CommonUtil.isNull(value)) {
            String idCard = value.toLowerCase();
            int length = idCard.length();
            //校验位数
            if (length != 15 && length != 18) {
                throw new IllegalArgumentException(ID_CARD_ERROR_MSG + ", value:" + value);
            }
            //校验区域
            if (!isArea(idCard.substring(0, 2))) {
                throw new IllegalArgumentException(ID_CARD_ERROR_MSG + ", value:" + value);
            }
            //校验日期
            if (15 == length && !isDate6(idCard.substring(6, 12))) {
                throw new IllegalArgumentException(ID_CARD_ERROR_MSG + ", value:" + value);
            }
            if (18 == length && !isDate8(idCard.substring(6, 14))) {
                throw new IllegalArgumentException(ID_CARD_ERROR_MSG + ", value:" + value);
            }
            //校验18位校验码
            if (18 == length) {
                char[] idCardArray = idCard.toCharArray();
                int sum = 0;
                for (int i = 0; i < idCardArray.length - 1; i++) {
                    if (idCardArray[i] < '0' || idCardArray[i] > '9') {
                        throw new IllegalArgumentException(ID_CARD_ERROR_MSG + ", value:" + value);
                    }
                    sum += (idCardArray[i] - '0') * factorArr[i];
                }
                if (idCardArray[idCardArray.length - 1] != parityBit[sum % 11]) {
                    throw new IllegalArgumentException(ID_CARD_ERROR_MSG + ", value:" + value);
                }
            }
        }
    }

    private static boolean isArea(String area) {
        return RegexUtil.test(REGEX_AREA, area) && zoneNum.containsKey(Integer.valueOf(area));
    }

    private static boolean isDate6(String date) {
        return isDate8("19" + date);
    }

    private static boolean isDate8(String date) {
        if (!RegexUtil.test(REGEX_DATE8, date)) {
            return false;
        }
        int[] iaMonthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
        return !(year < MIN_YEAR || year > MAX_YEAR) && !(month < 1 || month > 12) && !(day < 1 || day > iaMonthDays[month - 1]);
    }


    ///
    /*static void isIp(String value) {
        regex(REGEX_IP, value, IP_ERROR_MSG + ", value:" + value);
    }*/

    static void isIp(String value) {
        boolean isIp = IpUtil.isIp(value);
        if(!isIp){
            throw new IllegalArgumentException(IP_ERROR_MSG + ", value:" + value);
        }
    }

    static void chinese(String value) {
        boolean ret = CharUtil.isChinese(value);
        if (!ret) {
            throw new IllegalArgumentException(CHINESE_ERROR_MSG + ", value:" + value);
        }
    }

    static void english(String value) {
        regex(REGEX_ENGLISH, value, ENGLISH_ERROR_MSG + ", value:" + value);
    }

    static void phone(String value) {
        regex(REGEX_PHONE, value, PHONE_NUM_ERROR_MSG + ", value:" + value);
    }

    static void email(String value) {
        regex(REGEX_EMAIL, value, EMAIL_ERROR_MSG + ", value:" + value);
    }
}
