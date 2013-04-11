package com.brazoft.foundation.jpa;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.brazoft.foundation.jpa.api.AbstractCriteriaStrategy;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class DefaultCriteriaStrategy<T> extends AbstractCriteriaStrategy<T>
{
	@Override
	protected void setCriteria(T search, Criteria query)
	{
		Map<String, Object> fields;
		Object value;

		fields = JPAUtil.getNotNullFields(search);

		for (String fieldName : fields.keySet())
		{
			value = fields.get(fieldName);
			query.add(this.getCriterion(fieldName, value));
		}
	}

	/**
	 * @param fieldName
	 * @param value
	 * @return Returns Criterion
	 */
	protected Criterion getCriterion(String fieldName, Object value)
	{
		String s;

		if (value instanceof String)
		{
			s = (String) value;

			if (s.indexOf("%") > -1)
			{
				return Restrictions.like(fieldName, value);
			}
		}

		return Restrictions.eq(fieldName, value);
	}
}