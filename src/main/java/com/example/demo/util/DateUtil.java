package com.example.demo.util;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.Date;public class DateUtil {    public static Date dateformate(String time) {        Date tem = null;        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        try {            tem = sdf.parse(time);        }catch (ParseException e){            e.getMessage();        }        return tem;    }}