package gb.dao;

import java.util.List;

import gb.vo.GbAndProductVO;
import gb.vo.GbVO;
import gb.vo.ProductVO;

public interface GBDao {
	void insert(GbVO gbVO);

	void update(GbVO gbVO);

	void delete(Integer gb_id);

	GbVO findByPrimaryKey(Integer gb_id);

	List<GbVO> getAll();
	
	// 新增此方法進行 Join 查詢
	List<GbAndProductVO> getGbAndProductJoin(); 
	
	void insertProductWithImages(ProductVO product);
	
	List<GbAndProductVO> selectByKeyWords(String how, String keywords);

}
