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

import com.monitor.common.define.DataTypeEnum;
import com.monitor.common.define.QueryScopeEnum;
import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ErrorCodeMsgEnum;
import com.monitor.server.entity.biz.AllSensorAllPastInfo;
import com.monitor.server.entity.biz.AllSensorCurInfo;
import com.monitor.server.entity.biz.AllSensorPastInfo;
import com.monitor.server.entity.biz.HomePageInfo;
import com.monitor.server.entity.dev.DataPointInfo;
import com.monitor.server.entity.dev.DataPointsDevInfo;
import com.monitor.server.entity.dev.DataPointsDevStatisticsInfo;
import com.monitor.server.service.SensorService;
import com.monitor.server.service.UserDevService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 甲醛传感器Controller
 *
 * @author yinhong
 *
 */
@Api(value = "Formaldehyde", description = "Formaldehyde Controller")
@Controller
@RequestMapping(value = "/formaldehyde")
public class FormaldehydeController {

  @Autowired
  private SensorService sensorService;
  @Autowired
  private UserDevService userDevService;

  @ApiOperation(value = "获取首页所有数据（包括：传感器当前值、历史值（天/请取平均值使用））", httpMethod = "GET",
      notes = "获取首页所有数据（包括：传感器当前值、历史值（天/请取平均值使用）")
  @RequestMapping(value = "/getHomePageVal/{userAccount}/{devSN}", method = RequestMethod.GET,
      consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResponseVo<HomePageInfo> getHomePageVal(
      @ApiParam(required = true, name = "userAccount",
          value = "用户账号") @PathVariable("userAccount") String userAccount,
      @ApiParam(required = true, name = "devSN",
          value = "设备SN") @PathVariable("devSN") String devSN) {

    // 设置默认值
    ResponseVo<HomePageInfo> responseVo = new ResponseVo<HomePageInfo>();
    responseVo.setStatus(ErrorCodeMsgEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMsgEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (!userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMsgEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMsgEnum.DevNotExisted.getErrorMessage());
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

    if (ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
        .equalsIgnoreCase(curSensorInfo.getStatus())
        && ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(allPastSensorInfo.getStatus())) {

      HomePageInfo homePageInfo = new HomePageInfo();

      // 获取当前传感器值
      homePageInfo.setHchoCurValue(curSensorInfo.getContent().getHcho());
      homePageInfo.setTvocCurValue(curSensorInfo.getContent().getTvoc());

      // PM2.5历史值（天）,只取平均值
      List<DataPointsDevStatisticsInfo> hchoDataPointsListByDay =
          allPastSensorInfo.getContent().getDayPastSensorInfo().getHchoValueList();
      List<DataPointInfo> averageHchoDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      if (hchoDataPointsListByDay != null) {
        for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : hchoDataPointsListByDay) {
          DataPointInfo averageDataPointInfo = new DataPointInfo();
          averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
          averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
          averageHchoDataPointInfoListByDay.add(averageDataPointInfo);
        }
        homePageInfo.setHchoAverageValueByDay(averageHchoDataPointInfoListByDay);
      }

      // PM10历史值（天）,只取平均值
      List<DataPointsDevStatisticsInfo> tvocDataPointsListByDay =
          allPastSensorInfo.getContent().getDayPastSensorInfo().getTvocValueList();
      List<DataPointInfo> averageTvocDataPointInfoListByDay = new ArrayList<DataPointInfo>();
      if (tvocDataPointsListByDay != null) {
        for (DataPointsDevStatisticsInfo dataPointsStatisticsInfo : tvocDataPointsListByDay) {
          DataPointInfo averageDataPointInfo = new DataPointInfo();
          averageDataPointInfo.setCollecttime(dataPointsStatisticsInfo.getCollecttime());
          averageDataPointInfo.setValue(dataPointsStatisticsInfo.getValue());
          averageTvocDataPointInfoListByDay.add(averageDataPointInfo);
        }
        homePageInfo.setTvocAverageValueByDay(averageTvocDataPointInfoListByDay);
      }

      // 设置最终返回数据
      responseVo.setStatus(ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString());
      responseVo.setMessage(ErrorCodeMsgEnum.SUCCESS.getErrorMessage());
      responseVo.setContent(homePageInfo);
      return responseVo;
    } else {

      if (!ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
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
    responseVo.setStatus(ErrorCodeMsgEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMsgEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (!userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMsgEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMsgEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 分别从设备获取各个传感器的值
    ResponseVo<DataPointsDevInfo> hchoCurrentValue =
        getCurSensorValByType(userAccount, devSN, DataTypeEnum.HCHO.getVal());
    ResponseVo<DataPointsDevInfo> tvocCurrentValue =
        getCurSensorValByType(userAccount, devSN, DataTypeEnum.TVOC.getVal());

    // 如果查询成功，则返回数据；如果查询失败，则返回失败信息
    if (ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
        .equalsIgnoreCase(hchoCurrentValue.getStatus())
        && ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(tvocCurrentValue.getStatus())) {

      // 将各个传感器的值都封装到一个对象返回
      AllSensorCurInfo curSensorInfo = new AllSensorCurInfo();
      curSensorInfo.setHcho(hchoCurrentValue.getContent().getValue());
      curSensorInfo.setTvoc(tvocCurrentValue.getContent().getValue());

      responseVo.setStatus(ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString());
      responseVo.setMessage(ErrorCodeMsgEnum.SUCCESS.getErrorMessage());
      responseVo.setContent(curSensorInfo);

      return responseVo;
    } else {
      if (!ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(hchoCurrentValue.getStatus())) {
        responseVo.setStatus(hchoCurrentValue.getStatus());
        responseVo.setMessage(hchoCurrentValue.getMessage());
        return responseVo;
      } else {
        responseVo.setStatus(tvocCurrentValue.getStatus());
        responseVo.setMessage(tvocCurrentValue.getMessage());
        return responseVo;
      }
    }

  }

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
          value = "传感器类型（HCHO,TVOC）") @PathVariable("sensorType") String sensorType) {

    // 设置返回默认值
    ResponseVo<DataPointsDevInfo> responseVo = new ResponseVo<DataPointsDevInfo>();
    responseVo.setStatus(ErrorCodeMsgEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMsgEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (!userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMsgEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMsgEnum.DevNotExisted.getErrorMessage());
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

  @ApiOperation(value = "获取所有传感器以天时间段的历史值", httpMethod = "GET", notes = "获取用户某个设备所有传感器天时间段历史值")
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
    responseVo.setStatus(ErrorCodeMsgEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMsgEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (!userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMsgEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMsgEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 分别从设备获取所有传感器以天单位历史值
    ResponseVo<AllSensorPastInfo> dayPastSensorInfo =
        getAllPastSensorValByPeriod(userAccount, devSN, QueryScopeEnum.Day.getVal());

    if (ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
        .equalsIgnoreCase(dayPastSensorInfo.getStatus())) {

      AllSensorAllPastInfo allPastSensorInfo = new AllSensorAllPastInfo();
      allPastSensorInfo.setDayPastSensorInfo(dayPastSensorInfo.getContent());

      responseVo.setStatus(ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString());
      responseVo.setMessage(ErrorCodeMsgEnum.SUCCESS.getErrorMessage());
      responseVo.setContent(allPastSensorInfo);
      return responseVo;

    } else {
      responseVo.setStatus(dayPastSensorInfo.getStatus());
      responseVo.setMessage(dayPastSensorInfo.getMessage());
      return responseVo;
    }

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

    // 设置默认值
    ResponseVo<AllSensorPastInfo> responseVo = new ResponseVo<AllSensorPastInfo>();
    responseVo.setStatus(ErrorCodeMsgEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMsgEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (!userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMsgEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMsgEnum.DevNotExisted.getErrorMessage());
        return responseVo;
      }
    } catch (BusinessException e) {
      responseVo.setStatus(e.getErrorCode().toString());
      responseVo.setMessage(e.getMessage());
      return responseVo;
    }

    // 分别从设备获取各个传感器某个周期的值
    ResponseVo<List<DataPointsDevStatisticsInfo>> hchoValue =
        getPastSensorValByTypePeriod(userAccount, devSN, DataTypeEnum.HCHO.getVal(), timePeriod);
    ResponseVo<List<DataPointsDevStatisticsInfo>> tvocValue =
        getPastSensorValByTypePeriod(userAccount, devSN, DataTypeEnum.TVOC.getVal(), timePeriod);

    if (ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString().equalsIgnoreCase(hchoValue.getStatus())
        && ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
            .equalsIgnoreCase(tvocValue.getStatus())) {
      // 遍历获取值
      AllSensorPastInfo pastSensorInfo = new AllSensorPastInfo();
      pastSensorInfo.setHchoValueList(hchoValue.getContent());
      pastSensorInfo.setTvocValueList(tvocValue.getContent());

      responseVo.setStatus(ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString());
      responseVo.setMessage(ErrorCodeMsgEnum.SUCCESS.getErrorMessage());
      responseVo.setContent(pastSensorInfo);
      return responseVo;

    } else {
      if (!ErrorCodeMsgEnum.SUCCESS.getErrorCode().toString()
          .equalsIgnoreCase(hchoValue.getStatus())) {
        responseVo.setStatus(hchoValue.getStatus());
        responseVo.setMessage(hchoValue.getMessage());
        return responseVo;
      } else {
        responseVo.setStatus(tvocValue.getStatus());
        responseVo.setMessage(tvocValue.getMessage());
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
          value = "传感器类型（HCHO,TVOC）") @PathVariable("sensorType") String sensorType,
      @ApiParam(required = true, name = "timePeriod",
          value = "时间周期（Day,Week,Month）") @PathVariable("timePeriod") String timePeriod) {


    // 设置返回默认值
    ResponseVo<List<DataPointsDevStatisticsInfo>> responseVo =
        new ResponseVo<List<DataPointsDevStatisticsInfo>>();
    responseVo.setStatus(ErrorCodeMsgEnum.FAILURE.getErrorCode().toString());
    responseVo.setMessage(ErrorCodeMsgEnum.FAILURE.getErrorMessage());

    // 根据用户ID检查输入设备ID是否正确,不正确直接返回设备不存在
    try {
      if (!userDevService.checkUserDevIsBinded(userAccount, devSN)) {
        responseVo.setStatus(ErrorCodeMsgEnum.DevNotExisted.getErrorCode().toString());
        responseVo.setMessage(ErrorCodeMsgEnum.DevNotExisted.getErrorMessage());
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

}
