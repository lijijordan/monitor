package com.monitor.server.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ConstantObject;
import com.monitor.server.comm.ErrorCodeMessEnum;
import com.monitor.server.entity.AllSensorAllPastInfo;
import com.monitor.server.entity.AllSensorCurInfo;
import com.monitor.server.entity.AllSensorPastInfo;
import com.monitor.server.entity.DataPointInfo;
import com.monitor.server.entity.DataPointsDevInfo;
import com.monitor.server.entity.DataPointsDevStatisticsInfo;
import com.monitor.server.entity.HomePageInfo;
import com.monitor.server.entity.SensorAllPastInfo;
import com.monitor.server.entity.SensorDetailPageInfo;
import com.monitor.server.service.SensorService;
import com.monitor.server.service.UserDevService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 水质传感器Controller
 *
 * @author yinhong
 *
 */
@Api(value = "WaterQuality", description = "WaterQuality Controller")
@Controller
@RequestMapping(value = "/waterquality")
public class WaterQualityController {

  @Autowired
  private SensorService sensorService;
  @Autowired
  private UserDevService userDevService;

  @ApiOperation(value = "获取首页所有数据（包括：传感器当前值、历史值（天、周、月）、健康度）", httpMethod = "GET",
      notes = "获取首页所有数据（包括：传感器当前值、历史值（天、周、月）、健康度")
  @RequestMapping(value = "/getHomePageVal/{userAccount}/{devSN}", method = RequestMethod.GET,
      consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<HomePageInfo> getHomePageVal(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN) {

    // 设置返回默认值
    ResponseVo<HomePageInfo> responseVo = new ResponseVo<HomePageInfo>();
    responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMessEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMessEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 获取相关显示数据
    ResponseVo<AllSensorCurInfo> curSensorInfo = getAllSensorCurVal(userAccount, devSN);
    ResponseVo<AllSensorAllPastInfo> allPastSensorInfo = getAllSensorAllPastVal(userAccount, devSN);
    // ResponseVo<HealthInfo> healthInfo = getEntiretySensorHealth(userID,
    // equID);

    if (ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
        .equalsIgnoreCase(curSensorInfo.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(allPastSensorInfo.getStatus())) {

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
      List<DataPointsDevStatisticsInfo> dtsDataPointsListByDay =
          allPastSensorInfo.getContent().getDayPastSensorInfo().getDtsValueList();
      List<DataPointInfo> averageDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxDataPointInfoListByDay = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : dtsDataPointsListByDay) {
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
      List<DataPointsDevStatisticsInfo> dtsDataPointsListByWeek =
          allPastSensorInfo.getContent().getWeekPastSensorInfo().getDtsValueList();
      List<DataPointInfo> averageDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : dtsDataPointsListByWeek) {
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
      List<DataPointsDevStatisticsInfo> dtsDataPointsListByMonth =
          allPastSensorInfo.getContent().getMonthPastSensorInfo().getDtsValueList();
      List<DataPointInfo> averageDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : dtsDataPointsListByMonth) {
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
      List<DataPointsDevStatisticsInfo> salinityDataPointsListByDay =
          allPastSensorInfo.getContent().getDayPastSensorInfo().getSalinityValueList();
      List<DataPointInfo> averageSalinityDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minSalinityDataSalinityPointInfoListByDay =
          new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxSalinityDataPointInfoListByDay = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : salinityDataPointsListByDay) {
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
      List<DataPointsDevStatisticsInfo> salinityDataPointsListByWeek =
          allPastSensorInfo.getContent().getWeekPastSensorInfo().getSalinityValueList();
      List<DataPointInfo> averageSalinityDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minSalinityDataSalinityPointInfoListByWeek =
          new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxSalinityDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : salinityDataPointsListByWeek) {
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
      List<DataPointsDevStatisticsInfo> salinityDataPointsListByMonth =
          allPastSensorInfo.getContent().getMonthPastSensorInfo().getSalinityValueList();
      List<DataPointInfo> averageSalinityDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minSalinityDataSalinityPointInfoListByMonth =
          new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxSalinityDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : salinityDataPointsListByMonth) {
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
      List<DataPointsDevStatisticsInfo> lightDataPointsListByDay =
          allPastSensorInfo.getContent().getDayPastSensorInfo().getLightValueList();
      List<DataPointInfo> averageLightDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minLightDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxLightDataPointInfoListByDay = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : lightDataPointsListByDay) {
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
      List<DataPointsDevStatisticsInfo> lightDataPointsListByWeek =
          allPastSensorInfo.getContent().getWeekPastSensorInfo().getLightValueList();
      List<DataPointInfo> averageLightDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minLightDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxLightDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : lightDataPointsListByWeek) {
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
      List<DataPointsDevStatisticsInfo> lightDataPointsListByMonth =
          allPastSensorInfo.getContent().getMonthPastSensorInfo().getLightValueList();
      List<DataPointInfo> averageLightDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minLightDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxLightDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : lightDataPointsListByMonth) {
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
      List<DataPointsDevStatisticsInfo> pHDataPointsListByDay =
          allPastSensorInfo.getContent().getDayPastSensorInfo().getPhValueList();
      List<DataPointInfo> averagePhDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minPhDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxPhDataPointInfoListByDay = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : pHDataPointsListByDay) {
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
      List<DataPointsDevStatisticsInfo> pHDataPointsListByWeek =
          allPastSensorInfo.getContent().getWeekPastSensorInfo().getPhValueList();
      List<DataPointInfo> averagePhDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minPhDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxPhDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : pHDataPointsListByWeek) {
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
      List<DataPointsDevStatisticsInfo> pHDataPointsListByMonth =
          allPastSensorInfo.getContent().getMonthPastSensorInfo().getPhValueList();
      List<DataPointInfo> averagePhDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minPhDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxPhDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : pHDataPointsListByMonth) {
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
      List<DataPointsDevStatisticsInfo> tempDataPointsListByDay =
          allPastSensorInfo.getContent().getDayPastSensorInfo().getTempValueList();
      List<DataPointInfo> averageTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByDay) {
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
      List<DataPointsDevStatisticsInfo> tempDataPointsListByWeek =
          allPastSensorInfo.getContent().getWeekPastSensorInfo().getTempValueList();
      List<DataPointInfo> averageTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByWeek) {
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
      List<DataPointsDevStatisticsInfo> tempDataPointsListByMonth =
          allPastSensorInfo.getContent().getWeekPastSensorInfo().getTempValueList();
      List<DataPointInfo> averageTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByMonth) {
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
      return responseVo;

    } else {

      if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(curSensorInfo.getStatus())) {
        responseVo.setStatus(curSensorInfo.getStatus());
        responseVo.setMessage(curSensorInfo.getMessage());
        return responseVo;
      } else {
        responseVo.setStatus(allPastSensorInfo.getStatus());
        responseVo.setMessage(allPastSensorInfo.getMessage());
        return responseVo;
      }
    }

  }

  @ApiOperation(value = "获取某类传感器详情页所有数据（包括：传感器当前值、历史值（天、周、月））", httpMethod = "GET",
      notes = "获取用户设备某类传感器详情页所有数据（包括：传感器当前值、历史值（天、周、月）")
  @RequestMapping(value = "/getSensorDetailPageVal/{userAccount}/{devSN}/{sensorType}",
      method = RequestMethod.GET, consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<SensorDetailPageInfo> getSensorDetailPageVal(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN,
      @ApiParam(required = true, name = "sensorType",
          value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType) {

    // 设置默认值
    ResponseVo<SensorDetailPageInfo> responseVo = new ResponseVo<SensorDetailPageInfo>();
    responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMessEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMessEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 获取相关显示数据
    ResponseVo<DataPointsDevInfo> curSensorInfo =
        getCurSensorValByType(userAccount, devSN, sensorType);
    ResponseVo<SensorAllPastInfo> allPastSensorVal =
        getAllPastSensorValByType(userAccount, devSN, sensorType);

    if (ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
        .equalsIgnoreCase(curSensorInfo.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(allPastSensorVal.getStatus())) {

      SensorDetailPageInfo sensorDetailPageInfo = new SensorDetailPageInfo();

      // 当前值
      sensorDetailPageInfo.setCurValue(curSensorInfo.getContent().getValue());

      // 历史值（天）
      List<DataPointsDevStatisticsInfo> tempDataPointsListByDay =
          allPastSensorVal.getContent().getDayPastSensorInfo();

      List<DataPointInfo> averageTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxTempDataPointInfoListByDay = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByDay) {
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
      List<DataPointsDevStatisticsInfo> tempDataPointsListByWeek =
          allPastSensorVal.getContent().getWeekPastSensorInfo();

      List<DataPointInfo> averageTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxTempDataPointInfoListByWeek = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByWeek) {
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
      List<DataPointsDevStatisticsInfo> tempDataPointsListByMonth =
          allPastSensorVal.getContent().getMonthPastSensorInfo();

      List<DataPointInfo> averageTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> minTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();
      List<DataPointInfo> maxTempDataPointInfoListByMonth = new ArrayList<DataPointInfo>();

      for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : tempDataPointsListByMonth) {
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

      responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
      responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
      responseVo.setContent(sensorDetailPageInfo);
      return responseVo;

    } else {

      if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(curSensorInfo.getStatus())) {
        responseVo.setStatus(curSensorInfo.getStatus());
        responseVo.setMessage(curSensorInfo.getMessage());
        return responseVo;
      } else {
        responseVo.setStatus(allPastSensorVal.getStatus());
        responseVo.setMessage(allPastSensorVal.getMessage());
        return responseVo;
      }
    }


  }

  @ApiOperation(value = "获取所有传感器当前值", httpMethod = "GET", notes = "获取用户某个设备所有传感器当前值")
  @RequestMapping(value = "/getAllSensorCurVal/{userAccount}/{devSN}", method = RequestMethod.GET,
      consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<AllSensorCurInfo> getAllSensorCurVal(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN) {

    // 设置返回默认值
    ResponseVo<AllSensorCurInfo> responseVo = new ResponseVo<AllSensorCurInfo>();
    responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMessEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMessEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 分别从设备获取各个传感器的值
    ResponseVo<DataPointsDevInfo> phCurrentValue =
        getCurSensorValByType(userAccount, devSN, ConstantObject.SENSOR_TYPE_PH);
    ResponseVo<DataPointsDevInfo> tempCurrentValue =
        getCurSensorValByType(userAccount, devSN, ConstantObject.SENSOR_TYPE_TEMPERATURE);
    ResponseVo<DataPointsDevInfo> salinityCurrentValue =
        getCurSensorValByType(userAccount, devSN, ConstantObject.SENSOR_TYPE_SALINITY);
    ResponseVo<DataPointsDevInfo> lightCurrentValue =
        getCurSensorValByType(userAccount, devSN, ConstantObject.SENSOR_TYPE_LIGHT);
    ResponseVo<DataPointsDevInfo> tdsCurrentValue =
        getCurSensorValByType(userAccount, devSN, ConstantObject.SENSOR_TYPE_TDS);

    if (ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
        .equalsIgnoreCase(phCurrentValue.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(tempCurrentValue.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(salinityCurrentValue.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(lightCurrentValue.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(tdsCurrentValue.getStatus())) {

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
      return responseVo;

    } else {

      if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(phCurrentValue.getStatus())) {
        responseVo.setStatus(phCurrentValue.getStatus());
        responseVo.setMessage(phCurrentValue.getMessage());
        return responseVo;
      } else if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(tempCurrentValue.getStatus())) {
        responseVo.setStatus(tempCurrentValue.getStatus());
        responseVo.setMessage(tempCurrentValue.getMessage());
        return responseVo;
      } else if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(salinityCurrentValue.getStatus())) {
        responseVo.setStatus(salinityCurrentValue.getStatus());
        responseVo.setMessage(salinityCurrentValue.getMessage());
        return responseVo;
      } else if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(lightCurrentValue.getStatus())) {
        responseVo.setStatus(lightCurrentValue.getStatus());
        responseVo.setMessage(lightCurrentValue.getMessage());
        return responseVo;
      } else {
        responseVo.setStatus(tdsCurrentValue.getStatus());
        responseVo.setMessage(tdsCurrentValue.getMessage());
        return responseVo;
      }
    }

  }

  @ApiOperation(value = "获取所有传感器所有时间段历史值(包括：平均值、最大值、最小值)", httpMethod = "GET",
      notes = "获取用户某个设备所有传感器所有时间段（包括：天、周、月）历史值(包括：平均值、最大值、最小值)")
  @RequestMapping(value = "/getAllSensorAllPastVal/{userAccount}/{devSN}",
      method = RequestMethod.GET, consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<AllSensorAllPastInfo> getAllSensorAllPastVal(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN) {

    // 设置默认返回值
    ResponseVo<AllSensorAllPastInfo> responseVo = new ResponseVo<AllSensorAllPastInfo>();
    responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMessEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMessEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 分别从设备获取所有传感器历史值（天、周、月）
    ResponseVo<AllSensorPastInfo> dayPastSensorInfo =
        getAllPastSensorValByPeriod(userAccount, devSN, ConstantObject.TIMEPERIOD_DAY);
    ResponseVo<AllSensorPastInfo> weekPastSensorInfo =
        getAllPastSensorValByPeriod(userAccount, devSN, ConstantObject.TIMEPERIOD_WEEK);
    ResponseVo<AllSensorPastInfo> monthPastSensorInfo =
        getAllPastSensorValByPeriod(userAccount, devSN, ConstantObject.TIMEPERIOD_MONTH);

    if (ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
        .equalsIgnoreCase(dayPastSensorInfo.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(weekPastSensorInfo.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(monthPastSensorInfo.getStatus())) {

      AllSensorAllPastInfo allPastSensorInfo = new AllSensorAllPastInfo();
      allPastSensorInfo.setDayPastSensorInfo(dayPastSensorInfo.getContent());
      allPastSensorInfo.setMonthPastSensorInfo(monthPastSensorInfo.getContent());
      allPastSensorInfo.setWeekPastSensorInfo(weekPastSensorInfo.getContent());

      responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
      responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
      responseVo.setContent(allPastSensorInfo);
      return responseVo;

    } else {

      if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(dayPastSensorInfo.getStatus())) {
        responseVo.setStatus(dayPastSensorInfo.getStatus());
        responseVo.setMessage(dayPastSensorInfo.getMessage());
        return responseVo;
      } else if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(weekPastSensorInfo.getStatus())) {
        responseVo.setStatus(weekPastSensorInfo.getStatus());
        responseVo.setMessage(weekPastSensorInfo.getMessage());
        return responseVo;
      } else {
        responseVo.setStatus(monthPastSensorInfo.getStatus());
        responseVo.setMessage(monthPastSensorInfo.getMessage());
        return responseVo;
      }
    }

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
  @RequestMapping(value = "/getCurSensorValByType/{userAccount}/{devSN}/{sensorType}",
      method = RequestMethod.GET, consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<DataPointsDevInfo> getCurSensorValByType(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN,
      @ApiParam(required = true, name = "sensorType",
          value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType) {

    // 设置返回默认值
    ResponseVo<DataPointsDevInfo> responseVo = new ResponseVo<DataPointsDevInfo>();
    responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMessEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMessEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 从设备获取传感器的值
    try {
      responseVo = sensorService.getCurSensorValByType(devSN, sensorType);
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
    }

    return responseVo;
  }

  @ApiOperation(value = "获取某类型传感器所有时间周期历史值(包括：平均值、最大值、最小值)", httpMethod = "GET",
      notes = "获取用户某个设备某类型传感器所有时间周期历史值(包括：平均值、最大值、最小值)")
  @RequestMapping(value = "/getAllPastSensorValByType/{userAccount}/{devSN}/{sensorType}",
      method = RequestMethod.GET, consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<SensorAllPastInfo> getAllPastSensorValByType(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN,
      @ApiParam(required = true, name = "sensorType",
          value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType) {

    // 设置返回默认值
    ResponseVo<SensorAllPastInfo> responseVo = new ResponseVo<SensorAllPastInfo>();
    responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMessEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMessEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 分别从设备获取所有传感器历史值（天、周、月）
    ResponseVo<List<DataPointsDevStatisticsInfo>> dayPastSensorInfo =
        getPastSensorValByTypePeriod(userAccount, devSN, sensorType, ConstantObject.TIMEPERIOD_DAY);
    ResponseVo<List<DataPointsDevStatisticsInfo>> weekPastSensorInfo = getPastSensorValByTypePeriod(
        userAccount, devSN, sensorType, ConstantObject.TIMEPERIOD_WEEK);
    ResponseVo<List<DataPointsDevStatisticsInfo>> monthPastSensorInfo =
        getPastSensorValByTypePeriod(userAccount, devSN, sensorType,
            ConstantObject.TIMEPERIOD_MONTH);

    if (ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
        .equalsIgnoreCase(dayPastSensorInfo.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(weekPastSensorInfo.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(monthPastSensorInfo.getStatus())) {

      SensorAllPastInfo sensorAllPastInfo = new SensorAllPastInfo();
      sensorAllPastInfo.setDayPastSensorInfo(dayPastSensorInfo.getContent());
      sensorAllPastInfo.setMonthPastSensorInfo(monthPastSensorInfo.getContent());
      sensorAllPastInfo.setWeekPastSensorInfo(weekPastSensorInfo.getContent());

      responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
      responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
      responseVo.setContent(sensorAllPastInfo);
      return responseVo;

    } else {
      if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(dayPastSensorInfo.getStatus())) {
        responseVo.setStatus(dayPastSensorInfo.getStatus());
        responseVo.setMessage(dayPastSensorInfo.getMessage());
        return responseVo;
      } else if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(weekPastSensorInfo.getStatus())) {
        responseVo.setStatus(weekPastSensorInfo.getStatus());
        responseVo.setMessage(weekPastSensorInfo.getMessage());
        return responseVo;
      } else {
        responseVo.setStatus(monthPastSensorInfo.getStatus());
        responseVo.setMessage(monthPastSensorInfo.getMessage());
        return responseVo;
      }
    }


  }

  @ApiOperation(value = "获取某类型传感器特定时间周期历史值(包括：平均值、最大值、最小值)", httpMethod = "GET",
      notes = "获取用户某个设备某类型传感器特定时间周期历史值(包括：平均值、最大值、最小值)")
  @RequestMapping(
      value = "/getPastSensorValByTypePeriod/{userAccount}/{devSN}/{sensorType}/{timePeriod}",
      method = RequestMethod.GET, consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<List<DataPointsDevStatisticsInfo>> getPastSensorValByTypePeriod(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN,
      @ApiParam(required = true, name = "sensorType",
          value = "传感器类型（PH,Temperature,Salinity,TDS,Light）") @PathVariable("sensorType") String sensorType,
      @ApiParam(required = true, name = "timePeriod",
          value = "时间周期（Day,Week,Month）") @PathVariable("timePeriod") String timePeriod) {

    // 从设备获取传感器的值
    ResponseVo<List<DataPointsDevStatisticsInfo>> responseVo =
        new ResponseVo<List<DataPointsDevStatisticsInfo>>();
    responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMessEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMessEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 从设备获取传感器的值
    try {
      responseVo = sensorService.getSensorValsByPeriod(devSN, sensorType, timePeriod);
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
    }

    return responseVo;
  }

  @ApiOperation(value = "获取所有传感器一段时间周期历史值(包括：平均值、最大值、最小值)", httpMethod = "GET",
      notes = "获取用户某个设备所有传感器一段时间周期历史值(包括：平均值、最大值、最小值)")
  @RequestMapping(value = "/getAllPastSensorValByPeriod/{userAccount}/{devSN}/{timePeriod}",
      method = RequestMethod.GET, consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<AllSensorPastInfo> getAllPastSensorValByPeriod(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN,
      @ApiParam(required = true, name = "timePeriod",
          value = "时间周期（Day,Week,Month）") @PathVariable("timePeriod") String timePeriod) {

    // 设置默认返回值
    ResponseVo<AllSensorPastInfo> responseVo = new ResponseVo<AllSensorPastInfo>();
    responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMessEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMessEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }


    // 分别从设备获取各个传感器某个周期的值
    ResponseVo<List<DataPointsDevStatisticsInfo>> phValue =
        getPastSensorValByTypePeriod(userAccount, devSN, ConstantObject.SENSOR_TYPE_PH, timePeriod);
    ResponseVo<List<DataPointsDevStatisticsInfo>> tempValue = getPastSensorValByTypePeriod(
        userAccount, devSN, ConstantObject.SENSOR_TYPE_TEMPERATURE, timePeriod);
    ResponseVo<List<DataPointsDevStatisticsInfo>> salinityValue = getPastSensorValByTypePeriod(
        userAccount, devSN, ConstantObject.SENSOR_TYPE_SALINITY, timePeriod);
    ResponseVo<List<DataPointsDevStatisticsInfo>> lightValue = getPastSensorValByTypePeriod(
        userAccount, devSN, ConstantObject.SENSOR_TYPE_LIGHT, timePeriod);
    ResponseVo<List<DataPointsDevStatisticsInfo>> dtsValue = getPastSensorValByTypePeriod(
        userAccount, devSN, ConstantObject.SENSOR_TYPE_TDS, timePeriod);

    if (ErrorCodeMessEnum.SUCCESS.getErrorCode().toString().equalsIgnoreCase(phValue.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(tempValue.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(salinityValue.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(lightValue.getStatus())
        && ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(dtsValue.getStatus())) {

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
      return responseVo;

    } else {
      if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(phValue.getStatus())) {
        responseVo.setStatus(phValue.getStatus());
        responseVo.setMessage(phValue.getMessage());
        return responseVo;
      } else if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(tempValue.getStatus())) {
        responseVo.setStatus(tempValue.getStatus());
        responseVo.setMessage(tempValue.getMessage());
        return responseVo;
      } else if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(salinityValue.getStatus())) {
        responseVo.setStatus(salinityValue.getStatus());
        responseVo.setMessage(salinityValue.getMessage());
        return responseVo;
      } else if (!ErrorCodeMessEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(lightValue.getStatus())) {
        responseVo.setStatus(lightValue.getStatus());
        responseVo.setMessage(lightValue.getMessage());
        return responseVo;
      } else {
        responseVo.setStatus(dtsValue.getStatus());
        responseVo.setMessage(dtsValue.getMessage());
        return responseVo;
      }
    }
  }

}
