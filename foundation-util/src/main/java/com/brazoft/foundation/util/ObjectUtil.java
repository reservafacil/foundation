package com.brazoft.foundation.util;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ObjectUtil {

  /**
   * @param from
   * @param to
   */
  public static void copy(Object from, Object to) {
    Object value;
    Field toField;
    try {
      if (from == null || to == null) {
        return;
      }

      for (Field field : ReflectionUtil.getFields(from.getClass())) {
        value = ReflectionUtil.getValue(from, field);

        toField = ReflectionUtil.getField(to.getClass(), field.getName());

        if (toField == null) {
          continue;
        }

        ReflectionUtil.setValue(to, value, toField);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param name
   * @param from
   * @param to
   */
  public static void copy(String name, Object from, Object to) {
    try {
      if (from == null || to == null) {
        return;
      }

      BeanUtils.copyProperty(to, name, from);
    } catch (Exception e) {
      ObjectUtil.handle(e);
    }
  }

  /**
   * @param bean
   * @return Returns Object as clone
   */
  public static Object clone(Object bean) {
    try {
      return BeanUtils.cloneBean(bean);
    } catch (Exception e) {
      ObjectUtil.handle(e);
      return null;
    }
  }

  /**
   * @param bean
   * @param values
   */
  public static void populate(Object bean, Map<Object, Object> values) {
    try {
      BeanUtils.populate(bean, values);
    } catch (Exception e) {
      ObjectUtil.handle(e);
    }
  }

  /**
   * @param bean
   * @return Returns composed (by all fields) hashCode
   */
  public static int hashCode(Object bean) {
    HashCodeBuilder builder;

    builder = new HashCodeBuilder();

    for (Field field : bean.getClass().getDeclaredFields()) {
      builder.append(ReflectionUtil.getValue(bean, field));
    }

    return builder.toHashCode();
  }

  /**
   * @param bean
   * @param another
   * @return Returns if all fields between objects are equals
   */
  public static boolean equals(Object bean, Object another) {
    Class<?> beanClass;
    Class<?> anotherClass;
    Object beanValue;
    Object anotherValue;

    beanClass = bean.getClass();
    anotherClass = another.getClass();

    if ((bean == null || another == null) || !beanClass.equals(anotherClass)) {
      return false;
    }

    for (Field field : beanClass.getDeclaredFields()) {
      beanValue = ReflectionUtil.getValue(bean, field);
      anotherValue = ReflectionUtil.getValue(another, field);

      if (beanValue == null || !beanValue.equals(anotherValue)) {
        return false;
      }
    }

    return true;
  }

  private static void handle(Exception e) {
    e.printStackTrace();
    throw new RuntimeException(e);
  }
}