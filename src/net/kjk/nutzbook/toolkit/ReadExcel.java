package net.kjk.nutzbook.toolkit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class ReadExcel {
	
	
	
	    private static POIFSFileSystem fs;
	    private static HSSFWorkbook wb;
	    private static HSSFSheet sheet;
	    private static HSSFRow row;

	    /**
	     * 读取Excel表格表头的内容
	     * @param InputStream
	     * @return String 表头内容的数组
	     */
	    public  static  String[] readExcelTitle(String path) {
	        try {
	        	 InputStream is = new FileInputStream(path);
	            fs = new POIFSFileSystem(is);
	            wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        sheet = wb.getSheetAt(0);
	        row = sheet.getRow(0);
	        // 标题总列数
	        int colNum = row.getPhysicalNumberOfCells();
	        System.out.println("colNum:" + colNum);
	        String[] title = new String[colNum];
	        for (int i = 0; i < colNum; i++) {
	            //title[i] = getStringCellValue(row.getCell((short) i));
	            title[i] = getCellFormatValue(row.getCell((short) i));
	        }
	        return title;
	    }
	    
	    
	    /**
	     * 根据表头内容和定义好的变量和中文map，进行变量名的匹配
	     * @param path
	     * @param tabelHead
	     * @return
	     * @throws Exception
	     */
	    public static String[] impotrHead(String path, Map<String, String> tabelHead ) throws Exception{
	    	String []  ss=readExcelTitle(path);
			String headName = "";
			boolean flag = true;
			for (int i = 0; i < ss.length; i++) {
				if(flag){
					flag = false;
					for(Map.Entry<String, String> entry: tabelHead.entrySet()) {
						if(ss[i].equals(entry.getKey())){
							if(headName.equals("")){
								headName = entry.getValue();
							}
							else{
								headName = headName +","+entry.getValue();
							}
							flag = true;
							break;
						}
					}
				}
				else{
					break;
				}	
			}
			if(flag){
				String[] fieldNames = headName.split(",");
				return fieldNames;
			}
			return null;
			
		}
	    
	    /**
	     * 读取Excel数据内容
	     * @param InputStream
	     * @return Map 包含单元格数据内容的Map对象
	     */
	    public static Map<Integer, String> readExcelContent(String path) {
	        Map<Integer, String> content = new HashMap<Integer, String>();
	        String str = "";
	        try {
	        	InputStream is = new FileInputStream(path);
	            fs = new POIFSFileSystem(is);
	            wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        sheet = wb.getSheetAt(0);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(0);
	        int colNum = row.getPhysicalNumberOfCells();
	        // 正文内容应该从第二行开始,第一行为表头的标题
	        for (int i = 1; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            int j = 0;
	            while (j < colNum) {
	                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
	                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
	                // str += getStringCellValue(row.getCell((short) j)).trim() +
	                // "-";
	                str += getCellFormatValue(row.getCell((short) j)).trim() + ",";
	                j++;
	            }
	            content.put(i, str);
	            str = "";
	        }
	        return content;
	    }    
	    
	    /**
	     * 根据HSSFCell类型设置数据
	     * @param cell
	     * @return
	     */
	    private static String getCellFormatValue(HSSFCell cell) {
	        String cellvalue = "";
	        if (cell != null) {
	            // 判断当前Cell的Type
	            switch (cell.getCellType()) {
	            // 如果当前Cell的Type为NUMERIC
	            case HSSFCell.CELL_TYPE_NUMERIC:
	            case HSSFCell.CELL_TYPE_FORMULA: {
	                // 判断当前的cell是否为Date
	                if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                    // 如果是Date类型则，转化为Data格式
	                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
	                    //cellvalue = cell.getDateCellValue().toLocaleString();
	                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
	                    Date date = cell.getDateCellValue();
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	                    cellvalue = sdf.format(date);
	                }
	                // 如果是纯数字
	                else {
	                    // 取得当前Cell的数值
	                    cellvalue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            // 如果当前Cell的Type为STRIN
	            case HSSFCell.CELL_TYPE_STRING:
	                // 取得当前的Cell字符串
	                cellvalue = cell.getRichStringCellValue().getString();
	                break;
	            // 默认的Cell值
	            default:
	                cellvalue = " ";
	            }
	        } else {
	            cellvalue = "";
	        }
	        return cellvalue;

	    }
	    
	
	
	
	 protected static <T> Object parseValueWithType(String value, Class<?> type) {
		 Object result = null;
	        try { // 根据属性的类型将内容转换成对应的类型
	            if (Boolean.TYPE == type) {
	                result = Boolean.parseBoolean(value);
	            } else if (Byte.TYPE == type) {
	                result = Byte.parseByte(value);
	            } else if (Short.TYPE == type) {
	                result = Short.parseShort(value);
	            } else if (Integer.TYPE == type) {
	                result = Integer.parseInt(value);
	            } else if (Long.TYPE == type) {
	                result = Long.parseLong(value);
	            } else if (Float.TYPE == type) {
	                result = Float.parseFloat(value);
	            } else if (Double.TYPE == type) {
	                result = Double.parseDouble(value);
	            } else if (Class.forName("java.lang.Double") == type){
	            	result = Double.parseDouble(value);
	            } else {
	                result = (Object) value;
	            }
	        } catch (Exception e) {
	            // 把异常吞掉直接返回null
	        }
	        return result;
    }
	
	
	 public static <T> List<T> readExcels(Class<T> clazz, Map<String, String> oldMap, String[] fieldNames,Map<Integer, String> excelMaps) throws Exception {
			List<T> dataModels = new ArrayList<T>();
			for (int i = 1; i <= excelMaps.size(); i++) {
				String row[] = excelMaps.get(i).split(",");
               /* System.out.println(excelMaps.get(i));*/
                T target = clazz.newInstance();
                for (int j = 0; j < fieldNames.length; j++) {
					String fieldName = fieldNames[j];
					if (fieldName == null) {
						continue; // 过滤serialVersionUID属性
					}
					if(j==row.length){
						break;
					}
					// 获取excel单元格的内容
					String cell = row[j];
					String content = "";
					if (cell != null) {
						content = cell;
					}
					
				/*	target 指定对象
					fieldName 属性名
					argTypes 参数类型
					args 参数列表*/
					Field field = clazz.getDeclaredField(fieldName);
					 invokeSetter(target, fieldName,
	                         parseValueWithType(content, field.getType()));
				}
                dataModels.add(target); 
            }
			return dataModels;
		}
	 
	    public static <T> void invokeSetter(T target, String fieldName, Object args)
	            throws NoSuchFieldException, SecurityException,
	            NoSuchMethodException, IllegalAccessException,
	            IllegalArgumentException, InvocationTargetException {
	        // 如果属性名为xxx，则方法名为setXxx
	        String methodName = "set" + firstCharUpperCase(fieldName);
	        Class<?> clazz = target.getClass();
	        Field field = clazz.getDeclaredField(fieldName);
	        Method method = clazz.getMethod(methodName, field.getType());
	        method.invoke(target, args);
	    }
	    public static String firstCharUpperCase(String str) {
	        StringBuffer buffer = new StringBuffer(str);
	        if (buffer.length() > 0) {
	            char c = buffer.charAt(0);
	            buffer.setCharAt(0, Character.toUpperCase(c));
	        }
	        return buffer.toString();
	    }
}

