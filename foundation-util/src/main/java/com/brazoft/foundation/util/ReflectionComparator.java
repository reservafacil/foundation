package com.brazoft.foundation.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ReflectionComparator<T> implements Comparator<T> {

  public static enum EDirection {
    ASCENDING, DESCENDING;

    int compare(Comparable c1, Comparable c2) {
      if (this.equals(ASCENDING)) {
        return c1.compareTo(c2);
      }

      return c2.compareTo(c1);
    }
  }

  private EDirection direction;

  private List<Field> fields = new ArrayList<Field>();

  public ReflectionComparator(EDirection direction) {
    this.direction = direction;
  }

  public void add(String name, Class<?> clazz) {
    this.add(ReflectionUtil.getField(clazz, name));
  }

  public void add(Field field) {
    if (!Arrays.asList(field.getType().getInterfaces()).contains(Comparable.class)) {
      throw new RuntimeException("The field " + field.getName()
          + " must be a instance of Comparable.");
    }

    this.fields.add(field);
  }

  public int compare(T o1, T o2) {
    int compareTo = 0;
    Comparable<?> c1;
    Comparable<?> c2;

    for (Field field : this.fields) {
      c1 = this.get(field, o1);
      c2 = this.get(field, o2);

      compareTo = this.direction.compare(c1, c2);

      if (compareTo != 0) {
        break;
      }
    }

    return compareTo;
  }

  private Comparable<?> get(Field field, T object) {
    return (Comparable<?>) ReflectionUtil.getValue(object, field);
  }
}