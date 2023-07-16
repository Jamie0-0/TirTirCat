package product.model;

import java.util.List;

import product.model.ProductVO;

public interface ProductDAO_interface {
    public void insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public void delete(Integer p_id);
    public ProductVO findByPrimaryKey(Integer p_id);
    public List<ProductVO> getAll();
}