package com.monitor.server.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.model.DataPointsInfo;
import com.monitor.common.model.DataPointsStatisticsInfo;
import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.AllPastSensorInfo;
import com.monitor.server.comm.ConstantObject;
import com.monitor.server.comm.CurSensorInfo;
import com.monitor.server.comm.HealthInfo;
import com.monitor.server.comm.HomePageInfo;
import com.monitor.server.comm.PastSensorInfo;
import com.monitor.server.web.service.SensorService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 传感器Controller
 *
 * @author yinhong
 *
 */
@Api(value = "Sensor", description = "Sensor Controller")
@Controller
@RequestMapping(value = "/sensor")
public class SensorController {

	@Autowired
	private SensorService sensorService;

	@ApiOperation(value = "获取首页所有数据（包括：传感器当前值、历史值（天、周、月）、健康度）", httpMethod = "GET", notes = "获取首页所有数据（包括：传感器当前值、历史值（天、周、月）、健康度")
	@RequestMapping(value = "/getHomePageVal/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public HomePageInfo getHomePageVal(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		// 获取相关显示数据
		ResponseVo<CurSensorInfo> curSensorInfo = getAllSensorCurVal(userID, equID);
		ResponseVo<AllPastSensorInfo> allPastSensorInfo = getAllSensorPastVal(userID, equID);
		ResponseVo<HealthInfo> healthInfo = getSensorEntiretyHealth(userID, equID);

		HomePageInfo homePageInfo = new HomePageInfo();

		// 获取当前传感器值
		homePageInfo.setSalinityCurValue(curSensorInfo.getContent().getSalinity());
		homePageInfo.setTdsCurValue(curSensorInfo.getContent().getTds());
		homePageInfo.setTempCurValue(curSensorInfo.getContent().getTemperature());
		homePageInfo.setLightCurValue(curSensorInfo.getContent().getLight());
		homePageInfo.setPhCurValue(curSensorInfo.getContent().getPh());

		// 获取当前健康度
		homePageInfo.setAverageHealth(healthInfo.getContent().getAverage());
		homePageInfo.setSalinityHealth(healthInfo.getContent().getSalinity());
		homePageInfo.setTdsHealth(healthInfo.getContent().getTds());
		homePageInfo.setPhHealth(healthInfo.getContent().getPh());
		homePageInfo.setLightHealth(healthInfo.getContent().getLight());
		homePageInfo.setTempHealth(healthInfo.getContent().getTemperature());

		// TDS历史值
		homePageInfo.setTdsAverageValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getDtsValue().getValue());
		homePageInfo
				.setTdsMaxValueByDay(allPastSensorInfo.getContent().getDayPastSensorInfo().getDtsValue().getMaxvalue());
		homePageInfo
				.setTdsMinValueByDay(allPastSensorInfo.getContent().getDayPastSensorInfo().getDtsValue().getMinvalue());

