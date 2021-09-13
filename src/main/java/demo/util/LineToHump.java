package demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineToHump {

        private static Pattern linePattern = Pattern.compile("_(\\w)");

        /** 下划线转驼峰 */

        public static String lineToHump(String str) {

            str = str.toLowerCase();

            Matcher matcher = linePattern.matcher(str);

            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {

                matcher.appendReplacement(sb, matcher .group(1).toUpperCase());

            }

            matcher.appendTail(sb);

            return sb.toString();

        }

        /** 驼峰转下划线 */

        public static String humpToLine(String str) {

            return str.replaceAll("[A-Z]", "_$0").toLowerCase();

        }

        private static Pattern humpPattern = Pattern.compile("[A-Z]");

        /** 驼峰转下划线 */

        public static String humpToLine2(String str) {

            Matcher matcher = humpPattern.matcher(str);

            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {

                matcher.appendReplacement(sb, "_" + matcher .group(0).toLowerCase());

            }

            matcher.appendTail(sb);

            return sb.toString();

        }

        public static void main(String[] args) {

            String lineToHump = lineToHump("hello_guys");

            System.out.println(lineToHump);

            System.out.println(humpToLine(lineToHump));

            System.out.println(humpToLine2(lineToHump));

        }

}
