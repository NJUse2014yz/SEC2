package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;

/**
 * 
 * @author xiaosaisai
 * AccountBlService对应的驱动
 */
public class AccountBlDriver {
	public  void drive(AccountBlService accountBlService){
		ResultMessage message1=accountBlService.addAccount(new AccountVO("八方物流", 10000));
		if(message1==ResultMessage.SUCCESS)
			System.out.println("添加账户成功");
		else 
			System.out.println("不好意思，操作失败哟");
		
		ResultMessage message2=accountBlService.deleteAccount("0000000001");
		if(message2==ResultMessage.SUCCESS)
			System.out.println("删除账户成功");
		else 
			System.out.println("不好意思，操作失败哟");
		
		ResultMessage message3=accountBlService.modifyAccount(new AccountVO("八方物流", 10000));
		if(message3==ResultMessage.SUCCESS)
			System.out.println("修改账户成功");
		else 
			System.out.println("不好意思，操作失败哟");
		
		AccountVO avo=accountBlService.observeAccount("0000000001");
		System.out.println(avo.getName()+" "+avo.getBalance());
		
		ArrayList<AccountVO> list=accountBlService.observeList();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getName()+" "+list.get(i).getBalance());
		}
	}
	public static void main(String[] args) {
		AccountBlService service=new AccountBlStub();
		AccountBlDriver driver=new AccountBlDriver();
		driver.drive(service);
	}
}
