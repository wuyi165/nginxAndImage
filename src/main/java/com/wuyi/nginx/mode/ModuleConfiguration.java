package com.wuyi.nginx.mode;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 模块配置
 */
@Data
public class ModuleConfiguration extends  BaseConfiguration{

    public ModuleConfiguration(String name){
        moduleName = name;
    }


    /**
     * 模块名称
     */
    public String moduleName;

    /**
     * 通用配置模块内容
     */
    public List<CommonConfigurations> commlist =new LinkedList<>();

    /**
     * 模块包含模块内容
     */
    public List<ModuleConfiguration> moduList=new LinkedList<>();


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
        sb.append(NEWLINE+NEWLINE);
        if(annotate!=null ){
            sb.append("#"+annotate + NEWLINE);
        }
        if(isEnable){
            sb.append(moduleName+" {"+NEWLINE);
        }else{
            sb.append("#"+moduleName+" {"+NEWLINE);
        }

        if(commlist.size()>0){
            for(CommonConfigurations config:commlist){
                if(isEnable) {
                    sb.append(config.getBodyFornmart());
                }else{
                    sb.append("#"+config.getBodyFornmart());
                }
            }
        }

        if(moduList.size()>0){
            for(ModuleConfiguration config:moduList){
                sb.append(config.getBodyFornmart());
            }
        }

        if(isEnable){
            sb.append("}"+NEWLINE);
        }else{
            sb.append("#}"+NEWLINE);
        }

        return sb.toString();
    }
}
