package product_fe.service;

import java.util.LinkedList;
import java.util.List;

import product_fe.dao.ProductLikeDao;
import product_fe.dao.ProductLikeDaoImpl;
import product_fe.vo.ProductLike;

public class ProductLikeServiceImpl implements ProductLikeService {
	private ProductLikeDao dao;

	List<String> msgs;

	public ProductLikeServiceImpl() {
		dao = new ProductLikeDaoImpl();
		msgs = new LinkedList<String>();
	}

	@Override
	public boolean showProductLike(int pl_uid, int pl_p_id) {
		ProductLike productLike = dao.selectByUidAndPId(pl_uid, pl_p_id);
		System.out.println(productLike);

		if (productLike != null) {
			return true;

		} else {
			return false;
		}

	}

	@Override
	public boolean manageProductLike(int pl_uid, int pl_p_id) {
		msgs.clear();
		ProductLike productLike = dao.selectByUidAndPId(pl_uid, pl_p_id);
//		System.out.println(productLike);

		if (productLike == null) {
			dao.insert(pl_uid, pl_p_id);
			msgs.add("已追蹤商品");
			return false;

		} else {
			dao.delete(pl_uid, pl_p_id);
			msgs.add("已取消追蹤商品");
			return true;
		}

//		msgs.clear();
//		ProductLike productLike = dao.selectByUidAndPId(pl_uid, pl_p_id);
//		System.out.println(productLike);
//
//		if (productLike != null) {
////			System.out.println(productLike);
//
//			dao.delete(pl_uid, pl_p_id);
//			msgs.add("已取消追蹤商品");
////			return msgs;
//
//		} else if (productLike == null) {
////			System.out.println(productLike);
//
//			dao.insert(pl_uid, pl_p_id);
//			msgs.add("已追蹤商品");
////			return msgs;
//		}

//		return null;

	}

	@Override
	public List<String> getMsgs() {
		return msgs;
	}

}
