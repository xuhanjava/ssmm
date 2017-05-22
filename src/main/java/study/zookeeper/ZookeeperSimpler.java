package study.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xuhan on 17-5-10.
 */
public class ZookeeperSimpler implements Watcher {
    private static  final String MATCHINE_ADDRESS = "192.168.1.105:2181,192.168.1.103:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override public void process(WatchedEvent watchedEvent) {
        System.out.println("receive wathced event");
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws IOException {
        //构造方法客户端和服务器端会话创建的过程是一个异步的过程，方法在初始化之后立即返回，但大多数情况下，没有真正建立一个可用的会话，会话的生命周期
        //还是处于“CONNECING”状态。当会话真正创建的时候，服务器器端会向客户端发送一个事件通知，客户端接受后才算真正建立乐了会话。
        ZooKeeper zookeeper = new ZooKeeper(MATCHINE_ADDRESS,5000,new ZookeeperSimpler());
        System.out.println(zookeeper.getState());
        //下面这两个可以在下次创建zookeeper的时候再次使用，来达到复用的作用。
        zookeeper.getSessionId();
        zookeeper.getSessionPasswd();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        System.out.println("Zookeeper session established");
    }
}
