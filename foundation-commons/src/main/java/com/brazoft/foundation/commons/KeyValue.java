package com.brazoft.foundation.commons;

public class KeyValue<K, V> {

  private K key;

  private V value;

  public K getKey() {
    return key;
  }

  public KeyValue<K, V> key(K key) {
    this.key = key;
    return this;
  }

  public V getValue() {
    return value;
  }

  public KeyValue<K, V> value(V value) {
    this.value = value;
    return this;
  }
}