package com.wuyi.nginx.mode;

import lombok.Data;

/**
 * 公共配置
 */
@Data
public class CommonConfigurations extends BaseConfiguration {

    public  CommonConfigurations(String key,String value){
        this.key = key;
        this.value = value;
        isEnable = true;
        annotate = null;
    }

    public  CommonConfigurations(String key,String value,String annotate ){
        this.key = key;
        this.value = value;
        this.annotate = annotate;
        isEnable = true;

    }

    public  CommonConfigurations(String key,String value,String annotate,boolean enable ){
        this.key = key;
        this.value = value;
        this.annotate = annotate;
        isEnable = enable;
    }

    public  String key;
    public  String value;
    /**
     * 是否启用
     */
    public  boolean isEnable=true;

    /**
     * 注解
     */
    public  String  annotate;




    @Override
    public String getBodyFornmart() {
        StringBuilder sb = new StringBuilder();
        if(annotate !=null){
            sb.append("#"+annotate+NEWLINE);
        }
        if(!isEnable){
            sb.append("#");
        }
        if(value == null || value == ""){
            sb.append(key+LINEEND+NEWLINE);
        }else{
            sb.append(key+"  "+value+LINEEND+NEWLINE);
        }


        return  sb.toString();
    }
}
