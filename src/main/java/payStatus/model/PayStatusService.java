package payStatus.model;
import java.util.List;

public class PayStatusService {

	private PayStatusDAO_interface dao;

	public PayStatusService() {
		dao = new PayStatusDAO();
	}

	public List<PayStatusVO> getAll() {
		return dao.getAll();
	}
}
