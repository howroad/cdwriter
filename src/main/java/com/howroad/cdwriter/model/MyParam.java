package com.howroad.cdwriter.model;

import com.google.common.base.CaseFormat;
import com.howroad.cdwriter.constants.TableContans;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: MyParam.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * @since：2019年2月1日 下午1:38:11
 */
public class MyParam {

    private String paramName;
    private String paramRemark;
    private String columnName;
    private MyType type;
    private boolean nullable;
    private String defaultValue;
    private Map<String, String> map = new HashMap<String, String>();

    public MyParam(String paramName, String columnName, String paramRemark, int dateType, int columnSize, int decimalDigits,
                   int nullableValue, String defaultV) {
        super();
        this.type = new MyType(dateType, columnSize, decimalDigits);
        this.paramName = paramName;
        this.paramRemark = paramRemark;
        this.columnName = columnName;
        this.nullable = nullableValue == 1;
        switch (dateType) {
            case Types.DECIMAL:
                this.defaultValue = defaultV;
                break;
            case Types.VARCHAR :
                this.defaultValue = StringUtils.isBlank(defaultV) ? null : "''" + defaultV + "''";
                break;
            case Types.TIMESTAMP:
                this.defaultValue = defaultV;
                break;
            case Types.DATE:
                this.defaultValue = defaultV;
                break;
            case Types.CHAR:
                this.defaultValue = StringUtils.isBlank(defaultV) ? null : "''" + defaultV + "''";
                break;
            case Types.BLOB:
                this.defaultValue = defaultV;
                break;
            case Types.CLOB:
                this.defaultValue = defaultV;
                break;
            default:
                throw new RuntimeException("未知的类型");
        }
        init();
    }

    public MyParam(String paramRemark, String columnName, String typeStr, String nullable0, String default0, String paramName) {
        super();
        this.paramRemark = paramRemark;
        this.columnName = columnName.toUpperCase();
        this.paramName = StringUtils.isNotBlank(paramName) ? paramName : CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        String columnType = typeStr.trim().toUpperCase();
        Matcher mat = Pattern.compile("(?<=\\()(\\S+)(?=\\))").matcher(typeStr);
        if (TableContans.DATE.equals(columnType)) {
            this.type = new MyType(Types.DATE, 0, 0);
            this.defaultValue = default0;
        } else if (TableContans.TIMESTAMP.equals(columnType)) {
            this.type = new MyType(Types.TIMESTAMP, 0, 0);
            this.defaultValue = default0;
        } else if (columnType.startsWith(TableContans.NUMBER)) {
            if (mat.find()) {
                String inner = mat.group();
                if(inner.contains(TableContans.SPILIT_CHAR)){
                    this.type = new MyType(Types.DECIMAL, new Integer(inner.split(TableContans.SPILIT_CHAR)[0]), new Integer(inner.split(TableContans.SPILIT_CHAR)[1]));
                }else{
                    this.type = new MyType(Types.DECIMAL, new Integer(inner), NumberUtils.INTEGER_ZERO);
                }

            } else {
                this.type = new MyType(Types.DECIMAL, 0, 0);
            }
            this.defaultValue = default0;
        } else if (columnType.startsWith(TableContans.VARCHAR2)) {
            if (mat.find()) {
                this.type = new MyType(Types.VARCHAR, new Integer(mat.group()), 0);
            } else {
                throw new RuntimeException("类型错误");
            }
            this.defaultValue = StringUtils.isBlank(default0)? null : "''" + default0 + "''";
        } else if (columnType.startsWith(TableContans.INTEGER)) {
            this.type = new MyType(Types.DECIMAL, 0, 0);
            this.defaultValue = default0;
        } else if (columnType.startsWith(TableContans.BLOB)) {
            this.type = new MyType(Types.BLOB, 0, 0);
            this.defaultValue = default0;
        }else if(columnType.startsWith(TableContans.CLOB)){
            this.type = new MyType(Types.CLOB, 0, 0);
            this.defaultValue = default0;
        }
        else {
            throw new RuntimeException("未知类型 : " + columnType);
        }
        this.nullable = !TableContans.NOTNULL.equalsIgnoreCase(nullable0.trim());
        init();
    }

    public void init() {
        map.put("paramName", this.paramName);
        map.put("paramRemark", this.paramRemark);
        map.put("paramType", this.type.getParamTypeName());
        map.put("bigName", CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, this.paramName));
        map.put("columnName", this.columnName);
        map.put("columnType", this.type.getColumnTypeName());
        map.put("columnSize", String.valueOf(this.type.getColumnSize()));
        map.put("defaultValue", StringUtils.isBlank(this.defaultValue ) ? "" :"DEFAULT ON NULL " + this.defaultValue + " ");
        //TODO 实际上dafault on null的时候就自动设置了not null
        map.put("nullable", this.nullable ? "" : "NOT NULL");
    }

    public MyType getType() {
        return type;
    }

    public void setType(MyType type) {
        this.type = type;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamRemark() {
        return paramRemark;
    }

    public void setParamRemark(String paramRemark) {
        this.paramRemark = paramRemark;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
