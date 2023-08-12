package product_fe.dao;

import java.util.Map;

import product_fe.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class ProductJedisDaoImpl implements ProductJedisDao {
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	@Override
	public void setCartList(Map<String, String> cartListString, int uid) {

		Jedis jedis = pool.getResource();
		jedis.hmset("user:" + uid + ":cart.list", cartListString);
		jedis.close();
		
	}

	@Override
	public void deleteCartItem(Map<String, String> cartListString, int uid, String p_id) {

		Jedis jedis = pool.getResource();	
		jedis.hdel("user:" + uid + ":cart.list", p_id);
		jedis.close();
		
	}
}
