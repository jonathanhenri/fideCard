package com.fidecard.application.utils;

import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
	
	public static Date convertStringToData(String data) {
		Date dataEntrada;
		if (StringUtils.isEmpty(data)) {
			dataEntrada = new Date();
		} else {
			dataEntrada = asDate(data);
		}
		return dataEntrada;
	}
	
	public static Date parseDataWithNoTimezone(String data) {
		return parseData(data, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date parseData(String data, String padrao) {
		SimpleDateFormat format = new SimpleDateFormat(padrao);
		
		try {
			return format.parse(data);
		} catch (ParseException var4) {
			throw new RuntimeException("Erro ao fazer o parse da data " + data + " com o padrao " + padrao);
		}
	}
	
	public static Date parseDataWithTimezoneAuto(String data) {
		int millisDot = data.indexOf(46);
		StringBuilder sb = new StringBuilder();
		sb.append("yyyy-MM-dd'T'HH:mm:ss");
		if (millisDot > 0) {
			sb.append(".SSS");
		}
		
		sb.append("X");
		return parseData(data, sb.toString());
	}
	
	public static Date asDate(String sData) {
		if (StringUtils.isBlank(sData)) {
			return null;
		} else {
			if (sData.endsWith("Z")) {
				sData = sData.replace("Z", "");
			}
			
			Date data;
			if (sData.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")) {
				data = parseDataWithNoTimezone(sData);
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{1,3}$")) {
				data = parseData(sData, "yyyy-MM-dd HH:mm:ss.SSS");
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{1,3}(-|\\+)\\d{2}$")) {
				data = parseData(sData, "yyyy-MM-dd HH:mm:ss.SSSX");
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(-|\\+)\\d{2}:\\d{2}$")) {
				data = parseDataWithTimezone(sData);
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}-\\d{2}:\\d{2}$")) {
				data = parseDataWithTimezoneAuto(sData);
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$")) {
				data = parseDataXml(sData);
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")) {
				data = parseData(sData, "yyyy-MM-dd HH:mm");
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$")) {
				data = parseData(sData, "yyyy-MM-dd'T'HH:mm");
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
				data = parseData(sData, "yyyy-MM-dd");
			} else if (sData.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
				data = parseData(sData, "dd/MM/yyyy");
			} else if (sData.matches("^\\d{2}:\\d{2}:\\d{2}$")) {
				data = parseData(sData, "HH:mm:ss");
			} else if (sData.matches("^\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}$")) {
				data = parseData(sData, "dd/MM/yyyy HH:mm:ss");
			} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}-\\d{4}$")) {
				data = parseData(sData, "yyyy-MM-dd'T'HH:mm:ssX");
			} else {
				if (sData.matches("^\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}\\d{2}$")) {
					return parseData(sData, "yyyyMMddHHmmss");
				}
				
				if (sData.matches("^20\\d{2}\\d{2}\\d{2}$")) {
					data = parseData(sData, "yyyyMMdd");
				} else if (sData.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+$")) {
					data = parseData(sData, "yyyy-MM-dd'T'HH:mm:ss.SSS");
				} else if (sData.matches("^\\d{4}\\d{2}")) {
					data = parseData(sData, "yyyyMM");
				} else {
					try {
						data = parseData(sData, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
					} catch (RuntimeException var3) {
						throw new IllegalArgumentException("Valor '" + sData + "' não pode ser convertido para Data");
					}
				}
			}
			
			return data;
		}
	}
	
	public static Date parseDataXml(String date) {
		return parseData(date, "yyyy-MM-dd'T'HH:mm:ss");
	}
	
	public static Date parseDataWithTimezone(String data) {
		String result = data.substring(0, 22) + data.substring(23, 25);
		return parseData(result, "yyyy-MM-dd'T'HH:mm:ssZ");
	}
	
	public static Date parseData(String data, String padrao, Locale locale) {
		SimpleDateFormat format = new SimpleDateFormat(padrao, locale);
		
		try {
			return format.parse(data);
		} catch (ParseException var5) {
			throw new RuntimeException("Erro ao fazer o parse da data " + data + " com o padrao " + padrao);
		}
	}
}
