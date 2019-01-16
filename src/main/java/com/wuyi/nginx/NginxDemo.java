package com.wuyi.nginx;


import com.wuyi.nginx.mode.CommonConfigurations;
import com.wuyi.nginx.mode.ModuleConfiguration;
import com.wuyi.nginx.mode.NginxConfiguration;
import com.wuyi.nginx.mode.UpstreamConfiguration;

import java.util.LinkedList;
import java.util.List;

public class NginxDemo {


    public static void main(String[] args) {
        NginxConfiguration nginxConfiguration = new NginxConfiguration();

        List<ModuleConfiguration>  nginxModuList = new LinkedList<>();
        List<CommonConfigurations> nginxCommList = new LinkedList<>();
        nginxConfiguration.setCommlist(nginxCommList);
        nginxConfiguration.setModuList(nginxModuList);



        nginxCommList.add( new CommonConfigurations("worker_processes","auto"));
        nginxCommList.add(new CommonConfigurations("error_log","logs/error.log"));
        nginxCommList.add(new CommonConfigurations("error_log","logs/error.log  notice"));
        nginxCommList.add(new CommonConfigurations("error_log","logs/error.log  info"));
        nginxCommList.add( new CommonConfigurations("pid"," logs/nginx.pid"));
        nginxCommList.add( new CommonConfigurations("worker_rlimit_nofile","65535","指定进程可以打开的最大描述符：数目"));







        ModuleConfiguration moduleConfiguration = new ModuleConfiguration("events ");

        moduleConfiguration.getCommlist().add(new CommonConfigurations("worker_connections","8096","最大连接数，默认为512"));
        moduleConfiguration.getCommlist().add(new CommonConfigurations("accept_mutex","on","设置网路连接序列化，防止惊群现象发生，默认为on"));
        moduleConfiguration.getCommlist().add(new CommonConfigurations("multi_accept","on","设置一个进程是否同时接受多个网络连接，默认为off"));
        moduleConfiguration.getCommlist().add(new CommonConfigurations("use","epoll","事件驱动模型，select|poll|kqueue|epoll|resig|/dev/poll|eventport",false));
        moduleConfiguration.getCommlist().add(new CommonConfigurations("keepalive_timeout","65","keepalive超时时间"));
        moduleConfiguration.getCommlist().add(new CommonConfigurations("client_header_buffer_size","4k","客户端请求头部的缓冲区大小。这个可以根据你的系统分页大小来设置，一般一个请求头的大小不会超过1k，不过由于一般系统分页都要大于1k，所以这里设置为分页大小。分页大小可以用命令getconf PAGESIZE 取得。"));
        moduleConfiguration.getCommlist().add( new CommonConfigurations("open_file_cache max","65535","这个将为打开文件指定缓存，默认是没有启用的，max指定缓存数量，建议和打开文件数一致，inactive是指经过多长时间文件没被请求后删除缓存。"));
        moduleConfiguration.getCommlist().add( new CommonConfigurations("open_file_cache_valid","80s","这个是指多长时间检查一次缓存的有效信息。"));
        moduleConfiguration.getCommlist().add( new CommonConfigurations("open_file_cache_min_uses","1","指令中的inactive参数时间内文件的最少使用次数，如果超过这个数字，文件描述符一直是在缓存中打开的，如上例，如果有一个文件在inactive时间内一次没被使用，它将被移除"));
//        moduleConfiguration.getCommlist().add( new CommonConfigurations("","",""));
//        moduleConfiguration.getCommlist().add( new CommonConfigurations("","",""));

        nginxModuList.add(moduleConfiguration);


        ModuleConfiguration httpModuleConfiguration = new ModuleConfiguration("http ");
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("include","mime.types","文件扩展名与文件类型映射表"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("default_type","application/octet-stream","#默认文件类型，默认为text/plain"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("access_log","off","取消服务日志",false));


        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("log_format","myFormat '$remote_addr–$remote_user [$time_local] $request $status $body_bytes_sent $http_referer $http_user_agent $http_x_forwarded_for'","自定义格式",false));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("sendfile","on","允许sendfile方式传输文件，默认为off，可以在http块，server块，location块"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("sendfile_max_chunk","100k","每个进程每次调用传输数量不能大于设定的值，默认为0，即不设上限"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("keepalive_timeout","65","连接超时时间，默认为75s，可以在http，server，location块"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("keepalive_requests","100","设置一个keep-alive连接上可以服务的请求的最大数量，当最大请求数量达到时，连接被关闭。默认是100"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_connect_timeout","90","后端服务器连接的超时时间_发起握手等候响应超时时间"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_read_timeout","180","连接成功后_等候后端服务器响应时间_其实已经进入后端的排队之中等候处理（也可以说是后端服务器处理请求的时间）"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_send_timeout","180","后端服务器数据回传时间_就是在规定时间之内后端服务器必须传完所有的数据"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_buffer_size","256k","设置从被代理服务器读取的第一部分应答的缓冲区大小，通常情况下这部分应答中包含一个小的应答头，默认情况下这个值的大小为指令proxy_buffers中指定的一个缓冲区的大小，不过可以将其设置为更小"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_buffers","4K","设置用于读取应答（来自被代理服务器）的缓冲区数目和大小，默认情况也为分页大小，根据操作系统的不同可能是4k或者8k"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_busy_buffers_size","256K","设置用于读取应答（来自被代理服务器）的缓冲区数大小"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_temp_file_write_size","256k","设置在写入proxy_temp_path时数据的大小，预防一个工作进程在传递文件时阻塞太长"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_temp_path","/data0/proxy_temp_dir","proxy_temp_path和proxy_cache_path指定的路径必须在同一分区",false));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("proxy_cache_path","/data0/proxy_cache_dir levels=1:2 keys_zone=cache_one:200m inactive=1d max_size=30g","设置内存缓存空间大小为200MB，1天没有被访问的内容自动清除，硬盘缓存空间大小为30GB",false));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("client_header_buffer_size ","4k","客户端请求头部的缓冲区大小。这个可以根据你的系统分页大小来设置，一般一个请求的头部大小不会超过1k，不过由于一般系统分页都要大于1k，所以这里设置为分页大小。"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("client_max_body_size","300m","设定通过nginx上传文件的大小"));
       // httpModuleConfiguration.getCommlist().add( new CommonConfigurations("client_body_buffer_size","","此指令设置用于请求主体的缓冲区大小。 如果主体超过缓冲区大小，则完整主体或其一部分将写入临时文件。 如果NGINX配置为使用文件而不是内存缓冲区，则该指令会被忽略。 默认情况下，该指令为32位系统设置一个8k缓冲区，为64位系统设置一个16k缓冲区。 该指令在NGINX配置的http，server和location区块使用。"));

        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("large_client_header_buffers","8 128k","客户请求头缓冲大小。nginx默认会用client_header_buffer_size这个buffer来读取header值，如果header过大，它会使用large_client_header_buffers来读取"));
        httpModuleConfiguration.getCommlist().add( new CommonConfigurations("tcp_nopush","on","此选项允许或禁止使用socke的TCP_CORK的选项，此选项仅在使用sendfile的时候使用"));
       // httpModuleConfiguration.getCommlist().add( new CommonConfigurations("","",""));
        //httpModuleConfiguration.getCommlist().add( new CommonConfigurations("","",""));


        ModuleConfiguration upstreamConfiguration = new ModuleConfiguration("upstream abbportal");
        upstreamConfiguration.setEnable(false);
        upstreamConfiguration.getCommlist().add(new CommonConfigurations("192.168.10.172:8090",""));
        upstreamConfiguration.getCommlist().add(new CommonConfigurations("192.168.10.173:8090",""));
        upstreamConfiguration.getCommlist().add(new CommonConfigurations("192.168.10.174:8090",""));
        httpModuleConfiguration.getModuList().add(upstreamConfiguration);





        ModuleConfiguration serviceConfiguration = new ModuleConfiguration("http");
        serviceConfiguration.getCommlist().add(new CommonConfigurations("listen","80","监控的端口"));
        serviceConfiguration.getCommlist().add(new CommonConfigurations("server_name","da.deloitte.com","监听的域名"));

        ModuleConfiguration localConfiguration = new ModuleConfiguration("location /");
        localConfiguration.getCommlist().add(new CommonConfigurations("proxy_pass","http://10.173.48.51:9000/","代理的服务器设置可以是单机可以是集群"));
        localConfiguration.getCommlist().add(new CommonConfigurations("proxy_set_header","Host $host",""));
        localConfiguration.getCommlist().add(new CommonConfigurations("proxy_set_header","X-Real-IP $remote_addr",""));
        localConfiguration.getCommlist().add(new CommonConfigurations("proxy_set_header","REMOTE-HOST $remote_addr",""));
        localConfiguration.getCommlist().add(new CommonConfigurations("proxy_set_header","X-Forwarded-For $proxy_add_x_forwarded_for",""));
        localConfiguration.getCommlist().add(new CommonConfigurations("index","index.html index.htm",""));
        serviceConfiguration.getModuList().add(localConfiguration);

        httpModuleConfiguration.getModuList().add(serviceConfiguration);


        nginxModuList.add(httpModuleConfiguration);



        System.out.println(nginxConfiguration.getBodyFornmart());

    }

}