		homePageInfo.setTdsAverageValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getDtsValue().getValue());
		homePageInfo.setTdsMaxValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getDtsValue().getMaxvalue());
		homePageInfo.setTdsMinValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getDtsValue().getMinvalue());

		homePageInfo.setTdsAverageValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getDtsValue().getValue());
		homePageInfo.setTdsMaxValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getDtsValue().getMaxvalue());
		homePageInfo.setTdsMinValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getDtsValue().getMinvalue());

		// Salinity历史值
		homePageInfo.setSalinityAverageValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getSalinityValue().getValue());
		homePageInfo.setSalinityMaxValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getSalinityValue().getMaxvalue());
		homePageInfo.setSalinityMinValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getSalinityValue().getMinvalue());

		homePageInfo.setSalinityAverageValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getSalinityValue().getValue());
		homePageInfo.setSalinityMaxValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getSalinityValue().getMaxvalue());
		homePageInfo.setSalinityMinValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getSalinityValue().getMinvalue());

		homePageInfo.setSalinityAverageValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getSalinityValue().getValue());
		homePageInfo.setSalinityMaxValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getSalinityValue().getMaxvalue());
		homePageInfo.setSalinityMinValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getSalinityValue().getMinvalue());

		// 灯光历史值
		homePageInfo.setLightAverageValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getLightValue().getValue());
		homePageInfo.setLightMaxValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getLightValue().getMaxvalue());
		homePageInfo.setLightMinValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getLightValue().getMinvalue());

		homePageInfo.setLightAverageValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getLightValue().getValue());
		homePageInfo.setLightMaxValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getLightValue().getMaxvalue());
		homePageInfo.setLightMinValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getLightValue().getMinvalue());

		homePageInfo.setLightAverageValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getLightValue().getValue());
		homePageInfo.setLightMaxValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getLightValue().getMaxvalue());
		homePageInfo.setLightMinValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getLightValue().getMinvalue());

		// PH历史值
		homePageInfo
				.setPhAverageValueByDay(allPastSensorInfo.getContent().getDayPastSensorInfo().getPhValue().getValue());
		homePageInfo
				.setPhMaxValueByDay(allPastSensorInfo.getContent().getDayPastSensorInfo().getPhValue().getMaxvalue());
		homePageInfo
				.setPhMinValueByDay(allPastSensorInfo.getContent().getDayPastSensorInfo().getPhValue().getMinvalue());

		homePageInfo.setPhAverageValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getPhValue().getValue());
		homePageInfo
				.setPhMaxValueByWeek(allPastSensorInfo.getContent().getWeekPastSensorInfo().getPhValue().getMaxvalue());
		homePageInfo
				.setPhMinValueByWeek(allPastSensorInfo.getContent().getWeekPastSensorInfo().getPhValue().getMinvalue());

		homePageInfo.setPhAverageValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getPhValue().getValue());
		homePageInfo.setPhMaxValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getPhValue().getMaxvalue());
		homePageInfo.setPhMinValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getPhValue().getMinvalue());

		// 温度历史值
		homePageInfo.setTempAverageValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getTempValue().getValue());
		homePageInfo.setTempMaxValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getTempValue().getMaxvalue());
		homePageInfo.setTempMinValueByDay(
				allPastSensorInfo.getContent().getDayPastSensorInfo().getTempValue().getMinvalue());

		homePageInfo.setTempAverageValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getTempValue().getValue());
		homePageInfo.setTempMaxValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getTempValue().getMaxvalue());
		homePageInfo.setTempMinValueByWeek(
				allPastSensorInfo.getContent().getWeekPastSensorInfo().getTempValue().getMinvalue());

		homePageInfo.setTempAverageValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getTempValue().getValue());
		homePageInfo.setTempMaxValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getTempValue().getMaxvalue());
		homePageInfo.setTempMinValueByMonth(
				allPastSensorInfo.getContent().getMonthPastSensorInfo().getTempValue().getMinvalue());

		return homePageInfo;
	}

	@ApiOperation(value = "获取所有传感器当前值", httpMethod = "GET", notes = "获取用户某个设备所有传感器当前值")
	@RequestMapping(value = "/getAllSensorCurVal/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<CurSensorInfo> getAllSensorCurVal(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 分别从设备获取各个传感器的值
		ResponseVo<DataPointsActiveInfo> phCurrentValue = getSensorCurValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_PH);
		ResponseVo<DataPointsActiveInfo> tempCurrentValue = getSensorCurValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_TEMPERATURE);
		ResponseVo<DataPointsActiveInfo> salinityCurrentValue = getSensorCurValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_SALINITY);
		ResponseVo<DataPointsActiveInfo> lightCurrentValue = getSensorCurValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_LIGHT);
		ResponseVo<DataPointsActiveInfo> tdsCurrentValue = getSensorCurValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_TDS);

		// 将各个传感器的值都封装到一个对象
		CurSensorInfo curSensorInfo = new CurSensorInfo();
		curSensorInfo.setPh(phCurrentValue.getContent().getValue());
		curSensorInfo.setLight(lightCurrentValue.getContent().getValue());
		curSensorInfo.setTds(tdsCurrentValue.getContent().getValue());
		curSensorInfo.setSalinity(salinityCurrentValue.getContent().getValue());
		curSensorInfo.setTemperature(tempCurrentValue.getContent().getValue());

		ResponseVo<CurSensorInfo> responseVo = new ResponseVo<CurSensorInfo>();
		responseVo.setStatus(phCurrentValue.getStatus());
		responseVo.setMessage(phCurrentValue.getMessage());
		responseVo.setContent(curSensorInfo);

		return responseVo;
	}

	@ApiOperation(value = "获取所有传感器所有时间段历史值", httpMethod = "GET", notes = "获取用户某个设备所有传感器所有时间段（包括：天、周、月）历史值(包括：平均值、最大值、最小值)")
	@RequestMapping(value = "/getAllSensorPastVal/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<AllPastSensorInfo> getAllSensorPastVal(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 分别从设备获取所有传感器历史值（天、周、月）
		ResponseVo<PastSensorInfo> dayPastSensorInfo = getSensorValsByPeriod(userID, equID,
				ConstantObject.TIMEPERIOD_DAY);
		ResponseVo<PastSensorInfo> weekPastSensorInfo = getSensorValsByPeriod(userID, equID,
				ConstantObject.TIMEPERIOD_WEEK);
		ResponseVo<PastSensorInfo> monthPastSensorInfo = getSensorValsByPeriod(userID, equID,
				ConstantObject.TIMEPERIOD_MONTH);

		AllPastSensorInfo allPastSensorInfo = new AllPastSensorInfo();
		allPastSensorInfo.setDayPastSensorInfo(dayPastSensorInfo.getContent());
		allPastSensorInfo.setMonthPastSensorInfo(monthPastSensorInfo.getContent());
		allPastSensorInfo.setWeekPastSensorInfo(weekPastSensorInfo.getContent());

		ResponseVo<AllPastSensorInfo> responseVo = new ResponseVo<AllPastSensorInfo>();
		responseVo.setStatus(dayPastSensorInfo.getStatus());
		responseVo.setMessage(dayPastSensorInfo.getMessage());
		responseVo.setContent(allPastSensorInfo);

		return responseVo;
	}

	@ApiOperation(value = "获取所有传感器健康度以及综合健康度", httpMethod = "GET", notes = "获取用户某个设备所有传感器健康度以及综合健康度")
	@RequestMapping(value = "/getSensorEntiretyHealth/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<HealthInfo> getSensorEntiretyHealth(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		// 查询所有传感器整体综合健康度（待修改）
		HealthInfo healthInfo = new HealthInfo();
		healthInfo.setAverage("91");
		healthInfo.setPh("80");
		healthInfo.setLight("89");
		healthInfo.setSalinity("89");
		healthInfo.setTds("90");
		healthInfo.setTemperature("95");

		ResponseVo<HealthInfo> responseVo = new ResponseVo<HealthInfo>();
		responseVo.setStatus("0");
		responseVo.setMessage("OK");
		responseVo.setContent(healthInfo);

		return responseVo;
	}

	@ApiOperation(value = "获取某类型传感器当前值", httpMethod = "GET", notes = "获取用户某个设备某类型传感器当前值")
	@RequestMapping(value = "/getSensorCurValByType/{userID}/{equID}/{sensorType}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsActiveInfo> getSensorCurValByType(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 从设备获取传感器的值
		ResponseVo<DataPointsActiveInfo> responseVo = sensorService.getCurSensorValByType(equID, sensorType);

		return responseVo;
	}

	@ApiOperation(value = "获取某类型传感器一段时间周期历史值(包括：平均值、最大值、最小值)", httpMethod = "GET", notes = "获取用户某个设备某类型传感器一段时间周期历史值(包括：平均值、最大值、最小值)")
	@RequestMapping(value = "/getSensorValByPeriod/{userID}/{equID}/{sensorType}/{timePeriod}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsStatisticsInfo> getSensorValByPeriod(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType,
			@ApiParam(required = true, name = "timePeriod", value = "时间周期（Day,Week,Month）") @PathVariable("timePeriod") String timePeriod) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 从设备获取传感器的值
		ResponseVo<DataPointsStatisticsInfo> responseVo = sensorService.getSensorValsByPeriod(equID, sensorType,
				timePeriod);

		return responseVo;
	}

	@ApiOperation(value = "获取所有传感器一段时间周期历史值(包括：平均值、最大值、最小值)", httpMethod = "GET", notes = "获取用户某个设备所有传感器一段时间周期历史值(包括：平均值、最大值、最小值)")
	@RequestMapping(value = "/getSensorValsByPeriod/{userID}/{equID}/{timePeriod}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<PastSensorInfo> getSensorValsByPeriod(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "timePeriod", value = "时间周期（Day,Week,Month）") @PathVariable("timePeriod") String timePeriod) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 分别从设备获取各个传感器某个周期的值
		ResponseVo<DataPointsStatisticsInfo> phValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_PH, timePeriod);
		ResponseVo<DataPointsStatisticsInfo> tempValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_TEMPERATURE, timePeriod);
		ResponseVo<DataPointsStatisticsInfo> salinityValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_SALINITY, timePeriod);
		ResponseVo<DataPointsStatisticsInfo> lightValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_LIGHT, timePeriod);
		ResponseVo<DataPointsStatisticsInfo> dtsValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_TDS, timePeriod);

		PastSensorInfo pastSensorInfo = new PastSensorInfo();
		pastSensorInfo.setSalinityValue(salinityValue.getContent());
		pastSensorInfo.setLightValue(lightValue.getContent());
		pastSensorInfo.setPhValue(phValue.getContent());
		pastSensorInfo.setTempValue(tempValue.getContent());
		pastSensorInfo.setDtsValue(dtsValue.getContent());

		ResponseVo<PastSensorInfo> responseVo = new ResponseVo<PastSensorInfo>();
		responseVo.setStatus(phValue.getStatus());
		responseVo.setMessage(phValue.getMessage());
		responseVo.setContent(pastSensorInfo);

		return responseVo;
	}

}
