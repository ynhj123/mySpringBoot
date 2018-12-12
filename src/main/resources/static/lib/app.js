/**
 * Created by wwb on 2017/6/28.
 */
(function (W, $, J) {
    if (typeof W.app != 'undefined') {
        return;
    }

    function app() {
    }

    app.prototype.origin = W.location.origin;

    /**
     * 字符串是否为空
     * @param str
     */
    app.prototype.isEmpty = function (str) {
        return typeof(str) == 'undefined' || str == null || "" === $.trim(str);
    };
    /**
     * ajax请求
     * @param url
     * @param params
     * @param success
     * @param error
     */
    app.prototype.ajaxRequest = function (url, params, success, error) {
        var that = this;
        that.ajaxRequest(url, params, success, error, false);
    };
    /**
     * ajax请求并显示加载
     * @param url
     * @param params
     * @param success
     * @param error
     */
    app.prototype.ajaxRequestWithLoading = function (url, params, success, error) {
        var that = this;
        that.ajaxRequest(url, params, success, error, true);
    };
    app.prototype.ajaxRequest = function (url, params, success, error, loading) {
        var that = this;
        if (loading) {
            that.loading();
        }
        $.ajax({
            url: url,
            type: "post",
            data: params,
            dataType: "text",
            beforeSend: function (XMLHttpRequest) {
                XMLHttpRequest.setRequestHeader("X-Requested-Custom-Type", "ajax");
            },
            success: function (result) {
                if (loading) {
                    that.closeAllLayerPop();
                }
                result = that.getJsonObject(result);
                success(result);
            },
            error: function () {
                if (loading) {
                    that.closeAllLayerPop();
                }
                error();
            }
        });
    };

    app.prototype.ajaxGetRequest = function (url, params, success, error, loading) {
        var that = this;
        if (loading) {
            that.loading();
        }
        $.ajax({
            url: url,
            type: "get",
            data: params,
            dataType: "text",
            beforeSend: function (XMLHttpRequest) {
                XMLHttpRequest.setRequestHeader("X-Requested-Custom-Type", "ajax");
            },
            success: function (result) {
                if (loading) {
                    that.closeAllLayerPop();
                }
                result = that.getJsonObject(result);
                success(result);
            },
            error: function () {
                if (loading) {
                    that.closeAllLayerPop();
                }
                error();
            }
        });
    };

    /**
     * ajax请求统一响应显示
     * @param url
     * @param params
     * @param success
     * @param error
     * @param msg
     */
    app.prototype.ajaxRequestUnifiedShow = function (url, params, success, error, msg) {
        var that = this;
        that.ajaxRequestUnified(url, params, success, error, msg, false);
    };
    /**
     * 带loading和统一响应显示的ajax请求
     * @param url
     * @param params
     * @param success
     * @param error
     * @param msg
     */
    app.prototype.ajaxRequestUnifiedShowWithLoading = function (url, params, success, error, msg) {
        var that = this;
        that.ajaxRequestUnified(url, params, success, error, msg, true);
    };
    app.prototype.ajaxRequestUnifiedShowWithLoadingNoAsync = function (url, params, success, error, msg) {
        var that = this;
            var that = this;
            if (true) {
                that.loading();
            }
            $.ajax({
                url: url,
                type: "post",
                data: params,
                async:false,
                dataType: "json",
                beforeSend: function (XMLHttpRequest) {
                    XMLHttpRequest.setRequestHeader("X-Requested-Custom-Type", "ajax");
                },
                success: function (result) {
                    if (true) {
                        that.closeAllLayerPop();
                    }
                    if ("1" == result.code) {//成功
                        that.showSuccessMsg(msg + "成功", function () {
                            success(result);
                        });
                    }else if (that.isEmpty(result.msg)) {//错误信息不为空
                        if ("0" == result.code) {
                            that.showFailMsg(msg + "失败", function () {
                                error();
                            });
                        } else {
                            that.showErrorMsg(msg + "出错", function () {
                                error();
                            });
                        }

                    } else {
                        if ("0" == result.code) {
                            that.showFailMsg(result.msg, function () {
                                error();
                            });
                        } else {
                            that.showErrorMsg(result.msg, function () {
                                error();
                            });
                        }
                    }
                },
                error: function () {
                    if (true) {
                        that.closeAllLayerPop();
                    }
                    that.showErrorMsg(result.msg, function () {
                        error();
                    });
                }
            });
    };
    app.prototype.ajaxRequestUnified = function (url, params, success, error, msg, loading) {
        var that = this;
        if (loading) {
            that.loading();
        }
        $.ajax({
            url: url,
            type: "post",
            data: params,
            dataType: "json",
            beforeSend: function (XMLHttpRequest) {
                XMLHttpRequest.setRequestHeader("X-Requested-Custom-Type", "ajax");
            },
            success: function (result) {
                if (loading) {
                    that.closeAllLayerPop();
                }
                if ("1" == result.code) {//成功
                    that.showSuccessMsg(msg + "成功", function () {
                        success(result);
                    });
                }else if (that.isEmpty(result.msg)) {//错误信息不为空
                    if ("0" == result.code) {
                        that.showFailMsg(msg + "失败", function () {
                            error();
                        });
                    } else {
                        that.showErrorMsg(msg + "出错", function () {
                            error();
                        });
                    }

                } else {
                    if ("0" == result.code) {
                        that.showFailMsg(result.msg, function () {
                            error();
                        });
                    } else {
                        that.showErrorMsg(result.msg, function () {
                            error();
                        });
                    }
                }
            },
            error: function () {
                if (loading) {
                    that.closeAllLayerPop();
                }
                that.showErrorMsg(result.msg, function () {
                    error();
                });
            }
        });
    };
    /**
     * 获取json对象
     * @param jsonStr
     */
    app.prototype.getJsonObject = function (jsonStr) {
        if (this.isEmpty(jsonStr)) {
            return null;
        }
        return J.parse(jsonStr);
    };
    /**
     * 获取json字符串
     * @param jsonObject
     */
    app.prototype.getJsonStr = function (jsonObject) {
        if (this.isEmpty(jsonObject)) {
            return null;
        }
        return J.stringify(jsonObject);
    };
    /**
     * 获取请求参数
     * @param name
     * @returns {*}
     */
    app.prototype.getUrlParamter = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURL(r[2]);
        }
        return null;
    }
    /**
     * 毫秒转换为日期字符串
     * @param millisecond
     */
    app.prototype.millisecondConvertToStr = function (millisecond) {
        var that = this;
        if (that.isEmpty(millisecond)) {
            return "";
        }
        var date = new Date(millisecond);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        month = that.fillSpecifiedDigits(month, 2, "0");
        var day = date.getDate();
        day = that.fillSpecifiedDigits(day, 2, "0");
        var hour = date.getHours();
        hour = that.fillSpecifiedDigits(hour, 2, "0");
        var minute = date.getMinutes();
        minute = that.fillSpecifiedDigits(minute, 2, "0");
        var second = date.getSeconds();
        second = that.fillSpecifiedDigits(second, 2, "0");
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    };
    /**
     * 填充到指定位数
     * @param origin
     * @param digits
     * @param character
     */
    app.prototype.fillSpecifiedDigits = function (origin, digits, character) {
        var lengthDiff = digits - (origin + "").length;
        if (lengthDiff > 0) {
            for (var i = 0; i < lengthDiff; i++) {
                origin = character + origin;
            }
        }
        return origin;
    };
    /**
     * 显示信息
     * @param msg
     */
    app.prototype.showMsg = function (msg) {
        layer.msg(msg);
    };
    /**
     * 显示成功信息
     * @param msg
     * @param callback
     */
    app.prototype.showSuccessMsg = function (msg, callback) {
        var that = this;
        layer.alert(msg, {icon: 1, shade: 0.6, skin: 'dialog_same'}, function (result) {
            that.closeAllLayerPop();
            if (!that.isEmpty(callback)) {
                callback(result);
            }
        });
    };
    /**
     * 显示失败信息
     * @param msg
     * @param callback
     */
    app.prototype.showFailMsg = function (msg, callback) {
        var that = this;
        layer.alert(msg, {icon: 2, shade: 0.6, skin: 'dialog_same'}, function () {
            that.closeAllLayerPop();
            if (!that.isEmpty(callback)) {
                callback();
            }
        });
    };
    /**
     * 显示错误信息
     * @param msg
     * @param callback
     */
    app.prototype.showErrorMsg = function (msg, callback) {
        var that = this;
        layer.alert(msg, {icon: 2, shade: 0.6, skin: 'dialog_same'}, function () {
            that.closeAllLayerPop();
            if (!that.isEmpty(callback)) {
                callback();
            }
        });
    };
    /**
     * 确认框
     * @param msg
     * @param sure
     * @param cancel
     */
    app.prototype.confirm = function (msg, sure, cancel) {
        layer.confirm(msg, {
            btn: ['确定', '取消']
            ,shade: 0.6
            ,skin:'dialog_same'
        }, function () {
            sure();
        }, function () {
            cancel();
        });
    };
    /**
     * loading
     */
    app.prototype.loading = function () {
        layer.load(1, {shade: [0.2, '#000']});
    };
    /**
     *显示图片
     * @param urls
     * @param start
     */
    app.prototype.showPhoto = function (urls, start) {
        var data = [];
        $(urls).each(function () {
            data.push({src: this});
        });
        start = this.isEmpty(start) ? 0 : start;
        layer.photos({
            photos: {
                start: start
                , data: data
            } //格式见API文档手册页
            , anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
            , shade: 0.6
        });
    };
    /**
     * 关闭所有layer弹窗
     */
    app.prototype.closeAllLayerPop = function () {
        layer.closeAll();
    };
    /**
     * 退出登录
     */
    app.prototype.logout = function () {
        window.location.href = "http://passport2.chaoxing.com/logout.html?refer=" + this.origin + "/logout";
    };
    /**
     * 退出登录(活动相关页面)
     */
    app.prototype.logoutActivityHome = function () {
        var that = this;
        window.location.href = "http://passport2.chaoxing.com/logout.html?refer=" + this.origin + "/activity/" + that.getActivityIdByPathName(window.location.pathname);
    };
    /**
     * 显示登录弹窗
     */
    app.prototype.showLoginPop = function () {
        $("#toLogin").show();
    };
    /**
     * 加载页面
     * @param dom
     * @param url
     */
    app.prototype.loadPage = function (dom, url, data) {
        var that = this;
        that.loading();
        $("" + dom).load(url, that.isEmpty(data) ? {} : data, function (response, status, xhr) {
            that.closeAllLayerPop();
            if ("error" == status) {
                //加载出错时显示错误页面的信息
                $("" + dom).html(response);
            }
        });
    };
    /**
     * 获取cookie的值
     * @param key
     * @returns {*}
     */
    app.prototype.getCookie = function (key) {
        var arr = document.cookie.match(new RegExp("(^| )" + key + "=([^;]*)(;|$)"));
        if (arr != null) return unescape(arr[2]);
        return null;
    };
    /**
     * 从cookie中获取uid
     * @returns {*}
     */
    app.prototype.getUid = function () {
        var that = this;
        return that.getCookie("_uid")
    };
    /**
     * 根据pathName获取活动id
     * @param pathName
     * @returns {*}
     */
    app.prototype.getActivityIdByPathName = function (pathName) {
        var that = this;
        var activityId = null;
        if (!that.isEmpty(pathName) && pathName.indexOf("/activity") == 0) {
            var arrs = pathName.split("/");
            $(arrs).each(function (i) {
                if (this == "activity") {
                    activityId = arrs[i + 1];
                    return;
                }
            });

        }
        return activityId;
    };
    /**
     * 获取活动的路径
     * @param activityId
     * @returns {string}
     */
    app.prototype.getActivityPath = function (activityId) {
        var that = this;
        return that.origin + ctx + "/activity/" + activityId;
    };
    /**
     * 将textarea的内容转换为html显示的内容（保留空格回车）
     * @param content
     * @returns {string | *}
     */
    app.prototype.textareaToHtml = function (content) {
        var reg = new RegExp("\n", "g");
        var regSpace = new RegExp(" ", "g");
        content = content.replace(reg, "<br>");
        content = content.replace(regSpace, "&nbsp;");
        return content;
    };
    /**
     * 将html显示的内容转换为textarea的内容（保留空格回车）
     * @param content
     * @returns {string | *}
     */
    app.prototype.htmlToTextarea = function (content) {
        var reg = new RegExp("<br>", "g");
        var regSpace = new RegExp("&nbsp;", "g");
        content = content.replace(reg, "\n");
        content = content.replace(regSpace, " ");
        return content;
    };
    /**
     *设置头部导航
     * @param key
     */
    app.prototype.setHeaderNavigation = function (key) {
        if (this.isEmpty(key)) {
            return;
        }
        $(header_vm.menus).each(function () {
            if (this.key == key) {
                this.current = true;
            } else {
                this.current = false;
            }
        });
    };
    W['app'] = new app();
})(window, jQuery, JSON);
//一些其他方法
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
Array.prototype.pushArray = function (array) {
    var that = this;
    $.each(array, function () {
        that.push(this);
    });
};
//年月日
Vue.filter('fullDataStrToYYYYMMDD', function (fullDataStr) {
    if (app.isEmpty(fullDataStr)) {
        return "";
    }
    return fullDataStr.substr(0, 10);
});
//年月日中文
Vue.filter('fullDataStrToYYYYMMDDChinese', function (fullDataStr) {
    if (app.isEmpty(fullDataStr)) {
        return "";
    }
    var result = fullDataStr.substr(0, 10);
    result = result.split("-");
    return result[0] + "年" + result[1] + "月" + result[2] + "日";
});
//年月日时
Vue.filter('fullDataStrToYYYYMMDDHH', function (fullDataStr) {
    if (app.isEmpty(fullDataStr)) {
        return "";
    }
    return fullDataStr.substr(0, 13);
});
//年月日时分
Vue.filter('fullDataStrToYYYYMMDDHHMM', function (fullDataStr) {
    if (app.isEmpty(fullDataStr)) {
        return "";
    }
    var that = app;
    var date = new Date(fullDataStr);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    month = that.fillSpecifiedDigits(month, 2, "0");
    var day = date.getDate();
    day = that.fillSpecifiedDigits(day, 2, "0");
    var hour = date.getHours();
    hour = that.fillSpecifiedDigits(hour, 2, "0");
    var minute = date.getMinutes();
    minute = that.fillSpecifiedDigits(minute, 2, "0");
    return year + "-" + month + "-" + day + " " + hour + ":" + minute;
});
//年月日时分秒
Vue.filter('fullDataStrToYYYYMMDDHHMMSS', function (fullDataStr) {
    if (app.isEmpty(fullDataStr)) {
        return "";
    }
    return fullDataStr;
});
//活动主页
Vue.filter('activityHome', function (activityId) {
    if (app.isEmpty(activityId)) {
        return "";
    }
    return app.getActivityPath(activityId);
});
Vue.filter('timeSurplus', function (fullDataStr) {
    if (app.isEmpty(fullDataStr)) {
        return "";
    }
    fullDataStr = fullDataStr.substr(0, 10);
    fullDataStr += " 23:59:59";
    fullDataStr = fullDataStr.replace(/-/g, "/");
    var time1 = Date.parse(new Date(fullDataStr));
    var time2 = Date.parse(new Date());
    var nDays = "";
    if(parseInt(time1 - time2)>0){
        var nDays = Math.abs(parseInt((time1 - time2)/1000/3600/24));
        if(nDays<0){
            nDays = "活动已结束";
        }else{
            nDays = "还有"+parseInt(nDays+1)+"天结束";
        }
    }else{
        nDays = "活动已结束";
    }

    return nDays;
});
Vue.filter('timeStart', function (fullDataStr) {
    if (app.isEmpty(fullDataStr)) {
        return "";
    }
    fullDataStr = fullDataStr.substr(0, 10);
    fullDataStr += " 00:00:00";
    fullDataStr = fullDataStr.replace(/-/g, "/");
    var time1 = Date.parse(new Date(fullDataStr));
    var time2 = Date.parse(new Date());
    var nDays = "";
    if(parseInt(time1 - time2)>0){
        var nDays = Math.abs(parseInt((time1 - time2)/1000/3600/24));
        nDays = "还有"+parseInt(nDays+1)+"天开始";
    }else{
        nDays = "活动已开始";
    }

    return nDays;
});
