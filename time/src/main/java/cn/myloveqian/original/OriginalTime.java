package cn.myloveqian.original;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by diaoz on 2017/1/8.
 * cn.myloveqian.original
 */
public class OriginalTime {

    public static void main(String[] args) {

        /**
         * 获取当前的日期，时间，自动格式化
         * 2017-01-08
         * 13:10:16.014
         * 2017-01-08T13:10:16.014
         * output:today's a local date:2017-01-08 today's a local time : 13:10:16.014 today's a local date and local time : 2017-01-08T13:10:16.014
         */
        LocalDate today = LocalDate.now();
        LocalTime todayTime = LocalTime.now();
        LocalDateTime todayDateTime = LocalDateTime.now();
        Clock currentClock = Clock.systemUTC();
        Clock currentZoneClock = Clock.systemDefaultZone();
        System.out.println("today's a local date:" + today +
                " today's a local time : " + todayTime +
                " today's a local date and local time : " + todayDateTime);
        System.out.println("current clock : " + currentClock.toString() +
                "current zone clock : " + currentZoneClock.toString());

        /**
         * 2017
         * 1
         * 8
         * output:Year : 2017  Month : 1  Day: 8
         */
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d  Month : %d  Day: %d %n", year, month, day);

        /**
         *
         * 定义日期
         * 2016-12-31
         * 2016-12-31T13:23:32
         * output:special date : 2016-12-31, special date time : 2016-12-31T13:23:32
         */
        LocalDate specialDate = LocalDate.of(2016, 12, 31);
        LocalDateTime specialDateTime = LocalDateTime.of(2016, 12, 31, 13, 23, 32);
        System.out.println("special date : " + specialDate +
                ", special date time : " + specialDateTime);

        /**
         * 时间的加减，和原来相差无几,优点是线程安全的，不过重新定义的时间需要重新赋值。
         * 15:55:19.182
         * 2015-01-29
         * 2017-01-15
         * output:Time after change : 13:09:58.380,day after change: 2015-01-29,other method to update time : 2017-01-15
         */
        LocalTime afterTime = todayTime.plusHours(2).plusMinutes(50L).plusSeconds(10L).minusHours(3);
        LocalDate afterDate = today.plusWeeks(3).minusYears(2);
        LocalDate otherMethod = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Time after change : " + afterTime +
                ",day after change: " + afterDate +
                ",other method to update time : " + otherMethod);

        /**
         * 日期比较
         * output:special date early: true, special date late: false, the sample day: false
         */
        System.out.println("special date early: " + specialDate.isBefore(today) +
                ", special date late: " + specialDate.isAfter(today) +
                ", the sample day: " + specialDate.isEqual(today));

        /**
         * 每年同一天的比较，这个比较方便
         * output:nothing
         */
        LocalDate memorialDay = LocalDate.of(2014, 6, 16);
        MonthDay memorialMonthDay = MonthDay.of(memorialDay.getMonth(), memorialDay.getDayOfMonth());
        MonthDay todayMonthDay = MonthDay.from(today);
        System.out.println(todayMonthDay.equals(memorialMonthDay) ? "today is our memorial day!" : "nothing");
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);

        /**
         * 时区转换
         * 2017-01-08T14:38:58.575-05:00[America/New_York]
         * output:Current date and time in a particular timezone : 2017-01-08T14:38:58.575-05:00[America/New_York]
         */
        ZoneId america = ZoneId.of("America/New_York");
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(todayDateTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);

        /**
         * 算两个时间的间隔
         * today and special
         */
        LocalDate myDate = LocalDate.of(2016, 11, 30);
        Period between = Period.between(myDate, today);
        System.out.println("Months left between today and special : " + between.getMonths());

        /**
         * 时间戳
         */
        Instant instant = Instant.now();
        System.out.println("timestamp: " + instant);

        String someDate = "20140116";
        LocalDate formatted = LocalDate.parse(someDate, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", someDate, formatted);

        LocalDateTime arrivalDate  = LocalDateTime.now();
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy  hh:mm a");
            String landing = arrivalDate.format(format);
            System.out.printf("Arriving at :  %s %n", landing);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        }

    }

}
