package com.monitor.server.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ConstantObject;
import com.monitor.server.comm.ErrorCodeMessEnum;
import com.monitor.server.entity.AllSensorAllPastInfo;
import com.monitor.server.entity.AllSensorCurInfo;
import com.monitor.server.entity.AllSensorPastInfo;
import com.monitor.server.entity.DataPointInfo;
import com.monitor.server.entity.HomePageInfo;
import com.monitor.server.entity.SensorAllPastInfo;
import com.monitor.server.entity.SensorDetailPageInfo;
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

	public static Logger sensorControllerLogger = Logger.getLogger(SensorController.class);

	@ApiOperation(value = "获取首页所有数据（包括：传感器当前值、历史值（天、周、月）、健康度）", httpMethod = "GET", notes = "获取首页所有数据（包括：传感器当前值、历史值（天、周、月）、健康度")
	@RequestMapping(value = "/getHomePageVal/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<HomePageInfo> getHomePageVal(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		ResponseVo<HomePageInfo> responseVo = new ResponseVo<HomePageInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		// 获取相关显示数据
		ResponseVo<AllSensorCurInfo> curSensorInfo = getAllSensorCurVal(userID, equID);
		ResponseVo<AllSensorAllPastInfo> allPastSensorInfo = getAllSensorAllPastVal(userID, equID);
		// ResponseVo<HealthInfo> healthInfo = getEntiretySensorHealth(userID,
		// equID);

		if (curSensorInfo.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& allPastSensorInfo.getStatus()
						.equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())) {

			HomePageInfo homePageInfo = new HomePageInfo();

			// 获取当前传感器值
			homePageInfo.setSalinityCurValue(curSensorInfo.getContent().getSalinity());
			homePageInfo.setTdsCurValue(curSensorInfo.getContent().getTds());
			homePageInfo.setTempCurValue(curSensorInfo.getContent().getTemperature());
			homePageInfo.setLightCurValue(curSensorInfo.getContent().getLight());
			homePageInfo.setPhCurValue(curSensorInfo.getContent().getPh());

			// 获取当前健康度
			// homePageInfo.setAverageHealth(healthInfo.getContent().getAverage());
			// homePageInfo.setSalinityHealth(healthInfo.getContent().getSalinity());
			// homePageInfo.setTdsHealth(healthInfo.getContent().getTds());
			// homePageInfo.setPhHealth(healthInfo.getContent().getPh());
			// homePageInfo.setLightHealth(healthInfo.getContent().getLight());
			// homePageInfo.setTempHealth(healthInfo.getContent().getTemperature());

			// TDS历史值（天）
			List<DataPointsStatisticsInfo> dtsDataPointsListByDay = allPastSensorInfo.getContent()
					.getDayPastSensorInfo().getDtsValueList();
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
			List<DataPointsStatisticsInfo> dtsDataPointsListByWeek = allPastSensorInfo.getContent()
					.getWeekPastSensorInfo().getDtsValueList();
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
			List<DataPointsStatisticsInfo> lightDataPointsListByDay = allPastSensorInfo.getContent()
					.getDayPastSensorInfo().getLightValueList();
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
			List<DataPointsStatisticsInfo> pHDataPointsListByWeek = allPastSensorInfo.getContent()
					.getWeekPastSensorInfo().getPhValueList();
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
			List<DataPointsStatisticsInfo> pHDataPointsListByMonth = allPastSensorInfo.getContent()
					.getMonthPastSensorInfo().getPhValueList();
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
			List<DataPointsStatisticsInfo> tempDataPointsListByDay = allPastSensorInfo.getContent()
					.getDayPastSensorInfo().getTempValueList();
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
			List<DataPointsStatisticsInfo> tempDataPointsListByWeek = allPastSensorInfo.getContent()
					.getWeekPastSensorInfo().getTempValueList();
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

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(homePageInfo);
		}

		return responseVo;
	}

	@ApiOperation(value = "获取某类传感器详情页所有数据（包括：传感器当前值、历史值（天、周、月））", httpMethod = "GET", notes = "获取用户设备某类传感器详情页所有数据（包括：传感器当前值、历史值（天、周、月）")
	@RequestMapping(value = "/getSensorDetailPageVal/{userID}/{equID}/{sensorType}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<SensorDetailPageInfo> getSensorDetailPageVal(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType) {

		ResponseVo<SensorDetailPageInfo> responseVo = new ResponseVo<SensorDetailPageInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 获取相关显示数据
		ResponseVo<DataPointsActiveInfo> curSensorInfo = getCurSensorValByType(userID, equID, sensorType);
		ResponseVo<SensorAllPastInfo> allPastSensorVal = getAllPastSensorValByType(userID, equID, sensorType);

		if (curSensorInfo.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& allPastSensorVal.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())) {

			SensorDetailPageInfo sensorDetailPageInfo = new SensorDetailPageInfo();

			// 当前值
			sensorDetailPageInfo.setCurValue(curSensorInfo.getContent().getValue());

			// 历史值（天）
			List<DataPointsStatisticsInfo> tempDataPointsListByDay = allPastSensorVal.getContent()
					.getDayPastSensorInfo();

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
			sensorDetailPageInfo.setAverageValueByDay(averageTempDataPointInfoListByDay);
			sensorDetailPageInfo.setMaxValueByDay(maxTempDataPointInfoListByDay);
			sensorDetailPageInfo.setMinValueByDay(minTempDataPointInfoListByDay);

			// 历史值（周）
			List<DataPointsStatisticsInfo> tempDataPointsListByWeek = allPastSensorVal.getContent()
					.getWeekPastSensorInfo();

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
			sensorDetailPageInfo.setAverageValueByWeek(averageTempDataPointInfoListByWeek);
			sensorDetailPageInfo.setMaxValueByWeek(maxTempDataPointInfoListByWeek);
			sensorDetailPageInfo.setMinValueByWeek(minTempDataPointInfoListByWeek);

			// 历史值（月）
			List<DataPointsStatisticsInfo> tempDataPointsListByMonth = allPastSensorVal.getContent()
					.getMonthPastSensorInfo();

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
			sensorDetailPageInfo.setAverageValueByMonth(averageTempDataPointInfoListByMonth);
			sensorDetailPageInfo.setMaxValueByMonth(minTempDataPointInfoListByMonth);
			sensorDetailPageInfo.setMinValueByMonth(maxTempDataPointInfoListByMonth);

			responseVo.setContent(sensorDetailPageInfo);
		}

		return responseVo;
	}

	@ApiOperation(value = "获取所有传感器当前值", httpMethod = "GET", notes = "获取用户某个设备所有传感器当前值")
	@RequestMapping(value = "/getAllSensorCurVal/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<AllSensorCurInfo> getAllSensorCurVal(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		ResponseVo<AllSensorCurInfo> responseVo = new ResponseVo<AllSensorCurInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 分别从设备获取各个传感器的值
		ResponseVo<DataPointsActiveInfo> phCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_PH);
		ResponseVo<DataPointsActiveInfo> tempCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_TEMPERATURE);
		ResponseVo<DataPointsActiveInfo> salinityCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_SALINITY);
		ResponseVo<DataPointsActiveInfo> lightCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_LIGHT);
		ResponseVo<DataPointsActiveInfo> tdsCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_TDS);

		if (phCurrentValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& tempCurrentValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& salinityCurrentValue.getStatus()
						.equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& lightCurrentValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& tdsCurrentValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())) {

			// 将各个传感器的值都封装到一个对象
			AllSensorCurInfo curSensorInfo = new AllSensorCurInfo();

			curSensorInfo.setPh(phCurrentValue.getContent().getValue());
			curSensorInfo.setLight(lightCurrentValue.getContent().getValue());
			curSensorInfo.setTds(tdsCurrentValue.getContent().getValue());
			curSensorInfo.setSalinity(salinityCurrentValue.getContent().getValue());
			curSensorInfo.setTemperature(tempCurrentValue.getContent().getValue());

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(curSensorInfo);
		}

		return responseVo;
	}

	@ApiOperation(value = "获取所有传感器所有时间段历史值(包括：平均值、最大值、最小值)", httpMethod = "GET", notes = "获取用户某个设备所有传感器所有时间段（包括：天、周、月）历史值(包括：平均值、最大值、最小值)")
	@RequestMapping(value = "/getAllSensorAllPastVal/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<AllSensorAllPastInfo> getAllSensorAllPastVal(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		ResponseVo<AllSensorAllPastInfo> responseVo = new ResponseVo<AllSensorAllPastInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 分别从设备获取所有传感器历史值（天、周、月）
		ResponseVo<AllSensorPastInfo> dayPastSensorInfo = getAllPastSensorValByPeriod(userID, equID,
				ConstantObject.TIMEPERIOD_DAY);
		ResponseVo<AllSensorPastInfo> weekPastSensorInfo = getAllPastSensorValByPeriod(userID, equID,
				ConstantObject.TIMEPERIOD_WEEK);
		ResponseVo<AllSensorPastInfo> monthPastSensorInfo = getAllPastSensorValByPeriod(userID, equID,
				ConstantObject.TIMEPERIOD_MONTH);

		if (dayPastSensorInfo.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& weekPastSensorInfo.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& monthPastSensorInfo.getStatus()
						.equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())) {

			AllSensorAllPastInfo allPastSensorInfo = new AllSensorAllPastInfo();
			allPastSensorInfo.setDayPastSensorInfo(dayPastSensorInfo.getContent());
			allPastSensorInfo.setMonthPastSensorInfo(monthPastSensorInfo.getContent());
			allPastSensorInfo.setWeekPastSensorInfo(weekPastSensorInfo.getContent());

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(allPastSensorInfo);

		}

		return responseVo;
	}

	// @ApiOperation(value = "获取所有传感器健康度以及综合健康度", httpMethod = "GET", notes =
	// "获取用户某个设备所有传感器健康度以及综合健康度")
	// @RequestMapping(value = "/getEntiretySensorHealth/{userID}/{equID}",
	// method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	// @ResponseBody
	// public ResponseVo<HealthInfo> getEntiretySensorHealth(
	// @ApiParam(required = true, name = "userID", value = "用户ID")
	// @PathVariable("userID") String userID,
	// @ApiParam(required = true, name = "equID", value = "设备ID")
	// @PathVariable("equID") String equID) {
	//
	// // 查询所有传感器整体综合健康度（待修改）
	// HealthInfo healthInfo = new HealthInfo();
	// healthInfo.setAverage("91");
	// healthInfo.setPh("80");
	// healthInfo.setLight("89");
	// healthInfo.setSalinity("89");
	// healthInfo.setTds("90");
	// healthInfo.setTemperature("95");
	//
	// ResponseVo<HealthInfo> responseVo = new ResponseVo<HealthInfo>();
	// responseVo.setStatus("0");
	// responseVo.setMessage("OK");
	// responseVo.setContent(healthInfo);
	//
	// return responseVo;
	// }

	@ApiOperation(value = "获取某类型传感器当前值", httpMethod = "GET", notes = "获取用户某个设备某类型传感器当前值")
	@RequestMapping(value = "/getCurSensorValByType/{userID}/{equID}/{sensorType}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsActiveInfo> getCurSensorValByType(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		ResponseVo<DataPointsActiveInfo> responseVo = null;

		// 从设备获取传感器的值
		try {

			responseVo = sensorService.getCurSensorValByType(equID, sensorType);

		} catch (BusinessException e) {
			sensorControllerLogger.error(e.getMessage(), e);

			responseVo = new ResponseVo<DataPointsActiveInfo>();
			responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

	@ApiOperation(value = "获取某类型传感器所有时间周期历史值(包括：平均值、最大值、最小值)", httpMethod = "GET", notes = "获取用户某个设备某类型传感器所有时间周期历史值(包括：平均值、最大值、最小值)")
	@RequestMapping(value = "/getAllPastSensorValByType/{userID}/{equID}/{sensorType}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<SensorAllPastInfo> getAllPastSensorValByType(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType) {

		ResponseVo<SensorAllPastInfo> responseVo = new ResponseVo<SensorAllPastInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 分别从设备获取所有传感器历史值（天、周、月）
		ResponseVo<List<DataPointsStatisticsInfo>> dayPastSensorInfo = getPastSensorValByTypePeriod(userID, equID,
				sensorType, ConstantObject.TIMEPERIOD_DAY);
		ResponseVo<List<DataPointsStatisticsInfo>> weekPastSensorInfo = getPastSensorValByTypePeriod(userID, equID,
				sensorType, ConstantObject.TIMEPERIOD_WEEK);
		ResponseVo<List<DataPointsStatisticsInfo>> monthPastSensorInfo = getPastSensorValByTypePeriod(userID, equID,
				sensorType, ConstantObject.TIMEPERIOD_MONTH);

		if (dayPastSensorInfo.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& weekPastSensorInfo.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& monthPastSensorInfo.getStatus()
						.equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())) {

			SensorAllPastInfo sensorAllPastInfo = new SensorAllPastInfo();
			sensorAllPastInfo.setDayPastSensorInfo(dayPastSensorInfo.getContent());
			sensorAllPastInfo.setMonthPastSensorInfo(monthPastSensorInfo.getContent());
			sensorAllPastInfo.setWeekPastSensorInfo(weekPastSensorInfo.getContent());

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(sensorAllPastInfo);

		}

		return responseVo;
	}

	@ApiOperation(value = "获取某类型传感器特定时间周期历史值(包括：平均值、最大值、最小值)", httpMethod = "GET", notes = "获取用户某个设备某类型传感器特定时间周期历史值(包括：平均值、最大值、最小值)")
	@RequestMapping(value = "/getPastSensorValByTypePeriod/{userID}/{equID}/{sensorType}/{timePeriod}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<List<DataPointsStatisticsInfo>> getPastSensorValByTypePeriod(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType,
			@ApiParam(required = true, name = "timePeriod", value = "时间周期（Day,Week,Month）") @PathVariable("timePeriod") String timePeriod) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 从设备获取传感器的值
		ResponseVo<List<DataPointsStatisticsInfo>> responseVo = null;

		// 从设备获取传感器的值
		try {

			responseVo = sensorService.getSensorValsByPeriod(equID, sensorType, timePeriod);

		} catch (BusinessException e) {
			sensorControllerLogger.error(e.getMessage(), e);

			responseVo = new ResponseVo<List<DataPointsStatisticsInfo>>();
			responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

	@ApiOperation(value = "获取所有传感器一段时间周期历史值(包括：平均值、最大值、最小值)", httpMethod = "GET", notes = "获取用户某个设备所有传感器一段时间周期历史值(包括：平均值、最大值、最小值)")
	@RequestMapping(value = "/getAllPastSensorValByPeriod/{userID}/{equID}/{timePeriod}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<AllSensorPastInfo> getAllPastSensorValByPeriod(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "timePeriod", value = "时间周期（Day,Week,Month）") @PathVariable("timePeriod") String timePeriod) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		ResponseVo<AllSensorPastInfo> responseVo = new ResponseVo<AllSensorPastInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		// 分别从设备获取各个传感器某个周期的值
		ResponseVo<List<DataPointsStatisticsInfo>> phValue = getPastSensorValByTypePeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_PH, timePeriod);
		ResponseVo<List<DataPointsStatisticsInfo>> tempValue = getPastSensorValByTypePeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_TEMPERATURE, timePeriod);
		ResponseVo<List<DataPointsStatisticsInfo>> salinityValue = getPastSensorValByTypePeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_SALINITY, timePeriod);
		ResponseVo<List<DataPointsStatisticsInfo>> lightValue = getPastSensorValByTypePeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_LIGHT, timePeriod);
		ResponseVo<List<DataPointsStatisticsInfo>> dtsValue = getPastSensorValByTypePeriod(userID, equID,
				ConstantObject.SENSOR_TYPE_TDS, timePeriod);

		if (phValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& tempValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& salinityValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& lightValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())
				&& dtsValue.getStatus().equalsIgnoreCase(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString())) {

			// 遍历获取值
			AllSensorPastInfo pastSensorInfo = new AllSensorPastInfo();
			pastSensorInfo.setSalinityValueList(salinityValue.getContent());
			pastSensorInfo.setLightValueList(lightValue.getContent());
			pastSensorInfo.setPhValueList(phValue.getContent());
			pastSensorInfo.setTempValueList(tempValue.getContent());
			pastSensorInfo.setDtsValueList(dtsValue.getContent());

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(pastSensorInfo);

		}

		return responseVo;
	}

}
