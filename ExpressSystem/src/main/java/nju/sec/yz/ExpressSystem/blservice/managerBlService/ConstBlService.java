package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import nju.sec.yz.ExpressSystem.vo.ConstVO;

/**
 * @author xiaosaisai
 * 常量制定--改和查
 */
public interface ConstBlService {
	public ConstVO observeConst();
	public ResultMessage modifyConst (ConstVO cv);
}
