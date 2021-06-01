import java.util.List;
import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZK {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 创建一个Zookeeper实例，第一个参数为目标服务器地址和端口，第二个参数为Session超时时间，第三个为节点变化时的回调方法
        ZooKeeper zk = new ZooKeeper("192.168.24.128:2181", 500000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                // dosomething
//                System.out.println("watcher event fire...");
            }
        });

        // 创建一个节点root，数据是mydata,不进行ACL权限控制，节点为永久性的(即客户端shutdown了也不会消失)
//        zk.create("/root", "mydata".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 在root下面创建一个childone znode,数据为childone,不进行ACL权限控制，节点为永久性的
//        zk.create("/root/childone", "childone".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 取得/root节点下的子节点名称,返回List<String>
        List<String> Items = zk.getChildren("/config/clients", true);
        for (String item : Items) {
            System.out.println(item);
        }
//        {"version":1,"config":{"producer_byte_rate":"1024","consumer_byte_rate":"1"}}
        zk.setData("/config/clients/test", "{\"version\":1,\"config\":{\"producer_byte_rate\":\"1\",\"consumer_byte_rate\":\"2\"}".getBytes(), -1);
        byte[] bytes = zk.getData("/config/clients/test", true, null);
        System.out.println(new String(bytes));


//        zk.delete("/root/childone", -1);
        zk.close();
    }
}