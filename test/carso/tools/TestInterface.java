package carso.tools;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.alibaba.fastjson.JSONObject;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestInterface {

	//以下为接口参数
	private String searchType = "0";
	private Integer shopperId = 19;
	private Integer shopperPid = 18;
	private String jobNum = "winbox_test";
	private Integer employeeId = 32054;
	private String sign = "9af3f202b93a7fcec8e94b7523b28a56";
	//0表示数量 1表示金额 
	private String productType = "0";
	//0表示升序  asc  1 表示降序  desc
	private Integer sortType = 1;
	private Integer pageNo = 1;
	private Integer pageSize = 5;
	
	//以下为测试参数（无需修改）
	private String localTestingUrl = "";
	private String remoteTestingUrl = "";
	private String interfaceUrl = "";	
	private String startDate = "";
	private String endDate = "";
	private String startDateOld = "";
	private String endDateOld = "";
	private String local_result = "";
	private String remote_result = "";
	
	private static int count = 0;
	private static Map<String, String> resultMap = new HashMap<String, String>();

	@BeforeClass
	public static void setUpAll(){
		count = 1;	
	}
	
	@AfterClass
	public static void printAll(){
		for (Map.Entry<String, String> entry : resultMap.entrySet()) {  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		} 	
	}
	
	@Before
	public void setUp() throws Exception {
		Properties pps = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream("resource/config.properties"));
		pps.load(in);
		if(count >= 3){
			localTestingUrl = pps.getProperty("local_ApiServiceUrl");
			remoteTestingUrl = pps.getProperty("remote_ApiServiceUrl");	
		}
		else{
			localTestingUrl = pps.getProperty("local_MemberUrl");
			remoteTestingUrl = pps.getProperty("remote_MemberUrl");	
		}
		
		count++;
	}

	/**
	 * 员工报表统计数据(今-周-月)
	 * 
	 */
	//@Test
	public void test1Getcashes(){
		interfaceUrl = "/cashes/getcashes.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 员工首页营业数据+打赏数据请求
	 * 
	 */
	//@Test
	public void test2GetcashesAndAward(){
		interfaceUrl = "/cashes/getcashesAndAward.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 员工个人销售统计数据(单品统计)
	 * 
	 */
	//@Test
	public void test3GetGoodsAndCategorySale(){
		interfaceUrl = "/cashes/getGoodsAndCategorySale.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 会员统计
	 * 
	 */
	//@Test
	public void test4GetMember(){
		interfaceUrl = "/appshoperinterface/getMember.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 商家主页营业数据
	 * 
	 */
	//@Test
	public void test5GetStatistics(){
		interfaceUrl = "/appshoperinterface/getStatistics.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 获取报表统计数据
	 * 
	 */
	//@Test
	public void test6GetMemberReportStatic(){
		interfaceUrl = "/appshoperinterface/getMemberReportStatic.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 收银报表数据(同比)
	 * 
	 */
	//@Test
	public void test7GetMemberReportByPayModel(){
		interfaceUrl = "/appshoperinterface/getMemberReportByPayModel.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 商家单品(商品)统计
	 * 
	 */
	@Test
	public void test8GetMemberSalesReport(){
		interfaceUrl = "/appshoperinterface/getMemberSalesReport.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 商家单品(商品)统计
	 * 
	 */
	//@Test
	public void test9GetMemberSalesReportCatory(){
		interfaceUrl = "/appshoperinterface/getMemberSalesReportCatory.htm";
		testInterface(interfaceUrl);
	}
	
	/**
	 * 根据日期类型传入参数
	 * 
	 * @param dateType
	 * @return
	 */
	private Map<String, String> getParam(Integer dateType){
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("searchType", searchType);
		map.put("shopperId", shopperId == null ? "" : shopperId.toString());
		map.put("shopperPid", shopperPid == null ? "" : shopperPid.toString());
		map.put("employeeId", employeeId == null ? "" : employeeId.toString());
		map.put("jobNum", jobNum);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("startDateOld", startDateOld);
		map.put("endDateOld", endDateOld);
		map.put("sign", sign);
		map.put("dateType", dateType.toString());
		map.put("type", dateType.toString());
		map.put("productType", productType);
		map.put("sortType", sortType == null ? "" : sortType.toString());
		map.put("pageNo", pageNo == null ? "" : pageNo.toString());
		map.put("pageSize", pageSize == null ? "" : pageSize.toString());		
		
		return map;
	}
	
	/**
	 * 查看结果
	 * 
	 * @param dateType
	 * @param interfaceNmae
	 * @return
	 */
	private JSONObject createResultJson(Integer dateType,String interfaceNmae){	
		JSONObject json = new JSONObject();
		
		json.put("interfaceName", interfaceNmae);
		json.put("dateType", dateType);
		json.put("startDate", startDate);
		json.put("endDate", endDate);
		json.put("startDateOld", startDateOld);
		json.put("endDateOld", endDateOld);
		json.put("local_result", local_result);
		json.put("remote_result", remote_result);
		
		return json;
	}
	
	/**
	 * 新旧接口结果对比
	 * 
	 * @param interfaceUrl
	 */
	private void testInterface(String interfaceUrl){
		
		Map<String, String> map = new HashMap<String, String>();
		
		for(Integer i=2; i<=2; i++){
			getDate(i);
			map = getParam(i);
			
			local_result = HttpUtil.buildRequest(localTestingUrl + interfaceUrl, map,
					36000, 36000, "utf-8");
			remote_result = HttpUtil.buildRequest(remoteTestingUrl + interfaceUrl, map,
					36000, 36000, "utf-8");
			
			JSONObject json = createResultJson(i,interfaceUrl);
			resultMap.put(interfaceUrl +"?dateType=" + i, json.toJSONString());
			
			assertEquals("出错日期类型:" + i + json.toJSONString(),local_result,remote_result);
		}	
	}
	
	/**
	 * 日期计算
	 * 
	 * @param type
	 */
	private void getDate(int type){
	    endDate= DateUtil.getDay();
        if("0".equals(String.valueOf(type))||type>2) {       //  0:今日
        	startDate=DateUtil.getDayFirst();
        	startDateOld=DateUtil.getDayFirst(-1);
		}else if("1".equals(String.valueOf(type))) {//  1:本周  
			startDate=DateUtil.getWeekFirst();
			startDateOld=DateUtil.getWeekFirst(-7);
		}else if("2".equals(String.valueOf(type))) { //  2:本月
			startDate=DateUtil.getMonthFirst();
			startDateOld=DateUtil.getMonthFirst(-1);
		}else if("-1".equals(String.valueOf(type))) { //  -1:昨天（同比前天）
			startDate=DateUtil.getDayFirst(-1);
			startDateOld=DateUtil.getDayFirst(-2);
			endDate=DateUtil.getDayFirst();
		}
        endDateOld= startDate;	
	}

}
