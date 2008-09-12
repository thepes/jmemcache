package org.copains.jmemcache.exceptions;

public class JMemCacheAnnotationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -663369968652190462L;

	private String errorMessage;
	
	public JMemCacheAnnotationException(AnnotationExceptionsTypes type)
	{
		if (type == AnnotationExceptionsTypes.MISSING_INSTANCE)
			errorMessage = "Object not annoted with JMCInstance";
		if (type == AnnotationExceptionsTypes.MISSING_KEY)
			errorMessage = "Missing required aznnotation : JMCKey";
	}
	
	@Override
	public String getMessage() {
		return errorMessage;
	}
	
}
