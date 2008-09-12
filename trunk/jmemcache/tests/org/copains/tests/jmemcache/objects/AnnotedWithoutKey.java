package org.copains.tests.jmemcache.objects;

import org.copains.jmemcache.annotations.JMCInstance;

@JMCInstance(instanceName="testAnnot",lifetime=10000,maxElements=1000)
public class AnnotedWithoutKey {

}
