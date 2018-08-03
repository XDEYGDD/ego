package cn.gzsxt.rest.test;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestRedisCluster {
	public static void main(String[] args) {
		HostAndPort h1  = new HostAndPort("192.168.182.3", 7001);
		HostAndPort h2  = new HostAndPort("192.168.182.3", 7002);
		HostAndPort h3  = new HostAndPort("192.168.182.3", 7003);
		HostAndPort h4  = new HostAndPort("192.168.182.3", 7004);
		HostAndPort h5  = new HostAndPort("192.168.182.3", 7005);
		HostAndPort h6  = new HostAndPort("192.168.182.3", 7006);
		
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(h1);
		nodes.add(h2);
		nodes.add(h3);
		nodes.add(h4);
		nodes.add(h5);
		nodes.add(h6);
		
		JedisCluster jc = new JedisCluster(nodes);
		String name = jc.get("name");
		System.out.println(name);
	}
}
