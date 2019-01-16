package com.wuyi.nginx.mode;

public abstract class BaseConfiguration {
    public static String  NEWLINE= System.getProperty("line.separator");
    public static String  LINEEND=";";

    public abstract String getBodyFornmart();
}
