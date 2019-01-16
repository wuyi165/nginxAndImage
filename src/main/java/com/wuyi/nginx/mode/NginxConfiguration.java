package com.wuyi.nginx.mode;

import lombok.Data;

import java.util.List;

@Data
public class NginxConfiguration  extends  BaseConfiguration{

    /**
     * 通用配置模块内容
     */
    public List<CommonConfigurations> commlist;

    /**
     * 模块包含模块内容
     */
    public List<ModuleConfiguration> moduList;


    /**
     * 格式化
     * @return
     */
    public String getBodyFornmart(){
        StringBuilder sb = new StringBuilder();
        if(commlist!=null){
            for(CommonConfigurations config:commlist){
                sb.append(config.getBodyFornmart());
            }
        }

        if(moduList!=null){
            for(ModuleConfiguration config:moduList){
                sb.append(config.getBodyFornmart());
            }
        }
        return sb.toString();
    }
}
