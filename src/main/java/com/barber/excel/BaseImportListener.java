package com.barber.excel;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ModelBuildEventListener;
import com.barber.enums.TaskResultEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static cn.hutool.core.collection.CollUtil.isNotEmpty;
import static cn.hutool.core.util.StrUtil.isNotBlank;
import static com.alibaba.excel.util.DateUtils.parseDate;
import static com.baomidou.mybatisplus.core.toolkit.ObjectUtils.isEmpty;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.stream;


/**
 * @author liheng
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class BaseImportListener<E> extends ModelBuildEventListener {

	/**
	 * 最大导入条数
	 * TODO 暂时上限调至5000条
	 */
	protected final int maxCount = 5000;

	/**
	 * 行数
	 */
	protected int row = 1;

	/**
	 * 正常条数
	 */
	protected int successCount = 0;

	/**
	 * 异常条数
	 */
	protected int errorCount = 0;

	/**
	 * 缓存的数据列表
	 */
	protected List<E> list = new ArrayList<>();

	/**
	 * 告警提示
	 */
	protected List<String> warningPrompts = new ArrayList<>();

	/**
	 * 错误提示
	 */
	protected List<String> errorPrompts = new ArrayList<>();


	private boolean success = false;

	private String exceptionMsg = null;

	private boolean headCheck = false;

	protected boolean hasError = false;

	public static final Integer DATE_LENGTH = 8;

	public static final Integer DATE_LENGTH_SIX = 6;

	private TaskResultEnum taskResultEnum = TaskResultEnum.SUCCESS;

	private Map<Integer, List<String>> errorMap = Maps.newHashMap();

	private Map<Integer, List<String>> warnMap = Maps.newHashMap();

	@Override
	public void invokeHead(Map<Integer, CellData> cellDataMap, AnalysisContext context) {
		if (headRowNumber() == row) {
			Enum<? extends HeadEnum>[] headData = getHead();
			headCheck = cellDataMap.size() == headData.length && stream(headData).allMatch(head ->
				((HeadEnum) head).getFieldName().equals(StrUtil.toString((cellDataMap.get(head.ordinal())))));
		}
	}

	@Override
	public void invoke(Map<Integer, CellData> cellDataMap, AnalysisContext context) {
		hasError = false;
		int currentErrorSize = errorPrompts.size();

		try {
			E entity = convertEntity(new CellDataDTO(cellDataMap));

			if (errorPrompts.size() > currentErrorSize) {
				errorCount++;
			} else {
				successCount++;
			}
			list.add(entity);
		} catch (Exception e) {
			e.printStackTrace();
			addErrorPrompt("内部服务异常！");
			errorCount++;
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		doFinish();
	}

	/**
	 * 转换保单对象
	 *
	 * @param cellData 单元格数据
	 * @return 保单对象
	 */
	protected abstract E convertEntity(CellDataDTO cellData);

	/**
	 * 获取表头
	 *
	 * @return 表头
	 */
	protected abstract Enum<? extends HeadEnum>[] getHead();

	/**
	 * 默认表头行数
	 *
	 * @return 表头行数
	 */
	protected int headRowNumber() {
		return 1;
	}

	protected void addWarningPrompts(String prompt) {
		List<String> list = this.warnMap.getOrDefault(row - headRowNumber() - 1, Lists.newArrayList());
		list.add(prompt);
		warnMap.put(row - headRowNumber() - 1, list);
		warningPrompts.add("第" + row + "行：" + prompt);
	}

	protected void addWarningPrompts(E entity) {
	}

	protected void addErrorPrompt(String prompt) {
		if (StrUtil.isNotEmpty(prompt)) {
			List<String> list = this.errorMap.getOrDefault(row - headRowNumber() - 1, Lists.newArrayList());
			list.add(prompt);
			errorMap.put(row - headRowNumber() - 1, list);
			errorPrompts.add("第" + row + "行：" + prompt);
			hasError = true;
		}
	}

	protected void addErrorPrompt(HeadEnum head, String prompt) {
		addErrorPrompt("【" + head.getFieldName() + "】" + prompt);
	}

	@Override
	public void onException(Exception exception, AnalysisContext context) {
		exception.printStackTrace();
	}



	protected RuntimeException throwWrongFormatException(String fieldName) {
		return new RuntimeException("【" + fieldName + "】字段格式不对");
	}

	//<editor-fold defaultstate="collapsed" desc="校验转换">

	protected String toStr(final Object value) {
		if (isEmpty(value)) {
			return null;
		}
		return String.valueOf(value);
	}

	/**
	 * 转换单元格数据
	 *
	 * @param convert 转换器
	 * @param <T>     数据格式
	 * @return 高阶转换器
	 */
	protected <T> BiFunction<String, String, T> convertCellData(Function<String, T> convert) {
		return (fieldName, data) -> {
			try {
				return convert.apply(data);
			} catch (Exception e) {
				throw throwWrongFormatException(fieldName);
			}
		};
	}

	/**
	 * 转换单元格数据（会抛出字段内容系统中不存在）
	 *
	 * @param convert 转换器
	 * @param <T>     数据格式
	 * @return 高阶转换器
	 */
	protected <T> BiFunction<String, String, T> convertWithNotExist(BiFunction<String, String, T> convert) {
		return (fieldName, data) -> {
			if (isEmpty(data)) {
				return null;
			}
			T value = convert.apply(fieldName, data);
			if (isEmpty(value)) {
				throw new RuntimeException("【" + fieldName + "】字段内容系统中不存在");
			} else {
				return value;
			}
		};
	}

	/**
	 * 转换单元格数据（会抛出字段内容系统中不存在）
	 *
	 * @param convert 转换器
	 * @param <T>     数据格式
	 * @param msg     提示信息
	 * @return 高阶转换器
	 */
	protected <T> BiFunction<String, String, T> convertWithNotExist(BiFunction<String, String, T> convert, String msg) {
		return (fieldName, data) -> {
			if (isEmpty(data)) {
				return null;
			}
			T value = convert.apply(fieldName, data);
			if (isEmpty(value)) {
				throw new RuntimeException("【" + fieldName + "】" + msg);
			} else {
				return value;
			}
		};
	}


	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="校验数据">

	/**
	 * 校验忽略空值
	 *
	 * @param check 校验表达式
	 * @param <T>   数据类型
	 * @return 校验表达式
	 */
	protected <T> Predicate<T> checkIgnoreEmpty(Predicate<T> check) {
		return data -> isEmpty(data) || check.test(data);
	}

	/**
	 * 校验数据内容规范
	 *
	 * @param check 校验表达式
	 * @param <T>   数据类型
	 * @return 带提示的校验表达式
	 */
	protected <T> BiPredicate<String, T> checkNotMeetSpec(Predicate<T> check) {
		return checkData(checkIgnoreEmpty(check), "字段内容不符合规范");
	}

	/**
	 * 校验数据内容规范
	 *
	 * @param check 校验表达式
	 * @param <T>   数据类型
	 * @return 带提示的校验表达式
	 */
	protected <T> BiPredicate<String, T> checkNotMeetSpec(Predicate<T> check, String prompt) {
		return checkData(checkIgnoreEmpty(check), prompt);
	}

	/**
	 * 校验数据并添加提示
	 *
	 * @param check  校验表达式
	 * @param prompt 提示
	 * @param <T>    数据类型
	 * @return 带提示的校验表达式
	 */
	protected <T> BiPredicate<String, T> checkData(Predicate<T> check, String prompt) {
		return (fieldName, data) -> {
			if (check.test(data)) {
				return true;
			} else {
				addErrorPrompt("【" + fieldName + "】" + prompt);
				return false;
			}
		};
	}

	/**
	 * 校验字段是否为空
	 *
	 * @param <T> 数据类型
	 * @return 带提示的校验表达式
	 */
	protected <T> BiPredicate<String, T> checkIsNotEmpty() {
		return checkData(ObjectUtil::isNotEmpty, "字段不能为空");
	}

	/**
	 * 校验字段是否不为空
	 *
	 * @param <T> 数据类型
	 * @return 带提示的校验表达式
	 */
	protected <T> BiPredicate<String, T> checkIsEmpty() {
		return checkData(ObjectUtil::isEmpty, "字段格式不对");
	}

	/**
	 * 校验字段是否为空
	 *
	 * @param <T> 数据类型
	 * @return 带提示的校验表达式
	 */
	protected <T> BiPredicate<String, T> checkLength(int length) {
		return checkNotMeetSpec(data -> data.toString().length() <= length);
	}

	/**
	 * 忽略校验
	 *
	 * @param <T> 数据类型
	 * @return 带提示的校验表达式
	 */
	protected <T> BiPredicate<String, T> checkIgnore() {
		return (fieldName, data) -> true;
	}

	/**
	 * 校验费率0.1-99.9999999999
	 *
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, Double> checkPercentScale() {
		return checkDoubleScale(-100.0, 100.0, 10);
	}

	/**
	 * 校验浮点数
	 *
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, Double> checkDoubleScale() {
		return checkDoubleScale(0.0, Double.MAX_VALUE);
	}

	/**
	 * 校验浮点数
	 *
	 * @param min 最小值
	 * @param max 最大值
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, Double> checkDoubleScale(double min, double max) {
		return checkDoubleScale(min, max, 2);
	}

	/**
	 * 校验浮点数
	 *
	 * @param min   最小值
	 * @param max   最大值
	 * @param scale 位数
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, Double> checkDoubleScale(double min, double max, int scale) {
		return checkNotMeetSpec(data -> data >= min && data <= max && valueOf(data).scale() <= scale);
	}

	/**
	 * 校验百分比bigDecimal
	 */
	protected BiPredicate<String, BigDecimal> checkBigDecimalScale() {
		return checkBigDecimalScale(new BigDecimal("0.00"), new BigDecimal("100.00"));
	}

	/**
	 * 校验百分比bigDecimal
	 *
	 * @param min 最小值
	 * @param max 最大值
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, BigDecimal> checkBigDecimalScale(BigDecimal min, BigDecimal max) {
		return checkBigDecimalScale(min, max, 2);
	}

	/**
	 * 校验百分比bigDecimal
	 *
	 * @param min   最小值
	 * @param max   最大值
	 * @param scale 位数
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, BigDecimal> checkBigDecimalScale(BigDecimal min, BigDecimal max, int scale) {
		return checkNotMeetSpec(data -> min.compareTo(data) < 1 && max.compareTo(data) > -1 && data.scale() <= scale);
	}

	/**
	 * 校验百分比bigDecimal
	 *
	 * @param min    最小值
	 * @param max    最大值
	 * @param scale  位数
	 * @param prompt 提示
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, BigDecimal> checkBigDecimalScale(BigDecimal min, BigDecimal max, int scale, String prompt) {
		return checkNotMeetSpec(data -> min.compareTo(data) < 1 && max.compareTo(data) > -1 && data.scale() <= scale, prompt);
	}

	/**
	 * 校验是否自然数
	 *
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, Integer> checkIntegerPositiveOrZero() {
		return checkIntegerPositive(0, Integer.MAX_VALUE);
	}

	/**
	 * 校验是否正整数
	 *
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, Integer> checkIntegerPositive() {
		return checkIntegerPositive(1, Integer.MAX_VALUE);
	}

	/**
	 * 校验整数
	 *
	 * @param min 最小值
	 * @param max 最大值
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, Integer> checkIntegerPositive(int min, int max) {
		return checkNotMeetSpec(data -> data >= min && data <= max);
	}

	/**
	 *
	 */
	static final String PHONE_PATTERN = "^[1][3-9]\\d{9}$";

	/**
	 * 校验手机号格式
	 *
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, String> checkPhone() {
		return checkNotMeetSpec(data -> Pattern.matches(PHONE_PATTERN, data));
	}

	/**
	 * 根据传入正则校验数据格式
	 *
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, String> checkByPattern(String pattern) {
		return checkNotMeetSpec(data -> Pattern.matches(pattern, data));
	}

	/**
	 * 根据传入正则校验数据格式 及长度校验
	 *
	 * @return 带提示的校验表达式
	 */
	protected BiPredicate<String, String> checkByPatternAndLength(String pattern, int length) {
		return checkNotMeetSpec(data -> Pattern.matches(pattern, data) && data.length() <= length);
	}

	@Override
	public boolean hasNext(AnalysisContext context) {
		row++;
		boolean hasNext = maxCount >= successCount + errorCount;
		if (!hasNext) {
			taskResultEnum = TaskResultEnum.IMPORT_COUNT_ERROR;
			exceptionMsg = "最多支持导入" + maxCount + "条数据！";
		}
		hasNext = (headCheck || row <= headRowNumber()) && hasNext;
		if (!hasNext) {
			doFinish();
		}
		return hasNext;
	}

	protected void doFinish() {
		if (!headCheck) {
			taskResultEnum = TaskResultEnum.IMPORT_FORMAT_ERROR;
			exceptionMsg = "导入模板表头校验不通过，请检查导入模板！";
		}
		if (isEmpty(list) && errorMap.isEmpty() && warnMap.isEmpty() && taskResultEnum == TaskResultEnum.SUCCESS) {
			taskResultEnum = TaskResultEnum.IMPORT_DATA_EMPTY_ERROR;
		}
		success = errorCount == 0 && null == exceptionMsg && isNotEmpty(list);
	}

	protected interface HeadEnum {

		/**
		 * 列索引
		 *
		 * @return 索引
		 */
		Integer getIndex();

		/**
		 * 字段名称
		 *
		 * @return 字段名称
		 */
		String getFieldName();

	}

	@AllArgsConstructor
	@Getter
	@ToString
	protected class CellDataDTO {

		private final Map<Integer, CellData> cellDataMap;

		public String get(HeadEnum head) {
			Object data = cellDataMap.get(head.getIndex());
			return null != data ? String.valueOf(data).trim() : null;
		}

		@SafeVarargs
		public final <T> T get(HeadEnum head, BiFunction<String, String, T> convert, BiPredicate<String, T>... checks) {
			T value;
			try {
				value = convert.apply(head.getFieldName(), get(head));
				if (isEmpty(checks)) {
					return value;
				}
				boolean flag = stream(checks).collect(() -> !isEmpty(checks),
						(r, check) -> r = r && check.test(head.getFieldName(), value), (a, b) -> a = a && b);
				return flag ? value : null;
			} catch (Exception e) {
				addErrorPrompt(e.getMessage());
				return null;
			}
		}

		@SafeVarargs
		public final <T> T get(HeadEnum head, Function<String, T> convert, BiPredicate<String, T>... checks) {
			return get(head, convertCellData(convert), checks);
		}

		@SafeVarargs
		public final <T extends Enum<T>> T getEnum(HeadEnum head, Function<String, T> convert,
												   BiPredicate<String, T>... checks) {
			return get(head, convertWithNotExist((fieldName, data) ->
					convert.apply(convertCellData(this::toStr).apply(fieldName, data))), checks);
		}

		@SafeVarargs
		public final Date getDate(HeadEnum head, String pattern, BiPredicate<String, Date>... checks) {
			return get(head, (fieldName, data) -> {
				String value = convertCellData(this::toStr).apply(fieldName, data);
				if (isNotBlank(value)) {
					try {
						return parseDate(value, pattern);
					} catch (Exception e) {
						throw throwWrongFormatException(fieldName);
					}
				} else {
					return null;
				}
			}, checks);
		}

		@SafeVarargs
		public final Date getDate(HeadEnum head, BiPredicate<String, Date>... checks) {
			return get(head, (fieldName, data) -> {
				String value = convertCellData(this::toStr).apply(fieldName, data);
				if (isNotBlank(value)) {
					try {
						return parseDateTime(value, fieldName);
					} catch (Exception e) {
						throw throwWrongFormatException(fieldName);
					}
				} else {
					return null;
				}
			}, checks);
		}
		private Date parseDateTime(String value, String fieldName) throws ParseException {
			String datePart = value.replaceAll("[-/.年月]", "");
			datePart = StrUtil.removeSuffix(datePart, "日");
			if (DATE_LENGTH == datePart.length()) {
				return parseDate(datePart, DatePattern.PURE_DATE_PATTERN);
			} else if (DATE_LENGTH_SIX == datePart.length()) {
				return parseDate(datePart, DatePattern.SIMPLE_MONTH_PATTERN);
			}
			throw throwWrongFormatException(fieldName);
		}

		protected String dateTimeHandler(String value) {
			String datePart = value.replaceAll("[-/.年月]", "");
			datePart = StrUtil.removeSuffix(datePart, "日");
			return datePart;
		}

		@SafeVarargs
		public final String getStr(HeadEnum head, BiPredicate<String, String>... checks) {
			return get(head, this::toStr, checks);
		}

		@SafeVarargs
		public final Integer getInt(HeadEnum head, BiPredicate<String, Integer>... checks) {
			return get(head, value -> isNotBlank(value) ? Integer.valueOf(value) : null, checks);
		}

		@SafeVarargs
		public final Long getLong(HeadEnum head, BiPredicate<String, Long>... checks) {
			return get(head, value -> isNotBlank(value) ? Long.valueOf(value) : null, checks);
		}

		@SafeVarargs
		public final Double getDouble(HeadEnum head, BiPredicate<String, Double>... checks) {
			return get(head, value -> isNotBlank(value) ? Double.valueOf(value) : null, checks);
		}

		@SafeVarargs
		public final BigDecimal getBigDecimal(HeadEnum head, BiPredicate<String, BigDecimal>... checks) {
			return get(head, value -> isNotBlank(value) ? new BigDecimal(value) : null, checks);
		}


		private String toStr(final String value) {
			return isNotBlank(value) ? value : null;
		}

	}


	public TaskResultEnum getTaskResultEnum() {
		return taskResultEnum;
	}

	public Map<Integer, List<String>> getErrorMap() {
		return errorMap;
	}

	public Map<Integer, List<String>> getWarnMap() {
		return warnMap;
	}
}
