package articles.dao;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import webSocket.jedis.JedisPoolUtil;

public class ArticlesJedisDaoImpl implements ArticlesJedisDao{
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static List<String> getArticlesByTag() {
		String key = "";
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}
	
	public static void setArticlesTag(String articles) {
		String tag= "";
		String tagValue = "";
		Jedis jedis = pool.getResource();
		jedis.rpush(tag, tagValue);

		jedis.close();
	}
}
