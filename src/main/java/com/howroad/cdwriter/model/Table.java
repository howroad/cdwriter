package com.howroad.cdwriter.model;

import com.google.common.base.CaseFormat;
import com.howroad.cdwriter.conf.PageConfig;
import com.howroad.cdwriter.constants.TableContans;
import oracle.sql.TIMESTAMP;

import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * <p>Title: Table.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2018年12月26日 下午4:56:49
 *
 */
public class Table{
    
	private String tableName;
	private String tableRemart;
	private Map<String, String> map = new HashMap<String, String>();
	private List<MyParam> paramList;

    public Table(String tableName, String tableRemark, List<MyParam> paramList) {
        super();
        this.tableName = tableName.toUpperCase();
        this.tableRemart = tableRemark == null || "null".equals(tableRemark) ? "" : tableRemark;
        this.paramList = paramList;
        
    }
    private void initMap() {
        if(hasDateType()) {
            map.put("import", "import java.util.Date;");
        }else {
            map.put("import", "");
        }
        map.put("entityName", getEntityName());
        map.put("entityNameLow", getEntityName().toLowerCase());
        map.put("remark", this.tableRemart);
        map.put("tableName", getTableName());
        map.put("pkName",paramList.get(0).getParamName());
        map.put("pkColumnName",paramList.get(0).getColumnName());
        map.put("seqName", getSeqName());
        map.put("pkNameUp",CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, paramList.get(0).getParamName()));
        map.put("tableNo", tableNo());
        
    }

    /** 重新载入对应关系
     * @param map*/
    public void reloadColumnMap(Map<String, Map.Entry<String, Integer>> map){
        for (MyParam myParam : paramList) {
            myParam.setParamName(map.get(myParam.getColumnName()).getKey());
            myParam.init();
        }
        this.initMap();
    }
    
    //获得缩写
    public String tableNo() {
        String tableName = getTableName();
        String reg = "[^\\_]+";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(tableName);
        StringBuffer buffer = new StringBuffer();
        while(matcher.find()) {
            String str = matcher.group();
            buffer.append(str.charAt(0));
        }
        return buffer.toString();
    }
    
    
	/**
	 * 是否有时间类型
	 * @Description:
	 * @return boolean
	 * @author luhao
	 * @since：2019年1月30日 下午4:44:48
	 */
	private boolean hasDateType() {
	    if(paramList == null || paramList.isEmpty()) {
	        throw new RuntimeException("无列表信息！");
	    }
       for (MyParam myParam : paramList) {
            if(myParam == null) {
                throw new RuntimeException("Param中无信息！");
            }
            if(myParam.getType().getValue() == Types.DATE || myParam.getType().getValue() == Types.TIMESTAMP) {
                return true;
            }
        }
	    return false;
	}


    /**
     * 
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2019年1月10日 下午5:03:34
     */
    public String getSeqName() {
        String seqName = null;
        if(!PageConfig.SEQ_ON_LAST) {
            seqName = "SEQ_" + getTableName();
        }else {
            seqName = getTableName() + "_SEQ";
        }
        return seqName;
    }
    
    /**
     * 获得实体类的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:32:04
     */
    public String getEntityName() {
        String entityName = null;
        if(tableName.toUpperCase().startsWith("UM_")) {
            entityName = tableName.substring(tableName.indexOf(TableContans.UNDER_LINE) + 1);
            entityName = "UM_" + entityName.substring(entityName.indexOf(TableContans.UNDER_LINE) + 1);
        }else if (tableName.contains(TableContans.UNDER_LINE)) {
            entityName = tableName.substring(tableName.indexOf(TableContans.UNDER_LINE));
        } else {
            entityName = tableName;
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, entityName);
    }
    
    /**
     * 获得JavaBean的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:31:36
     */
    public String getJaveBeanFileName() {
        return getEntityName() + TableContans.PO + ".java";
    }
    
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableRemart() {
		return tableRemart;
	}
	public void setTableRemart(String tableRemart) {
		this.tableRemart = tableRemart;
	}
	
    @Override
	public Table clone() {
	    return new Table(tableName, tableRemart,paramList);
	}
	@Override
    public String toString() {
        return "Table [tableName=" + tableName + ", tableRemart=" + tableRemart + "]";
    }
    public Map<String, String> getMap() {
	    initMap();
        return map;
    }

    public List<MyParam> getParamList() {
        return paramList;
    }

    public void setParamList(List<MyParam> paramList) {
        this.paramList = paramList;
    }

    /**
     * 获得插入语句的字段，例如to_date('','')
     * @param obj
     * @param param
     * @return
     */
    public String getInsertValue(Object obj,MyParam param) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int columnType = param.getType().getValue();
        String result = null;
        if(obj == null) {
            result = "null";
        }else if (Types.DECIMAL == columnType) {
            result = String.valueOf(obj);
        } else if (Types.VARCHAR == columnType || Types.CHAR == columnType) {
            String str = String.valueOf(obj);
            str = str.replaceAll("'", "''");
            str = str.replace("&", "' || '&' || '");
            result = "'" + str + "'";
        } else if (Types.TIMESTAMP == columnType || Types.DATE == columnType) {
            if(obj.getClass() == oracle.sql.TIMESTAMP.class){
                TIMESTAMP time = (TIMESTAMP) obj;
                Date dateTime = null;
                try {
                    dateTime = time.dateValue();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String value = sdf.format(dateTime);
                result = "TO_DATE('" + value + "', 'YYYY-MM-DD HH24:MI:SS')";
            }else {
                String value = sdf.format(obj);
                result = "TO_DATE('" + value + "', 'YYYY-MM-DD HH24:MI:SS')";
            }
        } else {
            throw new RuntimeException("未知类型！");
        }
        return result;
    }
}
