package com.wuyi.nginx.mode;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class UpstreamConfiguration extends  BaseConfiguration {
    private  static final  String UPSTREAM="upstream";

    public  UpstreamConfiguration(String name){
        this.name = name;
    }

    public String name;

    List<CommonConfigurations> commList = new LinkedList<>();

    @Override
    public String getBodyFornmart() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPSTREAM "+name +"{"+NEWLINE);
        if(commList!=null){
            for(CommonConfigurations config:commList){
                sb.append(config.getBodyFornmart());
            }
        }
        sb.append("}"+NEWLINE);
        return sb.toString();
    }
}
