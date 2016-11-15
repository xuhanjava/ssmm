package com.google.ssmm.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * elastic-search 5.x测试
 * All operations are completely asynchronous inherited nature (either accepts a listener, or returns a future).
 * <p>
 * <p>
 * Created by xuhan on 16-11-24.
 */
public class EsClient {
    private static ThreadLocal<TransportClient> threadLocal = new ThreadLocal<>();

    static {
        Settings settings = Settings.builder()
            .put("cluster.name", "xuhan-test")
            .put("client.transport.sniff", true)
            .build();
        //Add transport addresses and do something with the client...
        //不加入集群，远程调用。自动的探查下面地址的机器  connect to the nodes in its internal node list

        /*The Transport client comes with a cluster sniffing feature which allows it to dynamically add new hosts
        and remove old ones.When sniffing is enabled, the transport client will connect to the nodes in its internal
        node list, which is built via calls to addTransportAddress.After this, the client will call the internal cluster
        state API on those nodes to discover available data nodes.The internal node list of the client will be replaced
        with those data nodes only.This list is refreshed every five seconds by
        default.Note that the IP addresses the sniffer connects to are the ones declared as the publish address in those
        node’s elasticsearch config.

        Keep in mind that the list might possibly not include the original node it connected to if that node is
        not a data node.If,for instance, you initially connect to a master node, after sniffing, no further
        requests will go to that master node, but rather to any data nodes instead.The reason the transport client
        excludes non - data nodes is to avoid sending search traffic to master only nodes.

        In order to enable sniffing, set client.transport.sniff to true.
        parameters:
        client.transport.ignore_cluster_name       Set to true to ignore cluster name validation of connected nodes. (since 0.19.4)

        client.transport.ping_timeout              The time to wait for a ping response from a node. Defaults to 5s.

        client.transport.nodes_sampler_interval    How often to sample / ping the nodes listed and connected. Defaults to 5s.
        */
        TransportClient client = null;

        //得把hosts文件给改了。 之后再 sudo /etc/init.d/networking restart
        //还有可能是stackoverflower 所以得把内存设置的大一点
        try {
            client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("xuhan"), 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadLocal.set(client);
    }

    public static void shutdown(){
        // on shutdown
        TransportClient esClient = threadLocal.get();
        if(esClient != null){
            esClient.close();
        }
    }

    public static TransportClient getInstance(){
        return threadLocal.get();
    }
}
