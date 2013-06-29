package com.brazoft.foundation.util;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
class InputStreamLoader extends org.apache.velocity.runtime.resource.loader.ResourceLoader {

  private InputStream input;

  /**
   * @param input
   */
  public InputStreamLoader(InputStream input) {
    this.input = input;
  }

  @Override
  public long getLastModified(Resource resource) {
    return 0;
  }

  @Override
  public InputStream getResourceStream(String resource) throws ResourceNotFoundException {
    return this.input;
  }

  @Override
  public void init(ExtendedProperties properties) {
    return;
  }

  @Override
  public boolean isSourceModified(Resource resource) {
    return false;
  }
}