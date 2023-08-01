package master.model;

import java.util.List;

public interface MasterDao_interface {
    public void update(MasterVO masterVO);
    public MasterVO findByPrimaryKey(Integer m_id);

}
