package com.dnegri.springboot.exception.handler;

public class EFPException extends Exception {

	private static final long serialVersionUID = 1L;

	public EFPException(String mensaje)
	{
		this(mensaje, null);
	}
	
	public EFPException(Throwable ex)
	{
		super(null, ex);
	}
	
	public EFPException(String mensaje, Throwable ex)
	{
		super(mensaje , ex);
	}
	
	
}