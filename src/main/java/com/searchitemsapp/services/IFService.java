package com.searchitemsapp.services;

import org.springframework.stereotype.Service;

@SuppressWarnings("unchecked")
@FunctionalInterface
@Service
public interface IFService<R, T> {
	public R service(final T... str);
}