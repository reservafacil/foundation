package com.brazoft.foundation.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.directive.Directive;

import com.brazoft.foundation.io.StringInputStream;
import com.brazoft.foundation.util.api.TemplateDirective;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public class VelocityUtil {

  private static final VelocityUtil instance = new VelocityUtil();

  private final RuntimeServices runtime;

  private List<String> directives = new ArrayList<String>();

  private VelocityUtil() {
    this.runtime = RuntimeSingleton.getRuntimeServices();
    this.init();
  }

  private void init() {
    this.runtime.addProperty("runtime.log.error.stacktrace", "true");
    this.runtime.addProperty("runtime.log.warn.stacktrace", "false");
    this.runtime.addProperty("runtime.log.info.stacktrace", "false");
    this.runtime.addProperty("runtime.log.invalid.reference", "true");
    this.runtime.addProperty("resource.loader", "file");
    this.runtime.addProperty("velocimacro.permissions.allow.inline", "true");
    this.runtime.addProperty("velocimacro.permissions.allow.inline.to.replace.global", "true");
  }

  public static VelocityUtil getInstance() {
    return VelocityUtil.instance;
  }

  public void addDirective(Class<? extends Directive> clazz) {
    this.directives.add(clazz.getName());
    this.runtime.addProperty("userdirective", this.toUserDirectiveValue());
  }

  public void setDirectivePath(File path) {
    this.runtime.addProperty(TemplateDirective.KEY, path.getAbsolutePath());
  }

  public void build() {
    this.runtime.init();
  }

  public InputStream merge(VelocityContext context, org.apache.velocity.Template template)
      throws Exception {
    return new StringInputStream(this.toString(context, template));
  }

  public String toString(VelocityContext context, org.apache.velocity.Template template)
      throws Exception {
    StringWriter writer;

    writer = new StringWriter();
    template.merge(context, writer);

    return writer.toString();
  }

  public Template createTemplate(String name, byte[] content) throws Exception {
    return createTemplate(name, new ByteArrayInputStream(content));
  }

  public Template createTemplate(String name, InputStream content) throws Exception {
    Template template;

    template = new org.apache.velocity.Template();
    template.setName(name);
    template.setRuntimeServices(this.runtime);
    template.setResourceLoader(new InputStreamLoader(content));
    template.process();

    return template;
  }

  private String toUserDirectiveValue() {
    StringBuffer csv;

    csv = new StringBuffer();

    for (String value : this.directives) {
      csv.append(value).append(",");
    }

    csv.delete(csv.length() - 1, csv.length());

    return csv.toString();
  }
}