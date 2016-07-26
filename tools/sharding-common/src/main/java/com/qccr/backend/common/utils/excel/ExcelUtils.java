/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.backend.common.utils.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

/**
 * 
 * @author wangyz
 * @version $Id: ExcelUtils.java, v 0.1 2016年3月4日 下午1:46:40 wangyz Exp $
 */
public class ExcelUtils {

	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";
	/**
	 * Excel 2007
	 */
	private final static String XLSX = "xlsx";

	public static String getVersion(String name) {
		// 默认03版本
		if (subName(name).equals(XLSX)) {
			return XLSX;
		} else {
			return XLS;
		}
	}

	public static String subName(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		Integer inedx = name.lastIndexOf(".");
		if (inedx < 0) {
			return null;
		}
		return name.substring(inedx + 1, name.length());
	}

	/**
	 * 解析excel返回数组
	 * 
	 * @param file
	 * @return
	 * @date: 2016年3月4日 下午2:33:21
	 */
	public static <E> List<E> getArrayList(InputStream file, String filename, Class<E> cls) throws Exception {
		// 区别2003和2007版本
		Workbook workbook = null;
		if (getVersion(filename).equals(XLS)) {
			workbook = new HSSFWorkbook(file);
		} else if (getVersion(filename).equals(XLSX)) {
			workbook = new XSSFWorkbook(file);
		}
		// 获得所有属性
		Field[] fields = cls.getDeclaredFields();
		// 获得模型字段
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		rowIterator.next();
		List<E> list = Lists.newArrayList();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			E entity = cls.newInstance();
			for (int i = 0; i < fields.length; i++) {
				Method method = cls.getMethod(getMethodName(fields[i]), fields[i].getType());
				method.invoke(entity, getValue(row.getCell(i), fields[i]));
			}
			list.add(entity);
		}
		return list;
	}

	/**
	 * 返回list集合
	 * 
	 * @param file
	 * @return
	 * @date: 2016年3月4日 下午2:33:21
	 */
	public static <E> List<E> parseArray(InputStream file, String filename, Class<E> cls) throws Exception {
		return getArrayList(file, filename, cls);
	}

	/**
	 * 获得表单格的值
	 * 
	 * @param cell
	 * @return
	 * @throws Exception
	 * @date: 2016年3月4日 下午4:02:16
	 */
	public static Object getValue(Cell cell, Field field) throws Exception {
		if (cell == null)
			return null;

		if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
			return null;

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			if (cell.getStringCellValue().equals(""))
				return null;

			if (field.getType().equals(String.class)) {
				return cell.getStringCellValue().trim();
			}

			if (field.getType().equals(Double.class)) {
				return new Double(cell.getStringCellValue());
			}

			if (field.getType().equals(Integer.class)) {
				return Integer.parseInt(cell.getStringCellValue());
			}
			// 如果模型属性是日期形式，转换成日期，目前只支持 yyyy-MM-dd HH:mm:ss 格式
			if (field.getType().equals(Date.class)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sdf.parse(cell.getStringCellValue());
			}
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (field.getType().equals(Integer.class)) {
				return new Double(cell.getNumericCellValue()).intValue();
			}

			if (field.getType().equals(Double.class)) {
				return new Double(cell.getNumericCellValue());
			}

			if (field.getType().equals(Long.class)) {
				return new Long(cell.getNumericCellValue() + "");
			}

			if (field.getType().equals(String.class)) {
				String value = cell.getNumericCellValue() + "";
				String ls = value.substring(value.lastIndexOf(".") + 1, value.length());
				if (ls.equals("0")) {
					return value.substring(0, value.indexOf("."));
				}
				return value.trim();
			}
		}
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			if (field.getType().equals(Boolean.class)) {
				return new Boolean(cell.getStringCellValue());
			}
		}
		if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
			return null;
		}

		throw new Exception("excel解析错误!字段名字为:" + field.getName() + ",excel的值为" + cell);
	}

	/**
	 * 导出excel
	 * 
	 * @param exprotObject
	 * @return
	 * @throws Exception
	 * @date: 2016年3月7日 下午5:12:22
	 */
	public static void exportToExcel(HttpServletResponse response, ExprotObject exprotObject) throws Exception {
		OutputStream os = response.getOutputStream();
		XSSFWorkbook wwb = new XSSFWorkbook();
		XSSFSheet ws = wwb.createSheet();

		XSSFFont headfont = wwb.createFont();
		headfont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		XSSFCellStyle headstyle = wwb.createCellStyle();
		headstyle.setFont(headfont);
		// 标题行
		List<String> columns = exprotObject.getColumns();
		// 内容行
		List<String> valueColumns = exprotObject.getValueColumns();
		// 数据
		List<? extends Object> data = exprotObject.getData();
		// 生成第一行
		if (exprotObject.getIsTitleColumns() && !CollectionUtils.isEmpty(columns)) {
			XSSFRow row = ws.createRow(0);
			for (int i = 0; i < columns.size(); i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellValue(columns.get(i));
			}
		}

		if (!CollectionUtils.isEmpty(data)) {
			for (int i = 0; i < data.size(); i++) {
				XSSFRow row = ws.createRow(i + 1);
				for (int j = 0; j < valueColumns.size(); j++) {
					XSSFCell cell = row.createCell(j);
					Class<?> cls = PropertyUtils.getPropertyType(data.get(i), valueColumns.get(j));
					// 如果是日期格式需要注册格式转换器给beanUtils使用
					if (cls != null && cls.equals(Date.class)) {
						DateConverter dateConverter = new DateConverter();
						dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
						ConvertUtils.register(dateConverter, Date.class);
						cell.setCellValue(BeanUtils.getProperty(data.get(i), valueColumns.get(j)));
					} else {
						cell.setCellValue(BeanUtils.getProperty(data.get(i), valueColumns.get(j)));
					}
				}
			}
		}
		wwb.write(os);
		wwb.close();
		os.flush();
		os.close();
	}

	/**
	 * 下载excel
	 * 
	 * @param response
	 * @param exprotObject
	 * @throws Exception
	 * @date: 2016年3月7日 下午12:48:47
	 */
	public static void exportexcle(HttpServletResponse response, ExprotObject exprotObject) throws Exception {
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=carCategoryList.xlsx");
		response.setContentType("application/msexcel");
		exportToExcel(response, exprotObject);
	}

	public static String getMethodName(Field field) {
		String fieldName = field.getName();
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());

	}

}
