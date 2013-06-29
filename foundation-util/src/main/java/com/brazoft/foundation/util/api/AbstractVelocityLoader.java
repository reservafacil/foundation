package com.brazoft.foundation.util.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

import com.brazoft.foundation.util.ResourceHelper;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractVelocityLoader extends FileResourceLoader {

  @Override
  public long getLastModified(Resource resource) {
    return resource.getLastModified();
  }

  @Override
  public InputStream getResourceStream(String resource) throws ResourceNotFoundException {
    Enumeration<URL> resources;

    try {
      resources = ResourceHelper.getMetaResources(resource.substring(1));
      if (resources.hasMoreElements()) {
        return resources.nextElement().openStream();
      }

      return this.getStreamFor(resource);
    } catch (Exception e) {
      throw new ResourceNotFoundException(e.getMessage());
    }
  }

  @Override
  public boolean isSourceModified(Resource resource) {
    return false;
  }

  /**
   * @param resource
   * @return Returns InputStream
   * @throws IOException
   */
  protected abstract InputStream getStreamFor(String resource) throws IOException;
}