package nju.sec.yz.ExpressSystem.blservice.accountBlService;

/**
 * @author xiaosaisai
 * 常量制定--改和查
 */
public interface ConstBlService {
	public ConstVO observeConst();
	public ResultMessage modifyConst (ConstVO cv);
}
