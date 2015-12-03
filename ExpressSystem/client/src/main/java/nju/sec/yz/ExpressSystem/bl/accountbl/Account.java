package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.po.AccountPO;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;

/**
 * Account的领域模型对象 负责账户管理的具体实现
 * 
 * @author 周聪
 */
public class Account implements Initialable<AccountVO, AccountPO> {

	private AccountDataService accountData;

	public Account() {
		try {
			accountData = DatafactoryProxy.getAccountDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	public ResultMessage addAccount(AccountVO av) {
		ResultMessage message = null;
		// 验证information
		String validresult = isValid(av);
		if (!validresult.equals("success")) {
			return new ResultMessage(Result.FAIL, validresult);
		}
		// 创建PO并保存
		AccountPO po = changeVOToPO(av);
		try {
			message = accountData.insert(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return message;
	}

	public ResultMessage deleteAccount(String name) {
		ResultMessage result = null;
		// 调用data层方法,验证id是否存在
		try {
			result = accountData.delete(name);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return result;
	}

	public ResultMessage modifyAccount(AccountVO av) {
		ResultMessage message = null;
		// 验证改过之后的vo
		String validresult = isValid(av);
		if (!validresult.equals("success"))
			return new ResultMessage(Result.FAIL, validresult);
		// vo转po,数据库更新po
		AccountPO po = changeVOToPO(av);
		try {
			message = accountData.update(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return message;
	}

	public AccountVO observeAccount(String name) {
		AccountVO vo = null;
		try {
			AccountPO po = accountData.find(name);
			if (po == null)
				return null;
			vo = show(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vo;
	}

	public ArrayList<AccountVO> observeList() {
		ArrayList<AccountPO> listPO = null;
		ArrayList<AccountVO> listVO = new ArrayList<AccountVO>();
		// 获取数据库中的userpo列表
		try {
			listPO = accountData.findAll();
			if (listPO == null)
				return null;
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 将userpo列表转换成uservo列表
		for (int i = 0; i < listPO.size(); i++) {
			AccountVO vo = show(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	/**
	 * 有付款单通过审批时，通过此方法更新账户
	 */
	public ResultMessage updatePayment(String account, double d) {
		AccountVO vo = this.observeAccount(account);

		vo.setBalance(vo.getBalance() - d);

		ResultMessage message = this.modifyAccount(vo);

		return message;
	}

	/**
	 * 有收款单通过审批时，通过此方法更新账户
	 */
	public ResultMessage updateCollection(String account, double num) {
		AccountVO vo = this.observeAccount(account);

		vo.setBalance(vo.getBalance() + num);

		ResultMessage message = this.modifyAccount(vo);

		return message;
	}

	@Override
	public AccountVO show(AccountPO po) {
		String name = po.getName();
		double balance = po.getBalance();
		AccountVO vo = new AccountVO(name, balance);
		return vo;
	}

	@Override
	public AccountPO changeVOToPO(AccountVO av) {
		String name = av.getName();
		double balance = av.getBalance();
		AccountPO po = new AccountPO(name, balance);
		return po;
	}

	private String isValid(AccountVO av) {
		// String name=av.getName();
		double balance = av.getBalance();
		if (balance < 0.0) {
			return "没钱的账户";
		}
		return "success";
	}

	@Override
	public ResultMessage init(List<AccountVO> vos) {
		ResultMessage message = new ResultMessage(Result.FAIL);

		List<AccountPO> pos = new ArrayList<>();
		for (AccountVO vo : vos) {
			String validResult = isValid(vo);
			if (!validResult.equals("success"))
				return new ResultMessage(Result.FAIL, vos.indexOf(vo) + " " + validResult);

			AccountPO po = this.changeVOToPO(vo);
			pos.add(po);
		}

		try {
			message = accountData.init(pos);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}

}
