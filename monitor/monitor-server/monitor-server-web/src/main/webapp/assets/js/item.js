/**
 * Custom JS
 */
// Counto To
// $(function () {
//     if ($(".count-to").length) {
//         $(".count-to").countTo({
//             refreshInterval: 20
//         });
//     }
// });

// Define colors
var brandAccentColor = "rgba(164, 113, 198, 1)";
var brandPinkColor = "rgba(240, 98, 146, 1)";
var brandOrangeColor = "rgba(255, 138, 101, 1)";
var brandTealColor = "rgba(77, 182, 172, 1)";

var type = getUrlParam('type');

$(document).ready(function () {
    
    $('#type').html(type);
    $('#type_day').html(type);
    $('#type_week').html(type);
    $('#type_month').html(type);
    $.ajax({
        url: "/fishapi/sensor/getSensorDetailPageVal/fisher01/fisher01/" + type,
        xhrFields: {
            withCredentials: true
        }
    }).done(function (data) {
        console.log(data);
        // 当前值
        $('.count-to').countTo({from: 0, to: data.content.curValue});
        renderLine(data.content.averageValueByDay, ".chart_day", "hh:mm:ss");
        renderLine(data.content.averageValueByWeek, ".chart_week", "d");
        renderLine(data.content.averageValueByMonth, ".chart_month", "d");

    });

});

// Sidebar

function renderLine(averageValueByDay, div, format) {
    var labels = [], data = [];
    for (var i = 0; i < averageValueByDay.length; i++) {
        var o = averageValueByDay[i];
        // console.log(new Date(o.collecttime));
        labels.push(new Date(o.collecttime).Format(format));
        data.push(o.value);
    }

    function chartColor(color, opacity) {
        var color = color.slice(0, -2) + opacity + ")";
        return color;
    }

    // Charts: Visitors
    if ($(div).length) {

        Chart.defaults.global.responsive = true;
        Chart.defaults.global.legend.display = false;

        var ctx = $(div).get(0).getContext("2d");
        var chartVisitors = new Chart(ctx, {
            type: "line",
            data: {
                labels: labels,
                datasets: [
                    {
                        label: "value",
                        fill: true,
                        borderWidth: 2,
                        backgroundColor: chartColor(brandAccentColor, .2),
                        borderColor: chartColor(brandAccentColor, .5),
                        pointBorderColor: "#fff",
                        pointBackgroundColor: chartColor(brandAccentColor, .5),
                        pointBorderWidth: 2,
                        pointHoverBackgroundColor: chartColor(brandAccentColor, 5),
                        pointHoverBorderColor: "#fff",
                        pointHoverBorderWidth: 2,
                        pointRadius: 3,
                        data: data
                    }
                ]
            }
        });
    }
};


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

