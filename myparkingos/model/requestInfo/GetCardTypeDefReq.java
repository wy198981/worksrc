package com.example.administrator.myparkingos.model.requestInfo;

/**
 * Created by Administrator on 2017-04-11.
 */
public class GetCardTypeDefReq
{
    private String token;          // Y  用户登录时候获取的token值
    private String jsonModel;      // N

    private String jsonSearchParam;// N 查询条件。参考《数据查询条件结构》一节。 如果同时指定了jsonModel和jsonSearchParam，则两组条件都有效，两组条件间是逻辑与的关系。
    private Integer PageSize;      // N 每页最大记录数。必须和Page参数一起使用。
    private Integer Page;          // N 表示选取分页后的第几页的数据。从1开始编号。必须和PageSize参数一起使用。
    private String OrderField;     // N 指定查询的排序规则。如：”ID asc, PID desc”。ID和PID是数据结构中的字段名，asc表示升序排列，desc表示降序排列
    private String ExportFields;   // N 需要导出的字段列表。JSON格式的对象。如{“UserNO”:”用户编号”,”UserName”:”姓名”,……}
    private String CallBack;       // N 是否使用JSONP方式。关于JSONP方式请参考Javascript跨域访问一节。

    @Override
    public String toString()
    {
        return "GetCardTypeDefReq{" +
                "token='" + token + '\'' +
                ", jsonModel='" + jsonModel + '\'' +
                ", jsonSearchParam='" + jsonSearchParam + '\'' +
                ", PageSize=" + PageSize +
                ", Page=" + Page +
                ", OrderField='" + OrderField + '\'' +
                ", ExportFields='" + ExportFields + '\'' +
                ", CallBack='" + CallBack + '\'' +
                '}';
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getJsonModel()
    {
        return jsonModel;
    }

    public void setJsonModel(String jsonModel)
    {
        this.jsonModel = jsonModel;
    }

    public String getJsonSearchParam()
    {
        return jsonSearchParam;
    }

    public void setJsonSearchParam(String jsonSearchParam)
    {
        this.jsonSearchParam = jsonSearchParam;
    }

    public Integer getPageSize()
    {
        return PageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        PageSize = pageSize;
    }

    public Integer getPage()
    {
        return Page;
    }

    public void setPage(Integer page)
    {
        Page = page;
    }

    public String getOrderField()
    {
        return OrderField;
    }

    public void setOrderField(String orderField)
    {
        OrderField = orderField;
    }

    public String getExportFields()
    {
        return ExportFields;
    }

    public void setExportFields(String exportFields)
    {
        ExportFields = exportFields;
    }

    public String getCallBack()
    {
        return CallBack;
    }

    public void setCallBack(String callBack)
    {
        CallBack = callBack;
    }
}
