package cn.gdpu.service;

import java.io.Serializable;

public interface ScoreService<T, ID extends Serializable> extends BaseService<T, ID> {
	/**求科目平均分
	 * @param subject
	 * @return
	 */
	public abstract double getAvgSubject(String subject);
	/**求科目最高分
	 * @param subject
	 * @return
	 */
	public abstract double getMaxSubject(String subject);
	/**求科目最低分
	 * @param subject
	 * @return
	 */
	public abstract double getMinSubject(String subject);
}