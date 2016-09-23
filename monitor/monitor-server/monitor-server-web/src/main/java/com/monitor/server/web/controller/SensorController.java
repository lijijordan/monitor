package com.monitor.server.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.model.DataPointsStatisticsInfo;
import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.AllPastSensorInfo;
import com.monitor.server.comm.ConstantObject;
import com.monitor.server.comm.CurSensorInfo;
import com.monitor.server.comm.DataPointInfo;
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

		// TDS历史值（天）
		List<DataPointsStatisticsInfo> dtsDataPointsListByDay = allPastSensorInfo.getContent().getDayPastSensorInfo()
				.getDtsValueList();
		List<DataPointInfo> averageDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxDataPointInfoListByDay = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : dtsDataPointsListByDay) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageDataPointInfoListByDay.add(averageDataPointInfo);
			minDataPointInfoListByDay.add(minDataPointInfo);
			maxDataPointInfoListByDay.add(maxDataPointInfo);
		}

		homePageInfo.setTdsAverageValueByDay(averageDataPointInfoListByDay);
		homePageInfo.setTdsMaxValueByDay(maxDataPointInfoListByDay);
		homePageInfo.setTdsMinValueByDay(minDataPointInfoListByDay);

		// TDS历史值（周）
		List<DataPointsStatisticsInfo> dtsDataPointsListByWeek = allPastSensorInfo.getContent().getWeekPastSensorInfo()
				.getDtsValueList();
		List<DataPointInfo> averageDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : dtsDataPointsListByWeek) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageDataPointInfoListByWeek.add(averageDataPointInfo);
			minDataPointInfoListByWeek.add(minDataPointInfo);
			maxDataPointInfoListByWeek.add(maxDataPointInfo);
		}

		homePageInfo.setTdsAverageValueByWeek(averageDataPointInfoListByWeek);
		homePageInfo.setTdsMaxValueByWeek(maxDataPointInfoListByWeek);
		homePageInfo.setTdsMinValueByWeek(minDataPointInfoListByWeek);

		// TDS历史值（月）
		List<DataPointsStatisticsInfo> dtsDataPointsListByMonth = allPastSensorInfo.getContent()
				.getMonthPastSensorInfo().getDtsValueList();
		List<DataPointInfo> averageDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : dtsDataPointsListByMonth) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageDataPointInfoListByMonth.add(averageDataPointInfo);
			minDataPointInfoListByMonth.add(minDataPointInfo);
			maxDataPointInfoListByMonth.add(maxDataPointInfo);
		}

		homePageInfo.setTdsAverageValueByMonth(averageDataPointInfoListByMonth);
		homePageInfo.setTdsMaxValueByMonth(maxDataPointInfoListByMonth);
		homePageInfo.setTdsMinValueByMonth(minDataPointInfoListByMonth);

		// Salinity历史值（天）
		List<DataPointsStatisticsInfo> salinityDataPointsListByDay = allPastSensorInfo.getContent()
				.getDayPastSensorInfo().getSalinityValueList();
		List<DataPointInfo> averageSalinityDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minSalinityDataSalinityPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxSalinityDataPointInfoListByDay = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : salinityDataPointsListByDay) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageSalinityDataPointInfoListByDay.add(averageDataPointInfo);
			minSalinityDataSalinityPointInfoListByDay.add(minDataPointInfo);
			maxSalinityDataPointInfoListByDay.add(maxDataPointInfo);
		}

		homePageInfo.setSalinityAverageValueByDay(averageSalinityDataPointInfoListByDay);
		homePageInfo.setSalinityMaxValueByDay(maxSalinityDataPointInfoListByDay);
		homePageInfo.setSalinityMinValueByDay(minSalinityDataSalinityPointInfoListByDay);

		// Salinity历史值（周）
		List<DataPointsStatisticsInfo> salinityDataPointsListByWeek = allPastSensorInfo.getContent()
				.getWeekPastSensorInfo().getSalinityValueList();
		List<DataPointInfo> averageSalinityDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minSalinityDataSalinityPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxSalinityDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : salinityDataPointsListByWeek) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageSalinityDataPointInfoListByWeek.add(averageDataPointInfo);
			minSalinityDataSalinityPointInfoListByWeek.add(minDataPointInfo);
			maxSalinityDataPointInfoListByWeek.add(maxDataPointInfo);
		}

		homePageInfo.setSalinityAverageValueByWeek(averageSalinityDataPointInfoListByWeek);
		homePageInfo.setSalinityMaxValueByWeek(maxSalinityDataPointInfoListByWeek);
		homePageInfo.setSalinityMinValueByWeek(minSalinityDataSalinityPointInfoListByWeek);

		// Salinity历史值（月）
		List<DataPointsStatisticsInfo> salinityDataPointsListByMonth = allPastSensorInfo.getContent()
				.getMonthPastSensorInfo().getSalinityValueList();
		List<DataPointInfo> averageSalinityDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minSalinityDataSalinityPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxSalinityDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : salinityDataPointsListByMonth) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageSalinityDataPointInfoListByMonth.add(averageDataPointInfo);
			minSalinityDataSalinityPointInfoListByMonth.add(minDataPointInfo);
			maxSalinityDataPointInfoListByMonth.add(maxDataPointInfo);
		}

		homePageInfo.setSalinityAverageValueByMonth(averageSalinityDataPointInfoListByMonth);
		homePageInfo.setSalinityMaxValueByMonth(maxSalinityDataPointInfoListByMonth);
		homePageInfo.setSalinityMinValueByMonth(minSalinityDataSalinityPointInfoListByMonth);

		// 灯光历史值（天）
		List<DataPointsStatisticsInfo> lightDataPointsListByDay = allPastSensorInfo.getContent().getDayPastSensorInfo()
				.getLightValueList();
		List<DataPointInfo> averageLightDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minLightDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxLightDataPointInfoListByDay = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : lightDataPointsListByDay) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageLightDataPointInfoListByDay.add(averageDataPointInfo);
			minLightDataPointInfoListByDay.add(minDataPointInfo);
			maxLightDataPointInfoListByDay.add(maxDataPointInfo);
		}

		homePageInfo.setLightAverageValueByDay(averageLightDataPointInfoListByDay);
		homePageInfo.setLightMaxValueByDay(maxLightDataPointInfoListByDay);
		homePageInfo.setLightMinValueByDay(minLightDataPointInfoListByDay);

		// 灯光历史值（周）
		List<DataPointsStatisticsInfo> lightDataPointsListByWeek = allPastSensorInfo.getContent()
				.getWeekPastSensorInfo().getLightValueList();
		List<DataPointInfo> averageLightDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minLightDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxLightDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : lightDataPointsListByWeek) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageLightDataPointInfoListByWeek.add(averageDataPointInfo);
			minLightDataPointInfoListByWeek.add(minDataPointInfo);
			maxLightDataPointInfoListByWeek.add(maxDataPointInfo);
		}

		homePageInfo.setLightAverageValueByWeek(averageLightDataPointInfoListByWeek);
		homePageInfo.setLightMaxValueByWeek(maxLightDataPointInfoListByWeek);
		homePageInfo.setLightMinValueByWeek(minLightDataPointInfoListByWeek);

		// 灯光历史值（月）
		List<DataPointsStatisticsInfo> lightDataPointsListByMonth = allPastSensorInfo.getContent()
				.getMonthPastSensorInfo().getLightValueList();
		List<DataPointInfo> averageLightDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minLightDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxLightDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : lightDataPointsListByMonth) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageLightDataPointInfoListByMonth.add(averageDataPointInfo);
			minLightDataPointInfoListByMonth.add(minDataPointInfo);
			maxLightDataPointInfoListByMonth.add(maxDataPointInfo);
		}

		homePageInfo.setLightAverageValueByMonth(averageLightDataPointInfoListByMonth);
		homePageInfo.setLightMaxValueByMonth(maxLightDataPointInfoListByMonth);
		homePageInfo.setLightMinValueByMonth(minLightDataPointInfoListByMonth);

		// PH历史值（天）
		List<DataPointsStatisticsInfo> pHDataPointsListByDay = allPastSensorInfo.getContent().getDayPastSensorInfo()
				.getPhValueList();
		List<DataPointInfo> averagePhDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minPhDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxPhDataPointInfoListByDay = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : pHDataPointsListByDay) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averagePhDataPointInfoListByDay.add(averageDataPointInfo);
			minPhDataPointInfoListByDay.add(minDataPointInfo);
			maxPhDataPointInfoListByDay.add(maxDataPointInfo);
		}
		homePageInfo.setPhAverageValueByDay(averagePhDataPointInfoListByDay);
		homePageInfo.setPhMaxValueByDay(maxPhDataPointInfoListByDay);
		homePageInfo.setPhMinValueByDay(minPhDataPointInfoListByDay);

		// PH历史值（周）
		List<DataPointsStatisticsInfo> pHDataPointsListByWeek = allPastSensorInfo.getContent().getWeekPastSensorInfo()
				.getPhValueList();
		List<DataPointInfo> averagePhDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minPhDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxPhDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : pHDataPointsListByWeek) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averagePhDataPointInfoListByWeek.add(averageDataPointInfo);
			minPhDataPointInfoListByWeek.add(minDataPointInfo);
			maxPhDataPointInfoListByWeek.add(maxDataPointInfo);
		}

		homePageInfo.setPhAverageValueByWeek(averagePhDataPointInfoListByWeek);
		homePageInfo.setPhMaxValueByWeek(maxPhDataPointInfoListByWeek);
		homePageInfo.setPhMinValueByWeek(minPhDataPointInfoListByWeek);

		// PH历史值（月）
		List<DataPointsStatisticsInfo> pHDataPointsListByMonth = allPastSensorInfo.getContent().getMonthPastSensorInfo()
				.getPhValueList();
		List<DataPointInfo> averagePhDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minPhDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxPhDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : pHDataPointsListByMonth) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averagePhDataPointInfoListByMonth.add(averageDataPointInfo);
			minPhDataPointInfoListByMonth.add(minDataPointInfo);
			maxPhDataPointInfoListByMonth.add(maxDataPointInfo);
		}
		homePageInfo.setPhAverageValueByMonth(averagePhDataPointInfoListByMonth);
		homePageInfo.setPhMaxValueByMonth(maxPhDataPointInfoListByMonth);
		homePageInfo.setPhMinValueByMonth(minPhDataPointInfoListByMonth);

		// 温度历史值（天）
		List<DataPointsStatisticsInfo> tempDataPointsListByDay = allPastSensorInfo.getContent().getDayPastSensorInfo()
				.getTempValueList();
		List<DataPointInfo> averageTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByDay) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageTempDataPointInfoListByDay.add(averageDataPointInfo);
			minTempDataPointInfoListByDay.add(minDataPointInfo);
			maxTempDataPointInfoListByDay.add(maxDataPointInfo);
		}
		homePageInfo.setTempAverageValueByDay(averageTempDataPointInfoListByDay);
		homePageInfo.setTempMaxValueByDay(maxTempDataPointInfoListByDay);
		homePageInfo.setTempMinValueByDay(minTempDataPointInfoListByDay);

		// 温度历史值（周）
		List<DataPointsStatisticsInfo> tempDataPointsListByWeek = allPastSensorInfo.getContent().getWeekPastSensorInfo()
				.getTempValueList();
		List<DataPointInfo> averageTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByWeek) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageTempDataPointInfoListByWeek.add(averageDataPointInfo);
			minTempDataPointInfoListByWeek.add(minDataPointInfo);
			maxTempDataPointInfoListByWeek.add(maxDataPointInfo);
		}
		homePageInfo.setTempAverageValueByWeek(averageTempDataPointInfoListByWeek);
		homePageInfo.setTempMaxValueByWeek(maxTempDataPointInfoListByWeek);
		homePageInfo.setTempMinValueByWeek(minTempDataPointInfoListByWeek);

		// 温度历史值（月）
		List<DataPointsStatisticsInfo> tempDataPointsListByMonth = allPastSensorInfo.getContent()
				.getWeekPastSensorInfo().getTempValueList();
		List<DataPointInfo> averageTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> minTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
		List<DataPointInfo> maxTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

		for (DataPointsStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByMonth) {
			DataPointInfo averageDataPointInfo = new DataPointInfo();
			DataPointInfo minDataPointInfo = new DataPointInfo();
			DataPointInfo maxDataPointInfo = new DataPointInfo();

			averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			minDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
			maxDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());

			averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
			minDataPointInfo.setValue(dataPointsStatisticsInfo.getMinvalue());
			maxDataPointInfo.setValue(dataPointsStatisticsInfo.getMaxvalue());

			averageTempDataPointInfoListByMonth.add(averageDataPointInfo);
			minTempDataPointInfoListByMonth.add(minDataPointInfo);
			maxTempDataPointInfoListByMonth.add(maxDataPointInfo);
		}
		homePageInfo.setTempAverageValueByMonth(averageTempDataPointInfoListByMonth);
		homePageInfo.setTempMaxValueByMonth(maxTempDataPointInfoListByMonth);
		homePageInfo.setTempMinValueByMonth(minTempDataPointInfoListByMonth);

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
	public ResponseVo<List<DataPointsStatisticsInfo>> getSensorValByPeriod(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType,
			@ApiParam(required = true, name = "timePeriod", value = "时间周期（Day,Week,Month）") @PathVariable("timePeriod") String timePeriod) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 从设备获取传感器的值
		ResponseVo<List<DataPointsStatisticsInfo>> responseVo = sensorService.getSensorValsByPeriod(equID, sensorType,
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
		ResponseVo<List<DataPointsStatisticsInfo>> phValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_PH, timePeriod);
		ResponseVo<List<DataPointsStatisticsInfo>> tempValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_TEMPERATURE, timePeriod);
		ResponseVo<List<DataPointsStatisticsInfo>> salinityValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_SALINITY, timePeriod);
		ResponseVo<List<DataPointsStatisticsInfo>> lightValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_LIGHT, timePeriod);
		ResponseVo<List<DataPointsStatisticsInfo>> dtsValue = getSensorValByPeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_TDS, timePeriod);

		// 遍历获取值
		PastSensorInfo pastSensorInfo = new PastSensorInfo();
		pastSensorInfo.setSalinityValueList(salinityValue.getContent());
		pastSensorInfo.setLightValueList(lightValue.getContent());
		pastSensorInfo.setPhValueList(phValue.getContent());
		pastSensorInfo.setTempValueList(tempValue.getContent());
		pastSensorInfo.setDtsValueList(dtsValue.getContent());

		ResponseVo<PastSensorInfo> responseVo = new ResponseVo<PastSensorInfo>();
		responseVo.setStatus(phValue.getStatus());
		responseVo.setMessage(phValue.getMessage());
		responseVo.setContent(pastSensorInfo);

		return responseVo;
	}

}
