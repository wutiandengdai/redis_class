package chap01.hello;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * React 测试
 * 1、启动react服务器
 * 2、run as Java Application
 * @author ucs_yelei
 * @date 2017-4-5
 */
public class RedisJedis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RedisJedis rj = new RedisJedis();
		rj.ping();
		rj.hello();
		rj.llist();
		rj.keys();
	}
	
	public void ping(){
		try{
			Jedis jedis = new Jedis("localhost");
			System.out.println("Connected to server!");
			
			System.out.println(jedis.ping());
			jedis.close();
		}catch(Exception e){
			System.out.println("Connection fail!");
		}
	}
	
	public void hello(){
		Jedis jedis = new Jedis("localhost");
		jedis.set("hello", "world");
		System.out.println(jedis.get("hello"));
		jedis.close();
	}
	
	public void llist(){
		String jbl = "java-book-list";
		Jedis jedis = new Jedis("localhost");
		jedis.lpush(jbl, "Thinking in Java");
		jedis.lpush(jbl, "Head First Java");
		jedis.lpush(jbl, "Core Java");
		
		List<String> list = jedis.lrange(jbl, 0, 5);
		for(String s:list){
			System.out.println(s);
		}
		jedis.close();
	}
	
	public void keys(){
		Jedis jedis = new Jedis("localhost");
		
		Set<String> set = jedis.keys("*");
		for(String s:set){
			System.out.println(s);
		}
		jedis.close();
	}
}
