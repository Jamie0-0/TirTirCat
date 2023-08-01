package gb.service;

import java.util.Base64;
import java.util.List;

import gb.dao.GBDao;
import gb.dao.GbDAOImpl;
import gb.vo.GbAndProductVO;
import gb.vo.GbVO;
import gb.vo.ProductVO;



public class GbServiceImpl implements GBService {
    private GBDao dao;
    
    public GbServiceImpl() {
    	dao = new GbDAOImpl();
    }

	@Override
	public void addGb(GbVO gbVO) {
		dao.insert(gbVO);
	}

	@Override
	public void updateGb(GbVO gbVO) {
		dao.update(gbVO);
	}

	@Override
	public void deleteGb(Integer gb_id) {
		dao.delete(gb_id);
	}

	@Override
	public GbVO getGbByPrimaryKey(Integer gb_id) {
		return dao.findByPrimaryKey(gb_id);
		
	}

	@Override
	public List<GbVO> getAllGb() {
		return dao.getAll();
	}

	@Override
    public List<GbAndProductVO> getAllGbJoinProduct() {
        return dao.getGbAndProductJoin();
    }
	
	
//以下///////////////////////////////////////////////////////////////////////////////
	@Override
	public List<GbAndProductVO> getAllGbJoinProductWithBase64() {
	    List<GbAndProductVO> gbAndProductList = dao.getGbAndProductJoin();
	    for (GbAndProductVO gbAndProductVO : gbAndProductList) {
	        ProductVO productVO = gbAndProductVO.getProductVO();
	        if (productVO != null) {
	            // 轉換圖片資料為Base64字串
	            String base64PicOne = convertImageToBase64(productVO.getP_pic_one());
	            String base64PicTwo = convertImageToBase64(productVO.getP_pic_two());
	            String base64PicThree = convertImageToBase64(productVO.getP_pic_three());
	        }
	    }
	    return gbAndProductList;
	}

	private String convertImageToBase64(byte[] imageBytes) {
	    return Base64.getEncoder().encodeToString(imageBytes);
	}
//以上。/////////////////////////////////////////////////////////////////////////////////////
}
