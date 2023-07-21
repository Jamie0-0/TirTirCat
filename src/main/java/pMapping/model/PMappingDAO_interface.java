package pMapping.model;

import java.util.List;


public interface PMappingDAO_interface {
    public void insert(PMappingVO pMappingVO);
    public PMappingVO findByPrimaryKey(Integer pm_id);
    public List<PMappingVO> getAll();
}
