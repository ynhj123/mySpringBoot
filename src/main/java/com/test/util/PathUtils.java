package com.test.util;

import com.test.TestApplication;
import com.test.util.constant.CommonConstant;

/**
 * @className: PathUtils
 * @description:
 * @author: chaoxing
 * @date: 2018-04-28 14:22:08
 * @version: ver 1.0
 */
public class PathUtils {

    /** 项目的部署路径 */
    private static final String project_deploy_path = TestApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    /** 获取项目部署的目录
     * @Description: 
     * @author: 
     * @Date: 2018-04-28 14:32:13
     * @param: 
     * @return: java.lang.String
     */
    public static String getProjectDeployDir() {
        return FileUtils.getFileDir(project_deploy_path);
    }
    /** 获取上传根路径
     * @Description: 
     * @author: 
     * @Date: 2018-04-28 14:57:31
     * @param: 
     * @return: java.lang.String
     */
    public static String getUploadRootPath() {
        return getProjectDeployDir() + CommonConstant.DEFAULT_FILE_SEPARATOR + PropertiesUtils.getStringValue("resource", "upload_root_path");
    }

}
