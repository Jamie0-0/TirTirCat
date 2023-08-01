package pType.model;

import java.util.List;
import pType.model.PTypeVO;

public interface PTypeDAO_interface {
    public void insert(PTypeVO pTypeVO);
    public PTypeVO findByPrimaryKey(Integer pt_id);
    public List<PTypeVO> getAll();
}
