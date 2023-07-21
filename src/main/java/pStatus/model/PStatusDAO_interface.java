package pStatus.model;

import java.util.List;

public interface PStatusDAO_interface {
    public void insert(PStatusVO pStatusVO);
    public PStatusVO findByPrimaryKey(Integer ps_id);
    public List<PStatusVO> getAll();
}
